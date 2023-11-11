package shop.springbootapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import shop.springbootapp.model.dto.UserForAdminPanelDTO;
import shop.springbootapp.service.UserService;

@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage(@ModelAttribute("userForAdminPanelDTO") UserForAdminPanelDTO userForAdminPanelDTO,
                            Model model) {
        if (!model.containsAttribute("userForAdminPanelDTO")) {
            model.addAttribute(userForAdminPanelDTO);
        }
        return "admin-page";
    }
}
