package entities;

public class Flower extends Product {

    private String color;

    public Flower(String name, double price, String color){
        super(name, price);
        this.color = color;
    }

    public String getcolor() {
        return this.color;
    }

}
