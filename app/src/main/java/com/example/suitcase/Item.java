package com.example.suitcase;

import android.net.Uri;

public class Item {
    private int id;
    private String name, description;
    private Uri image;
    private double price;
    private boolean purchased;

    //Each class variable has a getter method (e.g., getId, getName)
    // to retrieve the value of that variable and a setter method (e.g., setId, setName) to set its value.
    // These methods allow you to access and modify the attributes of an Item object

    public Item() {}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
    // returns a string containing the values of all the item's attributes.
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", purchased=" + purchased +
                '}';
    }
}