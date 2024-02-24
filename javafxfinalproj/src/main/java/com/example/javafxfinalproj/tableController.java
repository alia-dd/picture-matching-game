package com.example.javafxfinalproj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class tableController {

    ObservableList<String> combData = FXCollections.observableArrayList();

    @FXML
    private ComboBox dpscomb;

    @FXML
    private TableColumn<classTable, String> score_l;

    @FXML
    private TableColumn<classTable, String> level;

    @FXML
    private TableView<classTable> score_table;

    @FXML
    private TableColumn<classTable, Integer> id;

    @FXML
    private Button btnHome;
    String selectedItem = "";



    @FXML
    public void initialize() throws FileNotFoundException {
        FileInputStream input2 = new FileInputStream("C:\\Users\\meyra\\Documents\\cashar\\java\\javafxfinalproj\\src\\main\\asset\\home1.png");
        Image image2 = new Image(input2);
        ImageView view2 = new ImageView(image2);
        view2.setFitWidth(20);
        view2.setFitHeight(20);

        connect();
        btnHome.setGraphic(view2);
        combFill();
        dpscomb.getSelectionModel().select(0);
        System.out.println((String) dpscomb.getSelectionModel().getSelectedItem());
        fillTable();



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

    @FXML
    void selectLevel(ActionEvent event) {
        connect();
        selectedItem = (String) dpscomb.getSelectionModel().getSelectedItem();
        ObservableList<classTable> score = FXCollections.observableArrayList();

        System.out.println(selectedItem);
        try {
            myPsmt = con.prepareStatement("SELECT * FROM gamescore  where level = ? ORDER BY score");
            myPsmt.setString(1, (String) selectedItem);
            rst = myPsmt.executeQuery();

            while (rst.next()){
                classTable ftd = new classTable();
                ftd.setId(rst.getString("id"));
                ftd.setLevel(rst.getString("level"));
                ftd.setScore(rst.getString("score"));
                score.add(ftd);
            }
            score_table.setItems(score);
            id.setCellValueFactory(new PropertyValueFactory<classTable, Integer>("id"));
            level.setCellValueFactory(f -> f.getValue().levelProperty());
            score_l.setCellValueFactory(f -> f.getValue().scoreProperty());

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void fillTable(){
        selectedItem = (String) dpscomb.getSelectionModel().getSelectedItem();
        ObservableList<classTable> score = FXCollections.observableArrayList();

        System.out.println(selectedItem);
        try {
            myPsmt = con.prepareStatement("SELECT * FROM gamescore  where level = ? ORDER BY score");
            myPsmt.setString(1, (String) selectedItem);
            rst = myPsmt.executeQuery();

            while (rst.next()){
                classTable ftd = new classTable();
                ftd.setId(rst.getString("id"));
                ftd.setLevel(rst.getString("level"));
                ftd.setScore(rst.getString("score"));
                score.add(ftd);
            }
            score_table.setItems(score);
            id.setCellValueFactory(new PropertyValueFactory<classTable, Integer>("id"));
            level.setCellValueFactory(f -> f.getValue().levelProperty());
            score_l.setCellValueFactory(f -> f.getValue().scoreProperty());

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    Connection con;
    PreparedStatement myPsmt;
    ResultSet rst = null;
    void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/testjvx", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void combFill(){

        try {


            // Query which needs parameters DISTINCT

            String query = "SELECT DISTINCT level FROM gamescore";
            myPsmt= con.prepareStatement(query);


            rst = myPsmt.executeQuery();
            while ((rst.next())) {
                combData.addAll(rst.getString("level"));
            }
            dpscomb.setItems(combData);


        } catch (Exception e) {System.out.println(e);}


    }

}
