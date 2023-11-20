package shop.springbootapp.service;

import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    AppUser findByUsername(String username);
    void initUser();
    void changeLastLoginTime(String name);

    int getUsersFromSessionRegistryCount();

    List<AppUser> getLastLoggedUsers();

    void registerUser(UserServiceModel userServiceModel);
}
