package com.example.demo;

public class Coffee {
    int id;
    String name;
    boolean milk;

    // In order for Spring to serialize Coffee objects, we need
    // to define getter and setter methods for each attribute
    public int getId() {
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public boolean getMilk() {
        return this.milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    // we will use the toString method in later examples
    public String toString() {
        return "[" + name + "," + Boolean.toString(milk) + "]";
    }
    
}
