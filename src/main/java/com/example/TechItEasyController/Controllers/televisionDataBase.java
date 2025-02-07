package com.example.TechItEasyController.Controllers;

public class televisionDataBase {
    private String name;
    private double price;
    private String brand;


    public televisionDataBase(String name, double price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
