/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HomeController implements Initializable {

    @FXML
    private Text txtProfil;
    @FXML
    private Text txtBienvenue;
    @FXML
    private ComboBox<String> cboAnnee;
    @FXML
    private AnchorPane anchorContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // txtProfil.setText("Profil,"+ConnexionController.getCtrl().getUser().getRole());
        loadComboBox();
        try {
            loadView("v_classe");
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
    
    private void loadComboBox(){
         cboAnnee.getItems().add("2021-2022");
         cboAnnee.getItems().add("2020-2021");
         cboAnnee.getItems().add("2019-2020");
    }

    private void loadView(String view) throws IOException{
         AnchorPane   root = FXMLLoader.load(getClass()
                              .getResource("/views/"+view+".fxml"));
         anchorContent.getChildren().clear();
         anchorContent.getChildren().add(root);
    }
    @FXML
    private void handleLoadViewAccueil(ActionEvent event) throws IOException {
           loadView("v_classe");
    }

    @FXML
    private void handleLoadViewClasse(ActionEvent event) throws IOException {
           loadView("v_classe");
    }

    @FXML
    private void handleLoadViewInscription(ActionEvent event) throws IOException {
         loadView("v_inscription");
    }

    @FXML
    private void handleLoadViewAffectation(ActionEvent event) {
    }

    @FXML
    private void handleLoadViewConnexion(ActionEvent event) {
    }
    
}
