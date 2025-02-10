package com.rr.desafio_anota_ai.domain.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("categories")
public class Category {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    public Category() {
    }

    public Category(String id, String title, String description, String ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
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
}
