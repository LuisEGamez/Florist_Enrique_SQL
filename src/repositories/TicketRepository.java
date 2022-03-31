package repositories;

import database.Database;
import entities.Ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    private Database database;

    private Connection connection;

    public TicketRepository(Database database) throws SQLException {
        this.database = database;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Florist_Enrique_SQL","root", "");
    }


    public Ticket createTicket(){
        return new Ticket();
    }

    public boolean addTicket(Ticket ticket){
        boolean added = false;
        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("INSERT INTO ticket () VALUES ()");

            miQuery.executeUpdate();
            added = true;

        } catch (SQLException e) {
            System.out.println("Error al insertar tree");
            e.printStackTrace();
        }

        return added;
    }

    public Ticket findOne(int i){

        return database.getTickets().get(i);

    }

    public List<Ticket> findAll(){

        return database.getTickets();

    }

    /*public List<Ticket> getOldSales(LocalDate date1) {

        List<Ticket> oldTickets = new ArrayList<>();

        for (int i = 0; i < findAll().size(); i++) {

            if (findOne(i).getDate().compareTo(date1) <= 0) {

                oldTickets.add(findOne(i));

            }
        }

        return oldTickets;
    }*/

    /*public List<Ticket> getOldSales(LocalDate date1, LocalDate date2){

        List<Ticket> oldTickets = new ArrayList<>();

        for (int i = 0; i<findAll().size(); i++){

            if(findOne(i).getDate().compareTo(date2) < 0 && findOne(i).getDate().compareTo(date1) > 0 ){

                oldTickets.add(findOne(i));

            }
        }

        return oldTickets;
    }*/

    public List<Double> getTotalPricesFromDatabase(){

        List<Double> totalSalesList = new ArrayList<>();

        for(int i = 0; i<database.getTickets().size(); i++){
            totalSalesList.add(database.getTickets().get(i).getTotal());

        }
        return totalSalesList;

    }

}
