/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;

import TourOperator.Metier.Classement;
import java.util.ArrayList;
import java.util.List;
import TourOperator.Metier.Voyage;

/**
 *
 * @author Romeo Mbende
 */
public class ModeleVoyage implements DAOVoyage {
    private List<Voyage> mesVoyages = new ArrayList<>();
    private int idVoyAct = 0;
    
    @Override
    public Voyage create(Voyage newvy) {

        for (Voyage vy : mesVoyages) {
            if (vy.getCode().equals(newvy.getCode()) && vy.getNom().equals(newvy.getNom()) && vy.getDateDebut().equals(newvy.getDateDebut()) && vy.getDateFin().equals(newvy.getDateFin()) && vy.getCoutTotal().equals(newvy.getCoutTotal())) {
                   return null;
            }
        }
        idVoyAct++;
        newvy.setIdvoyage(idVoyAct);
        mesVoyages.add(newvy);
        return newvy;
    }
    
    @Override
    public Voyage read(Voyage vyrech) {
        int idrech = vyrech.getIdvoyage();
        for (Voyage vy : mesVoyages) {
            if (vy.getIdvoyage() == idrech) {
                return vy;
            }
        }

        return null;
    }
    
    @Override
    public Voyage update(Voyage vyrech) {
        Voyage vy = read(vyrech);
        if (vy == null) {
            return null;
        }
        vy.setCode(vyrech.getCode());
        vy.setNom(vyrech.getNom());
        vy.setDateDebut(vyrech.getDateDebut());
        vy.setDateFin(vyrech.getDateFin());
        vy.setCoutTotal(vyrech.getCoutTotal());
        vy.setClassements(vyrech.getClassements());
        return vy;
    }
    
    @Override
    public boolean delete(Voyage vyrech){
        Voyage vy = read(vyrech);
        if(vy!=null) {mesVoyages.remove(vy);  
                      return true;
        }
        else return false;
    }
    @Override
    public List<Voyage> readAll() {
        return mesVoyages;
    }

    @Override
    public boolean add(Voyage vy, Classement cl) {
        vy.getClassements().add(cl);
        return true;
    }  
    
}
 