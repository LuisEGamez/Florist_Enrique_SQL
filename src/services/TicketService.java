package services;

import entities.Ticket;
import repositories.DecorRepository;
import repositories.FlowerRepository;
import repositories.TicketRepository;
import repositories.TreeRepository;
import vista.View;

public class TicketService {

    private TicketRepository ticketRepository;
    private TreeRepository treeRepository;
    private FlowerRepository flowerRepository;
    private DecorRepository decorRepository;

    public TicketService ( TicketRepository ticketRepository, TreeRepository treeRepository,
                           FlowerRepository flowerRepository, DecorRepository decorRepository) {
        this.ticketRepository = ticketRepository;
        this.treeRepository = treeRepository;
        this.flowerRepository = flowerRepository;
        this.decorRepository = decorRepository;
    }

    public void setTotalValueTicket(Ticket ticket){
        double total;
        total = ticketRepository.getTotalValueTreesByTicket(ticket) +
                    ticketRepository.getTotalValueFlowersByTicket(ticket) +
                        ticketRepository.getTotalValueDecorsByTicket(ticket);

        ticketRepository.setTotalValueTicket(ticket, total);
    }

    public void getOldTickets(String date1, String date2){

        for(Ticket ticket: ticketRepository.getOldTickets(date1,date2)){

            View.showInfoTickets(ticket, ticketRepository.getListTreesByTicket(ticket),
                                            ticketRepository.getListFlowersByTicket(ticket),
                                                ticketRepository.getListDecorsByTicket(ticket));
        }
    }

    /*public double getTotalSales(){
        double totalSales = 0;
        for(Double totalPrice : ticketRepository.getTotalPricesFromDatabase()){
            totalSales += totalPrice;
        }
        return totalSales;

    }*/
}
