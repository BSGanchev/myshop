package shop.springbootapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderRequestDTO {
    @JsonProperty("items")
    private List<RequestProductDTO> products;

    public OrderRequestDTO() {
    }

    public List<RequestProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<RequestProductDTO> products) {
        this.products = products;
    }
}
