package shop.springbootapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @OneToMany
    private List<Product> products;
    @ManyToOne
    private AppUser buyer;

    public Order() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public AppUser getBuyer() {
        return buyer;
    }

    public void setBuyer(AppUser buyer) {
        this.buyer = buyer;
    }
}
