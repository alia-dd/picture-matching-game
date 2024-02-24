package com.example.javafxfinalproj;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class MemoryGameController {

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

        try {
            hideButtons();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }));

    Timeline timelineCmp = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
        try {
            BackToScreen();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }));

    Text text;
    static Timeline timeline2;
    int mins = 0, secs = 0, millis = 0;

    public static boolean starttimer = false;
    private boolean firstCardClicked = false;
    private boolean match;
    private boolean complete;
    Cards  firstCard = null;
    Cards secondCard = null;
    Stage stage;
    Scene scene;

    GameBoard board = new GameBoard();
    int boardsize = board.rowNum;

    @FXML
    private AnchorPane Gpane;
    @FXML
    private GridPane gameMatrix;
    @FXML
    private Button btnload;
    @FXML
    private Label txlable;
    @FXML
    private Button btnSetting;



    @FXML
    public void initialize() throws FileNotFoundException {
        sets();
        timer();
        if (starttimer)
        {
            timeline2.play();
        }
        else if (!starttimer){
            timeline2.stop();
        }
        System.out.println(boardsize);
        board.populateBoard();
        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                System.out.println(boardsize);
                FileInputStream input = new FileInputStream("/C:/Users/meyra/OneDrive/Pictures/logo.png/");
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                if(boardsize == 8){
                    imageView.setFitWidth(75);
                    imageView.setFitHeight(75);
                }
                else {
                    imageView.setFitWidth(90);
                    imageView.setFitHeight(90);
                }
                imageView.setUserData(row+","+col);
                imageView.setOnMouseClicked(event -> {
                    try {
                        cardListener(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                gameMatrix.add(imageView, row, col);
            }
        }

       icom();

    }

    private void cardListener(MouseEvent event) throws IOException {
        Node sourceComponent = (Node) event.getSource();
        String rowAndColumn = (String)sourceComponent.getUserData();
        System.out.println(rowAndColumn);

        int selectedRow = Integer.parseInt(rowAndColumn.split(",")[0]);
        int selectedCol = Integer.parseInt(rowAndColumn.split(",")[1]);
        System.out.println(board.Cells[selectedRow][selectedCol].wasGuessed);
        if(!board.Cells[selectedRow][selectedCol].wasGuessed){
        String image = board.Cells[selectedRow][selectedCol].value;

        FileInputStream imageFile = new FileInputStream ("/C:/Users/meyra/Documents/cashar/java/javafxfinalproj/src/main/asset/"+image+".png");



        Image selectedImage = new Image(imageFile);
        ((ImageView)sourceComponent).setImage(selectedImage);

            CheckIfMarch(selectedRow, selectedCol);
        }
        CheckIfComplete();
        if(complete){
            starttimer = false;
            timeline2.stop();
            saveScore();
            FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("Level-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setTitle("Memory Game");
            timelineCmp.play();
            File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
            URL url= file.toURI().toURL();
            scene.getStylesheets().add(url.toExternalForm());
        }
    }

    @FXML
    void CheckIfComplete(){
//
        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                if(!board.Cells[row][col].wasGuessed){
                    return;
                }
            }
        }
        complete = true;
    }

    @FXML
    void CheckIfMarch(int selectedRow, int selectedCol) throws FileNotFoundException {
        if(!firstCardClicked){
            //If next turn is started before old buttons are hidden
            if(!match){
                if(firstCard != null && secondCard != null ){
                    hideButtons();
                    timeline.stop();
                }
            }
            match = false;
            firstCardClicked = true;

            firstCard = board.Cells[selectedRow][selectedCol];

            return;
        }

        if(board.Cells[firstCard.row][firstCard.col] == board.Cells[selectedRow][selectedCol]){
            return;
        }
        secondCard = board.Cells[selectedRow][selectedCol];

        firstCardClicked = false;

        if(firstCard.value.equals(secondCard.value)){
            //matching pair
            System.out.println("Match");
            board.Cells[firstCard.row][firstCard.col].wasGuessed = true;
            board.Cells[secondCard.row][secondCard.col].wasGuessed = true;
            match = true;
            return;
        }

        timeline.play();
    }

    @FXML
    public void BackToScreen() throws IOException {
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());
    }

    @FXML
    void btnRelode(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MemoryGameController.class.getResource("MemoryGame-view.fxml"));
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
    void btnpause(ActionEvent event) throws IOException {
        Stage stages = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingController sc = new SettingController();
        sc.prestage = stages;
        FXMLLoader fxmlLoader = new FXMLLoader(MemoryGameController.class.getResource("Setting-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        starttimer = false;
        timeline2.stop();
        stage.centerOnScreen();

        File file= new File("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\java\\com\\example\\javafxfinalproj\\Style.css");
        URL url= file.toURI().toURL();
        scene.getStylesheets().add(url.toExternalForm());
        stage.showAndWait();
    }

    private void hideButtons() throws FileNotFoundException {

        FileInputStream input = new FileInputStream("/C:/Users/meyra/OneDrive/Pictures/logo.png/");
        Image questionImage = new Image(input);
        int indexFirstCardInList = (firstCard.row * boardsize) + firstCard.col;
        ((ImageView)gameMatrix.getChildren().get(indexFirstCardInList)).setImage(questionImage);

        int indexSecondCardInList = (secondCard.row * boardsize) + secondCard.col;
        ((ImageView)gameMatrix.getChildren().get(indexSecondCardInList)).setImage(questionImage);

    }

    void icom() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\asset\\reset.png");
        Image image = new Image(input);
        ImageView view = new ImageView(image);
        view.setFitWidth(30);
        view.setFitHeight(30);
        btnload.setGraphic(view);

        FileInputStream input2 = new FileInputStream("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\asset\\grid.png");
        Image image2 = new Image(input2);
        ImageView view2 = new ImageView(image2);
        view2.setFitWidth(30);
        view2.setFitHeight(30);
        btnSetting.setGraphic(view2);
    }

    void sets(){
        if(boardsize==8){
            Gpane.setPrefWidth(825);
            Gpane.setPrefHeight(665);
            btnSetting.setLayoutX(830);
            btnSetting.setLayoutY(20);
            btnload.setLayoutY(20);
            txlable.setLayoutY(20);
            Gpane.setLayoutY(80);
        }
        else if(boardsize==6){
            Gpane.setPrefWidth(745);
            Gpane.setPrefHeight(630);
            btnSetting.setLayoutX(750);
        }
        else{
            Gpane.setPrefWidth(549);
            Gpane.setPrefHeight(420);
            btnSetting.setLayoutX(555);
        }
    }

    void timer(){
        txlable.setText("");

        text = new Text("00:00:000");
        timeline2 = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                change(text);
            }
        }));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(false);
    }

    void change(Text text) {
        if(millis == 1000) {
            secs++;
            millis = 0;
        }
        if(secs == 60) {
            mins++;
            secs = 0;
        }
        text.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
                + (((secs/10) == 0) ? "0" : "") + secs + ":"
                + (((millis/10) == 0) ? "00" : (((millis/100) == 0) ? "0" : "")) + millis++);
        txlable.setText(String.valueOf(text.getText()));
    }



    Connection con;
    PreparedStatement myPsmt;
    int rst = 0;
    void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/testjvx", "root", "");
            System.out.println("complete");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void saveScore(){
        connect();
        String lev = "";
        if(boardsize == 4){
            lev = "Easy";
        }
        else if(boardsize == 6){
            lev = "Medium";
        }

        try {


            String query = "INSERT INTO gamescore (level, score) VALUES (?,?)";
            myPsmt= con.prepareStatement(query);

            myPsmt.setString(1, lev);
            myPsmt.setString(2, txlable.getText());


            rst = myPsmt.executeUpdate();

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}