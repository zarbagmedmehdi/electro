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
public class Commande {

    private Long id;
    private Date dateCommande;
    private int etat;
    private Long clientId;

    public Commande() {
    }

    public Commande(Long id, Date dateCommande, int etat, Long clientId) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.etat = etat;
        this.clientId = clientId;
    }

    public Commande(Date dateCommande, int etat, Long clientId) {
        this.dateCommande = dateCommande;
        this.etat = etat;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", dateCommande=" + dateCommande + ", etat=" + etat + ", clientId=" + clientId + '}';
    }

}
