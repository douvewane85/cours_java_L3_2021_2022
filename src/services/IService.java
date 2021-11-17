/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Classe;
import entities.Etudiant;
import entities.Professeur;
import entities.User;
import java.util.List;

/**
 *
 * @author USER
 */
public interface IService {

    /*
          ajout,modification,suppression boolean 
            true ajout correct
            false Erreur
     */
    //RP
    /* Gerer Classe*/
    public int addClasse(Classe classe);

    public boolean updateClasse(Classe classe);

    public boolean deleteClasse(int id);

    public List<Classe> showAllClasse();

    public Classe searchClasseById(int id);

    /* Affecter  Professeur */
    public Professeur searchProfesseurByNci(String nci);

    public boolean addAffectation(Professeur prof, Classe classe, String annee);

    /*Lister Classe d'un Professeur*/
    public List<Classe> searchClasseByProfesseur(String nci, String anne);

    /*Lister Professeur*/
    public List<Professeur> showAllProfesseur();
    //AC
    //AC
    //Inscription

    public Etudiant searchEtudiantByMatricule(String matricule);

    public boolean addInscription(Etudiant etu, Classe classe, String annee);

    public Etudiant searchInscriptionByEtudiantAndYear(int id, String annee);
    //Lister inscrits

    public List<Etudiant> searchIncription(String annee);
    public List<Etudiant> searchIncription(String annee,Classe classe);

    /* Se Connecter */
    public User seConnecter(String login, String password);
}
