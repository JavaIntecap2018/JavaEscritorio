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
public class Activity {

    public int activityID;
    public String name;
    public int townID;
   // public int id_dep;
    public int type_id;

    public Activity() {
    }

    public Activity(int activityID, String name, int townID, int type_id) {
        this.activityID = activityID;
        this.name = name;
        this.townID = townID;
      
        this.type_id = type_id;

    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTownID() {
        return townID;
    }

    public void setTownID(int townID) {
        this.townID = townID;
    }


    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

}
