/**
 * Sample Skeleton for 'graph.fxml' Controller Class
 */

package com.example.grafic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Controller {


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="drawButton"
    private Button drawButton; // Value injected by FXMLLoader

    @FXML // fx:id="clearButton"
    private Button clearButton; // Value injected by FXMLLoader

    @FXML // fx:id="endX"
    private TextField endX; // Value injected by FXMLLoader

    @FXML // fx:id="endY"
    private TextField endY; // Value injected by FXMLLoader

    @FXML // fx:id="outputGraph"
    private Pane outputGraph; // Value injected by FXMLLoader

    @FXML // fx:id="startX"
    private TextField startX; // Value injected by FXMLLoader

    @FXML // fx:id="startY"
    private TextField startY; // Value injected by FXMLLoader

    @FXML // fx:id="ef1"
    private Text ef1; // Value injected by FXMLLoader

    @FXML // fx:id="ef2"
    private Text ef2; // Value injected by FXMLLoader

    @FXML // fx:id="ef3"
    private Text ef3; // Value injected by FXMLLoader

    @FXML // fx:id="ef4"
    private Text ef4; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert drawButton != null : "fx:id=\"drawButton\" was not injected: check your FXML file 'graph.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'graph.fxml'.";
        assert endX != null : "fx:id=\"endX\" was not injected: check your FXML file 'graph.fxml'.";
        assert endY != null : "fx:id=\"endY\" was not injected: check your FXML file 'graph.fxml'.";
        assert outputGraph != null : "fx:id=\"outputGraph\" was not injected: check your FXML file 'graph.fxml'.";
        assert startX != null : "fx:id=\"startX\" was not injected: check your FXML file 'graph.fxml'.";
        assert startY != null : "fx:id=\"startY\" was not injected: check your FXML file 'graph.fxml'.";
        drawButton.setOnAction(actionEvent -> {
            outputGraph.getChildren().clear();
            bgFill();
        });
        clearButton.setOnAction(actionEvent -> {
            outputGraph.getChildren().clear();
        });
    }

    @FXML
    void bgFill() {
        float gWidth = (float) outputGraph.getWidth();
        float gHeight = (float) outputGraph.getHeight();

        //lines
        Line line;
        Circle circle;
        for (int i = 1; i <= 19; i++) {
            line = new Line(0, i * 41, gWidth, i * 41);
            line.setStroke(Color.LIGHTGRAY);
            outputGraph.getChildren().add(line);

            line = new Line(i * 41, 0, i * 41, gHeight);
            line.setStroke(Color.LIGHTGRAY);
            outputGraph.getChildren().add(line);

            circle=new Circle(gWidth/2, i*41, 2);
            circle.setFill(Color.BLACK);
            outputGraph.getChildren().add(circle);

            circle=new Circle(i*41, gHeight/2, 2);
            circle.setFill(Color.BLACK);
            outputGraph.getChildren().add(circle);
        }
        //Ox
        line = new Line(1, gHeight / 2, gWidth - 1, gHeight / 2);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        outputGraph.getChildren().add(line);
        //Oy
        line = new Line(gWidth / 2, 1, gWidth / 2, gHeight - 1);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        outputGraph.getChildren().add(line);
        //numbers
        Label lbl;
        short c=-9;
        for (int i = 1; i <= 19; i++) {
            lbl = new Label(String.valueOf(c));
            lbl.setLayoutX(gWidth/2+2);
            lbl.setLayoutY(i*41);
            outputGraph.getChildren().add(lbl);

            lbl = new Label(String.valueOf(c));
            lbl.setLayoutX(i*41);
            lbl.setLayoutY(gHeight/2+2);
            if(c!=0) outputGraph.getChildren().add(lbl);
            c++;
        }
    }
    @FXML
    void drawFunction(){

    }
    double y(double x){
        return x*x;
    }
}
/*boolean check = true;
        int sX = 0, sY=0, eX=0, eY=0;

        if(isFieldEmpty(getStartX())){
            ef1.setOpacity(1);
            check=false;
        }else{
            sX=strToInt(getStartX());
        }

        if(isFieldEmpty(getStartY())){
            ef2.setOpacity(1);
            check=false;
        }else{
            sY=strToInt(getStartY());
        }

        if(isFieldEmpty(getEndX())){
            ef3.setOpacity(1);
            check=false;
        }else{
            eX=strToInt(getEndX());
        }

        if(isFieldEmpty(getEndY())){
            ef4.setOpacity(1);
            check=false;
        }else{
            eY=strToInt(getEndY());
        }

        if(check) {
            Line line = new Line(sX, sY, eX, eY);
            outputGraph.getChildren().add(line);
        }*/
/*
    @FXML
    String getStartX(){
       return startX.getText();
    }
    @FXML
    String getStartY(){
        return startY.getText();
    }
    @FXML
    String getEndX(){
        return endX.getText();
    }
    @FXML
    String getEndY(){
        return endY.getText();
    }
    @FXML
    boolean isFieldEmpty(String s){
        if(s=="") return true;
        else return false;
    }
    }*/