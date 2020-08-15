/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;

import TourOperator.Metier.Deplacement;
import java.util.ArrayList;
import java.util.List;
import TourOperator.Metier.Ville;

/**
 *
 * @author Romeo Mbende
 */
public class ModeleVille implements DAOVille {
    private List<Ville> mesVilles = new ArrayList<>();
    private int idVilAct = 0;

    public Ville create(Ville newvl) {

        for (Ville vl : mesVilles) {
            if (vl.getNom().equals(newvl.getNom()) && vl.getDescription().equals(newvl.getDescription()) && vl.getLattitude().equals(newvl.getLattitude()) && vl.getLongitude().equals(newvl.getLongitude())) {
                   return null;
            }
        }
        idVilAct++;
        newvl.setIdville(idVilAct);
        mesVilles.add(newvl);
        return newvl;
    }

    public Ville read(Ville vlrech) {
        String nom = vlrech.getNom();
        for(Ville vl : mesVilles){
            if(vl.getNom().equals(nom)) return vl;
        }

        return null;
    }

    public Ville update(Ville vlrech) {
        Ville vl = read(vlrech);
        if (vl == null) {
            return null;
        }
        vl.setNom(vlrech.getNom());
        vl.setDescription(vlrech.getDescription());
        vl.setLattitude(vlrech.getLattitude());
        vl.setLongitude(vlrech.getLongitude());
        vl.setPays(vlrech.getPays());
        return vl;
    }
    
    public boolean delete(Ville vlrech){
        Ville vl = read(vlrech);
        if(vl!=null) {mesVilles.remove(vl);  
                      return true;
        }
        else return false;
    }
    
    @Override
    public List<Ville> readAll() {
        return mesVilles;
    }
     
  
   
}
