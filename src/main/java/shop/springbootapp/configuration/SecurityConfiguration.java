package shop.springbootapp.configuration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.repository.UserRepository;
import shop.springbootapp.service.impl.UserDetailServiceImpl;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.authorizeHttpRequests(
                authorizeRequest -> authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/users/login", "/users/register").permitAll()
                        .requestMatchers("/owner").hasRole(RoleNameEnum.OWNER.name())
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> formLogin
                        .loginPage("/users/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureForwardUrl("/users/error?continue")
        ).logout(
                logout -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)

                //TODO: remember me!
        ).build();
    }
    @Bean
    public UserDetailServiceImpl userDetailService(UserRepository userRepository){
        return new UserDetailServiceImpl(userRepository);
    }

}
