/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CommandeItem;
import bean.Electromenager;
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
public class ElectromenagerService {


        Connexion cnx=new Connexion();


        public Electromenager instantiateElectrom(ResultSet rset) throws SQLException {
            return new Electromenager(rset.getString("REFERENCE"),rset.getLong("id"),rset.getString("LIBELLE"),
                    rset.getDouble("PRIX_VENTE"));

        }


        public Electromenager getElectromenager (long idElectrom ) throws SQLException {
            Connection connection=null;
            CallableStatement cs = null;
            Electromenager el=new Electromenager();

            try {
                connection=cnx.getConnectionStatement();

                CallableStatement call =
                        connection. prepareCall("select * from electromenager where id=?");
                call.setLong(1,idElectrom);
                call.execute ();
                ResultSet rset=call.getResultSet();
                 rset.next();
                 el=instantiateElectrom(rset);
            } catch (Exception e) {
                System.err.println("Got an exception! get commande ");
                System.err.println ( " get commande" +e.getMessage());
            } finally {
                if (cs != null) cs.close();
                if (connection != null){ connection.close();
                    System.out.println("connection closed   get commande");}
            }
            System.out.println(el);

            return el;

        }

    }


