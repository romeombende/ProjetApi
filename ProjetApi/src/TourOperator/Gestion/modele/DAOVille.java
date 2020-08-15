/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;

import TourOperator.Metier.Deplacement;
import java.util.List;
import TourOperator.Metier.Ville;

/**
 *
 * @author Romeo Mbende
 */
public interface DAOVille {
    Ville create(Ville newvl);

    boolean delete(Ville vlrech);

    Ville read(Ville vlrech);
    

    Ville update(Ville vlrech);
    
    List<Ville> readAll();
    
   
}
