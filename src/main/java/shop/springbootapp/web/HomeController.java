package shop.springbootapp.web;

import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.service.ProductService;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Product> aLlProducts = productService.getALlProducts();

        if(!model.containsAttribute("allProducts")){
            model.addAttribute("allProducts", aLlProducts);
        }

        return "index";
    }


}
