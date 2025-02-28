package com.pcbuilder.backend.models.hardware;

public class Storage implements Hardware {
    private int id;
    private String name;
    private String imageUrl;
    private String format;
    private String protocol;
    private int size;
    private float price;

    public Storage(
            int id, String name, String imageUrl, String format,
            String protocol, int size, float price
        ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.format = format;
        this.protocol = protocol;
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

    public String getFormat() {
        return format;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getSize() {
        return size;
    }
}