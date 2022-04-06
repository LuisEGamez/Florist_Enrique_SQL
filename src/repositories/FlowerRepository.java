package repositories;

import entities.Flower;
import entities.Product;
import vista.View;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerRepository {

    private Connection connection;

    public FlowerRepository() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Florist_Enrique_SQL","root", "");
    }

    public Flower createFlower(String name, String color){
        return new Flower(name, color);
    }

    public boolean exists (Flower flower){

        boolean exists = false;
        PreparedStatement miQuery;
        ResultSet rs;

        try {
            miQuery = connection.prepareStatement("SELECT * FROM flowers WHERE name = ? AND color = ?");
            miQuery.setString(1, flower.getName());
            miQuery.setString(2, flower.getColor());
            rs = miQuery.executeQuery();

            if (rs.next()){
                exists = true;
            }

        } catch (SQLException e) {
            View.showMessage("ERROR WHEN CHECKING IF EXISTS FLOWER DB");
            e.printStackTrace();
        }

        return exists;

    }

    public PreparedStatement insertFlower(Flower flower) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("INSERT INTO flowers (name, color, price, quantity) VALUES ( ? , ?, ?, ?)");
        miQuery.setString(1, flower.getName());
        miQuery.setString(2, flower.getColor());
        miQuery.setDouble(3, flower.getPrice());
        miQuery.setInt(4, flower.getQuantity());

        return miQuery;
    }

    public PreparedStatement increaseQuantityFlower(Flower flower) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("UPDATE flowers SET quantity = quantity + ?  WHERE name = ? AND color = ?");
        miQuery.setDouble(1, flower.getQuantity());
        miQuery.setString(2, flower.getName());
        miQuery.setString(3, flower.getColor());

        return miQuery;
    }

    public PreparedStatement decreaseQuantityFlower(Flower flower) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("UPDATE flowers SET quantity = quantity - ?  WHERE name = ? AND color = ?");
        miQuery.setDouble(1, flower.getQuantity());
        miQuery.setString(2, flower.getName());
        miQuery.setString(3, flower.getColor());

        return miQuery;
    }

    public int getQuantityTypeOfFlower(Flower flower) throws SQLException {
        int quantity = 0;
        ResultSet rs;
        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("SELECT quantity FROM flowers WHERE name = ? And color = ?");
        miQuery.setString(1, flower.getName());
        miQuery.setString(2, flower.getColor());
        rs = miQuery.executeQuery();
        if(rs.next()) {
            quantity = rs.getInt("quantity");
        }
        return quantity;
    }

    public boolean addFlower(Flower flower){
        boolean added = false;
        PreparedStatement miQuery;

        try {
            if (!exists(flower)) {

                miQuery = insertFlower(flower);

            }else{

                miQuery = increaseQuantityFlower(flower);

            }
            miQuery.executeUpdate();
            added = true;

        } catch (SQLException e) {
            View.showMessage("ERROR WHEN ADDING FLOWER DB");
            e.printStackTrace();
        }

        return added;
    }

    public boolean removeFlower(Flower flower){

        boolean removed = false;
        int quantity;
        PreparedStatement miQuery;

            try {

                quantity = getQuantityTypeOfFlower(flower);

                if( quantity >= flower.getQuantity() ){
                    miQuery = decreaseQuantityFlower(flower);
                    miQuery.executeUpdate();
                    removed = true;
                }
            } catch (SQLException e) {
                View.showMessage("ERROR WHEN REMOVING FLOWER DB");
                e.printStackTrace();
            }

        return removed;
    }

    public Flower findOneFlower(Flower flower){
        PreparedStatement query;
        ResultSet rs;

        try {

            query = connection.prepareStatement("SELECT * FROM flowers WHERE name = ? AND color = ?");
            query.setString(1, flower.getName());
            query.setString(2, flower.getColor());
            rs = query.executeQuery();
            while (rs.next()){
                flower.setId(rs.getInt("id_flower"));
                flower.setName(rs.getString("name"));
                flower.setColor(rs.getString("color"));
                flower.setPrice(rs.getDouble("price"));
                flower.setQuantity(rs.getInt("quantity"));
            }

        } catch (SQLException e) {
            View.showMessage("ERROR WHEN FINDING FLOWER DB");
            e.printStackTrace();
        }

        return flower;
    }

    public List<Product> getFlowersFromDatabase(){
        List<Product> flowers = new ArrayList<>();
        Flower flower;
        Statement statement;
        ResultSet rs;
        try {

            statement= connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM flowers");

            while (rs.next()) {
                flower = new Flower();
                flower.setName(rs.getString("name"));
                flower.setColor(rs.getString("color"));
                flower.setPrice(rs.getDouble("price"));
                flower.setQuantity(rs.getInt("quantity"));
                flowers.add(flower);
            }

        } catch (SQLException e) {
            View.showMessage("ERROR WHEN FINDING FLOWER DB");
            e.printStackTrace();
        }

        return flowers;
    }

}
