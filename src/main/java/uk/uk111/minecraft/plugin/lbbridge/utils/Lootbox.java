package uk.uk111.minecraft.plugin.lbbridge.utils;

public class Lootbox {
    private String name;
    private double price;

    public Lootbox(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}
