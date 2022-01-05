package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UnitThreeFrq {
    @GetMapping("/unitthreefrq")
    public String UnitThreeFrqPage(
        @RequestParam(name="length", required=false, defaultValue="") int length, 
        @RequestParam(name="x", required=false, defaultValue="") int x, 
        @RequestParam(name="y", required=false, defaultValue="") int y, 
        Model model
    ) {
        model.addAttribute("length", length);
        model.addAttribute("x", x);
        model.addAttribute("y", y);

        return "unitthreefrq";
    }
}
