/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sms.entities.Classe;
import sms.entities.User;

/**
 *
 * @author USER
 */
public class UserDao implements IDao<User> {
     private final String SQL_SELECT_LOGIN_PASSWORD="SELECT * FROM `user` where login like ? and password like ?";
    private DaoMysql sgbd=new DaoMysql();
    
    @Override
    public int insert(User obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(User obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   public User findLoginAndPassword(String login,String password) {
      User data=null;
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_LOGIN_PASSWORD);
         try {
             sgbd.getPs().setString(1, login);
             sgbd.getPs().setString(2, password);
              ResultSet rs=sgbd.executeSelect();
       
            if(rs.next()){
                data =new User(
                       rs.getInt("id_user"),  
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password")
                );
            
            }
        }catch (SQLException ex) {
             Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
         } 
     
       sgbd.closeConnexion();
       return data;
    }
    
}
