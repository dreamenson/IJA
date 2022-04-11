package gui;

import java.awt.*;
import java.applet.*;
import back.FileHandler;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import java.io.File;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {
        stage.setTitle("IJA - project 2022");
        //StackPane pane = new StackPane();
        //Button btn = new Button("Import file");
        //FileChooser fc = new FileChooser();
        //fc.setInitialDirectory(new File(System.getProperty("user.dir"), "data"));
        //btn.setOnAction(e-> {
       //     File selectedFile = fc.showOpenDialog(stage);
        //    String path = selectedFile.getAbsolutePath();
        //    FileHandler fh = new FileHandler(path);
       //     fh.read();
       // });
        //boolean add = pane.getChildren().add(btn);
        //scene = new Scene(pane, 1000, 640);
        //stage.setScene(scene);
        //stage.show();

        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu insertMenu = new Menu("Insert");
        Menu helpMenu = new Menu("Help");
        Menu exitMenu = new Menu("Exit");

        // Create MenuItems
        MenuItem newFile = new MenuItem("New file");

        //Image img = new Image("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\new.png");
        //ImageView view = new ImageView(img);

        newFile.setGraphic(new ImageView("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\new.png"));

        MenuItem openFile = new MenuItem("Open File");
        openFile.setGraphic(new ImageView("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\open_file.png"));

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir"), "data"));
        openFile.setOnAction(e-> {
            File selectedFile = fc.showOpenDialog(stage);
            String path = selectedFile.getAbsolutePath();
            FileHandler fh = new FileHandler(path);
            fh.read();
        });

        MenuItem saveFile = new MenuItem("Save File");
        saveFile.setGraphic(new ImageView("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\save_file.png"));

        MenuItem Undo = new MenuItem("Undo");
        Undo.setGraphic(new ImageView("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\undo.png"));

        MenuItem Redo = new MenuItem("Redo");
        Redo.setGraphic(new ImageView("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\redo.png"));

        MenuItem newClass = new MenuItem("Class");
        newClass.setGraphic(new ImageView("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\class.png"));
        MenuItem newRelationship = new MenuItem("Relationship");

        MenuItem AssocationRelationship = new MenuItem("Assocation");
        AssocationRelationship.setGraphic(new ImageView("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\association.png"));
        MenuItem GeneralizationRelationship = new MenuItem("Generalization");
        GeneralizationRelationship.setGraphic(new ImageView("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\generalization.png"));
        MenuItem InheritanceRelationship = new MenuItem("Inheritance");
        InheritanceRelationship.setGraphic(new ImageView("file:C:\\Users\\Pavol\\IdeaProjects\\IJA\\src\\gui\\graphics\\realization.png"));
        MenuItem RealizationRelationship = new MenuItem("Realization");
        MenuItem AggregationRelationship = new MenuItem("Aggregation");
        MenuItem CompositionRelationship = new MenuItem("Composition");


        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newFile, openFile, saveFile);
        editMenu.getItems().addAll(Undo,Redo);
        insertMenu.getItems().addAll(newClass, AssocationRelationship, GeneralizationRelationship, InheritanceRelationship, RealizationRelationship,AggregationRelationship, CompositionRelationship);


        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu, insertMenu, helpMenu, exitMenu);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        Scene scene = new Scene(root, 1000, 640);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}