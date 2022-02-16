package com.example.quiz;

import java.util.ArrayList;

public class RaadwanQuiz {
    public static void main(String[] args) {
        // Create an ArrayList of Type Number, my ArrayList is called squirrels

        ArrayList<Number> squirrels = new ArrayList<Number>();

        // Initialize 10 squirrels of class type Number

        // Insert Number instance into ArrayList Squirrel in least to greatest order by
        // random number, mine required nested loops

        squirrels.add(new Number());
        Number newNum = new Number();
        if (squirrels.get(0).getRandomNum() > newNum.getRandomNum())
            squirrels.add(0, newNum);
        else if (squirrels.get(0).getRandomNum() < newNum.getRandomNum())
            squirrels.add(newNum);

        while (squirrels.size() != 10) {
            Number obj = new Number();
            int num = obj.getRandomNum();

            for (int i = 1; i < squirrels.size(); i++) {
                if (num > squirrels.get(i - 1).getRandomNum() && num < squirrels.get(i).getRandomNum()) {
                    squirrels.add(i, obj);
                    obj.index = i;
                }
            }
        }

        // Print a formatted message with number of Squirrels and Index by which they
        // were created, use enhanced for loop
        // System.out.println(squirrels.get(0).getRandomNum());

        for (Number num : squirrels) {
            System.out.println("Squirrels: " + num.getRandomNum() + " Day: " + num.getIndex());
        }
    }
}

// Write a Class Number
class Number {
    // instance variables
    int randomNum;
    int index;

    // Number has a zero Argument constructor
    // It initializes a random number between 3 and 36, ie the number of squirrels
    // in class
    public Number() {
        randomNum = (int) Math.floor(Math.random() * (34 + 3));
    }

    // It contains a getter for the Random Number
    public int getRandomNum() {
        return randomNum;
    }

    // It contains a getter for Index, or the order it was initialized
    public int getIndex() {
        return index;
    }
}
