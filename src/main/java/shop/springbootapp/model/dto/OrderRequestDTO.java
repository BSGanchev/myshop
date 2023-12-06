package shop.springbootapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderRequestDTO {
    @JsonProperty("items")
    private List<ProductForOrderDTO> products;

    public OrderRequestDTO() {
    }

    public List<ProductForOrderDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductForOrderDTO> products) {
        this.products = products;
    }
}
