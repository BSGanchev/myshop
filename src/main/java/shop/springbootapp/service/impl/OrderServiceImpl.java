package shop.springbootapp.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.dto.OrderRequestDTO;
import shop.springbootapp.model.dto.ProductForOrderDTO;
import shop.springbootapp.model.entity.AppUser;
import shop.springbootapp.model.entity.Order;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.repository.OrderRepository;
import shop.springbootapp.service.OrderService;
import shop.springbootapp.service.UserService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void processOrder(String id) {
        //TODO
    }

    @Override
    public void createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        UUID orderId = UUID.randomUUID();
        System.out.println(orderId);
        order.setId(orderId);
        order.setOrderDate(Instant.now());

        AppUser buyer = this.userService.getCurrentUser();

        order.setProducts(orderRequestDTO.getProducts()
                .stream()
                .map(e -> modelMapper.map(e, Product.class))
                .collect(Collectors.toList()));
        if (Objects.isNull(buyer)) {
            throw new UsernameNotFoundException("User with that name does not exist");
        }
        order.setTotal(orderRequestDTO.getProducts()
                .stream()
                .map(ProductForOrderDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setBuyer(buyer);

        this.orderRepository.saveAndFlush(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
}
