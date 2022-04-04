package repositories;


import entities.Ticket;
import vista.View;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {


    private Connection connection;

    public TicketRepository() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Florist_Enrique_SQL","root", "");
    }


    public Ticket createTicket(){
        return new Ticket();
    }

    public boolean addTicket(Ticket ticket){
        boolean added = false;
        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("INSERT INTO ticket (productos, total) VALUES (?, ?)");
            miQuery.setString(1, ticket.listProductsToJson());
            miQuery.setDouble(2, ticket.getTotal());
            miQuery.executeUpdate();
            added = true;

        } catch (SQLException e) {
            System.out.println("Error al insertar tree");
            e.printStackTrace();
        }

        return added;
    }

    /*public Ticket findOne(int i){



    }

    public List<Ticket> findAll(){



    }*/

    public List<Ticket> getOldSales(String date1, String date2) {

        List<Ticket> oldTickets = new ArrayList<>();

        Ticket ticket;
        PreparedStatement query;
        ResultSet rs;

        try {

            query = connection.prepareStatement("SELECT * FROM `ticket` WHERE date  between ? and ?");
            query.setString(1, date1);
            query.setString(2, date2);
            rs = query.executeQuery();
            while (rs.next()){

                ticket = new Ticket();
                ticket.setNumTicket(rs.getInt("id_ticket"));
                ticket.setDate(rs.getString("date"));
                ticket.setProductos(rs.getString("productos"));
                ticket.setTotal(rs.getDouble("Total"));

                oldTickets.add(ticket);

            }

        } catch (SQLException e) {
            View.showMessage("Error al buscar un tree");
            e.printStackTrace();
        }

        return oldTickets;
    }

    /*public List<Ticket> getOldSales(LocalDate date1, LocalDate date2){

        List<Ticket> oldTickets = new ArrayList<>();

        for (int i = 0; i<findAll().size(); i++){

            if(findOne(i).getDate().compareTo(date2) < 0 && findOne(i).getDate().compareTo(date1) > 0 ){

                oldTickets.add(findOne(i));

            }
        }

        return oldTickets;
    }*/

    /*public List<Double> getTotalPricesFromDatabase(){

        List<Double> totalSalesList = new ArrayList<>();

        for(int i = 0; i<database.getTickets().size(); i++){
            totalSalesList.add(database.getTickets().get(i).getTotal());

        }
        return totalSalesList;

    }*/

}
