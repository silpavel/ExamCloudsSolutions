package ru.mail.nn.pasha.collectiontasks;

public class Student{
    private String name;
    private int course;
    private double avgMark;
    // ---
    Student(String name, int course, double avgMark){
        this.name= name;
        this.course= course;
        this.avgMark= avgMark;
    }
    // ---
    public String getName(){ return name;}
    public int getCourse(){ return course;}
    public double getAvgMark(){ return avgMark;}
    //
    public void setName(String name){ this.name= name;}
    public void setCourse(int course){ this.course= course;}
    public void setAvgMark(double avgMark){ this.avgMark= avgMark;}
    // ---
    @Override
    public String toString(){
        return String.format("{'%s', y:%d, m:%.2f}", name, course, avgMark);
    }
    public void upGrade(){course++;}

}
