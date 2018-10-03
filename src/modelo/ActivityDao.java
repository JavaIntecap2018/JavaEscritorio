/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ActivityDao extends Conexion {

    private String sql;
    private PreparedStatement ejecutar;
    private String respuesta;

    //Agregar un nuevo Cliente, Los procesos de eliminar y actualizar son similares
    public String registrarActivity(Activity activity) {

        respuesta = null;
        try {

            System.out.println(activity.getActivityID());
            this.Conectar();
            sql = "insert into activity values(?,?,?,?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, activity.getActivityID());
            ejecutar.setString(2, activity.getName());
            ejecutar.setInt(3, activity.getTownID());
            //ejecutar.setInt(4, activity.getId_dep());
            ejecutar.setInt(4, activity.getType_id());
            ejecutar.executeUpdate();

            respuesta = "Registro Almacenado con Exito";
        } catch (SQLException ex) {
            System.out.println("Error en Statement" + ex);
            respuesta = "No se pudo almacenar el registro";
        } finally {
            this.cerrarConex();
        }
        return respuesta;
    }

    //BUSCAR DATOS
    public ArrayList<Activity> listaActivity() {
        ArrayList<Activity> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from activity order by activity_id";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Activity activity = new Activity();
                activity.setActivityID(resultado.getInt("activity_id"));
                activity.setName(resultado.getString("name"));
                activity.setTownID(resultado.getInt("town_id"));
                // activity.setId_dep(resultado.getInt("id_dep"));
                activity.setType_id(resultado.getInt("type_id"));

                lista.add(activity);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }

    public String eliminarActivity(int codigo) {
        try {
            this.Conectar();
            sql = "delete from activity where activity_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, codigo);
            ejecutar.executeUpdate();
            respuesta = "Registro Eliminado";

        } catch (SQLException ex) {

            System.out.println("Error en Conexion: " + ex);
            respuesta = "Error, no se puede eliminar el registro";
        }
        return respuesta;
    }

    public String daoModificarActivity(Activity activity) {
        try {
            this.Conectar();

            sql = "update activity set name=?, town_id=?, type_id=? where activity_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);

            ejecutar.setString(1, activity.getName());
            ejecutar.setInt(2, activity.getTownID());
            //  ejecutar.setInt(3, activity.getId_dep());
            ejecutar.setInt(3, activity.getType_id());
            ejecutar.setInt(4, activity.getActivityID());
            ejecutar.executeUpdate();
            respuesta = "datos modificados con exito";

        } catch (SQLException ex) {
            //Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en conexion: " + ex);
            respuesta = "No se puede modificar";
        }
        return respuesta;
    }

    public ArrayList<VistaActivity> listaActivityView() {
        ArrayList<VistaActivity> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from vw_activities order by activity_id";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                VistaActivity activity = new VistaActivity();
                activity.setId_dept(resultado.getInt("id_dept"));
                activity.setDeparament_name(resultado.getString("departament_name"));
                activity.setTown_id(resultado.getInt("town_id"));
                activity.setTown_name(resultado.getString("town_name"));
                activity.setActivity_name(resultado.getString("activity_name"));
                activity.setActivity_id(resultado.getInt("activity_id"));
                activity.setType_id(resultado.getInt("type_id"));
                activity.setType_name(resultado.getString("type_name"));

                lista.add(activity);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }

    //es para actualizar en prime faces 
//     public Activity buscarId(int datos){
//        Activity miActivity=new Activity();
//        ResultSet result;
//        try {
//            this.Conectar();
//            
//            sql="select * from activity where activity_id=?";
//           
//            ejecutar = this.getMiconexion().prepareStatement(sql);
//           
//            ejecutar.setInt(1, datos);
//         
//            result=ejecutar.executeQuery();
//           result.next();
//            miActivity.setActivityID(result.getInt("activity_id"));
//             miActivity.setName(result.getString("name"));
//             miActivity.setTownID(result.getInt("town_id"));
//             miActivity.setId_dep(result.getInt("id_dep"));
//             miActivity.setType_id(result.getInt("type_id"));
//            respuesta = "Petición procesada";
//             
//             ejecutar.close();
//        } catch (SQLException ex) {
//           
//            System.out.println("Error en conexion: " + ex);
//            respuesta="No se puede modificar";
//        }
//        finally{  
//            this.cerrarConex();
//        
//        }
//         
//        return miActivity;
//    }
}
