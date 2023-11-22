package shop.springbootapp.service.impl;

import jakarta.persistence.Id;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.model.events.UserRegistrationEvent;
import shop.springbootapp.model.service.UserServiceModel;
import shop.springbootapp.repository.RoleRepository;
import shop.springbootapp.repository.UserRepository;
import shop.springbootapp.service.UserActivationService;
import shop.springbootapp.service.UserService;
import shop.springbootapp.service.exception.UserAlreadyExistException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionRegistry sessionRegistry;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserActivationService userActivationService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, SessionRegistry sessionRegistry, ModelMapper modelMapper, ApplicationEventPublisher applicationEventPublisher, UserActivationService userActivationService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRegistry = sessionRegistry;
        this.modelMapper = modelMapper;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userActivationService = userActivationService;
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
    public List<AppUser> getLoggedUsers() {
        List<Object> principals = this.sessionRegistry.getAllPrincipals();
        return principals.stream()
                .map(principal -> modelMapper.map(principal, AppUser.class))
                .toList()
                .stream().map(appUser-> {
                    AppUser loggedUser = this.userRepository.findByUsername(appUser.getUsername()).orElse(null);
                    modelMapper.map(loggedUser, AppUser.class);
                    return loggedUser;
                }).collect(Collectors.toList());
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        if(emailExist(userServiceModel)){
            throw new UserAlreadyExistException(
                    "There is an account with that email address: "
                            + userServiceModel.getEmail());
        }

        userServiceModel.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        userServiceModel.setRegistered(LocalDateTime.now());
        userServiceModel.getRoles().add(roleRepository.findByRole(RoleNameEnum.USER).orElse(null));
        userServiceModel.setDisabled(true);
        this.userRepository.save(modelMapper.map(userServiceModel, AppUser.class));

        this.applicationEventPublisher.publishEvent(new UserRegistrationEvent("UserService", userServiceModel.getEmail(), userServiceModel.getUsername()));

    }
    private boolean emailExist(UserServiceModel userServiceModel) {
        return this.userRepository.findByEmail(userServiceModel.getEmail()).isPresent();
    }

    @Override
    public AppUser getCurrentUser(String name) {
        AppUser appUser = this.userRepository.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("Not found!"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication.toString());

        return appUser;
    }

    @Override
    public void setUserActive(String activationLink) {

        this.userRepository.activateUser(activationLink);
    }

}
