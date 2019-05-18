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
public class ReductionService {
    Connexion cnx=new Connexion();
    public Reduction instantiateReduction(ResultSet r) throws SQLException {

        return new Reduction(r.getLong("id"),r.getFloat("pourcentage"));
    }

    public Reduction getReductionAdequat (long id ) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
        Facture facture=new Facture();
        Reduction reduction=new Reduction();
        try {
            connection=cnx.getConnectionStatement();
            //   cs = connection.prepareCall(" select getCommande from dual ");
            //   cs.registerOutParameter(1, OracleTypes.CURSOR);
            CallableStatement call =
                    connection. prepareCall("select * from reduction where id=?");
            call.setLong(1,id);
            call.execute ();

            ResultSet rset=call.getResultSet();
            rset.next();
            reduction=instantiateReduction(rset);

        } catch (Exception e) {
            System.err.println("Got an exception!  ");
            System.err.println ( " e" +e.getMessage());
        } finally {
            if (cs != null) cs.close();
            if (connection != null){ connection.close();
                System.out.println("connection closed   ");}
        }
        System.out.println(facture.toString());
        return reduction;

    }
    public List<Reduction> getReductions( ) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
       List< Reduction> reductions=new ArrayList<>();
        try {
            connection=cnx.getConnectionStatement();
            //   cs = connection.prepareCall(" select getCommande from dual ");
            //   cs.registerOutParameter(1, OracleTypes.CURSOR);
            CallableStatement call =
                    connection. prepareCall("select * from reduction ");

            call.execute ();

            ResultSet rset=call.getResultSet();
            rset.next();
            reductions=creatListReductions(rset);

        } catch (Exception e) {
            System.err.println("Got an exception!  ");
            System.err.println ( " e" +e.getMessage());
        } finally {
            if (cs != null) cs.close();
            if (connection != null){ connection.close();
                System.out.println("connection closed   ");}
        }
        System.out.println(reductions.toString());
        return reductions;

    }
    public List<Reduction> creatListReductions(ResultSet rset) throws SQLException {
        List<Reduction> list =new ArrayList<>();
        while (rset.next ()){
            Reduction c=instantiateReduction(rset);
            list.add(c);
        }
        return list;

    }
}
