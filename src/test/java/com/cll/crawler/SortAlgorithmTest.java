package com.cll.crawler;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author chenliangliang
 * @date: 2017/12/19
 */
public class SortAlgorithmTest {

    @Test
    public void test1() {


        int[] a = new int[100000];
        for (int i = 0; i < 100000; i++) {
            a[i] = i + 1;
        }


        ArrayUtils.shuffle(a);

        /*int[] copy = Arrays.copyOf(a, 100000);

        int[] copy1 = Arrays.copyOf(a, 100000);

        int[] copy2 = Arrays.copyOf(a, 100000);*/

        long start, end;
        /*start = System.currentTimeMillis();
        int[] res1 = SortAlgorithm.maoPao(a);
        end = System.currentTimeMillis();
        System.out.println("maoPao spend " + (end - start) + " ms time");*/
        //System.out.println(Arrays.toString(res1));
       /* start=System.currentTimeMillis();
        int [] res2=SortAlgorithm.maoPaoPlus(copy);
        end=System.currentTimeMillis();
        System.out.println("maoPaoPlus spend "+(end-start)+" ms time");

        start=System.currentTimeMillis();
        int [] res3=SortAlgorithm.countSort(copy1);
        end=System.currentTimeMillis();
        System.out.println("countSort spend "+(end-start)+" ms time");*/

        start = System.currentTimeMillis();
        int[] res4 = SortAlgorithm.shellSort(a);
        end = System.currentTimeMillis();
        System.out.println("shellSort spend " + (end - start) + " ms time");

        System.out.println(Arrays.toString(res4));

    }
}
