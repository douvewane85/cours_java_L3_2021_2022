/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sms.dao.AnneeDao;
import sms.services.SmsService;

/**
 *
 * @author USER
 */
public class HomeController implements Initializable {
    
    SmsService service =new SmsService();
    private Label label;
    @FXML
    private AnchorPane anchorContent;
    
   ObservableList<String> anneeScolaires;
    @FXML
    private ComboBox<String> cboAnneeScolaire;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnInscription;
    @FXML
    private Button btnClasse;
    @FXML
    private Button btnProfesseur;
    
    private String anneeScolaireEncours;
    @FXML
    private Text lblBienvenue;
    @FXML
    private Text lblProfil;

    public String getAnneeScolaireEncours() {
        return anneeScolaireEncours;
    }

    public void setAnneeScolaireEncours(String anneeScolaireEncours) {
        this.anneeScolaireEncours = anneeScolaireEncours;
    }
   private static  HomeController ctrl;

    public static HomeController getCtrl() {
        return ctrl;
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service.setAnneeDao(new AnneeDao());
        anneeScolaires=FXCollections.observableArrayList(service.listerAnneeScolaire());
        cboAnneeScolaire.setItems(anneeScolaires);
        cboAnneeScolaire.getSelectionModel().selectFirst();
        this.ctrl=this;
        lblBienvenue.setText("Bienvenue,"+ConnexionController.getCtrl().getUserConnect().getNom_complet());
        lblProfil.setText(ConnexionController.getCtrl().getUserConnect().getRole());
        isProfesseur();
        loadView("inscription");
         
    }    
    
    public void loadView(String view){
         anneeScolaireEncours=cboAnneeScolaire.getSelectionModel().getSelectedItem();
            AnchorPane root;   
         try {
            root = FXMLLoader.load(getClass().getResource(view+".fxml"));
             anchorContent.getChildren().clear();
            anchorContent.getChildren().add((Node)root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleLoadDashboard(ActionEvent event) {
    }

    @FXML
    private void handleLoadAccueil(ActionEvent event) {
         loadView("inscription");
    }

    @FXML
    private void handleLoadInscription(ActionEvent event) {
         loadView("inscription");
    }

    @FXML
    private void handleLoadClasse(ActionEvent event) {
         loadView("classe");
    }

    @FXML
    private void handleLoadProfesseur(ActionEvent event) {
         loadView("professeur");
    }

    @FXML
    private void handleSelectAnneeScolaire(ActionEvent event) {
        setAnneeScolaireEncours(cboAnneeScolaire.getSelectionModel().getSelectedItem());
    }
    
    private void isRP(){
        if(ConnexionController.getCtrl().getUserConnect().getRole().compareTo("ROLE_RP")==0)
            disabledBtnMenu(false);
    }
     private void isAC(){
        if(ConnexionController.getCtrl().getUserConnect().getRole().compareTo("ROLE_AC")==0){
             disabledBtnMenu(false);
             btnClasse.setDisable(true); 
        }
           
    }
     private void isProfesseur(){
        if(ConnexionController.getCtrl().getUserConnect().getRole().compareTo("ROLE_PROFESSEUR")==0){
              disabledBtnMenu(false);
               btnClasse.setDisable(true); 
               btnInscription.setDisable(true);
              
        }
           
    }
     
      
    private void disabledBtnMenu(boolean active){
        btnAccueil.setDisable(active);
        btnClasse.setDisable(active);
        btnDashboard.setDisable(active);
        btnInscription.setDisable(active);
        btnProfesseur.setDisable(active);
    }
}
