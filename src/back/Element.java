package back;

/**
 * Trieda reprezentuje pomenovany element (thing), ktory moze byt sucastou akejkolvek casti v diagrame.
 * @author Viliam Holik
 */
public class Element {
    protected String name;

    /**
     * Vytvori instanciu so zadanym nazvom
     * @param name nazov Elementu
     */
    public Element(String name) {
        this.name = name;
    }

    /**
     * Vrati nazov elementu
     * @return nazov elementu
     */
    public String getName() {
        return name;
    }

    /**
     * Premenuje element
     * @param newName novy nazov elementu
     */
    public void rename(String newName) {
        name = newName;
    }
}
