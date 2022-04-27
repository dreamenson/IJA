package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Viliam Holik
 */
public class ClassDiagram extends Element {
    List<UMLClass> classList = new ArrayList<>();
    List<UMLRelation> relationList = new ArrayList<>();

    public ClassDiagram(String name) {
        super(name);
    }

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

    public UMLClass findClass(String name) {
        for (UMLClass umlClass : classList) {
            if (name.equals(umlClass.getName())) {
                return umlClass;
            }
        }
        return null;
    }

    public List<UMLClass> getClassList() {
        return Collections.unmodifiableList(classList);
    }

    public List<UMLRelation> getRelationList() {
        return Collections.unmodifiableList(relationList);
    }
}

