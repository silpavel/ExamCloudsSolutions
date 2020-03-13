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
    /* 5. Создайте класс, содержащий пару строк с пробелами, пару массивов,
     * пару полей - базовых типов данных. Создайте объект этого класса,
     * сохраните объект в файл настроек в виде "поле=значение", прочитайте
     * из файла, восстановите объект.*/
    /* 6. Возьмите все три файла и добавьте их в zip архив,
     * пользуясь пакетом java.util.zip.* */
}

