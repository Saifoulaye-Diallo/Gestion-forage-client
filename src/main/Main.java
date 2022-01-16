package main;

import factory.Factory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    Factory factory = new Factory();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login/Login.fxml"));
        primaryStage.setTitle("LOGIN");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        }
    }

