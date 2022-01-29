package com.daofab.assessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info; 


@Configuration
public class SwaggerConfig {

	  @Bean
	    public OpenAPI openApiConfig() {
	        return new OpenAPI().info(apiInfo());
	    }

	    public Info apiInfo() {
	        Info info = new Info();
	        info
	                .title("DAOFAB assessment API")
	                .description("DAOFAB assessment Swagger Open API")
	                .version("v1.0.0");
	        return info;
	    }

}
