package com.example.studentregapp;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button btnAddCls;

    @FXML
    private Button btnAddMen;

    @FXML
    private Button btnStuReg;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAddCls.setOnAction(e -> onAddClassClick());
        btnAddMen.setOnAction(e -> {
            onAddMentorClick();
        });

        btnStuReg.setOnAction(e -> onRegButtonClick());


    }
    private void addMakeFade() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> onAddClassClick());
        fadeTransition.play();
    }

    private void onRegButtonClick() {
        Stage homeStage = (Stage) btnStuReg.getScene().getWindow();
        homeStage.setUserData(this);
        FXMLLoader fxmlLoader = new FXMLLoader(StudentTableController.class.getResource("Student-view.fxml"));
        try {
            Scene studentScene = new Scene(fxmlLoader.load());
            homeStage.setScene(studentScene);
            homeStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @FXML
    private void onAddClassClick() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(ClassViewController.class.getResource("Class-view.fxml"));
        try {
            Scene addCls = new Scene(fxmlLoader.load());
            stage.setScene(addCls);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


    @FXML
    private void onAddMentorClick() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MentorController.class.getResource("Mentor-view.fxml"));
        try {
            Scene mentorScene = new Scene(fxmlLoader.load());
            stage.setScene(mentorScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

