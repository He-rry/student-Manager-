package com.example.studentregapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentRegApp extends Application implements Initializable {
    @FXML
    private Label lblPassword;

    @FXML
    private Label lblusername;


    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtUerPass;

    private final DataStore store = DataStore.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnLogin.setOnAction(e -> setBtnLoginClick());


    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentRegApp.class.getResource("Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(":3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    public void setBtnLoginClick() {

        if (txtUsername.getText().toString().equals("Herry") && txtUerPass.getText().toString().equals("003")) {


            Stage currentStage = (Stage) btnLogin.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(StudentRegApp.class.getResource("Home.fxml"));
            Scene sigUpScene;
            try {
                sigUpScene = new Scene(fxmlLoader.load());
                currentStage.setScene(sigUpScene);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (txtUsername.getText().isEmpty() && txtUerPass.getText().isEmpty()) {
            lblusername.setText("Please enter Username");
            lblPassword.setText("Please enter Password");
        } else {
            lblusername.setText("Incorrect Username");
            lblPassword.setText("Incorrect Password");
        }

    }

}