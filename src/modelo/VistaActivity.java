/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author sergio
 */
public class VistaActivity {
    
    private int town_id, activity_id, type_id, id_dept;
    private String town_name;
    private String activity_name, type_name, deparament_name;

    public VistaActivity() {
    }

    public VistaActivity(int id_dept,int town_id, int activity_id, int type_id, String town_name, String activity_name, String type_name, String deparament_name) {
        this.town_id = town_id;
        this.activity_id = activity_id;
        this.type_id = type_id;
        this.town_name = town_name;
        this.activity_name = activity_name;
        this.type_name = type_name;
        this.deparament_name = deparament_name;
        this.id_dept=id_dept;
    }

    public String getDeparament_name() {
        return deparament_name;
    }

    public void setDeparament_name(String deparament_name) {
        this.deparament_name = deparament_name;
    }

   

    public int getTown_id() {
        return town_id;
    }

    public void setTown_id(int town_id) {
        this.town_id = town_id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getTown_name() {
        return town_name;
    }

    public void setTown_name(String town_name) {
        this.town_name = town_name;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getId_dept() {
        return id_dept;
    }

    public void setId_dept(int id_dept) {
        this.id_dept = id_dept;
    }

    @Override
    public String toString() {
        return "VistaActivity{" + "town_id=" + town_id + ", activity_id=" + activity_id + ", type_id=" + type_id + ", id_dept=" + id_dept + ", town_name=" + town_name + ", activity_name=" + activity_name + ", type_name=" + type_name + ", deparament_name=" + deparament_name + '}';
    }

   

  
    
    
    
    
    
    
}
