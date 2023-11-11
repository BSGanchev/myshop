package shop.springbootapp.service.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.entity.Role;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.repository.RoleRepository;
import shop.springbootapp.service.RoleService;

import java.util.Arrays;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Service

public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {

        if (this.roleRepository.count() != 0) {
            return;
        }
        Arrays.stream(RoleNameEnum.values())
                .forEach(roleNameEnum -> {
                    Role role = new Role();
                    role.setRole(roleNameEnum);
                    this.roleRepository.save(role);
                });
    }
}
