package shop.springbootapp.service;

import shop.springbootapp.model.dto.AddProductDTO;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getALlProducts();

    Product getProductById(String id);

    void addProduct(AddProductDTO addProductDTO, Picture picture);

    void updateProduct(AddProductDTO oldProduct, Picture picture, String id);
}
