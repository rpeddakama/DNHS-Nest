
public class CoinGame {
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
            result = 2;

        } else {
            result = 1;

        }

        return result;
    }

    public String playGame(int player1Move) {
        int player1, player2, round = 0;
        player1 = player2 = startingCoins;

        while (round != maxRounds &&
                player1 >= 3 &&
                player2 >= 3) {

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

        return String.format(
                "%s wins.\n",
                player1 > player2 ? "player 1" : "player 2");
    }
}

class Display {
    public String longestStreak(String str) {
        int longestCount = 1;
        char longestChar = str.charAt(0);

        int currentCount = 1;
        char currentChar = str.charAt(0);

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
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
}
