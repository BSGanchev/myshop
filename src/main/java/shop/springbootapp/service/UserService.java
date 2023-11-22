package shop.springbootapp.service;

import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.entity.UserActivationToken;
import shop.springbootapp.model.service.UserServiceModel;

import java.util.List;
import java.util.UUID;

public interface UserService {
    AppUser findByUsername(String username);

    void initUser();

    void changeLastLoginTime(String name);

    int getUsersFromSessionRegistryCount();

    List<AppUser> getLoggedUsers();

    void registerUser(UserServiceModel userServiceModel);

    AppUser getCurrentUser(String name);

    void setUserActive(UUID id);

    UserActivationToken getActivationToken(String token);

    void deleteUsedToken(UserActivationToken activationToken);
}
