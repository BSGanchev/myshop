package shop.springbootapp.web;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.view.AppUserView;
import shop.springbootapp.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/view")
    public String adminPage(Model model) {
        model.addAttribute("activeUsersCount", this.userService.getUsersFromSessionRegistryCount());

        List<AppUser> lastLoggedUsers = this.userService.getLoggedUsers();

        List<AppUserView> appUserViews = lastLoggedUsers.stream()
                .map(appUser -> (modelMapper.map(appUser, AppUserView.class)))
                .toList();


        List<AppUserView> allAppUsers = this.userService.getAllRegistered();
        model.addAttribute("allAppUsers", allAppUsers);

        return "admin";
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
    @GetMapping("/user-details/{id}")
    public String userDetails(@PathVariable String id, Model model) {

        AppUser appUser = this.userService.findById(id);
        if (Objects.nonNull(appUser)) {

            List<String> roles = appUser.getRoles().stream().map(role -> role.getRole().name()).collect(Collectors.toList());
            model.addAttribute("appUser", appUser);
            model.addAttribute("roles", roles);
        }

        return "user-details";
    }

    @PostMapping("/user-details/{id}")
    public String userDetailsConfirm(@PathVariable String id,
                                     @ModelAttribute("appUser") AppUser appUser) {

        AppUser oldUser = this.userService.findById(id);
        this.userService.updateUserDetail(oldUser, appUser);

        return "redirect:/admin/view";
    }
}
