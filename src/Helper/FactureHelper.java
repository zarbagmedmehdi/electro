package Helper;
 import bean.Commande;
 import bean.Reduction;
 import javafx.beans.property.ObjectProperty;
 import javafx.beans.property.SimpleObjectProperty;
 import javafx.beans.property.SimpleStringProperty;

 import java.sql.SQLException;
 import java.time.LocalDate;
 import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import bean.Facture;
 import service.CommandeService;
 import service.ReductionService;

public class FactureHelper {


//        private Long id;
//        private Date dateFacture;
//        private String modePaiement;
//        private Long commandeId;
//        private Long utilisateurId;
//        private Long reductionID;

//private final SimpleStringProperty id;
//        private final SimpleStringProperty etat;
//        private final SimpleStringProperty client;
//        private ObjectProperty<LocalDate>dateCommande;

     private ReductionService reductionService= new ReductionService();
   private   CommandeService commandeService=new CommandeService();
        private final SimpleStringProperty id;
        private final SimpleStringProperty montant;
        private final SimpleStringProperty prixTotal;

        private ObjectProperty<LocalDate> dateFacturation ;

        public FactureHelper(Facture f) throws SQLException {


            Commande   c=commandeService.getCommandeByFacture(f);
            Reduction r=reductionService.getReductionAdequat(f.getReductionID(),0F);
            id = new SimpleStringProperty(c.getId().toString());
            this.dateFacturation = new SimpleObjectProperty( LocalDate.parse(c.getDateCommande().toString()));

            montant=new SimpleStringProperty( String.valueOf(commandeService.getMontantTotal(c.getId())));
            prixTotal=new SimpleStringProperty(String.valueOf((commandeService.getMontantTotal(c.getId())*(100-r.getPourcentage())/100)));

        }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getMontant() {
        return montant.get();
    }

    public SimpleStringProperty montantProperty() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant.set(montant);
    }

    public String getPrixTotal() {
        return prixTotal.get();
    }

    public SimpleStringProperty prixTotalProperty() {
        return prixTotal;
    }

    public void setPrixTotal(String prixTotal) {
        this.prixTotal.set(prixTotal);
    }

    public LocalDate getDateFacturation() {
        return dateFacturation.get();
    }

    public ObjectProperty<LocalDate> dateFacturationProperty() {
        return dateFacturation;
    }

    public void setDateFacturation(LocalDate dateFacturation) {
        this.dateFacturation.set(dateFacturation);
    }

    @Override
    public String toString() {
        return "FactureHelper{" +

                ", id=" + id +
                ", montant=" + montant +
                ", prixTotal=" + prixTotal +
                ", dateFacturation=" + dateFacturation +
                '}';
    }
}
