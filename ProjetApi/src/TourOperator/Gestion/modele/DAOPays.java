/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;

import java.util.List;
import TourOperator.Metier.Pays;


/**
 *
 * @author Romeo Mbende
 */
public interface DAOPays {
    Pays create(Pays newpy);

    boolean delete(Pays pyrech);

    Pays read(Pays pyrech);
    

    Pays update(Pays pyrech);
    
    List<Pays> readAll();
 
}
