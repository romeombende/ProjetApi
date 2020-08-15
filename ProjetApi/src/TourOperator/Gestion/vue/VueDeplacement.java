/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.vue;


import TourOperator.Metier.Deplacement;
import java.math.BigDecimal;
import java.util.List;
import TourOperator.Metier.Classement;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Romeo Mbende
 */
public class VueDeplacement extends VueCommune{
     private Scanner sc = new Scanner(System.in);
     
      public void displayMsg(String msg) {
        System.out.println(msg);
    }

    public String getMsg(String invite) {
        displayMsg(invite);
        String msg = sc.nextLine();
        return msg;
    }
    
      public String menu() {
        do {
            String chs = getMsg("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.ajout classement\n6.affichage complet\n7.fin\nchoix : ");
            if(chs.matches("[1-7]"))return chs;   
            displayMsg("choix invalide");
        } while (true);
    }
    
    public Deplacement create() {
         
       
        System.out.println("Date de debut jj mm aaaa ?");
        int j = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        LocalDate d1 = LocalDate.of(a, m, j);
        System.out.println("Date de fin jj mm aaaa ?");
        int j2 = sc.nextInt();
        int m2 = sc.nextInt();
        int a2 = sc.nextInt();
        sc.skip("\n");
        LocalDate d2 = LocalDate.of(a2, m2, j2);
        BigDecimal cout = new BigDecimal(getMsg("cout : "));
        Deplacement newdp = new Deplacement(0, d1, d2, cout);
        return newdp;
        
    }
    public void affichage(Deplacement dp){
        getMsg(dp.toString());
    }
  
    public void display(Deplacement dp) {
        displayMsg(dp.toString());
        /*if (!dp.getClassements().isEmpty()) {
            String rep;
            do {
                rep = getMsg("Afficher ses deplacements (o/n) ");
            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                for (Classement cl : dp.getClassements()) {
                    displayMsg(cl.toString());
                }
            }
        }*/
       for (Classement cl :dp.getClassements()) {
                    displayMsg(cl.toString());
       }
    }

 
    public Deplacement update(Deplacement dp) {
       
        do {
            String ch = getMsg("1.changement du cout\n2.fin");
            switch (ch) {
                case "1":
                    BigDecimal ncout = new BigDecimal(getMsg(" nouveau cout : "));
                    dp.setCout(ncout);
                    break;
                case "2":
                    return dp;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }


    public int read() {
        String ns = getMsg("Identifiant deplacement : ");
        int n = Integer.parseInt(ns);
        return n;
    }

    public void affAll(List<Deplacement> ld) {
        int i =1;
        for(Deplacement dp:ld){
            displayMsg(i+"."+dp.toString());
            i++;
        }
    }

}
