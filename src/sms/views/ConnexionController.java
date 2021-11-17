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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sms.dao.AffectationClasseDao;
import sms.dao.ClasseDao;
import sms.dao.InscriptionDao;
import sms.dao.UserDao;
import sms.entities.User;
import sms.services.SmsInterface;
import sms.services.SmsService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ConnexionController implements Initializable {
    
    private SmsService service =new SmsService();
    private User userConnect;
    private static ConnexionController ctrl;

    public static ConnexionController getCtrl() {
        return ctrl;
    }

    public User getUserConnect() {
        return userConnect;
    }

    @FXML
    private TextField txtLogin;
    @FXML
    private Button btnConnexion;
    @FXML
    private Label lblErreur;
    @FXML
    private Button btnAnnuler;
    @FXML
    private PasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblErreur.setVisible(false);
        service.setUserDao(new UserDao());
        ctrl=this;
    }    

    @FXML
    private void handleConnexion() throws IOException {
        if(txtLogin.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty() ){
            lblErreur.setText("Login ou Mot de Passe Obligatoire");
            lblErreur.setVisible(true);
        }else{
            userConnect=service.connexion(txtLogin.getText(), txtPassword.getText());
            if(userConnect==null){
                 lblErreur.setText("Login ou Mot de Passe Incorrect");
                  lblErreur.setVisible(true);
            }else{
                this.btnAnnuler.getScene().getWindow().hide();
                 AnchorPane root;
               
                        root = FXMLLoader.load(getClass().getResource("home.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(scene);
                        stage.show();
               
        
       
            }
        }
    }

    @FXML
    private void handleClearFied(ActionEvent event) {
        txtPassword.clear();
        txtLogin.clear();
    }
    
}
