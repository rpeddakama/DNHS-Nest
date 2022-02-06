
public class LightSequence {

    String seq;

    public String toString() {
        return seq;
    }

    public LightSequence(String seq) {
        this.seq = seq;
    }

    public String insertSegment(String segment, int ind) {
        String newSequence = "";
        for (int i = 0; i < segment.length(); i++) {
            if (i == ind) {
                newSequence = segment.substring(0, ind) + segment + segment.substring(ind, segment.length() - 1);
            }

        }

        return newSequence;
    }

    public void changeSequence(String se) {
        seq = se;
    }

    public void display() {
        System.out.println("Display");
    }

}

class Display {
    public static void main(String[] args) {
        LightSequence gradShow = new LightSequence("0101 0101 0101");
        gradShow.display();

        gradShow.changeSequence("0011 0011 0011");

        String resultSeq = gradShow.insertSegment("1111 1111", 4);

        String oldSequence = gradShow.toString();
        String newSeq = oldSequence.replaceFirst("11", "");

        double a = 1, b = 2;
        double answer = Math.sqrt(a*a + b*b);
    }
}
