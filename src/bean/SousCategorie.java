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
public class SousCategorie {

    private Long id;
    private String nom;
    private String specifite;
    private int seuilAlert;
    private Long categorieId;

    public SousCategorie() {
    }

    public SousCategorie(String nom, String specifite, int seuilAlert, Long categorieId) {
        this.nom = nom;
        this.specifite = specifite;
        this.seuilAlert = seuilAlert;
        this.categorieId = categorieId;
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

    public String getSpecifite() {
        return specifite;
    }

    public void setSpecifite(String specifite) {
        this.specifite = specifite;
    }

    public int getSeuilAlert() {
        return seuilAlert;
    }

    public void setSeuilAlert(int seuilAlert) {
        this.seuilAlert = seuilAlert;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    @Override
    public String toString() {
        return "SousCategorie{" + "id=" + id + ", nom=" + nom + ", specifite=" + specifite + ", seuilAlert=" + seuilAlert + ", categorieId=" + categorieId + '}';
    }

}
