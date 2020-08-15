/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Romeo Mbende
 */
public class Ville {
     /**
     * identifiant unique de la ville
     */
    protected int idville;
    /**
     * nom de la ville
     */
    protected String nom;
    /**
     * description de la ville
     */
    protected String description;
    /**
     * lattitude de la ville
     */
    protected Double lattitude;
    /**
     * longitude de la ville
     */
    protected Double longitude;
    /**
     * ensemble des commandes des pays
     */
    protected Pays pays ;
    /**
     * constructeur par défaut
     */
     protected List<Deplacement> deplacements = new ArrayList<>();
    /**
     * ensemble des commandes des arrivees
     */
    public Ville() {

    }
      

    /**
     * constructeur paramétré
     *
     * @param idville identifiant unique du ville, affecté par la base de
     * données
     * @param nom nom de la ville
     * @param description description de la ville
     * @param lattitude lattitude de la ville
     * @param longitude longitude de la ville
     */
   public Ville(int idville,String nom, String description, Double lattitude, Double longitude ,Pays pays) {
        this.idville = idville;
        this.nom = nom;
        this.description = description;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.pays = pays;
     
     }

    public Ville(String nom, String description, Double lattitude, Double longitude,Pays pays) {
        this.nom = nom;
        this.description = description;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.pays = pays;
    }
    
     public Ville(String nom, String description, Double lattitude, Double longitude) {
        this.nom = nom;
        this.description = description;
        this.lattitude = lattitude;
        this.longitude = longitude;
        
    }
     public Ville(String nom) {
        this.nom = nom;
    }

   
 /**
     * getter idville
     *
     * @return identifiant de la ville
     */
    public int getIdville() {
        return idville;
    }

    /**
     * setter idville
     *
     * @param idville identifiant de la ville
     */
    public void setIdville(int idville) {
        this.idville = idville;
    }

    /**
     * getter nom
     *
     * @return nom de la ville
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     *
     * @param nom nom de la ville
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter description
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter lattitude
     *
     * @return lattitude
     */
    public Double getLattitude() {
        return lattitude;
    }

    /**
     * setter lattitude
     *
     * @param lattitude lattitude de la ville
     */
    public void setLangue(Double lattitude) {
        this.lattitude = lattitude;
    }

    /**
     * getter longitude
     *
     * @return longitude de la ville
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * setter longitude
     *
     * @param longitude longitude de la ville
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Deplacement> getDeplacements() {
        return deplacements;
    }

    public void setDeplacements(List<Deplacement> deplacements) {
        this.deplacements = deplacements;
    }
    
    
    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Ville{" + "idville=" + idville + ", nom=" + nom + ", description=" + description + ", lattitude=" + lattitude + ", longitude=" + longitude + ", pays=" + pays + '}';
    }

   
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nom);
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
        final Ville other = (Ville) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    
}
