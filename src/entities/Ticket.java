package entities;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Ticket{

    private int numTicket;
    private List<Product> products;
    private String productos;
    private String date;
    private double total;

    public Ticket() {
        numTicket = 0;
        products = new ArrayList<>();
        date = "";
        total = 0;
    }

    public double getTotal() {
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getDate() {
        return date;
    }

    public int getNumTicket() {
        return numTicket;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {

        this.productos = productos;
    }

    public void setNumTicket(int numTicket) {
        this.numTicket = numTicket;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String listProductsToJson(){
        Gson  gson = new GsonBuilder().setPrettyPrinting().create(); // Instanciamos el objeto GsonBuilder para crear una impresión legible
        return gson.toJson(products);
    }

    public void showProductos(){
        for (Product product : products) {

            if (product instanceof Tree) {

                Tree tree = (Tree) product;
                System.out.println(tree.showInfo());

            } else if (product instanceof Flower) {

                Flower flower = (Flower) product;
                System.out.println(flower.showInfo());

            } else if (product instanceof Decor) {

                Decor decor = (Decor) product;
                System.out.println(decor.showInfo());
            }
        }
    }
}
