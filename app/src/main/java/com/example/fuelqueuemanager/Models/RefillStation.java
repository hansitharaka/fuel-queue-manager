package com.example.fuelqueuemanager.Models;

public class RefillStation {
    private String id;
    private String StationName;
    private String Address;
    private String RegistrationNumber;

    public RefillStation() {
    }

    public RefillStation(String id, String stationName, String address, String registrationNumber) {
        this.id = id;
        StationName = stationName;
        Address = address;
        RegistrationNumber = registrationNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRegistrationNumber() {
        return RegistrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        RegistrationNumber = registrationNumber;
    }
}
