package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Trieda reprezentuje operaciu, ktora ma svoje meno, navratovy typ a zoznam argumentov, je odvodena od triedy UMLAttribute. Argument je reprezentovany triedou UMLAttribute. Mozno pouzit ako sucast Class-y alebo rozhrania.
 * @author Viliam Holik
 */
public class UMLOperation extends UMLAttribute {
    List<UMLAttribute> attrList = new ArrayList<>();

    /**
     * Vytvori instanciu s danym nazvom a navratovym typom
     * @param name nazov operacie
     * @param type navratovy typ operacie
     */
    public UMLOperation(java.lang.String name, UMLClassifier type) {
        super(name, type);
    }

    /**
     * Tovarna metoda pre vytvorenie instancie operacie
     * @param name nazov operacie
     * @param type navratovy typ operacie
     * @param args zoznam argumentov operacie
     * @return instanciu reprezentujucu operaciu v diagrame UML
     */
    public static UMLOperation create(java.lang.String name, UMLClassifier type, UMLAttribute... args) {
        UMLOperation tmp = new UMLOperation(name, type);
        for (UMLAttribute arg : args) {
            tmp.addArgument(arg);
        }
        return tmp;
    }

    /**
     * Prida novy argument do zoznamu argumentov. Argument sa vlozi na koniec zoznamu, pokial v zozname uz existuje argument s rovnakym nazvom neurobi nic
     * @param arg vkladany argument
     * @return uspech operacie - true uspesna a false neuspesna
     */
    public boolean addArgument(UMLAttribute arg) {
        String tmp = arg.getName();
        for (UMLAttribute umlAttribute : attrList) {
            if (tmp.equals(umlAttribute.getName())) {
                return false;
            }
        }
        return attrList.add(arg);
    }

    /**
     * Vracia nemodifikovatelny zoznam argumentov
     * @return nemodifikovatelny zoznam argumentov
     */
    public List<UMLAttribute> getArguments() {
        return Collections.unmodifiableList(attrList);
    }
}
