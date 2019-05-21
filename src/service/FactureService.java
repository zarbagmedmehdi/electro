/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.Facture;
import bean.Reduction;
import util.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.toIntExact;

/**
 *
 * @author simob
 */
public class FactureService {
    Connexion cnx=new Connexion();

    public int createFacture (String mode ,int id ,int util_id ) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
        try {
         connection=cnx.getConnectionStatement();
      //   Statement st=connection.createStatement();
        // insertFacture(var_mode VARCHAR2,var_id number ,var_util number ,var_reduc_id number)
         cs = connection.prepareCall("{ ?=call insertFacture(?,?,?)}");
cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, mode);
        cs.setLong(3, id);
        cs.setLong(4, util_id);


        cs.execute();

    // ResultSet result=cs.getResultSet();
            System.out.println( "inside"+ cs.getInt(1));

    } catch (Exception e) {
        System.err.println("Got an exception! create Facture ");
        System.err.println ( "create Facture" +e.getMessage());
        return -1;
    } finally {
        if (cs != null) cs.close();
        if (connection != null){ connection.close();
            System.out.println("connection closed  create Facture");
            return 1; }
    }
        return 1;
    }
    public List<Facture> createListFacture(ResultSet rset) throws SQLException {
        List<Facture> list =new ArrayList<>();
        while (rset.next ()){
            Facture f=instantiateFacture(rset);
            list.add(f);
        }
        return list;

    }
    public Facture getFactureByCmdId (long idCommande ) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
       Facture facture=new Facture();
        try {
            connection=cnx.getConnectionStatement();

            CallableStatement call =
                    connection. prepareCall("select * from facture where COMMANDE_ID=?");
            call.setLong(1,idCommande);
            call.execute ();

            ResultSet rset=call.getResultSet();
            rset.next();
 facture=instantiateFacture(rset);

        } catch (Exception e) {
            System.err.println("Got an exception!  ");
            System.err.println ( " e" +e.getMessage());
        } finally {
            if (cs != null) cs.close();
            if (connection != null){ connection.close();
                System.out.println("connection closed   ");}
        }
        System.out.println(facture.toString());
        return facture;

    }
    public Facture instantiateFacture(ResultSet r) throws SQLException {
        //(Long id, Date dateFacture, String modePaiement, Long commandeId, Long utilisateurId, Long reductionID)

        return new Facture(r.getLong("id"),r.getDate("DATEFACTURATION"),
        r.getString("MODEPAIEMENT"),r.getLong("COMMANDE_ID"),r.getLong("UTILISATEUR_ID"),
                r.getLong("REDUCTION_ID"));
    }



    public List<Facture> findByCriteria(String email, String nom, long idCaissier,String modePaiement ,Date date) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
List<Facture> list=new ArrayList<>();
        String query="select * from facture f , reduction r , commande c ,utilisateur u, client cl  where f.commande_id=c.id "+
                " and f.utilisateur_id=u.id and f.reduction_id=r.id and c.client_id=cl.id ";
        if (email!=null){ query+=" and cl.email=?";}
        if (nom!=null) { query+=" and cl.nom=?"; }
        if (idCaissier!=0){ query+=" and f.utilisateur_id=?"; }
        if (modePaiement!=null){ query+=" and MODEPAIEMENT=?"; }
        if (date!=null) { query+=" and DATEFACTURATION=?"; }
     //   query+=";";
        try {

        int i=1;
            connection=cnx.getConnectionStatement();
            PreparedStatement call = connection.prepareStatement (query);
            if (email!=null){ call.setString(i,email );i++; }
                if (nom!=null) { call.setString(i,nom);i++; }
                if (idCaissier!=0){ call.setLong(i,idCaissier);i++; }
                if (modePaiement!=null){ call.setString(i,modePaiement);i++;}
                if (date!=null) { call.setDate(i,date);i++;}
            call.toString();

            call.execute ();
            ResultSet rset=call.getResultSet();
            list=createListFacture(rset);

        } catch (Exception e) {
            System.err.println("Got an exception!  ");
            System.err.println ( " e" +e.getMessage());
        }
            if (connection != null){ connection.close();
                System.out.println("connection closed   ");}

        return list;
    }
    public List<Facture> findByCriteria2(List<Facture> factureList,Float montantMin) throws SQLException {
        CommandeService commandeService=new CommandeService();
        if (montantMin!=0F){
        for (int i=0;i<factureList.size();i++)
        {Facture f=factureList.get(i);
            float montant=commandeService.getMontantTotal( toIntExact(f.getCommandeId()));
            if (montant<montantMin){
                factureList.remove(i);
            }
        }}

        return factureList;
    }
    public  List<Facture> findByCriteria3(List<bean.Facture> factureList, Float pourcentage ) throws SQLException {
        ReductionService reductionService = new ReductionService();
        if (pourcentage!=0F) {
            Reduction r = reductionService.getReductionAdequat(0, pourcentage);
            System.out.println("hahya reduction f findby 3 "+r);

            for (int i = 0; i < factureList.size(); i++) {
                Facture f = factureList.get(i);

                if (f.getReductionID() != r.getId()) {
                    factureList.remove(i);
                }
            }
        }
        System.out.println("find by criteria 3 la liste des factures "+factureList);
        return factureList;
    }
}
