/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Tourist_Place {
    public int tourist_place_id;
    public int id_dep;
    public String name;
    public int place_type_id;

    public int getTourist_place_id() {
        return tourist_place_id;
    }

    public void setTourist_place_id(int tourist_place_id) {
        this.tourist_place_id = tourist_place_id;
    }

    public int getId_dep() {
        return id_dep;
    }

    public void setId_dep(int id_dep) {
        this.id_dep = id_dep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlace_type_id() {
        return place_type_id;
    }

    public void setPlace_type_id(int place_type_id) {
        this.place_type_id = place_type_id;
    }
}
