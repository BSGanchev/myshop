package shop.springbootapp.service;

import shop.springbootapp.model.entity.AppUser;

public interface UserService {
    AppUser findByEmail(String email);
    void initUser();

}
