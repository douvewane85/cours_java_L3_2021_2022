/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sms.entities.Etudiant;
import sms.entities.Professeur;

/**
 *
 * @author USER
 */
public class EtudiantDao implements IDao<Etudiant>{
    private final String SQL_INSERT="INSERT INTO `user` (`nom_complet`, `login`, `password`, `role`,`matricule`, `tuteur`) VALUES (?,?,?,?,?, ?)";
    private final String SQL_SELECT_ALL="select * from user where role like 'ROLE_ETUDIANT' ";
    private final String SQL_SELECT_INSCRITS_ANNEE="select * from user u,inscription i,classe c where "
                                                     + "  role like 'ROLE_ETUDIANT' and  "
                                                     + "  u.id_user=i.etu_id and i.classe_id=c.id_classe"
                                                     + "  and annee_scolaire like ?";
    private final String SQL_SELECT_INSCRITS_ANNEE_CLASSE="select * from user u,inscription i,classe c where "
                                                     + "  role like 'ROLE_ETUDIANT' and  "
                                                     + "  u.id_user=i.etu_id and i.classe_id=c.id_classe"
                                                     + "  and annee_scolaire like ?"
                                                     + "  and c.id_classe=?";
    private final String SQL_SELECT_BY_ID="select * from user where role like 'ROLE_ETUDIANT' and id_user=? ";
    private final String SQL_SELECT_BY_MATRICULE="select * from user where role like 'ROLE_ETUDIANT' and matricule like ? ";
    private DaoMysql sgbd=new DaoMysql();
    private ClasseDao classeDao=new ClasseDao();
    
    
    @Override
    public int insert(Etudiant etu) {
         int result=0;
      sgbd.ouvrirConnexionBD();
    
      sgbd.preparerRequete(SQL_INSERT);
        try {
            sgbd.getPs().setString(1,etu.getNom_complet());
            sgbd.getPs().setString(2,etu.getLogin());
            sgbd.getPs().setString(3,etu.getPassword());
            sgbd.getPs().setString(4,etu.getRole());  
            sgbd.getPs().setString(5,etu.getMatricule());
            sgbd.getPs().setString(6,etu.getTuteur());
            result=  sgbd.executeMisAJour();
            ResultSet rs=sgbd.getPs().getGeneratedKeys();
            if(rs.next()){
               result=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      sgbd.closeConnexion();
      return result;
    }

    @Override
    public int update(Etudiant obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Etudiant findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Etudiant> findAll() {
       List<Etudiant> etus=new ArrayList<>();
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_ALL);
        
        ResultSet rs=sgbd.executeSelect();
        try {
            while(rs.next()){
                //String nci, String grade, int id, String nom_complet, String login, String password
                Etudiant etu =new Etudiant(rs.getString("matricule"), 
                                              rs.getString("tuteur"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
                etu.setClasse(classeDao.findById(rs.getInt("id_classe_encours")));
              etus.add(etu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       sgbd.closeConnexion();
       return etus;
    }
    
     public List<Etudiant> findAll(String annee) {
       List<Etudiant> etus=new ArrayList<>();
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_INSCRITS_ANNEE);
      
        try {
              sgbd.getPs().setString(1, annee);
              ResultSet rs=sgbd.executeSelect();
            while(rs.next()){
                //String nci, String grade, int id, String nom_complet, String login, String password
                Etudiant etu =new Etudiant(rs.getString("matricule"), 
                                              rs.getString("tuteur"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
                etu.setClasse(classeDao.findById(rs.getInt("classe_id")));
              etus.add(etu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       sgbd.closeConnexion();
       return etus;
    }
     public List<Etudiant> findAll(String annee,int idclasse) {
       List<Etudiant> etus=new ArrayList<>();
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_INSCRITS_ANNEE_CLASSE);
      
        try {
              sgbd.getPs().setString(1, annee);
              sgbd.getPs().setInt(2, idclasse);
              ResultSet rs=sgbd.executeSelect();
            while(rs.next()){
                //String nci, String grade, int id, String nom_complet, String login, String password
                Etudiant etu =new Etudiant(rs.getString("matricule"), 
                                              rs.getString("tuteur"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
                etu.setClasse(classeDao.findById(rs.getInt("classe_id")));
              etus.add(etu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       sgbd.closeConnexion();
       return etus;
    }
  public Etudiant findByMatricule(String matricule) {
       Etudiant etu=null;
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_BY_MATRICULE);
        try {
              sgbd.getPs().setString(1, matricule);
    
              ResultSet rs=sgbd.executeSelect();
              
            while(rs.next()){
                //String nci, String grade, int id, String nom_complet, String login, String password
               etu =new Etudiant(rs.getString("matricule"), 
                                              rs.getString("tuteur"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
                etu.setClasse(classeDao.findById(rs.getInt("classe_id")));
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       sgbd.closeConnexion();
       return etu;
    }
    
}
