package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UMLClass extends UMLClassifier {
    private boolean isAbstract;
    List<UMLAttribute> attrArray = new ArrayList<>();
    List<UMLOperation> operArray = new ArrayList<>();

    public UMLClass(java.lang.String name) {
        super(name);
        isAbstract = false;
    }

    public boolean isAbstract() {
        return isAbstract;
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

    public boolean addOperation(UMLOperation oper) {
        String name = oper.getName();
        for (UMLOperation operation : operArray) {
            if (operation.getName().equals(name)) {
                return false;
            }
        }
        return operArray.add(oper);
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

    public List<UMLAttribute> getAttributes() {
        return Collections.unmodifiableList(attrArray);
    }

    public List<UMLOperation> getOperations() {
        return Collections.unmodifiableList(operArray);
    }
}
