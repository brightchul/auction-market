package io.youngwon.app.config.auth;


import io.youngwon.app.domain.users.domain.UsersRepository;
import io.youngwon.app.security.Jwt;
import io.youngwon.app.security.JwtAccessDeniedHandler;
import io.youngwon.app.security.JwtAuthenticationProvider;
import io.youngwon.app.security.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final Jwt jwt;

    private final JwtAccessDeniedHandler accessDeniedHandler;

    private final JwtTokenConfigure jwtTokenConfigure;

    private final EntryPointUnauthorizedHandler unauthorizedHandler;



    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(jwtTokenConfigure.getHeader(), jwt);
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(UsersRepository usersRepository) {
        return new JwtAuthenticationProvider(usersRepository);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable()

            .and()
            .authorizeRequests()
            .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
            .antMatchers("/api/**").permitAll()//.anyRequest()
            .and()
            .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler)
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .logout()
            .logoutSuccessUrl("/");

//                .oauth2Login()
//                .userInfoEndpoint();
//                .userService(customOAuth2UserService);
        http
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
