package shop.springbootapp.model.service;

import shop.springbootapp.model.entity.Role;
import shop.springbootapp.model.enums.RoleNameEnum;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserServiceModel {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Set<Role> roles;
    private LocalDateTime registered;

    public UserServiceModel() {
        this.roles = new HashSet<>();
        this.registered = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered =registered;
    }
}
