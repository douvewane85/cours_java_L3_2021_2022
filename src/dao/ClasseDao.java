/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import entities.Classe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
public class ClasseDao implements IDao<Classe>{

    DataBase database =new DataBase();
    /*Requete SQL*/
    private final String SQL_INSERT="INSERT INTO `classe` (`libelle`) VALUES (?)";
    private final String SQL_UPDATE="UPDATE `classe` SET `libelle` = ? WHERE `classe`.`id_classe` = ?";
    private final String SQL_DELETE="Delete from classe where id_classe=?";
    private final String SQL_ALL="select * from classe";
    private final String SQL_BY_ID="select * from classe where id_classe=?";
    private final String SQL_BY_PROF_ANNEE="select * from classe c, classe_prof a,user p"
                                          + "  where c.id_classe=a.classe_id "
                                          + "  and a.prof_id=p.id_user"
                                          + "  and a.anne_scolaire like ?"
                                          + "  and p.nci like ?";
    @Override
    public int insert(Classe classe) {
        int lastInsertId=0;
        database.openConnexion();
            database.initPreparedStament(SQL_INSERT);
        try {
            //ORM Objet vers Relation
            database.getPs().setString(1, classe.getLibelle());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return lastInsertId;
    }

    @Override
    public int update(Classe classe) {
       int nbreLigne=0;
        database.openConnexion();
            database.initPreparedStament(SQL_UPDATE);
        try {
            //ORM Objet vers Relation
            database.getPs().setString(1, classe.getLibelle());
            database.getPs().setInt(2, classe.getId());
            nbreLigne=database.executeUpdate(SQL_UPDATE);
           
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return nbreLigne;
    }

    @Override
    public int delete(int id) {
       int nbreLigne=0;
        database.openConnexion();
            database.initPreparedStament(SQL_DELETE);
        try {
            //ORM Objet vers Relation
            database.getPs().setInt(1, id);
            nbreLigne=database.executeUpdate(SQL_DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return nbreLigne;
    }

    @Override
    public List<Classe> findAll() {
         List<Classe> classes=new ArrayList<>();
        database.openConnexion();
            database.initPreparedStament(SQL_ALL);
            ResultSet rs=database.executeSelect(SQL_ALL);
        try {
            while(rs.next()){
                //Mapping Relation vers Objet
                Classe cl=new Classe(
                        rs.getInt("id_classe"),
                        rs.getString("libelle")
                );
                classes.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return classes;
    }

    @Override
    public Classe findById(int id) {
         Classe classe=null;
           database.openConnexion();
            database.initPreparedStament(SQL_BY_ID);
            ResultSet rs=database.executeSelect(SQL_BY_ID);
        try {
            if(rs.next()){
                //Mapping Relation vers Objet
                classe=new Classe(
                        rs.getInt("id_classe"),
                        rs.getString("libelle")
                );
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return classe;
    }
    public List<Classe> findClasseByProfesseur(String nci, String anne)  {
         List<Classe> classes=new ArrayList<>();
        database.openConnexion();
            database.initPreparedStament(SQL_BY_PROF_ANNEE);
        try {
              database.getPs().setString(1, anne);
              database.getPs().setString(2, nci);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
          
            ResultSet rs=database.executeSelect(SQL_BY_PROF_ANNEE);
        try {
            while(rs.next()){
                //Mapping Relation vers Objet
                Classe cl=new Classe(
                        rs.getInt("id_classe"),
                        rs.getString("libelle")
                );
                classes.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return classes;
    }
}
