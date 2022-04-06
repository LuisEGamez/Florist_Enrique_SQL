package entities;

public abstract class Product {

    protected int id;
    protected String name;
    protected double price;
    protected int quantity;

    public Product() {
    }

    public Product(String name) {
        id = 0;
        this.name = name;
        price = 0;
        quantity = 0;
    }

    public Product(String name, int quantity) {
        id = 0;
        this.name = name;
        this.quantity = quantity;
        price = 0;
    }

    public Product(String name, double price, int quantity){
        id = 0;
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract String showInfoWithOutQuantity();

    public abstract String showInfoWithQuantity();





}
