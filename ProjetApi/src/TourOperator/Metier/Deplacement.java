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
public class Deplacement {
     /**
     * identifiant unique du deplacement
     */
    protected int iddeplacement;
    /**
     * date de debut de deplacement
     */
    protected LocalDate dateHeureDebut;
    /**
     * date de fin du deplacement
     */
    protected LocalDate dateHeureFin;
    /**
     * cout du deplacement
     */
    protected BigDecimal cout;
    /**
     * ensemble des commandes des classements
     */
    protected List<Classement> classements = new ArrayList<>();
    /**
     * ensemble des commandes des arrivees
     */
    protected Ville departs ;
   
    /**
     * constructeur par défaut
     */
    protected Ville arrivees ;
    protected int i ;

    /**
     * ensemble des commandes des departs
     */
    
    public Deplacement() {

    }
      

    /**
     * constructeur paramétré
     *
     * @param iddeplacement identifiant unique du deplacement, affecté par la base de
     * données
     * @param dateHeureDebut date de debut du deplacement
     * @param dateHeureFin date de fin du deplacement
     * @param cout cout du deplacement
     */
   public Deplacement(int iddeplacement,LocalDate dateHeureDebut, LocalDate dateHeureFin, BigDecimal cout ,Ville departs,Ville arrivees) {
        this.iddeplacement = iddeplacement;
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
        this.cout = cout;
        this.departs = departs;
        this.arrivees = arrivees;
     
     }

    public Deplacement(int i, LocalDate dateHeureDebut, LocalDate dateHeureFin, BigDecimal cout) {
        this.i = i;
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
        this.cout = cout;
    }

    public Deplacement(LocalDate dateHeureDebut, LocalDate dateHeureFin, BigDecimal cout, Ville departs, Ville arrivees) {
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
        this.cout = cout;
        this.departs = departs;
        this.arrivees = arrivees;
    }

    public Deplacement(int iddeplacement) {
        this.iddeplacement = iddeplacement;
    }

    

  
   
    
 /**
     * getter iddeplacement
     *
     * @return identifiant du deplacement
     */
    public int getIddeplacement() {
        return iddeplacement;
    }

    /**
     * setter iddeplacement
     *
     * @param iddeplacement identifiant du deplacement
     */
    public void setIddeplacement(int iddeplacement) {
        this.iddeplacement = iddeplacement;
    }

    /**
     * getter dateHeureDebut
     *
     * @return date et heure du deplacement
     */
    public LocalDate getDateHeureDebut() {
        return dateHeureDebut;
    }

    /**
     * setter dateHeureDebut
     *
     * @param dateHeureDebut date de debut du deplacement
     */
    public void setDateHeuureDebut(LocalDate dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    /**
     * getter dateHeureFin
     *
     * @return dateHeureFin
     */
    public LocalDate getDateHeureFin() {
        return dateHeureFin;
    }

    /**
     * setter dateHeureFin
     *
     * @param dateHeureFin date de fin du deplacement 
     */
    public void setDateHeureFin(LocalDate dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    /**
     * getter cout
     *
     * @return cout
     */
    public BigDecimal getCout() {
        return cout;
    }

    /**
     * setter cout
     *
     * @param cout cout du deplacement
     */
    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }
    
     public Ville getDeparts() {
        return departs;
    }

    public void setDeparts(Ville departs) {
        this.departs = departs;
    }

    public Ville getArrivees() {
        return arrivees;
    }

    public void setArrivees(Ville arrivees) {
        this.arrivees = arrivees;
    }

    public List<Classement> getClassements() {
        return classements;
    }

    public void setClassements(List<Classement> classements) {
        this.classements = classements;
    }

    @Override
    public String toString() {
        return "Deplacement{" + "iddeplacement=" + iddeplacement + ", dateHeureDebut=" + dateHeureDebut + ", dateHeureFin=" + dateHeureFin + ", cout=" + cout + ", departs=" + departs + ", arrivees=" + arrivees + '}';
    }

   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.dateHeureDebut);
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
        final Deplacement other = (Deplacement) obj;
        if (!Objects.equals(this.dateHeureDebut, other.dateHeureDebut)) {
            return false;
        }
        return true;
    }

    public void setDateHeureDebut(LocalDate dateHeureDebut) {
        this.dateHeureDebut=dateHeureDebut;
        
    }

  
    

    
}
