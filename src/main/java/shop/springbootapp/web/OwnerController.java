package shop.springbootapp.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.UserService;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    public OwnerController(UserService userService, ModelMapper modelMapper, ProductService productService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @GetMapping("/orders")
    public String ownerPage(Model model) {
        if (!model.containsAttribute("products")) {
            model.addAttribute("products", this.productService.getALlProducts());
        }
        return "orders";
    }
}
