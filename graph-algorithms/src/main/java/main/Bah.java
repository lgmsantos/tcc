package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Bah {

    static final int SIZE = 10000000;
    static final int ITERATION = 1000000;
    static final int SEED = 1;

    public static void main(String[] args) {
        new ArrayTester().runTest();
        new HashTester().runTest();
        new ArrayListTester().runTest();
        //new LinkedListTester().runTest();
    }

    static abstract class Tester {
        public abstract void test();

        Random r = new Random(SEED);
        String name;

        public void runTest() {
            Date start = new Date();
            test();
            Date end = new Date();
            System.out.printf("%s: %ss\n", name, (end.getTime() - start.getTime()) / 1000.0);
        }
    }

    static class ArrayTester extends Tester {
        {
            name = "Array";
        }

        @Override
        public void test() {
            int[] x = new int[SIZE];
            for (int i = 0; i < ITERATION; i++)
                x[r.nextInt(SIZE)]++;
        }
    }

    static class HashTester extends Tester {
        {
            name = "Hash";
        }

        Map<Integer, Integer> x = new HashMap<>();
        {
            for (int i = 0; i < SIZE; i++)
                x.put(i, 0);
        }

        @Override
        public void test() {
            for (int i = 0; i < ITERATION; i++) {
                int k = r.nextInt(SIZE);
                x.put(k, x.get(k) + 1);
            }
        }
    }

    static abstract class ListTester extends Tester {

        List<Integer> x = newList();
        {
            for (int i = 0; i < SIZE; i++)
                x.add(0);
        }

        @Override
        public void test() {
            for (int i = 0; i < ITERATION; i++) {
                int k = r.nextInt(SIZE);
                x.set(k, x.get(k) + 1);
            }
        }

        abstract List<Integer> newList();

    }

    static class ArrayListTester extends ListTester {
        {
            name = "ArrayList";
        }

        @Override
        List<Integer> newList() {
            return new ArrayList<Integer>();
        }

    }

    static class LinkedListTester extends ListTester {
        {
            name = "LinkedList";
        }
        
        @Override
        List<Integer> newList() {
            return new LinkedList<Integer>();
        }
    }

}
