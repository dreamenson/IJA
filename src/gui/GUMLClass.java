package gui;

import back.UMLAttribute;
import back.UMLClass;
import back.UMLOperation;
import javafx.scene.layout.VBox;

public class GUMLClass {
    private UMLClass umlClass;
    private VBox attrBox;
    private VBox operBox;

    public GUMLClass(UMLClass umlClass, VBox attrBox, VBox operBox) {
        this.umlClass = umlClass;
        this.attrBox = attrBox;
        this.operBox = operBox;
        loadAttributes();
        loadOperations();
    }

    private void loadAttributes() {
        umlClass.getAttributes().forEach( (a) -> {
            new GUMLAttribute(a, attrBox);
        });
    }

    private void loadOperations() {
        umlClass.getOperations().forEach( (o) -> {
            new GUMLOperation(o, operBox);
        });
    }

    public void addAttribute(UMLAttribute ua) {
        new GUMLAttribute(ua, attrBox);
    }

    public void addOperation(UMLOperation uo) {
        new GUMLOperation(uo, operBox);
    }
}
