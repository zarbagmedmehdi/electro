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
public class CommandeItem {

    private Long id;
    private int quantite;
    private Long electromenagerID;
    private Long commandeId;

    public CommandeItem() {
    }

    public CommandeItem(int quantite, Long electromenagerID, Long commandeId) {
        this.quantite = quantite;
        this.electromenagerID = electromenagerID;
        this.commandeId = commandeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Long getElectromenagerID() {
        return electromenagerID;
    }

    public void setElectromenagerID(Long electromenagerID) {
        this.electromenagerID = electromenagerID;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    @Override
    public String toString() {
        return "CommandeItem{" + "id=" + id + ", quantite=" + quantite + ", electromenagerID=" + electromenagerID + ", commandeId=" + commandeId + '}';
    }

}
