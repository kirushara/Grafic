package com.example.grafic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
        logInButton.setOnAction(actionEvent -> {
            String login = loginField.getText().trim();
            String password = passField.getText().trim();

            if(!login.equals("")&&!password.equals(""))
                loginUser(login, password);
            else {
                emptyField(false, loginField, ef);
                emptyField(false, passField, ef);
            }
        });
        regButton.setOnAction(actionEvent -> {
            closeWindowAndGoToAnother(regButton, "register");
        });
        contButton.setOnAction(actionEvent -> {
            closeWindowAndGoToAnother(contButton, "graph");
        });
    }

    private void loginUser(String loginText, String loginPassword) {

    }

}
