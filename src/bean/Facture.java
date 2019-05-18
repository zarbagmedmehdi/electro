/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author simob
 */
public class Facture {

    private Long id;
    private Date dateFacture;
    private String modePaiement;
    private Long commandeId;
    private Long utilisateurId;
    private Long reductionID;

    public Facture() {
    }

    public Facture(Long id, Date dateFacture, String modePaiement, Long commandeId, Long utilisateurId, Long reductionID) {
        this.id = id;
        this.dateFacture = dateFacture;
        this.modePaiement = modePaiement;
        this.commandeId = commandeId;
        this.utilisateurId = utilisateurId;
        this.reductionID = reductionID;
    }

    public Facture(Date dateFacture, String modePaiement, Long commandeId, Long utilisateurId, Long reductionID) {
        this.dateFacture = dateFacture;
        this.modePaiement = modePaiement;
        this.commandeId = commandeId;
        this.utilisateurId = utilisateurId;
        this.reductionID = reductionID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Long getReductionID() {
        return reductionID;
    }

    public void setReductionID(Long reductionID) {
        this.reductionID = reductionID;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", dateFacture=" + dateFacture +
                ", modePaiement='" + modePaiement + '\'' +
                ", commandeId=" + commandeId +
                ", utilisateurId=" + utilisateurId +
                ", reductionID=" + reductionID +
                '}';
    }
}
