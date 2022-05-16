package gui;

import back.FileHandler;
import back.UMLRelation;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GUMLRelation {
    private UMLRelation umlRelation;
    private Line relation;
    private Circle direction;
    private Pane mainPane;

    public GUMLRelation(UMLRelation r, Pane mainPane) {
        umlRelation = r;
        this.mainPane = mainPane;
        relation = new Line();
        relation.setOnMouseClicked(this::remove);
        direction = new Circle();
        switch (umlRelation.getRelation()) {
            case 1:
                relation.getStyleClass().add("red");
                direction.getStyleClass().add("red");
                break;
            case 2:
                relation.getStyleClass().add("blue");
                direction.getStyleClass().add("blue");
                break;
            case 3:
                relation.getStyleClass().add("green");
                direction.getStyleClass().add("green");
                break;
            case 4:
                relation.getStyleClass().add("yellow");
                direction.getStyleClass().add("yellow");
                break;
        };
        relation.setStyle("-fx-stroke-width: 2px;");
        relation.toBack();
        GUMLClassifier first = umlRelation.getFirstClass().getG();
        GUMLClassifier second = umlRelation.getSecondClass().getG();
        relation.setStartX(first.getClassBox().getTranslateX() + 75);
        relation.setStartY(first.getClassBox().getTranslateY() + 30);
        relation.setEndX(second.getClassBox().getTranslateX() + 75);
        relation.setEndY(second.getClassBox().getTranslateY() + 30);
        mainPane.getChildren().addAll(relation, direction);
    }

    private void remove(MouseEvent event) {
        if (event.getClickCount()==2 && event.getButton() == MouseButton.SECONDARY) {
            mainPane.getChildren().remove(relation);
            mainPane.getChildren().remove(direction);
            FileHandler.getInstance().getClassDiagram().removeRelation(umlRelation);
            FileHandler.getInstance().getClassDiagram().getG().removeGRelation(this);
        }
    }

    public void redraw() {
        GUMLClassifier first = umlRelation.getFirstClass().getG();
        GUMLClassifier second = umlRelation.getSecondClass().getG();
        double firstX = first.getClassBox().getTranslateX() + 75;
        double firstY = first.getClassBox().getTranslateY() + 50;
        double secondX = second.getClassBox().getTranslateX() + 75;
        double secondY = second.getClassBox().getTranslateY() + 50;
        double subX = Math.abs(firstX - secondX);
        double subY = Math.abs(firstY - secondY);
        int pos = 0;
        if (firstX < secondX) {
            if (firstY < secondY) {
                if (subX < subY) {
                    pos = 1;
                } else {
                    pos = 4;
                }
            } else {
                if (subX < subY) {
                    pos = 3;
                } else {
                    pos = 4;
                }
            }
        } else {
            if (firstY < secondY) {
                if (subX < subY) {
                    pos = 1;
                } else {
                    pos = 2;
                }
            } else {
                if (subX < subY) {
                    pos = 3;
                } else {
                    pos = 2;
                }
            }
        }

        double startX = 0;
        double startY = 0;
        double endX = 0;
        double endY = 0;

        firstX -= 75;
        firstY -= 50;
        secondX -= 75;
        secondY -= 50;

        switch (pos) {
            case 1:
                startX = firstX + (first.getClassBox().getWidth() / 2);
                startY = firstY + first.getClassBox().getHeight();
                endX = secondX + (second.getClassBox().getWidth() / 2);
                endY = secondY;
                break;
            case 2:
                startX = firstX;
                startY = firstY + (first.getClassBox().getHeight() / 2);
                endX = secondX + second.getClassBox().getWidth();
                endY = secondY + (second.getClassBox().getHeight() / 2);
                break;
            case 3:
                startX = firstX + (first.getClassBox().getWidth() / 2);
                startY = firstY;
                endX = secondX + (second.getClassBox().getWidth() / 2);
                endY = secondY + second.getClassBox().getHeight();
                break;
            case 4:
                startX = firstX + first.getClassBox().getWidth();
                startY = firstY + (first.getClassBox().getHeight() / 2);
                endX = secondX;
                endY = secondY + (second.getClassBox().getHeight() / 2);
                break;
        }

        direction.setCenterX(startX);
        direction.setCenterY(startY);
        direction.setRadius(10);

        relation.setStartX(startX);
        relation.setStartY(startY);
        relation.setEndX(endX);
        relation.setEndY(endY);
    }

    public UMLRelation getUmlRelation() {
        return umlRelation;
    }

    public void delLine() {
        mainPane.getChildren().removeAll(relation, direction);
    }
}
