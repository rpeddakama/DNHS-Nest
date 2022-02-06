public class Main {
    public static void main(String[] args) {
        boolean rsvp = true;
        int selection = 2;
        String option1 = "", option2 = "";

        if (rsvp)
            System.out.println("attending");
        else
            System.out.println("not attending");

        if (selection == 1) {
            System.out.println("beef");
        } else if (selection == 2) {
            System.out.println("chicken");
        } else if (selection == 3) {
            System.out.println("pasta");
        } else {
            System.out.println("fish");
        }

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
        } else {
            option1 = "Sorry you can't make it.";
        }

        System.out.println(option1.equals(option2));
    }
}

class Draw {
    public void drawLine(int x1, int y1, int x2, int y2) {
    }

    public void drawSquare(int x, int y, int len) {
        len = Math.min(len, 10);

        drawLine(x, y, x + len, y);
        drawLine(x + len, y, x + len, y - len);
        drawLine(x + len, y - len, x, y - len);
        drawLine(x, y - len, x, y);
    }

}
