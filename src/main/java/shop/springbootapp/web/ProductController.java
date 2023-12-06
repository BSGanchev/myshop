package shop.springbootapp.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.service.PictureService;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.exception.ProductNotFoundException;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final PictureService pictureService;

    public ProductController(ProductService productService, PictureService pictureService) {
        this.productService = productService;
        this.pictureService = pictureService;
    }

    @GetMapping("/all")
    public String getProductPage(Model model) {
        List<Product> aLlProducts = this.productService.getALlProducts();
        if (!model.containsAttribute("allProducts")) {
            model.addAttribute("allProducts", aLlProducts);
        }
        return "products";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);

        if (Objects.isNull(product)) {
            throw new ProductNotFoundException("Product cannot be found ! ID: " + id);

        }
        return ResponseEntity.ok(product);
    }
}
