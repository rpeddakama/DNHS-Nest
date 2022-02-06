
public class Animal {
    protected String animalType;
    protected String species;
    protected String name;

    public Animal(String animalType, String species, String name) {
        this.animalType = animalType;
        this.species = species;
        this.name = name;
    }

    public String toString() {
        return String.format("%s the %s is a %s", name, species, animalType);
    }

    public static void main(String[] args) {
        Elephant e = new Elephant("abby", 2.0f);
        System.out.println(e);
    }
}

class Herbivore extends Animal {
    public Herbivore(String species, String name) {
        super("herbivore", species, name);
    }
}

class Elephant extends Herbivore {
    private float tuskLength;

    public Elephant(String name, float tuskLength) {
        super("elephant", name);
        this.tuskLength = tuskLength;
    }

    public String toString() {
        return super.toString() + String.format(" with tusks %f meters long", tuskLength);
    }
}
