package com.example.grafic;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.example.grafic.methodsInitializedMultipleTimes.closeWindowAndGoToAnother;
import static com.example.grafic.methodsInitializedMultipleTimes.emptyField;

public class SignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInButton;

    @FXML
    private TextField loginField;

    String getLogin() {
        return loginField.getText();
    }

    @FXML
    private Text ef;

    @FXML
    private TextField passField;

    @FXML
    private Button regButton;

    @FXML
    private Button contButton;

    @FXML
    void initialize() {
        assert contButton != null : "fx:id=\"contButton\" was not injected: check your FXML file 'signUp.fxml'.";
        assert ef != null : "fx:id=\"ef\" was not injected: check your FXML file 'signUp.fxml'.";
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'signUp.fxml'.";
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'signUp.fxml'.";
        assert passField != null : "fx:id=\"passField\" was not injected: check your FXML file 'signUp.fxml'.";
        assert regButton != null : "fx:id=\"regButton\" was not injected: check your FXML file 'signUp.fxml'.";

        logInButton.setOnAction(actionEvent -> {
            String login = loginField.getText().trim();
            String password = passField.getText().trim();

            try {
                if (!loginUser(login, password)) {
                    emptyField(false, loginField, ef);
                    emptyField(false, passField, ef);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        regButton.setOnAction(actionEvent -> {
            closeWindowAndGoToAnother(regButton, "register");
        });
        contButton.setOnAction(actionEvent -> {
            contButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("graph.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Controller controller = loader.getController();
            controller.setUserPanelVisible();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    private boolean loginUser(String loginText, String loginPassword) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet res = dbHandler.getUser(loginText, loginPassword);
        int counter = 0;
        while (res.next())
            counter++;
        if (counter >= 1) {
            logInButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("graph.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Controller controller = loader.getController();
            controller.initData(this, null);
            controller.comboBoxFill();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        return false;
    }


}
