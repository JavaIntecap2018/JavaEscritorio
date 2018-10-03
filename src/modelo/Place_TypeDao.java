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

public class Place_TypeDao extends Conexion{
     private String sql;
    private PreparedStatement ejecutar;
    private String respuesta;
    //Agregar un nuevo Cliente, Los procesos de eliminar y actualizar son similares
    public String registrarPlace_Type(Place_Type tipo) {
        
        respuesta = null;
        try {
          
            this.Conectar();
            sql = "insert into place_type values(?,?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, tipo.getType_place_id());
            ejecutar.setString(2, tipo.getName());
           
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
    public ArrayList<Place_Type> listaPlace_Type() {
        ArrayList<Place_Type> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from place_type";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Place_Type tipo = new Place_Type();
                tipo.setType_place_id(resultado.getInt("place_type_id"));
                tipo.setName(resultado.getString("name"));
                lista.add(tipo);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
    
    public String eliminarPlace_Type(int codigo){
        try {
            this.Conectar();
            sql="delete from place_type where place_type_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, codigo);
            ejecutar.executeUpdate();
            respuesta="Registro Eliminado";
            
        } catch (SQLException ex) {
          
            System.out.println("Error en Conexion: " + ex);
            respuesta="Error, no se puede eliminar el registro";
        }
         return respuesta;
    }
    
    public String daoModificarPlace_Type(Place_Type tipo){
        try {
            this.Conectar();
            sql="update place_type set name=? where place_type_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, tipo.getName());
            
            ejecutar.setInt(2, tipo.getType_place_id());
            ejecutar.executeUpdate();
            respuesta = "datos modificados con exito";
            
        } catch (SQLException ex) {
           
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        return respuesta;
    }
    
    //es para actualizar en prime faces 
    public Place_Type buscarId(int datos){
        Place_Type place=new Place_Type();
        ResultSet result;
        try {
            this.Conectar();
            
            sql="select * from place_type where place_type_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, datos);
         
            result=ejecutar.executeQuery();
           result.next();
             place.setType_place_id(result.getInt("place_type_id"));
            place.setName(result.getString("name"));
            
            respuesta = "Petición procesada";

             ejecutar.close();
        } catch (SQLException ex) {
            
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        finally{  
            this.cerrarConex();
        
        }
         
        return place;
    }
   
}
