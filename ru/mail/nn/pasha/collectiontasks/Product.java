package ru.mail.nn.pasha.collectiontasks;

public class Product implements Comparable<Product>{
    private String name;
    private int price;
    Product(String name, int price){
        this.name= name;
        this.price= price;
    }
    //
    public String getName(){ return name;}
    public int getPrice(){ return price;}
    //
    @Override
    public int hashCode(){
        int k=31;
        int resault=1;
        resault= k*resault + name.hashCode();
        resault= k*resault + price;
        return resault;
    }
    @Override
    public String toString(){
        return "{name: \'" + name + "\', price: " + price + "}";
    }
    @Override
    public int compareTo(Product right){
        if(right == null) throw new IllegalArgumentException();
        int resault= name.compareTo(right.name);
        if(resault != 0) return resault;
        return price - right.price;
    }
}
