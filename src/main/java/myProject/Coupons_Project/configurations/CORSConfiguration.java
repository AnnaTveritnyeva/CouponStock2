package myProject.Coupons_Project.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfiguration {
    @Bean
    public CorsFilter corsFilter(){
        //create new CORS configuration...
        CorsConfiguration config = new CorsConfiguration();
        //allow get credentials in CORS
        config.setAllowCredentials(true);
        //allow get from any ip/domain
        config.addAllowedOrigin("*");
        //allow to get any header
        config.addAllowedHeader("*");
        //tell which VERB is allowed
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        //allow to get any route -> localhost:8080/api/coupon-> /api/coupon and implement the config
        //create new url configuration for browsers
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        //return new configuration
        return new CorsFilter(source);
    }
}

