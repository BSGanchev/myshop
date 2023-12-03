package shop.springbootapp.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.springbootapp.model.entity.Order;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.service.OrderService;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final OrderService orderService;

    public OwnerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String ownerPage(Model model) {
        List<Order> allOrders = this.orderService.getAllOrders();

        if (!model.containsAttribute("orders")) {
            model.addAttribute("orders", allOrders);
        }

            System.out.println(this.orderService.getAllOrders());
        return "orders";
    }
}
