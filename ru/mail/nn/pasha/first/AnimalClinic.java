package ru.mail.nn.pasha.first;
// short testing polymorphism
public class AnimalClinic {
    public void inspectPet(Pet pet){
        System.out.println(pet.toString());
    }
}
class Pet{
    private String name;
    private int age;
    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
class Cat extends Pet{
    private int weight;
    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +'\''+
                super.toString()+
                '}';
    }
    public Cat(String name, int age, int weight) {
        super(name, age);
        this.weight = weight;
    }
}
class Dog extends Pet{
    private String place;
    @Override
    public String toString() {
        return "Dog{" +
                "place='" + place + '\'' +
                super.toString()+
                '}';
    }
    public Dog(String name, int age, String place) {
        super(name, age);
        this.place = place;
    }
}
/* for testing
        Pet[] pets= new Pet[3];
        pets[0]= new Dog("Alpha", 2, "home");
        pets[1]= new Cat("Black",3, 4);
        pets[2]= new Cat("White",1, 2);
        for (Pet pet: pets) {
            System.out.println(pet.toString());
        }

 */
