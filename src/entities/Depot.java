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
public class Depot {
    
    private int idDepot;
    private Date dateDepot;
    private String etat, regime;
    private float totalDepense;

    public Depot() {
    }

    public Depot(int idDepot, Date dateDepot, String etat, String regime, float totalDepense) {
        this.idDepot = idDepot;
        this.dateDepot = dateDepot;
        this.etat = etat;
        this.regime = regime;
        this.totalDepense = totalDepense;
    }

    public Depot(Date dateDepot, String etat, String regime, float totalDepense) {
        this.dateDepot = dateDepot;
        this.etat = etat;
        this.regime = regime;
        this.totalDepense = totalDepense;
    }

    public int getIdDepot() {
        return idDepot;
    }

    public void setIdDepot(int idDepot) {
        this.idDepot = idDepot;
    }

    public Date getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(Date dateDepot) {
        this.dateDepot = dateDepot;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public float getTotalDepense() {
        return totalDepense;
    }

    public void setTotalDepense(float totalDepense) {
        this.totalDepense = totalDepense;
    }

    @Override
    public String toString() {
        return "Depot{" + "idDepot=" + idDepot + ", dateDepot=" + dateDepot + ", etat=" + etat + ", regime=" + regime + ", totalDepense=" + totalDepense + '}';
    }
    
}
