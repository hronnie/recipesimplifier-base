package com.codeproj.recipesimplifierbase.rest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mobile.device.Device;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.codeproj.recipesimplifierbase.common.DeviceDummy;
import com.codeproj.recipesimplifierbase.common.DeviceProvider;
import com.codeproj.recipesimplifierbase.common.TimeProvider;
import com.codeproj.recipesimplifierbase.data.dao.impl.CustomUserDetailsDAOImpl;
import com.codeproj.recipesimplifierbase.model.Authority;
import com.codeproj.recipesimplifierbase.model.User;
import com.codeproj.recipesimplifierbase.rest.AuthenticationController;
import com.codeproj.recipesimplifierbase.security.TokenHelper;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationControllerTest {

    private MockMvc mvc;

    @MockBean
    private TimeProvider timeProviderMock;

    private static final String TEST_USERNAME = "testUser";

    @Autowired
    private TokenHelper tokenHelper;

    @MockBean
    private CustomUserDetailsDAOImpl userDetailsService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DeviceProvider deviceProvider;

    @Autowired
    private DeviceDummy device;

    @Before
    public void setup() {

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        User user = new User();
        user.setUsername("username");
        Authority authority = new Authority();
        authority.setId(0L);
        authority.setName("ROLE_USER");
        List<Authority> authorities = Arrays.asList(authority);
        user.setAuthorities(authorities);
        user.setLastPasswordResetDate(new Timestamp(DateUtil.yesterday().getTime()));
        when(this.userDetailsService.loadUserByUsername(eq("testUser"))).thenReturn(user);
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(tokenHelper, "EXPIRES_IN", 100); // 100 sec
        ReflectionTestUtils.setField(tokenHelper, "MOBILE_EXPIRES_IN", 200); // 200 sec
        ReflectionTestUtils.setField(tokenHelper, "SECRET", "queenvictoria");

        device.setMobile(false);
        device.setNormal(false);
        device.setTablet(false);
    }

    @Test
    public void shouldGetEmptyTokenStateWhenGivenValidOldToken() throws Exception {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.yesterday());
        this.mvc.perform(post("/auth/refresh")
                .header("Authorization", "Bearer 123"))
                .andExpect(content().json("{access_token:null,expires_in:null}"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldRefreshNotExpiredWebToken() throws Exception {

        given(timeProviderMock.now())
                .willReturn(new Date(30L));

        device.setNormal(true);
        String token = createToken(device);
        String refreshedToken = tokenHelper.refreshToken(token, device);
        given(deviceProvider.getCurrentDevice(any()))
                .willReturn(device);
        this.mvc.perform(post("/auth/refresh")
                .header("Authorization", "Bearer " + token))
                .andExpect(content().json("{access_token:" + refreshedToken + ",expires_in:100}"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldRefreshNotExpiredMobileToken() throws Exception {
        given(timeProviderMock.now())
                .willReturn(new Date(30L));
        device.setMobile(true);
        String token = createToken(device);
        String refreshedToken = tokenHelper.refreshToken(token, device);
        given(deviceProvider.getCurrentDevice(any()))
                .willReturn(device);
        this.mvc.perform(post("/auth/refresh")
                .header("Authorization", "Bearer " + token))
                .andExpect(content().json("{access_token:" + refreshedToken + ",expires_in:200}"));
    }

    @Test
    public void shouldNotRefreshExpiredWebToken() throws Exception {
        Date beforeSomeTime = new Date(DateUtil.now().getTime() - 15 * 1000);
        when(timeProviderMock.now())
                .thenReturn(beforeSomeTime);
        device.setNormal(true);
        String token = createToken(device);
        this.mvc.perform(post("/auth/refresh")
                .header("Authorization", "Bearer " + token))
                .andExpect(content().json("{access_token:null,expires_in:null}"));
    }

    @Test
    public void shouldRefreshExpiredMobileToken() throws Exception {
        Date beforeSomeTime = new Date(DateUtil.now().getTime() - 15 * 1000);
        when(timeProviderMock.now())
                .thenReturn(beforeSomeTime);
        device.setNormal(true);
        String token = createToken(device);
        this.mvc.perform(post("/auth/refresh").header("Authorization", "Bearer " + token))
                .andExpect(content().json("{access_token:null,expires_in:null}"));
    }
    
    @Test
    public void testCreateAuthentication() {
    		
    }
 
    private String createToken(Device device) {
        return tokenHelper.generateToken(TEST_USERNAME, device);
    }
}
