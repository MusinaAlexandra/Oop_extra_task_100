package com.company;

import java.util.ArrayList;
import java.util.List;

public class Task_100 {
    public static void task_100(String line) {
        String[] op = new String[]{" + ", " - ", " * ", " / ", ""};
        String[] result = findResults(line, 100L, op);
        printResults(result);

    }

    public static String[] findResults(String digits, Long expectedResult, String[] op) {
        String[] variants = findAllVariants(digits, op);

        //String[] variants = new String[1];
        //variants[0] = "9 / 8 / 7 / 6 + 54 + 3 * 2";
        /*variants[0] = "98 + 7 + 6 - 5 - 4 * 3 / 2";
        variants[1] = "9 + 8 + 7 + 6 + 5 + 4 + 3 + 2";
        variants[2] = "9 - 8 - 7 - 6 - 5 - 4 - 3 - 2";
        variants[3] = "9 * 8 * 7 + 6 - 5 / 4 / 3 + 2";
        variants[4] = "9 * 8 * 7 * 6 * 5 * 4 * 3 * 2";
        variants[5] = "98 + 7 * 6 + 5 - 43 - 2";
        variants[6] = "98 + 7 * 6 - 5 / 4 * 32";
        variants[7] = "9 + 8 - 7 / 6 * 5 + 4 - 3 * 2";
        variants[8] = "9 + 8 - 7 + 6 - 5 + 4 - 3 + 2";
        variants[9] = "98765432";
        */
        List<String> result = new ArrayList<>();
        for (String var: variants) {
            if (checkAnswer(var, expectedResult)) {
                result.add(var);
            }
        }

        return result.toArray(new String[result.size()]);
    }

    public static boolean checkAnswer(String var, Long expectedResult) {
        Long res;
        String line = var;
        boolean checkMinus = false;
        while (line.indexOf('*') != -1 || line.indexOf('/') != -1) {
            int size = line.length();

            for (int i = 0; i < size; i++) {
                if (line.charAt(i) == '*') {
                    //System.out.println(line);

                    double prev = findPrev(line, i);
                    double next = findNext(line, i);

                    String pp = " " + (int)prev + " ";
                    int inx1 = line.substring(0,i).lastIndexOf(pp) - 1;
                    int inx2 = ((int)next + "").length() + i +2;

                    int mdlRes = (int)(prev * next);
                    String start = inx1 == -2 ? "" : line.substring(0, inx1+2);
                    String end = inx2 > size -1 ? "" : line.substring(inx2);
                    line =  start + mdlRes + end;
                    //System.out.println(line);
                    break;
                }
                if (line.charAt(i) == '/') {
                    double prev = findPrev(line, i);
                    double next = findNext(line, i);
                    String pp = " " + (int)prev + " ";
                    int inx1 = line.substring(0,i).lastIndexOf(pp) - 1;
                    int inx2 = ((int)next + "").length() + i +2;
                    int mdlRes = (int)(prev / next);
                    String start = inx1 == -2 ? "" : line.substring(0, inx1+2);
                    String end = inx2 > size -1 ? "" : line.substring(inx2);
                    line =  start + mdlRes + end;
                    //System.out.println(line);
                    break;
                }
            }
        }

        while (line.indexOf('+') != -1 || line.indexOf('-') != -1) {
            int size = line.length();
            for (int i = 0; i < size; i++) {
                if (line.charAt(i) == '+') {
                    double prev = findPrev(line, i);
                    double next = findNext(line, i);

                    String pp = " " + (int)prev + " ";
                    int inx1 = line.substring(0,i).lastIndexOf(pp) - 1;
                    int inx2 = ((int)next + "").length() + i +2;
                    int mdlRes = 0;
                    if (checkMinus) {
                        mdlRes = (int) (-prev + next);
                        checkMinus = false;
                    } else {
                        mdlRes = (int) (prev + next);
                    }
                    String start = inx1 == -2 ? "" : line.substring(0, inx1+2);
                    String end = inx2 > size -1 ? "" : line.substring(inx2);
                    line =  start + mdlRes + end;
                    //System.out.println(line);
                    break;
                }
                if (line.charAt(i) == '-') {
                    double prev = findPrev(line, i);
                    double next = findNext(line, i);
                    if (prev != -1) {
                        String pp = " " + (int) prev + " ";
                        int inx1 = line.substring(0, i).lastIndexOf(pp) - 1;
                        int inx2 = ((int)next + "").length() + i +2;
                        int mdlRes = 0;
                        if (checkMinus) {
                            mdlRes = (int) (-prev - next);
                            checkMinus = false;
                        } else {
                            mdlRes = (int) (prev - next);
                        }
                        String start = inx1 == -2 ? "" : line.substring(0, inx1 + 2);
                        String end = inx2 > size -1 ? "" : line.substring(inx2);
                        line = start + mdlRes + end;
                        //System.out.println(line);
                    } else {
                        checkMinus = true;
                        line = line.substring(1);
                        //System.out.println(line + "****");
                    }
                    break;
                }
            }
        }
        res = Long.parseLong(line);
        if (checkMinus){
            res = -res;
        }
        return expectedResult == res;
    }

    public static double findNext(String s, int index) {
        String str = "";
        index++;
        while (index < s.length() && s.charAt(index) != '*' && s.charAt(index) != '/' && s.charAt(index) != '+' && s.charAt(index) != '-') {
            str += s.charAt(index);
            index++;
        }
        return Double.parseDouble(str);
    }

    public static double findPrev(String s, int index) {
        String str = "";
        index--;
        while (index > -1 && s.charAt(index) != '+' && s.charAt(index) != '-') {
            str += s.charAt(index);
            index--;
        }
        try {
            String stt = (new StringBuffer(str)).reverse().toString();
            return Double.parseDouble(stt.trim());
        } catch (Exception e) {
            return -1;
        }
    }

    public static String[] findAllVariants(String digits, String[] op) {
        int iterations = (int) Math.pow(op.length, digits.length() - 1);
        String[] expr = new String[iterations];
        for (int i = 0; i < iterations; i++) {
            StringBuilder builder = new StringBuilder("" + digits.charAt(0));
            int operation = i;
            for (int j = 1; j < digits.length(); j++) {
                int action = operation % op.length;
                builder.append(op[action]);
                builder.append(digits.charAt(j));
                operation /= op.length;
            }
            expr[i] = builder.toString();
        }
        return expr;
    }

    public static void printResults(String[] result) {
        for (String str:result) {
            System.out.println(str + "=100");
        }
    }
}
