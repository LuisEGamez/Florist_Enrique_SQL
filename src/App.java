import entities.*;
import repositories.DecorRepository;
import repositories.FlowerRepository;
import repositories.TicketRepository;
import repositories.TreeRepository;
import services.FloristService;
import tools.Keyboard;
import vista.View;

import java.sql.SQLException;
import java.util.List;


public class App {

    public static void main(String[] args) {
        Tree tree;
        Flower flower;
        Decor decor;
        Ticket ticket;
        List<Ticket> tickets;
        int choice, choice2;
        boolean selectMaterial;
        boolean productAddedToTicket = false;
        TreeRepository treeRepository = null;
        FlowerRepository flowerRepository = null;
        DecorRepository decorRepository = null;
        TicketRepository ticketRepository = null;
        try {

            treeRepository = new TreeRepository();
            flowerRepository = new FlowerRepository();
            decorRepository = new DecorRepository();
            ticketRepository = new TicketRepository();

        } catch (SQLException e) {

            View.showMessage("ERROR WHILE CONNECTING TO THE DB");
            e.printStackTrace();

        }

        FloristService floristService = new FloristService(treeRepository,flowerRepository,decorRepository,ticketRepository);

        //------------- Menu ------------------

        do{
            View.showOptions();
            choice = Keyboard.readInt("");
            if (!isBetween0And12(choice)){
                View.showOptions();
                choice = Keyboard.readInt("");
            }else{

                switch (choice){

                    case 0:
                        View.showMessage("SOFTWARE SUCCESSFULLY CLOSED");
                        break;
                    case 1:
                        tree = treeRepository.createTree(Keyboard.readString("ENTER NAME."), Keyboard.readDouble("ENTER HEIGHT"));
                        if (!treeRepository.exists(tree)) {
                            tree.setPrice(Keyboard.readDouble("ENTER PRICE"));
                        }
                        tree.setQuantity(Keyboard.readInt("ENTER QUANTITY"));
                        View.treeAdded(treeRepository.addTree(tree));
                        break;
                    case 2:
                        flower = flowerRepository.createFlower(Keyboard.readString("ENTER NAME."), Keyboard.readString("ENTER COLOR."));
                        if (!flowerRepository.exists(flower)) {
                            flower.setPrice(Keyboard.readDouble("ENTER PRICE"));
                        }
                        flower.setQuantity(Keyboard.readInt("ENTER QUANTITY"));
                        View.flowerAdded(flowerRepository.addFlower(flower));
                        break;
                    case 3:
                        decor = decorRepository.createDecor(Keyboard.readString("ENTER NAME."));

                        do{
                            selectMaterial = decor.setTypeOfMaterial(Keyboard.readInt("""
                                                                        MATERIAL:\s
                                                                        1-WOOD
                                                                        2-PLASTIC"""));
                            if(!selectMaterial){View.showMessage("SELECT 1 OR 2");}
                        }while (!selectMaterial);
                        if (!decorRepository.exists(decor)) {
                            decor.setPrice(Keyboard.readDouble("ENTER PRICE"));
                        }
                        decor.setQuantity(Keyboard.readInt("ENTER QUANTITY"));
                        View.decorAdded(decorRepository.addDecor(decor));
                        break;

                    case 4:
                        View.showStock(floristService.getTrees(), floristService.getFlowers(), floristService.getDecorations());
                        break;

                    case 5:

                        tree = treeRepository.createTree(Keyboard.readString("ENTER NAME"), Keyboard.readDouble("ENTER HEIGHT"));
                        if(treeRepository.exists(tree)){
                            tree.setQuantity(Keyboard.readInt("ENTER QUANTITY"));
                            View.showProductRemoved(treeRepository.removeTree(tree));
                        }else {
                            View.showMessage("PRODUCT NOT FOUND");
                        }
                        break;

                    case 6:
                        flower = flowerRepository.createFlower(Keyboard.readString("ENTER NAME"), Keyboard.readString("ENTER COLOR"));
                        if(flowerRepository.exists(flower)){
                            flower.setQuantity(Keyboard.readInt("ENTER QUANTITY"));
                            View.showProductRemoved(flowerRepository.removeFlower(flower));
                        }else {
                            View.showMessage("PRODUCT NOT FOUND");
                        }
                        break;

                    case 7:
                        decor = decorRepository.createDecor(Keyboard.readString("ENTER NAME."));

                        do{
                            selectMaterial = decor.setTypeOfMaterial(Keyboard.readInt("""
                                                                        MATERIAL:\s
                                                                        1-WOOD
                                                                        2-PLASTIC"""));
                            if(!selectMaterial){View.showMessage("SELECT 1 OR 2");}
                        }while (!selectMaterial);
                        if(decorRepository.exists(decor)){
                            decor.setQuantity(Keyboard.readInt("ENTER QUANTITY"));
                            View.showProductRemoved(decorRepository.removeDecor(decor));
                        }else {
                            View.showMessage("PRODUCT NOT FOUND");
                        }
                        break;

                    case 8:
                        View.showTotalValueFlorist(floristService.getTotalValue());
                        break;

                        /*
                        *
                        * Creamos un objeto Ticket en el que iremos añadiendo productos de nuestra base de datos. Una vez que el producto esta
                        * añadido al ticket se elimina de nuestra base de datos
                        *
                         */
                    case 9:
                        ticketRepository.addTicket();
                        ticket = ticketRepository.createTicket();
                        do {
                            View.showProducts();
                            choice2 = Keyboard.readInt("");
                            switch (choice2){

                                // Añadimos un árbol a la tabla que relaciona ticket y árboles

                                case 1:
                                    tree = treeRepository.createTree(Keyboard.readString("ENTER NAME"),Keyboard.readDouble("ENTER HEIGHT"));
                                    tree = treeRepository.findOneTree(tree);
                                    if(tree.getId() == 0){
                                        View.showMessage("PRODUCT NOT FOUND");
                                    }else if (tree.getQuantity() < 1) {
                                        View.showMessage("INSUFFICIENT STOCK");
                                    }else {
                                        productAddedToTicket = ticketRepository.insertTreeToTicket(ticket, tree);
                                        View.productAdded(productAddedToTicket);
                                        tree.setQuantity(1);
                                        treeRepository.removeTree(tree);
                                    }
                                    break;

                                // Añadimos una flor a la tabla que relaciona ticket y flores

                                case 2:
                                    flower = flowerRepository.createFlower(Keyboard.readString("ENTER NAME"),Keyboard.readString("ENTER COLOR"));
                                    flower = flowerRepository.findOneFlower(flower);
                                    if(flower.getId() == 0){
                                        View.showMessage("PRODUCT NOT FOUND");
                                    }else if (flower.getQuantity() < 1) {
                                        View.showMessage("INSUFFICIENT STOCK");
                                    }else {
                                        productAddedToTicket = ticketRepository.insertFlowerToTicket(ticket, flower);
                                        View.productAdded(productAddedToTicket);
                                        flower.setQuantity(1);
                                        flowerRepository.removeFlower(flower);
                                    }
                                    break;

                                // Añadimos una decor a la tabla que relaciona ticket y decor

                                case 3:
                                    decor = decorRepository.createDecor(Keyboard.readString("ENTER NAME."));
                                    do{
                                        selectMaterial = decor.setTypeOfMaterial(Keyboard.readInt("""
                                                                        MATERIAL:\s
                                                                        1-WOOD
                                                                        2-PLASTIC"""));
                                        if(!selectMaterial){View.showMessage("SELECT 1 OR 2");}
                                    }while (!selectMaterial);
                                    decor = decorRepository.findOneDecor(decor);
                                    if(decor.getId() == 0){
                                        View.showMessage("PRODUCT NOT FOUND");
                                    }else if (decor.getQuantity() < 1) {
                                        View.showMessage("INSUFFICIENT STOCK");
                                    }else {
                                        productAddedToTicket = ticketRepository.insertDecorToTicket(ticket, decor);
                                        View.productAdded(productAddedToTicket);
                                        decor.setQuantity(1);
                                        decorRepository.removeDecor(decor);
                                    }
                                    break;

                            }
                        }while (choice2 != 0);
                        floristService.setTotalValueTicket(ticket);
                        if(!productAddedToTicket){
                            ticketRepository.removeTicket(ticket);
                            View.showMessage("TICKET NOT ADDED, TICKET EMPTY, A TICKET MUST CONTAIN AT LEAST ONE PRODUCT");
                        }
                        break;

                    /*
                     *
                     * Esta opción nos devuelve una lista de todos los tickets entre las fechas señaladas,
                     * Recorre toda la lista de tickets antiguos y nos va devolviendo un ticket con la
                     * lista total de los productos que tiene cada ticket seleccionado.
                     *
                     * En cada vuelta de bucle mostramos toda la información de cada ticket.
                     *
                     */
                    case 10:

                        tickets = ticketRepository.getOldTickets(Keyboard.readString("DATE 1: YYYY-MM-DD"), Keyboard.readString("DATE 2: YYYY-MM-DD"));

                        for(Ticket tck: tickets){

                            View.showInfoTickets(floristService.setProductsOnTicket(tck));
                        }

                        break;

                    case 11:

                        View.showTotalSales(ticketRepository.getTotalPricesFromDatabase());

                        break;
                }

                System.out.println("--------------------------------------------");

            }
        }while (choice!=0);
    }

    static boolean isBetween0And12(int choice){
        return (choice >= 0) && (choice <=11);
    }

}
