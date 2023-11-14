package shop.springbootapp.model.view;

import shop.springbootapp.model.entity.Role;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class AppUserView {
    private UUID id;
    private String username;
    private LocalDateTime lastLogged;
    private Set<Role> roles;

    public AppUserView() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLastLogged() {
        return lastLogged;
    }

    public void setLastLogged(LocalDateTime lastLogged) {
        this.lastLogged = lastLogged;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "ActiveUserDTO{" +
                "username='" + username + '\'' +
                ", lastLogged='" + lastLogged + '\'' +
                '}';
    }
}
