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

public class Tourist_PlaceDao extends Conexion{
     private String sql;
    private PreparedStatement ejecutar;
    private String respuesta;
    //Agregar un nuevo Cliente, Los procesos de eliminar y actualizar son similares
    public String registrarTourist_Place(Tourist_Place tipo) {
        
        respuesta = null;
        try {
            
            this.Conectar();
            sql = "insert into tourist_place values(?,?,?,?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, tipo.getTourist_place_id());
            ejecutar.setInt(2, tipo.getId_dep());
            ejecutar.setString(3, tipo.getName());
            ejecutar.setInt(4, tipo.getPlace_type_id());
            
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
    public ArrayList<Tourist_Place> listaTourist_Place() {
        ArrayList<Tourist_Place> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from tourist_place";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Tourist_Place tipo = new Tourist_Place();
                tipo.setTourist_place_id(resultado.getInt("tourist_place_id"));
                tipo.setId_dep(resultado.getInt("id_town"));
                 tipo.setName(resultado.getString("name"));
               tipo.setPlace_type_id(resultado.getInt("place_type_id"));
                
                
                lista.add(tipo);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
    
    public String eliminarTourist_Place(int codigo){
        try {
            this.Conectar();
            sql="delete from tourist_place where tourist_place_id=?";
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
    
    public String daoModificarTourist_Place(Tourist_Place tipo){
        try {
            this.Conectar();
            sql="update tourist_place set id_town=?, name=?, place_type_id=? where tourist_place_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, tipo.getId_dep());
            ejecutar.setString(2, tipo.getName());
            ejecutar.setInt(3, tipo.getPlace_type_id());
            ejecutar.setInt(4, tipo.getTourist_place_id());
            ejecutar.executeUpdate();
            respuesta = "datos modificados con exito";
            
        } catch (SQLException ex) {
            
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        return respuesta;
    }
    
    //es para actualizar en prime faces 
    public Tourist_Place buscarId(int datos){
        Tourist_Place place=new Tourist_Place();
        ResultSet result;
        try {
            this.Conectar();
            
            sql="select * from tourist_place where tourist_place_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, datos);
         
            result=ejecutar.executeQuery();
           result.next();
             place.setTourist_place_id(result.getInt("tourist_place_id"));
            place.setId_dep(result.getInt("id_town"));
            place.setName(result.getString("name"));
            place.setPlace_type_id(result.getInt("place_type_id"));
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
