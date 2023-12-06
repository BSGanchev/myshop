package shop.springbootapp.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.dto.ProductDTO;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.model.entity.ProductType;
import shop.springbootapp.repository.ProductRepository;
import shop.springbootapp.repository.ProductTypeRepository;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
    }



    @Override
    public List<Product> getALlProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(UUID id) {
        if (id == null || id.toString().trim().isBlank()) {
            throw new ProductNotFoundException("Product not found");
        }
        return productRepository.findById(id).orElse(null);
    }


    @Override
    public void addProduct(ProductDTO productDTO, Picture picture) {
        ModelMapper modelMapper = new ModelMapper();

        Product product = modelMapper.map(productDTO, Product.class);
        product.setPicture(picture);

        ProductType productType = this.productTypeRepository.findAll().stream()
                .filter(e -> e.getProductTypeName().name().contains(productDTO.getType()))
                .findFirst().get();

        product.setType(productType);
        this.productRepository.save(product);
    }


}
