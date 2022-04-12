package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Viliam Holik
 */
public class UMLOperation extends UMLAttribute {
    List<UMLAttribute> attrList = new ArrayList<>();

    public UMLOperation(java.lang.String name, UMLClassifier type) {
        super(name, type);
    }

    public static UMLOperation create(java.lang.String name, UMLClassifier type, UMLAttribute... args) {
        UMLOperation tmp = new UMLOperation(name, type);
        for (UMLAttribute arg : args) {
            tmp.addArgument(arg);
        }
        return tmp;
    }

    public boolean addArgument(UMLAttribute arg) {
        String tmp = arg.getName();
        for (UMLAttribute umlAttribute : attrList) {
            if (tmp.equals(umlAttribute.getName())) {
                return false;
            }
        }
        return attrList.add(arg);
    }

    public List<UMLAttribute> getArguments() {
        return Collections.unmodifiableList(attrList);
    }
}
