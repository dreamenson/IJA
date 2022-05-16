package back;

/**
 * Trieda reprezentuje vztahy medzi jednotlivymi Class-ami a rozhraniami v diagrame tried
 * @author Viliam Holik
 */
public class UMLRelation {
    private final UMLClassifier firstClass;
    private final UMLClassifier secondClass;
    private int relation; // 1-generalizacia, 2-asociacia, 3-agregacia, 4-kompozicia

    /**
     * Konstruktor pre vytvorenie vztahu medzi 2 Class-ami
     * @param firstC prva Class-a, ktora je vo vztahu
     * @param secondC druha Class-a, ktora je vo vztahu
     * @param relation typ vztahu
     */
    public UMLRelation(UMLClassifier firstC, UMLClassifier secondC, int relation) {
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
    public int getRelation() {
        return relation;
    }

    /**
     * Funkcia na zmenu typu relacie
     * @param relation typ relacie
     */
    public void changeRelation(int relation) {
        this.relation = relation;
    }

    public String getTypeOfRelation() {
        switch (relation){
            case 1:
                return "generalize";
            case 2:
                return "associate";
            case 3:
                return "agregate";
            case 4:
                return "composite";
            default:
                throw new IllegalStateException("Unexpected value: " + relation);
        }
    }
}
