package entities;

public class Flower extends Product {

    private String color;

    public Flower() {
    }

    public Flower(String name, String color) {
        super(name);
        this.color = color;
    }

    public Flower(String name, String color, int quantity) {
        super(name, quantity);
        this.color = color;
    }

    public Flower(String name, String color, double price, int quantity){
        super(name, price, quantity);
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String showInfoWithOutQuantity() {
        return "->PRODUCT: " + super.getName() + "  ->COLOR: " + this.color + "  ->PRICE: " + super.getPrice() + "€";
    }

    @Override
    public String showInfoWithQuantity() {
        return "->PRODUCT: " + super.getName() + "  ->COLOR: " + this.color + "  ->PRICE: " + super.getPrice() + "€  ->QUANTITY: " + super.getQuantity() + " Uds";
    }
}
