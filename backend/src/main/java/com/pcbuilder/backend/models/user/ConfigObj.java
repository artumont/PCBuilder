package com.pcbuilder.backend.models.user;

public class ConfigObj {
    private int id;
    private int userId;
    private String configData;
    private String creationDate;

    public ConfigObj(int id, int userId, String configData, String creationDate) {
        this.id = id;
        this.userId = userId;
        this.configData = configData;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getConfigData() {
        return configData;
    }

    public void setConfigData(String configData) {
        this.configData = configData;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
