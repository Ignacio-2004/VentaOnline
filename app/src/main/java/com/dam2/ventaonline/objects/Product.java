package com.dam2.ventaonline.objects;

public class Product {

    private String id;
    private String  name;
    private double cost;

    public Product(String id, String name,double cost) {
        this.id = id;
        this.name = name.trim();
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
