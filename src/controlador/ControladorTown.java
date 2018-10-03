/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Town;

import modelo.TownDao;

import vista.JintFrmTown;

/**
 *
 * @author sergio
 */
public class ControladorTown extends MouseAdapter implements ActionListener {

    JintFrmTown town = new JintFrmTown();
    TownDao townDao = new TownDao();
    Town modelo = new Town();

    public ControladorTown(JintFrmTown vistaTown, TownDao modeloTown) {
        this.town = vistaTown;
        this.townDao = modeloTown;

        this.town.jBtnGuardar.addActionListener(this);
        this.town.jBtnActualizar.addActionListener(this);
        this.town.jBtnEliminar.addActionListener(this);
        this.town.jBtnLimpiar.addActionListener(this);

        this.town.jTblTipoCuenta.addMouseListener(this);

        cargarDatosTown();

    }

    public void cargarDatosTown() {
        //modelo de la jTable
        DefaultTableModel modelotabla = new DefaultTableModel();
        //asignar el modelo a la tabla de la vista
        this.town.jTblTipoCuenta.setModel(modelotabla);

        //asignar titulos a las columnas
        modelotabla.addColumn("Id");
        modelotabla.addColumn("Name");
        modelotabla.addColumn("Id_Depto");

        ArrayList<Town> lista = new ArrayList();
        //obtener la lista de tipos de cuenta
        lista = townDao.listaTown();
        //object
        Object[] valorColumna = new Object[3];

        for (int i = 0; i < lista.size(); i++) {
            valorColumna[0] = lista.get(i).getTown_id();
            valorColumna[1] = lista.get(i).getName();
            valorColumna[2] = lista.get(i).getId_dep();
            //permite agregar filas
            modelotabla.addRow(valorColumna);
        }

    }

    public void guardarTown() {
        Town townPrincipal = new Town();
        townPrincipal.setTown_id(Integer.parseInt(town.jTxtTown.getText()));
        townPrincipal.setName(town.jTxtName.getText());
        townPrincipal.setId_dep(Integer.parseInt(town.jTxtDepto.getText()));
        String respuesta = townDao.registrarTown(townPrincipal);
        if (respuesta != null) {

            JOptionPane.showMessageDialog(null, respuesta);

        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }

    }

    public void actualizarTown() {
        Town townPrincipal = new Town();
        townPrincipal.setTown_id(Integer.parseInt(town.jTxtTown.getText()));
        townPrincipal.setName(town.jTxtName.getText());
        townPrincipal.setId_dep(Integer.parseInt(town.jTxtDepto.getText()));
        String respuesta = townDao.daoModificarTown(townPrincipal);
        if (respuesta != null) {

            JOptionPane.showMessageDialog(null, respuesta);

        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }

    }
    
      public void eliminarTown() {
        Town townPrincipal = new Town();
        townPrincipal.setTown_id(Integer.parseInt(town.jTxtTown.getText()));

        String respuesta = townDao.eliminarTown(townPrincipal.getTown_id());
        if (respuesta != null) {

            JOptionPane.showMessageDialog(null, respuesta);

        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }
    }
      
        public void limpiarTown() {
        town.jTxtTown.setText(null);
        town.jTxtName.setText(null);
        town.jTxtDepto.setText(null);
        town.jTxtTown.requestFocus();

    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == town.jBtnGuardar) {
            guardarTown();
            cargarDatosTown();

        }

        if (e.getSource() == town.jBtnActualizar) {
            actualizarTown();
            cargarDatosTown();

        }
        
        if(e.getSource()==town.jBtnEliminar){
            
            eliminarTown();
            cargarDatosTown();
            
        }
        
        if(e.getSource()==town.jBtnLimpiar){
            limpiarTown();
            
        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == town.jTblTipoCuenta) {
            town.jTxtTown.setText(town.jTblTipoCuenta.getValueAt(town.jTblTipoCuenta.getSelectedRow(), 0).toString());
            town.jTxtName.setText(town.jTblTipoCuenta.getValueAt(town.jTblTipoCuenta.getSelectedRow(), 1).toString());
            town.jTxtDepto.setText(town.jTblTipoCuenta.getValueAt(town.jTblTipoCuenta.getSelectedRow(), 2).toString());

        }

    }

}
