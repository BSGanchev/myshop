package shop.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.springbootapp.model.entity.Role;
import shop.springbootapp.model.enums.RoleNameEnum;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(RoleNameEnum role);
}
