//author Pavol Babjak - xbabja03

package gui;

import back.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;


import java.io.File;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private ClassDiagram classd;

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

        newFile.setGraphic(new ImageView("file:src/gui/graphics/new.png"));

        MenuItem openFile = new MenuItem("Open File");
        openFile.setGraphic(new ImageView("file:src/gui/graphics/open_file.png"));

        MenuItem saveFile = new MenuItem("Save File");
        saveFile.setGraphic(new ImageView("file:src/gui/graphics/save_file.png"));

        MenuItem Undo = new MenuItem("Undo");
        Undo.setGraphic(new ImageView("file:src/gui/graphics/undo.png"));

        MenuItem Redo = new MenuItem("Redo");
        Redo.setGraphic(new ImageView("file:src/gui/graphics/redo.png"));

        MenuItem newClass = new MenuItem("Class");
        newClass.setGraphic(new ImageView("file:src/gui/graphics/class.png"));
        MenuItem newRelationship = new MenuItem("Relationship");

        MenuItem AssocationRelationship = new MenuItem("Assocation");
        AssocationRelationship.setGraphic(new ImageView("file:src/gui/graphics/association.png"));
        MenuItem GeneralizationRelationship = new MenuItem("Generalization");
        GeneralizationRelationship.setGraphic(new ImageView("file:src/gui/graphics/generalization.png"));
        MenuItem InheritanceRelationship = new MenuItem("Inheritance");
        InheritanceRelationship.setGraphic(new ImageView("file:src/gui/graphics/realization.png"));
        MenuItem RealizationRelationship = new MenuItem("Realization");
        MenuItem AggregationRelationship = new MenuItem("Aggregation");
        MenuItem CompositionRelationship = new MenuItem("Composition");


        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newFile, openFile, saveFile);
        editMenu.getItems().addAll(Undo,Redo);
        insertMenu.getItems().addAll(newClass, AssocationRelationship, GeneralizationRelationship, InheritanceRelationship, RealizationRelationship,AggregationRelationship, CompositionRelationship);


        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu, insertMenu, helpMenu, exitMenu);

        Region rect=new Region(); //instantiating Rectangle
        //rect.setX(100); //setting the X coordinate of upper left //corner of rectangle
        //rect.setY(100); //setting the Y coordinate of upper left //corner of rectangle
        //rect.setWidth(100); //setting the width of rectangle
        //rect.setHeight(100); // setting the height of rectangle
        //rect.setFill(Color.WHITE);
        //rect.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 1; -fx-border-color: black; -fx-min-width: 100; -fx-min-height:100; -fx-max-width:100; -fx-max-height: 100;");
        Line line = new Line(); //instantiating Line class
        line.setStartX(0); //setting starting X point of Line
        line.setStartY(0); //setting starting Y point of Line
        line.setEndX(100); //setting ending X point of Line
        line.setEndY(0); //setting ending Y point of Line

        StackPane stack = new StackPane();

        //classd.getClassList().forEach( (n) -> {
        //    //Text text = new Text(n.getName());
            stack.getChildren().addAll(rect, line);
      //  });

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(stack);

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir"), "data"));
        openFile.setOnAction(e-> {
            File selectedFile = fc.showOpenDialog(stage);
            String path = selectedFile.getAbsolutePath();
            FileHandler fh = new FileHandler(path);
            fh.read();
            classd = fh.getClassDiagram();
            //StackPane stack = new StackPane();
            //classd.getClassList().forEach( (n) -> {System.out.println("Pes " + n.getName());Text text = new Text(n.getName());stack.getChildren().addAll(text);});

        });

        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("css.css");

        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}