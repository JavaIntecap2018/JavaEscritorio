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
import modelo.Place_Type;
import modelo.Place_TypeDao;
import modelo.Tourist_PlaceDao;
import vista.JintFrmPlaceType;

/**
 *
 * @author sergio
 */
public class ControladorPlaceType extends MouseAdapter implements ActionListener {

    Place_TypeDao placeTypeDao = new Place_TypeDao();
    JintFrmPlaceType placeType = new JintFrmPlaceType();
    Place_Type PlaceTypePrincipal = new Place_Type();

    public ControladorPlaceType(JintFrmPlaceType vistaPlaceType, Place_TypeDao modeloPlaceTypeDao) {

        this.placeType = vistaPlaceType;
        this.placeTypeDao = modeloPlaceTypeDao;

        this.placeType.jBtnGuardar.addActionListener(this);
        this.placeType.jBtnActualizar.addActionListener(this);
        this.placeType.jBtnEliminar.addActionListener(this);
        this.placeType.jBtnLimpiar.addActionListener(this);

        this.placeType.jTblTipoCuenta.addMouseListener(this);

        cargarDatosPlaceType();

    }

    public void cargarDatosPlaceType() {
        //modelo de la jTable
        DefaultTableModel modelotabla = new DefaultTableModel();
        //asignar el modelo a la tabla de la vista
        this.placeType.jTblTipoCuenta.setModel(modelotabla);

        //asignar titulos a las columnas
        modelotabla.addColumn("placeType");
        modelotabla.addColumn("Name");

        ArrayList<Place_Type> lista = new ArrayList();
        //obtener la lista de tipos de cuenta
        lista = placeTypeDao.listaPlace_Type();
        //object
        Object[] valorColumna = new Object[3];

        for (int i = 0; i < lista.size(); i++) {
            valorColumna[0] = lista.get(i).getType_place_id();
            valorColumna[1] = lista.get(i).getName();

            //permite agregar filas
            modelotabla.addRow(valorColumna);
        }

    }

    public void guardarPlaceType() {
        Place_Type PlaceTypePrincipal = new Place_Type();
        PlaceTypePrincipal.setType_place_id(Integer.parseInt(placeType.jTxtPlace.getText()));
        PlaceTypePrincipal.setName(placeType.jTxtName.getText());
        String respuesta = placeTypeDao.registrarPlace_Type(PlaceTypePrincipal);
        if (respuesta != null) {

            JOptionPane.showMessageDialog(null, respuesta);

        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }

    }

    public void actualizarPlaceType() {
        Place_Type PlaceTypePrincipal = new Place_Type();
        PlaceTypePrincipal.setType_place_id(Integer.parseInt(placeType.jTxtPlace.getText()));
        PlaceTypePrincipal.setName(placeType.jTxtName.getText());
        String respuesta = placeTypeDao.daoModificarPlace_Type(PlaceTypePrincipal);
        if (respuesta != null) {

            JOptionPane.showMessageDialog(null, respuesta);

        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }

    }

    public void eliminarPlaceType() {
        Place_Type PlaceTypePrincipal = new Place_Type();
        PlaceTypePrincipal.setType_place_id(Integer.parseInt(placeType.jTxtPlace.getText()));
        String respuesta = placeTypeDao.eliminarPlace_Type(PlaceTypePrincipal.getType_place_id());
        if (respuesta != null) {

            JOptionPane.showMessageDialog(null, respuesta);

        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }
    }

    public void limpiarPlaceType() {
        placeType.jTxtPlace.setText(null);
        placeType.jTxtName.setText(null);
        placeType.jTxtPlace.requestFocus();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == placeType.jBtnGuardar) {

            guardarPlaceType();
            cargarDatosPlaceType();

        }

        if (e.getSource() == placeType.jBtnActualizar) {
            actualizarPlaceType();
            cargarDatosPlaceType();

        }
        if (e.getSource() == placeType.jBtnEliminar) {
            eliminarPlaceType();
            cargarDatosPlaceType();

        }
        if (e.getSource() == placeType.jBtnLimpiar) {
            limpiarPlaceType();

        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        placeType.jTxtPlace.setText(placeType.jTblTipoCuenta.getValueAt(placeType.jTblTipoCuenta.getSelectedRow(), 0).toString());
        placeType.jTxtName.setText(placeType.jTblTipoCuenta.getValueAt(placeType.jTblTipoCuenta.getSelectedRow(), 1).toString());

    }

}
