package learn.wingit.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/create-account").permitAll()
                .antMatchers("/api/manufacturers/*").hasRole("ADMIN")
                .antMatchers("/api/types/*").hasRole("ADMIN")
                .antMatchers("/api/sizes/*").hasRole("ADMIN")
                .antMatchers("/api/models/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/orders/user/*").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/orders").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/orders/*").hasAnyRole()
                .antMatchers(HttpMethod.GET, "/api/planes").permitAll()
                .antMatchers("/api/planes/hidden/all/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/planes/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/planes/id/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/planes/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/planes").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/planes/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/planes/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/users/*").hasAnyRole()
                .antMatchers(HttpMethod.DELETE, "/api/users/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users/").hasRole("ADMIN")
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
