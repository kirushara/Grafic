/**
 * Sample Skeleton for 'graph.fxml' Controller Class
 */

package com.example.grafic;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import static com.example.grafic.methodsInitializedMultipleTimes.emptyField;

public class Controller {
    void initData(SignUp su, Register r) {
        if (su != null)
            userName.setText(su.getLogin());
        else
            userName.setText(r.getLogin());
    }

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

    @FXML // fx:id="clearButton"
    private Button refreshButton; // Value injected by FXMLLoader

    @FXML // fx:id="outputGraph"
    private Pane outputGraph; // Value injected by FXMLLoader

    @FXML
    private TextField formulaInput;

    @FXML
    private Text ef;

    @FXML
    private Text userName;

    @FXML
    private Text extraText1;

    @FXML
    private Text extraText2;

    @FXML
    private ComboBox<String> formulasList;

    final short dS = 41; //division size

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException {
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'graph.fxml'.";
        assert drawButton != null : "fx:id=\"drawButton\" was not injected: check your FXML file 'graph.fxml'.";
        assert ef != null : "fx:id=\"ef\" was not injected: check your FXML file 'graph.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'graph.fxml'.";
        assert formulaInput != null : "fx:id=\"formulaInput\" was not injected: check your FXML file 'graph.fxml'.";
        assert formulasList != null : "fx:id=\"formulasList\" was not injected: check your FXML file 'graph.fxml'.";
        assert outputGraph != null : "fx:id=\"outputGraph\" was not injected: check your FXML file 'graph.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'graph.fxml'.";
        comboBoxFill();
        drawButton.setOnAction(actionEvent -> {
            outputGraph.getChildren().clear();
            bgFill();
            drawFunction();
            if(!Objects.equals(userName.getText(), "")) {
                try {
                    addFormulaToDb();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        clearButton.setOnAction(actionEvent -> {
            outputGraph.getChildren().clear();
            bgFill();
            formulaInput.setText("");
        });
        exitButton.setOnAction(actionEvent -> {
            System.exit(0);
        });
        formulasList.setOnAction(actionEvent -> {
            if (!Objects.equals(formulasList.getValue(), "Choose formula"))
                formulaInput.setText(formulasList.getValue());
        });
        refreshButton.setOnAction(actionEvent -> {
            try {
                comboBoxFill();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void setUserPanelVisible() {
        extraText1.setVisible(false);
        extraText2.setVisible(false);
        formulasList.setVisible(false);
        refreshButton.setVisible(false);
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

            circle = new Circle(gWidth / 2, i * dS, 2);
            circle.setFill(Color.BLACK);
            outputGraph.getChildren().add(circle);

            circle = new Circle(i * dS, gHeight / 2, 2);
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
        short c = -9;
        for (int i = 1; i <= 19; i++) {
            lbl = new Label(String.valueOf(-c));
            lbl.setLayoutX(gWidth / 2 + 2);
            lbl.setLayoutY(i * dS);
            outputGraph.getChildren().add(lbl);

            lbl = new Label(String.valueOf(c));
            lbl.setLayoutX(i * dS);
            lbl.setLayoutY(gHeight / 2 + 2);
            if (c != 0) outputGraph.getChildren().add(lbl);
            c++;
        }
    }

    @FXML
    void drawFunction() {
        String s = formulaInput.getText().trim();
        if (s.equals("")) {
            emptyField(false, formulaInput, ef);
            return;
        } else {
            emptyField(true, formulaInput, ef);
        }
        float gWidth = (float) outputGraph.getWidth();
        float gHeight = (float) outputGraph.getHeight();
        Line line;
        for (float x = -gWidth / 2; x < gWidth / 2; x++) {
            line = new Line(
                    x + gWidth / 2,
                    -f(x, s, 0) + gHeight / 2,
                    x + 1 + gWidth / 2,
                    -f(x + 1, s, 0) + gHeight / 2
            );
            line.setStroke(Color.GREEN);
            line.setStrokeWidth(2);
            outputGraph.getChildren().add(line);
        }
    }

    double f(double x, String s, double f) {
        int num;
        double incr;
        int c;
        char buff;
        char[] array;
        while (!s.matches("[$]+")) {
            if (s.contains("x^")) {
                c = s.indexOf("^") + 1;
                buff = s.charAt(c);
                num = Character.getNumericValue(buff);
                incr = Math.pow(x, num) / Math.pow(41, num - 1);
                f += incr;
                array = s.toCharArray();
                array[c] = '$';
                array[c - 1] = '$';
                array[c - 2] = '$';
                s = new String(array);
            }
            if (s.contains("+")) {
                c = s.indexOf("+");
                array = s.toCharArray();
                array[c] = '$';
                s = new String(array);
                f(x, s, f);
            }
        }
        return f;
    }

    void addFormulaToDb() throws SQLException {
        String login = userName.getText().trim();
        String formula = formulaInput.getText().trim();
        DatabaseHandler dbHandler = new DatabaseHandler();
        if (!dbHandler.formulaCheck(login, formula) && !formula.equals(""))
            dbHandler.setFormula(login, formula);
    }

    void comboBoxFill() throws SQLException {
        String login = userName.getText();
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.getFormula(login);
        ObservableList<String> list = FXCollections.observableArrayList();
        while (rs.next())
            list.add(rs.getString("formula"));
        formulasList.setItems(list);
        formulasList.setValue("Choose formula");
    }
}
