package back;

/**
 * Trieda reprezentuje vztahy medzi jednotlivymi Class-ami a rozhraniami v diagrame tried
 * @author Viliam Holik
 */
public class UMLRelation extends Element {
    private final UMLClassifier firstClass;
    private final UMLClassifier secondClass;
    private String relation;

    /**
     * Konstruktor pre vytvorenie vztahu medzi 2 Class-ami
     * @param name nazov vztahu
     * @param firstC prva Class-a, ktora je vo vztahu
     * @param secondC druha Class-a, ktora je vo vztahu
     * @param relation typ vztahu
     */
    public UMLRelation(String name, UMLClassifier firstC, UMLClassifier secondC, String relation) {
        super(name);
        firstClass = firstC;
        secondClass = secondC;
        this.relation = relation;
    }

    /**
     * Funkcia vracia prvu Class-u vo vztahu
     * @return prva Class-a
     */
    public UMLClassifier getFirstClass() {
        return firstClass;
    }

    /**
     * Funkcia vracia druhu Class-u vo vztahu
     * @return druha Class-a
     */
    public UMLClassifier getSecondClass() {
        return secondClass;
    }

    /**
     * Funkcia vracia nazov vztahu
     * @return nazov vztahu
     */
    public String getRelation() {
        return relation;
    }

    /**
     * Funkcia na zmenu typu relacie
     * @param relation typ relacie
     */
    public void changeRelation(String relation) {
        this.relation = relation;
    }
}
