package services;

import entities.Product;
import repositories.DecorRepository;
import repositories.FlowerRepository;
import repositories.TicketRepository;
import repositories.TreeRepository;
import vista.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FloristService {

    private TreeRepository treeRepository;
    private FlowerRepository flowerRepository;
    private DecorRepository decorRepository;
    private TicketRepository ticketRepository;



    public FloristService(TreeRepository treeRepository, FlowerRepository flowerRepository,
                          DecorRepository decorRepository, TicketRepository ticketRepository){
        this.treeRepository = treeRepository;
        this.flowerRepository = flowerRepository;
        this.decorRepository = decorRepository;
        this.ticketRepository = ticketRepository;
    }

    public ResultSet quantityTreesStock(){

        return treeRepository.getTreeStockQuantity();

    }

    public ResultSet quantityFlowersStock(){

        return flowerRepository.getFlowerStockQuantity();

    }

    public ResultSet quantityDecorsStock(){

        return decorRepository.getDecorStockQuantity();

    }

    public double getTotalValue(){
        double result = 0;
        try {
            ResultSet rsTree = treeRepository.getTotalPrice();
            ResultSet rsFlower = flowerRepository.getTotalPrice();
            ResultSet rsDecor = decorRepository.getTotalPrice();

            if (rsTree.next()){
                result += rsTree.getDouble("Total");
            }

            if (rsFlower.next()){
                result += rsFlower.getDouble("Total");
            }

            if (rsDecor.next()){
                result += rsDecor.getDouble("Total");
            }

        }catch (SQLException e){
            View.showMessage("Error al calcular el precio total");
            e.printStackTrace();
        }

        return result;

    }

    public List<Product> getTrees(){

        return treeRepository.getTreesFromDatabase();
    }

    public List<Product> getFlowers(){

        return flowerRepository.getFlowersFromDatabase();
    }

    public List<Product> getDecorations(){

        return  decorRepository.getDecorFromDatabase();
    }


}
