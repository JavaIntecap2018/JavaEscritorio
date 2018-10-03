/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.sql.Date;
//import java.util.Date;
/**
 *
 * @author Usuario
 */
public class ActivityC {
    public int activity_calendar_id;
    public int activity_id;
    /*public Date date_start;
    public Date date_finish;*/
    public String date_start;
    public String date_finish;
    public int getActivity_calendar_id() {
        return activity_calendar_id;
    }

    public void setActivity_calendar_id(int activity_calendar_id) {
        this.activity_calendar_id = activity_calendar_id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getDate_start() {
        return date_start;
    }
 public String getDate_finish() {
       
        return date_finish;
    }
   /* public void setDate_start(String start) {
      
        this.date_start = new Date((Integer.parseInt(start.substring(0, 4)))-(1901), (Integer.parseInt(start.substring(5, 7))+11),Integer.parseInt(start.substring(8, 10)));
   
    }

   

    public void setDate_finish(String finish) {
         //System.out.println(finish);
       this.date_finish = new Date((Integer.parseInt(finish.substring(0, 4)))-(1901), (Integer.parseInt(finish.substring(5, 7))+11),Integer.parseInt(finish.substring(8, 10)));
   
         
    }*/
    public void setDate_start(String start) {
      
        this.date_start = start;
   
    }

   

    public void setDate_finish(String finish) {
         //System.out.println(finish);
       this.date_finish = finish;
   
         
    }
}
