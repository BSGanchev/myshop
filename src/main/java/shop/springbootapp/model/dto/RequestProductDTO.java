package shop.springbootapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import shop.springbootapp.model.entity.ProductType;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestProductDTO {
    String id;
    private String productName;

    private ProductType type;

    private BigDecimal price;

    private String pictureUrl;
    private String description;

    public RequestProductDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
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

    @Override
    public String toString() {
        return "RequestProductDTO{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}