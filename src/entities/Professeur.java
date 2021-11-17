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
public class Professeur extends User {
    private String grade;
    private String nci;
     private final String ROLE="ROLE_PROFESSEUR";

    public Professeur() {
    }

    public Professeur(String grade, String nci, String nomComplet) {
        super(nomComplet);
        this.grade = grade;
        this.nci = nci;
    }

    public Professeur(String grade, String nci, int id, String nomComplet) {
        super(id, nomComplet);
        this.grade = grade;
        this.nci = nci;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }
     
     
}

