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
public class Utilisateur {

    private Long id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String statut;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String login, String password, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.statut = statut;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password=" + password + ", statut=" + statut + '}';
    }

}
