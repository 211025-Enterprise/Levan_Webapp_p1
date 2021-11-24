package model;

import Annotations.NoNull;
import Annotations.PKey;

public class Car {
    @NoNull
    public String brand;
    @NoNull
    public String model;
    @NoNull
    public double speed;
    @PKey
    public int ownerID;

    public Car() {
    }

    public Car(String brand, String model, double speed, int ownerID) {
        this.brand = brand;
        this.model = model;
        this.speed = speed;
        this.ownerID = ownerID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", speed=" + speed +
                ", ownerID=" + ownerID +
                '}';
    }
}
