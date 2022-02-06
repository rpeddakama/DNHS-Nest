import java.util.ArrayList;

public class UserName {
    private ArrayList<String> possibleNames;

    public UserName(String firstName, String lastName) {
        possibleNames = new ArrayList<String>();    
        for (int i = 0; i < firstName.length(); i++) {
            possibleNames.add(lastName + firstName.substring(i, i + 1));
        }
    }

    public void setAvailableUserNames(String[] usedNames) {
        for(String name : usedNames) {
            if (possibleNames.contains(name)) possibleNames.remove(name);
        }
    }

}
