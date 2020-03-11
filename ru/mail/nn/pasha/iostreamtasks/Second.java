package ru.mail.nn.pasha.iostreamtasks;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Second {
    /* Запишите в файл объект, имеющий нестатические поля типов:
     * пару разных базовых типов, один массив базовых типов и строку String.
     * Размер массива и строки должны храниться в файле.
     * использовать только байтовый поток, сериализацию и т.п. использовать нельзя.
     */
    File file= new File("data/complex.txt");
    public void write(Person person){
        try(
                FileOutputStream fout= new FileOutputStream(file, true);
                BufferedOutputStream bout= new BufferedOutputStream(fout);
                DataOutputStream dout= new DataOutputStream(bout);
        ){
            // write name
            byte[] str= person.getName().getBytes();
            dout.writeInt(str.length);//size of name, <int>
            dout.write(str);// <byte[]>
            // age
            dout.write(person.getAge());// <byte>
            // count
            dout.writeLong(person.getCount());
            // upDown
            int[] tmpUpDown= person.getUpDownCountHistory();
            dout.writeInt(tmpUpDown.length);// length <int>
            for(int value: tmpUpDown)
                dout.writeInt(value);
            System.out.println("!Write of object OK!");
        }catch(IOException e){
            System.out.println(e);
            System.out.println("!Write of object error!");
        }
    }
    /* прочитайте объект из файла, выведите на экран объект до записи и
     * прочитанный из файла объект, использовать toString
     */
    public Person read(){
        String name="";
        byte age;
        long count;
        int[] upDownCountHistory;
        try(
                FileInputStream fin= new FileInputStream(file);
                BufferedInputStream bin= new BufferedInputStream(fin);
                DataInputStream din= new DataInputStream(bin);
        ){
            // read name
            byte[] str= new byte[din.readInt()];// <int>
            din.read(str);// <byte[]>
            name= new String(str);
            // age
            age= din.readByte();// <byte>
            // count
            count= din.readLong();
            // upDown
            upDownCountHistory= new int[din.readInt()];
            for(int i=0; i < upDownCountHistory.length; i++)
                upDownCountHistory[i]= din.readInt();
            System.out.println("!Read of object OK!");
            return new Person(name, age, count, upDownCountHistory);
        }catch(IOException e){
            System.out.println(e);
            System.out.println("!Read of object error!");
        }
        return null;

    }
    /* put in main for testing
        Second second= new Second();
        int[] upDown= new int[]{10, -50, 20, -100};
        byte age=25;
        Person person= new Person("Leo",age, 1000L, upDown);
        second.write(person);
        Person newPerson= second.read();
        System.out.println(person);
        System.out.println(newPerson);
    * */
    /////////////////////////
    /* дозапишите в файл таким образом еще два таких объекта, но с другими
     * значениями полей, прочитайте в массив, выведите его на экран
     */
    public List<Person> readAll(){
        String name="";
        byte age;
        long count;
        int[] upDownCountHistory;
        try(
                FileInputStream fin= new FileInputStream(file);
                BufferedInputStream bin= new BufferedInputStream(fin);
                DataInputStream din= new DataInputStream(bin);
        ){
            List<Person> list= new ArrayList<>();
            // read name
            while(din.available()>0) {
                byte[] str = new byte[din.readInt()];// <int>
                din.read(str);// <byte[]>
                name = new String(str);
                // age
                age = din.readByte();// <byte>
                // count
                count = din.readLong();
                // upDown
                upDownCountHistory = new int[din.readInt()];
                for (int i = 0; i < upDownCountHistory.length; i++)
                    upDownCountHistory[i] = din.readInt();
                list.add(new Person(name, age, count, upDownCountHistory));
            }
            System.out.println("!Read of object OK!");
            return list;

        }catch(IOException e){
            System.out.println(e);
            System.out.println("!Read of object error!");
        }
        return null;

    }
    public void test1(){
        Second second= new Second();
        second.write(new Person("Leo",(byte)15, 1000L, new int[]{-20, -50 , -30 , -50, -50}));
        second.write(new Person("Mike",(byte)25, 1500L, new int[]{50, 50 ,50 ,50}));
        second.write(new Person("Liana",(byte)35, 2000L, new int[]{10, -10}));
        List<Person> persons= second.readAll();
        for(Person person: persons)
            System.out.println(person);


    }

    /* Сделайте тоже самое но с помощью сериализации*/
    // сделаем class Person implements Serializable
    /* Вместе с О каждый раз записывается заголовок потока,
     * наличие более одного заголовка вызовет ошибку при чтении
     *  второго и последующих объектов. Решение:
     * переопределить метод writeStreamHeader класса ObjectOutputStream,
     * путем создания объета анонимного класса */
    public void writeObj(Person person){
        // write
        File file= new File("data/serials.txt");
        try(
                FileOutputStream fout= new FileOutputStream(file, true);
                ObjectOutputStream out=
                        file.length() == 0
                        ? new ObjectOutputStream(fout)// файл пустой, пишем первый объект + заголовок
                        : new ObjectOutputStream(fout){// файл не пустой, пишем только объект
                            @Override
                            protected void writeStreamHeader() throws IOException {
                                //чтобы заголовок повторно не записывался
                                reset();
                            }
                        }
        ){
            out.writeObject(person);
            out.flush();
            System.out.println("!Write of object(s) OK!");
        }catch (IOException e){
            System.out.println("!Write of object(s) error!");
        };
    }
    public List<Person> readAllObj(){
        List<Person> inList= new ArrayList<>();
        // read
        try( ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/serials.txt"))){
            // при использовании ObjectInputStream while(in.available() > 0) не работает
            boolean b= true;
            Person person;
            try{
                while((person= (Person) in.readObject()) != null)
                    inList.add(person);
            }catch(EOFException e){}
            System.out.println("!Read of object(s) OK!");
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e);
            System.out.println("!Read of object(s) error!");
        }
        return inList;
    }
    public void test2(){
        writeObj(new Person("Leo",(byte)15, 1000L, new int[]{-20, -50 , -30 , -50, -50}));
        writeObj(new Person("Mike",(byte)25, 1500L, new int[]{50, 50 ,50 ,50}));
        writeObj(new Person("Liana",(byte)35, 2000L, new int[]{10, -10}));
        List<Person> inList= readAllObj();
        for(Person person: inList)
            System.out.println(person);


    }

}

