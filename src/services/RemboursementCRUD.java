/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Assurance;
import entities.Remboursement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author asus
 */
public class RemboursementCRUD implements IRemboursementService<Remboursement> {

    Connection cnx;
    Statement stm;
    ArrayList<Remboursement> list;
    
    public RemboursementCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public Boolean ajouter(Remboursement r) {
        int res = 0;
        try {
            String qry = "INSERT INTO `remboursement`(`date_remboursement`, `reponse`, `montant_rembourse`, `id_depot`) VALUES ('" + r.getDateRemboursement() + "','" + r.getReponse() + "','" + r.getMontantRembourse() + "','" + r.getIdDepot() + "')";
            stm = cnx.createStatement();
            res = stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            
        }
        
        return res != 0;
    }

    @Override
    public Boolean supprimer(Remboursement r) {
        int res = 0;
        try {

            stm = cnx.createStatement();
            String qry = "DELETE FROM`remboursement` WHERE id_remboursement = '" + r.getIdRemboursement() + "';";
            res = stm.executeUpdate(qry);
        }   catch (SQLException ex) {
             System.out.println("Exception: " + ex.getMessage());
        }
        return res !=0;
    }

    @Override
    public Boolean modifier(Remboursement r) {
        int res = 0;
        try {

            stm = cnx.createStatement();
            String qry = "UPDATE `remboursement` SET `date_remboursement`='" + r.getDateRemboursement() + "',`reponse`='" + r.getReponse() + "',`montant_rembourse`='" + r.getMontantRembourse() + "' WHERE `id_remboursement`='" + r.getIdRemboursement() + "';";
            res = stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return res != 0;
    }

    @Override
    public ObservableList<Remboursement> afficher() {
        List<Remboursement> remboursements = new ArrayList<>();

    try {
        String qry = "SELECT * FROM `remboursement`";
        stm = cnx.createStatement();

        ResultSet rs = stm.executeQuery(qry);

        while (rs.next()) {
            Remboursement r = new Remboursement();
            r.setIdRemboursement(rs.getInt("id_remboursement"));
            r.setDateRemboursement(rs.getDate("date_remboursement").toLocalDate());
            r.setReponse(rs.getString("reponse"));
            r.setMontantRembourse(rs.getFloat("montant_rembourse"));
            r.setIdDepot(rs.getInt("id_depot"));
            remboursements.add(r);
        }
        
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return FXCollections.observableArrayList(remboursements);
  
    }
    
}
