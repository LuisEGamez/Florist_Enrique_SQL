package vista;

import entities.*;
import java.util.List;


public class View {

    public static void showStock(List<Product> trees, List<Product> flowers, List<Product> decors ) {


            showMessage("TREES:");

            trees.forEach(x -> showMessage(x.showInfoWithQuantity()));

            showMessage("-----------------\n");

            showMessage("FLOWERS:");

            flowers.forEach(x -> showMessage(x.showInfoWithQuantity()));

            showMessage("-----------------\n");

            showMessage("DECORATIONS:");

            decors.forEach(x -> showMessage(x.showInfoWithQuantity()));

            showMessage("-----------------\n");

    }

    public static void showInfoTickets(Ticket ticket){

        if(ticket == null){
            showMessage("WRONG DATE, INSERT CORRECT DATE");
        }else{

            System.out.println("Nº TICKET: " + ticket.getNumTicket());
            System.out.println("DATE: " + ticket.getDate());

            ticket.getProducts().forEach(x -> System.out.println(x.showInfoWithOutQuantity()));

            System.out.println("TOTAL: " + ticket.getTotal());
        }

    }

    public static void showTotalValueFlorist(double totalValue) {

        System.out.println("TOTAL VALUE: " + totalValue + "€");

    }

    public static void showProductRemoved(boolean removed) {
        if (removed) {
            System.out.println("PRODUCT SUCCESSFULLY REMOVE.");
        } else {
            System.out.println("PRODUCT NOT REMOVE, WRONG QUANTITY INSUFFICIENT STOCK .");
        }
    }

    public static void showTotalSales(double totalSales) {

        System.out.println("TOTAL SALES: " + ((double) Math.round(totalSales * 100d) / 100d) + "€");

    }

    public static void showOptions() {
        System.out.println("SELECT OPTION 0 - 11:");
        System.out.println("""
                1-ADD TREE.
                2-ADD FLOWER.
                3-ADD DECOR.
                4-SHOW STOCK.
                5-REMOVE TREE.
                6-REMOVE FLOWER.
                7-REMOVE DECOR.
                8-TOTAL VALUE
                9-CREATE TICKET
                10-SHOW OLD TICKETS
                11-SHOW TOTAL MONEY
                0-EXIT.""");
    }

    public static void showProducts() {
        System.out.println("SELECT PRODUCT 0 - 3:");
        System.out.println("""
                1-TREE.
                2-FLOWER.
                3-DECOR.
                0-EXIT.""");
    }

    public static void treeAdded ( boolean result){
        if (result) {
            System.out.println("TREE SUCCESSFULLY ADDED.");
        } else {
            System.out.println("TREE NOT ADDED.");
        }
    }

    public static void flowerAdded ( boolean result){
        if (result) {
            System.out.println("FLOWER SUCCESSFULLY ADDED.");
        } else {
            System.out.println("FLOWER NOT ADDED.");
        }
    }

    public static void decorAdded ( boolean result){
        if (result) {
            System.out.println("DECORATION SUCCESSFULLY ADDED.");
        } else {
            System.out.println("DECORATION NOT ADDED.");
        }
    }

    public static void productAdded (boolean result){
        if (result) {
            System.out.println("PRODUCT SUCCESSFULLY ADDED.");
        } else {
            System.out.println("PRODUCT NOT FOUND.");
        }
    }

    public static void ticketAdded ( boolean result){
        if (result) {
            System.out.println("TICKET SUCCESSFULLY ADDED.");
        } else {
            System.out.println("TICKET NOT FOUND.");
        }
    }

    public static void showMessage (String message){
        System.out.println(message);
    }

}


