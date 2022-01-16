package ui.menuGestionUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_fonction;
    @FXML
    private  Button btn_role;
    @FXML
    private TextField connected;
    public void allAction(ActionEvent event) throws IOException {
        if (event.getSource() == btn_user)
        {
            loadPage(event, "USER", "../user/user.fxml");
        }else if (event.getSource()== btn_fonction)
        {
            loadPage(event, "FONCTION","../fonction/fonction.fxml" );
        }else if (event.getSource()== btn_role)
        {
            loadPage(event, "FONCTION","../role/role.fxml" );
        }
    }

    private void loadPage(ActionEvent event, String title, String url )throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
