package com.example.project_ecommerce.model;

public class Item {
    private String id, docId, name, quantity, picture;

    public Item(){}

    public Item(String id, String name, String quantity, String picture){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.quantity = picture;
    }
}