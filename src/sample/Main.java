package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ClientService;
import service.CommandeService;
import service.FactureService;
import util.Connexion;

import java.sql.Date;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ChercherFacture.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
 FactureService factureService=new FactureService();
        CommandeService commandeService=new CommandeService();
        ClientService c =new ClientService();
        try {
              //commandeService.getMontantTotal(1);
          //  System.out.println(factureService.getFactureByCmdId(1).toString());
            //c.getClientName(1);
            factureService. findByCriteria("zarbag.mehdi@gmail.com", "zarbag",1,"cash" , null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //launch(args);
        try {
            //('il existe deja une facture pour cette commande' );
            //factureService. createFacture ("cash" ,1 ,1 );
            commandeService.getCommandes(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        launch(args);

    }
}
