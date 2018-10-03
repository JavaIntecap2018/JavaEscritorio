/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ActivityC;
import modelo.ActivityCDAO;
import vista.JinFrmActivityCalendar;

/**
 *
 * @author sergio
 */
public class ControladorActivityCalendar extends MouseAdapter implements ActionListener {

    JinFrmActivityCalendar vistaAC = new JinFrmActivityCalendar();
    ActivityCDAO modeloACdao = new ActivityCDAO();
    ActivityC activity = new ActivityC();

    public ControladorActivityCalendar(JinFrmActivityCalendar vistaAC, ActivityCDAO modeloACdao) {

        this.vistaAC = vistaAC;
        this.modeloACdao = modeloACdao;

    }

    public void LlenarTabla(JTable tablaDatos) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaDatos.setModel(modeloTabla);
        
        modeloTabla.addColumn("Actividad Calendario Id");
        modeloTabla.addColumn("Actividad Id");
        modeloTabla.addColumn("Fecha Inicio");
        modeloTabla.addColumn("Fecha Fin");
        
        Object[] columna = new Object[4];
        int numRegistros = modeloACdao.listaActivityC().size();
        
        for(int i=0;i<numRegistros;i++){
            
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
