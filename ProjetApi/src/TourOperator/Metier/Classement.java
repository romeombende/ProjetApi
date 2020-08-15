/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Metier;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Romeo Mbende
 */
public class Classement {
     /**
     * identifiant unique du classement
     */
    protected int idclassement;
    /**
     * position du classement
     */
    protected int position;
    /**
     * liste des voyages
     */
    protected List<Voyage> voyages = new ArrayList<>();
     /**
     * ensemble des commandes des voyages
     */
    
    protected  Voyage voyage;
     /**
     * ensemble des commandes des deplacements
     */
    protected Deplacement deplacement ;
    /**
     * constructeur par défaut
     */
    protected List<Deplacement> deplacements = new ArrayList<>();
    
    public Classement() {

    }
      

    /**
     * constructeur paramétré
     *
     * @param idclassement identifiant unique du classement, affecté par la base de
     * données
     * @param position position dans le classement
    
     */
   public Classement(int position,Voyage voyage,Deplacement deplacement ) {
        
        this.position = position;
        this.voyage = voyage;
        this.deplacement = deplacement;
     
     }
    public Classement(int idclassement) {
        this.idclassement = idclassement;
     }

    public Classement(int position, Deplacement deplacement, Voyage voyage) {
        this.position = position;
        this.deplacement = deplacement; 
        this.voyage = voyage;
          
    }



   

   
 /**
     * getter idclassement
     *
     * @return classement du voyage
     */
    public int getIdclassement() {
        return idclassement;
    }

    /**
     * setter idclassement
     *
     * @param idclassement identifiant du classement
     */
    public void setIdclassement(int idclassement) {
        this.idclassement = idclassement;
    }

    /**
     * getter position
     *
     * @return position du voyage
     */
    public int getPosition() {
        return position;
    }

    /**
     * setter position
     *
     * @param position position du voyage
     */
    public void setPosition(int position) {
        this.position = position;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public Deplacement getDeplacement() {
        return deplacement;
    }
    
   
    public void setDeplacement(Deplacement deplacement) {
        this.deplacement = deplacement;
    }


    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }

    public List<Deplacement> getDeplacements() {
        return deplacements;
    }

    public void setDeplacements(List<Deplacement> deplacements) {
        this.deplacements = deplacements;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idclassement;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Classement other = (Classement) obj;
        if (this.idclassement != other.idclassement) {
            return false;
        }
        return true;
    }
       
     

   
    @Override
    public String toString() {
        return "Classement{ position=" + position + ", voyage=" + voyage + ", deplacement=" + deplacement + '}';
    }

   

   
    

    
}
