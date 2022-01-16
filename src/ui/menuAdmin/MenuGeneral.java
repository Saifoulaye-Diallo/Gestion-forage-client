package ui.menuAdmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tools.Outils;

import java.io.IOException;

public class MenuGeneral {
    @FXML
    private Button btn_client ;
    @FXML
    private Button btn_abonnement ;
    @FXML
    private Button btn_compteur ;
    @FXML
    private Button btn_facture ;
    @FXML
    private Button btn_new_abonnement;
    @FXML
    private Button btn_village;
    @FXML
    private Button btn_reglement;
    @FXML
    private Button btn_utilisateur;
    @FXML
    private Button btn_compteurs;
    @FXML
    void menuAction(ActionEvent event) throws IOException {
        if (event.getSource()== btn_client){
            Outils.loadSub(event,"CLIENT","../ui/client/Client.fxml");
        }else if (event.getSource() == btn_abonnement){
            Outils.loadSub(event,"ABONNEMENTS","../ui/abonnementClient/AbonnementClient.fxml");
        }else if (event.getSource() == btn_new_abonnement){
            Outils.loadSub(event,"ABONNEMENT","../ui/abonnement/Abonnement.fxml");
        }else if (event.getSource() == btn_village){
            Outils.loadSub(event,"VILLAGE","../ui/village/village.fxml");
        }else if (event.getSource() == btn_compteur){
            Outils.loadSub(event,"COMPTERU","../ui/newAbonnes/NewAbonnes.fxml");
        }else if (event.getSource() == btn_facture){
            Outils.loadSub(event,"FACTURE","../ui/facture/Facture.fxml");
        }else if (event.getSource() == btn_reglement){
            Outils.loadSub(event,"REGLEMENT","../ui/reglement/Reglement.fxml");
        }else if (event.getSource() == btn_utilisateur){
            Outils.loadSub(event,"UTILISATEUR","../ui/menuGestionUser/menuUser.fxml");
        }else if (event.getSource() == btn_compteurs){
            Outils.loadSub(event,"COMPTEURS","../ui/compteur/compteurs.fxml");
        }
    }
}
