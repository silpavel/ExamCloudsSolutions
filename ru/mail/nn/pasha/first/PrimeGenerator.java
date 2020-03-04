package ru.mail.nn.pasha.first;

import java.util.ArrayList;
import java.util.Random;

/** prime number generator 3, 5, 7, ...*/
public class PrimeGenerator{
    ArrayList<Integer> primes= new ArrayList<>();
    public PrimeGenerator(int edge){
        primes.add(2);
        for(int i=3; i <= edge; i+=2){
            boolean isPrime= true;
            for(Integer prime: primes){
                if(i%prime == 0){
                    isPrime=false;
                    break;
                }
            }
            if(isPrime) primes.add(i);
        }
    }

}
