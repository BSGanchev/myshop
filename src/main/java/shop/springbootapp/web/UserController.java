package shop.springbootapp.web;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.springbootapp.model.dto.RegisterUserDTO;
import shop.springbootapp.model.service.UserServiceModel;
import shop.springbootapp.service.UserService;

import java.time.LocalDateTime;

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

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        if (!model.containsAttribute("registerUserDTO")) {
            model.addAttribute(new RegisterUserDTO());
        }
        return "register";
    }
    @GetMapping("/logout")
    public String logout(){
        return "index";
    }
    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("registerUserDTO") RegisterUserDTO registerUserDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerUserDTO",registerUserDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "registerUserDTO", bindingResult);

            return "redirect:register";
        }

        this.userService.registerUser(modelMapper.map(registerUserDTO, UserServiceModel.class));


        System.out.println(LocalDateTime.now());

        return "redirect:login";
    }
}
