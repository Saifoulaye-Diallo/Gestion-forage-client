package tools;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Outils {
    public static void showConfirmatinMessage(String titre,String message){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle(titre);
        a.setContentText(message);
        a.showAndWait();
    }
    public static void showErrorMessage(String titre,String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private  void loadPage(ActionEvent event , String title,String url) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
    public static void load(ActionEvent event,String title,String url) throws IOException {
        new Outils().loadPage(event, title, url);
    }
    private  void loadSubPage(ActionEvent event , String title,String url) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.showAndWait();
    }
    public static void loadSub(ActionEvent event,String title,String url) throws IOException {
        new Outils().loadSubPage(event, title, url);
    }
}
