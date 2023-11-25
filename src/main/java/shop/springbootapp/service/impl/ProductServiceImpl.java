package shop.springbootapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.model.enums.ProductTypeEnum;
import shop.springbootapp.repository.ProductRepository;
import shop.springbootapp.repository.ProductTypeRepository;
import shop.springbootapp.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
        if (this.productRepository.count() != 0) {
            return;
        }
        Product product = new Product();
        product.setProductName("Purple Baby Frame");
        product.setPrice(BigDecimal.valueOf(12.99));
        product.setType(productTypeRepository
                .findProductTypeByProductTypeName(ProductTypeEnum.Baby_Frame)
                .orElse(null));
        product.setPictureUrl("https://m.media-amazon.com/images/I/613MGDH4PDL.jpg");
        product.setDescription("Amazing baby frame!!!");
        this.productRepository.save(product);

        Product product1 = new Product();
        product1.setProductName("Black Baby Frame");
        product1.setPrice(BigDecimal.valueOf(12.99));
        product1.setType(productTypeRepository
                .findProductTypeByProductTypeName(ProductTypeEnum.Baby_Frame)
                .orElse(null));
        product1.setPictureUrl("https://m.media-amazon.com/images/I/613MGDH4PDL.jpg");
        product1.setDescription("Other Amazing baby frame!!!");
        this.productRepository.save(product1);
    }

    @Override
    public List<Product> getALlProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }

        return productRepository.findById(UUID.fromString(id)).orElse(null);
    }
}
