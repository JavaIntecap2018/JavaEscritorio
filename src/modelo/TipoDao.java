package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TipoDao extends Conexion{
     private String sql;
    private PreparedStatement ejecutar;
    private String respuesta;
    //Agregar un nuevo Cliente, Los procesos de eliminar y actualizar son similares
    public String registrarCliente(Tipo tipo) {
        
        respuesta = null;
        try {
            System.out.println(tipo.getCodigoTipo());
            this.Conectar();
            sql = "insert into types values(?,?,?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, tipo.getCodigoTipo());
            ejecutar.setString(2, tipo.getNombreTipo());
            ejecutar.setString(3, tipo.getDescripcion());
           
            
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
    public ArrayList<Tipo> listaTipo() {
        ArrayList<Tipo> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from types";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Tipo tipo = new Tipo();
                tipo.setCodigoTipo(resultado.getInt("type_id"));
                tipo.setNombreTipo(resultado.getString("name"));
                 tipo.setDescripcion(resultado.getString("description"));
               
                
                
                lista.add(tipo);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
    
    public String eliminarTipo(int codigo){
        try {
            System.out.println("lord");
            this.Conectar();
            sql="delete from types where type_id=?";
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
    
    public String daoModificarTipo(Tipo tipo){
        try {
            this.Conectar();
            sql="update types set description=?, name=? where type_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, tipo.getDescripcion());
            ejecutar.setString(2, tipo.getNombreTipo());
            ejecutar.setInt(3, tipo.getCodigoTipo());
            ejecutar.executeUpdate();
            respuesta = "datos modificados con exito";
            
        } catch (SQLException ex) {
           
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        return respuesta;
    }
    
    
     //BUSCAR DATOS
    public ArrayList<Tipo> listaTipoMostrar() {
        ArrayList<Tipo> lista = null;
        ResultSet resultado;
        
        try {
            this.Conectar();
            sql = "select * from types";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            
            while (resultado.next()) {
                Tipo tipo = new Tipo();
                tipo.setCodigoTipo(resultado.getInt("type_id"));
                tipo.setNombreTipo(resultado.getString("name"));
                tipo.setDescripcion(resultado.getString("description"));
               
                
                
                lista.add(tipo);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
    
        public ArrayList<Tipo> listaTipoMostrarTabla(int type) {
        ArrayList<Tipo> lista = null;
        ResultSet resultado;
        
        try {
            this.Conectar();
            sql = "select * from types where type_id =?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, type);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            
            while (resultado.next()) {
                Tipo tipo = new Tipo();
                tipo.setCodigoTipo(resultado.getInt("type_id"));
                tipo.setNombreTipo(resultado.getString("name"));
                tipo.setDescripcion(resultado.getString("description"));
               
                
                
                lista.add(tipo);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
        
    //es para actualizar en prime faces 
     public Tipo buscarId(int datos){
        Tipo tipo=new Tipo();
        ResultSet result;
        try {
            this.Conectar();
            
            sql="select * from types where type_id=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, datos);
         
            result=ejecutar.executeQuery();
           result.next();
             tipo.setCodigoTipo(result.getInt("type_id"));
            tipo.setNombreTipo(result.getString("name"));
            tipo.setDescripcion(result.getString("description"));
         
           
            respuesta = "Petición procesada";

             ejecutar.close();
        } catch (SQLException ex) {
           
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        finally{  
            this.cerrarConex();
        
        }
         
        return tipo;
    }
}
