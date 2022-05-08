//author Pavol Babjak - xbabja03

package gui;

import back.*;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.*;
import javafx.scene.control.*;
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
import javafx.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;
    public ClassDiagram classd;
    public List<SequenceDiagram> sqlist;
    //public List<SequenceDiagram> sqlist;
    //private double startDragX;
   // private double startDragY;

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
        Menu blankMenu = new Menu("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
        blankMenu.setDisable(true);

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
        menuBar.getMenus().addAll(fileMenu, editMenu, insertMenu, helpMenu, exitMenu,blankMenu);

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
            sqlist = fh.getSequenceDiagram();
            // rootpane.getChildren().clear();
            //   rootpane.getChildren().add(menuBar);

            maxLeght(0,classd);
            drawSeq(stage,rootpane,sqlist);
            //drawTable(rootpane, 0, classd);

            //System.out.println("The size of the ArrayList is: " + classd.getClassList().size());


        });

        Scene scene = new Scene(rootpane, 1200, 700);
        scene.getStylesheets().add("css.css");

        stage.setScene(scene);
        stage.setMaximized(true);
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
        //System.out.println("vosiel som do cyklusu");
        //cyklus na zistenie najdlhsej veci z tabulky

        String name = classd.getClassList().get(classcount).toString();
        name = name.substring(0, name.length() - 6);
        //System.out.println("name is: " + name);

        AtomicInteger maxLenght = new AtomicInteger(0);

        if (classd.getClassList().get(classcount) instanceof UMLClass)
        {

        //System.out.println("Class for test is: " + classd.getClassList().get(classcount).getName());

        if (((UMLClass)classd.getClassList().get(classcount)).getName().length() > maxLenght.get())
        {
            maxLenght.set(((UMLClass)classd.getClassList().get(classcount)).getName().length());
        }


        ((UMLClass) classd.getClassList().get(classcount)).getAttributes().forEach((a) ->
        {
            String tmp = a.getType().getName() + ":" + a.getName();
            if (tmp.length() > maxLenght.get()) {
                maxLenght.set(tmp.length());
            }
        });


        ((UMLClass)classd.getClassList().get(classcount)).getOperations().forEach((o) ->
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

        }
        if (classd.getClassList().get(classcount) instanceof UMLInterface)
        {
            if (((UMLInterface)classd.getClassList().get(classcount)).getName().length() > maxLenght.get())
            {
                maxLenght.set(((UMLInterface)classd.getClassList().get(classcount)).getName().length());
            }


            ((UMLInterface) classd.getClassList().get(classcount)).getAttributes().forEach((a) ->
            {
                String tmp = a.getType().getName() + ":" + a.getName();
                if (tmp.length() > maxLenght.get()) {
                    maxLenght.set(tmp.length());
                }
            });


            ((UMLInterface)classd.getClassList().get(classcount)).getOperations().forEach((o) ->
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

        }

        System.out.println("Maxleghth je: " + maxLenght);

        return maxLenght;
    }


    public static void drawTable(Pane rootpane, int classcount, ClassDiagram classd) {
        Pane table = new Pane();
        int attrCount = 0;
        int operCount = 0;
        String className = classd.getClassList().get(classcount).getName();
        System.out.println("Classname je " + className);
        int maxLenght = maxLeght(classcount, classd).intValue();
        if (maxLenght < 10)
        {
            maxLenght = 10;
        }

        if (classd.getClassList().get(classcount) instanceof UMLClass )
        {
            attrCount = ((UMLClass)classd.getClassList().get(classcount)).getAttributes().size();
            if (attrCount == 0)
            {
                attrCount=1;
            }
            operCount = ((UMLClass)classd.getClassList().get(classcount)).getOperations().size();
            if (operCount == 0)
            {
                operCount = 1;
            }
        }

        if (classd.getClassList().get(classcount) instanceof UMLInterface )
        {
            attrCount = ((UMLInterface)classd.getClassList().get(classcount)).getAttributes().size();
            if (attrCount == 0)
            {
                attrCount=1;
            }
            operCount = ((UMLInterface)classd.getClassList().get(classcount)).getOperations().size();
            if (operCount == 0)
            {
                operCount = 1;
            }
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

        //System.out.println("attrcount je " + attrCount);

        if (((UMLClass)classd.getClassList().get(classcount)).getAttributes().size() > 0 || ((UMLClass)classd.getClassList().get(classcount)).getOperations().size() > 0 )
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
            text.setText(((UMLClass)classd.getClassList().get(0)).getAttributes().get(i).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getAttributes().get(i).getName());
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
        String name = ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getName() +"(";
        int paramscount= ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().size();
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
                if ((((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getName() +"(").length() *10 + name.length() * 10 > maxLenght * 10 )
                {
                    params = params + "\n" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getName() +",";
                    newlinesadded = newlinesadded+1;
                    textonnewline = ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getName() +",";
                }
                else
                {
                    params = params + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getName() +",";
                }
            }
            else
            {

                if (textonnewline.length()*10 + (((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getName() +",").length()*10 > maxLenght * 10)
                {
                    params = params + "\n" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getName() +",";
                    newlinesadded = newlinesadded+1;
                    textonnewline = ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getName() +",";
                }
                else
                {
                    params = params + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getName() +",";
                    textonnewline = ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m-1).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m-1).getName() + "," + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getType().getName() + ":" + ((UMLClass)classd.getClassList().get(0)).getOperations().get(i).getArguments().get(m).getName() +",";
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

        table.getChildren().add(rect);
        table.getChildren().add(line2);
        table.getChildren().add(line);
        table.getChildren().add(operations);
        table.getChildren().add(attributes);

        }
        table.getChildren().add(classname);


        rect.setPrefHeight(30 + 20 * (totalCount+newlinesadded)); // setting the height of rectangle
        //System.out.println("totalY je " + (startY + 30 + 20 * (totalCount+newlinesadded)));
        //System.out.println("totalcount je " + totalCount);
       // System.out.println("newlinesadded " + newlinesadded);



        rootpane.getChildren().add(table);

        AtomicInteger startDragX = new AtomicInteger();
        AtomicInteger startDragY = new AtomicInteger();

        table.setOnMousePressed(e -> {
            startDragX.set ((int)e.getSceneX());
            startDragY.set ((int)e.getSceneY());
        });


        table.setOnMouseDragged(e -> {
            table.setTranslateX((int)e.getSceneX() - startDragX.get());
            table.setTranslateY((int)e.getSceneY() - startDragY.get());
        });

       /* classname.setOnMousePressed(e -> {
            table.getChildren().remove(classname);
            classname.setText("Cauko");
            table.getChildren().add(classname);

        });*/


        //pane.setOnMouseClicked((MouseEvent event) -> {
         //   System.out.println("mouseClicked");
       // });

    //line,line2,classname,attributes

}

    public static void drawSeq(Stage stage, Pane rootpane,List<SequenceDiagram> sqlist )
    {
        int AreaX = (int)stage.getWidth() - 50 ;
        int AreaY = (int)stage.getWidth() - 50;
        Pane diagram = new Pane();
        int participantsCount = sqlist.get(0).getParticipantList().size();
        int messageCount = sqlist.get(0).getMessageList().size();
        //
        //int participantsCount = 2;
        int startX=0;
        int startY=50;

        int linelenght = AreaY-60-startY;
        int Activate=0;
        int Deactivate=0;

        for (int i = 1; i <= participantsCount; i++)
        {
            //System.out.println("XXXXXXXXXXXXXX "+);
            String participantname = sqlist.get(0).getParticipantList().get(i-1).getName();
            if (participantsCount==1 && i==1)
            {
                startX = (AreaX/2)-(participantname.length()/2);
            }
            else if (participantsCount>1 && i==1)
            {
                startX = (int)stage.getWidth() - AreaX;
            }
            else if (participantsCount==2 && i==2)
            {
                startX = AreaX-(participantname.length() * 10);
            }
            else if (participantsCount>2 && i>1)
            {
                /*if (participantsCount==3 && i==3)
                {
                    startX = (AreaX/2)-(participantname.length()/2);
                }*/
                //if (participantsCount>2)
               // {
                    //startX = ((int)stage.getWidth() - AreaX + (sqlist.get(0).getParticipantList().get(0).getName().length())*10-(AreaX-(sqlist.get(0).getParticipantList().get(participantsCount-1).getName().length() * 10)))
               // (sqlist.get(0).getParticipantList().get(0).getName().length()*10)
                    startX = (sqlist.get(0).getParticipantList().get(0).getName().length()*10)+(AreaX / participantsCount)*(i-1);
              //  }
              //  startX = AreaX-(participantname.length() * 10);
            }
            /*else if (participantsCount>2 && i==participantsCount)
            {
                startX = AreaX-(participantname.length() * 10);
            }*/
            Region UpperRect = new Region(); //instantiating Rectangle
            UpperRect.setLayoutX(startX); //setting the X coordinate of upper left //corner of rectangle
            UpperRect.setLayoutY(startY); //setting the Y coordinate of upper left //corner of rectangle
            UpperRect.setPrefWidth(participantname.length() * 10); //setting the width of rectangle
            UpperRect.setPrefHeight(30); //setting the width of rectangle

            Text Upperparticipantnametext = new Text(participantname);
            Upperparticipantnametext.setStyle("-fx-font-size: 15;-fx-font-weight: bold;");
            Upperparticipantnametext.setX(5+startX);
            Upperparticipantnametext.setY(70);

            Line line = new Line(); //instantiating Line class
            line.setStartX(startX+(participantname.length() * 10/2)); //setting starting X point of Line
            line.setStartY(startY + 30); //setting starting Y point of Line
            line.setEndX(startX+(participantname.length() * 10/2)); //setting ending X point of Line
            line.setEndY(AreaY-30); //setting ending Y point of Line

            Region BottomRect = new Region(); //instantiating Rectangle
            BottomRect.setLayoutX(startX); //setting the X coordinate of upper left //corner of rectangle
            BottomRect.setLayoutY(AreaY-30); //setting the Y coordinate of upper left //corner of rectangle
            BottomRect.setPrefWidth(participantname.length() * 10); //setting the width of rectangle
            BottomRect.setPrefHeight(30); //setting the width of rectangle

            Text Bottomparticipantnametext = new Text(participantname);
            Bottomparticipantnametext.setStyle("-fx-font-size: 15;-fx-font-weight: bold;");
            Bottomparticipantnametext.setX(5+startX);
            Bottomparticipantnametext.setY(AreaY-10);

            for (int m = 0; m < messageCount; m++)
            {
                //System.out.println("Message m je "+m);
                if (sqlist.get(0).getMessageList().get(m).getType()==1 && sqlist.get(0).getMessageList().get(m).getFirstClass().getName() == sqlist.get(0).getParticipantList().get(i-1).getName())
                {
                    Activate = (linelenght / messageCount) *(m+1);
                    System.out.println("Aktivacia "+participantname + " prebehne na sprave cislo "+m + " a akt je "+Activate);
                }
                if (sqlist.get(0).getMessageList().get(m).getType()==2 && sqlist.get(0).getMessageList().get(m).getFirstClass().getName() == sqlist.get(0).getParticipantList().get(i-1).getName())
                {
                    Deactivate = (linelenght / messageCount) *(m+1);
                    System.out.println("Dektivacia "+participantname + " prebehne na sprave cislo "+m + " a dea je "+Deactivate);
                }
                Line messageline = new Line(); //instantiating Line class
                messageline.setStartX(startX+(participantname.length() * 10/2)); //setting starting X point of Line
                messageline.setStartY(startY+50 + Activate); //setting starting Y point of Line
                messageline.setEndX(startX+(participantname.length() * 10/2)); //setting ending X point of Line
                messageline.setEndY(startY+50+ Deactivate); //setting ending Y point of Line
                messageline.setStroke(Color.BLUE);
                messageline.setStrokeWidth(5);
                diagram.getChildren().addAll(messageline);
            }

            diagram.getChildren().addAll(UpperRect,Upperparticipantnametext,line,BottomRect,Bottomparticipantnametext);

        }
        rootpane.getChildren().add(diagram);



       /* for (int i = 0; i < participantsCount; i++)
        {


        }*/

       /* if (participantsCount==1)
        {
            String participantname = sqlist.get(0).getParticipantList().get(0).getName();
            Region rect = new Region(); //instantiating Rectangle
            int startX = (AreaX/2)-(participantname.length()/2);
            int startY = 50;
            rect.setLayoutX(startX); //setting the X coordinate of upper left //corner of rectangle
            rect.setLayoutY(startY); //setting the Y coordinate of upper left //corner of rectangle
            rect.setPrefWidth(participantname.length() * 10); //setting the width of rectangle
            rect.setPrefHeight(30); //setting the width of rectangle

            Text participantnametext = new Text(participantname);
            participantnametext.setStyle("-fx-font-size: 15;-fx-font-weight: bold;");

            participantnametext.setX(5+(AreaX/2)-(participantname.length()/2));
            //startX + 5 + (participantname.length() * 10 / 2))
            //text.setX(startX + maxLenght*10 - ((className.length()*10) / 2));
            //text.setX(100);
            participantnametext.setY(70);

            Line line = new Line(); //instantiating Line class
            line.setStartX(startX+(participantname.length() * 10/2)); //setting starting X point of Line
            line.setStartY(startY + 30); //setting starting Y point of Line
            line.setEndX(startX+(participantname.length() * 10/2)); //setting ending X point of Line
            line.setEndY(AreaY); //setting ending Y point of Line

            diagram.getChildren().addAll(rect,participantnametext,line);
            rootpane.getChildren().add(diagram);
        }

        //if (participantsCount==2)
        //{
            String participantname = sqlist.get(0).getParticipantList().get(0).getName();
            Region rect = new Region(); //instantiating Rectangle
            int startX = (int)stage.getWidth() - AreaX;
            int startY = 50;
            rect.setLayoutX(startX); //setting the X coordinate of upper left //corner of rectangle
            rect.setLayoutY(startY); //setting the Y coordinate of upper left //corner of rectangle
            rect.setPrefWidth(participantname.length() * 10); //setting the width of rectangle
            rect.setPrefHeight(30); //setting the width of rectangle

            Text participantnametext = new Text(participantname);
            participantnametext.setStyle("-fx-font-size: 15;-fx-font-weight: bold;");

            participantnametext.setX(startX +5);
            //startX + 5 + (participantname.length() * 10 / 2))
            //text.setX(startX + maxLenght*10 - ((className.length()*10) / 2));
            //text.setX(100);
            participantnametext.setY(70);

            Line line = new Line(); //instantiating Line class
            line.setStartX(startX+(participantname.length() * 10/2)); //setting starting X point of Line
            line.setStartY(startY + 30); //setting starting Y point of Line
            line.setEndX(startX+(participantname.length() * 10/2)); //setting ending X point of Line
            line.setEndY(AreaY); //setting ending Y point of Line

            diagram.getChildren().addAll(rect,participantnametext,line);
            rootpane.getChildren().add(diagram);

            String participantname = sqlist.get(0).getParticipantList().get(0).getName();
            Region rect = new Region(); //instantiating Rectangle
            int startX = (int)stage.getWidth() - AreaX;
            int startY = 50;
            rect.setLayoutX(startX); //setting the X coordinate of upper left //corner of rectangle
            rect.setLayoutY(startY); //setting the Y coordinate of upper left //corner of rectangle
            rect.setPrefWidth(participantname.length() * 10); //setting the width of rectangle
            rect.setPrefHeight(30); //setting the width of rectangle

            Text participantnametext = new Text(participantname);
            participantnametext.setStyle("-fx-font-size: 15;-fx-font-weight: bold;");

            participantnametext.setX(startX +5);
            //startX + 5 + (participantname.length() * 10 / 2))
            //text.setX(startX + maxLenght*10 - ((className.length()*10) / 2));
            //text.setX(100);
            participantnametext.setY(70);

            Line line = new Line(); //instantiating Line class
            line.setStartX(startX+(participantname.length() * 10/2)); //setting starting X point of Line
            line.setStartY(startY + 30); //setting starting Y point of Line
            line.setEndX(startX+(participantname.length() * 10/2)); //setting ending X point of Line
            line.setEndY(AreaY); //setting ending Y point of Line

            diagram.getChildren().addAll(rect,participantnametext,line);
            rootpane.getChildren().add(diagram);
        //}
    //int participantscount = sqlist.size();
    //System.out.println("participantscount size je "+participantscount);
        System.out.println("participantov je "+sqlist.get(0).getParticipantList().size());
        //System.out.println("Stagesize je "+(int)stage.getWidth()+ " a " +(int)stage.getHeight());
*/

    }

}

