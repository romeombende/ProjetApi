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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import TourOperator.Metier.Pays;
import myconnections.DBConnection;


public class ModelePaysDB implements DAOPays {
      
    protected Connection dbConnect;
    
 /**
     * méthode permettant d'injecter la connexion à la DB venue de l'application
     * principale
     */
    public ModelePaysDB() {
        dbConnect = DBConnection.getConnection();
    }

    /**
     * création d'un pays sur base des valeurs de son objet métier
     *
     * @param obj pays à créer
     * @return pays créé
     */
    @Override
    public Pays create(Pays obj) {
        String req1 = "insert into api_pays(code,nomp,langue,monnaie) values(?,?,?,?)";
        String req2 = "select idpays from api_pays where code=? and nomp=? ";
        try ( PreparedStatement pstm1 = dbConnect.prepareStatement(req1);  PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, obj.getCode());
            pstm1.setString(2, obj.getNom());
            pstm1.setString(3, obj.getLangue());
            pstm1.setString(4, obj.getMonnaie());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                return null;
            } 
            pstm2.setString(1, obj.getCode());
            pstm2.setString(2, obj.getNom());
            ResultSet rs = pstm2.executeQuery();
            if (rs.next()) {
                int idpays = rs.getInt(1);
                obj.setIdpays(idpays);
                return read(obj);
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * récupération des données d'un pays sur base de son code     
     * @param pyrech pays recherché
     * @return pays trouvé
     */
    @Override
    public Pays read(Pays pyrech) {
        String code = pyrech.getCode();
        String req = "select * from api_pays pays left join api_ville ville on pays.idpays = ville.idpays where pays.code = ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);) {

            pstm.setString(1, code);
            try ( ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int idpays = rs.getInt("IDPAYS");
                    String nom = rs.getString("NOMP");
                    String langue = rs.getString("LANGUE");
                    String monnaie = rs.getString("MONNAIE");
                    Pays py = new Pays(idpays, code, nom, langue, monnaie);
                   
                    return py;

                } else {
                    return null;
                }

            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * mise à jour des données du pays sur base de son identifiant
     *
     * @return Pays
     * @param obj pays à mettre à jour
     */
    @Override
    public Pays update(Pays obj) {
        String req = "update api_pays set  langue=?  where idpays= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(2, obj.getIdpays());
            // pstm.setString(1, obj.getCode());
            //pstm.setString(2, obj.getNom());
            pstm.setString(1, obj.getLangue());
            //pstm.setString(4, obj.getMonnaie());
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
     * effacement du pays sur base de son identifiant
     *
     * @param obj pays à effacer
     */
    @Override
    public boolean delete(Pays obj) {

        String req = "delete from api_pays where idpays= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdpays());
            int n = pstm.executeUpdate();
            if (n == 0) return false;
            else return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Pays> readAll() {
        String req = "select * from api_pays";
        List<Pays> lp = new ArrayList<>();
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);  ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                int idpays = rs.getInt("IDPAYS");
                String code = rs.getString("CODE");
                String nom = rs.getString("NOMP");
                String langue = rs.getString("LANGUE");
                String monnaie = rs.getString("MONNAIE");
                lp.add(new Pays(idpays, code, nom, langue, monnaie));
            }
            return lp;

        } catch (Exception e) {
            return lp;
        }
    }
    
    
}
