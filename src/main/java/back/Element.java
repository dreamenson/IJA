package back;

public class Element {
    protected String name;

    public Element(String name) {
        name = name;
    }

    public String getName() {
        return name;
    }

    public void rename(String newName) {
        name = newName;
    }
}
