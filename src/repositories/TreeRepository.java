package repositories;


import entities.Product;
import entities.Tree;
import vista.View;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreeRepository {


    private Connection connection;

    public TreeRepository() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Florist_Enrique_SQL","root", "");
    }

    public Tree createTree(String name, double price, double height, int quantity){
        return new Tree(name, price, height, quantity);
    }

    public Tree createTree(String name, double height){
        return new Tree(name, height);
    }

    public Tree createTree(String name, double height, int quantity){
        return new Tree(name, height, quantity);
    }

    public boolean exists (Tree tree){

        boolean exists = false;
        PreparedStatement miQuery;
        ResultSet rs;

        try {
            miQuery = connection.prepareStatement("SELECT * FROM trees WHERE name = ? AND height = ?");
            miQuery.setString(1, tree.getName());
            miQuery.setDouble(2, tree.getHeight());
            rs = miQuery.executeQuery();

            if (rs.next()){
                exists = true;
            }

        } catch (SQLException e) {
            View.showMessage("Error al comprobar la existencia de trees");
            e.printStackTrace();
        }

        return exists;

    }

    public PreparedStatement insertTree(Tree tree) throws SQLException {

        PreparedStatement miQuery;

            miQuery = connection.prepareStatement("INSERT INTO trees (name, price, height, quantity) VALUES ( ? , ?, ?, ?)");
            miQuery.setString(1, tree.getName());
            miQuery.setDouble(2, tree.getPrice());
            miQuery.setDouble(3, tree.getHeight());
            miQuery.setInt(4, tree.getQuantity());

        return miQuery;
    }

    public PreparedStatement increaseQuantityTree(Tree tree) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("UPDATE trees SET quantity = quantity + ?  WHERE name = ? AND height = ? ");
        miQuery.setDouble(1, tree.getQuantity());
        miQuery.setString(2, tree.getName());
        miQuery.setDouble(3, tree.getHeight());

        return miQuery;
    }

    public PreparedStatement decreaseQuantityTree(Tree tree) throws SQLException {

        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("UPDATE trees SET quantity = quantity - ?  WHERE name = ? AND height = ? ");
        miQuery.setDouble(1, tree.getQuantity());
        miQuery.setString(2, tree.getName());
        miQuery.setDouble(3, tree.getHeight());

        return miQuery;
    }

    public int getQuantityTypeOfTree(Tree tree) throws SQLException {
        int quantity = 0;
        ResultSet rs;
        PreparedStatement miQuery;

        miQuery = connection.prepareStatement("SELECT quantity FROM trees WHERE name = ? And height = ?");
        miQuery.setString(1,tree.getName());
        miQuery.setDouble(2, tree.getHeight());
        rs = miQuery.executeQuery();
        if(rs.next()) {
            quantity = rs.getInt("quantity");
        }
        return quantity;
    }

    public boolean addTree (Tree tree )  {

        boolean added = false;
        PreparedStatement miQuery;

        try {
            if (!exists(tree)) {

                miQuery = insertTree(tree);

            }else{

                miQuery = increaseQuantityTree(tree);

            }
            miQuery.executeUpdate();
            added = true;

        } catch (SQLException e) {
            View.showMessage("Error al añadir tree");
            e.printStackTrace();
        }

        return added;

    }

    public boolean removeTree(Tree tree){

        boolean removed = false;
        int quantity;
        PreparedStatement miQuery;

            try {

                quantity = getQuantityTypeOfTree(tree);

                if( quantity >= tree.getQuantity() ){
                    miQuery = decreaseQuantityTree(tree);
                    miQuery.executeUpdate();
                    removed = true;
                }
            } catch (SQLException e) {
                View.showMessage("Error al eliminar cantidad trees");
                e.printStackTrace();
            }

        return removed;
    }

    public Tree findOneTree(Tree tree){
        PreparedStatement query;
        ResultSet rs;

        try {

            query = connection.prepareStatement("SELECT * FROM trees WHERE name = ? AND height = ?");
            query.setString(1, tree.getName());
            query.setDouble(2, tree.getHeight());
            rs = query.executeQuery();
            while (rs.next()){
                tree.setId(rs.getInt("id_tree"));
                tree.setName(rs.getString("name"));
                tree.setHeight(rs.getDouble("height"));
                tree.setPrice(rs.getDouble("price"));
                tree.setQuantity(rs.getInt("quantity"));
            }

        } catch (SQLException e) {
            View.showMessage("Error al buscar un tree");
            e.printStackTrace();
        }

        return tree;
    }

    public List<Product> getTreesFromDatabase(){
        List<Product> trees = new ArrayList<>();
        Tree tree;
        Statement statement;
        ResultSet rs;
        try {

            statement= connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM trees");

            while (rs.next()) {
                tree = new Tree();
                tree.setName(rs.getString("name"));
                tree.setHeight(rs.getDouble("height"));
                tree.setPrice(rs.getDouble("price"));
                tree.setQuantity(rs.getInt("quantity"));
                trees.add(tree);
            }

        } catch (SQLException e) {
            View.showMessage("Error al realizar la búsqueda en trees");
            e.printStackTrace();
        }

        return trees;
    }

}
