/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.vue;


import TourOperator.Metier.Pays;
import java.util.List;

/**
 *
 * @author Romeo Mbende
 */
public class VuePays extends VueCommune{
     public String menu() {
        do {
            String chs = getMsg("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.affichage complet\n6.fin\nchoix : ");
            if(chs.matches("[1-6]"))return chs;   
            displayMsg("choix invalide");     
        } while (true);
    }

    public Pays create() {
        String code = getMsg("code pays : ");
        String nom = getMsg("nom: ");
        String langue = getMsg("langue : ");
        String monnaie = getMsg("monnaie :");
        Pays newpy = new Pays(code, nom, langue, monnaie);
        return newpy;
    }

 
    public void display(Pays py) {
        displayMsg(py.toString());
    }


    public Pays update(Pays py) {

        do {
            String ch = getMsg("1.changement de la langue\n2.fin");
            switch (ch) {
                case "1":
                    String langue = getMsg("langue : ");
                    py.setLangue(langue);
                    break;
                case "2":
                    return py;
                default:
                    displayMsg("choix invalide");
            }
           
        } while (true);
    }

    public String read() {
        String code = getMsg("code de pays :");
        return code;
    }

    
    public void affAll(List<Pays> lp) {
        int i=1;
        for (Pays py : lp) {
            displayMsg(i+"."+py.toString());
            i++;
        }           
    }
}
