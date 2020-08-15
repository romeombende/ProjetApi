/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Romeo Mbende
 */
public class Voyage {
     /**
     * identifiant unique du voyage
     */
    protected int idvoyage;
    /**
     * code du voyage
     */
    protected String code;
    /**
     * nom du voyage
     */
    protected String nom;
    /**
     * date de debut de voyage
     */
    protected LocalDate dateDebut;
    /**
     * date de fin de voyage
     */
    protected LocalDate dateFin;
    /**
     * cout total du voyage
     */
    protected BigDecimal coutTotal;
    /**
     * ensemble des commandes des classements
     */
    protected List<Classement> classements = new ArrayList<>();
    /**
     * constructeur par défaut
     */
    public Voyage() {

    }
      

    /**
     * constructeur paramétré
     *
     * @param idvoyage identifiant unique du pays, affecté par la base de
     * données
     * @param code code du pays
     * @param nom nom du pays
     * @param dateDebut date debut du voyage
     * @param dateFin date fin du voyage
     * @param coutTotal cout totl du voyage
     */
   public Voyage(String code, String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal coutTotal) {
        this.code = code;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.coutTotal = coutTotal;
     }
   
   public Voyage(int idvoyage) {
        this.idvoyage = idvoyage;
   }

    public Voyage(int idvoyage, String code, String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal coutTotal) {
         this.idvoyage = idvoyage;
         this.code = code;
         this.nom = nom;
         this.dateDebut = dateDebut;
         this.dateFin = dateFin;
         this.coutTotal = coutTotal;
    }
   

 /**
     * getter idvoyage
     *
     * @return identifiant du voyage
     */
    public int getIdvoyage() {
        return idvoyage;
    }

    /**
     * setter idvoyage
     *
     * @param idvoyage identifiant du voyage
     */
    public void setIdvoyage(int idvoyage) {
        this.idvoyage = idvoyage;
    }

    /**
     * getter code
     *
     * @return code du voyage
     */
    public String getCode() {
        return code;
    }

    /**
     * setter code
     *
     * @param code code du voyage
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
     * getter dateDebut
     *
     * @return dateDebut
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * setter dateDebut
     *
     * @param dateDebut dateDebut
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * getter dateFin
     *
     * @return dateFin du voyage
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * setter dateFin
     *
     * @param dateFin dateFin du voyage
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    
    public BigDecimal getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(BigDecimal coutTotal) {
        this.coutTotal = coutTotal;
    }

    public List<Classement> getClassements() {
        return classements;
    }

    public void setClassements(List<Classement> classements) {
        this.classements = classements;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.idvoyage;
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
        final Voyage other = (Voyage) obj;
        if (this.idvoyage != other.idvoyage) {
            return false;
        }
        return true;
    }

    

  @Override
    public String toString() {
        return "Voyage{" + "idvoyage=" + idvoyage + ", code=" + code + ", nom=" + nom + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", coutTotal=" + coutTotal + '}';
    }
}
