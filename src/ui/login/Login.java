package ui.login;

import entities.User;
import factory.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tools.Notification;
import tools.Outils;
import java.io.IOException;

public class Login {
    @FXML
    private TextField email;
    @FXML
    private PasswordField pswd;
    private Factory factory = new Factory();
    public static User us ;
    public void connection(ActionEvent event) throws IOException {
       String em = email.getText();
       String pd = pswd.getText();
        if (em.equals("") || pd.equals(""))
        {
            Notification.NotiError("ERREUR", "Veillez saisir le login et le mot de passer!!");
        }else {
            try{
                us = factory.iUser.login(em,pd);
                if(us.getEtat() == false){
                   Outils.showErrorMessage("LOGIN","SALUT "+us.getNom()+" "+us.getPrenom()+"VOTRE COMPTE EST DESACTIVER VEILLEZ CONTACTER L\'ADMINISTRATEUR POUR PLUS DE DETAILS!!");
                }else if (us.getIsfirst() == true) {
                    Outils.load(event,"NOUVEAU MOT DE PASSE","../ui/newpassword/NewPassword.fxml");
                }else {
                    Notification.NotifSucces("LOGIN","BIENVENUE"+us.getNom()+" "+us.getPrenom());
                    if (us.getRoles().getNom().equals("ROLE_ADMIN")){
                        Outils.load(event,"MENU","../ui/menuAdmin/Menu.fxml");
                    }else if (us.getRoles().getNom().equals("ROLE_GESCLIENTELE")){
                        Outils.load(event,"MENU","../ui/menuGClientele/Menu.fxml");
                    }else if (us.getRoles().getNom().equals("ROLE_GESCOMMERCIALE")){
                        Outils.load(event,"MENU","../ui/menuGCommerciale/Menu.fxml");
                    }else if (us.getRoles().getNom().equals("ROLE_GESCOMPTEUR")){
                        Outils.load(event,"MENU","../ui/menuGCompteur/Menu.fxml");
                    }
                }
            }catch (Exception ex){
                email.setText("");
                pswd.setText("");
                Notification.NotiError("LOGIN","LOGIN OU MOT DE PASSE INCORRECT !!"+ex.getMessage());
            }
        }
    }
}
