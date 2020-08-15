/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.presenter;

import TourOperator.Gestion.presenter.PresenterDeplacement;
import TourOperator.Gestion.vue.VueVoyage;
import TourOperator.Metier.Voyage;
import TourOperator.Gestion.modele.DAOVoyage;
import TourOperator.Gestion.modele.DAODeplacement;
import TourOperator.Metier.Classement;
import TourOperator.Metier.Deplacement;
import java.util.List;

/**
 *
 * @author Romeo Mbende
 */
public class PresenterVoyage {
    
    private DAOVoyage mdvo;
    private VueVoyage vuevo;
    private PresenterDeplacement pd;
    private DAODeplacement mdd;

    public PresenterVoyage(DAOVoyage mdvo, VueVoyage vuev,PresenterDeplacement pd,DAODeplacement mdd) { 
        this.mdvo = mdvo; //injection de dépendance
        this.vuevo = vuev;
        this.pd=pd;
        this.mdd=mdd;
    }


    public void gestion() {

        do {
            String chs = vuevo.menu();
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

        Voyage newvy = vuevo.create();
        newvy = mdvo.create(newvy);
        if (newvy == null) {
            vuevo.displayMsg("erreur lors de la création du voyage - doublon");
            return;
        }

        vuevo.displayMsg("voyage créé");
        vuevo.display(newvy);

    }

    protected Voyage recherche() {

        int nrech = vuevo.read();
        Voyage vy = new Voyage(nrech);
        System.err.println(vy.getIdvoyage());
        vy = mdvo.read(vy);
        if (vy == null) {
            vuevo.displayMsg("voyage introuvable");
            return null;
        }
        vuevo.display(vy);
        return vy;
    }

    protected void modification() {
        Voyage vy = recherche();
        if (vy != null) {
            vy =  vuevo.update(vy);
            mdvo.update(vy);
        }
    }

    protected void suppression() {
        Voyage vy = recherche();
        if (vy != null) {
            String rep;
            do {
                rep = vuevo.getMsg("confirmez-vous la suppression (o/n) ? ");

            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
               if( mdvo.delete(vy))vuevo.displayMsg("voyage supprimé");
               else vuevo.displayMsg("voyage non supprimé");
            }

        }
    }
    
    private void addClassement() {
      Deplacement dp= new Deplacement();
            dp = pd.recherche();
     // Deplacement dp =pd.affAll();
      // if(dp==null) return;
       Voyage vy=recherche();
       if(vy==null) return;
      
       if(dp==null) return;
       String positions = vuevo.getMsg("position : ");
       Integer position = new Integer(positions);
       Classement cl = new Classement(position,dp,vy);
       boolean res= mdvo.add(vy,cl); 
      //boolean res = mdd.add(vy, cl);
      //vy.getClassements().add(cl);
       //dp.getClassements().add(cl);
       if(res ) vuevo.displayMsg("classement ajoutée");
       else vuevo.displayMsg("classement non ajoutée");
       System.out.println(cl);
      
    }

    protected void affAll() {
        vuevo.affAll(mdvo.readAll());
    }
    
    protected Voyage affAllvo() {
       List<Voyage> lv = mdvo.readAll();
       vuevo.affAll(lv);
        do{
         String chs=vuevo.getMsg("numéro du voyage choisi (0 pour aucun) :");
         int ch=Integer.parseInt(chs);
         if(ch==0)return null;
         if(ch>=1 && ch <= lv.size()) return lv.get(ch-1);
        } while(true);
    }
  
}
