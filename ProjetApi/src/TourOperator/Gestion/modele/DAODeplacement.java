/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;

import TourOperator.Metier.Classement;
import TourOperator.Metier.Deplacement;
import java.util.List;

/**
 *
 * @author Romeo Mbende
 */
public interface DAODeplacement {
    
    Deplacement create(Deplacement newdp);

    boolean delete(Deplacement dprech);

    Deplacement read(Deplacement dprech);

    Deplacement update(Deplacement dprech);
   
    public boolean add(Deplacement dp,Classement cl);
    
    List<Deplacement> readAll();
    
    
}
