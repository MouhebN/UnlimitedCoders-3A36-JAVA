/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import Entity.Consultation;
import java.util.List;

/**
 *
 * @author bytesudoer
 */
public interface InterfaceConsultation {
    public void ajouterConsultation(Consultation c);
    public void modifierConsultation(Consultation c);
    public void supprimerConsultation(int id);
    public List<Consultation> afficherConsultation();
    
}
