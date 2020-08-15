/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.vue;

import TourOperator.Metier.Pays;
import TourOperator.Metier.Ville;
import TourOperator.Metier.Deplacement;
import java.util.List;

/**
 *
 * @author Romeo Mbende
 */
public class VueVille extends VueCommune{
     public String menu() {
        do {
            String chs = getMsg("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.affichage complet\n6.fin\nchoix : ");
            if(chs.matches("[1-6]"))return chs;   
            displayMsg("choix invalide");     
        } while (true);
    }

    public Ville create() {
        String nom = getMsg("nom de la ville : ");
        String description = getMsg("description: ");
        Double lattitude = new Double(getMsg("lattitude : "));
        Double longitude = new Double(getMsg("longitude : "));
        //String code = getMsg("code du pays : ");
        //String nomp = getMsg("nom du pays : ");
        //String langue = getMsg("langue du pays : ");
        //String monnaie = getMsg("monnaie du pays : ");
        //Pays pays = new Pays("","","","");
        Ville newvl = new Ville(nom, description, lattitude, longitude);
        return newvl;
    }

 
    public void display(Ville vl) {
        //displayMsg(vl.toString());
         System.out.println(vl);
    }


    public Ville update(Ville vl) {

        do {
            String ch = getMsg("1.changement de description\n2.fin");
            switch (ch) {
                case "1":
                    String description = getMsg("description:");
                    vl.setDescription(description);
                    break;
                case "2":
                    return vl;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }

    
    public String read() {
        String nom = getMsg("nom de la ville :");
        return nom;
    }

    
    public void affAll(List<Ville> lv) {
        int i=1;
        for (Ville vl : lv) {
            displayMsg(i+"."+vl.toString());
            i++;
        }           
    }
}
