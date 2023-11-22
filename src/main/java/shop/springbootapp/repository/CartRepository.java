package shop.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.springbootapp.model.entity.Cart;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
