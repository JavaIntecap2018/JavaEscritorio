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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Departaments;
import modelo.DepartamentsDao;
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
    ArrayList<Departaments> listaDeptos = new ArrayList<>(); //datos departamentos

    ArrayList<Town> listaMunicipios = new ArrayList<Town>();

    public ControladorTown(JintFrmTown vistaTown, TownDao modeloTown) {
        this.town = vistaTown;
        this.townDao = modeloTown;

        this.town.jBtnGuardar.addActionListener(this);
        this.town.jBtnActualizar.addActionListener(this);
        this.town.jBtnEliminar.addActionListener(this);
        this.town.jBtnLimpiar.addActionListener(this);
        
        this.town.jTblTipoCuenta.addMouseListener(this);
        this.town.jCmbDept.addActionListener(this);
        this.town.jTxtDepto.setEnabled(false);
        this.town.jTxtName.setEnabled(true);

        cargarDatosTown();
        llenarCmbDeptos();

    }

    public void cargarDatosTown() {
        //modelo de la jTable
        DefaultTableModel modelotabla = new DefaultTableModel();
        //asignar el modelo a la tabla de la vista
        this.town.jTblTipoCuenta.setModel(modelotabla);

        //asignar titulos a las columnas
        modelotabla.addColumn("Id");
        modelotabla.addColumn("Dept Name");
        modelotabla.addColumn("Town Name");

        //obtener la lista de tipos de cuenta
        int numRegistros = townDao.listaTownView().size();
        //object
        Object[] valorColumna = new Object[3];

        for (int i = 0; i < numRegistros; i++) {

            valorColumna[0] = townDao.listaTownView().get(i).getTownId();
            valorColumna[1] = townDao.listaTownView().get(i).getDeptName();
            valorColumna[2] = townDao.listaTownView().get(i).getTownName();
            //permite agregar filas
            modelotabla.addRow(valorColumna);
        }

    }

    public void llenarCmbDeptos() {
        DepartamentsDao daocompo = new DepartamentsDao();
        DefaultComboBoxModel dc = new DefaultComboBoxModel();

        listaDeptos = daocompo.listaDepartamentos();
        this.town.jCmbDept.setModel(dc);
        for (int i = 0; i < listaDeptos.size(); i++) {
            dc.addElement(listaDeptos.get(i).getDepartament_name());

        }

    }

    public void llenarCmbMunicipios() {

        TownDao municipios = new TownDao();
        DefaultComboBoxModel defaultDepartmen = new DefaultComboBoxModel();

        //buscar el codigo del departamento para listar los municipios
        int codigoDepto = 0;
        for (int i = 0; i < listaDeptos.size(); i++) {
            if (listaDeptos.get(i).getDepartament_name().equals(this.town.jCmbDept.getSelectedItem())) {
                codigoDepto = listaDeptos.get(i).getId_dept();

                this.town.jTxtDepto.setText(String.valueOf(codigoDepto));
            }
        }
        

    }

    

    public void guardarTown() {
        Town townPrincipal = new Town();
        townPrincipal.setTown_id(Integer.parseInt(town.jTxtTown.getText()));
        townPrincipal.setId_dep(Integer.parseInt(town.jTxtDepto.getText()));
        townPrincipal.setName(town.jTxtName.getText());
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
//Cambio git 1/2

    public void mouseClickedDeptos() {

        String nombredepto = this.town.jTblTipoCuenta.getValueAt(this.town.jTblTipoCuenta.getSelectedRow(), 1).toString();
        //seleccionar departamento
        for (int i = 0; i < listaDeptos.size(); i++) {
            this.town.jCmbDept.setSelectedItem(nombredepto);
        }

        //seleccionar el municipio
        

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

        if (e.getSource() == town.jBtnEliminar) {

            eliminarTown();
            cargarDatosTown();

        }

        if (e.getSource() == town.jBtnLimpiar) {
            limpiarTown();

        }
        if (e.getSource() == town.jCmbDept) {
            llenarCmbMunicipios();
        }
       

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == town.jTblTipoCuenta) {
            town.jTxtTown.setText(town.jTblTipoCuenta.getValueAt(town.jTblTipoCuenta.getSelectedRow(), 0).toString());

            mouseClickedDeptos();
                town.jTxtName.setText(town.jTblTipoCuenta.getValueAt(town.jTblTipoCuenta.getSelectedRow(), 2).toString());
            
        }

    }

}
