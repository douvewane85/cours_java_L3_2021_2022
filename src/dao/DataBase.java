/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DataBase {
     private String url="jdbc:mysql://localhost:3306/sms";
     private String user="root";
     private String password="";
     Connection cnx=null;
     //Objet d'execution des requetes preparées
    private  PreparedStatement ps=null;
     
   public void openConnexion(){
       try {
           //1-Charger le Driver
           Class.forName("com.mysql.cj.jdbc.Driver");
           //2-Ouverture de la connexion
          cnx=DriverManager.getConnection(url,user,password);
           System.out.println("Connexion etablie");
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
             Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
         }
   }
   
   public void closeConnexion(){
       if(cnx!=null){
           try {
               cnx.close();
           } catch (SQLException ex) {
               Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
   
   public void initPreparedStament(String sql){
       //Verifier si requete commence par insert
       if(sql.toLowerCase().startsWith("insert")){
           try {
               ps=cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           } catch (SQLException ex) {
               Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else{
           try {
               ps=cnx.prepareStatement(sql);
           } catch (SQLException ex) {
               Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
    public int executeUpdate(String sql){
        int nbreLigne=0;
        
         try {
            nbreLigne= ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
         }
      return nbreLigne;
   }
    
     public ResultSet executeSelect(String sql){
         ResultSet rs=null;
        
         try {
            rs= ps.executeQuery();
         } catch (SQLException ex) {
             Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
         }
      return rs;
   }

    public PreparedStatement getPs() {
        return ps;
    }
     
     
}