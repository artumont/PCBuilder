package com.pcbuilder.backend.models.hardware;
public class Mobo implements Hardware {
    private int id;
    private String name;
    private String imageUrl;
    private String socket;
    private int sataStorageSlots;
    private int m2StorageSlots;
    private int ramSlots;
    private String ramType;
    private String size;
    private int chipsetId;
    private float price;

    public Mobo(
            int id, String name, String imageUrl, String socket, 
            int sataStorageSlots, int m2StorageSlots, int ramSlots, String ramType, 
            String size, int chipsetId, float price
        ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.socket = socket;
        this.sataStorageSlots = sataStorageSlots;
        this.m2StorageSlots = m2StorageSlots;
        this.ramSlots = ramSlots;
        this.ramType = ramType;
        this.size = size;
        this.chipsetId = chipsetId;
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

    public int getSataStorageSlots() {
        return sataStorageSlots;
    }

    public int getM2StorageSlots() {
        return m2StorageSlots;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public String getRamType() {
        return ramType;
    }

    public String getSize() {
        return size;
    }

    public int getChipsetId() {
        return chipsetId;
    }
}
