package com.example.sping_portfolio.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArnavUnitThreeFrq {
    @GetMapping("/ArnavUnitThreeFrq")

    public String ArnavUnitThreeFrqPage(

            @RequestParam(name="rsvp", required=false, defaultValue="") String rsvp,
            @RequestParam(name="selection", required=false, defaultValue="0.0") int selection,
            Model model

    ) {



        return "arnavunitthreefrq";
    }
}
