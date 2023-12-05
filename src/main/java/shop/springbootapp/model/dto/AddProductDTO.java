package shop.springbootapp.model.dto;

import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.model.enums.ProductTypeEnum;

import java.math.BigDecimal;

public class AddProductDTO {

    private String productName;

    private String type;

    private BigDecimal price;

    private String description;

    public AddProductDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
