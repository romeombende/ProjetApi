/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;

import TourOperator.Metier.Classement;
import java.util.ArrayList;
import java.util.List;
import TourOperator.Metier.Deplacement;

/**
 *
 * @author Romeo Mbende
 */
public class ModeleDeplacement implements DAODeplacement {
    
    private List<Deplacement> mesDeplacements = new ArrayList<>();
    private int idDepAct = 0;
    
    @Override
    public Deplacement create(Deplacement newdp) {

        for (Deplacement dp : mesDeplacements) {
            if (dp.getDateHeureDebut().equals(newdp.getDateHeureDebut()) && dp.getDateHeureFin().equals(newdp.getDateHeureFin()) && dp.getCout().equals(newdp.getCout())) {
                   return null;
            }
        }
        idDepAct++;
        newdp.setIddeplacement(idDepAct);
        mesDeplacements.add(newdp);
        return newdp;
    }
    
    @Override
    public Deplacement read(Deplacement dprech) {
        int idrech = dprech.getIddeplacement();
        for (Deplacement dp : mesDeplacements) {
            if (dp.getIddeplacement() == idrech) {
                return dp;
            }
        }

        return null;
    }
    
    @Override
    public Deplacement update(Deplacement dprech) {
        Deplacement dp = read(dprech);
        if (dp == null) {
            return null;
        }
        dp.setDateHeureDebut(dprech.getDateHeureDebut());
        dp.setDateHeureFin(dprech.getDateHeureFin());
        dp.setCout(dprech.getCout());
        dp.setArrivees(dprech.getArrivees());
        dp.setDeparts(dprech.getDeparts());
        dp.setClassements(dprech.getClassements());
        return dp;
    }
    
    @Override
    public boolean delete(Deplacement dprech){
        Deplacement dp = read(dprech);
        if(dp!=null) {mesDeplacements.remove(dp);  
                      return true;
        }
        else return false;
    }
    @Override
    public List<Deplacement> readAll() {
        return mesDeplacements;
    }

    @Override
    public boolean add(Deplacement dp, Classement cl) {
        dp.getClassements().add(cl);
        return true;
    }
}
