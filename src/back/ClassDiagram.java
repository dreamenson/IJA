package back;

import gui.GClassDiagram;
import gui.GUMLRelation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Trieda rezprezentuje diagram tried. Je odvodena od tiedy Element. Obsahuje zoznam tried UMLClass a zoznam vztahov UMLRelation.
 * @author Viliam Holik
 */
public class ClassDiagram extends Element {
    private GClassDiagram gClassDiagram;
    private List<UMLClassifier> classList = new ArrayList<>();
    private List<UMLRelation> relationList = new ArrayList<>();

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
     * Vytvori instanciu rozhrania a vlozi ju do diagramu. Pokial sa tam uz nachadza rozhranie s rovnakym nazvom, neurobi nic
     * @param name nazov vytvaraneho rozhrania
     * @return instancia vytvoreneho rozhrania, ak rozhranie s danym nazvom uz existuje vracia null
     */
    public UMLInterface createInterface(String name) {
        for (UMLClassifier umlClass : classList) {
            if (name.equals(umlClass.getName())){
                return null;
            }
        }
        UMLInterface tmp = new UMLInterface(name);
        classList.add(tmp);
        return tmp;
    }

    /**
     * Vytvori instanciu triedy UMLRelation a vlozi ju do diagramu. Pokial sa tam uz nachadza relacia s rovnakym nazvom, neurobi nic
     * @param firstC prva Class-a, ktora je vo vztahu
     * @param secondC druha Class-a, ktora je vo vztahu
     * @param relation typ vztahu
     * @return instancia vytvorenej relacie, ak neexistuju zadane Class-y vracia null
     */
    public UMLRelation createRelation(String firstC, String secondC, int relation) {
        UMLClassifier first = findClass(firstC);
        UMLClassifier second = findClass(secondC);
        if(first == null || second == null){
            System.out.println("ajaaaaj");
            return null;
        }
        UMLRelation tmp = new UMLRelation(first, second, relation);
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
    public UMLClassifier findClass(String name) {
        if (name == null) return null;
        for (UMLClassifier umlClass : classList) {
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
    public List<UMLClassifier> getClassList() {
        return Collections.unmodifiableList(classList);
    }

    public void removeClass(UMLClassifier classifier) {
        Iterator<UMLRelation> i = relationList.iterator();
        while (i.hasNext()) {
            UMLRelation gr = i.next();
            if (gr.getFirstClass() == classifier || gr.getSecondClass() == classifier) {
                i.remove();
            }
        }
        classList.remove(classifier);
    }

    /**
     * Vracia nemodifikovatelny zoznam relacii ktore sa nachadzaju v diagrame
     * @return nemodifikovatelny zoznam relacii
     */
    public List<UMLRelation> getRelationList() {
        return Collections.unmodifiableList(relationList);
    }

    public void addG(GClassDiagram gClassDiagram) {
        this.gClassDiagram = gClassDiagram;
    }

    public GClassDiagram getG() {
        return gClassDiagram;
    }

    public void removeRelation(UMLRelation umlRelation) {
        relationList.remove(umlRelation);
    }
}

