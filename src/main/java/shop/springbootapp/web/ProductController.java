package shop.springbootapp.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.exception.ProductNotFoundException;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(this.productService.getALlProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        System.out.println(id);
        if (Objects.isNull(product)) {
            throw new ProductNotFoundException("Product cannot be found ! ID: " + id);
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products")
    public String getProduct(){
        return "product";
    }
}
