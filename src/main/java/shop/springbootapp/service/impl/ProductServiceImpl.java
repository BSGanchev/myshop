package shop.springbootapp.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.dto.AddProductDTO;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.model.entity.ProductType;
import shop.springbootapp.repository.ProductRepository;
import shop.springbootapp.repository.ProductTypeRepository;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.exception.ProductNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductTypeRepository productTypeRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
        this.modelMapper = modelMapper;
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
    public void addProduct(AddProductDTO addProductDTO, Picture picture) {

        Product product = modelMapper.map(addProductDTO, Product.class);
        product.setPicture(picture);

        ProductType productType = this.productTypeRepository.findAll().stream()
                .filter(e -> e.getProductTypeName().name().contains(addProductDTO.getType()))
                .findFirst().get();

        product.setType(productType);
        this.productRepository.save(product);
    }

    @Override
    public void updateProduct(AddProductDTO newProduct, Picture picture, String id) {
        Product oldProduct = this.productRepository.findById(UUID.fromString(id)).orElse(null);
        if (Objects.isNull(newProduct)) {
            throw new ProductNotFoundException("That Product didnt exist");
        }
        oldProduct.setProductName(newProduct.getProductName());
        oldProduct.setType(this.productTypeRepository.findAll().stream().filter(e -> e.getProductTypeName().name().contains(newProduct.getType())).findFirst().get());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setDescription(newProduct.getDescription());
        if (Objects.nonNull(picture)) {
            oldProduct.setPicture(picture);
        }
        this.productRepository.save(oldProduct);
    }


}
