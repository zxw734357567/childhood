package com.zxw.childhood.jojoauth.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zxw
 * @date 2020-01-10 16:32
 */
@EnableWebSecurity
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        DelegatingPasswordEncoder delegatingPasswordEncoder =
                (DelegatingPasswordEncoder)  PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return delegatingPasswordEncoder;


    }
/*
    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http
//                        .exceptionHandling().accessDeniedHandler(new AuthAccessDeniedHandler())
//                        .authenticationEntryPoint(new AuthAuthenticationEntryPoint())
//                        .and()
                        .formLogin().loginPage("/authentication/require")
                        .loginProcessingUrl("/authentication/form")
                        //.successHandler(new AuthAuthenticationSuccessHandler())
                        .failureHandler(new AuthAuthenticationFailureHandler())
                        .and()
                        .logout().logoutUrl("/logout")
                        .logoutSuccessHandler(new AuthLogoutSuccessHandler())
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("vole")
                        .and()
                        .authorizeRequests();
        filterIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable();
        //配置手机号获取token
    }*/

}
