package com.pcbuilder.backend.models.hardware;

public class Case implements Hardware {
    private int id;
    private String name;
    private String imageUrl;
    private String size;
    private float price;

    public Case(
        int id, String name, 
        String imageUrl, String size, float price
    ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.size = size;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public float getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }
}