package com.pcbuilder.backend.models.hardware;

public class Monitor implements Hardware {
    private int id;
    private String name;
    private String imageUrl;
    private String resolution;
    private int refreshRate;
    private double size;
    private String panelType;
    private float price;

    public Monitor(
        int id, String name, String imageUrl, 
        String resolution, int refreshRate, double size, 
        String panelType, float price
    ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.size = size;
        this.panelType = panelType;
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

    public String getResolution() {
        return resolution;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public double getSize() {
        return size;
    }

    public String getPanelType() {
        return panelType;
    }
}