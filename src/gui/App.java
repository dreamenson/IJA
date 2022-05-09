package gui;

import back.FileHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 *
 * @author Viliam Holik
 */
public class App extends Application {
    private Scene scene;
    private Stage stage;
    private FileHandler fileHandler;

    public static void main() {
        launch();
    }

    @Override
    public void start(Stage st) {
        stage = st;
        stage.setTitle("IJA - project 2022");
        MenuBar menuBar = createMenuBar();

        VBox vbox = new VBox(menuBar);
        scene = new Scene(vbox, 1200, 700);
        scene.getStylesheets().add("css.css");

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");
        Menu exitMenu = new Menu("Exit");

        MenuItem newFile = new MenuItem("New file");
        newFile.setGraphic(new ImageView("file:src/gui/graphics/new.png"));
        MenuItem openFile = new MenuItem("Open File");
        openFile.setGraphic(new ImageView("file:src/gui/graphics/open_file.png"));
        MenuItem saveFile = new MenuItem("Save File");
        saveFile.setGraphic(new ImageView("file:src/gui/graphics/save_file.png"));

        openFileMenu(openFile);
        saveFileMenu(saveFile);

        MenuItem Undo = new MenuItem("Undo");
        Undo.setGraphic(new ImageView("file:src/gui/graphics/undo.png"));
        MenuItem Redo = new MenuItem("Redo");
        Redo.setGraphic(new ImageView("file:src/gui/graphics/redo.png"));

        // pridanie MenuItem do Menu
        fileMenu.getItems().addAll(newFile, openFile, saveFile);
        editMenu.getItems().addAll(Undo, Redo);

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu, exitMenu);
        return menuBar;
    }

    private void openFileMenu(MenuItem openFile) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir"), "data"));

        openFile.setOnAction(e -> {
            fileHandler = null;
            File selectedFile = fc.showOpenDialog(stage);
            String path = selectedFile.getAbsolutePath();
            fileHandler = new FileHandler(path);
            fileHandler.read();
        });
    }

    private void saveFileMenu(MenuItem saveFile) {
        FileChooser fc = new FileChooser();
        fc.setInitialFileName("UMLexample.txt");

        saveFile.setOnAction( e -> {
            if (fileHandler != null) {
                File selectedFile = fc.showSaveDialog(stage);
                String path = selectedFile.getAbsolutePath();
                fileHandler.write(path);
            }
        });
    }
}
