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
public class Pays {
    
    /**
     * identifiant unique du pays
     */
    protected int idpays;
    /**
     * code du pays
     */
    protected String code;
    /**
     * nom du pays
     */
    protected String nom;
    /**
     * langue du pays
     */
    protected String langue;
    /**
     * monnaie du pays
     */
    protected String monnaie;
     /**
     * ensemble des villes du pays
     */
    protected List<Ville> villes = new ArrayList<>();

    /**
     * constructeur par défaut
     */
    
    public Pays() {

    }
      

    /**
     * constructeur paramétré
     *
     * @param idpays identifiant unique du pays, affecté par la base de
     * données
     * @param code code du pays
     * @param nom nom du pays
     * @param langue langue du pays
     * @param monnaie monnaie du pays
     */
   public Pays(int idpays,String code, String nom, String langue, String monnaie) {
        this.idpays = idpays;
        this.code = code;
        this.nom = nom;
        this.langue = langue;
        this.monnaie = monnaie;
        
     
     }

    public Pays(String code) {
        this.code = code;  
    }
    public Pays(String code, String nom, String langue, String monnaie) {
        
        this.code = code;
        this.nom = nom;
        this.langue = langue;
        this.monnaie = monnaie;
     
     }
 /**
     * getter idpays
     *
     * @return identifiant du pays
     */
    public int getIdpays() {
        return idpays;
    }

    /**
     * setter idpays
     *
     * @param idpays identifiant du client
     */
    public void setIdpays(int idpays) {
        this.idpays = idpays;
    }

    /**
     * getter code
     *
     * @return code du pays
     */
    public String getCode() {
        return code;
    }

    /**
     * setter code
     *
     * @param code code du client
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * getter nom
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter langue
     *
     * @return langue
     */
    public String getLangue() {
        return langue;
    }

    /**
     * setter langue
     *
     * @param langue langue
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * getter monnaie
     *
     * @return monnaie du pays
     */
    public String getMonnaie() {
        return monnaie;
    }

    /**
     * setter monnaie
     *
     * @param monnaie monnaie du pays
     */
    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }
    
    @Override
    public String toString() {
        return "Pays{" + "idpays=" + idpays + ", code=" + code + ", nom=" + nom + ", langue=" + langue + ", monnaie=" + monnaie + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.idpays;
        hash = 67 * hash + Objects.hashCode(this.code);
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.langue);
        hash = 67 * hash + Objects.hashCode(this.monnaie);
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
        final Pays other = (Pays) obj;
        if (this.idpays != other.idpays) {
            return false;
        }
        return true;
    }

    
    
}
