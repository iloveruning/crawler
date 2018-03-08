package com.cll.crawler;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;

/**
 * @author chenliangliang
 * @date: 2017/12/18
 */
public class MatrixTest {


    @Test
    public void test1(){

        double a [][] = new double[4][3];

        RealMatrix matrix=new Array2DRowRealMatrix(a);

        System.out.println();
    }
}
