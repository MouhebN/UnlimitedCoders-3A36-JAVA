/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;

/**
 *
 * @author asus
 */
public class Remboursement {  
    
    private int idRemboursement;
    private String reponse;
    private Date DateRemboursement;
    private float montantRembourse;

    public Remboursement(int idRemboursement, String reponse, Date DateRemboursement, float montantRembourse) {
        this.idRemboursement = idRemboursement;
        this.reponse = reponse;
        this.DateRemboursement = DateRemboursement;
        this.montantRembourse = montantRembourse;
    }

    public Remboursement(String reponse, Date DateRemboursement, float montantRembourse) {
        this.reponse = reponse;
        this.DateRemboursement = DateRemboursement;
        this.montantRembourse = montantRembourse;
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

    public Date getDateRemboursement() {
        return DateRemboursement;
    }

    public void setDateRemboursement(Date DateRemboursement) {
        this.DateRemboursement = DateRemboursement;
    }

    public float getMontantRembourse() {
        return montantRembourse;
    }

    public void setMontantRembourse(float montantRembourse) {
        this.montantRembourse = montantRembourse;
    }

    @Override
    public String toString() {
        return "Remboursement{" + "idRemboursement=" + idRemboursement + ", reponse=" + reponse + ", DateRemboursement=" + DateRemboursement + ", montantRembourse=" + montantRembourse + '}';
    }  
    
}
