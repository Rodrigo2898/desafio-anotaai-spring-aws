package com.rr.desafio_anota_ai.domain.product;

import com.rr.desafio_anota_ai.domain.category.Category;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String category;

    public Product() {
    }

    public Product(String id, String title, String description, String ownerId, Integer price, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("title", title);
        json.put("description", description);
        json.put("ownerId", ownerId);
        json.put("categoryId", category);
        json.put("price", price);
        json.put("type", "product");
        return json.toString();
    }
}
