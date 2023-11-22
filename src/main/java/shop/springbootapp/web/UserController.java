package shop.springbootapp.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.springbootapp.model.dto.RegisterUserDTO;
import shop.springbootapp.model.service.UserServiceModel;
import shop.springbootapp.service.UserService;

import java.lang.reflect.Method;

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
    @PostMapping("/register")
    public String registerConfirm(@ModelAttribute("registerUserDTO") @Valid RegisterUserDTO registerUserDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerUserDTO",registerUserDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "registerUserDTO", bindingResult);

            return "redirect:register";
        }

        this.userService.registerUser(modelMapper.map(registerUserDTO, UserServiceModel.class));

        return "redirect:login";
    }

}
