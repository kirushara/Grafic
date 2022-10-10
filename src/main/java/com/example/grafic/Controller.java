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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Controller {


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="drawButton"
    private Button drawButton; // Value injected by FXMLLoader

    @FXML // fx:id="clearButton"
    private Button clearButton; // Value injected by FXMLLoader

    @FXML // fx:id="clearButton"
    private Button exitButton; // Value injected by FXMLLoader

    @FXML // fx:id="outputGraph"
    private Pane outputGraph; // Value injected by FXMLLoader

    @FXML
    private TextField formulaInput;

    final short dS = 41; //division size

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert drawButton != null : "fx:id=\"drawButton\" was not injected: check your FXML file 'graph.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'graph.fxml'.";
        assert exitButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'graph.fxml'.";
        assert outputGraph != null : "fx:id=\"outputGraph\" was not injected: check your FXML file 'graph.fxml'.";
        drawButton.setOnAction(actionEvent -> {
            outputGraph.getChildren().clear();
            bgFill();
            drawFunction();
        });
        clearButton.setOnAction(actionEvent -> {
            outputGraph.getChildren().clear();
            bgFill();
            System.out.print(formulaInput.getText().compareTo(""));
        });
        exitButton.setOnAction(actionEvent -> {
            System.exit(0);
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
            line = new Line(0, i * dS, gWidth, i * dS);
            line.setStroke(Color.LIGHTGRAY);
            outputGraph.getChildren().add(line);

            line = new Line(i * dS, 0, i * dS, gHeight);
            line.setStroke(Color.LIGHTGRAY);
            outputGraph.getChildren().add(line);

            circle=new Circle(gWidth/2, i*dS, 2);
            circle.setFill(Color.BLACK);
            outputGraph.getChildren().add(circle);

            circle=new Circle(i*dS, gHeight/2, 2);
            circle.setFill(Color.BLACK);
            outputGraph.getChildren().add(circle);
        }
        //Ox
        line = new Line(0, gHeight / 2, gWidth, gHeight / 2);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        outputGraph.getChildren().add(line);
        //Oy
        line = new Line(gWidth / 2, 0, gWidth / 2, gHeight);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        outputGraph.getChildren().add(line);
        //numbers
        Label lbl;
        short c=-9;
        for (int i = 1; i <= 19; i++) {
            lbl = new Label(String.valueOf(-c));
            lbl.setLayoutX(gWidth/2+2);
            lbl.setLayoutY(i*dS);
            outputGraph.getChildren().add(lbl);

            lbl = new Label(String.valueOf(c));
            lbl.setLayoutX(i*dS);
            lbl.setLayoutY(gHeight/2+2);
            if(c!=0) outputGraph.getChildren().add(lbl);
            c++;
        }
    }

    @FXML
    void drawFunction(){
        float gWidth = (float) outputGraph.getWidth();
        float gHeight = (float) outputGraph.getHeight();
        Line line;
        String s = formulaInput.getText();
        for(float x=-gWidth/2;x<gWidth/2;x++){
            line = new Line(
                    x + gWidth/2,
                    -f(x, s, 0) + gHeight/2,
                    x+1 +gWidth/2,
                    -f(x+1, s, 0)+ gHeight/2
            );
            line.setStroke(Color.GREEN);
            line.setStrokeWidth(2);
            outputGraph.getChildren().add(line);
        }
    }
    double f1(double x){
        short c=dS; //one division on ox, oy
        return x*x/41+x*x*x/(41*41);
    }


    double f(double x, String s, double f){
        int num;
        int c;
        char buff;
        char[]array;
        while(true) {
            if(s.contains("x^")){
                while(s.contains("x^")){
                    c = s.indexOf("^")+1;
                    buff=s.charAt(c);
                    num=Character.getNumericValue(buff);
                    f+=Math.pow(x, num)/Math.pow(41, num-1);
                    array=s.toCharArray();
                    array[c]='$';
                    array[c-1]='$';
                    array[c-2]='$';
                    s = new String(array);
                    s.replaceAll("$", "");
                    System.out.println(s);
                }
            }
            if(s.contains("+")){
                c=s.indexOf("+");
                array=s.toCharArray();
                array[c]='$';
                s = new String(array);

            }
            if(arrCheck(s)||s.compareTo("")==0) break;
        }
        return f;
    };
    boolean arrCheck(String s){
        char[]c=s.toCharArray();
        int l=s.length();
        for(int i=0;i<l;i++)
            if(c[i]!='$') return false;
        return true;
    }
}


/*
interface Operation{
        double execute(double... nums);
    }
 */
/*
 float gWidth = (float) outputGraph.getWidth();
        float gHeight = (float) outputGraph.getHeight();
        Operation operation = x -> x[0]*x[0]/3;

        for(int i = 0; i < gWidth; i++) {
            outputGraph.getChildren().add(
                    new Line(
                            i + gWidth/2,
                            -operation.execute(i) + gHeight/2,
                            i+1 +gWidth/2,
                            -operation.execute(i+1)+ gHeight/2
                    )
            );
        }
 */
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