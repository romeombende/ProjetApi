/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Metier.db;

import java.math.BigDecimal;
import TourOperator.Metier.Voyage;
import TourOperator.Metier.Deplacement;
import TourOperator.Metier.Classement;
import java.util.List;

/**
 *
 * @author Romeo Mbende
 */
public class ClassementDB extends Classement {
    protected int idclassement;
    protected int iddeplacement;
    protected int idvoyage;
    protected int position;
    
    
    public ClassementDB(int idclassement, int position,int iddeplacement, int idvoyage ) {
        super(position);
        this.idclassement = idclassement;
        this.iddeplacement = iddeplacement;
        this.idvoyage = idvoyage;
        this.position = position;
    }

     public int getIdclassement() {
        return idclassement;
    }
  
    public int getIdVoyage() {
        return idvoyage;
    }
    
    public int getIdDeplacement() {
        return iddeplacement;
    }

    public int getIddeplacement() {
        return iddeplacement;
    }

    public void setIddeplacement(int iddeplacement) {
        this.iddeplacement = iddeplacement;
    }

    public int getIdvoyage() {
        return idvoyage;
    }

    public void setIdvoyage(int idvoyage) {
        this.idvoyage = idvoyage;
    }

   
    public int getPosition() {
        return position;
    }

    
     public void setIdclassement(int idclassement) {
        this.idclassement = idclassement;
    }
     
    public void setIdVoyage(int idVoyage) {
        this.idvoyage = idVoyage;
    }


    public void setIdDeplacement(int idDeplacement) {
        this.iddeplacement = idDeplacement;
    }


    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ClassementDB{position ="+ position +" idclassement=" + idclassement + ", iddeplacement=" + iddeplacement + ", idvoyage=" + idvoyage + '}';
    }

    
  
    
    
}
