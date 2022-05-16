package gui;

import back.FileHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Viliam Holik
 */
public class App extends Application {
    private Scene scene;
    private Stage stage;
    private FileHandler fileHandler;
    private TabPane tabPane;
    private GClassDiagram gClassDiagram;
    private List<GSequenceDiagram> gSequenceDiagramList = new ArrayList<>();

    public static void main() {
        launch();
    }

    @Override
    public void start(Stage st) {
        stage = st;
        stage.setTitle("IJA - project 2022");
        MenuBar menuBar = createMenuBar();
        HBox mainGUI = createmainGUI();

        VBox vbox = new VBox(menuBar, mainGUI);
        scene = new Scene(vbox, 1200, 700);
        scene.getStylesheets().add("css.css");

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private HBox createmainGUI() {
        tabPane = new TabPane();
        GridPane gridPane = new GridPane();
        HBox hb = new HBox(tabPane, gridPane);
        hb.setFillHeight(true);
        return hb;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");
        Menu exitMenu = new Menu();
        Label exitItem = new Label("Exit");
        exitMenu.setGraphic(exitItem);

        MenuItem newFile = new MenuItem("New file");
        newFile.setGraphic(new ImageView("file:src/gui/graphics/new.png"));
        MenuItem openFile = new MenuItem("Open file");
        openFile.setGraphic(new ImageView("file:src/gui/graphics/open_file.png"));
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setGraphic(new ImageView("file:src/gui/graphics/save_file.png"));
        MenuItem saveAs = new MenuItem("Save as");
        saveAs.setGraphic(new ImageView("file:src/gui/graphics/save_file.png"));

        openFileMenu(openFile);
        saveFileMenu(saveFile);
        saveAsMenu(saveAs);

        exitItem.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });

        MenuItem Undo = new MenuItem("Undo");
        Undo.setGraphic(new ImageView("file:src/gui/graphics/undo.png"));

//        MenuItem Redo = new MenuItem("Redo");
//        Redo.setGraphic(new ImageView("file:src/gui/graphics/redo.png"));
        MenuItem newClass = new MenuItem("New Class");
        newClass.setOnAction(this::newClass);
        MenuItem newRelation = new MenuItem("New Relation");

        // pridanie MenuItem do Menu
        fileMenu.getItems().addAll(newFile, openFile, saveFile, saveAs);
        editMenu.getItems().addAll(Undo, newClass, newRelation);

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu, exitMenu);
        return menuBar;
    }

    private void newClass(ActionEvent actionEvent) {
        if (gClassDiagram != null) {
            gClassDiagram.newClass();
        }
    }

    private void openFileMenu(MenuItem openFile) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir"), "data"));

        openFile.setOnAction(e -> {
            File selectedFile = fc.showOpenDialog(stage);
            String path = selectedFile.getAbsolutePath();
            tabPane.getTabs().clear();
            fileHandler = FileHandler.createInstance(path);
            fileHandler.read();
            handleLoaded();
        });
    }

    private void handleLoaded() {
        gClassDiagram = new GClassDiagram(fileHandler.getClassDiagram(), tabPane);
        fileHandler.getSequenceDiagram().forEach( sq -> {
            gSequenceDiagramList.add(new GSequenceDiagram(sq, tabPane));
        });
    }

    private void saveFileMenu(MenuItem saveFile) {
        FileChooser fc = new FileChooser();
        fc.setInitialFileName("UMLexample.txt");

        saveFile.setOnAction( e -> {
            if (fileHandler != null) {
                fileHandler.write();
            }
        });
    }

    private void saveAsMenu(MenuItem saveAs) {
        FileChooser fc = new FileChooser();
        fc.setInitialFileName("UMLexample.txt");

        saveAs.setOnAction( e -> {
            if (fileHandler != null) {
                File selectedFile = fc.showSaveDialog(stage);
                String path = selectedFile.getAbsolutePath();
                fileHandler.setPath(path);
                fileHandler.write();
            }
        });
    }

}
