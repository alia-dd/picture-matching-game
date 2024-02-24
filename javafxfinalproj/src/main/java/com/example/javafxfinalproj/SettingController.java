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
import java.net.URL;

public class SettingController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnCont;

    public static Stage prestage = null;

    MemoryGameController gm = new MemoryGameController();

    @FXML
    public void initialize() throws FileNotFoundException {

        FileInputStream input = new FileInputStream("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\asset\\continue1.png");
        Image image = new Image(input);
        ImageView view = new ImageView(image);
        view.setFitWidth(50);
        view.setFitHeight(50);

        FileInputStream input2 = new FileInputStream("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\asset\\home1.png");
        Image image2 = new Image(input2);
        ImageView view2 = new ImageView(image2);
        view2.setFitWidth(50);
        view2.setFitHeight(50);


        btnCont.setGraphic(view);
        btnHome.setGraphic(view2);

    }

    @FXML
    void btncontinue(ActionEvent event) {
        close();
        gm.starttimer = true;
        gm.timeline2.play();

    }

    @FXML
    void BackToHome(ActionEvent event) throws IOException {
        Stage stagem = (Stage) btnHome.getScene().getWindow();
        stagem.hide();
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("Level-view.fxml"));
        Stage stage = prestage;
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Game Levels");
        stage.setScene(scene);
        close();
        stage.centerOnScreen();
        stage.show();
        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());
    }

    void close(){
        Stage stage = (Stage) btnCont.getScene().getWindow();
        stage.close();
    }
}
