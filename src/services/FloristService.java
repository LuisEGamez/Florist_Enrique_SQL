package services;

import entities.Florist;
import entities.Product;
import repositories.DecorRepository;
import repositories.FlowerRepository;
import repositories.TicketRepository;
import repositories.TreeRepository;
import vista.View;

import java.sql.ResultSet;
import java.util.List;

public class FloristService {

    private TreeRepository treeRepository;
    private FlowerRepository flowerRepository;
    private DecorRepository decorRepository;
    private TicketRepository ticketRepository;



    public FloristService(TreeRepository treeRepository, FlowerRepository flowerRepository, DecorRepository decorRepository, TicketRepository ticketRepository) {
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
    /*public double getTotalValue(){
        double result = 0;
        for(int i = 0; i<treeRepository.getTreeStockQuantity(); i++){

            result += treeRepository.getTreePrice(i);

        }
        for(int i = 0; i< flowerRepository.getFlowerStockQuantity(); i++){

            result += flowerRepository.getFlowerPrice(i);
        }
        for(int i = 0; i< decorRepository.getDecorStockQuantity(); i++){

            result += decorRepository.getDecorPrice(i);
        }
        return result;

    }*/

    public ResultSet getTrees(){

        return treeRepository.getTreesFromDatabase();
    }

    public ResultSet getFlowers(){

        return flowerRepository.getFlowersFromDatabase();
    }

    public ResultSet getDecorations(){

        return  decorRepository.getDecorFromDatabase();
    }


}
