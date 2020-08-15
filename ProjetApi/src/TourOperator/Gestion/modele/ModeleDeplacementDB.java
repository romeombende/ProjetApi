/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import myconnections.DBConnection;
import TourOperator.Metier.Classement;
import TourOperator.Metier.Deplacement;
import TourOperator.Metier.Voyage;
import TourOperator.Metier.Ville;
import TourOperator.Metier.Pays;
import TourOperator.Metier.db.ClassementDB;


/**
 *
 * @author Romeo Mbende
 */
public class ModeleDeplacementDB implements DAODeplacement {
    
  protected Connection dbConnect;

    /**
     * méthode permettant d'injecter la connexion à la DB venue de l'application
     * principale
     */
    public ModeleDeplacementDB() {
        dbConnect = DBConnection.getConnection();
    }

    /**
     * création d'un deplacement sur base des valeurs de son objet métier
     *
     * @param obj commande à créer
     * @return  créé
     */
    @Override
    public Deplacement create(Deplacement obj) {
       
        String req1 = "insert into api_deplacement(dateHeureDebut, dateHeureFin, cout,idvilleDepart,idvilleArrivee) values(?,?,?,?,?)";
        String req2 = "select * from api_deplacement where idvilleDepart=? and idvilleArrivee=?";
        try ( PreparedStatement pstm1 = dbConnect.prepareStatement(req1);  PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
          
            pstm1.setDate(1, Date.valueOf(obj.getDateHeureDebut()));
            pstm1.setDate(2, Date.valueOf(obj.getDateHeureFin()));
            pstm1.setBigDecimal(3, obj.getCout());
            pstm1.setInt(4, obj.getDeparts().getIdville());
            pstm1.setInt(5, obj.getArrivees().getIdville());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                return null;
            }
            pstm2.setInt(1, obj.getDeparts().getIdville());
            pstm2.setInt(2, obj.getArrivees().getIdville());
            ResultSet rs2 = pstm2.executeQuery();
            if (rs2.next()) {
                int iddeplacement = rs2.getInt(1);
                obj.setIddeplacement(iddeplacement);
                return obj;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * récupération des données d'un deplacement sur base de son identifiant
     *
     * @param dprech deplacement recherchée
     * @return deplacement trouvé
     */
    @Override
    public Deplacement read(Deplacement dprech) {
        int iddeplacement = dprech.getIddeplacement();
         String req = "select * from api_deplacement deplacement left join api_ville ville on ville.idville=deplacement.idvilledepart left join api_ville ville on ville.idville=deplacement.idvillearrivee left join api_pays pays on pays.idpays=ville.idpays left join api_classement classement on deplacement.iddeplacement=classement.iddeplacement left join api_voyage voyage on voyage.idvoyage=classement.idvoyage where deplacement.iddeplacement=?";
         //String req = "select * from api_deplacement where iddeplacement=?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(1, iddeplacement);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                LocalDate dateHeureDebut = rs.getDate("DATEHEUREDEBUT").toLocalDate();
                LocalDate dateHeureFin = rs.getDate("DATEHEUREFIN").toLocalDate();
                BigDecimal cout = rs.getBigDecimal("COUT");
                int idvilledepart =rs.getInt("IDVILLEARRIVEE");
                int idvillearrivee =rs.getInt("IDVILLEDEPART");
                int idville = rs.getInt("IDVILLE");
                String description = rs.getString("DESCRIPTION");
                String nomv = rs.getString("NOM");
                Double lattitude = rs.getDouble("LATTITUDE");
                Double longitude = rs.getDouble("LONGITUDE");
                int idpays = rs.getInt("IDPAYS");
                String codeP = rs.getString("CODE");
                String nomp = rs.getString("NOMP");
                String langue = rs.getString("LANGUE");
                String monnaie = rs.getString("MONNAIE");
                Pays py = new Pays(idpays, codeP, nomp, langue, monnaie);
                Ville vl = new Ville(idville, nomv, description, lattitude, longitude,py);
                Ville departs=new Ville(idvilledepart,nomv, description, lattitude, longitude,py);
                Ville arrivees=new Ville(idvillearrivee,nomv, description, lattitude, longitude,py);
                Deplacement dp = new Deplacement(iddeplacement, dateHeureDebut, dateHeureFin, cout , departs, arrivees);
                List<Classement> lc = new ArrayList<>();
                int idclassement = rs.getInt("IDCLASSEMENT");
                if (idclassement != 0) {
                    int idvoyage = rs.getInt("IDVOYAGE");
                    int position = rs.getInt("POSITION");
                    String code = rs.getString("CODE");
                    String nom = rs.getString("NOM");
                    LocalDate dateDebut = rs.getDate("DATEDEBUT").toLocalDate();
                    LocalDate dateFin = rs.getDate("DATEFIN").toLocalDate();
                    BigDecimal coutTotal = rs.getBigDecimal("COUTTOTAL");
                    Voyage vy = new Voyage(idvoyage, code, nom, dateDebut, dateFin, coutTotal);
                    //Voyage vy = new VoyageDB(idvoyage,"","",null,null,new BigDecimal(0));
                        //TODO  instancer un voyage sur base des infos de la jointure à 3 tables et l.setVoyage(voyage);
                    Classement c = new ClassementDB(idclassement,position , dprech.getIddeplacement(),idvoyage );
                    lc.add(c);
                    while (rs.next()) {
                        idvoyage = rs.getInt("IDVOYAGE");
                        position = rs.getInt("POSITION");
                        code = rs.getString("CODE");
                        nom = rs.getString("NOM");
                        dateDebut = rs.getDate("DATEDEBUT").toLocalDate();
                        dateFin = rs.getDate("DATEFIN").toLocalDate();
                        coutTotal = rs.getBigDecimal("COUTTOTAL");
                        vy = new Voyage(idvoyage,code,nom,dateDebut, dateFin, coutTotal);
                            
                       c = new ClassementDB(idclassement,position , dprech.getIddeplacement(),idvoyage );
                    
                        lc.add(c);
                    }
               }
                dp.setClassements(lc);
                 
                return dp;
            } else {
                return null;
                
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * mise à jour des données d'un classement sur base de son identifiant
     *
     * @return deplacement
     * @param obj deplacement à mettre à jour
     */
    @Override
    public Deplacement update(Deplacement obj) {
        String req = "update api_deplacement set cout=? where iddeplacement = ?";
       
         try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(2, obj.getIddeplacement());
            //pstm.setDate(2, Date.valueOf(obj.getDateHeureDebut()));
            //pstm.setDate(3, Date.valueOf(obj.getDateHeureFin()));
            pstm.setBigDecimal(1, obj.getCout());
            //pstm.setInt(4, obj.getDeparts().getIdville());
            //pstm.setInt(5, obj.getArrivees().getIdville());
            int n = pstm.executeUpdate();
            if (n == 0) {
                return null;
            }    
                    
            return read(obj);
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean add(Deplacement dp,Classement cl){
         String req= "insert into api_classement(position,iddeplacement,idvoyage) values(?,?,?)";
         try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            Voyage vy = (Voyage)cl.getVoyage();
            int idvoyage = vy.getIdvoyage();
            pstm.setInt(2, dp.getIddeplacement());
            pstm.setInt(3, idvoyage);
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

    /**
     * effacement du classement sur base de son identifiant
     *
     * @param obj ville à effacer
     */
    @Override
    public boolean delete(Deplacement obj) {

        String req = "delete from api_deplacement where iddeplacement= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIddeplacement());
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

    @Override
    public List<Deplacement> readAll() {
        //String req ="select * from api_deplacement";
        String req ="select * from api_deplacement deplacement left join api_ville ville on deplacement.idvilledepart=ville.idville left join api_ville ville on deplacement.idvillearrivee=ville.idville left join api_ville ville on deplacement.idvilledepart=ville.idville left join api_pays pays on ville.idpays=pays.idpays left join api_classement classement on classement.iddeplacement=deplacement.iddeplacement";
        List<Deplacement> ldp = new ArrayList<>();
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);ResultSet rs = pstm.executeQuery();) {

            
            while (rs.next()) {
                int iddeplacement = rs.getInt("IDDEPLACEMENT");
                LocalDate dateHeureDebut = rs.getDate("DATEHEUREDEBUT").toLocalDate();
                LocalDate dateHeureFin = rs.getDate("DATEHEUREFIN").toLocalDate();
                BigDecimal cout = rs.getBigDecimal("COUT");
                int idvillearrivee =rs.getInt("IDVILLEARRIVEE");
                int idvilledepart =rs.getInt("IDVILLEDEPART");
                int idville = rs.getInt("IDVILLE");
                String nom = rs.getString("NOM");
                String description = rs.getString("DESCRIPTION");
                Double lattitude = rs.getDouble("LATTITUDE");
                Double longitude = rs.getDouble("LONGITUDE");
                //Pays pays = new Pays("","","","");
                int idpays = rs.getInt("IDPAYS");
                String nomp = rs.getString("NOMP");
                String code = rs.getString("CODE");
                String langue = rs.getString("LANGUE");
                String monnaie = rs.getString("MONNAIE");
                Pays pays = new Pays(idpays, code, nomp, langue, monnaie);
                Ville departs=new Ville(idvilledepart,nom,description,lattitude,longitude,pays);
                Ville arrivees=new Ville(idvillearrivee,nom,description,lattitude,longitude,pays);
                //Deplacement dp = new Deplacement(dateHeureDebut, dateHeureFin, cout, departs, arrivees);
                //ldp.add(dp);
                ldp.add(new Deplacement(iddeplacement,dateHeureDebut, dateHeureFin, cout, departs, arrivees));
            }
            return ldp;

        } catch (Exception e) {
            return ldp;
        }
    }

   

    
}
