package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Trieda rezprezentuje diagram tried. Je odvodena od tiedy Element. Obsahuje zoznam tried UMLClass a zoznam vztahov UMLRelation.
 * @author Viliam Holik
 */
public class ClassDiagram extends Element {
    List<UMLClass> classList = new ArrayList<>();
    List<UMLRelation> relationList = new ArrayList<>();

    /**
     * Vytvori instaciu diagramu. Kazdy diagram ma svoj nazov
     * @param name nazov diagramu
     */
    public ClassDiagram(String name) {
        super(name);
    }

    /**
     * Vytvori instanciu triedy UMLClass a vlozi ju do diagramu. Pokial sa tam uz nachadza trieda s rovnakym nazvom, neurobi nic
     * @param name nazov vytvaranej triedy
     * @return instancia vytvorenej tridy, ak trieda s danym nazvom uz existuje vracia null
     */
    public UMLClass createClass(String name) {
        for (UMLClassifier umlClass : classList) {
            if (name.equals(umlClass.getName())){
                return null;
            }
        }
        UMLClass tmp = new UMLClass(name);
        classList.add(tmp);
        return tmp;
    }

    /**
     * Vytvori instanciu triedy UMLRelation a vlozi ju do diagramu. Pokial sa tam uz nachadza relacia s rovnakym nazvom, neurobi nic
     * @param name nazov vztahu
     * @param firstC prva Class-a, ktora je vo vztahu
     * @param secondC druha Class-a, ktora je vo vztahu
     * @param relation typ vztahu
     * @return instancia vytvorenej relacie, ak neexistuju zadane Class-y vracia null
     */
    public UMLRelation createRelation(String name, String firstC, String secondC, String relation) {
        UMLClass first = findClass(firstC);
        UMLClass second = findClass(secondC);
        if(first == null || second == null){
            System.out.println("ajaaaaj");
            return null;
        }
        UMLRelation tmp = new UMLRelation(name, first, second, relation);
        relationList.add(tmp);
        return tmp;
    }

    /*public UMLClassifier classifierForName(String name) {
        for (UMLClassifier umlClass : classList) {
            if (name.equals(umlClass.getName())) {
                return umlClass;
            }
        }
        UMLClassifier tmp = UMLClassifier.forName(name);
        classList.add(tmp);
        return tmp;
    }*/

    /**
     * Vyhlada v diagrame Class-u podla nazvu
     * @param name nazov Class-y
     * @return najdena Class-a, pokial v diagrame neexistuje Class-a s danym nazvom vracia null
     */
    public UMLClass findClass(String name) {
        for (UMLClass umlClass : classList) {
            if (name.equals(umlClass.getName())) {
                return umlClass;
            }
        }
        return null;
    }

    /**
     * Vracia nemodifikovatelny zoznam Class ktore sa nachadzaju v diagrame
     * @return nemodifikovatelny zoznam Class
     */
    public List<UMLClass> getClassList() {
        return Collections.unmodifiableList(classList);
    }

    /**
     * Vracia nemodifikovatelny zoznam relacii ktore sa nachadzaju v diagrame
     * @return nemodifikovatelny zoznam relacii
     */
    public List<UMLRelation> getRelationList() {
        return Collections.unmodifiableList(relationList);
    }
}

