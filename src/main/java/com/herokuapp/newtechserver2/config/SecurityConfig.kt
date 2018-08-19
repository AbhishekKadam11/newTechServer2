package com.herokuapp.newtechserver2.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // Note:
        // Use this to enable the tomcat basic authentication (tomcat popup rather than spring login page)
        // Note that the CSRf token is disabled for all requests (change it as you wish...)
        http.csrf().disable().authorizeRequests().antMatchers("/graphiql").permitAll()
    }

}