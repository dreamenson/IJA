package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Viliam Holik
 */
public class UMLInterface extends UMLClassifier {
    List<UMLAttribute> attrArray = new ArrayList<>();
    List<UMLOperation> operArray = new ArrayList<>();

    public UMLInterface(String name) {
        super(name);
    }

    /**
     * Vlozi atribut do rozhrania. Atribut sa vlozi nakoniec zoznamu, pokial uz rozhranie obsahuje atribut s rovnakym nazvom neurobi nic
     * @param attr vkladany atribut
     * @return uspech akcie, true ak sa podarilo, inak false
     */
    public boolean addAttribute(UMLAttribute attr) {
        String name = attr.getName();
        for (UMLAttribute attribute : attrArray) {
            if (attribute.getName().equals(name)) {
                return false;
            }
        }
        return attrArray.add(attr);
    }

    /**
     * Vlozi operaciu do rozhrania. Operacia sa vlozi na koniec zoznamu, pokial uz rozhranie obsahuje operaciu s rovnakym nazvom neurobi nic
     * @param oper vkladana operacia
     * @return uspech akcie, true ak sa podarilo, inak false
     */
    public boolean addOperation(UMLOperation oper) {
        String name = oper.getName();
        for (UMLOperation operation : operArray) {
            if (operation.getName().equals(name)) {
                return false;
            }
        }
        return operArray.add(oper);
    }

    /**
     * Vrati nemodifikovatelny zoznam atributov
     * @return nemodifikovatelny zoznam atributov
     */
    public List<UMLAttribute> getAttributes() {
        return Collections.unmodifiableList(attrArray);
    }

    /**
     * Vrati nemodifikovatelny zoznam atributov
     * @return nemodifikovatelny zoznam atributov
     */
    public List<UMLOperation> getOperations() {
        return Collections.unmodifiableList(operArray);
    }
}
