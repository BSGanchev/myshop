package shop.springbootapp.service.impl;

import org.springframework.stereotype.Service;
import shop.springbootapp.repository.OrderRepository;
import shop.springbootapp.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void processOrder(String id) {
        //TODO
    }
}
