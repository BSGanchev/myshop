package shop.springbootapp.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.UserService;

@Controller
public class OwnerController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    public OwnerController(UserService userService, ModelMapper modelMapper, ProductService productService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @GetMapping("/owner")
    public String ownerPage(Model model) {
        if (!model.containsAttribute("products")) {
            model.addAttribute("products", this.productService.getALlProducts());
        }
        return "owner-page";
    }
}
