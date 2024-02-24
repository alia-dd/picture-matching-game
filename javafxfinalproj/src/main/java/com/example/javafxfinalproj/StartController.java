package com.example.javafxfinalproj;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StartController {
    @FXML
    private Button btnClose;

    @FXML
    private Button btnStart;
    Stage stage;
    @FXML
    void StartGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("Level-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Game Levels");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());

    }


    @FXML
    void score_table(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MemoryGameApp.class.getResource("Table-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Game Levels");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());
    }

    @FXML
    void CloseApp(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
