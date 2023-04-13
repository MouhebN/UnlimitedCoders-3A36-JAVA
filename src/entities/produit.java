
package entities;

import javafx.scene.control.TextField;


public class produit {
     private int id;
    private String nom,matricule_asseu,prix;
    private String categorie_id ; 
    
    public produit() {
    }




    public String getCategorie() {
        return categorie_id;
    }

    public void setCategorie(String categorie_id) {
        this.categorie_id = categorie_id;
    }


    public produit(String categorie_id, String nom,String matricule_asseu,String prix) {
        this.matricule_asseu = matricule_asseu;
        this.prix = prix;
        this.nom = nom;
        this.categorie_id = categorie_id;
    }
 

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMatricule_asseu() {
        return matricule_asseu;
    }

    public void setMatricule_asseu(String matricule_asseu) {
        this.matricule_asseu = matricule_asseu;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", matricule_asseu=" + matricule_asseu + ", prix=" + prix + ", nom=" + nom + ", categorie_id=" + categorie_id + '}';
    }

    public void setCategorie(categorie c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    

    
    
    
    
    
}
