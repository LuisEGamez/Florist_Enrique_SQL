package services;

import entities.Product;
import repositories.DecorRepository;
import repositories.FlowerRepository;
import repositories.TicketRepository;
import repositories.TreeRepository;
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

    public List<Product> getTrees(){

        return treeRepository.getTreesFromDatabase();
    }

    public List<Product> getFlowers(){

        return flowerRepository.getFlowersFromDatabase();
    }

    public List<Product> getDecorations(){

        return  decorRepository.getDecorFromDatabase();
    }

    public double getTotalValue(){
        return getTotalValueTrees()+getTotalValueFlowers()+getTotalValueDecor();
    }

    public double getTotalValueTrees(){

        double result = 0;

        for (Product product: getTrees()){

            result += product.getPrice()* product.getQuantity();

        }
        return result;
    }

    public double getTotalValueFlowers(){

        double result = 0;

        for (Product product: getFlowers()){

            result += product.getPrice()* product.getQuantity();

        }
        return result;
    }

    public double getTotalValueDecor(){

        double result = 0;

        for (Product product: getDecorations()){

            result += product.getPrice()* product.getQuantity();

        }
        return result;
    }


}
