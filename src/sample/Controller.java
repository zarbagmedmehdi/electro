package sample;

import Helper.CommandeHelper;
import bean.Commande;
import bean.Facture;
import bean.Reduction;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import service.CommandeService;
import service.FactureService;
import service.ReductionService;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class Controller {
    FactureService factureService=new FactureService();
    @FXML
    TableView<CommandeHelper> cmdTableView;
    @FXML
    ToggleGroup toggleGroup=new ToggleGroup();
    @FXML
    RadioButton facture;
    @FXML
    RadioButton nonfacture;
    @FXML
    Button createBtn;
    @FXML
    Button voirBtn;
    @FXML
     TextField dateFacturation;
    @FXML
    TextField modePaiement;
    @FXML
    TextField  idUser;
    @FXML
    TextField pourcentageReduction;
    @FXML
    TextField montantTotal;
    @FXML
    TextField  total;
    CommandeHelper selectedItem=null;
    @FXML
    Pane paneFacture;
    @FXML
    Spinner mode;





    private  ObservableList<CommandeHelper> data =new ObservableListBase<CommandeHelper>() {
        @Override
        public CommandeHelper get(int index) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    };


    @FXML private TableColumn<CommandeHelper, Long> id=new TableColumn<>("id");
    @FXML private TableColumn<CommandeHelper, LocalDate> dateCommande=new TableColumn<>("date");
    @FXML private TableColumn<CommandeHelper, Long> etat=new TableColumn<>("etat");
    @FXML private TableColumn<CommandeHelper,String > client=new TableColumn<>("client");


@FXML
    public void initialize() throws SQLException {
    mode.setDisable(false);

    facture.setToggleGroup(toggleGroup);
    nonfacture.setToggleGroup(toggleGroup);
    nonfacture.setSelected(true);
    populateCommandeTable(0);
    paneFacture.setVisible(false);
    createBtn.setDisable(false);
    voirBtn.setDisable(true);
    ObservableList<String> modes = FXCollections.observableArrayList("cash", "par cheque ","par carte bancaire");
    SpinnerValueFactory<String> valueFactory = //
            new SpinnerValueFactory.ListSpinnerValueFactory<String>(modes);

    mode.setValueFactory(valueFactory);
}

    @FXML
    public void infoBtnClick(){

    }
    @FXML
    public void creatBtnClick() throws SQLException {
        System.out.println("creatbtnz");
        selectedItem = cmdTableView.getSelectionModel().getSelectedItem();
        if (selectedItem!=null && cmdTableView.getSelectionModel().getSelectedItem().getEtat().equals("0" )) {
        int valeur=factureService.createFacture( mode.getValue().toString() ,Integer.parseInt(selectedItem.getId()) ,1 );
            System.out.println("creatbtnz"+valeur);
          if(valeur==1){
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
              alert.setTitle("creation");
              alert.setHeaderText("creation:");
              alert.setContentText("avec succes !");
              alert.showAndWait();
          }
          populateCommandeTable(0);

        }

        }
    @FXML
    public void facturer() throws SQLException {
    populateCommandeTable(1);
        voirBtn.setDisable(false);
        createBtn.setDisable(true);
        paneFacture.setVisible(true);
        mode.setDisable(true);

    }
    @FXML
    public void nonfacture() throws SQLException {
        populateCommandeTable(0);
        voirBtn.setDisable(true);
        createBtn.setDisable(false);
        paneFacture.setVisible(false);
        mode.setDisable(false);


    }
    @FXML
    public void CommandesChanged() throws SQLException {
    Facture selectedFacture=new Facture();
        ReductionService reductionService=new ReductionService();
    CommandeService commandeService=new CommandeService();
    FactureService factureService=new FactureService();
    if (cmdTableView.getSelectionModel().getSelectedItem()!=null && !cmdTableView.getSelectionModel().getSelectedItem().getEtat().equals("0" )) {
        selectedItem = cmdTableView.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem.getClient());
         selectedFacture=factureService.getFactureByCmdId(Integer.parseInt(selectedItem.getId()));
        dateFacturation.setText(selectedFacture.getDateFacture().toString());
        modePaiement.setText(selectedFacture.getModePaiement());
        idUser.setText(selectedFacture.getUtilisateurId().toString());
        Reduction reduction =reductionService.getReductionAdequat(selectedFacture.getReductionID());
        pourcentageReduction.setText(reduction.getPourcentage().toString()+" %");
        Float montant=commandeService.getMontantTotal(Integer.parseInt(selectedItem.getId()));
        montantTotal.setText(montant.toString());
        Float totatprix=montant*(100-reduction.getPourcentage())/100;
        total.setText(totatprix.toString());

    }
    }



    private void populateCommandeTable(int et) throws SQLException {
        data =
                FXCollections.observableArrayList(
                        parseList(et)
                );
        id.setCellValueFactory(new PropertyValueFactory<CommandeHelper, Long>("id"));
        etat.setCellValueFactory(new PropertyValueFactory<CommandeHelper, Long>("etat"));
        client.setCellValueFactory(new PropertyValueFactory<CommandeHelper, String>("client"));
        dateCommande.setCellValueFactory(cellData -> cellData.getValue().dateCommandeProperty());

        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

// Custom rendering of the table cell.
        dateCommande.setCellFactory(column -> {
            return new TableCell<CommandeHelper, LocalDate>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {

                        setText(myDateFormatter.format(item));

                    }
                }
            };
        });

        cmdTableView.getColumns().clear();
        cmdTableView.getColumns().addAll(id, dateCommande, etat, client);
        System.out.println("i got in it ;");
        cmdTableView.getItems().setAll(parseList(et));
    }
    private List<CommandeHelper> parseList(int etat) throws SQLException {
        CommandeService cmdService=new CommandeService();
        List<CommandeHelper> cs=new ArrayList<>();
        List<Commande> cmds=cmdService.getCommandes(etat);
        for (int i=0; i<cmds.size();i++)
        {
            cs.add(new CommandeHelper(cmds.get(i)));
        }

        return cs;
    }
}
