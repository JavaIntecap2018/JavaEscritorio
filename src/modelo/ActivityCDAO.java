/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;



import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ActivityCDAO extends Conexion{
     private String sql;
    private PreparedStatement ejecutar;
    private String respuesta;
    private String inicio;
    private Date fechaInicio;
    private String fin;
    private Date fechaFinal;
    //Agregar un nuevo Cliente, Los procesos de eliminar y actualizar son similares
    public String registrarActivityC(ActivityC activ) {
        
        respuesta = null;
        try {
            this.Conectar();
            
            this.
            sql = "insert into activity_calendar values(?,?,?,?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, activ.getActivity_calendar_id());
            ejecutar.setInt(2, activ.getActivity_id());
            ejecutar.setDate(3,    new Date((Integer.parseInt(activ.getDate_start().substring(0, 4)))-(1901), (Integer.parseInt(activ.getDate_start().substring(5, 7))+11),Integer.parseInt(activ.getDate_start().substring(8, 10))));
   
            ejecutar.setDate(4,  new Date((Integer.parseInt(activ.getDate_finish().substring(0, 4)))-(1901), (Integer.parseInt(activ.getDate_finish().substring(5, 7))+11),Integer.parseInt(activ.getDate_finish().substring(8, 10))));
           
            
            ejecutar.executeUpdate();
            
            respuesta="Registro Almacenado con Exito";
        } catch (SQLException ex) {
            System.out.println("Error en Statement" + ex);
            respuesta="No se pudo almacenar el registro";
        } finally {
            this.cerrarConex();
        }
        return respuesta;
    }
    
    //BUSCAR DATOS
    public ArrayList<ActivityC> listaActivityC() {
        ArrayList<ActivityC> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from activity_calendar";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                ActivityC activ = new ActivityC();
                activ.setActivity_calendar_id(resultado.getInt("activity_calendar_id"));
                activ.setActivity_id(resultado.getInt("activity_id"));
                fechaInicio=resultado.getDate("date_start");
                fechaFinal=resultado.getDate("date_finish");
                inicio=fechaInicio.toString();
                fin=fechaFinal.toString();
                 
               
                 activ.setDate_start(inicio);
                 activ.setDate_finish(fin);
               
                
                lista.add(activ);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
    
    public String eliminarActivityC(int codigo){
        try {
            this.Conectar();
            sql="delete from activity_calendar where activity_calendar_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, codigo);
            ejecutar.executeUpdate();
            respuesta="Registro Eliminado";
            
        } catch (SQLException ex) {
            //Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en Conexion: " + ex);
            respuesta="Error, no se puede eliminar el registro";
        }
         return respuesta;
    }
    
    public String daoModificarActivityC(ActivityC activ){
        try {
            System.out.println("limon");
            this.Conectar();
            sql="update activity_calendar set  activity_id=?, date_start=?, date_finish=? where activity_calendar_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, activ.getActivity_id());
            ejecutar.setDate(2,    new Date((Integer.parseInt(activ.getDate_start().substring(0, 4)))-(1901), (Integer.parseInt(activ.getDate_start().substring(5, 7))+11),Integer.parseInt(activ.getDate_start().substring(8, 10))));
   
            ejecutar.setDate(3,  new Date((Integer.parseInt(activ.getDate_finish().substring(0, 4)))-(1901), (Integer.parseInt(activ.getDate_finish().substring(5, 7))+11),Integer.parseInt(activ.getDate_finish().substring(8, 10))));
           
            ejecutar.setInt(4, activ.getActivity_calendar_id());
            ejecutar.executeUpdate();
            respuesta = "datos modificados con exito";
            
        } catch (SQLException ex) {
            
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        return respuesta;
    }
    
    
    //es para actualizar en prime faces 
     public ActivityC buscarId(int datos){
        ActivityC miActivity=new ActivityC();
        ResultSet result;
        try {
            this.Conectar();
            
            sql="select * from activity_calendar where activity_calendar_id=?";
           
            ejecutar = this.getMiconexion().prepareStatement(sql);
           
            ejecutar.setInt(1, datos);
         
            result=ejecutar.executeQuery();
           result.next();
           
            miActivity.setActivity_calendar_id(result.getInt("activity_calendar_id"));
             miActivity.setActivity_id(result.getInt("activity_id"));
             fechaInicio=result.getDate("date_start");
                fechaFinal=result.getDate("date_finish");
                inicio=fechaInicio.toString();
                fin=fechaFinal.toString();
                 miActivity.setDate_start(inicio);
                 miActivity.setDate_finish(fin);
            
            respuesta = "Petición procesada";
             
             ejecutar.close();
        } catch (SQLException ex) {
          
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        finally{  
            this.cerrarConex();
        
        }
         
        return miActivity;
    }
}
