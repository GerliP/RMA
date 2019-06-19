package rma.project.rma.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import rma.project.rma.Repos.UserRepository;


import javax.sql.DataSource;


/**
 * Created by gerli on 06/03/2018.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource; // dataSource bean in the WebMvcConfig class


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // in WebMvcConfig class

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    UserRepository userRepository;


    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource);
        jdbcImpl.setUsersByUsernameQuery(usersQuery);
       jdbcImpl.setAuthoritiesByUsernameQuery(rolesQuery);
        return jdbcImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                csrf().disable().
                authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**" ).permitAll() // antMatcher() - specifies that request to a certain path must be authenticated.
                .antMatchers("/warranty", "/claimConfirmation/**").permitAll()
                .antMatchers("/login","/create","/newuser/**", "/api/**").permitAll()
                .antMatchers("/admin/**", "/users").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().formLogin().loginPage("/login")
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .rememberMe()
                .tokenValiditySeconds(5000)
                .key("appKey")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // logout when you get a GET request
                .logoutSuccessUrl("/");
//                .logoutSuccessUrl("/").and().exceptionHandling()
//                .accessDeniedPage("/access-denied");
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/");
//    }

}
