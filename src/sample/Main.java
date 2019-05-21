package sample;

import Helper.FactureHelper;
import bean.Electromenager;
import bean.Facture;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.*;
import util.Connexion;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
// FactureService factureService=new FactureService();
//        CommandeService commandeService=new CommandeService();
//        ReductionService rs=new ReductionService();
//        ElectromenagerService es=new ElectromenagerService();
//
//        ClientService c =new ClientService();
//        try {
//            System.out.println("cc");
//            Electromenager electromenager=es.getElectromenager(1);
//                       commandeService.getMontantTotal(1);
//          //  System.out.println(factureService.getFactureByCmdId(1).toString());
//            //c.getClientName(1);
//            System.out.println("cc");
// // rs.getReductionAdequat(0,1.0F);
//       //     List<Facture> list= factureService. findByCriteria(null, null,0L,"cash" , null);
//       //     System.out.println(list);
//    //   factureService.findByCriteria2(list,14000);
//            System.out.println("cc");
//
//     } catch (SQLException e) {
//       e.printStackTrace();
//      }


        launch(args);

    }
}
