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
            @RequestParam(name="rsvp", required=false, defaultValue="false") boolean rsvp,
            @RequestParam(name="selection", required=false, defaultValue="0") int selection,
            @RequestParam(name="option1", required=false, defaultValue="") String option1,
            @RequestParam(name="word", required=false, defaultValue="AAAAABBB") String word,


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

        if (rsvp){
            if (selection == 1){
                option1 = "Thanks for attending. You will be served beef.";
            }
            else if (selection == 2){
                option1 = "Thanks for attending. You will be served chicken.";
            }
            else if (selection == 3){
                option1 = "Thanks for attending. You will be served pasta.";
            }
            else{    option1 = "Thanks for attending. You will be served fish.";
            }
        }

        else{
            option1 = "Sorry you can't make it.";
        }

        model.addAttribute("option1", option1);

        int longestCount = 1;
        char longestChar = ' ';

        int currentCount = 1;
        char currentChar = ' ';

        int indexInitial = 0;
        int indexFinal = 0;

        for(int i = 1; i < word.length(); i++){
            if(word.charAt(i) == word.charAt(i-1)){
                currentChar = word.charAt(0);
                currentCount++;
                currentChar = word.charAt(i);
                if(currentCount == 2){
                    indexInitial = i-1;
                }
            }
            else{
                indexFinal = i;
                currentCount = 1;
            }
            if(currentCount > longestCount){
                longestCount = currentCount;
                longestChar = currentChar;
            }
        }
        word = "The longest streak was " + longestCount + " and the character of this streak was " + longestChar;
//        System.out.println(longestCount);
//        System.out.println(longestChar);

        model.addAttribute("word", word);

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