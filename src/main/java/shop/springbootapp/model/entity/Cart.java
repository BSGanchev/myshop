package shop.springbootapp.model.entity;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {
    @OneToOne
    private AppUser buyer;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:s")
    private LocalDateTime orderDate;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public AppUser getBuyer() {
        return buyer;
    }

    public void setBuyer(AppUser buyer) {
        this.buyer = buyer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
