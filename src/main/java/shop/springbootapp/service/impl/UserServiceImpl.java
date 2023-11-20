package shop.springbootapp.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.model.events.UserRegistrationEvent;
import shop.springbootapp.model.service.UserServiceModel;
import shop.springbootapp.repository.RoleRepository;
import shop.springbootapp.repository.UserRepository;
import shop.springbootapp.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionRegistry sessionRegistry;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, SessionRegistry sessionRegistry, ModelMapper modelMapper, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRegistry = sessionRegistry;
        this.modelMapper = modelMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public AppUser findByUsername(String username) {
        return null;
    }

    @Override
    public void initUser() {
        if(this.userRepository.count() != 0) {
            return;
        }
        AppUser appUser = new AppUser();
        appUser.setUsername("admin");
        appUser.setEmail("admin@example.com");
        appUser.setPhoneNumber("052112");
        appUser.setPassword(passwordEncoder.encode("test"));
        appUser.setRegistered(LocalDateTime.now());
        appUser.getRoles().add(this.roleRepository.findByRole(RoleNameEnum.ADMIN).orElse(null));
        this.userRepository.save(appUser);
    }
    @Override
    public void changeLastLoginTime(String name) {
        this.userRepository.setLastLoginTime(name);

    }
    @Override
    public int getUsersFromSessionRegistryCount() {
        return this.sessionRegistry.getAllPrincipals().size();
    }

    @Override
    public List<AppUser> getLastLoggedUsers() {
        return this.userRepository.findAllLLoggedIn();
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        userServiceModel.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        userServiceModel.setRegistered(LocalDateTime.now());
        userServiceModel.getRoles().add(roleRepository.findByRole(RoleNameEnum.USER).orElse(null));

        this.userRepository.save(modelMapper.map(userServiceModel, AppUser.class));

        this.applicationEventPublisher.publishEvent(new UserRegistrationEvent("UserService", userServiceModel.getEmail(), userServiceModel.getUsername()));

    }
}
