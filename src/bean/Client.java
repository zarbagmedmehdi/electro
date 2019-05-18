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
public class Client {

    private Long id;
    private String nom;
    private String prenom;
    private String numTel;
    private String adresse;
    private String email;
    private int firstYear;

    public Client() {
    }

    public Client(String nom, String prenom, String numTel, String adresse, String email, int firstYear) {
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.adresse = adresse;
        this.email = email;
        this.firstYear = firstYear;
    }

    public Client(String nom, String prenom, String numTel, String adresse, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.adresse = adresse;
        this.email = email;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numTel=" + numTel + ", adresse=" + adresse + ", email=" + email + ", firstYear=" + firstYear + '}';
    }

}
