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
public class Raadwan {
    @GetMapping("/raadwan")

    public String RaadwanAbout(
        @RequestParam(name="sequence", required=true, defaultValue="None") String sequence, 
        @RequestParam(name="newSequence", required=true, defaultValue="None") String newSequence, 
        @RequestParam(name="insertSequence", required=true, defaultValue="None") String insertSequence, 
        @RequestParam(name="length", required=true, defaultValue="4") int length, 
        @RequestParam(name="x", required=true, defaultValue="1") int x, 
        @RequestParam(name="y", required=true, defaultValue="1") int y, 
        Model model
    ) {
        LightSequence lightSequence = new LightSequence(sequence);

        model.addAttribute("sequence", lightSequence.getSequence());

        model.addAttribute("length", length);
        model.addAttribute("x", x);
        model.addAttribute("y", y);

        return "raadwan"; 
    }

    @GetMapping("/raadwan-data")
    @ResponseBody
    public String RaadData() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://f1-drivers-2021.p.rapidapi.com/drivers/lewis"))
                .header("x-rapidapi-host", "f1-drivers-2021.p.rapidapi.com")
                .header("x-rapidapi-key", "baa94acf5cmshe987609acdfe8f6p170e61jsn5f2012c5dd83")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

class LightSequence {
    String seq;

    public LightSequence(String seq) {
        this.seq = seq;
    }

    public String getSequence() {
        return this.seq;
    }

    public void display() {
        System.out.println(seq);
    }

    public void changeSequence(String seq) {
        this.seq = seq;
    }

    public String insertString(String segment, int ind) {
        return this.seq.substring(0, ind + 1) + segment + this.seq.substring(ind + 1);
    }

    public String binaryToText() {
        return String.valueOf((char)Integer.parseInt(this.seq, 2));
    }
}