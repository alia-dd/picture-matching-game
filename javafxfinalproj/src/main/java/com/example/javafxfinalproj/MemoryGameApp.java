package com.example.javafxfinalproj;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MemoryGameApp extends Application {
    public static void main(String[] args) {
        launch();
    }
    MemoryGameController gm = new MemoryGameController();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MemoryGameApp.class.getResource("Start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.centerOnScreen();
        gm.starttimer= true;
        stage.show();
        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());
    }

}