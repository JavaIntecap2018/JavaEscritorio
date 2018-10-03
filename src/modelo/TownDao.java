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


public class TownDao extends Conexion{
     private String sql;
    private PreparedStatement ejecutar;
    private String respuesta;
    //Agregar un nuevo Cliente, Los procesos de eliminar y actualizar son similares
    public String registrarTown(Town town) {
        
        respuesta = null;
        try {
            this.Conectar();
            sql = "insert into town values(?,?,?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, town.getTown_id());
            ejecutar.setString(2, town.getName());
            ejecutar.setInt(3, town.getId_dep());
           
            
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
    public ArrayList<Town> listaTown() {
        ArrayList<Town> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from town";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Town town = new Town();
                town.setTown_id(resultado.getInt("town_id"));
                town.setName(resultado.getString("name"));
                town.setId_dep(resultado.getInt("id_dep"));
               
                
                
                lista.add(town);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
    
    public String eliminarTown(int codigo){
        try {
            this.Conectar();
            sql="delete from town where town_id=?";
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
    
    public String daoModificarTown(Town town){
        try {
            this.Conectar();
            sql="update town set name=?, id_dep=? where town_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, town.getName());
            ejecutar.setInt(2, town.getId_dep());
            ejecutar.setInt(3, town.getTown_id());
            ejecutar.executeUpdate();
            respuesta = "datos modificados con exito";
            
        } catch (SQLException ex) {
            
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        return respuesta;
    }
    
    //es para actualizar en prime faces 
      public Town buscarId(int datos){
        Town place=new Town();
        ResultSet result;
        try {
            this.Conectar();
            
            sql="select * from town where town_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, datos);
         
            result=ejecutar.executeQuery();
           result.next();
             place.setTown_id(result.getInt("town_id"));
            place.setName(result.getString("name"));
         
            place.setId_dep(result.getInt("id_dep"));
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
     
      //BUSCAR DATOS
    public ArrayList<Town> listaTownDep(int idDepto) {
        ArrayList<Town> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from town where id_dep=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, idDepto);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Town town = new Town();
                town.setTown_id(resultado.getInt("town_id"));
                town.setName(resultado.getString("name"));
                town.setId_dep(resultado.getInt("id_dep"));
               
                
                
                lista.add(town);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
      
    
}
