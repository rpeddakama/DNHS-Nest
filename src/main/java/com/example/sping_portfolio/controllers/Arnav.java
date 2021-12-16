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
public class Arnav {
    @GetMapping("/arnav")
    public String ArnavAbout(
            @RequestParam(name="segment", required=false, defaultValue="") String segment,
            @RequestParam(name="a", required=false, defaultValue="0.0") double a,
            @RequestParam(name="b", required=false, defaultValue="0.0") double b,
            Model model
    ) {
        //Unit 2 FRQ
        LightSequence gradShow = new LightSequence("0101 0101 0101");
        gradShow.display();
        gradShow.changeSequence("0011 0011 0011");
        // String resultSeq = gradShow.insertSegment("1111 1111", 4);
        String oldSequence = gradShow.toString();
        String newSeq = oldSequence.replaceFirst(segment, "");
        String addedSeq = oldSequence + " " + segment;

        model.addAttribute("newSeq", newSeq);
        model.addAttribute("addedSeq", addedSeq);

        double answer = Math.sqrt(a*a + b*b);

        model.addAttribute("answer", answer);
        return "arnav";
    }

    @GetMapping("/arnav-data")
    @ResponseBody
    public String ArnavData() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://free-nba.p.rapidapi.com/players/237"))
                .header("x-rapidapi-host", "free-nba.p.rapidapi.com")
                .header("x-rapidapi-key", "baa94acf5cmshe987609acdfe8f6p170e61jsn5f2012c5dd83")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}