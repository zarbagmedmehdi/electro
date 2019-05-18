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
public class Electromenager {

    private Long id;
    private String reference;
    private String libelle;
    private String description;
    private Double prixVente;
    private Double prixAchat;
    private int existe;
    private String image;
    private Long sousCategorieId;
    private Long fournisseurID;

    public Electromenager() {
    }

    public Electromenager(String reference, String libelle, String description, Double prixVente, Double prixAchat, int existe, String image, Long sousCategorieId, Long fournisseurID) {
        this.reference = reference;
        this.libelle = libelle;
        this.description = description;
        this.prixVente = prixVente;
        this.prixAchat = prixAchat;
        this.existe = existe;
        this.image = image;
        this.sousCategorieId = sousCategorieId;
        this.fournisseurID = fournisseurID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }

    public Double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getExiste() {
        return existe;
    }

    public void setExiste(int existe) {
        this.existe = existe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getSousCategorieId() {
        return sousCategorieId;
    }

    public void setSousCategorieId(Long sousCategorieId) {
        this.sousCategorieId = sousCategorieId;
    }

    public Long getFournisseurID() {
        return fournisseurID;
    }

    public void setFournisseurID(Long fournisseurID) {
        this.fournisseurID = fournisseurID;
    }

    @Override
    public String toString() {
        return "Electromenager{" + "id=" + id + ", reference=" + reference + ", libelle=" + libelle + ", description=" + description + ", prixVente=" + prixVente + ", prixAchat=" + prixAchat + ", existe=" + existe + ", image=" + image + ", sousCategorieId=" + sousCategorieId + ", fournisseurID=" + fournisseurID + '}';
    }

}
