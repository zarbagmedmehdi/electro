/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import util.Connexion;

import java.sql.*;

/**
 *
 * @author simob
 */
public class ClientService {
    Connexion cnx=new Connexion();

    public String getClientName (long id) throws SQLException {
      //  getClientNameById
        Connection connection=null;
        CallableStatement cs = null;
        String name="cc";
        try {
            connection=cnx.getConnectionStatement();
            //   Statement st=connection.createStatement();
            // insertFacture(var_mode VARCHAR2,var_id number ,var_util number ,var_reduc_id number)
            cs = connection.prepareCall("select getClientNameById(?) from dual");
            cs.setLong(1, id);
            cs.execute();
            ResultSet result=cs.getResultSet();
            result.next();
           name=result.getString(1);


            System.out.println(name);

        } catch (Exception e) {
            System.err.println("Got an exception! create Facture ");
            System.err.println ( "create Facture" +e.getMessage());
        } finally {
            if (cs != null) cs.close();
            if (connection != null){ connection.close();
                System.out.println("connection closed  create Facture");}
        }
        return name;
    }


}
