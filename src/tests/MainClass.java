/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Assurance;
import entities.Remboursement;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import services.AssuranceCRUD;
import services.RemboursementCRUD;
import utils.MyConnection;

/**
 *
 * @author asus
 */
public class MainClass {

    public static void main(String[] args) {
        
        MyConnection db = MyConnection.getInstance();

        //Ajouter Assurance
        //Assurance a =new Assurance(5,"GAT","Ben Arous",500);
        //AssuranceCRUD ac = new AssuranceCRUD();
        //System.out.println(ac.ajouter(a));
        
        //Supprimer Assurance
        //Assurance a2=new Assurance(5);
        //AssuranceCRUD ac = new AssuranceCRUD();
        //System.out.println(ac.supprimer(a2));
        
        //Modifier Assurance
        //Assurance a3 =new Assurance(5, "STAR", "Tunis", 200);
        //AssuranceCRUD ac = new AssuranceCRUD();
        //System.out.println(ac.modifier(a3));
        
        //Afficher Assurance
        //Assurance a4 = new Assurance();
        //AssuranceCRUD ac = new AssuranceCRUD();
        //System.out.println(ac.afficher());
        
        //Ajouter Remboursement
        //Remboursement r =new Remboursement(1,"Accepte",LocalDate.of(2023,04,13),40);
        //RemboursementCRUD rc = new RemboursementCRUD();
        //System.out.println(rc.ajouter(r));
        
        //Supprimer Remboursement
        //Remboursement r2=new Remboursement(24);
        //RemboursementCRUD rc = new RemboursementCRUD();
        //System.out.println(rc.supprimer(r2));
        
        //Modifier Remboursements
        //Remboursement r3 =new Remboursement(25,"Rejete",LocalDate.of(2023,04,13),40);
        //RemboursementCRUD rc = new RemboursementCRUD();
        //System.out.println(rc.modifier(r3));
        
        //Afficher
        //Remboursement r4 = new Remboursement();
        //RemboursementCRUD rc = new RemboursementCRUD();
        //System.out.println(rc.afficher());
        
    }

}
