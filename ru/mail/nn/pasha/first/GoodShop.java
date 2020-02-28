package ru.mail.nn.pasha.first;
// demonstration inner classes (short version)
public class GoodShop {
    private Basket[] buyers;
    private Category[] abstractGoods;
        // constructors
    GoodShop(){
        openShop();
        buyers= null;
        abstractGoods= null;
    }
        //methods
    public void addBasket(String owner){}
    public void addCategory(String name){}
    public void addGood(Category category, String name, double price){}
    private void openShop(){System.out.println("Shop open");};
        // classes
    private class Basket{
        String owner;
        Good[] goods;
    }
    private class Good{
        String name;
        double price;
    }
    private class Category{
        String name;
        Good[] goods;
    }
}


