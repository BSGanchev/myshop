package shop.springbootapp.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.springbootapp.model.dto.OrderRequestDTO;
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
    public ResponseEntity<String> placeOrder(@RequestBody String request) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderRequestDTO orderRequestDTO = objectMapper.readValue(request, OrderRequestDTO.class);

        orderRequestDTO.getProducts().forEach(System.out::println);

        return ResponseEntity.ok("Successfully added order");
    }

}
