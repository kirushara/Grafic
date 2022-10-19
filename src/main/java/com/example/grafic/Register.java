package com.example.grafic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.example.grafic.methodsInitializedMultipleTimes.emptyField;

public class Register {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    String getLogin() {
        return loginField.getText();
    }

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
        assert ef != null : "fx:id=\"ef\" was not injected: check your FXML file 'register.fxml'.";
        assert ef1 != null : "fx:id=\"ef1\" was not injected: check your FXML file 'register.fxml'.";
        assert finishButton != null : "fx:id=\"finishButton\" was not injected: check your FXML file 'register.fxml'.";
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'register.fxml'.";
        assert passField != null : "fx:id=\"passField\" was not injected: check your FXML file 'register.fxml'.";
        assert regButton != null : "fx:id=\"regButton\" was not injected: check your FXML file 'register.fxml'.";
        assert regSccs != null : "fx:id=\"regSccs\" was not injected: check your FXML file 'register.fxml'.";

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
            finishButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("graph.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Controller controller = loader.getController();
            controller.initData(null, this);
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
