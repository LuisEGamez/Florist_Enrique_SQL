package services;

import entities.Product;
import entities.Ticket;
import repositories.DecorRepository;
import repositories.FlowerRepository;
import repositories.TicketRepository;
import repositories.TreeRepository;

import java.util.ArrayList;
import java.util.List;

public class FloristService {

    private TreeRepository treeRepository;
    private FlowerRepository flowerRepository;
    private DecorRepository decorRepository;
    private TicketRepository ticketRepository;



    public FloristService(TreeRepository treeRepository, FlowerRepository flowerRepository,
                          DecorRepository decorRepository, TicketRepository ticketRepository){
        this.treeRepository = treeRepository;
        this.flowerRepository = flowerRepository;
        this.decorRepository = decorRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Product> getTrees(){

        return treeRepository.getTreesFromDatabase();
    }

    public List<Product> getFlowers(){

        return flowerRepository.getFlowersFromDatabase();
    }

    public List<Product> getDecorations(){

        return  decorRepository.getDecorFromDatabase();
    }

    public double getTotalValue(){
        return getTotalValueTrees()+getTotalValueFlowers()+getTotalValueDecor();
    }

    public double getTotalValueTrees(){

        double result = 0;

        for (Product product: getTrees()){

            result += product.getPrice()* product.getQuantity();

        }
        return result;
    }

    public double getTotalValueFlowers(){

        double result = 0;

        for (Product product: getFlowers()){

            result += product.getPrice()* product.getQuantity();

        }
        return result;
    }

    public double getTotalValueDecor(){

        double result = 0;

        for (Product product: getDecorations()){

            result += product.getPrice()* product.getQuantity();

        }
        return result;
    }

    public void setTotalValueTicket(Ticket ticket){
        double total;
        total = ticketRepository.getTotalValueTreesByTicket(ticket) +
                ticketRepository.getTotalValueFlowersByTicket(ticket) +
                ticketRepository.getTotalValueDecorsByTicket(ticket);

        ticketRepository.setTotalValueTicket(ticket, total);
    }

    /*
    *
    * Este método recorre toda la lista de tickets antiguos y nos devuelve un tique con una
    * lista de todos los productos que tiene cada ticket seleccionado.
    *
     */

    public Ticket getOldTickets(String date1, String date2){

        Ticket result = null;
        List<Ticket> tickets = ticketRepository.getOldTickets(date1,date2);
        for(Ticket ticket: tickets){

            result = setProductsOnTicket(ticket);
        }

        return result;
    }

    /*
    *
    * Tomamos las listas de producto que nos devuelven las consultas y las añadimos a una
    * lista conjunta de productos que añadimos a cada ticket.
    *
     */

    public Ticket setProductsOnTicket(Ticket ticket){

        List<Product> products = new ArrayList<>();

        products.addAll(ticketRepository.getListTreesByTicket(ticket));
        products.addAll(ticketRepository.getListFlowersByTicket(ticket));
        products.addAll(ticketRepository.getListDecorsByTicket(ticket));

        ticket.getProducts().addAll(products);

        return ticket;

    }


}
