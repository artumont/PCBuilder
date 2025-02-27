package com.pcbuilder.backend.models.hardware;

public class Ram implements Hardware {
    private int id;
    private String name;
    private String imageUrl;
    private int speed;
    private int size;
    private String type;
    private float price;

    public Ram(
            int id, String name, String imageUrl, int speed, 
            int size, String type, float price
        ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.speed = speed;
        this.size = size;
        this.type = type;
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

    public int getSpeed() {
        return speed;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}