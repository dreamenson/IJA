package back;

public class    UMLClassifier extends Element {
    private final boolean isUserDefined;

    public UMLClassifier(String name, boolean isUserDefined) {
        super(name);
        this.isUserDefined = isUserDefined;
    }

    public UMLClassifier(String name) {
        super(name);
        this.isUserDefined = true;
    }

    public static UMLClassifier forName(String name) {
        return new UMLClassifier(name, false);
    }

    public boolean isUserDefined() {
        return this.isUserDefined;
    }

    public String toString() {
        return this.name+"("+ this.isUserDefined+")";
    }
}
