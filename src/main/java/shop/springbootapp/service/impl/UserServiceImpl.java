package shop.springbootapp.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.model.service.UserServiceModel;
import shop.springbootapp.repository.RoleRepository;
import shop.springbootapp.repository.UserRepository;
import shop.springbootapp.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository  roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionRegistry sessionRegistry;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, SessionRegistry sessionRegistry, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRegistry = sessionRegistry;
        this.modelMapper = modelMapper;
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
    @Transactional
    @Override
    public void changeLastLoginTime(String name) {
        this.userRepository.setLastLoginTime(name);
    }
    public int getUsersFromSessionRegistryCount() {
        return sessionRegistry.getAllPrincipals().size();
    }

    @Override
    public List<AppUser> getLastLoggedUsers() {
        return this.userRepository.findAllLLoggedIn();
    }

    @Override
    public AppUser registerUser(UserServiceModel userServiceModel) {

        userServiceModel.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        userServiceModel.getRoles().add(roleRepository.findByRole(RoleNameEnum.USER).orElse(null));

        return this.userRepository
                .save(modelMapper.map(userServiceModel, AppUser.class));
    }
}
