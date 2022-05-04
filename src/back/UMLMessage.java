package back;

/**
 *
 * @author Viliam Holik
 */
public class UMLMessage extends Element {
    private final int type;
    private final UMLClass firstClass;
    private final UMLClass secondClass;

    public UMLMessage(String name, int type, UMLClass first, UMLClass second) {
        super(name);
        this.type = type;
        firstClass = first;
        secondClass = second;
    }

    public int getType() {
        return type;
    }

    public UMLClass getFirstClass() {
        return firstClass;
    }

    public UMLClass getSecondClass() {
        return secondClass;
    }
}
