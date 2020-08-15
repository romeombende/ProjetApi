/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;

import TourOperator.Metier.Classement;
import TourOperator.Metier.Voyage;
import java.util.List;

/**
 *
 * @author Romeo Mbende
 */
public interface DAOVoyage {
     Voyage create(Voyage newvy);

    boolean delete(Voyage vyrech);

    Voyage read(Voyage vyrech);

    Voyage update(Voyage vyrech);
   
    public boolean add(Voyage vy,Classement cl);
    
    List<Voyage> readAll();
    
}
