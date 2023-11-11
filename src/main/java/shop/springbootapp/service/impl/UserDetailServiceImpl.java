package shop.springbootapp.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import shop.springbootapp.model.entity.Role;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.repository.UserRepository;

import java.util.stream.Collectors;

public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email" + email + " not found!"));
    }

    private UserDetails map(AppUser appUser) {
        return User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities(appUser.getRoles().stream().map(this::map).collect(Collectors.toSet()))
                .build();
    }
    private GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getRole().name());
    }
}