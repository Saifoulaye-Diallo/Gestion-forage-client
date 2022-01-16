package ui.newpassword;

import entities.User;
import factory.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tools.Notification;
import tools.Outils;
import ui.login.Login;


import java.net.URL;
import java.util.ResourceBundle;

public class NewPassword implements Initializable {
    @FXML
    private Button btn_valider;
    @FXML
    private TextField ancien;
    @FXML
    private TextField nouveau;
    @FXML
    private TextField confirmation;
    Factory factory = new Factory();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void changer(ActionEvent event){
        User us = Login.us;
        if (ancien.getText().equals("") || nouveau.getText().equals("") || confirmation.getText().equals("")){
            Outils.showErrorMessage("ERREUR","VEILLER RENSEIGNER TOUT LES CHAMPS !!!");
        }else{
            if (!us.getPassword().equals(ancien.getText())){
                Outils.showErrorMessage("ERREUR","L\'ANCIEN MOT DE PASSE EST INCORRECT !!!");
            }else if (!nouveau.getText().equals(confirmation.getText())){
                Outils.showErrorMessage("ERREUR","MOT DE PASSE DE CONFIRMATION INCORRECT !!!");
            }else {
                User users= new User();
                users.setId(us.getId());
                users.setEtat(true);
                users.setEmail(us.getEmail());
                users.setFonction(us.getFonction());
                users.setPassword(nouveau.getText());
                users.setNom(us.getNom());
                users.setPrenom(us.getPrenom());
                users.setRoles(us.getRoles());
                users.setIsfirst(false);
                try{
                    factory.iUser.update(users);
                    if (Login.us.getRoles().getNom().equals("ROLE_ADMIN")){
                        Outils.load(event,"MENU","../ui/menuAdmin/Menu.fxml");
                    }else if (Login.us.getRoles().getNom().equals("ROLE_GESCLIENTELE")){
                        Outils.load(event,"MENU","../ui/menuGClientele/Menu.fxml");
                    }else if (Login.us.getRoles().getNom().equals("ROLE_GESCOMMERCIALE")){
                        Outils.load(event,"MENU","../ui/menuGCommerciale/Menu.fxml");
                    }else if (Login.us.getRoles().getNom().equals("ROLE_GESCOMPTEUR")){
                        Outils.load(event,"MENU","../ui/menuGCompteur/Menu.fxml");
                    }
                    Notification.NotifSucces("MODIFICATION","MOT DE PASSE MODIFIER AVEC SUCCES !!!");
                }catch (Exception e){
                    Notification.NotiError("ERREUR","ERREUR L\'ORS DE L\'AJOUT !!!");
                }
            }
        }
    }

    public void allAction(ActionEvent event) {
        if (event.getSource() == btn_valider){
            changer(event);
        }
    }
}
