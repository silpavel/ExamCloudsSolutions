package ru.mail.nn.pasha.iostreamtasks;

import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

// использование классов FileReader, FileWriter, Zip
public class SymbolStream {
    /* 1. Возьмите любой текстовый файл, содержащий небольшой рассказ, выведите его
     *  на экран. */
    public void show(File text){
        try(
                FileReader in= new FileReader(text);
                BufferedReader bin= new BufferedReader(in);
        )
        {
            String str;
            while((str= bin.readLine()) != null){
                System.out.println(str);
            }
        }catch (IOException e){
            System.out.println("read error: "+e);
        }
    }
    /* 2. Подсчитайте в нем общее число символов, число различных символов,
     *  а также частоту появления каждого символа.*/
    public void count(File text){
        try(
                FileReader in= new FileReader(text);
                BufferedReader bin= new BufferedReader(in);
        )
        {

            int cnt=0;
            class CharFreq{
                char symbol;
                int absFreq;
                double freq;// absfreq/cnt*100%
                public CharFreq(char symbol, int absFreq, double freq) {
                    this.symbol = symbol;
                    this.absFreq = absFreq;
                    this.freq = freq;
                }
                @Override
                public String toString() {
                    return String.format("%5c \t %10d \t %.4f\n",
                            symbol, absFreq, freq);
                }
            }
            Comparator<CharFreq> compSymbol= (cf1, cf2)->cf1.symbol-cf2.symbol;
            Comparator<CharFreq> compAbsFreq= (cf1, cf2)->cf1.absFreq-cf2.absFreq;
            //next (int)0.002 - 0.001 equals zero is error, let use precession equal  0.000_1
            Comparator<CharFreq> compFreq= (cf1, cf2)->(int)(10_000*(cf1.freq - cf2.freq));
            int num;
            Map<Integer, CharFreq> mapFreq= new HashMap<>();
            // add key=new CharFreq to map
            // здесь считаются пробелы, запятые, цифры, непечатные символы, буквы разного кейса считаются разными
            while( ( num= bin.read() ) != -1){
                cnt++;
                if(mapFreq.containsKey(num)){// фильтр можно добавить где нибудь здесь
                    mapFreq.get(num).absFreq++;
                }else{
                    mapFreq.put(num, new CharFreq((char)num, 1, 0.0));
                }

            }
            // values of map to list and set value for freq from CharFreq
            List<CharFreq> listFreq= new ArrayList<>(mapFreq.values());
            for(CharFreq value: listFreq)
                value.freq= value.absFreq / (float)cnt *100;
            // show resault: symbol/absfreq/freq sorted by symbol or absfreq or freq
            System.out.println("count of symbol: "+ cnt);
            System.out.println("symbol\t\tcount\tfrequency(%)");
            listFreq.sort(compAbsFreq);// or compAbsFreq or compFreq
            for(CharFreq value: listFreq)
                System.out.print(value);


        }catch (IOException e){
            System.out.println("read error: "+e);
        }
    }
    /* 3. Зашифруйте текст с помощью кода гаммы (побитовое сложение символов
     * по модую 2), для получения гаммы используйте класс Random, начальную точку
     * выберете самостоятельно, сохраните результат в другой файл,
     * снова подсчитайте вероятность появления каждого символа.*/
    public void enygma(File text, File crypto, long key){

        Random rand= new Random(key);
        try(
            BufferedReader in= new BufferedReader(new FileReader(text));
            BufferedWriter out= new BufferedWriter(new FileWriter(crypto));
        ){
            int buf;
            while((buf= in.read()) != -1){
                out.write((char)buf ^ rand.nextInt(Byte.MAX_VALUE));
            }
        }catch(IOException e){
            System.out.println("crypt error: "+e);
        }
        /*for testing next add to main
        *
        *
        * SymbolStream syst= new SymbolStream();
        File text= new File("data/red.txt");
        //syst.count(text);
        File crypto= new File("data/crypto.txt");
        syst.enygma(text, crypto, 123);
        syst.count(crypto);
        //decrypto
        File decripto= new File("data/decrypto.txt");
        syst.enygma(crypto, decripto, 123);*/

    }
    /* 4. В исходном тексте подсчитайте число строк, число слов, число различных
     * слов, частоту появления каждого слова.*/
    public void count2(File text){
        try(
            BufferedReader in= new BufferedReader(new FileReader(text));
        ){
            String str;
            // читаем строки
            int countStr=0;//общее число строк
            int countWord=0;//общее число слов
            class WordFreq{
                String word;
                int absFreq;
                double freq;// absfreq/cnt*100%
                public WordFreq(String word, int absFreq, double freq) {
                    this.word = word;
                    this.absFreq = absFreq;
                    this.freq = freq;
                }
                @Override
                public String toString() {
                    return String.format("%10s \t %10d \t %.4f\n",
                            word, absFreq, freq);
                }
            }
            Comparator<WordFreq> compWord= (cf1, cf2)->cf1.word.compareTo(cf2.word);
            Comparator<WordFreq> compAbsFreqWord= (cf1, cf2)->cf1.absFreq-cf2.absFreq;
            //next (int)0.002 - 0.001 equals zero is error, let use precession equal  0.000_1
            Comparator<WordFreq> compFreqWord= (cf1, cf2)->(int)(10_000*(cf1.freq - cf2.freq));
            Map<String, WordFreq> mapFreqWord= new HashMap<>();
            while((str= in.readLine()) != null){
                countStr++;
                //переводим строку в нижний регистр, разбиваем на слова (только английский)
                String[] strs= str.toLowerCase().split("[^a-zA-Z0-9]+");
                //считаем слова
                for(String key: strs){
                    if(key.length() == 0) continue;
                    countWord++;
                    if(mapFreqWord.containsKey(key)){
                        mapFreqWord.get(key).absFreq++;
                    }else{
                        mapFreqWord.put(key, new WordFreq(key, 1, 0.0));
                    }
                }//end split and adding word
            }//end read of line
            List<WordFreq> listFreq= new ArrayList<>(mapFreqWord.values());
            for(WordFreq value: listFreq)
                value.freq= value.absFreq / (float)countWord *100;
            // show resault: word/absfreq/freq sorted by word or absfreq or freq
            System.out.println("count of lines: " + countStr);
            System.out.println("count of all word: " + countWord);
            System.out.println("symbol\t\tcount\tfrequency(%)");
            listFreq.sort(compWord);// or compAbsFreq or compFreq
            for(WordFreq value: listFreq)
                System.out.print(value);

        }catch(IOException e){
            System.out.println("read error: " + e);
        }
    }
    /* 5. Создайте класс, содержащий пару строк с пробелами, массивов,
     * пару полей - базовых типов данных. Создайте объект этого класса,
     * сохраните объект в файл настроек в виде "поле=значение", прочитайте
     * из файла, восстановите объект.*/
    public void saveObj(){
        Client client= new Client("Lucas", "+7 952 568-41-52", 1548, 38, 1, 3, 12, 45, 78, 63);
        System.out.println(client);
        // save
        try(BufferedWriter out= new BufferedWriter(new FileWriter("data/saveObj.txt"))){
            // я понимаю, что это надо выделить в отдельный метод, но тренировка в работе с файлами.
            out.write("name=" + client.getName() + '\n');
            out.write("age=" + client.getAge() + '\n');
            out.write("id=" + client.getId() + '\n');
            out.write("number=" + client.getPhoneNumber() + '\n');
            StringBuilder str= new StringBuilder("history=");
            for(int value: client.getBuy_prodId_history()){
                str.append(value+" ");
            }
            str.append('\n');
            out.write(str.toString().trim());
        }catch(IOException e){
            System.out.println("Save error: " + e);
        }// end write

        // read
        try(BufferedReader in= new BufferedReader(new FileReader("data/saveObj.txt"))){
            // предполагается, что мы не знаем порядок ключей в файле, надо проверять каждую строку
            // создадим пары ключ, значение
            Map<String, String> keyObj= new HashMap<>();
            String input;
            while((input= in.readLine()) != null){
                String[] keyValue= input.split("=");
                keyObj.put(keyValue[0], keyValue[1]);
            }
            // создадим новый объект, укажем значения по ключам и сразу выведем на экран
            String[] historyStr= keyObj.get("history").split(" ");
            int[] history=new int[historyStr.length];
            for (int i = 0; i <history.length ; i++) {
                history[i]= Integer.parseInt(historyStr[i]);
            }
            Client newClient= new Client(
                    keyObj.get("name"), keyObj.get("number"), Integer.parseInt(keyObj.get("id")),
                    Integer.parseInt(keyObj.get("age")), history);
            System.out.println(newClient);
        }catch (IOException e){
            System.out.println("read error: " + e);
        }// end read
    }

}
class Client{
    private String name;
    private String phoneNumber;
    private int id;
    private int age;
    private int[] buy_prodId_history;
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public int getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public int[] getBuy_prodId_history() {
        return buy_prodId_history;
    }
    public Client(String name, String phoneNumber, int id, int age, int...buy_prodId_history) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.age = age;
        this.buy_prodId_history = buy_prodId_history;
    }
    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", buy_prodId_history=" + Arrays.toString(buy_prodId_history) +
                '}';
    }

}

