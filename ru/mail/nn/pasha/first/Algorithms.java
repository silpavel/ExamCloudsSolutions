/**
 *  @author Paul.nn.mail.ru
 *  First part of solutions
 */
package ru.mail.nn.pasha.first;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;

/** Some sort algorithms */
public class Algorithms {
    /**
     * Sort array int[] by bubble sort (compare pair).
     * @param array int[] unsorted array.
     */
    public static void bubbleSort(int[] array){
        if(array==null || array.length<=1) return;
        int size=array.length;
        for(int i=size-1; i>=1; i--){
            for(int j=0; j<i; j++){
                if(array[j+1]<array[j]){
                    int buf= array[j+1];
                    array[j+1]= array[j];
                    array[j]= buf;
                }
            }
        }
    }
    /**
     * Testing of sort function using sort function as parameter.
     * Make new array, fill and sort it using parameter-function
     * @param size size of array int[]
     * @param calc function of sort
     */
    public static void testSort(int size, Consumer<int[]> calc){
        Random rand= new Random((new Date()).getTime());
        int[] arr= new int[size];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]= rand.nextInt(20);
        }
        System.out.println(Arrays.toString(arr));
        calc.accept(arr);//execute bubbleSort or other sort function
        System.out.println(Arrays.toString(arr));
    }
    /**
     * Sort array int[] by selection sort (find max)
     * @param array int[] unsorted array.
     */
    public static void selectSort(int[] array){
        if(array==null || array.length<=1) return;
        int size= array.length;
        for(int i=size-1; i>=1; i--){
            int indexOfMax=i;
            for(int j=0;j<=i;j++){
                //find max elem, save it index
                if(array[j] > array[indexOfMax]) indexOfMax=j;
            }
            if(indexOfMax != i){
                int buf= array[i];
                array[i]= array[indexOfMax];
                array[indexOfMax]= buf;
            }
        }
    }
}
