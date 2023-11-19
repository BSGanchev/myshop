package shop.springbootapp.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.repository.RoleRepository;

@Controller
public class HomeController {
    private final RoleRepository roleRepository;

    public HomeController(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().toString().contains(RoleNameEnum.ADMIN.name())) {
            return "redirect:/admin";
        } else if (authentication.getAuthorities().toString().contains(RoleNameEnum.OWNER.name())) {
            return "redirect:/owner";
        }
        return "index";
    }
}
