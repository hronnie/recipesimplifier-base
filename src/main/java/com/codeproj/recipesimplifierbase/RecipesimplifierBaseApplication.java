package com.codeproj.recipesimplifierbase;

import com.codeproj.recipesimplifierbase.common.RecipeImageFileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EntityScan("com.codeproj.recipesimplifierbase.model")
@EnableJpaRepositories("com.codeproj.recipesimplifierbase.data.repo")
@EnableTransactionManagement
@EnableConfigurationProperties({
        RecipeImageFileStorageProperties.class
})
public class RecipesimplifierBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesimplifierBaseApplication.class, args);
	}
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
}



