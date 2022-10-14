package com.example.grafic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import static com.example.grafic.methodsInitializedMultipleTimes.closeWindowAndGoToAnother;
import static com.example.grafic.methodsInitializedMultipleTimes.emptyField;

public class Register {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passField;

    @FXML
    private Text ef;
    @FXML
    private Text ef1;
    @FXML
    private Button regButton;

    @FXML
    private Button finishButton;

    @FXML
    private Text regSccs;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        regButton.setOnAction(actionEvent -> {
            if (loginField.getText().equals("")) {
                emptyField(true, loginField, ef1);
                emptyField(false, loginField, ef);
            } else if (dbHandler.loginCheck(loginField.getText())) {
                emptyField(true, loginField, ef);
                emptyField(false, loginField, ef1);
            } else {
                emptyField(true, loginField, ef);
                emptyField(true, loginField, ef1);
                dbHandler.regUser(loginField.getText(), passField.getText());
                regButton.setDisable(true);
                regSccs.setVisible(true);
                finishButton.setVisible(true);
            }
        });
        finishButton.setOnAction(actionEvent -> {
            closeWindowAndGoToAnother(finishButton, "graph");
        });
    }
}
