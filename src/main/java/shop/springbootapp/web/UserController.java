package shop.springbootapp.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.springbootapp.model.dto.RegisterUserDTO;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.entity.UserActivationToken;
import shop.springbootapp.model.service.UserServiceModel;
import shop.springbootapp.service.UserService;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @GetMapping("/login")
    public String login() {

        return "login";
    }
    @PostMapping("/login-error")
    public String onFailure(@ModelAttribute ("username") String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", true);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("registerUserDTO")) {
            model.addAttribute(new RegisterUserDTO());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@ModelAttribute("registerUserDTO") @Valid RegisterUserDTO registerUserDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "registerUserDTO", bindingResult);

            if (this.userService.findByEmail(registerUserDTO.getEmail()) != null) {
                redirectAttributes.addFlashAttribute("userEmailExist", true);
                redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "registerUserDTO", bindingResult);

            }
            return "redirect:register";
        }

        this.userService.registerUser(modelMapper.map(registerUserDTO, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/activation")
    public String registrationConfirm(Model model, @RequestParam("token") String token) {
        UserActivationToken activationToken = userService.getActivationToken(token);
        if (activationToken == null) {
            return "redirect:register";
        }
        AppUser appUser = activationToken.getUser();

        this.userService.setUserActive(appUser.getId());
        this.userService.deleteUsedToken(activationToken);

        return "redirect:login";
    }

}
