package entities;

public class Decor extends Product {

    private String typeOfMaterial;

    public Decor(String name, double price){
        super(name, price);
        typeOfMaterial = "";
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

    public String getMaterial() {
        return this.typeOfMaterial;
    }

}
