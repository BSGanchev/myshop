package shop.springbootapp.web;

import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.service.ProductService;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public HomeController(ProductService productService, ModelMapper modelMapper) {

        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().toString().contains(RoleNameEnum.ADMIN.name())) {
            return "redirect:/admin";
        }

        List<Product> aLlProducts = productService.getALlProducts();

        if(!model.containsAttribute("allProducts")){
            model.addAttribute("allProducts", aLlProducts);
        }

        return "index";
    }


}
