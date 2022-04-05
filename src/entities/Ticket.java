package entities;



public class Ticket{

    private int numTicket;
    private String date;
    private double total;

    public Ticket() {
        numTicket = 0;
        date = "";
        total = 0;
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

    public void setNumTicket(int numTicket) {
        this.numTicket = numTicket;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString(){
        return "Nº TICKET: " + numTicket +
                "\nDATE: " + date +
                "\nTOTAL: " + total;
    }

}
