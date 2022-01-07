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

class CoinGame {
    private final int startingCoins;
    private final int maxRounds;

    public CoinGame(int s, int r) {
        startingCoins = s;
        maxRounds = r;
    }

    public int getPlayer1Move() {

        return 0;

    }

    public int getPlayer2Move(int round) {
        int result = 0;

        if (round % 3 == 0) {
            result = 3;

        } else if (round % 2 == 0) {
            result =  2;

        } else {
            result = 1;

        }

        return result;
    }

    public String playGame(int player1Move) {
        int player1, player2, round = 0;
        player1 = player2 = startingCoins;

        while(
                round != maxRounds &&
                        player1 >= 3 &&
                        player2 >= 3
        ) {

            int aMove = player1Move;
            int bMove = getPlayer2Move(round);
            int diff = Math.abs(aMove - bMove);

            switch (diff) {
                case 0:

                case 1:
                    player2 += 1;
                    break;

                case 2:
                    player1 += 2;
                    break;

                default:
                    break;
            }

            player1 -= aMove;
            player2 -= bMove;
        }

        if (player1 == player2) {
            return "tie game";

        }

        return String.format (
                "%s wins.\n",
                player1 > player2 ? "player 1" : "player 2"
        );
    }
}

@Controller
public class Alvin {
    @GetMapping("/alvin")
    public String AlvinAbout(
            Model model,
            @RequestParam(name="segment", required=false, defaultValue="") String segment,
            @RequestParam(name="a", required=false, defaultValue="0.0") double a,
            @RequestParam(name="b", required=false, defaultValue="0.0") double b,

            @RequestParam(name="testStr", required = false, defaultValue="TESTING") String testStr,
            @RequestParam(name="numRound", required=false, defaultValue="0") int numRound,
            @RequestParam(name="numCoins", required=false, defaultValue="0") int numCoins,
            @RequestParam(name="move", required=false, defaultValue="0") int move
    ) {
        model = UnitTwoFrqPage(segment, a, b, model);
        model = UnitFourPage(testStr, numRound, numCoins, move, model);

        return "alvin";
    }

    @GetMapping("/alvin-data")
    @ResponseBody
    public String AlvinData() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://league-of-legends-stats.p.rapidapi.com/champions/nasus/stats"))
                .header("x-rapidapi-host", "league-of-legends-stats.p.rapidapi.com")
                .header("x-rapidapi-key", "baa94acf5cmshe987609acdfe8f6p170e61jsn5f2012c5dd83")
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

    public String longestStreak(String str) {
        int longestCount = 1;
        char longestChar = str.charAt(0);

        int currentCount = 1;
        char currentChar = str.charAt(0);

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i-1)) {
                currentCount++;

            } else {
                currentCount = 1;
                currentChar = str.charAt(i);

            }

            if (longestCount < currentCount) {
                longestCount = currentCount;
                longestChar = currentChar;
            }
        }

        return String.format("%c %d\n", longestChar, longestCount);
    }

    @GetMapping("/alvin-frq4")
    public Model UnitFourPage (
            String testStr,
            int numRounds, int numCoins, int move,
            Model model
    ) {
        model.addAttribute("str", longestStreak(testStr));

        CoinGame game = new CoinGame(numCoins, numRounds);
        model.addAttribute("game_winner", game.playGame(move));

        return model;
    }
}