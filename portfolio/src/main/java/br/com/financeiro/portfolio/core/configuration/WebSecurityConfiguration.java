package br.com.financeiro.portfolio.core.configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration implements HttpSessionListener {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String defaultSucessUrl = "/home";//String.format("/%s", TelasType.DASHBOARD.toString().toLowerCase());
        
        String[] authorizedResources = { 
                "/img/**", 
                "/css/**", 
                "/js/**", 
                "/vendor/**", 
                "/login/**",
                "/user/forgot-password",
                "/user/register-user",
                "/user/change-password" };

        return http
                .authorizeRequests()
                .antMatchers(authorizedResources)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl(defaultSucessUrl, true)
                    .permitAll())
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true))
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configurando 30 minutos (1800ms) como timeout padrão para a sessão HTTP
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(1800);
    }

}