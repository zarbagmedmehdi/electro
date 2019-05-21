package Helper;

import bean.Commande;
import bean.CommandeItem;
import bean.Electromenager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import service.CommandeItemService;
import service.CommandeService;
import service.ElectromenagerService;
import service.ReductionService;

import java.sql.SQLException;
import java.time.LocalDate;

public class CommandeItemHelper {

    //  return new Electromenager(rset.getString("REFERENCE"),rset.getLong("id"),rset.getString("LIBELLE"),
    //                    rset.getDouble("PRIX_VENTE"));
    // return new CommandeItem(rset.getInt("quantite"),rset.getLong("ELECTROMENAGER_ID"),
               // rset.getLong("COMMANDE_ID"));

    private ElectromenagerService  electromenagerService=new ElectromenagerService();
    private final SimpleStringProperty referenceProduit;
    private final SimpleStringProperty libelleProduit;
    private final SimpleStringProperty quantite;
    private final SimpleStringProperty prixVente;
    private final SimpleStringProperty prixCommandeItem;

    public CommandeItemHelper(CommandeItem commandeItem) throws SQLException {
        Electromenager e=electromenagerService.getElectromenager(commandeItem.getElectromenagerID());
        quantite=new SimpleStringProperty(String.valueOf(commandeItem.getQuantite()));
        referenceProduit=new SimpleStringProperty(e.getReference());
        libelleProduit=new SimpleStringProperty(e.getLibelle());
        prixVente=new SimpleStringProperty(String.valueOf(e.getPrixVente()));
        prixCommandeItem=new SimpleStringProperty(String.valueOf(e.getPrixVente()*commandeItem.getQuantite()));

    }

    public String getReferenceProduit() {
        return referenceProduit.get();
    }

    public SimpleStringProperty referenceProduitProperty() {
        return referenceProduit;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit.set(referenceProduit);
    }

    public String getLibelleProduit() {
        return libelleProduit.get();
    }

    public SimpleStringProperty libelleProduitProperty() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit.set(libelleProduit);
    }

    public String getQuantite() {
        return quantite.get();
    }

    public SimpleStringProperty quantiteProperty() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite.set(quantite);
    }

    public String getPrixVente() {
        return prixVente.get();
    }

    public SimpleStringProperty prixVenteProperty() {
        return prixVente;
    }

    public void setPrixVente(String prixVente) {
        this.prixVente.set(prixVente);
    }

    public String getPrixCommandeItem() {
        return prixCommandeItem.get();
    }

    public SimpleStringProperty prixCommandeItemProperty() {
        return prixCommandeItem;
    }

    public void setPrixCommandeItem(String prixCommandeItem) {
        this.prixCommandeItem.set(prixCommandeItem);
    }
}
