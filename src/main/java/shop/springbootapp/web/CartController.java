package shop.springbootapp.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.springbootapp.model.dto.CartProductDTO;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.exception.ProductNotFoundException;
import shop.springbootapp.util.SessionCartUtil;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final SessionCartUtil sessionCartUtil;

    public CartController(ProductService productService, SessionCartUtil sessionCartUtil) {
        this.productService = productService;
        this.sessionCartUtil = sessionCartUtil;
    }

    @GetMapping
    public String getCardPage(Model model, HttpServletRequest request) {
        List<CartProductDTO> sessionProducts = sessionCartUtil.getAllProducts(request);
        model.addAttribute("products", sessionProducts);
        return "cart";
    }

    @ResponseBody
    @PostMapping("/products/{id}")
    public String addProduct(@PathVariable String id, HttpServletRequest request) {
        Product product = productService.getProductById(id);
        if (Objects.isNull(product)) {
            throw new ProductNotFoundException("Product cannot be found ! ID: " + id);
        }
        return sessionCartUtil.addProduct(request, product);
    }

    @ResponseBody
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable String id, HttpServletRequest request) {
        return sessionCartUtil.deleteProduct(request, id);
    }

}
