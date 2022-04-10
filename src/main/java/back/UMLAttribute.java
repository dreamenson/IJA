package back;

public class UMLAttribute extends Element {
    protected UMLClassifier type;

    public UMLAttribute(String name, UMLClassifier type) {
        super(name);
        this.type = type;
    }

    public UMLClassifier getType() {
        return this.type;
    }

    public java.lang.String toString() {
        return String.format("%s:%s", this.name, this.type);
    }
}
