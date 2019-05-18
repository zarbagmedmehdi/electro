/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author simob
 */
public class Fournisseur {

    private Long id;
    private String nom;
    private String numTel;
    private String adresse;

    public Fournisseur() {
    }

    public Fournisseur(String nom, String numTel, String adresse) {
        this.nom = nom;
        this.numTel = numTel;
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "id=" + id + ", nom=" + nom + ", numTel=" + numTel + ", adresse=" + adresse + '}';
    }

}
