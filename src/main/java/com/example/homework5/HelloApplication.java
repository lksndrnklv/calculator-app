package com.example.homework5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml")).load();

        stage.setTitle("Calculator");
        stage.setScene(new Scene(root, 300, 400));
        stage.setResizable(false);
        stage.show();
    }
}
