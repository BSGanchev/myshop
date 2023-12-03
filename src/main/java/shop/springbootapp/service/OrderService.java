package shop.springbootapp.service;

import shop.springbootapp.model.dto.OrderRequestDTO;
import shop.springbootapp.model.entity.Order;

import java.util.List;

public interface OrderService {
    void processOrder(String id);

    void createOrder(OrderRequestDTO orderRequestDTO);

    List<Order> getAllOrders();
}
