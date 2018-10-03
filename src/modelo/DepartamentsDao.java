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
public class DepartamentsDao extends Conexion{
     private String sql;
    private PreparedStatement ejecutar;
    private String respuesta;
    //Agregar un nuevo Cliente, Los procesos de eliminar y actualizar son similares
    public String registrarDepartamentos(Departaments dep) {
        
        respuesta = null;
        try {
          
            this.Conectar();
            sql = "insert into departaments values(?,?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, dep.getId_dept());
            ejecutar.setString(2, dep.getDepartament_name());
           
           
            
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
    public ArrayList<Departaments> listaDepartamentos() {
        ArrayList<Departaments> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from departaments";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Departaments dep = new Departaments();
                dep.setId_dept(resultado.getInt("id_dept"));
                dep.setDepartament_name(resultado.getString("departament_name"));
                
                
                lista.add(dep);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
    
    public String eliminarDepartamentos(int codigo){
        try {
            this.Conectar();
            sql="delete from departaments where id_dept=?";
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
    
    public String daoModificarDepartamentos(Departaments dep){
        try {
            this.Conectar();
            sql="update departaments set departament_name=? where id_dept=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, dep.getDepartament_name());
            ejecutar.setInt(2, dep.getId_dept());
            ejecutar.executeUpdate();
            respuesta = "datos modificados con exito";
            
        } catch (SQLException ex) {
            
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        return respuesta;
    }
    
    
     //es para actualizar en prime faces 
   public Departaments buscarId(int datos){
        Departaments dep=new Departaments();
        ResultSet result;
        try {
            this.Conectar();
            
            sql="select * from departaments where id_dept=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, datos);
         
            result=ejecutar.executeQuery();
           result.next();
             dep.setId_dept(result.getInt("id_dept"));
             dep.setDepartament_name(result.getString("departament_name"));
            
            
            respuesta = "Petición procesada";

             ejecutar.close();
        } catch (SQLException ex) {
            
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        finally{  
            this.cerrarConex();
        
        }
         
        return dep;
    }
}
