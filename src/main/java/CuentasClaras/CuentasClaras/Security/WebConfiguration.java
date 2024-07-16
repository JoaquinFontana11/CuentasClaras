package CuentasClaras.CuentasClaras.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfiguration {

    
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
    	http.csrf(csrf -> csrf.disable())
    	.authorizeHttpRequests(authz -> authz
    			.requestMatchers("/").permitAll()
    			.anyRequest().authenticated()
    			).httpBasic(httpBasic -> {});
    	return http.build();
    }
}