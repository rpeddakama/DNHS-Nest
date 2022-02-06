
public class Word {
    public static void main(String[] args) {
        String[] words = {"ten", "fading", "fade", "faded", "fadeout", "fadein", "fadeaway"};
        for (String word : words) {
            if (word.endsWith("ing")) {
                System.out.println(word);
            }
        }

    }
}
