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

    public Flower createFlower(String name, String color, double price, int quantity){
        return new Flower(name, color, price, quantity);
    }

    public Flower createFlower(String name, String color, int quantity){
        return new Flower(name, color, quantity);
    }

    public boolean exists (Flower flower){

        boolean exists = false;
        PreparedStatement miQuery;
        ResultSet rs;

        try {
            miQuery = connection.prepareStatement("SELECT * FROM flower WHERE name = ? AND color = ?");
            miQuery.setString(1, flower.getName());
            miQuery.setString(2, flower.getcolor());
            rs = miQuery.executeQuery();

            if (rs.next()){
                exists = true;
            }

        } catch (SQLException e) {
            View.showMessage("Error al comprobar la existencia de flower");
            e.printStackTrace();
        }

        return exists;

    }

    public PreparedStatement insertFlower(Flower flower) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("INSERT INTO flower (name, color, price, quantity) VALUES ( ? , ?, ?, ?)");
        miQuery.setString(1, flower.getName());
        miQuery.setString(2, flower.getcolor());
        miQuery.setDouble(3, flower.getPrice());
        miQuery.setInt(4, flower.getQuantity());

        return miQuery;
    }

    public PreparedStatement increaseQuantityFlower(Flower flower) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("UPDATE flower SET quantity = quantity + ?  WHERE name = ? AND color = ?");
        miQuery.setDouble(1, flower.getQuantity());
        miQuery.setString(2, flower.getName());
        miQuery.setString(3, flower.getcolor());

        return miQuery;
    }

    public PreparedStatement decreaseQuantityFlower(Flower flower) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("UPDATE flower SET quantity = quantity - ?  WHERE name = ? AND color = ?");
        miQuery.setDouble(1, flower.getQuantity());
        miQuery.setString(2, flower.getName());
        miQuery.setString(3, flower.getcolor());

        return miQuery;
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
            View.showMessage("Error al añadir tree");
            e.printStackTrace();
        }

        return added;
    }

    public boolean removeFlower(Flower flower){

        boolean removed = false;
        int quantity = 0;
        ResultSet rs;
        PreparedStatement miQuery;

        if(exists(flower)){
            try {
                miQuery = connection.prepareStatement("SELECT quantity FROM flower WHERE name = ? And color = ?");
                miQuery.setString(1,flower.getName());
                miQuery.setString(2, flower.getcolor());
                rs = miQuery.executeQuery();
                if(rs.next()) {
                    quantity = rs.getInt("quantity");
                }

                if( quantity >= flower.getQuantity() ){
                    miQuery = decreaseQuantityFlower(flower);
                    miQuery.executeUpdate();
                    removed = true;
                }else {
                    View.showMessage("La cantidad introducida supera el stock actual");
                }
            } catch (SQLException e) {
                View.showMessage("Error al eliminar cantidad tree");
                e.printStackTrace();
            }
        }

        return removed;
    }

    public Product findOne (String name){

        Flower flower = null;
        PreparedStatement query;
        ResultSet rs;

        try {

            query = connection.prepareStatement("SELECT * FROM flower WHERE name = ? LIMIT 1");
            query.setString(1, name);
            rs = query.executeQuery();

            while (rs.next()){
                flower = new Flower();
                flower.setName(rs.getString("name"));
                flower.setPrice(rs.getDouble("price"));
                flower.setColor(rs.getString("color"));

            }

        } catch (SQLException e) {
            View.showMessage("Error al buscar un flower");
            e.printStackTrace();
        }

        return flower;
    }

    public ResultSet getFlowerStockQuantity(){
        Statement query;
        ResultSet resultSet = null;
        try {
            query = connection.createStatement();
            resultSet = query.executeQuery("SELECT COUNT(*) FROM flower");


        } catch (SQLException e) {
            View.showMessage("Error al contar tabla flores");
            e.printStackTrace();
        }

        return resultSet;
    }

    public List<Product> getFlowersFromDatabase(){
        List<Product> flowers = new ArrayList<>();
        Flower flower;
        Statement statement;
        ResultSet rs;
        try {

            statement= connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM flower");

            while (rs.next()) {
                flower = new Flower();
                flower.setName(rs.getString("name"));
                flower.setColor(rs.getString("color"));
                flower.setPrice(rs.getDouble("price"));
                flower.setQuantity(rs.getInt("quantity"));
                flowers.add(flower);
            }

        } catch (SQLException e) {
            View.showMessage("Error al realizar la búsqueda en flower");
            e.printStackTrace();
        }

        return flowers;
    }

    public ResultSet getTotalPrice(){

        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement= connection.createStatement();
            resultSet = statement.executeQuery("SELECT SUM(price) AS \"Total\" FROM tree");

        } catch (SQLException e) {
            View.showMessage("Error al realizar la suma del precio en flower");
            e.printStackTrace();
        }

        return resultSet;
    }

}
