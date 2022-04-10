package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UMLClass extends UMLClassifier {
    private boolean isAbstract;
    List<UMLAttribute> attrArray = new ArrayList<>();

    public UMLClass(java.lang.String name) {
        super(name);
        this.isAbstract = false;
    }

    public boolean isAbstract() {
        return this.isAbstract;
    }

    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public boolean addAttribute(UMLAttribute attr) {
        String name = attr.getName();
        for (UMLAttribute attribute : attrArray) {
            if (attribute.getName().equals(name)) {
                return false;
            }
        }
        return attrArray.add(attr);
    }

    public int getAttrPosition(UMLAttribute attr) {
        return attrArray.indexOf(attr);
    }

    public int moveAttrAtPosition(UMLAttribute attr, int pos) {
        if (!attrArray.remove(attr)) {
            return -1;
        }
        attrArray.add(pos, attr);
        return 0;
    }

    public java.util.List<UMLAttribute> getAttributes() {
        return Collections.unmodifiableList(attrArray);
    }
}
