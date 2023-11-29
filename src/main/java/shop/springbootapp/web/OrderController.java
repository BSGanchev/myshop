package shop.springbootapp.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.service.OrderService;
import shop.springbootapp.service.ProductService;

@RestController
@RequestMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {

        this.orderService = orderService;
        this.productService = productService;
    }
    @PostMapping(value = "/buy")
    public ResponseEntity<Product> placeOrder(@RequestBody Product product){



        System.out.println(product);
        return ResponseEntity.ok(product);
    }

}
