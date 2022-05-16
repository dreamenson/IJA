package back;

/**
 * Trieda reprezentuje atribut, ktory ma svoje meno a typ, je odvodena od treidy Element. Typ atributu je reprezentovany triedou UMLClassifier. Mozno pouzit ako atribut Class-y alebo argument operacie.
 * @author Viliam Holik
 */
public class UMLAttribute extends Element {
    protected UMLClassifier type;

    /**
     * Vytvori instanciu atributu
     * @param name nazov atributu
     * @param type typ atributu
     */
    public UMLAttribute(String name, UMLClassifier type) {
        super(name);
        this.type = type;
    }

    /**
     * Vracia typ atributu
     * @return typ atributu
     */
    public UMLClassifier getType() {
        return type;
    }

    /**
     * Vracia retazec reprezentujuci stav atributu v podobe "typ nazov"
     * @return retazec reprezentujuci atribut
     */
    public String toString() {
        return String.format("%s %s", type, name);
    }

    public boolean renameWhole(String newName) {
        if (!newName.matches("^[+\\-#~]\\w+ \\w+")) {
            return false;
        }
        String[] name = newName.split("\\s");
        type.rename(name[0]);
        this.rename(name[1]);
        return true;
    }
}
