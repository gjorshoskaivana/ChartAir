package mk.ukim.finki.chartair.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;

    public SecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider authenticationProvider)
    {
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2");
        web.ignoring().antMatchers("/h2/**");
    }

    @Override
     protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .authorizeRequests()
                 .antMatchers("/", "/home", "/register").permitAll()
                 .antMatchers("/admin/**").hasRole("ADMIN")
                 .anyRequest()
                 .authenticated()
                 .and()
                 .formLogin()
                 .loginPage("/login").permitAll()
                 .failureUrl("/login?error=BadCredentials")
                 .defaultSuccessUrl("/", true)
                 .and()
                 .logout()
                 .logoutUrl("/logout")
                 .clearAuthentication(true)
                 .invalidateHttpSession(true)
                 .deleteCookies("JSESSIONID")
                 .logoutSuccessUrl("/login")
                 .and()
                 .exceptionHandling().accessDeniedPage("/access_denied");

     }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("user"))
                .authorities("ROLE_USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities("ROLE_ADMIN");*/
        auth.authenticationProvider(authenticationProvider);

    }


}

