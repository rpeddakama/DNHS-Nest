package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
public class Exams {
    @GetMapping("/exams")
    public String ExamsPage(
        @RequestParam(name="examNameField", required=false, defaultValue="") String examNameField,
        @RequestParam(name="classNameField", required=false, defaultValue="") String classNameField,
        @RequestParam(name="teacherField", required=false, defaultValue="") String teacherField,
        @RequestParam(name="classPeriodField", required=false, defaultValue="") String classPeriodField,
        Model model
    ) {
        model.addAttribute("examName", examNameField);
        model.addAttribute("className", classNameField);
        model.addAttribute("teacher", teacherField);
        model.addAttribute("classPeriod", classPeriodField);

        return "exams";
    }
}