package ui.fonction;

import entities.Fonction;
import factory.Factory;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.Notification;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class FonctionController implements Initializable {
    //DECLARATION DES VARIABLES
    @FXML
    private TableView<Fonction> tabfonction;
    @FXML
    private TableColumn<Fonction, Integer> id;
    @FXML
    private TableColumn<Fonction, String> nomfonction;
    @FXML
    private TextField nom;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_actualiser;
    Factory factory = new Factory();

    public FonctionController() throws RemoteException {
    }

    // FONCTION PRINCIPALE
    @FXML
    public void allAction(ActionEvent event) throws RemoteException, NotBoundException {
        if (event.getSource() == btn_ajout)
        {
            add();
        }else if (event.getSource() == btn_modifier)
        {
            update();
        }else if (event.getSource() == btn_actualiser)
        {
            actualiser();
        }else if (event.getSource() == btn_supprimer)
        {
            delete();
        }
    }

    //AJOUT DE LA FONCTION
    public void add() throws RemoteException, NotBoundException {
        Fonction fonction = tabfonction.getSelectionModel().getSelectedItem();
        int maj = 0;
        if(fonction != null){
            maj = update();
        }
        if (maj != 0){
            Fonction fn = new Fonction();
            fn.setId(update());
            fn.setNom(nom.getText());
            if (nom.getText().equals("")){
                Notification.NotiError("MODIFICATION", "Veuiller saisir le nouveau nom du role !!");
            }else {
                try{
                    factory.iFonction.update(fn);
                    actualiser();
                    nom.setText("");
                    maj = 0;
                    Notification.NotifSucces("MODIFICATION","MODIFIER AVEC SUCCES!!!");
                }catch (Exception ex){
                    Notification.NotifSucces("MODIFICATION","ERREUR DE MODIFICATION!!!"+ex.getMessage());
                }
            }
        }else {
            String nomfn= nom.getText();
            Fonction fnadd = new Fonction();
            fnadd.setNom(nomfn);
            try{
                if (nom.getText().equals("")){
                    Notification.NotiError("AJOUT","VEILLEZ REMPLIR LE CHAMPS");
                }else {
                    factory.iFonction.add(fnadd);
                    nom.setText("");
                    actualiser();
                    Notification.NotifSucces("AJOUT","AJOUTER AVEC SUCCES!!!");
                }
            }catch (Exception ex ){
                Notification.NotifSucces("AJOUT","ERREUR D'AJOUT!!!"+ex.getMessage());
            }
        }
    }

    //MODIFICATION D'UNE FONCTION
    public int update() throws RemoteException, NotBoundException {
        Fonction fonction = tabfonction.getSelectionModel().getSelectedItem();
        if (fonction != null){
            return  fonction.getId();
        }else {
            Notification.NotiError("MODIFICATION","VEILLEZ SLECTIONNER LALIGNE A MODIFIER!!!");
        }
        return 0;
    }

    //SUPPRESSIOON D'UNE FONCTION
    public void delete() throws RemoteException, NotBoundException {
        Fonction fonction = tabfonction.getSelectionModel().getSelectedItem();;
        if (fonction != null){
            try{
                factory.iFonction.delete(fonction.getId());
                Notification.NotifSucces("SUPPRESSION","SUPPRIMER AVEC SUCCES!!");
                actualiser();
            }catch (Exception ex)
            {
                Notification.NotiError("SUPPRESSION","ERREUR LORS DE LA SUPPRESSION !!!"+ex.getMessage());
            }
        }else {
            Notification.NotiError("SUPPRESSION","VEILLEZ SELECTION UN ROLE !!!");
        }
    }


    //INITIALISATION DU TABLEAU
    private ObservableList<Fonction> fonctions = FXCollections.observableArrayList(factory.iFonction.getAll());
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            actualiser();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //REMPLISSAGE DU TABLEAU
    public void actualiser() throws RemoteException {
        ObservableList<Fonction> fonctions = FXCollections.observableArrayList(factory.iFonction.getAll());
        id.setCellValueFactory(new PropertyValueFactory<Fonction,Integer>("id"));
        nomfonction.setCellValueFactory(new PropertyValueFactory<Fonction,String>("nom"));
        tabfonction.setItems(fonctions);
    }
}
