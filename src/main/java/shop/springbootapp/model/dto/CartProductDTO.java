package shop.springbootapp.model.dto;

import java.util.Objects;
import java.util.UUID;

public class CartProductDTO {

    private UUID id;
    private ProductDTO product;
    private Integer quantity;

    public CartProductDTO() {
    }

    public CartProductDTO(UUID id, ProductDTO product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        if (Objects.isNull(getQuantity())) {
            setQuantity(1);
            return;
        }
        setQuantity(getQuantity() + 1);
    }

}
