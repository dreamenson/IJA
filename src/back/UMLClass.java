package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Trieda reprezentuje model triedy z jazyka UML. Rozsiruje triedu UMLClassifier. Obsahuje zoznam atributov a operacii.
 * @author Viliam Holik
 */
public class UMLClass extends UMLClassifier {
    private boolean isAbstract;
    List<UMLAttribute> attrArray = new ArrayList<>();
    List<UMLOperation> operArray = new ArrayList<>();

    /**
     * Vytvori instanciu Class-y, Class-a nie je abstraktna
     * @param name nazov triedy
     */
    public UMLClass(java.lang.String name) {
        super(name);
        isAbstract = false;
    }

    /**
     * Vrati test ci je Class-a abstraktna alebo nie
     * @return pokial je trieda abstraktna vracia true, inak false
     */
    public boolean isAbstract() {
        return isAbstract;
    }

    /**
     * Zmeni informaciu objektu, ci je Class-a abstraktna alebo nie
     * @param isAbstract ci sa jedna o abstraktnu Class-u alebo nie
     */
    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    /**
     * Vlozi atribut do Class-y. Atribut sa vlozi nakoniec zoznamu, pokial uz Class-a obsahuje atribut s rovnakym nazvom neurobi nic
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
     * Vlozi operaciu do Class-y. Operacia sa vlozi na koniec zoznamu, pokial uz v Class-a obsahuje operaciu s rovnakym nazvom neurobi nic
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
     * Vrati poziciu atributu v zozname atributov, pozicia sa indexuje od 0, ak Class-a neobsahuje dany atribut vracia -1
     * @param attr instancia triedy atributu
     * @return index najdeneho atributu, indexuje sa od 0
     */
    public int getAttrPosition(UMLAttribute attr) {
        return attrArray.indexOf(attr);
    }

    /**
     * Presunie atribut na zadanu poziciu, pozicia sa indexuje od 0. Pokial Class-a neobsahuje dany atribut, nic sa nerobi a vracia -1. Pri presune na zadanu poziciu sa nasledujuce atributy v zozname posunu o 1 poziciu doprava
     * @param attr presunovany atribut
     * @param pos nova pozicia
     * @return uspech operacie
     */
    public int moveAttrAtPosition(UMLAttribute attr, int pos) {
        if (!attrArray.remove(attr)) {
            return -1;
        }
        attrArray.add(pos, attr);
        return 0;
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
