package shop.springbootapp.service.impl;

import org.springframework.stereotype.Service;
import shop.springbootapp.model.entity.ProductType;
import shop.springbootapp.model.enums.ProductTypeEnum;
import shop.springbootapp.repository.ProductTypeRepository;
import shop.springbootapp.service.ProductTypeService;

import java.util.Arrays;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public void initProductTypes() {
        if (this.productTypeRepository.count() != 0) {
            return;
        }
        Arrays.stream(ProductTypeEnum.values()).forEach(productTypeEnum -> {
            ProductType productType = new ProductType();
            productType.setProductTypeName(productTypeEnum);
            this.productTypeRepository.save(productType);
        });
    }
}
