package shop.springbootapp.service;

import shop.springbootapp.model.dto.ProductDTO;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.model.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getALlProducts();

    Product getProductById(UUID id);


    void addProduct(ProductDTO productDTO, Picture picture);
}
