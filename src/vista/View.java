package vista;

import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;




public class View {

    public static void showStock(ResultSet resultSetTrees, ResultSet resultSetFlowers, ResultSet resultSetDecor ) {

        try {
            showMessage("TREES:");
            while (resultSetTrees.next()) {
                showMessage(resultSetTrees.getString("name") + " " + resultSetTrees.getString("price") +
                            " " + resultSetTrees.getString("height"));
            }

            showMessage("FLOWERS:");
            while (resultSetFlowers.next()) {
                showMessage(resultSetFlowers.getString("name") + " " + resultSetFlowers.getString("price") +
                        " " + resultSetFlowers.getString("color"));
            }

            showMessage("DECORATIONS:");
            while (resultSetDecor.next()) {
                showMessage(resultSetDecor.getString("name") + " " + resultSetDecor.getString("price") +
                        " " + resultSetDecor.getString("material"));
            }

        }catch (SQLException e){
            showMessage("Error al mostrar productos");
            e.printStackTrace();
        }

    }

    public static void showTotalValueFlorist(double totalValue) {

        System.out.println("TOTAL VALUE:" + totalValue + "€");

    }

    public static void showStockByProduct(ResultSet resultSetTrees, ResultSet resultSetFlowers, ResultSet resultSetDecor) {

        try {
            while (resultSetTrees.next()){
                showMessage("TREES: " + resultSetTrees.getString(1));
            }

            while (resultSetFlowers.next()){
                showMessage("FLOWERS: " + resultSetFlowers.getString(1));
            }

            while (resultSetDecor.next()){
                showMessage("DECORATION: " + resultSetDecor.getString(1));
            }

        }catch (SQLException e){
            showMessage("Error al mostrar resultado cuenta");
            e.printStackTrace();
        }

    }

    /*public static void showInfoTicket(Ticket ticket) {

        //System.out.println("TICKET NUMBER: " + ticket.getNumTicket() +
                            //"\nDATE: " + ticket.getDate());

        for (Product product : ticket.getProducts()) {

            if (product instanceof Tree) {

                Tree tree = (Tree) product;
                //System.out.println(tree.showInfo());

            } else if (product instanceof Flower) {

                Flower flower = (Flower) product;
                //System.out.println(flower.showInfo());

            } else if (product instanceof Decor) {

                Decor decor = (Decor) product;
                //System.out.println(decor.showInfo());
            }
        }

        System.out.println("TICKET TOTAL: " + ((double) Math.round(ticket.getTotal() * 100d) / 100d) + "€");
    }*/

    /*public static void showOldTickets(List<Ticket> oldTickets) {

        oldTickets.forEach(x -> showInfoTicket(x));

    }*/

    public static void showRemoveMessageConfirmation(boolean exist) { // Revisar
        if (exist) {
            System.out.println("PRODUCT SUCCESSFULLY REMOVE.");
        } else {
            System.out.println("PRODUCT NOT FOUND.");
        }
    }

    public static void showTotalSales(double totalSales) {

        System.out.println("TOTAL SALES: " + ((double) Math.round(totalSales * 100d) / 100d) + "€");

    }

    public static void options() {
        System.out.println("SELECT OPTION 0 - 12:");
        System.out.println("""
                1-ADD TREE.
                2-ADD FLOWER.
                3-ADD DECOR.
                4-SHOW STOCK.
                5-REMOVE TREE.
                6-REMOVE FLOWER.
                7-REMOVE DECOR.
                8-SHOW STOCK QUANTITY
                9-TOTAL VALUE
                10-CREATE TICKET
                11-SHOW OLD TICKETS
                12-SHOW TOTAL MONEY
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

    public static void productAdded ( boolean result){
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


