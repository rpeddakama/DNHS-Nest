package com.example.sping_portfolio.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

import com.example.util.LightSequence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClubAndTutorial {
    @GetMapping("/clubandtutorial")
    public String ClubAndTutorialString(
        @RequestParam(name="password", required=false, defaultValue="") String password,

        Model model

    ) {
        boolean approved = false;

        if(password.equals("password")){
            String passwordMessage = "This has been approved";
            approved = true;
            model.addAttribute("passwordMessage", passwordMessage);
        }
        else{
            String passwordMessage = "This has been not approved";
            model.addAttribute("passwordMessage", passwordMessage);
        }

        return "clubandtutorial";
    }
}
