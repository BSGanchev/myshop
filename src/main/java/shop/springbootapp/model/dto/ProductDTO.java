package shop.springbootapp.model.dto;

import shop.springbootapp.model.entity.ProductType;
import shop.springbootapp.model.enums.ProductTypeEnum;

import java.math.BigDecimal;
import java.util.UUID;


public class ProductDTO {

    private UUID id;
    private String productName;

    private ProductTypeEnum type;

    private BigDecimal price;

    private String pictureUrl;

    private String description;

    public ProductDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}