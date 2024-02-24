package com.example.javafxfinalproj;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LevelController{


    @FXML
    private Button btnHome;
    @FXML
    public void initialize() throws FileNotFoundException {
        FileInputStream input2 = new FileInputStream("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\asset\\home1.png");
        Image image2 = new Image(input2);
        ImageView view2 = new ImageView(image2);
        view2.setFitWidth(20);
        view2.setFitHeight(20);

        btnHome.setGraphic(view2);



    }

    GameBoard gb = new GameBoard();
    MemoryGameController gm = new MemoryGameController();
    @FXML
    void btnFour(ActionEvent event) throws IOException {
        gb.BoardSize(4,4);
        gm.starttimer = true;
        System.out.println(gb.rowNum);
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("MemoryGame-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());
    }

    @FXML
    void btnFive(ActionEvent event) throws IOException {
        gb.BoardSize(6,6);
        gm.starttimer = true;
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("MemoryGame-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),944,760);
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());
    }

    @FXML
    void btnSix(ActionEvent event) throws IOException {
        gb.BoardSize(8,8);
        gm.starttimer = true;
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("MemoryGame-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),990,800);
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());
    }

    @FXML
    void BackToHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("Start-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());

    }


}
