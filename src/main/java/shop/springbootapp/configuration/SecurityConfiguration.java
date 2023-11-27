package shop.springbootapp.configuration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.repository.UserRepository;
import shop.springbootapp.service.impl.MyUserDetailServiceImpl;
import shop.springbootapp.util.MyAuthenticationSuccessHandler;
import shop.springbootapp.util.MyLogoutSuccessHandler;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequest -> authorizeRequest
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/cart/**").permitAll()
                                .requestMatchers("/", "/users/login", "/users/register", "/users/activation").permitAll()
                                .requestMatchers("/owner").hasRole(RoleNameEnum.OWNER.name())
                                .requestMatchers("/admin").hasRole(RoleNameEnum.ADMIN.name())
                                .anyRequest().authenticated()
                ).formLogin(
                        formLogin -> formLogin
                                .loginPage("/users/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                                .successHandler(myAuthenticationSuccessHandler())
                                .failureForwardUrl("/users/error?continue")
                ).logout(
                        logout -> logout
                                .logoutUrl("/users/logout")
                                .logoutSuccessUrl("/")
                                .logoutSuccessHandler(myLogoutSuccessHandler())
                                .deleteCookies("JSESSIONID")
                                .invalidateHttpSession(true)
                ).

                sessionManagement(
                        httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                                .maximumSessions(1)
                                .sessionRegistry(sessionRegistry()))
                .build();
    }

    @Bean
    public MyUserDetailServiceImpl userDetailService(UserRepository userRepository) {
        return new MyUserDetailServiceImpl(userRepository);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public MyLogoutSuccessHandler myLogoutSuccessHandler() {
        return new MyLogoutSuccessHandler();
    }

    @Bean
    public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }
}