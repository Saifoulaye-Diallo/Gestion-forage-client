package ui.user;

import entities.Fonction;
import entities.Role;
import entities.User;
import factory.Factory;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.Notification;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController implements Initializable {
    @FXML
    private TableView<User> user;
    @FXML
    private TableColumn<User, Integer> userid;
    @FXML
    private TableColumn<User,String> usernom;
    @FXML
    private  TableColumn<User,String> userprenom;
    @FXML
    private TableColumn<User,String> useremail;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<Fonction> comboFonction;
    @FXML
    private ComboBox<Role> comboRole;
    @FXML
    private Button btn_valider;
    @FXML
    private Button btn_actualiser;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_desactiver;
    Factory factory = new Factory();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ComboFonction();
            listUser();
            ComboRole();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void allAction(ActionEvent event) throws Exception {
        if (event.getSource() == btn_valider)
        {
            add();
        }else if (event.getSource() == btn_modifier)
        {
            update();
        }else if (event.getSource() == btn_actualiser)
        {
        }else if (event.getSource() == btn_supprimer)
        {
            delete();
        }else  if (event.getSource() == btn_desactiver){
            desactiver();
        }
    }
    public void add() throws Exception {
        User user = new User();
        int modifier = update();
        if (modifier != 0 )
        {
            if (nom.getText().equals("") || prenom.getText().equals("")|| email.getText().equals("")
            || comboFonction.getValue() == null ){
                Notification.NotiError("ERREUR","VEILLEZ REMPLIR TOUT LES CHAMPS!!!");
            }else {
                user.setId(modifier);
                user.setNom(nom.getText());
                user.setPrenom(prenom.getText());
                user.setEmail(email.getText());
                user.setEtat(true);
                user.setPassword("gestionforage");
                user.setIsfirst(true);
                user.setFonction(comboFonction.getValue());
                user.setRoles(comboRole.getValue());
                try{
                    factory.iUser.update(user);
                    nom.setText("");
                    prenom.setText("");
                    email.setText("");
                    comboFonction.setValue(null);
                    listUser();
                    Notification.NotifSucces("MISE A JOUR","MODIFIER ABEC SUCCES!!!");
                }catch (Exception ex){
                    Notification.NotiError("MISE A JOUR","ERREUR !!"+ex.getMessage());
                }
            }
        }else {
            if (nom.getText().equals("") || prenom.getText().equals("")|| email.getText().equals("")
                    || comboFonction.getValue() == null ){
                Notification.NotiError("ERREUR","VEILLEZ REMPLIR TOUT LES CHAMPS!!!");
            }else{
                user.setNom(nom.getText());
                user.setPrenom(prenom.getText());
                user.setEmail(email.getText());
                user.setPassword("gestionforage");
                user.setFonction(comboFonction.getValue());
                user.setRoles(comboRole.getValue());
                user.setIsfirst(true);
                user.setEtat(true);
                try{
                    if (validate(email.getText())){
                        factory.iUser.add(user);
                        nom.setText("");
                        prenom.setText("");
                        email.setText("");
                        comboFonction.setValue(null);
                        comboRole.setValue(null);
                        listUser();
                        Notification.NotifSucces("AJOOUT","AJOUTER AVEC SUCCES!!!");
                    }else {
                        Notification.NotiError("AJOOUT","MAIL INCORRECT!!!");
                    }
                }catch (Exception ex){
                    Notification.NotiError("AJOUT","ERREUR LORS DE L'AJOUT!!!");
                }
            }
        }
    }
    public void ComboFonction() throws RemoteException{
        ObservableList<Fonction> fonctions = FXCollections.observableArrayList(factory.iFonction.getAll());
        comboFonction.setItems(fonctions);
    }
    public void ComboRole() throws RemoteException{
        ObservableList<Role> roles = FXCollections.observableArrayList(factory.iRole.getAll());
        comboRole.setItems(roles);
    }

    public void listUser() throws RemoteException {
        ObservableList<User> users = FXCollections.observableArrayList(factory.iUser.getAll());
        userid.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        usernom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
        userprenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        useremail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        user.setItems(users);
    }

    public void delete() throws RemoteException, NotBoundException {
        User userselect = user.getSelectionModel().getSelectedItem();
        if (userselect != null){
            if (factory.iUser.delete(userselect.getId()) == 0)
            {
                listUser();
                Notification.NotifSucces("SUPPRESSION","SUPPRIMER AVEC SUCCES!!!");
            }else {
                Notification.NotiError("SUPPRESSION","ERREUR DE SUPPRESSION!!!");
            }
        }else {
            Notification.NotiError("SUPPRESSION","VEILLEZ SELECTIONNER UN UTILISATEUR!!");
        }
    }

    public int update() throws RemoteException, NotBoundException {
        User userselect = user.getSelectionModel().getSelectedItem();
        if (userselect == null)
        {
            return 0;
        }else {
            return userselect.getId();
        }
    }
    public static final Pattern isValidMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean validate(String email) {
        Matcher matcher = isValidMail.matcher(email);
        return matcher.find();
    }

    public void desactiver(){
        User users = new User();
        User us = user.getSelectionModel().getSelectedItem();
        if (us == null){
            Notification.NotiError("ERREUR","VEILLEZ CHOISIR L\'UTILISATEUR A DESACTIVER !!!");
        }else {
            users.setId(us.getId());
            users.setEtat(false);
            users.setEmail(us.getEmail());
            users.setFonction(us.getFonction());
            users.setPassword(us.getPassword());
            users.setNom(us.getNom());
            users.setPrenom(us.getPrenom());
            users.setRoles(us.getRoles());
            users.setIsfirst(us.getIsfirst());
            try{
                factory.iUser.update(users);
                Notification.NotifSucces("MODIFICATION","MODIFIER AVEC SUCCES !!!");
            }catch (Exception e){
                Notification.NotiError("ERREUR","ERREUR L\'ORS DE L\'AJOUT !!!");
            }
        }
    }
}
