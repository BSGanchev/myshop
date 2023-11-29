package shop.springbootapp.model.mapper;


import org.springframework.stereotype.Component;
import shop.springbootapp.model.dto.ProductDTO;
import shop.springbootapp.model.entity.Product;

import java.util.Objects;
import java.util.UUID;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId().toString());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getType().getProductTypeName());
        dto.setProductName(entity.getProductName());
        dto.setPrice(entity.getPrice());
        dto.setPictureUrl(entity.getPictureUrl());
        return dto;
    }
}
