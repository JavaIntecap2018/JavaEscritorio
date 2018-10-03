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
import modelo.Tourist_Place;
import modelo.Tourist_PlaceDao;
import vista.JintFrmTouristPlace;

/**
 *
 * @author sergio
 */
public class ControladorTouristPlace extends MouseAdapter implements ActionListener {

    Tourist_PlaceDao touristPlaceDao = new Tourist_PlaceDao();
    JintFrmTouristPlace touristPlace = new JintFrmTouristPlace();
    Tourist_Place touristPlacePrincipal = new Tourist_Place();

    public ControladorTouristPlace(JintFrmTouristPlace touristPlaceVista, Tourist_PlaceDao touristPlaceDao) {

        this.touristPlaceDao = touristPlaceDao;
        this.touristPlace = touristPlaceVista;

        this.touristPlace.jBtnGuardar.addActionListener(this);
        this.touristPlace.jBtnActualizar.addActionListener(this);
        this.touristPlace.jBtnEliminar.addActionListener(this);
        this.touristPlace.jBtnLimpiar.addActionListener(this);

        this.touristPlace.jTblTipoCuenta.addMouseListener(this);

        cargarDatosTouristPlace();

    }

    public void cargarDatosTouristPlace() {
        //modelo de la jTable
        DefaultTableModel modelotabla = new DefaultTableModel();
        //asignar el modelo a la tabla de la vista
        this.touristPlace.jTblTipoCuenta.setModel(modelotabla);

        //asignar titulos a las columnas
        modelotabla.addColumn("TouristPlace");
        modelotabla.addColumn("IdTown");
        modelotabla.addColumn("Name");
        modelotabla.addColumn("PlaceTypeId");

        ArrayList<Tourist_Place> lista = new ArrayList();
        //obtener la lista de tipos de cuenta
        lista = touristPlaceDao.listaTourist_Place();
        //object
        Object[] valorColumna = new Object[4];

        for (int i = 0; i < lista.size(); i++) {
            valorColumna[0] = lista.get(i).getTourist_place_id();
            valorColumna[1] = lista.get(i).getId_dep();
            valorColumna[2] = lista.get(i).getName();
            valorColumna[3] = lista.get(i).getPlace_type_id();

            //permite agregar filas
            modelotabla.addRow(valorColumna);
        }

    }

    public void guardarTouristPlace() {
        Tourist_Place touristPlacePrincipal = new Tourist_Place();
        touristPlacePrincipal.setTourist_place_id(Integer.parseInt(touristPlace.jTxtTouristPlace.getText()));
        touristPlacePrincipal.setId_dep(Integer.parseInt(touristPlace.jTxtIdDepto.getText()));
        touristPlacePrincipal.setPlace_type_id(Integer.parseInt(touristPlace.jTxtPlace_Type_Id.getText()));
        touristPlacePrincipal.setName(touristPlace.jTxtName.getText());
        String respuesta = touristPlaceDao.registrarTourist_Place(touristPlacePrincipal);
        if (respuesta != null) {

            JOptionPane.showMessageDialog(null, respuesta);

        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }

    }

    public void actualizarTouristPlace() {
        Tourist_Place touristPlacePrincipal = new Tourist_Place();
        touristPlacePrincipal.setTourist_place_id(Integer.parseInt(touristPlace.jTxtTouristPlace.getText()));
        touristPlacePrincipal.setId_dep(Integer.parseInt(touristPlace.jTxtIdDepto.getText()));
        touristPlacePrincipal.setPlace_type_id(Integer.parseInt(touristPlace.jTxtPlace_Type_Id.getText()));
        touristPlacePrincipal.setName(touristPlace.jTxtName.getText());
        String respuesta = touristPlaceDao.daoModificarTourist_Place(touristPlacePrincipal);
        if (respuesta != null) {

            JOptionPane.showMessageDialog(null, respuesta);

        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }

    }

    public void eliminarTouristPlace() {
        Tourist_Place touristPlacePrincipal = new Tourist_Place();
        touristPlacePrincipal.setTourist_place_id(Integer.parseInt(touristPlace.jTxtTouristPlace.getText()));

        String respuesta = touristPlaceDao.eliminarTourist_Place(touristPlacePrincipal.getTourist_place_id());
        if (respuesta != null) {

            JOptionPane.showMessageDialog(null, respuesta);

        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }
    }

    public void limpiartTouristPlace() {
        touristPlace.jTxtTouristPlace.setText(null);
        touristPlace.jTxtName.setText(null);
        touristPlace.jTxtIdDepto.setText(null);
        touristPlace.jTxtPlace_Type_Id.setText(null);
        touristPlace.jTxtTouristPlace.requestFocus();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == touristPlace.jBtnGuardar) {

            guardarTouristPlace();
            cargarDatosTouristPlace();

        }
        if (e.getSource() == touristPlace.jBtnActualizar) {
            actualizarTouristPlace();
            cargarDatosTouristPlace();

        }
        if (e.getSource() == touristPlace.jBtnEliminar) {
            eliminarTouristPlace();
            cargarDatosTouristPlace();

        }
        if (e.getSource() == touristPlace.jBtnLimpiar) {
            limpiartTouristPlace();

        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        touristPlace.jTxtPlace_Type_Id.setText(touristPlace.jTblTipoCuenta.getValueAt(touristPlace.jTblTipoCuenta.getSelectedRow(), 3).toString());
        touristPlace.jTxtIdDepto.setText(touristPlace.jTblTipoCuenta.getValueAt(touristPlace.jTblTipoCuenta.getSelectedRow(), 1).toString());
        touristPlace.jTxtName.setText(touristPlace.jTblTipoCuenta.getValueAt(touristPlace.jTblTipoCuenta.getSelectedRow(), 2).toString());
        touristPlace.jTxtTouristPlace.setText(touristPlace.jTblTipoCuenta.getValueAt(touristPlace.jTblTipoCuenta.getSelectedRow(), 0).toString());

    }

}
