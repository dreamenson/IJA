package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassDiagram extends Element {
    List<UMLClass> classList = new ArrayList<>();

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

    public UMLClassifier findClassifier(String name) {
        for (UMLClassifier umlClass : classList) {
            if (name.equals(umlClass.getName())) {
                return umlClass;
            }
        }
        return null;
    }

    public List<UMLClass> getClassList() {
        return Collections.unmodifiableList(classList);
    }
}

