package shop.springbootapp.model.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.jfr.DataAmount;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderProduct {
    private String id;

    public OrderProduct() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
