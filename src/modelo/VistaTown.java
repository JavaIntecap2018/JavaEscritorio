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
//Nueva Vista
public class VistaTown {
    private int townId,idDept;
    private String townName,deptName;

    public VistaTown(int townId, int idDept, String townName, String deptName) {
        this.townId = townId;
        this.idDept = idDept;
        this.townName = townName;
        this.deptName = deptName;
    }

     public VistaTown() {
        
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public int getIdDept() {
        return idDept;
    }

    public void setIdDept(int idDept) {
        this.idDept = idDept;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "VistaTown{" + "townId=" + townId + ", idDept=" + idDept + ", townName=" + townName + ", deptName=" + deptName + '}';
    }
    
    
}
