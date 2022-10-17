package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// this is a web security configuration adapter for web security stuff
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /*this is the implementation of  WebSecurityConfigurerAdapter, and we override configure method here to give roles
        and password.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //set you configuration of authentication managerBuilder
        auth.inMemoryAuthentication()
                .withUser("foo")
                .password("foo")
                .roles("USER")
                .and()
                .withUser("blah")
                .password("blah")
                .roles("ADMIN");
    }

    // to secure your password with passwordencoder to change your password to base64 encoding
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    //override configure method with http security so we can add  permissions to our url or api endpoint
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http security implementation
        http.authorizeRequests()// authorize requests
                .antMatchers("/", "static/css", "static/js")// authorize all requests to this urls for all users
                .permitAll()// permissions to all users
                .antMatchers("/admin").hasRole("ADMIN")//  /admin endpoint is allowed to admin
                .antMatchers("/user").hasAnyRole("USER" , "ADMIN")// /user endpoint is allowed to admin and user
                .and()// and is basically allowed to use other methods (as per english language rules)
                .formLogin();// at the end form popup to login details as per url endpoint.

    }
}
