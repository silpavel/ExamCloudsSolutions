// Example using composition and inheritance
package ru.mail.nn.pasha.first;
import static java.lang.System.*;
public class Car{
    private Engine engine;
    private Driver driver;
    public void cstart(){out.println("Go!");}
    public void cstop(){out.println("Stop!");}
    public void turnLeft(){out.println("Turn left!");}
    public void turnRight(){out.println("Turn right!");}
    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine.toString() +
                ", driver=" + driver.toString() +
                '}';
    }
    public Car(Engine engine, Driver driver) {
        this.engine = engine;
        this.driver = driver;
    }
}
class Engine{
    private int power;
    private String producer;
    @Override
    public String toString() {
        return "Engine{" +
                "power=" + power +
                ", producer='" + producer + '\'' +
                '}';
    }
    public Engine(int power, String producer) {
        this.power = power;
        this.producer = producer;
    }
}
class Driver{
    private String name;
    private int experience;
    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }
    public Driver(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }
}
class Lorry extends Car{
    private int payload_in_ton;
    public Lorry(Engine engine, Driver driver, int payload_in_ton) {
        super(engine, driver);
        this.payload_in_ton = payload_in_ton;
    }
    @Override
    public String toString() {
        return "Lorry{" +
                "payload_in_ton=" + payload_in_ton +'\''+
                super.toString()+
                '}';
    }
}
class SportCar extends Car{
    private int limit_speed;
    public SportCar(Engine engine, Driver driver, int limit_speed) {
        super(engine, driver);
        this.limit_speed = limit_speed;
    }
    @Override
    public String toString() {
        return "SportCar{" +
                "limit_speed=" + limit_speed +'\''+
                super.toString()+
                '}';
    }
}
/* for testing put next lines in main
        Engine e1= new Engine(120,"Little");
        Engine e2= new Engine(820,"Biggest");
        Engine e3= new Engine(1120,"Storm");
        Driver d1= new Driver("Genry",3);
        Driver d2= new Driver("Mikle",5);
        Driver d3= new Driver("Lisa",1);
        Car car= new Car(e1, d1);
        Car lorry= new Lorry(e2, d2, 15);
        Car sportCar= new SportCar(e3, d3, 360);
        System.out.println(car.toString());
        System.out.println(lorry.toString());
        System.out.println(sportCar.toString());

 */
