package gui;

import back.UMLClass;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class GParticipant {
    private UMLClass umlClass;
    private Label particip;
    private Line line;

    public GParticipant(UMLClass p, Pane mainPane) {
        umlClass = p;
        makeParticipant(mainPane);
    }

    private void makeParticipant(Pane mainPane) {
        particip = new Label(umlClass.getName());
        particip.getStyleClass().add("participant");
        particip.setOnMouseDragged(this::drag);
        particip.setLayoutY(30);
        line = new Line();
        line.setStartX(50);
        line.setStartY(70);
        line.setEndY(720);
        line.setEndX(50);
        mainPane.getChildren().addAll(line, particip);
    }

    private void drag(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Node n = (Node) event.getSource();
            n.setTranslateX(n.getTranslateX() + event.getX());
            n.toFront();
            redrawLine();
        }
    }

    public void redrawLine() {
        double x = particip.getTranslateX() + particip.getWidth() / 2;
        line.setStartX(x);
        line.setEndX(x);
    }

    public void refresh() {
        particip.setText(umlClass.getName());
        redrawLine();
    }
}
