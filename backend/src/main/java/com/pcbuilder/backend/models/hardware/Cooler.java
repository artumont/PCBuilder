package com.pcbuilder.backend.models.hardware;

public class Cooler implements Hardware {
    private int id;
    private String name;
    private String socket;
    private String imageUrl;
    private float price;

    public Cooler(int id, String name, String socket, String imageUrl, float price) {
        this.id = id;
        this.name = name;
        this.socket = socket;
        this.imageUrl = imageUrl;
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

    public String getSocket() {
        return socket;
    }
}