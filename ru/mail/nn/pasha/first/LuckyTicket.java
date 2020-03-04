package ru.mail.nn.pasha.first;
/**Lucky tickets (demonstration thread)
 * Show frequency of occurrence lucky tickets (diff==0)
 * and difference of sums (000 and 999 max dif==27).
 * For calculate using case two addition thread and usually case.
 * */
public class LuckyTicket {
    private int[] diff;
    // usually
    public void show(){
        diff= new int[28];
        int[] digit= new int[6];
        for(int number=000_000; number<=999_999; number++){
            digit[0]= number / 100_000;
            digit[1]= number / 10_000 % 10;
            digit[2]= number / 1_000 % 10;
            //
            digit[3]= number / 100 % 10;
            digit[4]= number / 10 % 10;
            digit[5]= number % 10;
            //
            int def= (digit[0] + digit[1] + digit[2])-
                    (digit[3] + digit[4] + digit[5]);
            if(def<0) def*=-1;
            diff[def]++;
        }
        for(int def=0; def<=27; def++){
            System.out.printf("def= %2d, freq= %7.4f\n", def, diff[def]/10_000.0);
        }
    }
    // using two thread
    public void showByThread(){
        diff= new int[28];
        Thread t1= new Show(000_000, 499_999);
        Thread t2= new Show(500_000, 999_999);
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){}
        for(int def=0; def<=27; def++){
            System.out.printf("def= %2d, freq= %7.4f\n", def, diff[def]/10_000.0);
        }
    }
    // ---
    private class Show extends Thread{
        int first, last;
        Show(int first, int last){
            this.first= first;
            this.last= last;
        }
        @Override
        public void run() {
            int[] digit= new int[6];
            for(int number= first; number<= last; number++){
                digit[0]= number / 100_000;
                digit[1]= number / 10_000 % 10;
                digit[2]= number / 1_000 % 10;
                //
                digit[3]= number / 100 % 10;
                digit[4]= number / 10 % 10;
                digit[5]= number % 10;
                //
                int def= (digit[0] + digit[1] + digit[2])-
                        (digit[3] + digit[4] + digit[5]);
                if(def<0) def*=-1;
                synchronized (diff){
                    diff[def]++;
                }
            }
        }
    }
}
