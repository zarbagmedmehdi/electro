/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.Facture;
import oracle.jdbc.OracleTypes;
import util.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simob
 */
public class CommandeService {
    Connexion cnx=new Connexion();

    public List<Commande> getCommandes (int etat ) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
        List<Commande> cmds=new ArrayList<>();
        try {
           connection=cnx.getConnectionStatement();
         //   cs = connection.prepareCall(" select getCommande from dual ");
         //   cs.registerOutParameter(1, OracleTypes.CURSOR);
            CallableStatement call =
                    connection. prepareCall("select * from commande where etat=?");
            call.setInt(1,etat);
            call.execute ();
         ResultSet rset=call.getResultSet();

         cmds=creatListCommande(rset);
        } catch (Exception e) {
            System.err.println("Got an exception! get commande ");
            System.err.println ( " get commande" +e.getMessage());
        } finally {
            if (cs != null) cs.close();
            if (connection != null){ connection.close();
                System.out.println("connection closed   get commande");}
        }
        System.out.println(cmds);
        return cmds;

    }
    public Commande instantiateCommande(ResultSet rset) throws SQLException {

               return new Commande(rset.getLong("id"),rset.getDate("DATECOMMANDE"),
                       rset.getInt("ETAT"),rset.getLong("CLIENT_id"));

    }
    public List<Commande> creatListCommande(ResultSet rset) throws SQLException {
        List<Commande> list =new ArrayList<>();
        while (rset.next ()){
            Commande c=instantiateCommande(rset);
            list.add(c);
        }
        return list;

    }
    public Float getMontantTotal(long idCommande ) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
        Float montant=0F;
        try {
            connection=cnx.getConnectionStatement();
            //   Statement st=connection.createStatement();

           cs = connection.prepareCall("{ ?=call getMontantTotalCommande(?)}");
           cs.registerOutParameter(1, Types.FLOAT);
            cs.setLong(2, idCommande);
            cs.execute();
            // ResultSet result=cs.getResultSet();
            montant=  cs.getFloat(1);

        } catch (Exception e) {
            System.err.println("Got an exception! create Facture ");
            System.err.println ( "create Facture" +e.getMessage());
        } finally {
            if (cs != null) cs.close();
            if (connection != null){ connection.close();
                System.out.println("connection closed  create Facture");}

        }
        return montant;
    }
    public Commande getCommandeByFacture (Facture f) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
        Commande commande=new Commande();
        try {
            connection=cnx.getConnectionStatement();

            CallableStatement call =
                    connection. prepareCall("select * from commande where ID=?");
            call.setLong(1,f.getCommandeId());
            call.execute ();

            ResultSet rset=call.getResultSet();
            rset.next();
            commande=instantiateCommande(rset);

        } catch (Exception e) {
            System.err.println("Got an exception!  ");
            System.err.println ( " e" +e.getMessage());
        } finally {
            if (cs != null) cs.close();
            if (connection != null){ connection.close();
                System.out.println("connection closed   ");}
        }
        System.out.println(commande.toString());
        return commande;

    }
}
