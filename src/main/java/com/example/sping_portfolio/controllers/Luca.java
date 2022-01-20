package com.example.sping_portfolio.controllers;

import com.example.util.LightSequence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class Luca {
    @GetMapping("/luca")
    public String LucaAbout(
            Model model,
            @RequestParam(name="segment", required=false, defaultValue="") String segment,
            @RequestParam(name="a", required=false, defaultValue="0.0") double a,
            @RequestParam(name="b", required=false, defaultValue="0.0") double b
    ) {
        model = UnitTwoFrqPage(segment, a, b, model);
        return "luca";
    }

    @GetMapping("/luca-data")
    @ResponseBody
    public String PaulData() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=BTC,USD"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public Model UnitTwoFrqPage(
            String segment, double a, double b,
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

        return model;
    }
}