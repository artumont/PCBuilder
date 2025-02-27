package com.pcbuilder.backend.models.hardware;

public class Gpu implements Hardware {
    private int id;
    private String name;
    private String imageUrl;
    private String chipset;
    private int vram;
    private int wattage;
    private float price;

    public Gpu(
            int id, String name, String imageUrl, String chipset, 
            int vram, int wattage, float price
        ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.chipset = chipset;
        this.vram = vram;
        this.wattage = wattage;
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

    public String getChipset() {
        return chipset;
    }

    public int getVram() {
        return vram;
    }

    public int getWattage() {
        return wattage;
    }
}
