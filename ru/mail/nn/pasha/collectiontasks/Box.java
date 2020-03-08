package ru.mail.nn.pasha.collectiontasks;

public class Box{
    private Color color;
    Box(Color color){this.color= color;}
    void setColor(Color color){this.color= color;}
    Color getColor(){return color;}
    @Override
    public String toString(){
        return "{color= "+color+"}";
    }

}
