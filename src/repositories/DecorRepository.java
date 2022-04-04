package repositories;


import entities.Decor;
import entities.Product;
import vista.View;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DecorRepository {


    private Connection connection;

    public DecorRepository() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Florist_Enrique_SQL","root", "");
    }

    public Decor createDecor(String name){
        return new Decor(name);
    }

    public boolean exists (Decor decor){

        boolean exists = false;
        PreparedStatement miQuery;
        ResultSet rs;

        try {
            miQuery = connection.prepareStatement("SELECT * FROM decoration WHERE name = ? AND material = ?");
            miQuery.setString(1, decor.getName());
            miQuery.setString(2, decor.getMaterial());
            rs = miQuery.executeQuery();

            if (rs.next()){
                exists = true;
            }

        } catch (SQLException e) {
            View.showMessage("Error al comprobar la existencia de tree");
            e.printStackTrace();
        }

        return exists;

    }

    public PreparedStatement insertDecor(Decor decor) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("INSERT INTO decoration (name, material, price, quantity) VALUES ( ? , ?, ?, ?)");
        miQuery.setString(1, decor.getName());
        miQuery.setString(2, decor.getMaterial());
        miQuery.setDouble(3, decor.getPrice());
        miQuery.setInt(4, decor.getQuantity());

        return miQuery;
    }

    public PreparedStatement increaseQuantityDecor(Decor decor) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("UPDATE decoration SET quantity = quantity + ?  WHERE name = ? AND material = ? ");
        miQuery.setDouble(1, decor.getQuantity());
        miQuery.setString(2, decor.getName());
        miQuery.setString(3, decor.getMaterial());

        return miQuery;
    }

    public PreparedStatement decreaseQuantityDecor(Decor decor) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("UPDATE decoration SET quantity = quantity - ?  WHERE name = ? AND material = ? ");
        miQuery.setDouble(1, decor.getQuantity());
        miQuery.setString(2, decor.getName());
        miQuery.setString(3, decor.getMaterial());

        return miQuery;
    }

    public boolean addDecor(Decor decor){
        boolean added = false;
        PreparedStatement miQuery;

        try {
            if (!exists(decor)) {

                miQuery = insertDecor(decor);

            }else{

                miQuery = increaseQuantityDecor(decor);

            }
            miQuery.executeUpdate();
            added = true;

        } catch (SQLException e) {
            View.showMessage("Error al añadir decoration");
            e.printStackTrace();
        }

        return added;
    }

    public boolean removeDecor(Decor decor){

        boolean removed = false;
        int quantity = 0;
        ResultSet rs;
        PreparedStatement miQuery;

        if(exists(decor)){
            try {
                miQuery = connection.prepareStatement("SELECT quantity FROM decoration WHERE name = ? And material = ?");
                miQuery.setString(1,decor.getName());
                miQuery.setString(2, decor.getMaterial());
                rs = miQuery.executeQuery();
                if(rs.next()) {
                    quantity = rs.getInt("quantity");
                }

                if( quantity >= decor.getQuantity() ){
                    miQuery = decreaseQuantityDecor(decor);
                    miQuery.executeUpdate();
                    removed = true;
                }else {
                    View.showMessage("La cantidad introducida supera el stock actual");
                }
            } catch (SQLException e) {
                View.showMessage("Error al eliminar cantidad decoration");
                e.printStackTrace();
            }
        }

        return removed;
    }

    public Product findOne (String name){
        Decor decor = null;
        PreparedStatement query;
        ResultSet rs;

        try {

            query = connection.prepareStatement("SELECT * FROM decoration WHERE name = ? LIMIT 1");
            query.setString(1, name);
            rs = query.executeQuery();
            while (rs.next()){
                decor = new Decor();
                decor.setName(rs.getString("name"));
                decor.setPrice(rs.getDouble("price"));
                decor.setTypeOfMaterial(rs.getString("material"));

            }

        } catch (SQLException e) {
            View.showMessage("Error al buscar un decoration");
            e.printStackTrace();
        }

        return decor;
    }

    public ResultSet getDecorStockQuantity(){
        Statement query;
        ResultSet resultSet = null;
        try {
            query = connection.createStatement();
            resultSet = query.executeQuery("SELECT COUNT(*) FROM decoration");


        } catch (SQLException e) {
            View.showMessage("Error al contar tabla decoration");
            e.printStackTrace();
        }

        return resultSet;
    }

    public List<Product> getDecorFromDatabase(){
        List<Product> decors = new ArrayList<>();
        Decor decor;
        Statement statement;
        ResultSet rs;
        try {

            statement= connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM decoration");

            while (rs.next()) {
                decor = new Decor();
                decor.setName(rs.getString("name"));
                decor.setTypeOfMaterial(rs.getString("material"));
                decor.setPrice(rs.getDouble("price"));
                decor.setQuantity(rs.getInt("quantity"));
                decors.add(decor);
            }

        } catch (SQLException e) {
            View.showMessage("Error al realizar la búsqueda en flower");
            e.printStackTrace();
        }

        return decors;
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
