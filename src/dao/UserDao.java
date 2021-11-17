/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class UserDao implements IDao<User> {

      private DataBase database=new DataBase();
      private final String SQL_LOGIN_PASSWORD="select * from user "
                                              + " where login like ? and password like ?";
    @Override
    public int insert(User obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(User obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public User findUserLoginAndPassword(String login,String password){
         User user=null;
           database.openConnexion();
           database.initPreparedStament(SQL_LOGIN_PASSWORD);
          try {
              database.getPs().setString(1, login);
              database.getPs().setString(2, password);
              ResultSet rs=database.executeSelect(SQL_LOGIN_PASSWORD);
              if(rs.next()){
                  //User(int id, String nomComplet, String login, String password, String role)
                  user=new User(rs.getInt("id_user"),rs.getString("nom_complet"),
                                          rs.getString("login"), 
                                          rs.getString("password"),
                                          rs.getString("role"));
              }
              
          } catch (SQLException ex) {
              Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
          }
           
           database.closeConnexion();
         
        return user;
    }
}
