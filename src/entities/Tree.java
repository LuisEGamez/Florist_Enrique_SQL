package entities;

public class Tree extends Product {

    private double height;

    public Tree() {
    }

    public Tree(String name, double height) {
        super(name);
        this.height = height;
    }

    public Tree(String name, double height, int quantity) {
        super(name, quantity);
        this.height = height;
    }

    public Tree(String name, double height, double price, int quantity){
        super(name, price, quantity);
        this.height = height;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String showInfoWithOutQuantity() {
        return "Nombre árbol: " + super.getName() + "\nAltura: " + this.height + "\nPrecio: " + super.getPrice() + "€";
    }

    @Override
    public String showInfoWithQuantity() {
        return "Nombre árbol: " + super.getName() + "\nAltura: " + this.height + "\nPrecio: " + super.getPrice() + "€\n" + super.getQuantity() + " Uds";
    }
}
