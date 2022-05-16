package gui;

import back.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class GUMLClassifier {
    private UMLClassifier umlClassifier;
    private VBox classBox;
    private VBox attrBox;
    private VBox operBox;
    private Label name;
    private GUMLClass gumlClass;
    private TextField textField = new TextField();
    private TextField attrField = new TextField();
    private TextField operField = new TextField();
    private GClassDiagram gClassDiagram;
    private Pane mainPane;

    public GUMLClassifier(UMLClassifier umlClassifier, Pane mainPane, GClassDiagram gClassDiagram) {
        this.mainPane = mainPane;
        this.gClassDiagram = gClassDiagram;
        this.umlClassifier = umlClassifier;
        umlClassifier.addG(this);
        createClassBox(mainPane);
        if (umlClassifier instanceof UMLClass) {
            gumlClass = new GUMLClass((UMLClass)umlClassifier, attrBox, operBox);
        } else {
            //new GUMLInterface((UMLInterface)umlClassifier, attrBox, operBox);
        }
    }

    private void createClassBox(Pane mainPane) {
        classBox = new VBox();
        classBox.getStyleClass().add("classBox");
        classBox.setOnMouseDragged(this::drag);

        attrBox = new VBox();
        attrBox.setOnMouseClicked(this::addAttr);
        attrField.setOnAction(this::handleAttr);
        attrBox.getStyleClass().add("infoBox");

        operBox = new VBox();
        operBox.setOnMouseClicked(this::addOper);
        operField.setOnAction(this::handleOper);
        operBox.getStyleClass().add("infoBox");

        name = new Label(umlClassifier.getName());
        name.setOnMouseClicked(this::handleClick);
        textField.setOnAction(this::handleText);
        name.getStyleClass().add("nameLabel");
        name.setAlignment(Pos.CENTER);
        classBox.getChildren().addAll(name, new Separator(), attrBox, new Separator(), operBox);
        mainPane.getChildren().add(classBox);
    }

    private void handleOper(ActionEvent actionEvent) {
        String oper = operField.getText();
        UMLClass umlClass = (UMLClass)umlClassifier;
        UMLOperation uo = umlClass.addNewOperation(oper);
        operBox.getChildren().remove(operField);
        if (uo == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Nespravny atribut!\nAtribut musi byt v tvare \"[+-#~]typ nazov\"\nnapr. +String name");
            a.show();
        } else {
            gumlClass.addOperation(uo);
        }
    }

    private void addOper(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            operField.setText("+int example(String a, int b)");
            operBox.getChildren().add(operField);
            operField.selectAll();
            operField.requestFocus();
        }
    }

    private void handleAttr(ActionEvent actionEvent) {
        String attr = attrField.getText();
        UMLClass umlClass = (UMLClass)umlClassifier;
        UMLAttribute ua = umlClass.addNewAttribute(attr);
        attrBox.getChildren().remove(attrField);
        if (ua == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Nespravny atribut!\nAtribut musi byt v tvare \"[+-#~]typ nazov\"\nnapr. +String name");
            a.show();
        } else {
            gumlClass.addAttribute(ua);
        }
    }

    private void addAttr(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            attrField.setText("+String example");
            attrBox.getChildren().add(attrField);
            attrField.selectAll();
            attrField.requestFocus();
        }
    }

    private void drag(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Node n = (Node) event.getSource();
            n.setTranslateX(n.getTranslateX() + event.getX());
            n.setTranslateY(n.getTranslateY() + event.getY());
            n.toFront();
            gClassDiagram.getRelationList().forEach(GUMLRelation::redraw);
        }
    }

    private void handleClick(MouseEvent event) {
        if (event.getClickCount()==2 && event.getButton() == MouseButton.PRIMARY) {
            textField.setText(name.getText());
            classBox.getChildren().remove(name);
            classBox.getChildren().add(0, textField);
            textField.selectAll();
            textField.requestFocus();
        } else if (event.getClickCount()==2 && event.getButton() == MouseButton.SECONDARY) {
            mainPane.getChildren().remove(classBox);
            removeLines();
            FileHandler fileHandler = FileHandler.getInstance();
            fileHandler.getClassDiagram().removeClass(umlClassifier);
            umlClassifier = null;
            classBox = null;
        }
    }

    private void removeLines() {
        GClassDiagram gcd = FileHandler.getInstance().getClassDiagram().getG();
        gcd.removeGClass(umlClassifier);
    }

    private void handleText(ActionEvent event) {
        String newName = textField.getText();
        name.setText(newName);
        umlClassifier.rename(newName);
        classBox.getChildren().remove(textField);
        classBox.getChildren().add(0, name);
    }

    public VBox getClassBox() {
        return classBox;
    }
}
