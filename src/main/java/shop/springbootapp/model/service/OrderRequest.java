package shop.springbootapp.model.service;

import java.util.List;

public class OrderRequest {
    private List<OrderProduct> products;

    public OrderRequest() {
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }
}
