package back;

/**
 * @author Viliam Holik
 */
public class UMLClassifier extends Element {
    private final boolean isUserDefined;

    public UMLClassifier(String name, boolean isUserDefined) {
        super(name);
        this.isUserDefined = isUserDefined;
    }

    public UMLClassifier(String name) {
        super(name);
        isUserDefined = true;
    }

    public static UMLClassifier forName(String name) {
        return new UMLClassifier(name, false);
    }

    public boolean isUserDefined() {
        return isUserDefined;
    }

    public String toString() {
        return name+"("+ isUserDefined+")";
    }
}
