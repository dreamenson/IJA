package gui;

import back.UMLOperation;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class GUMLOperation {
    private UMLOperation umlOperation;
    private Label wholeOper;
    private TextField textField = new TextField();
    private VBox operBox;
    private int pos;

    public GUMLOperation(UMLOperation o, VBox operBox) {
        umlOperation = o;
        this.operBox = operBox;
        wholeOper = new Label(umlOperation.toString());
        wholeOper.setOnMouseClicked(this::rename);
        textField.setOnAction(this::handleText);
        operBox.getChildren().add(wholeOper);
    }

    private void rename(MouseEvent event) {
        if (event.getClickCount()==2 && event.getButton() == MouseButton.PRIMARY) {
            textField.setText(wholeOper.getText());
            pos = operBox.getChildren().indexOf(wholeOper);
            operBox.getChildren().remove(wholeOper);
            operBox.getChildren().add(pos, textField);
            textField.selectAll();
            textField.requestFocus();
        }
    }

    private void handleText(ActionEvent event) {
        String newName = textField.getText();
        if (umlOperation.renameWhole(newName)) {
            wholeOper.setText(umlOperation.toString());
            operBox.getChildren().remove(textField);
            operBox.getChildren().add(pos, wholeOper);
        } else {
            operBox.getChildren().remove(textField);
            operBox.getChildren().add(pos, wholeOper);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Operaciu sa nepodarilo vykonat!\nOperacia musi byt v tvare \"[+-#~]typ nazov(argumenty)\"\nnapr. +String rename(String newName)");
            a.show();
        }
    }
}
