package shop.springbootapp.service;

import shop.springbootapp.model.entity.Product;

import java.util.List;

public interface ProductService {
    void initProducts();

    List<Product> getALlProducts();

    Product getProductById(String id);
}
