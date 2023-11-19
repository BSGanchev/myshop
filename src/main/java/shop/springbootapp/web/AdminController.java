package shop.springbootapp.web;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping("/api/auth/status")
    public ResponseEntity<String> getAuthStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("authenticated");
        } else {
            return ResponseEntity.ok("not authenticated");
        }
    }
}
