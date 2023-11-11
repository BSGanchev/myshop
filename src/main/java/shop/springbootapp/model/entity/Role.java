package shop.springbootapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import shop.springbootapp.model.enums.RoleNameEnum;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private RoleNameEnum role;

    public Role() {
    }

    public RoleNameEnum getRole() {
        return role;
    }

    public void setRole(RoleNameEnum role) {
        this.role = role;
    }
}
