package com.example.sping_portfolio.controllers;

import com.example.util.LightSequence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class AddPerson {
    @GetMapping("/addperson")
    public String UnitTwoFrqPage(

            @RequestParam(name = "segment", required = false, defaultValue = "") String segment,
            @RequestParam(name = "a", required = false, defaultValue = "0.0") double a,
            @RequestParam(name = "b", required = false, defaultValue = "0.0") double b,
            Model model

    ) {
        LightSequence gradShow = new LightSequence("0101 0101 0101");
        gradShow.display();
        gradShow.changeSequence("0011 0011 0011");
        // String resultSeq = gradShow.insertSegment("1111 1111", 4);
        String oldSequence = gradShow.toString();
        String newSeq = oldSequence.replaceFirst(segment, "");

        model.addAttribute("newSeq", newSeq);

        double answer = Math.sqrt(a * a + b * b);

        model.addAttribute("answer", answer);

        return "addperson";
    }

    @GetMapping("/getperson")
    @ResponseBody
    public String GetPerson() {
        Connection connection = null;
        String result = "";

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            statement.executeUpdate("insert into person values(3, 'john')");
            ResultSet rs = statement.executeQuery("select * from person");

            while (rs.next()) {
                // read the result set
                result += "name = " + rs.getString("name") + "\n";
                result += "id = " + rs.getInt("id") + "\n";
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        System.out.println(result);
        return result;
    }
}
