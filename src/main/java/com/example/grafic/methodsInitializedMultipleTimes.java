package com.example.grafic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class methodsInitializedMultipleTimes {
    static void closeWindowAndGoToAnother(Button btn, String way) {
        btn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(methodsInitializedMultipleTimes.class.getResource(way + ".fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    static void closeWindow(Button btn){
        btn.getScene().getWindow().hide();
    }
    static void emptyField(boolean b, TextField formulaInput, Text ef){
        if(!b) {
            int depth = 20;
            DropShadow borderGlow= new DropShadow();
            borderGlow.setColor(Color.RED);
            borderGlow.setWidth(depth);
            borderGlow.setHeight(depth);
            formulaInput.setEffect(borderGlow);
            ef.setVisible(true);
        }
        else{
            formulaInput.setEffect(null);
            ef.setVisible(false);
        }
    }
}
