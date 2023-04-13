/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author asus
 */
public class Remboursement {  
    
    private int idRemboursement, idDepot;
    private String reponse;
    private LocalDate DateRemboursement;
    private float montantRembourse;

    public Remboursement(int idRemboursement) {
        this.idRemboursement = idRemboursement;
    }
    
    

    public Remboursement(int idRemboursement, int idDepot, String reponse, LocalDate DateRemboursement, float montantRembourse) {
        this.idRemboursement = idRemboursement;
        this.idDepot = idDepot;
        this.reponse = reponse;
        this.DateRemboursement = DateRemboursement;
        this.montantRembourse = montantRembourse;
    }
    
    public Remboursement(LocalDate DateRemboursement,String reponse, float montantRembourse, int idDepot) {
        this.idDepot = idDepot;
        this.reponse = reponse;
        this.DateRemboursement = DateRemboursement;
        this.montantRembourse = montantRembourse;
    }
    
    

    public Remboursement(int idRemboursement, String reponse, LocalDate DateRemboursement, float montantRembourse) {
        this.idRemboursement = idRemboursement;
        this.reponse = reponse;
        this.DateRemboursement = DateRemboursement;
        this.montantRembourse = montantRembourse;
    }

    public Remboursement(String reponse, LocalDate DateRemboursement, float montantRembourse) {
        this.reponse = reponse;
        this.DateRemboursement = DateRemboursement;
        this.montantRembourse = montantRembourse;
    }

    public Remboursement() {
    }

    public int getIdRemboursement() {
        return idRemboursement;
    }

    public void setIdRemboursement(int idRemboursement) {
        this.idRemboursement = idRemboursement;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public LocalDate getDateRemboursement() {
        return DateRemboursement;
    }

    public void setDateRemboursement(LocalDate DateRemboursement) {
        this.DateRemboursement = DateRemboursement;
    }

    public float getMontantRembourse() {
        return montantRembourse;
    }

    public void setMontantRembourse(float montantRembourse) {
        this.montantRembourse = montantRembourse;
    }

    public int getIdDepot() {
        return idDepot;
    }

    public void setIdDepot(int idDepot) {
        this.idDepot = idDepot;
    }

    @Override
    public String toString() {
        return "Remboursement{" + "idRemboursement=" + idRemboursement + ", idDepot=" + idDepot + ", reponse=" + reponse + ", DateRemboursement=" + DateRemboursement + ", montantRembourse=" + montantRembourse + '}';
    }
    
}
