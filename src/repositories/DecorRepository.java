package repositories;

import database.Database;
import entities.Decor;
import entities.Product;
import vista.View;

import java.sql.*;
import java.util.List;

public class DecorRepository {

    Database database;
    private Connection connection;

    public DecorRepository(Database database) throws SQLException {
        this.database = database;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Florist_Enrique_SQL","root", "");
    }

    public Decor createDecor(String name, double price){
        return new Decor(name, price);
    }

    public boolean addDecor(Decor decor){
        boolean added = false;
        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("INSERT INTO decoration (name, price, material) VALUES ( ?, ?, ?)");

            miQuery.setString(1, decor.getName());
            miQuery.setDouble(2, decor.getPrice());
            miQuery.setString(3, decor.getMaterial());

            miQuery.executeUpdate();
            added = true;

        } catch (SQLException e) {

            View.showMessage("Error al insertar decoration");
            e.printStackTrace();

        }

        return added;
    }

    public boolean removeDecor(String name){

        boolean removed = false;
        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("DELETE FROM decoration WHERE name = ? LIMIT 1");

            miQuery.setString(1, name);

            miQuery.executeUpdate();
            removed = true;

        } catch (SQLException e) {
            View.showMessage("Error al borrar decor");
            e.printStackTrace();
        }

        return removed;
    }

    public Product findOne (String name){
        Product product = null;
        boolean exist = false;
        int i = 0;

        while (!exist && i< database.getDecorations().size()) {

            if (name.equalsIgnoreCase(database.getDecorations().get(i).getName())) {

                exist = true;
                product = database.getDecorations().get(i);

            }
            i++;
        }
        return product;
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

    public ResultSet getDecorFromDatabase(){
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement= connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM decoration");

        } catch (SQLException e) {
            View.showMessage("Error al realizar la búsqueda en decoration");
            e.printStackTrace();
        }

        return resultSet;
    }

    public double getDecorPrice(int i){
        return database.getDecorations().get(i).getPrice();
    }
}
