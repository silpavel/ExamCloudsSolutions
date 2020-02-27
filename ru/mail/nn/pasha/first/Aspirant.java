package ru.mail.nn.pasha.first;
public class Aspirant extends Student{
    private String treatise;//some work
    public String getTreatise() {
        return treatise;
    }
    public Aspirant(String name, String group,
                    double averageMark, String treatise) {
        super(name, group, averageMark);
        this.treatise = treatise;
    }
    @Override
    public double getScholarship() {
        if(averageMark>=4 && averageMark<=5) return 700;
        else if(averageMark>3 && averageMark<4) return 500;
        else return 100;
    }
    @Override
    public String toString() {
        return "Aspirant{" +
                "treatise='" + treatise + '\'' +
                super.toString() +
                '}';
    }
}
class Student{
    private String name;
    private String group;
    protected double averageMark;
    public Student(String name, String group, double averageMark) {
        this.name = name;
        this.group = group;
        this.averageMark = averageMark;
    }
    public double getScholarship(){
        if(averageMark>=4 && averageMark<=5) return 500;
        else if(averageMark>3 && averageMark<4) return 300;
        else return 0;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", averageMark=" + averageMark +
                '}';
    }
}
