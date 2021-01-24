package com.example.medenjak.model;

public class Product {
    private String name;
    private String description;
    private String wayOfUse;
    private int price;
    private int picture;

    public Product(String name, String description, String wayOfUse, int price, int picture) {
        this.name = name;
        this.description = description;
        this.wayOfUse = wayOfUse;
        this.price = price;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWayOfUse() {
        return wayOfUse;
    }

    public void setWayOfUse(String wayOfUse) {
        this.wayOfUse = wayOfUse;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
