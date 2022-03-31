package repositories;

import database.Database;
import entities.Product;
import entities.Tree;
import vista.View;

import java.sql.*;

public class TreeRepository {

    private Database database;

    private Connection connection;

    public TreeRepository(Database database) throws SQLException {
        this.database = database;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Florist_Enrique_SQL","root", "");
    }

    public Tree createTree(String name, double price, double height){
        return new Tree(name, price, height);
    }

    public boolean addTree (Tree tree )  {

        boolean added = false;
        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("INSERT INTO tree (name, price, height) VALUES ( ? , ?, ?)");

            miQuery.setString(1, tree.getName());
            miQuery.setDouble(2, tree.getPrice());
            miQuery.setDouble(3, tree.getHeight());

            miQuery.executeUpdate();
            added = true;

        } catch (SQLException e) {
            View.showMessage("Error al insertar tree");
            e.printStackTrace();
        }

        return added;

    }

    public boolean removeTree(String name){

        boolean removed = false;
        PreparedStatement miQuery;

        try {
            miQuery = connection.prepareStatement("DELETE FROM tree WHERE name = ? LIMIT 1");

            miQuery.setString(1, name);

            miQuery.executeUpdate();
            removed = true;

        } catch (SQLException e) {
            View.showMessage("Error al borrar tree");
            e.printStackTrace();
        }

        return removed;
    }

    public Product findOne (String name){
        Product product = null;
        boolean exist = false;
        int i = 0;

        while (!exist && i< database.getTrees().size()) {

            if (name.equalsIgnoreCase(database.getTrees().get(i).getName())) {

                exist = true;
                product = database.getTrees().get(i);

            }
            i++;
        }
        return product;
    }

    public ResultSet getTreeStockQuantity(){
        Statement query;
        ResultSet resultSet = null;
        try {
            query = connection.createStatement();
            resultSet = query.executeQuery("SELECT COUNT(*) FROM tree");


        } catch (SQLException e) {
            View.showMessage("Error al al contar trees");
            e.printStackTrace();
        }

        return resultSet;

    }

    public ResultSet getTreesFromDatabase(){
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement= connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tree");

        } catch (SQLException e) {
            View.showMessage("Error al realizar la búsqueda en tree");
            e.printStackTrace();
        }

        return resultSet;
    }

    public double getTreePrice(int i){

        return database.getTrees().get(i).getPrice();

    }
}
