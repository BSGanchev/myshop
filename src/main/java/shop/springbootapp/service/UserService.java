package shop.springbootapp.service;

import shop.springbootapp.model.dto.RegisterUserDTO;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    AppUser findByEmail(String email);
    void initUser();
    void changeLastLoginTime(String name);

    int getUsersFromSessionRegistryCount();

    List<AppUser> getLastLoggedUsers();

    AppUser registerUser(UserServiceModel userServiceModel);
}
