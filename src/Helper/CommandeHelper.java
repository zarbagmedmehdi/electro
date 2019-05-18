package Helper;

import bean.Commande;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import service.ClientService;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class CommandeHelper {
    private final SimpleStringProperty id;
    private final SimpleStringProperty etat;
    private final SimpleStringProperty client;
    private ObjectProperty<LocalDate>dateCommande;

    public CommandeHelper(Commande c) throws SQLException {
        ClientService clientService=new ClientService();
        id = new SimpleStringProperty(c.getId().toString());
        client = new SimpleStringProperty(clientService.getClientName(c.getClientId()));
        this.dateCommande = new SimpleObjectProperty( LocalDate.parse(c.getDateCommande().toString()));
        etat = new SimpleStringProperty(String.valueOf(c.getEtat()));

    }
    public String getId() {
        return id.get();
    }


    public void setId(String id) {
        this.id.set(id);
    }

    public LocalDate getDateCommande() {
        return dateCommande.get();
    }



    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande.set(dateCommande);
    }

    public String getEtat() {
        return etat.get();
    }



    public void setEtat(String etat) {
        this.etat.set(etat);
    }

    public String getClient() {
        return client.get();
    }



    public void setClient(String client) {
        this.client.set(client);
    }

    public ObjectProperty<LocalDate> dateCommandeProperty() {
        return dateCommande;
    }
}
