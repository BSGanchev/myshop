package shop.springbootapp.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.dto.AddProductDTO;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.model.entity.ProductType;
import shop.springbootapp.model.enums.ProductTypeEnum;
import shop.springbootapp.repository.ProductRepository;
import shop.springbootapp.repository.ProductTypeRepository;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.exception.ProductNotFoundException;

import java.math.BigDecimal;
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
    public void initProducts() {
        if (this.productRepository.count() != 0) {
            return;
        }
    }

    @Override
    public List<Product> getALlProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        if (id == null || id.trim().isBlank()) {
            throw new ProductNotFoundException("Product not found");
        }
        return productRepository.findById(UUID.fromString(id)).orElse(null);
    }

    @Override
    public UUID addProduct(Product product) {
        ModelMapper modelMapper = new ModelMapper();

        return this.productRepository.save(product).getId();
    }

    @Override
    public void addProduct(AddProductDTO addProductDTO, Picture picture) {
        ModelMapper modelMapper = new ModelMapper();

        Product product = modelMapper.map(addProductDTO, Product.class);
        product.setPicture(picture);

        ProductType productType = this.productTypeRepository.findAll().stream()
                .filter(e -> e.getProductTypeName().name().contains(addProductDTO.getType()))
                .findFirst().get();

        product.setType(productType);
        this.productRepository.save(product);
    }


}
