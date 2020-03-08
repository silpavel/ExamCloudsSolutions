package ru.mail.nn.pasha.collectiontasks;

public class HeavyBox extends Box implements Comparable<HeavyBox>{
    private int weight;
    HeavyBox(Color color, int weight){
        super(color);
        this.weight= weight;
    }
    void setWeight(int weight){
        this.weight= weight;
    }
    int getWeight(){
        return weight;
    }
    @Override
    public String toString(){
        return "{weight= " + weight+ ", " + super.toString() + "}";
    }
    @Override
    public int compareTo(HeavyBox right){
        if(weight != right.weight) return weight - right.weight;
        return getColor().compareTo(right.getColor());
    }
}
