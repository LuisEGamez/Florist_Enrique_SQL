package repositories;

import database.Database;
import entities.Flower;
import entities.Product;
import vista.View;

import java.sql.*;
import java.util.List;

public class FlowerRepository {

    private Database database;
    private Connection connection;

    public FlowerRepository(Database database) throws SQLException {
        this.database = database;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Florist_Enrique_SQL","root", "");
    }

    public Flower createFlower(String name, double price, String color){
        return new Flower(name, price, color);
    }

    public boolean addFlower(Flower flower){
        boolean added = false;
        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("INSERT INTO flower (name, price, color) VALUES ( ?, ?, ?)");

            miQuery.setString(1, flower.getName());
            miQuery.setDouble(2, flower.getPrice());
            miQuery.setString(3, flower.getcolor());

            miQuery.executeUpdate();
            added = true;

        } catch (SQLException e) {

            View.showMessage("Error al insertar flower");
            e.printStackTrace();

        }

        return added;
    }

    public boolean removeFlower(String name){

        boolean removed = false;
        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("DELETE FROM flower WHERE name = ? LIMIT 1");

            miQuery.setString(1, name);

            miQuery.executeUpdate();
            removed = true;

        } catch (SQLException e) {
            View.showMessage("Error al borrar flower");
            e.printStackTrace();
        }

        return removed;
    }

    public Product findOne (String name){
        Product product = null;
        boolean exist = false;
        int i = 0;

        while (!exist && i< database.getFlowers().size()) {

            if (name.equalsIgnoreCase(database.getFlowers().get(i).getName())) {

                exist = true;
                product = database.getFlowers().get(i);

            }
            i++;
        }
        return product;
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

    public ResultSet getFlowersFromDatabase(){
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement= connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM flower");

        } catch (SQLException e) {
            View.showMessage("Error al realizar la búsqueda en flower");
            e.printStackTrace();
        }

        return resultSet;
    }

    public double getFlowerPrice(int i){
        return database.getFlowers().get(i).getPrice();
    }

}
