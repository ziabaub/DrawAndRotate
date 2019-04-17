package com;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.filehandeling.FileReader;
import com.filehandeling.FileWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import net.openhft.compiler.CompilerUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Demo extends Application {

    private static final String DATA_XML = "data.xml";
    private static final String DATA_JSON = "data.json";
    private static final String A_Z_A_Z = "[a-zA-Z]+";
    private static final String NUMERIC = "[0-9]+";
    private boolean chooseItRectangle = false;
    private boolean getContentsCreated = false;
    private boolean init = false;
    private boolean contentsCreated = false;
    private String object;
    private String object2;
    private int layoutX = 100;

    private int layoutY = 70;
    private int degree = 5;
    private int rotateCounter = 0;
    private ToggleGroup toggleGroup;
    private ToggleButton tbCircle;
    private ToggleButton tbRectan;
    private ToggleGroup toggleGroup2;
    private ToggleButton json;
    private ToggleButton xml;
    private Pane paneContents;
    private BorderPane pane;
    private ArrayList<Rectangle> rectangles;
    private ArrayList<Circle> circles;
    private ArrayList<ObjectClass> rectanglesObjects;
    private ArrayList<ObjectClass> circlesObjects;
    private ObjectListHandler objectListHandler;
    private String priority;
    private Button clear;
    private Button rotateObject;
    private Button createObject;
    private Button saveObject;
    private Button edit;
    private Button classLoader;
    private TextField recWidth;
    private TextField recHeight;
    private TextField rCircle;
    private TextField index;
    private Class aClass;
    private Class aClass2;


    @Override
    public void start(Stage primaryStage) throws IllegalAccessException, InstantiationException {
        pane = new BorderPane();
        paneContents = new Pane();
        pane.setPrefWidth(450);
        pane.setPrefHeight(450);
        pane.setLayoutY(60);

        createSceneContents();

        createObject.setOnAction(event -> {
            if (!contentsCreated) {
                createOtherSceneContents();
                if (!getContentsCreated) {
                    getSources();

                }
                getObject('r');
                getObject('c');
            }
            try {
                if (getContentsCreated) {
                    createObject();
                }
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        });

        classLoader.setOnAction(event -> {
            if (!getContentsCreated) {
                getSources();
            }
        });

        rotateObject.setOnAction(event -> rotateObject());

        saveObject.setOnAction(event -> {
            try {
                serializableToXML();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        clear.setOnAction(event -> clearScene());
        edit.setOnAction(event -> editObject());


        Scene scene = new Scene(paneContents, 650, 650);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void editObject() {
        String inputIndex = index.getText();
        if ((!inputIndex.isEmpty()) && (!inputIndex.matches(A_Z_A_Z))) {
            int indexObject = Integer.valueOf(index.getText());
            if (toggleGroup.getSelectedToggle() == tbRectan) {

                if ((indexObject < rectangles.size()) && (checkInputRectangle())) {
                    int height = Integer.valueOf(recWidth.getText());
                    int width = Integer.valueOf(recHeight.getText());
                    rectangles.get(indexObject).setHeight(height);
                    rectangles.get(indexObject).setWidth(width);
                    rectanglesObjects.get(indexObject).setValueHeight(width);
                    rectanglesObjects.get(indexObject).setValueWidth(height);
                }
            } else if (toggleGroup.getSelectedToggle() == tbCircle) {
                if ((indexObject < circles.size()) && (checkInputCircle())) {
                    int rCircl = Integer.valueOf(rCircle.getText());
                    circles.get(indexObject).setRadius(rCircl);
                    circlesObjects.get(indexObject).setRadius(rCircl);
                }
            }

        }

    }

    private boolean checkInputRectangle() {
        String width = recWidth.getText();
        String height = recHeight.getText();
        if ((width.isEmpty()) || (height.isEmpty())
                || (width.matches(A_Z_A_Z))
                || (height.matches(A_Z_A_Z))
                || (width.matches(NUMERIC) && width.length() > 3)
                || (height.matches(NUMERIC) && height.length() > 3)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkInputCircle() {
        String rCircl = rCircle.getText();
        if ((rCircl.isEmpty())
                || (rCircl.matches(A_Z_A_Z))
                || (rCircl.matches(NUMERIC) && rCircl.length() > 3)) {
            return false;
        } else {
            return true;
        }
    }

    private void createSceneContents() throws InstantiationException, IllegalAccessException {

        createObject = new Button("Create");
        rotateObject = new Button("Rotate");
        saveObject = new Button("Save!!");
        clear = new Button("Clear  ");
        classLoader = new Button("Class.L");
        edit = new Button("Edit ");

        toggleGroup2 = new ToggleGroup();
        json = new ToggleButton("JSon");
        json.setPrefSize(60, 20);
        xml = new ToggleButton("Xml");
        xml.setPrefSize(60, 20);

        json.setToggleGroup(toggleGroup2);
        xml.setToggleGroup(toggleGroup2);
        toggleGroup2.selectToggle(xml);

        HBox saveContents = new HBox(10);
        saveContents.getChildren().addAll(json, xml, saveObject);
        saveContents.setLayoutX(430);
        saveContents.setLayoutY(620);

        VBox buttons = new VBox(10);
        buttons.setLayoutX(570);
        buttons.setLayoutY(470);
        buttons.getChildren().addAll(classLoader, createObject, rotateObject, clear);

        if (init) {
            createOtherSceneContents();
            contentsCreated = true;
            getContentsCreated = true;
            getSources();
            getObject('r');
            getObject('c');
            generateObjects(objectListHandler);
        }
        paneContents.getChildren().addAll(buttons, saveContents);
    }

    private void createOtherSceneContents() {


        contentsCreated = true;
        HBox toggleBox = new HBox(20);


        toggleBox.setLayoutX(10);
        toggleBox.setLayoutY(10);

        VBox rectangleSize = new VBox(3);  // to put the rectangle toggle and his size field
        HBox rectangleSizeField = new HBox(4);
        VBox circleRadius = new VBox(3); // to put the Circule toggle and his radius field
        VBox editVBox = new VBox(3);

        recWidth = new TextField();
        recWidth.setPrefSize(60, 20);

        recHeight = new TextField();
        recHeight.setPrefSize(60, 20);

        rCircle = new TextField();
        rCircle.setPrefSize(60, 20);

        index = new TextField();
        index.setPrefSize(60, 20);
        index.setPrefSize(124, 20);


        toggleGroup = new ToggleGroup();
        tbCircle = new ToggleButton("Circle");
        tbCircle.setPrefSize(60, 20);
        tbRectan = new ToggleButton("Rectangle");
        tbRectan.setPrefSize(124, 20);


        rectangleSizeField.getChildren().addAll(recHeight, recWidth);
        rectangleSize.getChildren().addAll(tbRectan, rectangleSizeField);
        circleRadius.getChildren().addAll(tbCircle, rCircle);
        editVBox.getChildren().addAll(edit, index);

        tbCircle.setToggleGroup(toggleGroup);
        tbRectan.setToggleGroup(toggleGroup);


        toggleBox.getChildren().addAll(rectangleSize, circleRadius, editVBox);
        paneContents.getChildren().addAll(toggleBox, pane);
    }

    private void generateObjects(ObjectListHandler objectClasses) throws IllegalAccessException, InstantiationException {
        rectangles = new ArrayList<>();
        circles = new ArrayList<>();
        int counter = priority.length();
        int k = 0;
        int c = 0;
        int r = 0;
        int height;
        int width;
        ObjectClass objectClass = null;

        while (counter != 0) {
            if (priority.charAt(k) == 'r') {

                objectClass = (ObjectClass) aClass.newInstance();//getObject('r');

                height = objectClasses.getRectangles().get(r).getValueHeight();
                width = objectClasses.getRectangles().get(r).getValueWidth();

                Rectangle rectangle = (Rectangle) objectClass.createObject(height, width);
                rectangle.setLayoutX(objectClasses.getRectangles().get(r).getLayoutX());
                rectangle.setLayoutY(objectClasses.getRectangles().get(r).getLayoutY());
                rectangles.add(rectangle);
                pane.getChildren().add(rectangle);
                rotateCounter++;
                chooseItRectangle = true;
                r++;
            } else {

                objectClass = (ObjectClass) aClass2.newInstance();//getObject('c');


                Circle circle = (Circle) objectClass.createObject(objectClasses.getCircles().get(c).getRadius());
                circles.add(circle);
                pane.getChildren().add(circle);
                c++;
            }
            k++;
            counter--;
        }
    }

    private ObjectClass getObject(char type) {


        try {
            if (type == 'r') {

                aClass = CompilerUtils.CACHED_COMPILER.loadFromJava("com.RectangleObject", object);
                return (ObjectClass) aClass.newInstance();
            } else if (type == 'c') {

                aClass2 = CompilerUtils.CACHED_COMPILER.loadFromJava("com.CirclObject", object2);
                return (ObjectClass) aClass2.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setRectangleLayouts(Rectangle rectangle, ObjectClass objectClass) {

        rectangle.setLayoutX(layoutX);
        rectangle.setLayoutY(layoutY);
        objectClass.setLayoutX(layoutX);
        objectClass.setLayoutY(layoutY);

    }

    private void generateLayouts() {

        layoutX += ((layoutX * 5) / 100);
        layoutY += ((layoutY * 15) / 100);
    }

    private void setcirculeLayouts(Circle circle) {

        circle.setCenterX(250);
        circle.setCenterY(250);
    }


    private void createObject() throws IllegalAccessException, InstantiationException {
        ObjectClass objectClass;

        if (toggleGroup.getSelectedToggle() == tbRectan) {
            chooseItRectangle = true;
            priority += 'r';
            objectClass = (ObjectClass) aClass.newInstance();//getObject('r');

            addObject(objectClass, checkInputRectangle(), 'r');//

        } else if (toggleGroup.getSelectedToggle() == tbCircle) {
            priority += 'c';


            objectClass = (ObjectClass) aClass2.newInstance();//getObject('c');

            addObject(objectClass, checkInputCircle(), 'c');
        }
    }

    private void getSources() {
        getContentsCreated = true;
        FileReader fileReader = new FileReader();
        String pathRes = "/Users/ziadelsarrih/Desktop/Labs/OOP/oop4/src/main/java/outResources/";
        byte[] circlObject = fileReader.readFile(pathRes + "Obj1.txt");
        byte[] rectangleObject = fileReader.readFile(pathRes + "Obj2.txt");
        FileWriter fileWriter = new FileWriter();
        object2 = new String(circlObject);
        object = new String(rectangleObject);
        String pathDest = "/Users/ziadelsarrih/Desktop/Labs/OOP/oop4/src/main/java/com/";
        fileWriter.writeByte(circlObject, pathDest + "CirclObject.java");
        fileWriter.writeByte(rectangleObject, pathDest + "RectangleObject.java");

    }

    private void addObject(ObjectClass objectClass, boolean b, char type) {

        if (((rectanglesObjects == null) && (type == 'r'))) {

            Rectangle rectangle;
            if (!b) {
                rectangle = (Rectangle) objectClass.createObject(300, 200);
            } else {
                rectangle = (Rectangle) objectClass.createObject(Integer.valueOf(recHeight.getText()), Integer.valueOf(recWidth.getText()));
            }
            setRectangleLayouts(rectangle, objectClass);
            pane.getChildren().add(rectangle);
            rectangles = new ArrayList<>();
            rectanglesObjects = new ArrayList<>();
            rectangles.add(rectangle);
            rectanglesObjects.add(objectClass);
            generateLayouts();
            rotateCounter++;
        } else if ((circlesObjects == null) && (type == 'c')) {

            Circle circle;
            if (!b) {
                circle = (Circle) objectClass.createObject(200);
            } else {
                circle = (Circle) objectClass.createObject(Integer.valueOf(rCircle.getText()));
            }
            setcirculeLayouts(circle);
            pane.getChildren().add(circle);
            circles = new ArrayList<>();
            circles.add(circle);
            circlesObjects = new ArrayList<>();
            circlesObjects.add(objectClass);
        } else {
            if (type == 'r') {

                regenerateLastObject(objectClass, b, type);
                rotateCounter++;
            } else {

                regenerateLastObject(objectClass, b, type);

            }

        }
    }

    private void regenerateLastObject(ObjectClass objectClass, boolean size, char type) {


        int width = 0;
        int height = 0;
        int radius = 0;
        if (!size) {
            objectClass = generateNew(objectClass, type);
        } else {

            if (type == 'r') {
                width = Integer.valueOf(recWidth.getText());
                height = Integer.valueOf(recHeight.getText());
                objectClass.setValueHeight(height);
                objectClass.setValueWidth(width);
            } else {
                radius = Integer.valueOf(rCircle.getText());
                objectClass.setRadius(radius);
            }
        }
        if (type == 'r') {
            Rectangle rectangle = (Rectangle) objectClass.createObject();
            setRectangleLayouts(rectangle, objectClass);
            pane.getChildren().add(rectangle);
            rectangles.add(rectangle);
            rectanglesObjects.add(objectClass);
            generateLayouts();
        } else {
            Circle circle = (Circle) objectClass.createObject();
            setcirculeLayouts(circle);
            pane.getChildren().add(circle);
            circles.add(circle);
            circlesObjects.add(objectClass);
        }


    }

    public ObjectClass generateNew(ObjectClass objectClass, char type) {
        ObjectClass o;
        if (type == 'r') {
            o = rectanglesObjects.get(rectangles.size() - 1);
            int height = o.getValueHeight() - ((o.getValueHeight() * 10) / 100);
            int width = o.getValueWidth() - ((o.getValueWidth() * 10) / 100);
            objectClass.setValueHeight(height);
            objectClass.setValueWidth(width);
            return objectClass;
        } else {
            o = circlesObjects.get(circles.size() - 1);
            int radius = (o.getRadius() - (o.getRadius() * 10 / 100));
            objectClass.setRadius(radius);
            return objectClass;

        }
    }


    private void rotateObject() {
        if (chooseItRectangle) {
            if (rotateCounter > 0) {
                for (int i = rotateCounter - 1; i < rectangles.size(); i++) {
                    rectangles.get(i).setRotate(degree);
                    degree += 5;
                }
                rotateCounter--;
            } else {
                rotateCounter = rectangles.size() - 1;
            }

        }
    }

    private void clearScene() {
        pane.getChildren().clear();
        rectangles = null;
        circles = null;
        rectanglesObjects = null;
        circlesObjects = null;
        priority = "";
        layoutX = 100;
        layoutY = 70;

    }

    private void serializableToXML() throws IOException {

        ObjectListHandler objectListHandlerTem = new ObjectListHandler();
        objectListHandlerTem.setRectangles(rectanglesObjects);
        objectListHandlerTem.setCircles(circlesObjects);
        objectListHandlerTem.setPriorety(priority);
        if (toggleGroup2.getSelectedToggle() == json) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(DATA_JSON), objectListHandlerTem);
            File file = new File(DATA_XML);
            file.delete();


        } else {
            //  if ((rectanglesObjects != null) && (circlesObjects != null)) {
            try {
                XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.writeValue(new File(DATA_XML), objectListHandlerTem);
                File file = new File(DATA_JSON);
                file.delete();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            //  }
        }
    }

    public void init() throws IOException {
        priority = "";
        File file = new File(DATA_JSON);
        if (file.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonText = new String(Files.readAllBytes(Paths.get(DATA_JSON)));
            objectListHandler = objectMapper.readValue(jsonText, ObjectListHandler.class);
        } else {
            XmlMapper xmlMapper = new XmlMapper();
            String xmlText = new String(Files.readAllBytes(Paths.get(DATA_XML)));
            objectListHandler = xmlMapper.readValue(xmlText, ObjectListHandler.class);
        }
        init = objectListHandler != null;
        if (objectListHandler != null) {
            circlesObjects = objectListHandler.getCircles();
            rectanglesObjects = objectListHandler.getRectangles();
            priority = objectListHandler.getPriorety();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
