package com.pcbuilder.backend.models.hardware;

public class Cpu implements Hardware {
    private int id;
    private String name;
    private String imageUrl;
    private String socket;
    private float clockSpeed;
    private int cores;
    private int threads;
    private float price;

    public Cpu(
            int id, String name, String imageUrl, String socket, 
            float clockSpeed, int cores, int threads, float price
        ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.socket = socket;
        this.clockSpeed = clockSpeed;
        this.cores = cores;
        this.threads = threads;
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

    public float getClockSpeed() {
        return clockSpeed;
    }

    public int getCores() {
        return cores;
    }

    public int getThreads() {
        return threads;
    }
}