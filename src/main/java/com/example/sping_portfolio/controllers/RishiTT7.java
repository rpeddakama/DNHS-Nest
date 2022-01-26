package com.example.sping_portfolio.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
public class RishiTT7 {
    @GetMapping("/RishiTT7")
    public String RishiTT7 (
        @RequestParam(name="y", required=false, defaultValue="1") int y, 
        @RequestParam(name="x", required=false, defaultValue="1") int x, 
        Model model
    ) {

        int arr[] = {1,2,3,4,5,6,7,8};
        model.addAttribute("arr", arr);

        return "RishiTT7"; 
    }

}