package ui.client;

import entities.Village;
import factory.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.Notification;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class Client implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField telephone;
    @FXML
    private Button btn_annuler;
    @FXML
    private Button btn_enregistrer;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private ComboBox<Village> choice_village;
    @FXML
    private TableView<entities.Client> tbl_client;
    @FXML
    private TableColumn<entities.Client,String> cl_nom;
    @FXML
    private TableColumn<entities.Client,String> cl_prenom;
    @FXML
    private TableColumn<entities.Client,String> cl_numero;
    @FXML
    private TableColumn<entities.Client,String> cl_village;


    Factory factory = new Factory();
    public void allAction(ActionEvent event) throws IOException {
        if (event.getSource()== btn_annuler){
            annuler();
        }else if (event.getSource() == btn_enregistrer){
            add();
        }else if (event.getSource() == btn_modifier){
            modifier();
        }else if (event.getSource() == btn_supprimer){
            supprimer();
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
        ObservableList<entities.Client> clients = FXCollections.observableArrayList(factory.iClient.getlistClient());
        cl_nom.setCellValueFactory(new PropertyValueFactory<entities.Client,String>("nom"));
        cl_prenom.setCellValueFactory(new PropertyValueFactory<entities.Client,String>("prenom"));
        cl_numero.setCellValueFactory(new PropertyValueFactory<entities.Client,String>("numero"));
        cl_village.setCellValueFactory(new PropertyValueFactory<entities.Client,String>("village"));
        tbl_client.setItems(clients);
        choice_village();
    }
    public int select()
    {
        entities.Client client = tbl_client.getSelectionModel().getSelectedItem();
        return client.getId();
    }


    public void add() throws RemoteException {
        entities.Client c = new entities.Client();
        if (modifier()== null){
        String nomC = nom.getText();
        String prenomC = prenom.getText();
        String adresseC = adresse.getText();
        String numeroC = telephone.getText();
        c.setNom(nomC);
        c.setPrenom(prenomC);
        c.setAdresse(adresseC);
        c.setNumero(numeroC);
        c.setCpt(false);
        c.setAbone(false);
            c.setVillage(choice_village.getValue());
            if (nomC.equals("")  || prenomC.equals("") || adresseC.equals("") || numeroC.equals("") || choice_village == null){
            Notification.NotiError("ERROR","VEILLER REMPLIR TOUT LES CHAMPS DU FORMULAIRE !!!");
            }else {
                factory.iClient.addClient(c);
                actualiser();
                annuler();
                Notification.NotifSucces("AJOUT","AJOUTER AVEC SUCCES!!!");
            }
        }else {
            Integer id = modifier().getId();
            String nomCv = nom.getText();
            String prenomCv = prenom.getText();
            String adresseCv = adresse.getText();
            String numeroCv = telephone.getText();
            c.setId(id);
            c.setNom(nomCv);
            c.setPrenom(prenomCv);
            c.setAdresse(adresseCv);
            c.setNumero(numeroCv);
            if (nomCv.equals("")  || prenomCv.equals("") || adresseCv.equals("") || numeroCv.equals("") || choice_village== null ){
                Notification.NotiError("ERROR","VEILLER REMPLIR TOUT LES CHAMPS DU FORMULAIRE !!!");
            }else {
                factory.iClient.updateClient(c);
                actualiser();
                annuler();
                Notification.NotifSucces("MODIFICATION","MODIFIER AVEC SUCCES!!!");
            }
        }
    }
    public void choice_village() throws RemoteException {
        ObservableList<Village> villages = FXCollections.observableArrayList(factory.iVillage.getlistVillage());
        choice_village.setItems(villages);
    }

    public void annuler(){
        nom.setText("");
        prenom.setText("");
        adresse.setText("");
        telephone.setText("");
        choice_village.setValue(null);
    }

    public entities.Client modifier()
    {
        entities.Client client = tbl_client.getSelectionModel().getSelectedItem();;
        if (client == null){
            Notification.NotiError("ERROR","VEILLER SELECTIONNER LA LIGNE A SUPPRIMER !!");
        }else {
            nom.setText(client.getNom());
            prenom.setText(client.getPrenom());
            adresse.setText(client.getAdresse());
            telephone.setText(client.getNumero());
            return client;
        }
        return null;
    }

    public void supprimer() throws RemoteException {
        entities.Client client = tbl_client.getSelectionModel().getSelectedItem();;
        if (client == null){
            Notification.NotiError("ERROR","VEILLER SELECTIONNER LA LIGNE A SUPPRIMER !!");
        }else {
            factory.iClient.deleteClient(client.getId());
            actualiser();
            Notification.NotifSucces("SUPPRESSION","SUPPRIMER AVEC SUCCES!!!");
        }
    }



}
