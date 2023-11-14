package shop.springbootapp.service.impl;

import org.springframework.stereotype.Service;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.model.enums.ProductTypeEnum;
import shop.springbootapp.repository.ProductRepository;
import shop.springbootapp.repository.ProductTypeRepository;
import shop.springbootapp.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public void initProducts() {
        Product product = new Product();
        product.setProductName("Purple Baby Frame");
        product.setPrice(BigDecimal.valueOf(12.99));
        product.setType(productTypeRepository
                .findProductTypeByProductTypeName(ProductTypeEnum.Baby_Frame)
                .orElse(null));
        product.setPictureUrl("https://m.media-amazon.com/images/I/613MGDH4PDL.jpg");
        product.setDescription("Amazing baby frame!!!");
        this.productRepository.save(product);
    }

    @Override
    public List<Product> getALlProducts() {
        return this.productRepository.findAll();
    }
}
