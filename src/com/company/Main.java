package com.company;


import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 8 numbers in one line.");
        String line = in.nextLine();
        line =  line.trim();
        boolean check = false;
        try {
            int n = Integer.parseInt(line);
            System.out.println("---");
            if (line.length() != 8) {
                System.out.println("Line is not correct format. Length must be 8.");
            } else {
                check = true;
            }
        } catch (Exception e) {
            System.out.println("Line is not correct format. Symbols must be only numbers.");
        }

        if (check) {
            //Task_100.task_100(line);
            run_multithreaded_task_100(line);
        }
    }

    public static void run_multithreaded_task_100(String digits) {
        String[] op = new String[]{" + ", " - ", " * ", " / ", ""};
        String[] variants = Task_100.findAllVariants(digits, op);
        System.out.println(variants.length);
        int halfSize = variants.length;


        MultyThread firstThread = new MultyThread(variants, 0,halfSize - 1);
        MultyThread secondThread = new MultyThread(variants, halfSize, variants.length - 1);
        firstThread.start();
        secondThread.start();
    }
}
