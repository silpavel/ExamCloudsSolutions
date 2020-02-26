package ru.mail.nn.pasha.first;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/** simple matrix calculator*/
public class MatrixCalculator {
    private double[][] matrix;//double[row][column]
    private int column;
    private int row;
    /** Making matrix double[row][column] is filled zero
     * @param column dimension by x
     * @param row dimension by x
     * */
    public MatrixCalculator(int row, int column) {
        if(row<0 || column<0 ) return;
        this.column = column;
        this.row = row;
        matrix= new double[row][column];
    }
    /** Addition this matrix and matrix as parameter
     * @param matrixCalc second matrix type MatrixCalculator
     * @return new object MatrixCalculator or null
     * if matrixCalc is null or both matrix have different sizes*/
    public MatrixCalculator add(MatrixCalculator matrixCalc){
        if(matrixCalc ==null || column != matrixCalc.column
                || row != matrixCalc.row) return null;
        MatrixCalculator newMxCalc= new MatrixCalculator(row, column);
        for (int i = 0; i <row; i++) {
            for (int j = 0; j <column ; j++) {
                 newMxCalc.matrix[i][j]= matrix[i][j]+ matrixCalc.matrix[i][j];
            }
        }
        return newMxCalc;
    }
    /** Multiplication this matrix on double number.
     * @param number double number
     * @return new MatrixCalculator as this matrix is multiplied on double number.
     */
    public MatrixCalculator muxN(double number){
        MatrixCalculator resault= new MatrixCalculator(row, column);
        if(number == 0.0) return resault;
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <column ; j++) {
                resault.matrix[i][j]= this.matrix[i][j] * number;
            }
        }
        return resault;
    }
     /** Help function. It fill this matrix random double 0.0 to 9.9999.
     */
    public void fill(){
        Random rand= new Random((new Date()).getTime());
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <column ; j++) {
                matrix[i][j]= rand.nextDouble()*10;
            }
        }
    }
    /** Get value of column
     * @return value*/
    public int getColumn() {
        return column;
    }
    /** Get value of row
     * @return value*/
    public int getRow() {
        return row;
    }
    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        for(int i = 0; i <row ; i++) {
           sb.append(Arrays.toString(matrix[i])+"\n");
        }
        return sb.toString();
    }
    /* for testing. put next lines in main body
        MatrixCalculator mx1= new MatrixCalculator(2,2);
        MatrixCalculator mx2= new MatrixCalculator(2,2);
        MatrixCalculator mxResault;
        mx1.fill();
        mx2.fill();
        mxResault= mx1.add(mx2);
        System.out.println(mx1);
        System.out.println(mx2);
        System.out.println(mxResault);
        System.out.println(mxResault.muxN(2));

     */
}
