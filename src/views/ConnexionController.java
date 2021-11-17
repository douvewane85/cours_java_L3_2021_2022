/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Service;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ConnexionController implements Initializable {

    private Service service=new Service();
     private  User user;
     private static ConnexionController ctrl;
    
    @FXML
    private Text txtErreur;
    @FXML
    private TextField txtfLogin;
    @FXML
    private TextField txtpPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtErreur.setVisible(false);
        ctrl=this;
    }    

    @FXML
    private void handleConnexion(ActionEvent event) {
        String login=txtfLogin.getText().trim();
        String password=txtpPassword.getText().trim();
        if(login.isEmpty() || password.isEmpty()){
            txtErreur.setText("Login ou Mot de Passe Obligatoire");
            txtErreur.setVisible(true);
        }else{
              user= service.seConnecter(login, password);
               if(user==null){
                   txtErreur.setText("Login ou Mot de Passe Incorrect");
                   txtErreur.setVisible(true);
               }else{
                   //Cache la Fenetre de Connexion
                   this.txtErreur.getScene().getWindow().hide();
                   //Charger la Fenetre Home
                   AnchorPane root;
                  try {
                      root = FXMLLoader.load(getClass().getResource("/views/v_home.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(scene);
                        stage.show();
                  } catch (IOException ex) {
                      Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
                   
               }
          }
        
    }

    @FXML
    private void handleClear(ActionEvent event) {
        txtfLogin.clear();
        txtpPassword.clear();
         txtErreur.setVisible(false);
    }

    public User getUser() {
        return user;
    }

    public static ConnexionController getCtrl() {
        return ctrl;
    }
    
}
