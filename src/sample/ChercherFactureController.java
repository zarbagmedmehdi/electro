package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.FactureService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChercherFactureController {
    FactureService factureService=new FactureService();
    @FXML
    RadioButton emailRdBtn;
    @FXML
    RadioButton  nomRdBtn;
    @FXML
    RadioButton  idCaissierRdBtn;
    @FXML
    RadioButton  tauxRdBtn;
    @FXML
    RadioButton   modeRdBtn;
    @FXML
    RadioButton  montantRdBtn;
    @FXML
    RadioButton  dateRdBtn;
    @FXML
    TextField emailField;
    @FXML
    TextField nomField;
    @FXML
    TextField montantField;
    @FXML
    Spinner idCaissierSpinner;
    @FXML
    Spinner tauxSpinner;
    @FXML
    Spinner modeSpinner;
    @FXML
    Button chercherBtn;
    @FXML
    DatePicker dateFacturationPicker;
    @FXML
    TableView factureTable;
    @FXML
    TableView commandeTable;
    @FXML
    TableView commandeItemTable;

    ArrayList<TextField>  textFields=new ArrayList<TextField>();
    ArrayList<Spinner>  spinners=new ArrayList<Spinner>();
    @FXML
    public void initialize() {
         emailField.setDisable(true);
         nomField.setDisable(true);
         montantField.setDisable(true);
         idCaissierSpinner.setDisable(true);
         tauxSpinner.setDisable(true);
         modeSpinner.setDisable(true);
        dateFacturationPicker.setDisable(true);
    }
    public void rd(RadioButton rd, Control control) {
    if (rd.isSelected()) {
        control.setDisable(false);
    } else {
        control.setDisable(true);
    }
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


}
