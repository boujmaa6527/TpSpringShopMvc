
package fr.fms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception{

        PasswordEncoder passWord =   passwordEncoder();
        authBuilder.inMemoryAuthentication().withUser("sam").password(passWord.encode("12345")).roles("ADMIN", "USER");
        authBuilder.inMemoryAuthentication().withUser("rachid").password(passWord.encode("12345")).roles("USER");

        authBuilder.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());

    }
    @Bean
    PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin();

        //http.authorizeRequests().antMatchers("/save", "/delete", "/edit","/update","/article").hasRole("ADMIN");
        //http.authorizeRequests().antMatchers().hasRole("USER");
    }
}

