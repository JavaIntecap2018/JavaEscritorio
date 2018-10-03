/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Departaments;
import modelo.DepartamentsDao;
import vista.JintFrmDeptos;

/**
 *
 * @author sergio
 */
//public class ControladorDeptos implements ActionListener, MouseListener {
public class ControladorDeptos extends MouseAdapter implements ActionListener{

    JintFrmDeptos vistaDeptos = new JintFrmDeptos();
    DepartamentsDao modeloDeptos = new DepartamentsDao();
    Departaments depto = new Departaments();

    public ControladorDeptos(JintFrmDeptos vistaDeptos, DepartamentsDao modeloDeptos) {
        this.modeloDeptos = modeloDeptos;
        this.vistaDeptos = vistaDeptos;
        this.vistaDeptos.jCmbCodigo.setVisible(false);
        this.vistaDeptos.jBtnGuardarDepto.addActionListener(this);
        this.vistaDeptos.jBtlEliminarDepto.addActionListener(this);
        this.vistaDeptos.jBtnModDepto.addActionListener(this);
        this.vistaDeptos.jBtnClear.addActionListener(this);
        
        
        
        this.vistaDeptos.jTableDeptos.addMouseListener(this);
        this.vistaDeptos.jCmbdeptos.addActionListener(this);

        llenarTabla(vistaDeptos.jTableDeptos);
        

    }

    public void llenarTabla(JTable tablaDatos) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        DefaultComboBoxModel prCmb=new DefaultComboBoxModel();
        DefaultComboBoxModel prCmbCodigos=new DefaultComboBoxModel();
        
        
        
        tablaDatos.setModel(modeloTabla);
        vistaDeptos.jCmbdeptos.setModel(prCmb);
        vistaDeptos.jCmbCodigo.setModel(prCmbCodigos);

        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Nombre");

        Object[] columna = new Object[2];

        int numRegistros = modeloDeptos.listaDepartamentos().size();

        for (int i = 0; i < numRegistros; i++) {
            //listaDeptos.put(modeloDeptos.listaDepartamentos().get(i).getId_dept(), modeloDeptos.listaDepartamentos().get(i).getDepartament_name());
            columna[0] = modeloDeptos.listaDepartamentos().get(i).getId_dept();
            columna[1] = modeloDeptos.listaDepartamentos().get(i).getDepartament_name();
            prCmb.addElement(modeloDeptos.listaDepartamentos().get(i).getDepartament_name());
            prCmbCodigos.addElement(modeloDeptos.listaDepartamentos().get(i).getId_dept());
            modeloTabla.addRow(columna);
        }

    }

    public void insertDeptos() {

        depto.setId_dept(Integer.parseInt(vistaDeptos.jTxtIdDepto.getText()));
        depto.setDepartament_name(vistaDeptos.jTxtNombreDepto.getText());

        String respuestaInsert = this.modeloDeptos.registrarDepartamentos(depto);

        if (respuestaInsert != null) {
            JOptionPane.showMessageDialog(null, respuestaInsert);
            clean();

        } else {
            JOptionPane.showMessageDialog(null, respuestaInsert);
        }

    }

    public void modDeptos() {
        depto.setId_dept(Integer.parseInt(vistaDeptos.jTxtIdDepto.getText()));
        depto.setDepartament_name(vistaDeptos.jTxtNombreDepto.getText());

        String mensaje = modeloDeptos.daoModificarDepartamentos(depto);
        JOptionPane.showMessageDialog(vistaDeptos, mensaje);

    }

    public void deleteDepto() {

        depto.setId_dept(Integer.parseInt(vistaDeptos.jTxtIdDepto.getText()));
        String mensaje = modeloDeptos.eliminarDepartamentos(depto.getId_dept());
        JOptionPane.showMessageDialog(vistaDeptos, mensaje);
        clean();

    }

    public void clean() {
        vistaDeptos.jTxtIdDepto.setText(null);
        vistaDeptos.jTxtNombreDepto.setText(null);
        vistaDeptos.jTxtIdDepto.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaDeptos.jBtnGuardarDepto) {
            insertDeptos();
            llenarTabla(vistaDeptos.jTableDeptos);

        }
        if (e.getSource() == vistaDeptos.jBtlEliminarDepto) {
            deleteDepto();
            llenarTabla(vistaDeptos.jTableDeptos);
        }

        if (e.getSource() == vistaDeptos.jBtnModDepto) {
            modDeptos();
            llenarTabla(vistaDeptos.jTableDeptos);

        }

        if (e.getSource() == vistaDeptos.jBtnClear) {
            clean();

        }
        if (e.getSource()==vistaDeptos.jCmbdeptos) {
            try {
                vistaDeptos.jCmbCodigo.setSelectedIndex(vistaDeptos.jCmbdeptos.getSelectedIndex());
            } catch (Exception exy) {
                System.out.println("error");
            }
            
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vistaDeptos.jTableDeptos) {

            vistaDeptos.jTxtIdDepto.setText(vistaDeptos.jTableDeptos.getValueAt(vistaDeptos.jTableDeptos.getSelectedRow(), 0).toString());
            vistaDeptos.jTxtNombreDepto.setText(vistaDeptos.jTableDeptos.getValueAt(vistaDeptos.jTableDeptos.getSelectedRow(), 1).toString());

        }

    }

    

}
