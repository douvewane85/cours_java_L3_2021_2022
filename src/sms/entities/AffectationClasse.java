/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.entities;

/**
 *
 * @author USER
 */
public class AffectationClasse {
    private int id;
    private Classe classe;
    private Professeur prof;
    private String annee;
   
    
    

    public AffectationClasse() {
    }

    public AffectationClasse(int id, Classe classe, Professeur prof, String annee) {
        this.id = id;
        this.classe = classe;
        this.prof = prof;
        this.annee = annee;
    }

    public AffectationClasse(Classe classe, Professeur prof, String annee) {
        this.classe = classe;
        this.prof = prof;
        this.annee = annee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Professeur getProf() {
        return prof;
    }

    public void setProf(Professeur prof) {
        this.prof = prof;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
    
    
    
}
