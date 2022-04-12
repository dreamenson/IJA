package back;

/**
 * Trieda reprezentuje vztahy medzi jednotlivymi Class-ami v ClassDiagram-e
 * @author Viliam Holik
 */
public class UMLRelation extends Element {
    private UMLClass firstClass;
    private UMLClass secondClass;
    private String relation;

    public UMLRelation(String name, UMLClass firstC, UMLClass secondC, String relation) {
        super(name);
        firstClass = firstC;
        secondClass = secondC;
        this.relation = relation;
    }

    public void changeRelation(String relation) {
        this.relation = relation;
    }
}
