package com.example.fuelqueuemanager.Models;

public class VehicleUser {

    private String Id;
    private String Username;
    private String Adress;
    private String FuelType;
    private String VehicleNumber;

    public VehicleUser() {
    }

    public VehicleUser(String id, String username, String adress, String fuelType, String vehicleNumber) {
        Id = id;
        Username = username;
        Adress = adress;
        FuelType = fuelType;
        VehicleNumber = vehicleNumber;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }
}
