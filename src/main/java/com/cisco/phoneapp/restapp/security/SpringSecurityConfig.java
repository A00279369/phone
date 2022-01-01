package com.cisco.phoneapp.restapp.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

        // Create 2 users for demo
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.inMemoryAuthentication()
                    .withUser("user").password("{noop}password").roles("USER")
                    .and()
                    .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

        }

        // Secure the endpoins with HTTP Basic authentication
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    //HTTP Basic authentication
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/user/**").hasRole("USER")
                    .antMatchers(HttpMethod.POST, "/user").hasRole("USER")
                    .antMatchers(HttpMethod.PUT, "/user/**").hasRole("USER")
                    .antMatchers(HttpMethod.PATCH, "/user/**").hasRole("USER")
                    .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("USER")

                    .antMatchers(HttpMethod.GET, "/phone/**").hasRole("USER")
                    .antMatchers(HttpMethod.POST, "/phone").hasRole("USER")
                    .antMatchers(HttpMethod.PUT, "/phone/**").hasRole("USER")
                    .antMatchers(HttpMethod.PATCH, "/phone/**").hasRole("USER")
                    .antMatchers(HttpMethod.DELETE, "/phone/**").hasRole("USER")


                    .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .headers().frameOptions().sameOrigin();
        }

    /*@Bean
    public UserDetailsService userDetailsService() {
        //ok for demo
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }*/





    }
