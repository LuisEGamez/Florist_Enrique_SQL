package repositories;


import entities.*;
import vista.View;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TicketRepository {


    private Connection connection;

    public TicketRepository() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Florist_Enrique_SQL","root", "");
    }

    public boolean addTicket(){
        boolean added = false;
        Statement miQuery;

        try {
            miQuery = connection.createStatement();
            miQuery.executeUpdate("INSERT INTO tickets (id_ticket, date, Total) VALUES (NULL, current_timestamp(), NULL)");
            added = true;

        } catch (SQLException e) {
            System.out.println("ERROR WHEN INSERTING TICKET DB");
            e.printStackTrace();
        }

        return added;
    }

    public Ticket createTicket(){
        Ticket ticket = new Ticket();
        Statement miQuery;
        ResultSet rs;
        try {
            miQuery = connection.createStatement();
            rs = miQuery.executeQuery("SELECT * FROM tickets WHERE id_ticket = (SELECT MAX(id_ticket) FROM tickets)");
            while (rs.next()){
                ticket.setNumTicket(rs.getInt("id_ticket"));
                ticket.setDate(rs.getString("date"));
            }


        } catch (SQLException e) {
            System.out.println("ERROR WHEN FINDING TICKET DB");
            e.printStackTrace();
        }

        return ticket;
    }

    public boolean removeTicket(Ticket ticket){
        boolean removed = false;
        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("DELETE FROM tickets WHERE id_ticket = ?");
            miQuery.setInt(1, ticket.getNumTicket());
            miQuery.executeUpdate();
            removed = true;

        } catch (SQLException e) {
            System.out.println("ERROR WHEN REMOVING TICKET DB");
            e.printStackTrace();
        }

        return removed;
    }

    public boolean insertTreeToTicket(Ticket ticket, Tree tree){

        boolean insert = false;
        PreparedStatement miQuery;
        int idTree = tree.getId();

        if(idTree != 0) {
            try {
                miQuery = connection.prepareStatement("INSERT INTO tickets_has_trees (ticket_id, tree_id) VALUES ( ?, ?)");
                miQuery.setInt(1, ticket.getNumTicket());
                miQuery.setInt(2, idTree);
                miQuery.executeUpdate();
                insert = true;

            } catch (SQLException e) {
                View.showMessage("ERROR WHEN INSERTING TREE TO TICKET DB");
                e.printStackTrace();
            }
        }

        return insert;
    }

    public boolean insertFlowerToTicket(Ticket ticket, Flower flower){

        boolean insert = false;
        PreparedStatement miQuery;
        int idFlower = flower.getId();

        if(idFlower != 0) {
            try {
                miQuery = connection.prepareStatement("INSERT INTO tickets_has_flowers (tickets_id, flowers_id) VALUES ( ?, ?)");
                miQuery.setInt(1, ticket.getNumTicket());
                miQuery.setInt(2, idFlower);
                miQuery.executeUpdate();
                insert = true;

            } catch (SQLException e) {
                View.showMessage("ERROR WHEN INSERTING FLOWER TO TICKET DB");
                e.printStackTrace();
            }
        }

        return insert;
    }

    public boolean insertDecorToTicket(Ticket ticket, Decor decor){

        boolean insert = false;
        PreparedStatement miQuery;
        int idDecor = decor.getId();

        if(idDecor != 0) {
            try {
                miQuery = connection.prepareStatement("INSERT INTO tickets_has_decorations (tickets_id, decorations_id) VALUES ( ?, ?)");
                miQuery.setInt(1, ticket.getNumTicket());
                miQuery.setInt(2, idDecor);
                miQuery.executeUpdate();
                insert = true;

            } catch (SQLException e) {
                View.showMessage("ERROR WHEN INSERTING DECORATION TO TICKET DB");
                e.printStackTrace();
            }
        }

        return insert;
    }

    public double getTotalValueTreesByTicket(Ticket ticket){
        double result = 0;
        PreparedStatement miQuery;
        ResultSet rs;

            try {
                miQuery = connection.prepareStatement("SELECT SUM(price) AS 'Total' FROM tickets_has_trees LEFT JOIN trees ON tree_id = id_tree WHERE ticket_id = ?");
                miQuery.setInt(1, ticket.getNumTicket());
                rs = miQuery.executeQuery();
                if (rs.next()) {
                    result = rs.getDouble("Total");
                }


            } catch (SQLException e) {
                View.showMessage("ERROR WHEN SUM TOTAL TREES DB");
                e.printStackTrace();
            }
        return result;

    }

    public double getTotalValueFlowersByTicket(Ticket ticket){
        double result = 0;
        PreparedStatement miQuery;
        ResultSet rs;

        try {
            miQuery = connection.prepareStatement("SELECT SUM(price) AS 'Total' FROM tickets_has_flowers LEFT JOIN flowers ON flowers_id = id_flower WHERE tickets_id = ?");
            miQuery.setInt(1, ticket.getNumTicket());
            rs = miQuery.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("Total");
            }


        } catch (SQLException e) {
            View.showMessage("ERROR WHEN SUM TOTAL FLOWERS DB");
            e.printStackTrace();
        }
        return result;
    }

    public double getTotalValueDecorsByTicket(Ticket ticket){
        double result = 0;
        PreparedStatement miQuery;
        ResultSet rs;

        try {
            miQuery = connection.prepareStatement("SELECT SUM(price) AS 'Total' FROM tickets_has_decorations LEFT JOIN decorations ON decorations_id = id_decoration WHERE tickets_id = ?");
            miQuery.setInt(1, ticket.getNumTicket());
            rs = miQuery.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("Total");
            }


        } catch (SQLException e) {
            View.showMessage("ERROR WHEN SUM TOTAL DECORATIONS DB");
            e.printStackTrace();
        }
        return result;
    }

    public void setTotalValueTicket(Ticket ticket, double totalValueTicket){

        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("UPDATE tickets SET Total = ?  WHERE id_ticket = ? ");
            miQuery.setDouble(1, totalValueTicket);
            miQuery.setInt(2, ticket.getNumTicket());
            miQuery.executeUpdate();

        } catch (SQLException e) {
            View.showMessage("ERROR WHEN UPDATING TOTAL TICKET DB");
            e.printStackTrace();
        }

    }

    public List<Ticket> getOldTickets(String date1, String date2) {

        List<Ticket> oldTickets = new ArrayList<>();

        Ticket ticket;
        PreparedStatement query;
        ResultSet rs;

        try {

            query = connection.prepareStatement("SELECT * FROM tickets WHERE date between ? and ?");
            query.setString(1, date1);
            query.setString(2, date2);
            rs = query.executeQuery();
            while (rs.next()){
                ticket = new Ticket();
                ticket.setNumTicket(rs.getInt("id_ticket"));
                ticket.setDate(rs.getString("date"));
                ticket.setTotal(rs.getDouble("Total"));

                oldTickets.add(ticket);

            }

        } catch (SQLException e) {
            View.showMessage("ERROR WHEN FINDING TICKET DB");
            e.printStackTrace();
        }

        return oldTickets;
    }

    public List<Product> getListTreesByTicket(Ticket ticket){

        List<Product> trees = new ArrayList<>();

        Tree tree;
        PreparedStatement query;
        ResultSet rs;

        try {

            query = connection.prepareStatement("SELECT  name, height, price FROM tickets_has_trees LEFT JOIN trees ON tree_id = id_tree WHERE ticket_id = ?");
            query.setInt(1, ticket.getNumTicket());
            rs = query.executeQuery();
            while (rs.next()){
                tree = new Tree();
                tree.setName(rs.getString("name"));
                tree.setHeight(rs.getDouble("height"));
                tree.setPrice(rs.getDouble("price"));

                trees.add(tree);

            }

        } catch (SQLException e) {
            View.showMessage("ERROR WHEN FINDING TREE DB");
            e.printStackTrace();
        }

        return trees;

    }

    public List<Product> getListFlowersByTicket(Ticket ticket){

        List<Product> flowers = new ArrayList<>();

        Flower flower;
        PreparedStatement query;
        ResultSet rs;

        try {

            query = connection.prepareStatement("SELECT  name, color, price FROM tickets_has_flowers LEFT JOIN flowers ON flowers_id = id_flower WHERE tickets_id = ?");
            query.setInt(1, ticket.getNumTicket());
            rs = query.executeQuery();
            while (rs.next()){
                flower = new Flower();
                flower.setName(rs.getString("name"));
                flower.setColor(rs.getString("color"));
                flower.setPrice(rs.getDouble("price"));

                flowers.add(flower);

            }

        } catch (SQLException e) {
            View.showMessage("ERROR WHEN FINDING FLOWER DB");
            e.printStackTrace();
        }

        return flowers;

    }

    public List<Product> getListDecorsByTicket(Ticket ticket){

        List<Product> decors = new ArrayList<>();

        Decor decor;
        PreparedStatement query;
        ResultSet rs;

        try {

            query = connection.prepareStatement("SELECT  name, material, price FROM tickets_has_decorations LEFT JOIN decorations ON decorations_id = id_decoration WHERE tickets_id = ?");
            query.setInt(1, ticket.getNumTicket());
            rs = query.executeQuery();
            while (rs.next()){
                decor = new Decor();
                decor.setName(rs.getString("name"));
                decor.setTypeOfMaterial(rs.getString("material"));
                decor.setPrice(rs.getDouble("price"));

                decors.add(decor);

            }

        } catch (SQLException e) {
            View.showMessage("ERROR WHEN FINDING DECORATION DB");
            e.printStackTrace();
        }

        return decors;

    }

    public double getTotalPricesFromDatabase(){

        double result = 0;
        Statement miQuery;
        ResultSet rs;

        try {
            miQuery = connection.createStatement();
            rs = miQuery.executeQuery("SELECT SUM(Total) AS Total FROM tickets");
            if (rs.next()) {
                result = rs.getDouble("Total");
            }


        } catch (SQLException e) {
            View.showMessage("ERROR WHEN SUM TOTAL TICKET DB");
            e.printStackTrace();
        }
        return result;

    }

}
