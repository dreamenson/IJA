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

    public String toString() {
        String name = this.getType().getName() + " " + this.getName() + "(";
        StringBuilder args = new StringBuilder(name);
        this.getArguments().forEach( (a) -> args.append(a.getType().getName() + " " + a.getName() + ", "));
        int length = args.length();
        if (args.charAt(length - 2) == ',') {
            args.delete(length - 2, length);
        }
        args.append(")");
        return args.toString();
    }

    public boolean renameWhole(String newName) {
        if (!newName.matches("^[+\\-#~]\\w+\\s\\w+\\((\\s*\\w+\\s+\\w+,?)*\\)$")) {
            return false;
        }
        String[] parts = newName.split("\\(");
        parts[1] = parts[1].substring(0, parts[1].length() - 1);
        String[] name = parts[0].split("\\s");
        this.getType().rename(name[0]);
        this.rename(name[1]);
        attrList = new ArrayList<>();
        if (parts[1].length() == 0) {
            return true;
        }
        String[] args = parts[1].split(",\\s");
        for (String arg : args) {
            String[] argname = arg.split("\\s");
            addArgument(new UMLAttribute(argname[1], new UMLClassifier(argname[0])));
        }
        return true;
    }
}
