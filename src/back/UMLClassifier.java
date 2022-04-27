package back;

/**
 * Trieda reprezentuje klasifikator v diagrame
 * @author Viliam Holik
 */
public class UMLClassifier extends Element {
    private final boolean isUserDefined;

    /**
     * Vytvori instanciu klasifikatora
     * @param name nazov klasifikatora
     * @param isUserDefined uzivatelsky definovany
     */
    public UMLClassifier(String name, boolean isUserDefined) {
        super(name);
        this.isUserDefined = isUserDefined;
    }

    /**
     * Vytvori instanciu klasifikatora, tento je uzivatelsky definovany
     * @param name nazov klasifikatora
     */
    public UMLClassifier(String name) {
        super(name);
        isUserDefined = true;
    }

    /**
     * Tovarna metoda pre vytvorenie instancie klasifikatora pre zadany nazov. Instancia reprezentuje klasifikator, ktory nie je v diagrame modelovany
     * @param name nazov klasifikatora
     * @return vytvoreny klasifikator
     */
    public static UMLClassifier forName(String name) {
        return new UMLClassifier(name, false);
    }

    /**
     * Zisti ci je instancia klasifikatora uzivatelsky definovana
     * @return pokial je uzivatelsky definovany vracia true, inak false
     */
    public boolean isUserDefined() {
        return isUserDefined;
    }

    /**
     * Vracia retazec reprezentujuci klasifikator v podobe "nazov(userDefined)", kde userDefined je true alebo false
     * @return retazec reprezentujuci klasifikator
     */
    public String toString() {
        return name+"("+ isUserDefined+")";
    }
}
