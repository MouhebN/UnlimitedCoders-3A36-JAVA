/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Assurance;
import services.AssuranceCRUD;
import utils.MyConnection;

/**
 *
 * @author asus
 */
public class MainClass {

    public static void main(String[] args) {
        
        MyConnection db = MyConnection.getInstance();

        //Ajouter
        //Assurance a =new Assurance(5,"GAT","Ben Arous",500);
        //AssuranceCRUD ac = new AssuranceCRUD();
        //System.out.println(ac.ajouter(a));
        //Supprimer
        //Assurance a2=new Assurance(5);
        //AssuranceCRUD ac = new AssuranceCRUD();
        //System.out.println(ac.supprimer(a2));
        //Modifier
        //Assurance a3 =new Assurance(5, "STAR", "Tunis", 200);
        //AssuranceCRUD ac = new AssuranceCRUD();
        //System.out.println(ac.modifier(a3));
        //Afficher
        //Assurance a4 = new Assurance();
        //AssuranceCRUD ac = new AssuranceCRUD();
        //System.out.println(ac.afficher());
        
    }

}
