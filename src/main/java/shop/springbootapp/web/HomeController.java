package shop.springbootapp.web;

import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.springbootapp.model.dto.CartProductDTO;
import shop.springbootapp.model.dto.ProductDTO;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.model.enums.RoleNameEnum;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.util.SessionCartUtil;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final SessionCartUtil sessionCartUtil;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public HomeController(ProductService productService, SessionCartUtil sessionCartUtil, ModelMapper modelMapper) {

        this.sessionCartUtil = sessionCartUtil;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal().toString());

        List<CartProductDTO> sessionProducts = sessionCartUtil.getAllProducts(request);
        model.addAttribute("products", sessionProducts);

        List<Product> allProducts = this.productService.getALlProducts();

        model.addAttribute("allProducts", allProducts);


        if (authentication.getAuthorities().toString().contains(RoleNameEnum.ADMIN.name())) {
            return "redirect:/admin";
        } else if (authentication.getAuthorities().toString().contains(RoleNameEnum.OWNER.name())) {
            return "redirect:/owner";
        }


        return "index";
    }
}
