package com.example.util;

import java.util.Random;
import java.util.ArrayList;

public class Number {
    int squirrels;
    int index = 0;

    public Number() {
        Random rand = new Random();
        squirrels = rand.nextInt(36 - 3) + 3;
        index++;
    }

    public int getSquirrels() {
        return this.squirrels;
    }

    public int getIndex() {
        return this.index;
    }

    public static void main(String[] args) {
        ArrayList<Number> squirrels = new ArrayList<Number>();
        // adding each Number to arrayList
        for (int i = 0; i < 10; i++) {
            squirrels.add(new Number());
        }
        // sorting the squirrels
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (squirrels.get(i).getSquirrels() < squirrels.get(j).getSquirrels()) {
                    Number temp = squirrels.get(i);
                    squirrels.set(i, squirrels.get(j));
                    squirrels.set(j, temp);
                }
            }
        }
        // print formatted output
        for (int i = 0; i < 10; i++) {
            System.out
                    .println("Squirrels: " + squirrels.get(i).getSquirrels() + " Day: " + squirrels.get(i).getIndex());
        }

    }

}