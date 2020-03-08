package ru.mail.nn.pasha.collectiontasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.function.*;
import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Comparator;

public class Tasks {
    //1
    /*a) Создать динамический массив, содержащий объекты класса HeavyBox.
    * b) Раcпечатать его содержимое используя for each.
    * c) Изменить вес первого ящика на 1.
    * d) Удалить последний ящик.
    * e) Получить массив содержащий ящики из коллекции тремя способами и
    *    вывести на консоль.
    * f) Удалить все ящики.*/
    public void task1(){
        //a
        ArrayList<HeavyBox> heavyBoxes= new ArrayList<>();
        heavyBoxes.add(new HeavyBox(Color.RED, 1));
        heavyBoxes.add(new HeavyBox(Color.ORANGE, 2));
        heavyBoxes.add(new HeavyBox(Color.RED, 4));
        heavyBoxes.add(new HeavyBox(Color.YELLOW, 5));
        //b
        for (HeavyBox box: heavyBoxes){
            System.out.println(box);
        }
        //c
        heavyBoxes.get(1).setWeight(1);
        //d
        heavyBoxes.remove(heavyBoxes.size()-1);
        //e
        // 1
        Object[] hb1=  heavyBoxes.toArray();
        // 2
        HeavyBox[] hb2= new HeavyBox[heavyBoxes.size()];
        heavyBoxes.toArray(hb2);
        // 3
        HeavyBox[] hb3= heavyBoxes.toArray(new HeavyBox[0]);
        System.out.println(Arrays.toString(hb2));
    }
    //3. Создать TreeSet содержащий HeavyBox. HeavyBox должен реализовать
    //  интерфейс Comparable. Распечатать содержимое с помощью for each.
    public void task3(){
        TreeSet<HeavyBox> heavyBoxes= new TreeSet<>();
        heavyBoxes.add(new HeavyBox(Color.ORANGE, 1));
        heavyBoxes.add(new HeavyBox(Color.RED, 1));
        heavyBoxes.add(new HeavyBox(Color.RED, 4));
        heavyBoxes.add(new HeavyBox(Color.YELLOW, 5));
        for (HeavyBox box: heavyBoxes){
            System.out.println(box);
        }
    }
    /* 4. Пользователь вводит набор чисел в виде одной строки
    * "1, 2, 3, 4, 4, 5". Избавиться от повторяющихся элементов в строке и
    * вывести результат на экран. */
    public void task4(){
        Scanner sc= new Scanner(System.in);
        String[] ins= sc.nextLine().split("[^\\d]+");
        Set<String> set= new HashSet();
        set.addAll(Arrays.asList(ins));
        System.out.println(set);
    }
    /* 5. Напишите методы union(Set<?>... set) и intersect(Set<?> ... set),
     * реализующих операции объединения и пересечения множеств. Протестируйте
     *  работу этих методов на предварительно заполненных множествах.
     */
    public void task5(){
        Set<Integer> i1= new HashSet<>();
        i1.add(1);
        i1.add(2);
        i1.add(3);
        i1.add(4);
        Set<Integer> i2= new HashSet<>();
        i2.add(2);
        i2.add(3);
        i2.add(4);
        i2.add(5);
        Set<Integer> i3= new HashSet<>();
        i3.add(3);
        i3.add(4);
        i3.add(5);
        i3.add(6);
        Set<Integer> resault= union(i1, i2, i3);
        i1.remove(2);
        System.out.println(resault);
    }
    public <T> Set<T> union(Set<T>...sets){
        Set<T> resault=new HashSet<>();
        for(Set<T> set: sets){
            resault.addAll(set);
        }
        return resault;
    }
    public <T> Set<T> intersect(Set<T>...sets){
        if(sets == null || sets[0] == null) return null;
        Set<T> resault= new HashSet(sets[0]);// is new Set but link to sets[0]
        for(int i=1; i< sets.length; i++){
            resault.retainAll(sets[i]);
        }
        return resault;
    }
    /* 7. Создать коллекцию, содержащую объекты HeavyBox. Написать метод,
     * который перебирает элементы коллекции и проверяет вес коробок.
     *  Если вес коробки больше определенной величины, коробка перемещается
     *  в другую коллекцию.
     */
    public void task7(){
        //list
        List<HeavyBox> boxes= new ArrayList<>();
        boxes.add(new HeavyBox(Color.RED, 1));
        boxes.add(new HeavyBox(Color.RED, 2));
        boxes.add(new HeavyBox(Color.YELLOW, 1));
        boxes.add(new HeavyBox(Color.YELLOW, 2));
        boxes.add(new HeavyBox(Color.ORANGE, 3));
        //selected list
        List<HeavyBox> selectedBoxes= new ArrayList<>();
        //condition (if)
        int minWeight=2; // can take any number
        Predicate<HeavyBox> condition= hb->hb.getWeight() >= minWeight;
        //copy list to selected list using condition
        checkWeight(selectedBoxes, boxes, condition);
        System.out.println(selectedBoxes);

    }
    public <T> void checkWeight(
            List<T> dist, List<T> src, Predicate<T> pred){
        for(T value: src){
            if(pred.test(value)) dist.add(value);
        }
    }
    /* 8. Создайте HashMap, содержащий пары значений  - имя игрушки и
    * объект игрушки (класс Product).
    * Перебрать и распечатать пары значений entrySet().
    * Перебрать и распечатать набор из имен продуктов  - keySet().
    * Перебрать и распечатать значения продуктов - values().
    */
    public void task8(){
        HashMap<String, Product> toys= new HashMap<>();
        toys.put("car",new Product("car",120));
        toys.put("cubes",new Product("cubes",20));
        toys.put("doll",new Product("doll",150));
        System.out.println(toys.entrySet());
        System.out.println(toys.keySet());
        System.out.println(toys.values());
    }
    /* 9. Создать класс Student, содержащий следующие характеристики – имя,
     * группа, курс, оценки по предметам. Создать коллекцию, содержащую
     * объекты класса Student. Написать метод, который удаляет студентов
     * со средним баллом <3. Если средний балл>=3, студент переводится
     * на следующий курс. (при другом условии другое действие)
     */
    public void task9(){
        List<Student> students= new ArrayList<>();
        students.add(new Student("Ivan", 1, 3.7));
        students.add(new Student("Victor", 1, 4.7));
        students.add(new Student("Yana", 1, 2.7));
        students.add(new Student("Olga", 1, 4.9));
        students.add(new Student("Tilda", 2, 2.00));
        System.out.println(students);
        // condition
        Predicate<Student> condition= student->student.getAvgMark() < 3;
        // action
        Consumer<Student> act= Student::upGrade;
        // upgrade
        upGrade(students, condition, act);
        System.out.println(students);

    }
    // common upgrade : any type collection, any payload, any condition, any act
    public <T> void upGrade(
            Collection<T> coll, Predicate<T> condition, Consumer<T> act){
        Iterator<T> it= coll.iterator();// for iteration of elems of any collection
        while(it.hasNext()){
            T value= it.next();
            if(condition.test(value))//any condition
                it.remove();
            else
                act.accept(value);// any action
        }
    }
    /* 11. Вместо массивов используйте коллекции. Создать метод, распечатывающий
     * товары каталога, отсортированные по имени, цене или рейтингу. Добавить
     *  возможность сортировать в обратном порядке.
     */
    public void task11(){
        List<Product> catalog= new LinkedList<>();
        catalog.add(new Product("fish",250));
        catalog.add(new Product("bread",25));
        catalog.add(new Product("fish",200));
        catalog.add(new Product("bread",30));
        catalog.add(new Product("tea",200));
        // next natural sorting: all field, sort ascending, need realize inside class
        //Collections.sort(catalog);
        // next can to select field (if getField exists) and direct of sorting
        // by name, down
        Comparator<Product> nameDown=
                (cat1, cat2)->-cat1.getName().compareTo(cat2.getName());
        //by price, up
        Comparator<Product> priceUp=
                (cat1, cat2)->cat1.getPrice() - cat2.getPrice();
        catalog.sort(priceUp);
        for(Product prod: catalog)
            System.out.println(prod);
    }

}
