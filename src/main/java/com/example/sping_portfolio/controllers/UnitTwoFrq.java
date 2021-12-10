package com.example.sping_portfolio.controllers;

import com.example.util.LightSequence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UnitTwoFrq {
    @GetMapping("/UnitTwoFrq")
    public String UnitTwoFrqPage(
        
        @RequestParam(name="segment", required=false, defaultValue="") String segment, 
        @RequestParam(name="a", required=false, defaultValue="0.0") double a,
        @RequestParam(name="b", required=false, defaultValue="0.0") double b,
        Model model
    
    ) {
        LightSequence gradShow = new LightSequence("0101 0101 0101");
        gradShow.display();
        gradShow.changeSequence("0011 0011 0011");
        // String resultSeq = gradShow.insertSegment("1111 1111", 4);
        String oldSequence = gradShow.toString();
        String newSeq = oldSequence.replaceFirst(segment, "");

        model.addAttribute("newSeq", newSeq);
        
        double answer = Math.sqrt(a*a + b*b);

        model.addAttribute("answer", answer);

        
        return "UnitTwoFrq";
    }
}