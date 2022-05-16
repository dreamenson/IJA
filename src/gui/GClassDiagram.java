package gui;

import back.ClassDiagram;
import back.FileHandler;
import back.UMLClassifier;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GClassDiagram {
    private final ClassDiagram classDiagram;
    private final String name;
    private Tab tab;
    private Pane mainPane;
    private List<GUMLRelation> gumlRelations = new ArrayList<>();

    public GClassDiagram(ClassDiagram classDiagram, TabPane tabPane) {
        this.classDiagram = classDiagram;
        classDiagram.addG(this);
        name = classDiagram.getName();
        createTab(tabPane);
        handleContent();
    }

    private void handleContent() {
        classDiagram.getClassList().forEach( (n) -> {
            new GUMLClassifier(n, mainPane, this);
        });
        classDiagram.getRelationList().forEach( (r) -> {
            GUMLRelation gr =  new GUMLRelation(r, mainPane);
            gumlRelations.add(gr);
        });
    }

    private void createTab(TabPane tabPane) {
        mainPane = new Pane();
        createLegend();
        tab = new Tab(name, mainPane);
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    public void newClass() {
        UMLClassifier umlClassifier = classDiagram.createClass("Untitled");
        new GUMLClassifier(umlClassifier, mainPane, this);
    }

    public List<GUMLRelation> getRelationList() {
        return gumlRelations;
    }

    private void createLegend() {
        VBox vb = new VBox(new Label("Type of relation"), new Separator());
        vb.getStyleClass().add("legend");
        vb.getChildren().add(new Label("\t\tgeneralization"));
        vb.getChildren().add(new Label("\t\tassociation"));
        vb.getChildren().add(new Label("\t\taggregation"));
        vb.getChildren().add(new Label("\t\tcomposition"));
        vb.setLayoutY(620);
        vb.setLayoutX(20);
        Line red = new Line(35, 663, 75, 663);
        red.getStyleClass().add("red");
        Line blue = new Line(35, 680, 75, 680);
        blue.getStyleClass().add("blue");
        Line green = new Line(35, 697, 75, 697);
        green.getStyleClass().add("green");
        Line yellow = new Line(35, 714, 75, 714);
        yellow.getStyleClass().add("yellow");
        mainPane.getChildren().addAll(vb, red, blue, green, yellow);
    }

    public void removeGClass(UMLClassifier umlClassifier) {
        Iterator<GUMLRelation> i = gumlRelations.iterator();
        while (i.hasNext()) {
            GUMLRelation gr = i.next();
            if (gr.getUmlRelation().getFirstClass() == umlClassifier || gr.getUmlRelation().getSecondClass() == umlClassifier) {
                gr.delLine();
                i.remove();
            }
        }
    }

    public void removeGRelation(GUMLRelation gumlRelation) {
        gumlRelations.remove(gumlRelation);
    }

    public Pane getMainPane() {
        return mainPane;
    }
}
