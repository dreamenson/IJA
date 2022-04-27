//author Pavol Babjak - xbabja03

package gui;

import back.*;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;
    public ClassDiagram classd;
    private double startDragX;
    private double startDragY;

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
        editMenu.getItems().addAll(Undo, Redo);
        insertMenu.getItems().addAll(newClass, AssocationRelationship, GeneralizationRelationship, InheritanceRelationship, RealizationRelationship, AggregationRelationship, CompositionRelationship);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu, insertMenu, helpMenu, exitMenu);

        //classd.getClassList().forEach( (n) -> {
        //    //Text text = new Text(n.getName());
        //  stack.getChildren().addAll(rect, line);
        //  });

        // BorderPane root = new BorderPane();
        // root.setBottom(menuBar);

        //StackPane stack = new StackPane();
        //root.setCenter(stack);

        //StackPane stack = new StackPane();

        Pane rootpane = new Pane();

        ///menuBar.setLayoutX(100);
        //menuBar.setLayoutY(100);

        //menuBar.relocate(200,200);

        //menuBar.relocate().bind(pane.widthProperty());

        BorderPane menubarpane = new BorderPane();
        menubarpane.setTop(menuBar);
        rootpane.getChildren().add(menubarpane);


        //rootpane.getChildren().addAll(menuBar);

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir"), "data"));
        openFile.setOnAction(e -> {
            File selectedFile = fc.showOpenDialog(stage);
            String path = selectedFile.getAbsolutePath();
            FileHandler fh = new FileHandler(path);
            fh.read();
            classd = fh.getClassDiagram();
           // rootpane.getChildren().clear();
         //   rootpane.getChildren().add(menuBar);

            //maxLeght(0,classd);
            drawTable(rootpane, 1, classd);

            //System.out.println("The size of the ArrayList is: " + classd.getClassList().size());


            classd.getClassList().forEach((n) ->
            {
                //System.out.println("Pes " + n.getName());
                Text text = new Text(n.getName());
                text.setX(111);
                text.setY(50);
                //stack.getChildren().addAll(text);
            });

        });

        Scene scene = new Scene(rootpane, 600, 600);
        scene.getStylesheets().add("css.css");

        stage.setScene(scene);
        stage.show();

        ChangeListener stageSizeListener = (observable, oldValue, newValue) -> {

            System.out.println("Height: " + stage.getHeight() + " Width: " + stage.getWidth());
            menuBar.relocate(stage.getWidth() - 600, stage.getHeight() - 600);
        };

        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);

        //menuBar.widthProperty().addListener(stageSizeListener);
        //menuBar.heightProperty().addListener(stageSizeListener);




    }

    public static void main() {
        launch();
    }

    public static AtomicInteger maxLeght(int classcount, ClassDiagram classd) {
        System.out.println("vosiel som do cyklusu");
        //cyklus na zistenie najdlhsej veci z tabulky

        String name = classd.getClassList().get(classcount).toString();
        name = name.substring(0, name.length() - 6);
        //System.out.println("name is: " + name);

        AtomicInteger maxLenght = new AtomicInteger(0);

        //System.out.println("Class for test is: " + classd.getClassList().get(classcount).getName());

        if (classd.getClassList().get(classcount).getName().length() > maxLenght.get()) {
            maxLenght.set(classd.getClassList().get(classcount).getName().length());
        }

        classd.getClassList().get(classcount).getAttributes().forEach((a) ->
        {
            String tmp = a.getType().getName() + ":" + a.getName();
            if (tmp.length() > maxLenght.get()) {
                maxLenght.set(tmp.length());
            }
        });

        classd.getClassList().get(classcount).getOperations().forEach((o) ->
        {
            String tmp = o.getType().getName() + ":" + o.getName() +"()";
            if (tmp.length() > maxLenght.get()) {
                maxLenght.set(tmp.length());
            }
            o.getArguments().forEach((a) ->
            {
                if (a.getType().getName().length() > maxLenght.get()) {
                    maxLenght.set(a.getType().getName().length());
                }
            });

            //maxLenght.set(0);
        });
        System.out.println("Maxleghth je: " + maxLenght);

        return maxLenght;
    }


    public static void drawTable(Pane rootpane, int classcount, ClassDiagram classd) {
        Pane table = new Pane();
        String className = classd.getClassList().get(classcount).getName();
        int maxLenght = maxLeght(classcount, classd).intValue();
        if (maxLenght < 10)
        {
            maxLenght = 10;
        }

        int attrCount = classd.getClassList().get(classcount).getAttributes().size();
        if (attrCount == 0)
        {
            attrCount=1;
        }
        int operCount = classd.getClassList().get(classcount).getOperations().size();
        if (operCount == 0)
        {
            operCount = 1;
        }
        int totalCount = attrCount + operCount;
        //System.out.println("Maxleghth classcountu " + classcount +" je: "+ maxLenght);
        Region rect = new Region(); //instantiating Rectangle
        int startX = 100;
        int startY = 100;
        rect.setLayoutX(startX); //setting the X coordinate of upper left //corner of rectangle
        rect.setLayoutY(startY); //setting the Y coordinate of upper left //corner of rectangle
        rect.setPrefWidth(maxLenght * 10); //setting the width of rectangle
        //rect.setPrefHeight(startY + 30 + 20 * totalCount); // setting the height of rectangle
        //rect.setFill(Color.WHITE);
        //rect.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 1; -fx-border-color: black; -fx-min-width: 100; -fx-min-height:100; -fx-max-width:100; -fx-max-height: 100;");
        Line line = new Line(); //instantiating Line class
        line.setStartX(startX); //setting starting X point of Line
        line.setStartY(startY + 30); //setting starting Y point of Line
        line.setEndX(startX + maxLenght * 10); //setting ending X point of Line
        line.setEndY(startY + 30); //setting ending Y point of Line
        Text classname = new Text(className);
        classname.setStyle("-fx-font-size: 15;-fx-font-weight: bold;");

        classname.setX(startX + 5 + (maxLenght * 10 / 2) - ((className.length() * 10) / 2));
        //text.setX(startX + maxLenght*10 - ((className.length()*10) / 2));
        //text.setX(100);
        classname.setY(startY + 20);

        Line line2 = new Line(); //instantiating Line class
        line2.setStartX(startX); //setting starting X point of Line
        line2.setStartY(startY + 30 + attrCount * 20); //setting starting Y point of Line
        line2.setEndX(startX + maxLenght * 10); //setting ending X point of Line
        line2.setEndY(startY + 30 + attrCount * 20); //setting ending Y point of Line
        int newlinesadded=0;

        System.out.println("attrcount je " + attrCount);

        if (classd.getClassList().get(classcount).getAttributes().size() > 0 || classd.getClassList().get(classcount).getOperations().size() > 0 )
        {

        Pane attributes = new Pane();
        Pane operations = new Pane();

        for (int i = 0; i < attrCount; i++)
        {
            //Text text = new Text(classd.getClassList().get(1).getAttributes().getType().getName());
            //Text text2 = new Text(classd.getClassList().get(0).getAttributes().get(1).getType().getName() + ":" + classd.getClassList().get(0).getAttributes().get(1).getName());
            //classd.getClassList().get(1).getAttributes().get(i).getType().getName();
            //classd.getClassList().get(1).getAttributes().forEach( (a) ->
            // String type = a.getType().getName();
            // System.out.println(type + ":" + a.getName());

            Text text = new Text();
            text.setText(classd.getClassList().get(0).getAttributes().get(i).getType().getName() + ":" + classd.getClassList().get(0).getAttributes().get(i).getName());
            //System.out.println(classd.getClassList().get(0).getAttributes().get(i).getType().getName() + ":" + classd.getClassList().get(0).getAttributes().get(i).getName() );
            text.setStyle("-fx-font-size: 15;-fx-font-weight: normal;");
            text.setX(startX + 2);
            text.setY(startY + 25 + ((i + 1) * 20));
            attributes.getChildren().add(0, text);

            // text.setStyle("-fx-font-size: 15;-fx-font-weight: bold;");
            //text.setX(startX);
            //text.setX(startX + maxLenght*10 - ((className.length()*10) / 2));
            //text.setX(100);
            // text.setY(startY+20+(i*20));
            //pane.getChildren().addAll(text);
        }



        //pane.getChildren().addAll(rect,line,line2,classname,attributes);
        //line,line2,classname,attributes
        //pane.getChildren().addAll(rect, line, line2, classname);


    for (int i = 0; i < operCount; i++)
    {
        //Text text = new Text(classd.getClassList().get(1).getAttributes().getType().getName());
        //Text text2 = new Text(classd.getClassList().get(0).getAttributes().get(1).getType().getName() + ":" + classd.getClassList().get(0).getAttributes().get(1).getName());
        //classd.getClassList().get(1).getAttributes().get(i).getType().getName();
        //classd.getClassList().get(1).getAttributes().forEach( (a) ->
        // String type = a.getType().getName();
        // System.out.println(type + ":" + a.getName());

        Text text = new Text();
        String params = new String();
        String textonnewline = new String();
        String name = classd.getClassList().get(0).getOperations().get(i).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getName() +"(";
        int paramscount= classd.getClassList().get(0).getOperations().get(i).getArguments().size();
        if (paramscount==0)
        {
            params = ")";
           // newlinesadded=newlinesadded+1;
        }
        else
        {



        for (int m = 0; m < paramscount; m++)
        {
            //System.out.println("cyklus prebieha pre opercount " + i + " paramscount " + m);
            if (m==0)
            {
                if ((classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getName() +"(").length() *10 + name.length() * 10 > maxLenght * 10 )
                {
                    params = params + "\n" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getName() +",";
                    newlinesadded = newlinesadded+1;
                    textonnewline = classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getName() +",";
                }
                else
                {
                    params = params + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getName() +",";
                }
            }
            else
            {

                if (textonnewline.length()*10 + (classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getName() +",").length()*10 > maxLenght * 10)
                {
                    params = params + "\n" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getName() +",";
                    newlinesadded = newlinesadded+1;
                    textonnewline = classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getName() +",";
                }
                else
                {
                    params = params + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getName() +",";
                    textonnewline = classd.getClassList().get(0).getOperations().get(i).getArguments().get(m-1).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m-1).getName() + "," + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(0).getOperations().get(i).getArguments().get(m).getName() +",";
                }

            }

            if (m+1==paramscount)
            {
            params = params.substring(0, params.length() - 1) + ")";
            }


           // params = params + classd.getClassList().get(3).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + classd.getClassList().get(3).getOperations().get(i).getArguments().get(m).getName() +" ";
        }

        }



        //classd.getClassList().get(0).getOperations().get(i).getArguments().forEach( (a) ->
        //{
        //params.add(a.getType().getName() + ":" + a.getName());
       // });
        //System.out.println("params je " + params);


        //if (string.length() *15 +  > maxLenght * 10)

        //System.out.println("paramscount je" + paramscount);
        //String params =
        //System.out.println(classd.getClassList().get(0).getAttributes().get(i).getType().getName() + ":" + classd.getClassList().get(0).getAttributes().get(i).getName() );
        text.setText(name+params);
        text.setStyle("-fx-font-size: 15;-fx-font-weight: normal;");
        text.setX(startX+2);
        if (i==0)
        {
            text.setY(startY+(attrCount)*20+25+((i+1)*20));
        }
        else
        {
            if (paramscount==0)
            {
                text.setY(startY+(attrCount+newlinesadded)*20+25+((i+1)*20));
            }
            else
            {
                text.setY(startY+(attrCount+newlinesadded-(1))*20+25+((i+1)*20));
            }

        }

        operations.getChildren().add(0,text);

        //};

        // text.setStyle("-fx-font-size: 15;-fx-font-weight: bold;");

        //text.setX(startX);
        //text.setX(startX + maxLenght*10 - ((className.length()*10) / 2));
        //text.setX(100);
        // text.setY(startY+20+(i*20));
        //pane.getChildren().addAll(text);

    }


    //pane.getChildren().addAll(rect,line,line2,classname,attributes);

        table.getChildren().add(0,operations);
        table.getChildren().add(0,attributes);

            /*pane.setOnMousePressed(e -> {
                startDragX = e.getSceneX();
                startDragY = e.getSceneY();
            });

            pane.setOnMouseDragged(e -> {
                pane.setTranslateX(e.getSceneX() - startDragX);
                pane.setTranslateY(e.getSceneY() - startDragY);
            });*/

        }
        table.getChildren().add(0,classname);
        table.getChildren().add(0,line2);
        table.getChildren().add(0,line);

        rect.setPrefHeight(30 + 20 * (totalCount+newlinesadded)); // setting the height of rectangle
        //System.out.println("totalY je " + (startY + 30 + 20 * (totalCount+newlinesadded)));
        //System.out.println("totalcount je " + totalCount);
       // System.out.println("newlinesadded " + newlinesadded);

        table.getChildren().add(0,rect);

        rootpane.getChildren().add(table);

        //pane.setOnMouseClicked((MouseEvent event) -> {
         //   System.out.println("mouseClicked");
       // });

    //line,line2,classname,attributes

}

}
