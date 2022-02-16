package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BellSchedule {
    @GetMapping("/bellschedule")
    public String Schedule() {
        return "bellschedule";
    }

    // @GetMapping("/get-schedule")
    // @ResponseBody
    // public String GetSchedule(String schedule) {
    //     return 
    // }
}