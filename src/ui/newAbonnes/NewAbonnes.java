package ui.newAbonnes;

import entities.Abonnement;
import entities.Compteur;
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
import tools.Outils;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class NewAbonnes implements Initializable {
    @FXML
    private TableView<Abonnement> tbl_client;
    @FXML
    private TableColumn<Abonnement, String> cl_numero;
    @FXML
    private TableColumn<Abonnement, String> cl_client;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_compteur;
    @FXML
    private Button btn_enregistrer;
    @FXML
    private Button btn_annuler;
    @FXML
    private TextField numero;
    Factory factory = new Factory();

    public void allAction(ActionEvent event) throws RemoteException {
        if (event.getSource()== btn_annuler){
           // annuler();
        }else if (event.getSource() == btn_enregistrer){
           add();
        }else if (event.getSource() == btn_supprimer){

        }else if (event.getSource() == btn_compteur){
            compteur();
        }else {

        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            actualiser();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void actualiser() throws RemoteException {

        ObservableList<Abonnement> abo = FXCollections.observableArrayList();
        try {
            for (Abonnement ab: factory.iAbonnement.getlistAbonnement() ) {
                if (ab.getCpt() == false){
                    abo.add(ab);
                }

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        cl_numero.setCellValueFactory(new PropertyValueFactory<Abonnement,String>("numero"));
        cl_client.setCellValueFactory(new PropertyValueFactory<Abonnement,String>("client"));
        tbl_client.setItems(abo);
    }

    public void compteur(){
        entities.Abonnement ab = tbl_client.getSelectionModel().getSelectedItem();;
        if (ab == null){
            Notification.NotiError("ERREUR","VEILLER SELECTIONER UN CLIENT !!");
        }else {
            numero.setText(numCpt());
        }
    }
    public String numCpt(){
        Calendar now = Calendar.getInstance();
        return "CPT"+now.get(Calendar.DATE)+""+(now.get(Calendar.MONTH) + 1)+""
                +now.get(Calendar.YEAR)+""+now.get(Calendar.HOUR_OF_DAY)+""
                +now.get(Calendar.MINUTE)+""+now.get(Calendar.SECOND);
    }
    public Abonnement numAbon() throws RemoteException {
        entities.Abonnement ab = tbl_client.getSelectionModel().getSelectedItem();
        return ab;
    }
    public void add() throws RemoteException {
        Compteur cpt = new Compteur();
        String numCptt = numero.getText();
        if (numCptt.equals("")){
            Outils.showErrorMessage("ERREUR","VEILLER SELECTIONNER UN CLIENT ET APPUYER SUR LE BOUTTON COMPTEUR !!!");
        }else {
            Abonnement a = tbl_client.getSelectionModel().getSelectedItem();
            cpt.setNumeroCompteur(numCptt);
            cpt.setAbonnement(a);
            cpt.setIsValid("ACTIF");
            factory.iCompteur.addCompteur(cpt);
            numero.setText("");
            Abonnement ab = new Abonnement();
            ab.setCpt(true);
            ab.setNumero(a.getNumero());
            ab.setClient(a.getClient());
            ab.setTextDescript(a.getTextDescript());
            ab.setDateAbonnement(a.getDateAbonnement());
            ab.setId(a.getId());
            factory.iAbonnement.updateAbo(ab);
            actualiser();
            Notification.NotifSucces("AJOUT","AJOUTER AVEC SUCCES !!!");
        }
    }

}
