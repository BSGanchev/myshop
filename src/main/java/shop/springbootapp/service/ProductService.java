package shop.springbootapp.service;

import shop.springbootapp.model.dto.AddProductDTO;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.model.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void initProducts();

    List<Product> getALlProducts();

    Product getProductById(String id);

    UUID addProduct(Product product);

    void addProduct(AddProductDTO addProductDTO, Picture picture);
}
