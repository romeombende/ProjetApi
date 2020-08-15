/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.vue;

import java.math.BigDecimal;
import java.time.LocalDate;
import TourOperator.Metier.Voyage;
import TourOperator.Metier.Classement;
import java.util.List;


/**
 *
 * @author Romeo Mbende
 */
public class VueVoyage extends VueCommune {
   public String menu() {
        do {
            String chs = getMsg("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.ajout classement\n6.affichage complet\n7.fin\nchoix : ");
            if(chs.matches("[1-7]"))return chs;   
            displayMsg("choix invalide");
        } while (true);
    }
    
    public Voyage create() {
        String code = getMsg("code : ");
        String nom = getMsg("nom : ");
        System.out.println("Date de debut jj mm aaaa ?");
        int j = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        LocalDate dateDebut = LocalDate.of(a, m, j);
        System.out.println("Date de fin jj mm aaaa ?");
        int j2 = sc.nextInt();
        int m2 = sc.nextInt();
        int a2 = sc.nextInt();
        sc.skip("\n");
        LocalDate dateFin = LocalDate.of(a2, m2, j2);
        BigDecimal coutTotal = new BigDecimal(getMsg("cout Total : "));
        Voyage newvy = new Voyage(code, nom, dateDebut, dateFin, coutTotal);
        return newvy;
    }

  
    public void display(Voyage vy) {
        displayMsg(vy.toString());
       /*if (!vy.getClassements().isEmpty()) {
            String rep;
            do {
                rep = getMsg("Afficher ses voyages (o/n) ");
            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                for (Classement cl : vy.getClassements()) {
                    displayMsg(cl.toString());
                }
            }
        }
         displayMsg(vy.toString());*/
       for (Classement cl:vy.getClassements()) {
                    displayMsg(cl.toString());
       }
    }

 
    public Voyage update(Voyage vy) {
       
        do {
            String ch = getMsg("1.changement du nom\n2.fin");
            switch (ch) {
                case "1":
                    String nom = getMsg("nouveau nom :");
                    vy.setNom(nom);//on devrait tester que cela ne crée pas de doublons nom
                    break;
                case "2":
                    return vy;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }


    public int read() {
        String ns = getMsg("Identifiant du voyage : ");
        int n = Integer.parseInt(ns);
        return n;
    }

    public void affAll(List<Voyage> lv) {
        int i =1;
        for(Voyage vy:lv){
            displayMsg(i+"."+vy.toString());
            i++;
        }
    }
}
