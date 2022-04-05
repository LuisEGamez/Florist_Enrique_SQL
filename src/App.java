import entities.*;
import repositories.DecorRepository;
import repositories.FlowerRepository;
import repositories.TicketRepository;
import repositories.TreeRepository;
import services.FloristService;
import services.TicketService;
import tools.Keyboard;
import vista.View;

import java.sql.SQLException;



public class App {

    public static void main(String[] args) {

        Florist florist = new Florist("Margarita", "C/ Peru 254", "698574526");

        Tree tree;
        Flower flower;
        Decor decor;
        Ticket ticket;
        int choice, choice2;
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

            View.showMessage("Error al realizar la conexión con la base de datos");
            e.printStackTrace();

        }

        FloristService floristService = new FloristService(treeRepository,flowerRepository,decorRepository,ticketRepository);

        TicketService ticketService = new TicketService(ticketRepository,treeRepository, flowerRepository, decorRepository);

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
                        tree = treeRepository.createTree(Keyboard.readString("ENTER NAME."), Keyboard.readDouble("ENTER HEIGHT"),
                                                            Keyboard.readDouble("ENTER PRICE"), Keyboard.readInt("ENTER QUANTITY"));
                        View.treeAdded(treeRepository.addTree(tree));
                        break;
                    case 2:

                        flower = flowerRepository.createFlower(Keyboard.readString("ENTER NAME."), Keyboard.readString("ENTER COLOR."),
                                                                Keyboard.readDouble("ENTER PRICE"), Keyboard.readInt("ENTER QUANTITY"));

                        View.flowerAdded(flowerRepository.addFlower(flower));
                        break;
                    case 3:
                        decor = decorRepository.createDecor(Keyboard.readString("ENTER NAME."));
                        boolean select;
                        do{
                            select = decor.setTypeOfMaterial(Keyboard.readInt("""
                                                                        MATERIAL:\s
                                                                        1-WOOD
                                                                        2-PLASTIC"""));
                            if(!select){View.showMessage("SELECT 1 OR 2");}
                        }while (!select);
                        decor.setPrice(Keyboard.readDouble("ENTER PRICE"));
                        decor.setQuantity(Keyboard.readInt("ENTER QUANTITY"));
                        View.decorAdded(decorRepository.addDecor(decor));
                        break;

                    case 4:
                        View.showStock(floristService.getTrees(), floristService.getFlowers(), floristService.getDecorations());
                        break;

                    case 5:

                        tree = treeRepository.createTree(Keyboard.readString("ENTER NAME"), Keyboard.readDouble("ENTER HEIGHT"), Keyboard.readInt("ENTER QUANTITY"));

                        View.showRemoveMessageConfirmation(treeRepository.removeTree(tree));
                        break;

                    case 6:
                        flower = flowerRepository.createFlower(Keyboard.readString("ENTER NAME"), Keyboard.readString("ENTER COLOR"), Keyboard.readInt("ENTER QUANTITY"));

                        View.showRemoveMessageConfirmation(flowerRepository.removeFlower(flower));
                        break;

                    case 7:
                        decor = decorRepository.createDecor(Keyboard.readString("ENTER NAME."));
                        boolean select1;
                        do{
                            select1 = decor.setTypeOfMaterial(Keyboard.readInt("""
                                                                        MATERIAL:\s
                                                                        1-WOOD
                                                                        2-PLASTIC"""));
                            if(!select1){View.showMessage("SELECT 1 OR 2");}
                        }while (!select1);
                        decor.setQuantity(Keyboard.readInt("ENTER QUANTITY"));
                        View.showRemoveMessageConfirmation(decorRepository.removeDecor(decor));
                        break;

                    case 8:
                        View.showTotalValueFlorist(floristService.getTotalValue());
                        break;

                    case 9:
                        ticketRepository.addTicket();
                        ticket = ticketRepository.createTicket();
                        boolean productAdded = false;
                        do {
                            View.showProducts();
                            choice2 = Keyboard.readInt("");
                            switch (choice2){
                                case 1:
                                    tree = treeRepository.createTree(Keyboard.readString("ENTER NAME"),Keyboard.readDouble("ENTER HEIGHT"));
                                    tree = treeRepository.findOneTree(tree);
                                    if(tree.getId() == 0){
                                        View.showMessage("Producto no encontrado");
                                    }else if (tree.getQuantity() < 1) {
                                        View.showMessage("No hay stock del producto");
                                    }else {
                                        productAdded = ticketRepository.insertTreeToTicket(ticket, tree);
                                        View.productAdded(productAdded);
                                        tree.setQuantity(1);
                                        treeRepository.removeTree(tree);
                                    }
                                    break;
                                case 2:
                                    flower = flowerRepository.createFlower(Keyboard.readString("ENTER NAME"),Keyboard.readString("ENTER COLOR"));
                                    flower = flowerRepository.findOneFlower(flower);
                                    if(flower.getId() == 0){
                                        View.showMessage("Producto no encontrado");
                                    }else if (flower.getQuantity() < 1) {
                                        View.showMessage("No hay stock del producto");
                                    }else {
                                        productAdded = ticketRepository.insertFlowerToTicket(ticket, flower);
                                        View.productAdded(productAdded);
                                        flower.setQuantity(1);
                                        flowerRepository.removeFlower(flower);
                                    }
                                    break;
                                case 3:
                                    decor = decorRepository.createDecor(Keyboard.readString("ENTER NAME."));
                                    boolean select2;
                                    do{
                                        select2 = decor.setTypeOfMaterial(Keyboard.readInt("""
                                                                        MATERIAL:\s
                                                                        1-WOOD
                                                                        2-PLASTIC"""));
                                        if(!select2){View.showMessage("SELECT 1 OR 2");}
                                    }while (!select2);
                                    decor = decorRepository.findOneDecor(decor);
                                    if(decor.getId() == 0){
                                        View.showMessage("Producto no encontrado");
                                    }else if (decor.getQuantity() < 1) {
                                        View.showMessage("No hay stock del producto");
                                    }else {
                                        productAdded = ticketRepository.insertDecorToTicket(ticket, decor);
                                        View.productAdded(productAdded);
                                        decor.setQuantity(1);
                                        decorRepository.removeDecor(decor);
                                    }
                                    break;

                            }
                        }while (choice2 != 0);
                        ticketService.setTotalValueTicket(ticket);
                        if(!productAdded){
                            ticketRepository.removeTicket(ticket);
                            View.showMessage("Ticket eliminado");
                        }
                        break;

                    case 10:

                        ticketService.getOldTickets(Keyboard.readString("DATE 1: YYYY-MM-DD"), Keyboard.readString("DATE 2: YYYY-MM-DD"));

                        break;

                    case 11:
                        
                        //View.showTotalSales(ticketService.getTotalSales());

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
