package com.example.grafic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("graph.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 840);
        stage.setResizable(true);
        stage.setTitle("Function Graph Draw");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}