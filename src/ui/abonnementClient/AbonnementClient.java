package ui.abonnementClient;

import entities.Abonnement;
import entities.Client;
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
import java.rmi.RemoteException;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class AbonnementClient implements Initializable {
    @FXML
    private TableView<Abonnement> tbl_abonnement;
    @FXML
    private Button btn_supprimer;
    @FXML
    private TableColumn<entities.Abonnement,String> cl_numeroAbon;
    @FXML
    private TableColumn<entities.Abonnement, Date> cl_date;
    @FXML
    private TableColumn<entities.Abonnement,String> cl_description;
    @FXML
    private ComboBox<Client> combo_client;
    @FXML
    private Button search;
    Factory factory = new Factory();
    public void allAction(ActionEvent event) throws SQLException, RemoteException {
        if (event.getSource() == search){
            selection();
        }else if (event.getSource() == btn_supprimer){
            supprimer();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            selection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            combo_client();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void abonnements(int id) throws SQLException, RemoteException {
        ObservableList<entities.Abonnement> listAb = FXCollections.observableArrayList();
        for (entities.Abonnement a : factory.iAbonnement.getlistAbonnement()) {
            if (a.getClient().getId() == id )
                listAb.add(a);
        }
        cl_numeroAbon.setCellValueFactory(new PropertyValueFactory<entities.Abonnement,String>("numero"));
        cl_date.setCellValueFactory(new PropertyValueFactory<entities.Abonnement, Date>("dateAbonnement"));
        cl_description.setCellValueFactory(new PropertyValueFactory<entities.Abonnement, String>("textDescript"));
        tbl_abonnement.setItems(listAb);
    }
    public void combo_client() throws RemoteException {
        ObservableList<Client> clients = FXCollections.observableArrayList(factory.iClient.getlistClient());
        combo_client.setItems(clients);
    }

    public void selection() throws SQLException, RemoteException {
        Client client = combo_client.getValue();
        if (client == null ){
        }else {
                abonnements(client.getId());
        }
    }
    public void supprimer() throws RemoteException {
        Abonnement a = tbl_abonnement.getSelectionModel().getSelectedItem();
        if (a == null){
            Notification.NotiError("ERREUR","VEILLEZ SAISIR L\'ABONNEMENT A SUPPRIMER !!");
        }else {
            try{
                factory.iAbonnement.deleteAbo(a.getId());
            }catch (Exception ex){
                Notification.NotifSucces("SUCCES","ABONNEMENT SUPPRIMER AVEC SUCCES !!!"+ex.getMessage());
            }
        }
    }
}