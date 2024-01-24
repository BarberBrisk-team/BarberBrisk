package com.example.barberbrisk.objects;

public class HairCut {
    private Double price;
    private String nikName;

    public HairCut() {
    }

    public HairCut(Double price, String nikName) {
        this.price = price;
        this.nikName = nikName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setNikName(String nikName) {
        this.nikName = nikName;
    }

    public Double getPrice() {
        return price;
    }

    public String getNikName() {
        return nikName;
    }
}
