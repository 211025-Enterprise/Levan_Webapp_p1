package model;

import Annotations.NoNull;
import Annotations.PKey;
import Annotations.Unique;

public class Cat {
    @PKey
    public int chipID;
    @Unique
    public String name;
    @NoNull
    public String breed;
    @NoNull
    public boolean isFixed;
    public Cat(){
        chipID = 0;
        name = "cat1";
        breed = "orange";
        isFixed = false;
    }
    public Cat(int id, String name, String breed, boolean fixed){
        chipID = id;
        this.name = name;
        this.breed = breed;
        isFixed = fixed;
    }

    public int getChipID() {
        return chipID;
    }

    public void setChipID(int chipID) {
        this.chipID = chipID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "chipID=" + chipID +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", isFixed=" + isFixed +
                '}';
    }
}
