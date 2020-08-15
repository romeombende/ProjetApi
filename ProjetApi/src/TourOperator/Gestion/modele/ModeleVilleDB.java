/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TourOperator.Gestion.modele;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import TourOperator.Metier.Ville;
import TourOperator.Metier.Deplacement;
import TourOperator.Metier.Pays;
import myconnections.DBConnection;
/**
 *
 * @author Romeo Mbende
 */
public class ModeleVilleDB implements DAOVille {
    
     protected Connection dbConnect;

    /**
     * méthode permettant d'injecter la connexion à la DB venue de l'application
     * principale
     */
    public ModeleVilleDB() {
        dbConnect = DBConnection.getConnection();
    }

    /**
     * création d'une ville sur base des valeurs de son objet métier
     *
     * @param obj ville à créer
     * @return ville créé
     */
    @Override
    public Ville create(Ville obj) {

        String req1 = "insert into api_ville(nom,description,lattitude,longitude,idpays) values(?,?,?,?,?)";
        String req2 = "select idville from api_ville where nom=? and description=? ";
        try ( PreparedStatement pstm1 = dbConnect.prepareStatement(req1);  
              PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, obj.getNom());
            pstm1.setString(2, obj.getDescription());
            pstm1.setDouble(3, obj.getLattitude());
            pstm1.setDouble(4, obj.getLongitude());
            pstm1.setInt(5, obj.getPays().getIdpays());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                return null;
            }
            pstm2.setString(1, obj.getNom());
            pstm2.setString(2, obj.getDescription());
            ResultSet rs = pstm2.executeQuery();
            if (rs.next()) {
                int idville = rs.getInt(1);
                obj.setIdville(idville);
                return read(obj);
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * récupération des données d'une ville sur base de son identifiant
     *
     * @param vlrech ville recherché
     * @return ville trouvé
     */
    @Override
    public Ville read(Ville vlrech) {
        String nom = vlrech.getNom();
        String req = "select ville.idville,ville.nom,ville.description,ville.lattitude,ville.longitude,pays.idpays,pays.code,pays.nomp,pays.langue,pays.monnaie from api_ville ville left join api_pays pays on ville.idpays = pays.idpays where ville.nom = ?";
       //String req = "select * from api_ville ville left join api_pays pays on ville.idpays = pays.idpays where ville.nom = ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setString(1, nom);
             ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    int idville = rs.getInt("IDVILLE");
                    String description = rs.getString("DESCRIPTION");
                    Double lattitude = rs.getDouble("LATTITUDE");
                    Double longitude = rs.getDouble("LONGITUDE");
                    int idpays = rs.getInt("IDPAYS");
                    String code = rs.getString("CODE");
                    String nomp = rs.getString("NOMP");
                    String langue = rs.getString("LANGUE");
                    String monnaie = rs.getString("MONNAIE");
                    Pays py = new Pays(idpays, code, nomp, langue, monnaie);
                    //Pays py = new Pays ("","","","");
                    Ville vl = new Ville(idville, nom, description, lattitude, longitude,py);
                    
                    return vl;

                } else {
                    return null;
                }

            
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * mise à jour des données de la ville sur base de son identifiant
     *
     * @return Ville
     * @param obj ville à mettre à jour
     */
    @Override
    public Ville update(Ville obj) {
        String req = "update api_ville set description=? where idville= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(2, obj.getIdville());
            //pstm.setString(1, obj.getNom());
            pstm.setString(1, obj.getDescription());
            //pstm.setDouble(3, obj.getLattitude());
            //pstm.setDouble(4, obj.getLongitude());
            //pstm.setInt(5, obj.getPays().getIdpays());
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
     * effacement de la ville sur base de son identifiant
     *
     * @param obj ville à effacer
     */
    @Override
    public boolean delete(Ville obj) {

        String req = "delete from api_ville where idville= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdville());
            int n = pstm.executeUpdate();
            if (n == 0) return false;
            else return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Ville> readAll() {
        //String req = "select * from api_ville";
        String req="select ville.idville,ville.nom,ville.description,ville.lattitude,ville.longitude,pays.idpays,pays.code,pays.nomp,pays.langue,pays.monnaie from api_ville ville left join api_pays pays on ville.idpays = pays.idpays";
        List<Ville> lv = new ArrayList<>();
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);  ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                int idville = rs.getInt("IDVILLE");
                String nom = rs.getString("NOM");
                String description = rs.getString("DESCRIPTION");
                Double lattitude = rs.getDouble("LATTITUDE");
                Double longitude = rs.getDouble("LONGITUDE");
                int idpays = rs.getInt("IDPAYS");
                String code = rs.getString("CODE");
                String nomp = rs.getString("NOMP");
                String langue = rs.getString("LANGUE");
                String monnaie = rs.getString("MONNAIE");
                Pays pays = new Pays(idpays,code,nomp,langue,monnaie);
               // Pays pays = new Pays( idpays,"","","","");
                Ville vl = new Ville(idville, nom, description, lattitude, longitude, pays);
                lv.add(vl);
            }
            return lv;

        } catch (Exception e) {
            return lv;
        }
    }
    
   
      

}
