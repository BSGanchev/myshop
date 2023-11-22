package shop.springbootapp.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shop.springbootapp.service.RoleService;
import shop.springbootapp.service.UserService;

@Component
public class UserInit implements CommandLineRunner {
    private final RoleService roleService;
    private final UserService userService;

    public UserInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.userService.initUser();
    }
}
