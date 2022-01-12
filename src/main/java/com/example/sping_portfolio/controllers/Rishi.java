package com.example.sping_portfolio.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

class CoinGame {
    private int maxRounds;
    private int player1 = 0, player2 = 0;

    public CoinGame(int s, int r) {
        System.out.println(" INITIALIZE COIN GAME ");
        maxRounds = r;
    }

    public int getPlayer1Move() {
        Random random = new Random();
        int max = 3, min = 1;
        return random.nextInt(max - min + 1) + min;
    }

    public int getPlayer2Move(int round) {
        if (round % 3 == 0)
            return 3;
        if (round % 2 == 0)
            return 2;
        return 1;
    }

    public String playGame() {
        for (int i = 1; i <= maxRounds; i++) {
            int p1 = getPlayer1Move();
            int p2 = getPlayer2Move(i);
            if (p1 == p2 || Math.abs(p1 - p2) == 1)
                player2++;
            else
                player1 += 2;
        }

        if (player1 == player2)
            return ("tie game.");
        else if (player1 > player2)
            return ("Player 1 wins.");
        else
            return ("Player 2 wins.");
    }

}

@Controller
public class Rishi {
    @GetMapping("/rishi")
    public String RishiAbout(
            @RequestParam(name = "segment", required = false, defaultValue = "") String segment,
            @RequestParam(name = "a", required = false, defaultValue = "0.0") double a,
            @RequestParam(name = "b", required = false, defaultValue = "0.0") double b,
            @RequestParam(name = "rsvp", required = false, defaultValue = "false") boolean rsvp,
            @RequestParam(name = "selection", required = false, defaultValue = "0") int selection,
            @RequestParam(name = "option1", required = false, defaultValue = "") String option1,
            @RequestParam(name = "word", required = false, defaultValue = "AAAAABBB") String word,
            @RequestParam(name = "coins", required = false, defaultValue = "5") int coins,
            @RequestParam(name = "rounds", required = false, defaultValue = "3") int rounds,
            @RequestParam(name = "move", required = false, defaultValue = "2") int move,

            Model model) {

        // Unit 2 FRQ
        LightSequence gradShow = new LightSequence("0101 0101 0101");
        gradShow.display();
        gradShow.changeSequence("0011 0011 0011");
        String oldSequence = gradShow.toString();
        String newSeq = oldSequence.replaceFirst(segment, "");
        String addedSeq = oldSequence + " " + segment;

        model.addAttribute("newSeq", newSeq);
        model.addAttribute("addedSeq", addedSeq);

        double answer = Math.sqrt(a * a + b * b);

        model.addAttribute("answer", answer);

        if (rsvp) {
            if (selection == 1) {
                option1 = "Thanks for attending. You will be served beef.";
            } else if (selection == 2) {
                option1 = "Thanks for attending. You will be served chicken.";
            } else if (selection == 3) {
                option1 = "Thanks for attending. You will be served pasta.";
            } else {
                option1 = "Thanks for attending. You will be served fish.";
            }
        }

        else {
            option1 = "Sorry you can't make it.";
        }

        model.addAttribute("option1", option1);

        // Unit 4
        int ans = 1, cnt = 1;
        char cLong = word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1))
                cnt++;
            else {
                if (cnt > ans) {
                    ans = cnt;
                    cLong = word.charAt(i - 1);
                }
                cnt = 1;
            }
        }
        if (cnt > ans) {
            ans = cnt;
            cLong = word.charAt(word.length() - 1);
        }

        word = "The longest streak is " + ans + " and the character is " + cLong;
        model.addAttribute("word", word);

        // Unit 5
        CoinGame coinGame = new CoinGame(coins, rounds);
        model.addAttribute("winner", coinGame.playGame());

        return "rishi";

    }

    @GetMapping("/rishi-data")
    @ResponseBody
    public String RishiData() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://nfl-schedule.p.rapidapi.com/v1/schedules"))
                .header("x-rapidapi-host", "nfl-schedule.p.rapidapi.com")
                .header("x-rapidapi-key", "baa94acf5cmshe987609acdfe8f6p170e61jsn5f2012c5dd83")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}