/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;

import java.util.ArrayList;
import java.util.List;
import TourOperator.Metier.Pays;
import TourOperator.Metier.Ville;

/**
 *
 * @author Romeo Mbende
 */
public class ModelePays implements DAOPays{
    private List<Pays> mesPays = new ArrayList<>();
    private int idPayAct = 0;



    public Pays create(Pays newpy) {

        for (Pays py : mesPays) {
            if (py.getCode().equals(newpy.getCode()) && py.getNom().equals(newpy.getNom()) && py.getLangue().equals(newpy.getLangue()) && py.getMonnaie().equals(newpy.getMonnaie())) {
                   return null;
            }
        }
        idPayAct++;
        newpy.setIdpays(idPayAct);
        mesPays.add(newpy);
        return newpy;
    }

    public Pays read(Pays pyrech) {
       /* String idrech = pyrech.getCode();
        for (Pays py : mesPays) {
            if (py.getCode() == idrech) {
                return py;
            }
        }
       */
       String code = pyrech.getCode();
        for(Pays py : mesPays){
            if(py.getCode().equals(code)) return py;
        }
     
        return null;
    }

    public Pays update(Pays pyrech) {
        Pays py = read(pyrech);
        if (py == null) {
            return null;
        }
        py.setCode(pyrech.getCode());
        py.setNom(pyrech.getNom());
        py.setLangue(pyrech.getLangue());
        py.setMonnaie(pyrech.getMonnaie());
        return py;
    }
    
    public boolean delete(Pays pyrech){
        Pays py = read(pyrech);
        if(py!=null) {mesPays.remove(py);  
                      return true;
        }
        else return false;
    } 
    
    @Override
    public List<Pays> readAll() {
        return mesPays;
    }
    
    
}
