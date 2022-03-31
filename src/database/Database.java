package database;

import entities.*;
import vista.View;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Product> trees;
    private List<Product> flowers;
    private List<Product> decorations;
    private List<Ticket> tickets;

    public Database() {

        trees = new ArrayList<>();
        flowers = new ArrayList<>();
        decorations = new ArrayList<>();
        tickets = new ArrayList<>();

    }

    public List<Product> getTrees() {
        return trees;
    }
    public List<Product> getFlowers(){ return flowers; }
    public List<Product> getDecorations(){ return decorations; }
    public List<Ticket> getTickets() {
        return tickets;
    }


    public void setTrees(List<Product> trees) {
        this.trees = trees;
    }
    public void setFlowers(List<Product> flowers) { this.flowers = flowers; }
    public void setDecorations(List<Product> decorations) { this.decorations = decorations; }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    

}
