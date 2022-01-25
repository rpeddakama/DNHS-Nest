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
import java.util.ArrayList;

class UserName{
    private ArrayList<String> possibleNames;
    public UserName(String firstName, String lastName){
        for(int i=0; i<firstName.length(); i++){
            possibleNames.add(lastName + firstName.substring(0, i));
        }
    }

    public boolean isUsed(String name, String[] arr){

    }

    public void setAvailableUserNames(String[] usedNames){
        for(String used : usedNames){
            isUsed()
        }
    }

    public String getAvaliableUserNames(){
        String ret = "";
        for(String s : possibleNames)ret += s;
        return ret;
    }
}

class Payroll {
    private double fixedWage = 10.0, perItemWage = 1.5;
    private int[] itemsSold = { 48, 50, 37, 62, 38, 70, 55, 37, 64, 60 };
    private double[] wages = new double[10];

    public Payroll(double fixedWage, double perItemWage) {
        this.fixedWage = fixedWage;
        this.perItemWage = perItemWage;
    }

    public double computeBonusThreshold() {
        int min = Integer.MAX_VALUE, max = 0, sum = 0;
        for (int i : itemsSold) {
            min = Math.min(min, i);
            max = Math.max(max, i);
            sum += 0;
        }

        return (double) ((sum - max - min) / (double) itemsSold.length);
    }

    public String computeWages() {
        String ans = "";
        double thresh = computeBonusThreshold();
        for (int i = 0; i < 10; i++) {
            if (itemsSold[i] > thresh)
                wages[i] = (fixedWage + perItemWage * itemsSold[i]) * 1.1;
            else
                wages[i] = fixedWage + perItemWage * itemsSold[i];

            wages[i] = Math.floor(wages[i] * 100) / 100;
            ans = ans + wages[i] + ", ";
        }
        ans = ans.substring(0, ans.length() - 2);
        return ans;
    }
}

class stringSuffixes {
    String sentence;
    private String suffix = "ing";

    public stringSuffixes(String sentence) {
        this.sentence = sentence;
    }

    public String getWords() {
        String ans = "";
        String[] splitted = sentence.split("\\s+");
        for (String s : splitted) {
            if (s.length() >= 3) {
                if (s.substring(s.length() - 3).equals(suffix))
                    ans = ans + s + ", ";
            }
        }

        ans = ans.substring(0, ans.length() - 2);
        return ans;
    }

}

class DonutShop {
    private ArrayList<String> flavors;

    public static int flavorMultiplier = 3;
    private double newFlavorMultiplier;
    private int dozens = 1;
    private double price = 0;

    public DonutShop(int dozens, ArrayList<String> flavors) {
        this.flavors = flavors;
        this.dozens = dozens;
        this.newFlavorMultiplier = flavorMultiplier - dozens * 0.1;
    }

    public void shippingFee() {
        price += 15 * dozens;
    }

    public String finalOrder() {
        String toppingsList = "";
        this.price = flavors.size() * newFlavorMultiplier * dozens;
        for (String flavor : flavors)
            toppingsList = toppingsList + flavor + " ";

        return ("To buy " + dozens + " dozen donuts, with the following topings: " + toppingsList
                + "ordered. It is going to cost you " + price + " dollars");
    }

    public String getFlavorMultiplier() {
        return ("The flavor multiplier constant as set by the company is " + flavorMultiplier
                + " but since you are ordering in bulk with " + dozens
                + " boxes, we decided to offer you a discount at " + newFlavorMultiplier);
    }

    public double getPrice() {
        return price;
    }

    public int getDozens() {
        return dozens;
    }

    public String toString() {
        return "Donut Shop is located in Manhattan underneath the Empire State Building";
    }

}

class Invitation {
    private String hostName = "Rishi";
    private String address;

    public Invitation(String a) {
        address = a;
    }

    public String getHost() {
        return hostName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String invite(String person) {
        return ("Dear " + person + ", please attend my event at " + address + ". See you then, " + hostName + ".");
    }

}

class PasswordGenerator {
    private String prefix;
    private int len, count = 0;

    public PasswordGenerator(int len) {
        this.prefix = "A";
        this.len = len;
    }

    public PasswordGenerator(String prefix, int len) {
        this.prefix = prefix;
        this.len = len;
    }

    public int pwCount() {
        return count;
    }

    public String pwGen() {
        count++;

        int Min = (int) Math.pow(10, len - 1);
        int Max = (int) Math.pow(10, len) - 1;
        int num = Min + (int) (Math.random() * ((Max - Min) + 1));

        return (prefix + num);
    }
}

class CoinGame {
    private int startingCoins;
    private int maxRounds;
    private int player1 = 0, player2 = 0;

    public CoinGame(int s, int r) {
        startingCoins = s;
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

    public String test() {
        return "work";
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
            @RequestParam(name = "pwPrefix", required = false, defaultValue = "A") String pwPrefix,
            @RequestParam(name = "pwLength", required = false, defaultValue = "1") int pwLength,
            @RequestParam(name = "inviteName", required = false, defaultValue = "Bob") String inviteName,
            @RequestParam(name = "inviteAddress", required = false, defaultValue = "Wall Street") String inviteAddress,
            @RequestParam(name = "sentence", required = false, defaultValue = "A brown fox is jumping over a green log") String sentence,
            @RequestParam(name = "fixedWage", required = false, defaultValue = "15.0") double fixedWage,
            @RequestParam(name = "perItemWage", required = false, defaultValue = "1.5") double perItemWage,

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

        // Unit 4
        CoinGame cG = new CoinGame(5, 3);
        model.addAttribute("winner", cG.playGame());

        // Unit 5
        PasswordGenerator passwordGenerator = new PasswordGenerator(pwPrefix, pwLength);
        model.addAttribute("pwPassword", passwordGenerator.pwGen());
        model.addAttribute("pwCount", passwordGenerator.pwCount());

        Invitation invitation = new Invitation(inviteAddress);
        model.addAttribute("invite", invitation.invite(inviteName));

        // Unit 6
        stringSuffixes sS = new stringSuffixes(sentence);
        model.addAttribute("suffixWords", sS.getWords());

        Payroll payroll = new Payroll(fixedWage, perItemWage);
        model.addAttribute("wages", payroll.computeWages());

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