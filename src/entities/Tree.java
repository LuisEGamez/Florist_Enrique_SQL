package entities;

public class Tree extends Product {

    private double height;

    public Tree() {
    }

    public Tree(String name, double height) {
        super(name);
        if(height <= 0.5){
            this.height = 0.5;
        }else if(height > 0.5 && height <= 1){
            this.height = 1;
        }else if(height > 1 && height <= 1.5){
            this.height = 1.5;
        }else {
            this.height = 2;
        }

    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String showInfoWithOutQuantity() {
        return "->PRODUCT: " + super.getName() + "  HEIGHT: " + this.height + "mts  PRICE: " + super.getPrice() + "€";
    }

    @Override
    public String showInfoWithQuantity() {
        return "->PRODUCT: " + super.getName() + "  HEIGHT: " + this.height + "mts  PRICE: " + super.getPrice() + "€  QUANTITY: " + super.getQuantity() + " Uds";
    }
}
