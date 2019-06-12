package com.company;

import java.util.ArrayList;

class MultyThread extends Thread
{
    private ArrayList<String> varss = new ArrayList<>();

    public MultyThread(String[] varr, int start, int end) {
        for (int i = start; i <= end; i++) {
            varss.add(varr[i] + "");
        }
    }
    @Override
    public void run()
    {
        for (String v: varss) {
            if (Task_100.checkAnswer(v, 100L)) {
                System.out.println(v + " = 100");
            }
        }

    }
}