package shop.springbootapp.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.repository.RoleRepository;
import shop.springbootapp.repository.UserRepository;
import shop.springbootapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository  roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser findByEmail(String email) {
        return null;
    }

    @Override
    public void initUser() {
        if (this.userRepository.count() != 0) {
            return;
        }
        AppUser user = new AppUser();
        user.setEmail("admin@example.com");
        user.setPassword(passwordEncoder.encode("test"));
        user.setUsername("admin");
        user.setPhoneNumber("052");
        user.getRoles().add(this.roleRepository.findByRole(RoleNameEnum.ADMIN).orElse(null));
        this.userRepository.save(user);
        AppUser user1 = new AppUser();

        user1.setEmail("owner@example.com");
        user1.setPassword(passwordEncoder.encode("test"));
        user1.setUsername("owner");
        user1.setPhoneNumber("052");
        user1.getRoles().add(this.roleRepository.findByRole(RoleNameEnum.OWNER).orElse(null));
        this.userRepository.save(user1);
    }
}
