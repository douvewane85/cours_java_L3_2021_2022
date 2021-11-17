/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author USER
 */
public class Inscription {
    private int id;
    private String annee;
    //Attributs Navigationnel
    private Classe classe;
    private Etudiant etudiant;

    public Inscription() {
    }

    
    public Inscription(String annee, Classe classe, Etudiant etudiant) {
        this.annee = annee;
        this.classe = classe;
        this.etudiant = etudiant;
    }

    public Inscription(int id, String annee, Classe classe, Etudiant etudiant) {
        this.id = id;
        this.annee = annee;
        this.classe = classe;
        this.etudiant = etudiant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    
    
    
}
