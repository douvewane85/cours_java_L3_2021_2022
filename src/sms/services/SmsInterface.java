/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.services;

import java.util.List;
import sms.entities.Classe;
import sms.entities.Etudiant;
import sms.entities.Inscription;
import sms.entities.Professeur;
import sms.entities.User;

/**
 * @author USER
 */
public interface SmsInterface {
   public boolean inscription(Etudiant etu,Classe classe,String annee);
   public boolean ajouterClasse(Classe cl);
   public List<Classe> listerClasse();
   public boolean modifierClasse(Classe cl);
   public List<Etudiant>  ListerInscristUneClasse(int idClasse,String annee);
   public boolean ajouterProfesseur(Professeur prof);
   public List<Professeur> listerProfesseur();
   public List<Etudiant> listerEtudiant();
   public void affecter(Professeur prof,List<Classe> classes,String annee);
   public List<Classe> listerClasseUnProfesseur(int idProf,String anne);
   public User connexion(String login,String password);
   public List<String> listerAnneeScolaire();
   public Classe rechercherClasseParLibelle(String libelle);
   public Professeur rechercherProfesseurParNci(String nci);
   public Etudiant rechercherEtudiantParMatricule(String matricule);
   public List<Etudiant>  ListerInscristAnne(String annee);
   
}
