package com.zxw.childhood.jojoauth.configurations;

import com.zxw.childhood.jojoauth.utils.messages.AuthAuthenticationFailureHandler;
import com.zxw.childhood.jojoauth.utils.messages.AuthLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.annotation.Resource;

/**
 * @author zxw
 * @date 2020-01-10 16:32
 */
@EnableWebSecurity
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Resource
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //下面这两行配置表示在内存中配置了两个用户
        auth.inMemoryAuthentication()
                .withUser("zhangsan").roles("admin").authorities("ad").password(new BCryptPasswordEncoder().encode("123"))
                .and()
                .withUser("lisi").roles("user").authorities("ap").password(new BCryptPasswordEncoder().encode("123"));
    }*/
   /* @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        User.UserBuilder builder = User.builder();
        UserDetails user = builder.username("zhangsan").password("$2a$10$GStfEJEyoSHiSxnoP3SbD.R8XRowP1QKOdi.N6/iFEwEJWTQqlSba").roles("USER").build();
        UserDetails admin = builder.username("lisi").password("$2a$10$GStfEJEyoSHiSxnoP3SbD.R8XRowP1QKOdi.N6/iFEwEJWTQqlSba").roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/


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
                        .authorizeRequests().antMatchers("/authentication/require").permitAll()
                ;
        filterIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable();
        //配置手机号获取token
       // http.apply(mobileSecurityConfigurer);
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
public static void main(String[] args) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    System.out.println(bCryptPasswordEncoder.encode("123456"));
    System.out.println(bCryptPasswordEncoder.encode("12345678"));
}
}
