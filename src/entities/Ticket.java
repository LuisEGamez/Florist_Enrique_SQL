package entities;


import java.util.ArrayList;
import java.util.List;

public class Ticket{

    private int numTicket;
    private String date;
    private double total;
    private List<Product> products;

    public Ticket() {
        numTicket = 0;
        date = "";
        total = 0;
        products = new ArrayList<>();
    }

    public double getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }

    public int getNumTicket() {
        return numTicket;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setNumTicket(int numTicket) {
        this.numTicket = numTicket;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String toString(){
        return "Nº TICKET: " + numTicket +
                "\nDATE: " + date +
                "\nTOTAL: " + total;
    }

}
