package shop.springbootapp.model.dto;

import shop.springbootapp.model.entity.Role;

import java.util.HashSet;
import java.util.Set;

public class UserForAdminPanelDTO {

    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private Set<Role> roles;

    public UserForAdminPanelDTO() {
        this.roles = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
