package shop.springbootapp.configuration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.repository.UserRepository;
import shop.springbootapp.service.impl.MyUserDetailServiceImpl;
import shop.springbootapp.util.MyAuthenticationSuccessHandler;
import shop.springbootapp.util.MyLogoutSuccessHandler;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.authorizeHttpRequests(
                authorizeRequest -> authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/users/login", "/users/register").permitAll()
                        .requestMatchers("/owner").hasRole(RoleNameEnum.OWNER.name())
                        .requestMatchers("/admin").hasRole(RoleNameEnum.ADMIN.name())
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> formLogin
                        .loginPage("/users/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .successHandler(myAuthenticationSuccessHandler())
                        .failureForwardUrl("/users/error?continue")
        ).logout(
                logout -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/")
                        .logoutSuccessHandler(myLogoutSuccessHandler())
                        .deleteCookies("SESSIONID")
                        .invalidateHttpSession(true)

                //TODO: remember me!
        ).sessionManagement(
            httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                    .maximumSessions(1)
                    .sessionRegistry(sessionRegistry()))
        .build();
    }
    @Bean
    public MyUserDetailServiceImpl userDetailService(UserRepository userRepository){
        return new MyUserDetailServiceImpl(userRepository);
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public MyLogoutSuccessHandler myLogoutSuccessHandler(){
        return new MyLogoutSuccessHandler();
    }
    @Bean
    public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MyAuthenticationSuccessHandler();
    }
}
