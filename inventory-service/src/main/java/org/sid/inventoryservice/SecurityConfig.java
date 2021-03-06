package org.sid.inventoryservice;

import org.sid.inventoryservice.filters.JwtAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   /* private UserDetailServiceImpl userDetailService;

    public SecurityConfig(UserDetailServiceImpl userDetailService) {

        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);

    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //disable csrf if we use authtifcation without session , enable it when using session , csrf utilse les sessions
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //disable frame
        http.headers().frameOptions().disable();

        http.authorizeRequests().antMatchers("/h2-console/**", "/refreshToken**", "/login/**").permitAll();
        //pour autoriser tous les requetes
        // http.authorizeRequests().anyRequest().permitAll();
        // pour afficher le formulaire d'authtification
        // http.formLogin();

        /** ça une méthode l'autre méthode et par l'annotaition , on va aller sur le class main et ajouter @EnableGlablMEHTOdSecurity **/
        // http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("ADMIN");
        //http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("USER");

        http.authorizeRequests().anyRequest().authenticated();
        // ajout d'un filtre
       // http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

   /* @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/
}
