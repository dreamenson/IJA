package gui;

import back.UMLAttribute;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class GUMLAttribute {
    private UMLAttribute umlAttribute;
    private Label wholeAttr;
    private VBox attrBox;
    private TextField textField = new TextField();
    private int pos;

    public GUMLAttribute(UMLAttribute a, VBox attrBox) {
        umlAttribute = a;
        this.attrBox = attrBox;
        wholeAttr = new Label(umlAttribute.toString());
        wholeAttr.setOnMouseClicked(this::rename);
        textField.setOnAction(this::handleText);
        attrBox.getChildren().add(wholeAttr);
    }

    private void rename(MouseEvent event) {
        if (event.getClickCount()==2 && event.getButton() == MouseButton.PRIMARY) {
            textField.setText(wholeAttr.getText());
            pos = attrBox.getChildren().indexOf(wholeAttr);
            attrBox.getChildren().remove(wholeAttr);
            attrBox.getChildren().add(pos, textField);
            textField.selectAll();
            textField.requestFocus();
        }
    }

    private void handleText(ActionEvent event) {
        String newName = textField.getText();
        if (umlAttribute.renameWhole(newName)) {
            wholeAttr.setText(umlAttribute.toString());
            attrBox.getChildren().remove(textField);
            attrBox.getChildren().add(pos, wholeAttr);
        } else {
            attrBox.getChildren().remove(textField);
            attrBox.getChildren().add(pos, wholeAttr);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Operaciu sa nepodarilo vykonat!\nAtribut musi byt v tvare \"[+-#~]typ nazov\"\nnapr. +String name");
            a.show();
        }
    }
}
