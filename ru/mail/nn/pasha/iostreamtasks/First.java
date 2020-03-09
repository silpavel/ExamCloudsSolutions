package ru.mail.nn.pasha.iostreamtasks;

import java.io.*;
import java.util.Scanner;

public class First{
    /* Пользователь вводит строку. Записать её в файл, как массив байт, затем
     * вывести из файла в консоль. Чтение и запись и реализовать отдельными методами.
     * */
    /*в main:
    * First first= new First();
      System.out.println(first.writeUser());
      System.out.println(first.readUser());
      * */
    private String  src= "src/1.txt";
    private File file= new File(src);
    public String writeUser(){
        System.out.print("> ");
        Scanner sc= new Scanner(System.in);
        String input= sc.nextLine();
        sc.close();
        try(FileOutputStream out= new FileOutputStream(file)){
            out.write(input.getBytes());
        }catch(IOException e){
            return "Write error!";
        }
        return "Write complete";
    }
    public String readUser(){
        try(FileInputStream in= new FileInputStream(file)){
            System.out.println( new String( in.readAllBytes() ) );
        }catch (IOException e){
            return "Read error!";
        }
        return "Read complete";
    }
    /*Пользователь вводит в консоль массив double. Создать два метода:
    * первый записывает массив в файл как массив байт,
    * второй читает этот массив из файла и выводит на экран*/
    public String writeDouble(){
        Scanner scan= new Scanner(System.in);
        System.out.println("> ");
        String[] strs= scan.nextLine().split("[^\\d\\.]+");
        double[] dbl= new double[strs.length];
        // преполагается, что пользователь вводит массив без ошибок, но все же...
        try {
            for (int i = 0; i < dbl.length; i++) {
                System.out.println(dbl[i] = Double.parseDouble(strs[i]));
            }
        }catch (NumberFormatException e){return e.toString();}
        scan.close();
        // увы, простого метода превращения double в массив байт нет
        // Но он неявно присутвует в классе DataOutputStream
        try(
            FileOutputStream out= new FileOutputStream(file);
            DataOutputStream bout= new DataOutputStream(out)
        ){
            for(double value: dbl)
                bout.writeDouble(value);//то что нам нужно
        }catch (IOException e){
            return "write error";
        }
        return "write ok";
    }
    public String readDouble(){
        // здесь аналогично пользуемся классом DataInputStream
        try(
                FileInputStream out= new FileInputStream(file);
                DataInputStream bout= new DataInputStream(out)
        ){
            // предполагается, что размер массива в файле неизвестен
            // предполагается, что файл состоит только из массива double
            double d;
            while( bout.available() > 0 ){ // как еще определить конец файла?
                System.out.println(bout.readDouble());
            }
        }catch (IOException e){
            return "read error";
        }
        return "read ok";
    }
    //public String readDouble(){}


}
