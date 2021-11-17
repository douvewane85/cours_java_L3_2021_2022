/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author USER
 */
public interface IDao<T> {
    /*
        Fonction MAJ
        return int 
          insert => dernier id inserer
          update,delete  => nbre ligne modifier
    */
    
      public int insert(T obj );
      public int update(T obj );
      public int delete(int id );
       
    /*
      Fonction Interrogation
         return List findAll()
         return un Objet findByID()
    */
      
      public List<T> findAll();
      public T findById(int id);
}
