package shop.springbootapp.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.view.AppUserView;
import shop.springbootapp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {

        model.addAttribute("activeUsersCount", this.userService.getUsersFromSessionRegistryCount());

        List<AppUser> lastLoggedUsers = this.userService.getLastLoggedUsers();

        List<AppUserView> appUserViews = lastLoggedUsers.stream()
                .map(appUser -> (modelMapper.map(appUser, AppUserView.class)))
                .collect(Collectors.toList());

        model.addAttribute("lastLoggedUsers", appUserViews );

        return "admin-page";
    }
}
