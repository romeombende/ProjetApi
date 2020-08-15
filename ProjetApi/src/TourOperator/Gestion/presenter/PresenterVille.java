/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.presenter;

import TourOperator.Gestion.presenter.PresenterPays;
import java.util.List;
import TourOperator.Gestion.modele.DAOVille;
import TourOperator.Gestion.vue.VueVille;
import TourOperator.Metier.Pays;
import TourOperator.Metier.Ville;



/**
 *
 * @author Romeo Mbende
 */
public class PresenterVille {
    private DAOVille mdv;
    private VueVille vuev;
    private PresenterPays pp;

    public PresenterVille(DAOVille mdv, VueVille vuev,PresenterPays pp) { 
        this.mdv = mdv;//injection de dépendance
        this.vuev = vuev;
        this.pp = pp;
        
    }


    public void gestion() {

        do {
            String chs = vuev.menu();
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
                    affAll();
                    break;
                case "6":
                    return;

            }
        } while (true);

    }

    protected void ajout() {

        Ville newvl = vuev.create();
        Pays payschoisi = new Pays();
        payschoisi=pp.affAll();
        newvl.setPays(payschoisi);
        newvl = mdv.create(newvl);
        if (newvl == null) {
            vuev.displayMsg("erreur lors de la création du ville - doublon");
            return;
        } 
        vuev.displayMsg("ville créé");
        vuev.display(newvl);
      
    }

    protected Ville recherche() {

        String nrechs = vuev.read();
        Ville vl = new Ville(nrechs);
        vl = mdv.read(vl);
        if (vl == null) {
            vuev.displayMsg("ville introuvable");
            return null;
        }
        vuev.display(vl);
        return vl;
    }

    protected void modification() {
        Ville vl = recherche();
        if (vl != null) {
            vl =  vuev.update(vl);
            mdv.update(vl);
        }
    }

    protected void suppression() {
        Ville vl = recherche();
        if (vl != null) {
            String rep;
            do {
                rep = vuev.getMsg("confirmez-vous la suppression (o/n) ? ");

            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
               if(mdv.delete(vl))  vuev.displayMsg("ville supprimé");
               else vuev.displayMsg("ville non supprimé");
            }

        }
    }

    protected Ville affAll() {
       List<Ville> lv = mdv.readAll();
       vuev.affAll(mdv.readAll());
       do{
         String chs=vuev.getMsg("numéro de la ville choisie (0 pour aucune) :");
         int ch=Integer.parseInt(chs);
         if(ch==0)return null;
         if(ch>=1 && ch <= lv.size()) return lv.get(ch-1);
        } while(true);
    }

}
