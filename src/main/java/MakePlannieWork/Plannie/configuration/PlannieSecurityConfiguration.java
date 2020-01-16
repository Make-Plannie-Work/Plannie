package MakePlannieWork.Plannie.configuration;

import MakePlannieWork.Plannie.service.PlannieGebruikersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
@EnableGlobalMethodSecurity(securedEnabled = true)
public class PlannieSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    PlannieGebruikersService plannieGebruikersService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers("/registreren", "/resources/**", "/src/main/webapp/static/**", "/wachtwoordReset", "/wijzigWachtwoord*").permitAll()
                    .antMatchers("/user/savePassword*","/gebruikerWachtwoordUpdate*", "/wijzigWachtwoord*", "/testdata").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/index")
                    .defaultSuccessUrl("/gebruikerDetail")
                    .usernameParameter("username").passwordParameter("password")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/index")
                    .permitAll()
                ;

    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(plannieGebruikersService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    WebMvcConfigurer myWebMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                ViewControllerRegistration r = registry.addViewController("/index");
                r.setViewName("login");
            }
        };
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
