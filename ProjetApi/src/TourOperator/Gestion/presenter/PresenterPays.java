/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.presenter;


import java.util.List;
import TourOperator.Gestion.modele.DAOPays;
import TourOperator.Gestion.vue.VuePays;
import TourOperator.Metier.Pays;
import java.util.ArrayList;

/**
 *
 * @author Romeo Mbende
 */
public class PresenterPays {
    private DAOPays mdp;
    private VuePays vuep;

    public PresenterPays(DAOPays mdp, VuePays vuep) { 
        this.mdp = mdp;//injection de dépendance
        this.vuep = vuep;   
    }


    public void gestion() {

        do {
            String chs = vuep.menu();
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

        Pays newpy = vuep.create();
        newpy = mdp.create(newpy);
        if (newpy == null) {
            vuep.displayMsg("erreur lors de la création du pays - doublon");
            return;
        }

        vuep.displayMsg("pays créé");
        vuep.display(newpy);

    }

    protected Pays recherche() {

        String nrechs = vuep.read();
        Pays py = new Pays(nrechs);
        py = mdp.read(py);
        if (py == null) {
            vuep.displayMsg("pays introuvable");
            return null;
        }
        vuep.display(py);
        return py;
    }

    protected void modification() {
        Pays py = recherche();
        if (py != null) {
            py =  vuep.update(py);
            mdp.update(py);
        }
    }

    protected void suppression() {
        Pays py = recherche();
        if (py != null) {
            String rep;
            do {
                rep = vuep.getMsg("confirmez-vous la suppression (o/n) ? ");

            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
               if(mdp.delete(py))  vuep.displayMsg("pays supprimé");
               else vuep.displayMsg("pays non supprimé");
            }

        }
    }

    protected Pays affAll() {
       List<Pays> lp = new ArrayList<>();
       lp =  mdp.readAll();     
      vuep.affAll(lp);
       do{
         String chs=vuep.getMsg("numéro du pays choisi (0 pour aucun) :");
         int ch=Integer.parseInt(chs);
         if(ch==0)return null;
         if(ch>=1 && ch <= lp.size()) { 
             return lp.get(ch-1);
         }
        } while(true);
    }

    

}
