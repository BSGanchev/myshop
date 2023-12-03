package shop.springbootapp.service;

import shop.springbootapp.model.dto.OrderRequestDTO;

public interface OrderService {
    void processOrder(String id);

    void createOrder(OrderRequestDTO orderRequestDTO);
}
