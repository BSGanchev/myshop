package shop.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.springbootapp.model.entity.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    //TODO
}
