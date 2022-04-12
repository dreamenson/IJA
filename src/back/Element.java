package back;

/**
 * @author Viliam Holik
 */
public class Element {
    protected String name;

    public Element(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void rename(String newName) {
        name = newName;
    }
}
