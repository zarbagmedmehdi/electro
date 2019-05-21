/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.CommandeItem;
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
public class CommandeItemService {
    Connexion cnx=new Connexion();

//    private Long id;
//    private int quantite;
//    private Long electromenagerID;
//    private Long commandeId;

    public CommandeItem instantiateCommandeItem(ResultSet rset) throws SQLException {
        return new CommandeItem(rset.getInt("quantite"),rset.getLong("ELECTROMENAGER_ID"),
                rset.getLong("COMMANDE_ID"));

    }
    public List<CommandeItem> creatListCommandeItem(ResultSet rset) throws SQLException {
        List<CommandeItem> list =new ArrayList<>();
        while (rset.next ()){
            CommandeItem ci=instantiateCommandeItem(rset);
            list.add(ci);
        }
        return list;

    }
    public List<CommandeItem> getCommandesItems (Long id ) throws SQLException {
        Connection connection=null;
        CallableStatement cs = null;
        List<CommandeItem> cmds=new ArrayList<>();
        try {
            connection=cnx.getConnectionStatement();
            //   cs = connection.prepareCall(" select getCommande from dual ");
            //   cs.registerOutParameter(1, OracleTypes.CURSOR);
            CallableStatement call =
                    connection. prepareCall("select * from commandeItem where COMMANDE_ID=?");
            call.setLong(1,id);
            call.execute ();
            ResultSet rset=call.getResultSet();

            cmds=creatListCommandeItem(rset);
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
}
