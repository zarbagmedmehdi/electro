package sample;

import Helper.CommandeHelper;
import Helper.CommandeItemHelper;
import Helper.FactureHelper;
import bean.Commande;
import bean.CommandeItem;
import bean.Facture;
import bean.Reduction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChercherFactureController {
    private FactureService factureService=new FactureService();
    @FXML
    private  RadioButton emailRdBtn;
    @FXML
    private  RadioButton  nomRdBtn;
    @FXML
    private RadioButton  idCaissierRdBtn;
    @FXML
    private RadioButton  tauxRdBtn;
    @FXML
    private RadioButton   modeRdBtn;
    @FXML
    private RadioButton  montantRdBtn;
    @FXML
    private RadioButton  dateRdBtn;
    @FXML
    private TextField emailField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField montantField;
    @FXML
    private Spinner idCaissierSpinner;
    @FXML
    private Spinner tauxSpinner;
    @FXML
    private Spinner modeSpinner;
    @FXML
    private Button chercherBtn;
    @FXML
    private DatePicker dateFacturationPicker;
    @FXML
    private TableView factureTable;
    @FXML
    private TableView commandeTable;
    @FXML
    private TableView commandeItemTable;
 private String email=null;
    private String nom=null;
    private Long idCaissier=0L;
    private Float montant=0F;
    private Float taux=0F;
    private String mode=null;
    private java.sql.Date date=null;
    private List<Facture> factures=new ArrayList<>();
    private CommandeHelper selectedCommande=null;
    private FactureHelper selectedFacture=null;


    private  ObservableList<CommandeItemHelper> commandeItemdata =new ObservableListBase<CommandeItemHelper>() {
        @Override
        public CommandeItemHelper get(int index) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    };


    @FXML private TableColumn<CommandeItemHelper, String> referenceProduit=new TableColumn<>("referenceProduit");
    @FXML private TableColumn<CommandeItemHelper, String> libelleProduit=new TableColumn<>("libelleProduit");
    @FXML private TableColumn<CommandeItemHelper, Integer> quantite=new TableColumn<>("quantite");
    @FXML private TableColumn<CommandeItemHelper,Double > prixVente=new TableColumn<>("prixVente");
    @FXML private TableColumn<CommandeItemHelper,Double > prixCommandeItem=new TableColumn<>("prixCommandeItem");


    private  ObservableList<FactureHelper> factureData =new ObservableListBase<FactureHelper>() {
        @Override
        public FactureHelper get(int index) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    };



    @FXML private TableColumn<FactureHelper, Long> idF=new TableColumn<>("id");
    @FXML private TableColumn<FactureHelper, Float> montantF=new TableColumn<>("montant");
    @FXML private TableColumn<FactureHelper, Float> prixTotalF=new TableColumn<>("prixTotal");
    @FXML private TableColumn<FactureHelper,LocalDate > dateFacturationF=new TableColumn<>("dateFacturation");

    private  ObservableList<CommandeHelper> commandedata =new ObservableListBase<CommandeHelper>() {
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
         emailField.setDisable(true);
         nomField.setDisable(true);
         montantField.setDisable(true);
         idCaissierSpinner.setDisable(true);
         tauxSpinner.setDisable(true);
         modeSpinner.setDisable(true);
        dateFacturationPicker.setDisable(true);
        populateMode();
        populateCaissier();
        populateTaux();
    }


@FXML
        public void cherchetBtn() throws SQLException {
        FactureService factureService=new FactureService();
    email=fillStringAttrField(emailRdBtn,emailField);
   nom= fillStringAttrField(nomRdBtn,nomField);
   date = (java.sql.Date) fillDatePicker( dateRdBtn, dateFacturationPicker);
    taux= fillFloatAttrSpinner(tauxRdBtn,tauxSpinner);
    montant=fillFloatAttrField(montantRdBtn,montantField);
    idCaissier=fillLongAttrField(idCaissierRdBtn,idCaissierSpinner);
    mode=fillStringAttrSpinner(modeRdBtn, modeSpinner);
    factures=factureService.findByCriteria(email,nom,idCaissier,mode,date);
  factures=factureService.findByCriteria2(factures,montant);
  factures=factureService.findByCriteria3(factures,taux);
populateFactureTable(factures);
}

    @FXML
    public void populateCommandeTable() throws SQLException {

        selectedFacture= (FactureHelper) factureTable.getSelectionModel().getSelectedItem();
        if (selectedFacture!=null) {
            commandedata =
                    FXCollections.observableArrayList(
                            parseCommandeList(Long.parseLong(selectedFacture.getId()))
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

            commandeTable.getColumns().clear();
            commandeTable.getColumns().addAll(id, dateCommande, etat, client);
            System.out.println("i got in it ;");
            commandeTable.getItems().setAll(commandedata);
        }
    }
@FXML
public void populateCommandeIemTable() throws SQLException {

//
//    referenceProduit=new TableColumn<>("referenceProduit");
//    @FXML private TableColumn<CommandeHelper, String> libelleProduit=new TableColumn<>("libelleProduit");
//    @FXML private TableColumn<CommandeHelper, Integer> quantite=new TableColumn<>("quantite");
//    @FXML private TableColumn<CommandeHelper,Double > prixVente=new TableColumn<>("prixVente");
//    @FXML private TableColumn<CommandeHelper,Double > prixCommandeItem=new TableColumn<>("prixCommandeItem");

    selectedCommande= (CommandeHelper) commandeTable.getSelectionModel().getSelectedItem();
        if (selectedCommande!=null) {
            commandeItemdata =
                    FXCollections.observableArrayList(
                            parseCommandeItemList(Long.parseLong(selectedCommande.getId()))
                    );
            referenceProduit.setCellValueFactory(new PropertyValueFactory<CommandeItemHelper, String>("referenceProduit"));
            libelleProduit.setCellValueFactory(new PropertyValueFactory<CommandeItemHelper, String>("libelleProduit"));
            quantite.setCellValueFactory(new PropertyValueFactory<CommandeItemHelper, Integer>("quantite"));
            prixVente.setCellValueFactory(new PropertyValueFactory<CommandeItemHelper, Double>("prixVente"));
            prixCommandeItem.setCellValueFactory(new PropertyValueFactory<CommandeItemHelper, Double>("prixCommandeItem"));


            commandeItemTable.getColumns().clear();
            commandeItemTable.getColumns().addAll(referenceProduit, libelleProduit, quantite, prixVente,prixCommandeItem);
            System.out.println("i got in it ;");
            commandeItemTable.getItems().setAll(commandeItemdata);
        }
}


public void populateFactureTable (List<Facture> factureList) throws SQLException {
    factureData = FXCollections.observableArrayList(parseList(factureList));
    idF.setCellValueFactory(new PropertyValueFactory<FactureHelper, Long>("id"));
    montantF.setCellValueFactory(new PropertyValueFactory<FactureHelper, Float>("montant"));
    prixTotalF.setCellValueFactory(new PropertyValueFactory<FactureHelper, Float>("prixTotal"));
    dateFacturationF.setCellValueFactory(cellData -> cellData.getValue().dateFacturationProperty());

    DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

// Custom rendering of the table cell.
    dateFacturationF.setCellFactory(column -> {
        return new TableCell<FactureHelper, LocalDate>() {
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
    factureTable.getColumns().clear();
    factureTable.getColumns().addAll(idF, montantF, prixTotalF, dateFacturationF);
    System.out.println("i got in it ;");
    factureTable.getItems().setAll(factureData);
}
    @FXML
    public void disable1Rd(){
    rd(emailRdBtn,emailField);
     }
    @FXML
    public void disable2Rd(){
        rd(nomRdBtn,nomField);
    }
    @FXML
    public void disable3Rd(){
        rd(montantRdBtn,montantField);
    }
    @FXML
    public void disable1Sp(){
      rd(idCaissierRdBtn,idCaissierSpinner);
    }
    @FXML
    public void disable2Sp(){
        rd(tauxRdBtn,tauxSpinner);
    }
    @FXML
    public void disable3Sp(){
        rd(modeRdBtn,modeSpinner);
    }
    @FXML
    public void disableDatePicker(){
        rd(dateRdBtn,dateFacturationPicker);
    }

    public String fillStringAttrField(RadioButton rd, TextField control){
        String value=null;
        if (rd.isSelected()){
            value=control.getText();
            System.out.println(value);
        }
       return value;
    }
    public Float fillFloatAttrField(RadioButton rd, TextField control){
      Float  value=0F;
        if (rd.isSelected()){

            value=Float.parseFloat(control.getText());
            System.out.println(value);
        }
        return value ;
    }
    public Long fillLongAttrField(RadioButton rd, Spinner control){
        Long value=0L;
        if (rd.isSelected()){
            value=Long.parseLong(control.getValue().toString());
            System.out.println(value);
        }
        return value;
    }
    public String fillStringAttrSpinner(RadioButton rd, Spinner control){
        String value=null;
        if (rd.isSelected()){
            value=control.getValue().toString();
            System.out.println(value);
        }
        return value;
    }
    public Float fillFloatAttrSpinner(RadioButton rd, Spinner control){
        Float value=0F;
        if (rd.isSelected()){
            value=Float.parseFloat(control.getValue().toString());
            System.out.println(value);
        }
        return value;
    }
    public Date fillDatePicker(RadioButton rd, DatePicker control){
        Date value=null;
        if (rd.isSelected()){
            value=java.sql.Date.valueOf(control.getValue());
            System.out.println(value);
        }
        return value;
    }

    private List<FactureHelper> parseList( List<Facture> factures) throws SQLException {
        List<FactureHelper> fs=new ArrayList<>();

        for (int i=0; i<factures.size();i++)
        {
            fs.add(new FactureHelper(factures.get(i)));
        }
        System.out.println("ha list dyal factureHelper"+fs);
        return fs;

    }
    private List<CommandeHelper> parseCommandeList(Long id) throws SQLException {
        CommandeService cmdService=new CommandeService();
        List<CommandeHelper> cs=new ArrayList<>();
        Facture f=new Facture(id);
        Commande c=cmdService.getCommandeByFacture(f);

            cs.add(new CommandeHelper(c));


        return cs;
    }
    private List<CommandeItemHelper> parseCommandeItemList(Long id) throws SQLException {
        CommandeItemService commandeItemService=new CommandeItemService();
        List <CommandeItemHelper> ciss=new ArrayList<>();
        List<CommandeItem>  cis=commandeItemService.getCommandesItems(id);
        for (int i=0;i<cis.size();i++){
            ciss.add(new CommandeItemHelper(cis.get(i)));
        }

        return ciss;
    }
    public void populateMode(){
        ObservableList<String> modes = FXCollections.observableArrayList("cash", "par cheque ","par carte bancaire");
        SpinnerValueFactory<String> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(modes);

        modeSpinner.setValueFactory(valueFactory);
    }
    public void populateCaissier() throws SQLException {
        UtilisateurService us = new UtilisateurService();
        ObservableList<String> modes = FXCollections.observableArrayList(us.getCaissiers());
        SpinnerValueFactory<String> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(modes);

        idCaissierSpinner.setValueFactory(valueFactory);
    }
    public void populateTaux() throws SQLException {
        ReductionService rs=new ReductionService();
        ObservableList<String> modes = FXCollections.observableArrayList(rs.getListPourcentage());
        SpinnerValueFactory<String> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(modes);

        tauxSpinner.setValueFactory(valueFactory);
    }

    public void rd(RadioButton rd, Control control) {
        if (rd.isSelected()) {
            control.setDisable(false);
        } else {
            control.setDisable(true);
        }
    }
}
