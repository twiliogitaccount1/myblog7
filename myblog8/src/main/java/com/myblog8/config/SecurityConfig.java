package com.myblog8.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter
{
   //step 4
    @Autowired
    private CustomUserDetailsService userDetailsService ;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean() ;
    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected  void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers(HttpMethod.GET , "/api/**").permitAll()
                .antMatchers(HttpMethod.POST , "/api/auth/**").permitAll()
              //  .antMatchers(HttpMethod.GET , "/api/**").hasRole("ADMIN")
              //  .antMatchers(HttpMethod.GET , "/api/**").hasRole("USER")
              //  .antMatchers(HttpMethod.GET , "/api/**").hasAnyRole("USER","ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

   // @Override
   // @Bean
  //  protected UserDetailsService userDetailsService()
   // {
      //  UserDetails user = User.builder().username("user").password(passwordEncoder()
               // .encode("password")).roles("user").build();

     //   UserDetails admin = User.builder().username("admin").password(passwordEncoder()
              //  .encode("password")).roles("ADMIN").build();

       // return new InMemoryUserDetailsManager(user , admin);
  //  }


@Override
    protected void  configure(AuthenticationManagerBuilder auth) throws Exception
{
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}




}
