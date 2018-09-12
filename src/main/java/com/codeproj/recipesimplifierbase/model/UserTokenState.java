package com.codeproj.recipesimplifierbase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class UserTokenState {
	
    private String access_token;
    private Long expires_in;

    public UserTokenState() {
        this.access_token = null;
        this.expires_in = null;
    }
       
}