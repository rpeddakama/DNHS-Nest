import java.util.ArrayList;
import java.util.Random;

public class PasswordGenerator {
    private static int minPassLen = 8;

    private int passwordLen;
    private String prefix;
    private ArrayList<String> passList;

    public PasswordGenerator(int passLen) {
        this.passwordLen = Math.max(passLen, minPassLen);
        this.prefix = "A";
        passList = new ArrayList<>();
    }

    public PasswordGenerator(int passLen, String prefix) {
        this.passwordLen = Math.max(passLen, minPassLen);
        this.prefix = prefix;
        passList = new ArrayList<>();
    }

    public int getPassLen() {
        return passwordLen;
    }
    public String getPrefix() {
        return prefix;
    }
    public ArrayList<String> getPassList() {
        return passList;
    }
    public static int getMinPassLen() {
        return minPassLen;
    }

    public void setPassLen(int _passLen) {
        passwordLen = Math.max(_passLen, minPassLen);
    }
    public void setPrefix(String _prefix) {
        prefix = _prefix;
    }
    public void setPassList(ArrayList<String> _list) {
        passList = _list;
    }
    public static void setMinPassLen(int _len) {
        minPassLen = _len;
    }

    public int pwCount() {
        return passList.size();
    }

    public String pwGen() {
        String charDict = "1234567890";

        StringBuilder password = new StringBuilder();
        password.append(prefix + ".");

        Random rnd = new Random();
        while (password.length() < this.passwordLen) {
            int index = (int) (rnd.nextFloat() * charDict.length());
            password.append(charDict.charAt(index));
        }

        passList.add(password.toString());

        return password.toString();
    }

    public String toString() {
        return String.format("%d %d %s %s", passwordLen, minPassLen, prefix, passList.get(passList.size()-1));
    }
}
