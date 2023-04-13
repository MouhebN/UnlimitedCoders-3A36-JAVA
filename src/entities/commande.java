/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author rouja
 */
public class commande {
    private int id,quantite;
    private double prix;
    private produit produits;

    public commande() {
    }

    public produit getProduits() {
        return produits;
    }

    public void setProduits(produit produits) {
        this.produits = produits;
    }

    public commande(double prix,int quantite) {
        this.prix = prix;
        this.quantite = quantite;
    }

    public commande(int id, double prix, int quantite) {
        this.id = id;
        this.prix = prix;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "commande{" + "id=" + id + ", prix=" + prix + ", quantite=" + quantite + '}';
    }
    
    
    
}
