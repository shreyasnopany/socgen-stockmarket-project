package userservice.security.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import userservice.entity.UserType;
import userservice.security.jwt.JwtConfig;
import userservice.security.jwt.JwtTokenVerifier;
import userservice.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import userservice.service.UserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@NoArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecretKey secretKey;
    private JwtConfig jwtConfig;
    private UserService userService;
    static UserType userType;

    @Autowired
    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService, SecretKey secretKey,
                             JwtConfig jwtConfig) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable().authorizeRequests()
        // .antMatchers("/users/register/**", "/users/validateUsername/**",
        // "/users/validateEmail/**").permitAll()
        // .anyRequest().authenticated();
        http.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/users/register/**", "/users/validateUsername/**", "/users/validateEmail/**")
                .permitAll().antMatchers("/users/update", "/users/details").hasAuthority(userType.USER.name())
                .antMatchers("/admin/**").hasAuthority(userType.ADMIN.name()).anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

}
