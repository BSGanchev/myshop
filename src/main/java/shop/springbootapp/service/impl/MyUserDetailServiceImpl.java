package shop.springbootapp.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.entity.Role;
import shop.springbootapp.repository.UserRepository;

import java.util.stream.Collectors;

public class MyUserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with username" + username + " not found!"));
    }

    private UserDetails map(AppUser appUser) {
        return User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .disabled(appUser.isDisabled())
                .authorities(appUser.getRoles().stream().map(this::map).collect(Collectors.toSet()))
                .build();
    }
    private GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getRole().name());

    }
}
