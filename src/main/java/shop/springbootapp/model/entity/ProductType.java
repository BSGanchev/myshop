package shop.springbootapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import shop.springbootapp.model.enums.ProductTypeEnum;

@Entity
@Table(name = "product_types")
public class ProductType extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private ProductTypeEnum productTypeName;

    public ProductType() {
    }

    public ProductTypeEnum getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(ProductTypeEnum productTypeName) {
        this.productTypeName = productTypeName;
    }
}
