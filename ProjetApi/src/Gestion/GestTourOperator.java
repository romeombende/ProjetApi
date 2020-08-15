/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import java.util.Scanner;
import TourOperator.Gestion.modele.DAODeplacement;
import TourOperator.Gestion.modele.DAOVoyage;
import TourOperator.Gestion.modele.DAOPays;
import TourOperator.Gestion.modele.DAOVille;
import TourOperator.Gestion.modele.ModeleDeplacement;
import TourOperator.Gestion.modele.ModeleDeplacementDB;
import TourOperator.Gestion.modele.ModeleVoyage;
import TourOperator.Gestion.modele.ModeleVoyageDB;
import TourOperator.Gestion.modele.ModelePays;
import TourOperator.Gestion.modele.ModelePaysDB;
import TourOperator.Gestion.modele.ModeleVille;
import TourOperator.Gestion.modele.ModeleVilleDB;
import TourOperator.Gestion.presenter.PresenterDeplacement;
import TourOperator.Gestion.presenter.PresenterVoyage;
import TourOperator.Gestion.presenter.PresenterPays;
import TourOperator.Gestion.presenter.PresenterVille;
import TourOperator.Gestion.vue.VueDeplacement;
import TourOperator.Gestion.vue.VueVoyage;
import TourOperator.Gestion.vue.VuePays;
import TourOperator.Gestion.vue.VueVille;

/**
 *
 * @author Romeo Mbende
 */
public class GestTourOperator {
    private PresenterPays pp;
    private PresenterVille pv;
    private PresenterDeplacement pd;
    private PresenterVoyage pvo;
   
    
       Scanner sc = new Scanner(System.in);

    public void Gestion(int mode) {
        
        ///////////////////////////////////////////////////////////////////////////////
        //ATTENTION  à l'ordre il faut avoir instancié un élément avant de le passer en 
        //paramètre du constructeur des éléments suivants
        //cela concerne en particulier les presenters
        ///////////////////////////////////////////////////////////////////////////////
        DAOPays mdp=null;
        DAOVille mdv=null;
        DAODeplacement mdd=null;
        DAOVoyage mdvo=null;
           switch(mode)
           {
               case 1:
                   mdp=new ModelePays();
                   mdv=new ModeleVille();
                   mdd=new ModeleDeplacement();
                   mdvo=new ModeleVoyage();
                   break;
               case 2:
                   mdp=new ModelePaysDB();
                   mdv=new ModeleVilleDB();
                   mdd=new ModeleDeplacementDB();
                   mdvo=new ModeleVoyageDB(); 
                   break;
              default: 
                     System.out.println("mode incorrect");
                     System.exit(1);
           }
        
        VuePays vuep = new VuePays();
        VueVille vuev = new VueVille();
        VueDeplacement vued = new VueDeplacement();
        VueVoyage vuevo = new VueVoyage();
        pp = new PresenterPays(mdp, vuep);
        pv = new PresenterVille(mdv, vuev,pp); 
        pvo = new PresenterVoyage(mdvo, vuevo,pd,mdd);
        pd = new PresenterDeplacement(mdd, vued, pp,pvo,pv,mdvo);        
        pvo = new PresenterVoyage(mdvo, vuevo,pd,mdd);
        
        
        
        do {
            System.out.println("1.Pays\n2.Villes\n3.Voyages\n4.Deplacements\n5.fin");
            String ch = sc.nextLine();
            switch (ch) {
                case "1":
                    pp.gestion();
                    break;
                case "2":
                    pv.gestion();
                    break;
                case "3":
                    pvo.gestion();
                    break;
                case "4":
                    pd.gestion();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("choix invalide");
            }
        } while (true);
    }

    public static void main(String[] args) {
        int mode=0;
        do {
            System.out.println("1.Modèle collection\n2.Modèle DataBase");
            Scanner sc = new Scanner(System.in);
            try{
                mode = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Choix Incorrect");
              }
        } while (mode < 1 || mode > 2);
        
        GestTourOperator gt = new GestTourOperator();
        gt.Gestion(mode);
    }

}
