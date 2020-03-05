package ru.mail.nn.pasha.second;

import java.util.Arrays;


// Источник: https://www.examclouds.com/ru/java/java-core-russian/lesson12-tasks
public class StringTasks {
    /* 1.
    * a) Напишите метод, который принимает в качестве параметра любую строку,
    * b) Распечатать последний символ строки.
    * c) Проверить, заканчивается ли ваша строка подстрокой “!!!”.
    * d) Проверить, начинается ли ваша строка подстрокой “I like”.
    * e) Проверить, содержит ли ваша строка подстроку “Java”.
    * */
    String str= "I like Java!!!";
    public void sol_1_a(String str){this.str= str;}
    public void sol_1_b(){
        System.out.println(str.charAt(str.length()-1));
    }
    public void sol_1_c(){
        System.out.println(str.endsWith("!!!"));
    }
    public void sol_1_d(){
        System.out.println(str.startsWith("I like"));
    }
    public void sol_1_e(){
        System.out.println(str.contains("Java"));
    }
    /*
    * f) Найти позицию подстроки “Java” в строке “I like Java!!!”.
    * g) Заменить все символы “а” на “о”.
    * h) Преобразуйте строку к верхнему регистру.
    * i) Преобразуйте строку к нижнему регистру.
    * j) Вырезать подстроку "Java".
    * */
    public void sol_1_f(){
        System.out.println(str.indexOf("Java"));
    }
    public void sol_1_ghij(){
        System.out.println(str.replace('a','o'));
        System.out.println(str.toUpperCase());
        System.out.println(str.toLowerCase());
        System.out.println(str.replace("Java", ""));
    }
    /* 2.
    * а) Дано два числа, например 3 и 56, необходимо составить следующие строки:
    *   3 + 56 = 59; 3 – 56 = -53; 3 * 56 = 168.
    *   Использовать StringBuilder.append()
    * b) Замените символ “=” на слово “равно”.
    *   Используйте методы StringBuilder.insert(), StringBuilder.deleteCharAt().
    * c) Замените символ “=” на слово “равно”.
    *   Используйте методы StringBuilder.replace().
    */
    public void sol_2_a(){
        int a=3;
        int b=56;
        StringBuilder resault= new StringBuilder("");
        // сделаем 3 + 56 = 59, остальное аналогично
        resault.append(a);
        resault.append(" + ");// - *
        resault.append(b);
        resault.append(" = ");
        resault.append(a+b);// - *
        System.out.println(resault);
        // есть способ проще
        String res= String.format("%d + %d = %d",a, b, a+b);
        System.out.printf(res);
    }
    public void sol_2_b(){
        String str= "3 * 56 = 168";
        StringBuilder sb= new StringBuilder(str);
        int i= sb.indexOf("=");
        sb.delete(i,i+1);
        sb.insert(i,"равно");
        System.out.println(sb);
        // есть способ проще. Для sb.replace(i,j,строка) всё ещё нужны индексы.
        System.out.println(str.replace("=", "равно"));
    }
    /* 3.
    * Напишите метод, заменяющий в строке каждое второе вхождение
    * «OOP» (не учитываем регистр символов) на «it»*/
    public void sol_3(){
        String str="OoP sdd OOP sadf ooP sadfdf OOP dfds oop sdg";
        StringBuilder sb= new StringBuilder(str);
        boolean isEven=false;
        for(int i=0;i<sb.length()-3;i++){
            if(sb.substring(i,i+3).toLowerCase().equals("oop")){
                if(isEven){
                    sb.replace(i,i+3,"it");
                    isEven= !isEven;
                }else
                    isEven= !isEven;
            }
        }
        System.out.println(sb);
    }
    /* 4.
    * Даны строки разной длины (длина - четное число), необходимо вернуть ее
    * два средних знака: "string" → "ri", "code" → "od", "Practice"→"ct".
    */
    public void sol_4(String str){
        int i= str.length()/2;
        System.out.println(str.substring(i-1, i+1));
    }
    /* 5.
    * Создать строку, используя форматирование:
    * Студент [Фамилия] получил [оценка] по [предмету]
    * Выделить под фамилию 15 символов, под оценку 3 символа, предмет – 10.
    * */
    public String sol_5(String name, int mark, String subject){
        return String.format("Студент %15s получил %3d по %10s",
                name, mark, subject);
    }
    /* 6.
    * Создать регулярное выражение для замены строк cab, ccab, cccab
    */
    public void sol_6(){
        String str="cab dcat ccab sdfs cab sdf cccba sdf cab ccab";
        String regex= "[c]++ab";
        System.out.println(str.replaceAll(regex, "+"));
    }
    /* 9.
    * проверить, содержит ли строка адрес вида www.google.com или ru или org
    * */
    public void sol_9(String str){
        String regex= "(.*)www\\.[\\w]+\\.(com|org|ru)(.*)";
        System.out.println(str.matches(regex));
        // "sfdfdsfwww.google.comsdfgdsfgdf"
    }
    /* 10.
    * Дана строка “Versions: Java  5, Java 6, Java   7, Java 8, Java 12.”
    *   Найти все подстроки "Java X" и распечатать их.
    */
    public void sol_10(){
        String str= "Versions: Java  5, Java 6, Java   7, Java 8, Java 12.";
        String regex= "(Versions: |, |\\.)";
        for(String s: str.split(regex))
            System.out.println(s);
    }
    /* 11.
    * Найти слово, в котором число различных символов минимально.
    * Слово может содержать буквы и цифры. Если таких слов несколько,
    *  найти первое из них. Например в строке "fffff ab f 1234 jkjk"
    *  найденное слово должно быть "fffff".
    * */
    public void sol_11(){
        String str= "fffff ab f 1234 jkjk";
        // получим слова
        String[] words= str.split("[\\s]+");
        // получим из них новые слова, где буквы не повторяются
        StringBuilder[] setWords= new StringBuilder[words.length];
        for(int i=0; i<setWords.length;i++){
            setWords[i]= new StringBuilder("");
            for(char c: words[i].toCharArray())
                if(!setWords[i].toString().contains(c+""))
                    setWords[i].append(c);
        }
        // ищем слово с минимальной длинной, запоминаем индекс
        int index=0;
        for(int i=0; i< setWords.length; i++)
            if(setWords[i].length() < setWords[index].length())
                index= i;
        System.out.println(words[index]);
        // можно было вместо StringBuilder использовать HashSet
        // Set[] w= new HashSet[length]
        // w[i]= new HashSet(words[i].toCharArray())
        // w[i].size()
    }
    /* 12.
    * Предложение состоит из нескольких слов, разделенных пробелами.
    * Например: "One two three раз два три one1 two2 123".
    * Найти количество слов, содержащих только символы латинского алфавита.
    */
    public void sol_12(){
        String str="One two three раз два три one1 two2 123";
        String[] strs= str.split("[\\s]+");
        for(String find: strs)
            if(find.matches("[a-zA-Z]+"))
                System.out.println(find);
    }
    /* 13.
    * Предложение состоит из нескольких слов, например: "Если есть хвосты по
    * дз, начните с 1 не сданного задания. 123 324 111 4554". Среди слов,
    * состоящих только из цифр, найти слово-палиндром.
    */
    public void sol_13(){
        String str= "Если есть хвосты по дз, начните с 1 не сданного" +
                " задания. 123 324 111 4554";
        // получим строку, состоящую только из цифр и пробелов
        str= str.replaceAll("[^\\d]+"," ");
        str= str.trim();
        // разобъем на подстроки
        String[] nums= str.split("[\\s]");
        // сравним числа с их развёрнутой версией, и распечатаем их
        for(String num: nums)
            if(num.equals(
                    new StringBuilder(num).reverse().toString()
            ))
                System.out.println(num);

    }
}
