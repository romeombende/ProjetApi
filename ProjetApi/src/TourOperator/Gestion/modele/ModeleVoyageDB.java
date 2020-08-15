/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;

/**
 *
 * @author Romeo Mbende
 */
import TourOperator.Metier.Classement;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import TourOperator.Metier.Voyage;
import TourOperator.Metier.Deplacement;
import TourOperator.Metier.Ville;
import TourOperator.Metier.db.ClassementDB;
import myconnections.DBConnection;

/**
 *
 * @author Romeo Mbende
 */
public class ModeleVoyageDB implements DAOVoyage {
   
    protected Connection dbConnect;
    
    /**
     * méthode permettant d'injecter la connexion à la DB venue de l'application
     * principale
     */
   public ModeleVoyageDB() {
        dbConnect = DBConnection.getConnection();
    }

    /**
     * création d'une ville sur base des valeurs de son objet métier
     *
     * @param obj voyage à créer
     * @return voyage créé
     */
    @Override
    public Voyage create(Voyage obj) {
        
       
        String req1 = "insert into api_voyage(code, nom, dateDebut, dateFin, coutTotal) values(?,?,?,?,?)";
        String req2 = "select * from api_voyage where code=? ";
         try ( PreparedStatement pstm1 = dbConnect.prepareStatement(req1);PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, obj.getCode());
            pstm1.setString(2, obj.getNom());
            pstm1.setDate(3, Date.valueOf(obj.getDateDebut()));
            pstm1.setDate(4, Date.valueOf(obj.getDateFin()));
            pstm1.setBigDecimal(5, obj.getCoutTotal());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                return null;
            }
            pstm2.setString(1, obj.getCode());
            ResultSet rs2 = pstm2.executeQuery();
            if (rs2.next()) {
                int idvoyage = rs2.getInt(1);
                obj.setIdvoyage(idvoyage);
                return obj;
            }else{
            return null;
              }
            } catch (Exception e) {
            return null;
            }
           
        
    }

    /**
     * récupération des données d'un voyage sur base de son identifiant
     *
     * @param vyrech voyage recherché
     * @return voyage trouvé
     */
    @Override
    public Voyage read(Voyage vyrech) {
        int idvoyage = vyrech.getIdvoyage();
        String req = "select * from api_voyage voyage left join api_classement classement on voyage.idvoyage=classement.idclassement left join api_deplacement deplacement on classement.iddeplacement= deplacement.iddeplacement where voyage.idvoyage = ?";
       
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);) {

            pstm.setInt(1, idvoyage);
            ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    String code = rs.getString("CODE");
                    String nom = rs.getString("NOM");
                    LocalDate dateDebut = rs.getDate("DATEDEBUT").toLocalDate();
                    LocalDate dateFin = rs.getDate("DATEFIN").toLocalDate();
                    BigDecimal coutTotal = rs.getBigDecimal("COUTTOTAL");
                    Voyage vy = new Voyage(idvoyage, code, nom, dateDebut, dateFin, coutTotal);
                    List<Classement> lc = new ArrayList<>();
                    int idclassement = rs.getInt("IDCLASSEMENT");
                      if (idclassement != 0) {
                           int iddeplacement = rs.getInt("IDDEPLACEMENT");
                           int position = rs.getInt("POSITION");
                           LocalDate dateHeureDebut = rs.getDate("DATEHEUREDEBUT").toLocalDate();
                           LocalDate dateHeureFin = rs.getDate("DATEHEUREFIN").toLocalDate();
                           BigDecimal cout = rs.getBigDecimal("COUT");
                           int idvilledepart =rs.getInt("IDVILLEA");
                           int idvillearrivee =rs.getInt("IDVILLED");
                           Ville departs=new Ville(idvilledepart,"","",null,null,null);
                           Ville arrivees=new Ville(idvillearrivee,"","",null,null,null);
                           Deplacement dp = new Deplacement(iddeplacement, dateHeureDebut, dateHeureFin, cout , departs, arrivees);
                           Classement c = new ClassementDB(idclassement, iddeplacement, vyrech.getIdvoyage(), position);
                           lc.add(c);
                           while (rs.next()) {
                               iddeplacement = rs.getInt("IDDEPLACEMENT");
                               position = rs.getInt("POSITION");
                               dateHeureDebut = rs.getDate("DATEHEUREDEBUT").toLocalDate();
                               dateHeureFin = rs.getDate("DATEHEUREFIN").toLocalDate();
                               cout = rs.getBigDecimal("COUT");
                               idvilledepart =rs.getInt("IDVILLEA");
                               idvillearrivee =rs.getInt("IDVILLED");
                               departs=new Ville(idvilledepart,"","",null,null,null);
                               arrivees=new Ville(idvillearrivee,"","",null,null,null);
                               dp = new Deplacement(iddeplacement, dateHeureDebut, dateHeureFin, cout , departs, arrivees);
                               c = new ClassementDB(idclassement, iddeplacement,vyrech.getIdvoyage(), position);
                           
                        lc.add(c);
                           }
                      }
                   vy.setClassements(lc);

                return vy;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * mise à jour des données du voyage sur base de son identifiant
     *
     * @return voyage
     * @param obj voyage à mettre à jour
     */
    @Override
    public Voyage update(Voyage obj) {
        Voyage vy = (Voyage)obj;
        String req = "update api_voyage set code=?,nom=?  where idvoyage = ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(3, vy.getIdvoyage());
            pstm.setString(1, obj.getCode());
            pstm.setString(2, obj.getNom());
            int n = pstm.executeUpdate();
            if (n == 0) {
               return null;
            }
            return read(obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * effacement du voyage sur base de son identifiant
     *
     * @param obj voyage à effacer
     */
    @Override
    public boolean delete(Voyage obj) {

        String req = "delete from api_voyage where idvoyage= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdvoyage());
            int n = pstm.executeUpdate();
            if (n == 0) return false;
            else return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Voyage> readAll() {
        String req = "select * from api_voyage";
        List<Voyage> lv = new ArrayList<>();
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);  ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                int idvoyage = rs.getInt("IDVOYAGE");
                String code = rs.getString("CODE");
                String nom = rs.getString("NOM");
                LocalDate dateDebut = rs.getDate("DATEDEBUT").toLocalDate();
                LocalDate dateFin = rs.getDate("DATEFIN").toLocalDate();
                BigDecimal coutTotal = rs.getBigDecimal("COUTTOTAL");
                lv.add(new Voyage(idvoyage, code, nom, dateDebut, dateFin, coutTotal));
            }
            return lv;

        } catch (Exception e) {
            return lv;
        }
    } 

    @Override
    public boolean add(Voyage vy, Classement cl) {
        
        String req= "insert into api_classement(position,iddeplacement,idvoyage) values(?,?,?)";
         try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            Deplacement dp = (Deplacement)cl.getDeplacement();
           
            int iddeplacement= dp.getIddeplacement();
            pstm.setInt(3, vy.getIdvoyage());
            pstm.setInt(2, iddeplacement);
            pstm.setInt(1, cl.getPosition());
            int n = pstm.executeUpdate();
            if (n == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

   
}
