package com.pcbuilder.backend.models.hardware;

public class Psu implements Hardware {
    private int id;
    private String name;
    private String imageUrl;
    private int wattage;
    private String size;
    private float price;

    public Psu(
        int id, String name, String imageUrl, int wattage, 
        String size, float price
    ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.wattage = wattage;
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

    public int getWattage() {
        return wattage;
    }

    public String getSize() {
        return size;
    }
}