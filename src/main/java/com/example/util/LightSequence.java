package com.example.util;

public class LightSequence

{

String seq;

public LightSequence(String seq){ 
    this.seq = seq;
 }


public String insertSegment(String segment, int ind)

{ 
    String newSequence = "";
    for (int i = 0; i < segment.length(); i++) {
        if (i == ind) {
            newSequence = segment.substring(0, ind) +  segment + segment.substring(ind, segment.length()-1);
        }
        
    }

    return newSequence;
}

 
public void changeSequence(String se)

{ 
    seq = se;
}

 
public void display()

{
    System.out.println("Display");
}


}