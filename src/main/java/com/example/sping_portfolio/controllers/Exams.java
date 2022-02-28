package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;

@Controller
public class Exams {
    @GetMapping("/exams")
    public String ExamsPage(
            @RequestParam(name = "examNameField", required = false, defaultValue = "") String examNameField,
            @RequestParam(name = "classNameField", required = false, defaultValue = "") String classNameField,
            @RequestParam(name = "teacherField", required = false, defaultValue = "") String teacherField,
            @RequestParam(name = "classPeriodField", required = false, defaultValue = "") String classPeriodField,
            @RequestParam(name = "examDateField", required = false, defaultValue = "") String examDateField,
            Model model) {
        ArrayList<ExamEvent> eventList = new ArrayList<ExamEvent>();

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:exams.db");

            stmt = c.createStatement();
            String sql = "INSERT INTO EXAM (examName,className,teacher,classPeriod,examDate) " +
                    "VALUES ('" + examNameField + "', '" + classNameField + "', '" + teacherField + "', '"
                    + classPeriodField + "', '" + examDateField + "');";
            stmt.executeUpdate(sql);

            ResultSet rs = stmt.executeQuery("SELECT * FROM EXAM;");

            while (rs.next()) {
                String examName = rs.getString("examName");
                String className = rs.getString("className");
                String teacher = rs.getString("teacher");
                String classPeriod = rs.getString("classPeriod");
                String examDate = rs.getString("examDate");

                System.out.println(examName + " " + className + " " + teacher + " " + classPeriod + " " + examDate);
            
                eventList.add(new ExamEvent(examName, className, teacher, classPeriod, examDate));
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");

        model.addAttribute("eventList", eventList);

        return "exams";
    }
}