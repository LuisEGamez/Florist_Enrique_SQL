package entities;

public class Decor extends Product {

    private String typeOfMaterial;

    public Decor() {
    }

    public Decor(String name) {
        super(name);
        typeOfMaterial = "";
    }

    public Decor(String name, String typeOfMaterial, int quantity) {
        super(name, quantity);
        this.typeOfMaterial = typeOfMaterial;
    }

    public Decor(String name, double price, int quantity){
        super(name, price, quantity);
        typeOfMaterial = "";
    }

    public String getMaterial() {
        return this.typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public boolean setTypeOfMaterial(int type) {
        boolean select = false;
        if(type == 1) {
            typeOfMaterial = "WOOD";
            select = true;
        }else if(type == 2){
            typeOfMaterial = "PLASTIC";
            select = true;
        }
        return select;
    }

    @Override
    public String showInfoWithOutQuantity() {
        return "->PRODUCT: " + super.getName() + "  ->MATERIAL: " + this.typeOfMaterial + "  ->PRICE: " + super.getPrice() + "€";
    }

    @Override
    public String showInfoWithQuantity() {
        return "->PRODUCT: " + super.getName() + "  ->MATERIAL: " + this.typeOfMaterial + "  ->PRICE: " + super.getPrice() + "€  ->QUANTITY: " + super.getQuantity() + " Uds";
    }

}
