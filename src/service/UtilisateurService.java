/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Facture;
import bean.Reduction;
import util.Connexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simob
 */
public class UtilisateurService {
    Connexion cnx=new Connexion();
    public List<String> getCaissiers( ) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
        List< String> list=new ArrayList<>();
        try {
            connection=cnx.getConnectionStatement();

            CallableStatement call =
                    connection. prepareCall("select * from utilisateur where statut=?");
              call.setString(1,"caissier");
            call.execute ();

            ResultSet rset=call.getResultSet();

            while (rset.next ()){
                String l=String.valueOf(rset.getLong("id"));
                list.add(l);
            }
        } catch (Exception e) {
            System.err.println("Got an exception!  ");
            System.err.println ( " e" +e.getMessage());
        } finally {
            if (cs != null) cs.close();
            if (connection != null){ connection.close();
                System.out.println("connection closed   ");}
        }
        System.out.println(list.toString());
        return list;

    }
}
