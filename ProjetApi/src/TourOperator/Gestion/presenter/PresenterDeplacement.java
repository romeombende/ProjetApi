/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.presenter;

import java.math.BigDecimal;
import java.time.LocalDate;
import TourOperator.Gestion.modele.DAODeplacement;
import TourOperator.Gestion.modele.DAOVoyage;
import TourOperator.Gestion.vue.VueDeplacement;
import TourOperator.Metier.Deplacement;
import TourOperator.Metier.Classement;
import TourOperator.Metier.Ville;
import TourOperator.Metier.Voyage;


import java.util.List;

/**
 *
 * @author Romeo Mbende
 */
public class PresenterDeplacement {
    private DAODeplacement mdd;
    private VueDeplacement vued;
    private PresenterPays pp;
    private PresenterVoyage pvo;
    private PresenterVille pv;
    private PresenterDeplacement pd;
    private DAOVoyage mdvo;
            
    public PresenterDeplacement(DAODeplacement mdd, VueDeplacement vued,PresenterPays pp,PresenterVoyage pvo,PresenterVille pv,DAOVoyage mdvo) { 
        this.mdd = mdd; //injection de dépendance
        this.vued = vued;
        this.pp = pp;
        this.pvo = pvo;
        this.pv = pv;
        this.mdvo = mdvo;
    }


    public void gestion() {

        do {
            String chs = vued.menu();
            switch (chs) {
                case "1":
                    ajout();
                    break;
                case "2":
                    recherche();
                    break;
                case "3":
                    modification();
                    break;
                case "4":
                    suppression();
                    break;
                case "5":
                    addClassement();
                    break;
                case "6":
                    affAll();
                    break;
                case "7":
                    return;

            }
        } while (true);

    }

    protected void ajout() {

        Deplacement newdp = vued.create();
        Ville arrivee,depart;
        depart = pv.affAll();
        arrivee = pv.affAll();
        newdp.setDeparts(depart);
        newdp.setArrivees(arrivee);
        newdp = mdd.create(newdp);
        if (newdp == null) {
            vued.displayMsg("erreur lors de la création du deplacement - doublon");
            return;
        }
        newdp.getDeparts().getDeplacements().add(newdp);
        newdp.getArrivees().getDeplacements().add(newdp);
        vued.displayMsg("deplacement créé");
        vued.display(newdp);
        //vued.affichage(newdp);

    }

    protected Deplacement recherche() {

        int nrech = vued.read();
        Deplacement dp = new Deplacement(nrech,LocalDate.now(),LocalDate.now(),new BigDecimal(0),null,null);
        dp = mdd.read(dp);
        if (dp == null) {
            vued.displayMsg("deplacement introuvable");
            return null;
        }
        vued.display(dp);
        return dp;
    }

    protected void modification() {
        Deplacement dp = recherche();
        if (dp != null) {
            dp =  vued.update(dp);
            mdd.update(dp);
        }
    }

    protected void suppression() {
        Deplacement dp = recherche();
        if (dp != null) {
            String rep;
            do {
                rep = vued.getMsg("confirmez-vous la suppression (o/n) ? ");

            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
               if( mdd.delete(dp))vued.displayMsg("deplacement supprimé");
               else vued.displayMsg("deplacement non supprimé");
            }

        }
    }
    
    private void addClassement() {
          //pvo.addClassement();
         Deplacement dp=recherche();
       if(dp==null) return;
        //Voyage vy = new Voyage();
             //vy = pvo.recherche();
         Voyage vy =pvo.affAllvo();
       if(vy==null) return;
      
       String positions = vued.getMsg("position : ");
       Integer position = new Integer(positions);
       Classement cl = new Classement(position,dp,vy);
        //Classement res= mdc.create(cl);
       boolean res = mdd.add(dp, cl);
       //vy.getClassements().add(cl);
       //dp.getClassements().add(cl);
       if(res ) vued.displayMsg("classement ajoutée");
       else vued.displayMsg("classemnt non ajoutée");
        System.out.println(cl);
       
      
    }

    protected void affAll() {        
       List<Deplacement> ld = mdd.readAll();
       vued.affAll(mdd.readAll());
       
       
    }
    

  
}
