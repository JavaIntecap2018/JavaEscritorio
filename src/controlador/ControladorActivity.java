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
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Activity;
import modelo.ActivityDao;
import modelo.Departaments;
import modelo.DepartamentsDao;
import modelo.Tipo;
import modelo.TipoDao;
import modelo.Town;
import modelo.TownDao;
import vista.JintFrmActivity;

/**
 *
 * @author sergio
 */
public class ControladorActivity extends MouseAdapter implements ActionListener {

    JintFrmActivity vistaActivity = new JintFrmActivity();
    ActivityDao modeloActivityDao = new ActivityDao();
    Activity activity = new Activity();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    //LISTA TIPOS
    ArrayList<Tipo> listaTipos = new ArrayList<>();

    //LISTA DEPARTAMENTOS
    ArrayList<Departaments> listaDeptos = new ArrayList<>(); //datos departamentos
    //LISTA MUNICIPIOS
    ArrayList<Town> listaMunicipios = new ArrayList<Town>();

    public ControladorActivity(JintFrmActivity vistaActivity, ActivityDao modeloActivity) {
        this.vistaActivity = vistaActivity;
        this.modeloActivityDao = modeloActivity;
        this.vistaActivity.jBtnGuardar.addActionListener(this);
        this.vistaActivity.jBtnMod.addActionListener(this);
        this.vistaActivity.jBtnEliminar.addActionListener(this);
        this.vistaActivity.jBtnLimpiar.addActionListener(this);
        this.vistaActivity.jCmbDeptos.addActionListener(this);

        this.vistaActivity.jCmbMunicipios.addActionListener(this);

        this.vistaActivity.jTableActivity.addMouseListener(this);
        this.vistaActivity.jCmbTypes.addActionListener(this);
        this.vistaActivity.jTxtIdTownId.setEnabled(false);
        this.vistaActivity.jTxtDeparmentId.setEnabled(false);
        this.vistaActivity.jTxtTypeId.setEnabled(false);

        LlenarTablaActivityView();

        // LlenarTablaActivity(vistaActivity.jTableActivity);
        llenarCmbDeptos();

        // llenarCmbMunicipios();
        llenarCmbTypes();
    }

    public void LlenarTablaActivity(JTable tablaDatos) {

        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaDatos.setModel(modeloTabla);

        modeloTabla.addColumn("Id actividad");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Id municipio");
        // modeloTabla.addColumn("Id Depto");
        modeloTabla.addColumn("Tipo de actividad");

        Object[] columna = new Object[4];

        int numRegistros = modeloActivityDao.listaActivity().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloActivityDao.listaActivity().get(i).getActivityID();
            columna[1] = modeloActivityDao.listaActivity().get(i).getName();
            columna[2] = modeloActivityDao.listaActivity().get(i).getTownID();
            // columna[3] = modeloActivityDao.listaActivity().get(i).getId_dep();
            columna[3] = modeloActivityDao.listaActivity().get(i).getType_id();

            modeloTabla.addRow(columna);

        }

    }

    public void LlenarTablaActivityView() {

        modeloTabla = new DefaultTableModel();
        this.vistaActivity.jTableActivity.setModel(modeloTabla);

        modeloTabla.addColumn("Id actividad");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Departamento");
        modeloTabla.addColumn("Municipio");
        // modeloTabla.addColumn("Id Depto");
        modeloTabla.addColumn("Tipo Actividad");
        modeloTabla.addColumn("Actividad");

        Object[] columna = new Object[6];

        int numRegistros = modeloActivityDao.listaActivityView().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloActivityDao.listaActivityView().get(i).getActivity_id();
            columna[1] = modeloActivityDao.listaActivityView().get(i).getActivity_name();
            columna[2] = modeloActivityDao.listaActivityView().get(i).getDeparament_name();

            columna[3] = modeloActivityDao.listaActivityView().get(i).getTown_name();
            // columna[3] = modeloActivityDao.listaActivity().get(i).getId_dep();
            columna[4]=modeloActivityDao.listaActivityView().get(i).getType_name();
            columna[5] = modeloActivityDao.listaActivity().get(i).getName();

            modeloTabla.addRow(columna);

        }

    }

    public void llenarCmbDeptos() {
        DepartamentsDao daocompo = new DepartamentsDao();
        DefaultComboBoxModel dc = new DefaultComboBoxModel();

        listaDeptos = daocompo.listaDepartamentos();
        this.vistaActivity.jCmbDeptos.setModel(dc);
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
            if (listaDeptos.get(i).getDepartament_name().equals(this.vistaActivity.jCmbDeptos.getSelectedItem())) {
                codigoDepto = listaDeptos.get(i).getId_dept();

                this.vistaActivity.jTxtDeparmentId.setText(String.valueOf(codigoDepto));
            }
        }
        listaMunicipios = municipios.listaTownDep(codigoDepto);

        this.vistaActivity.jCmbMunicipios.setModel(defaultDepartmen);
        for (int i = 0; i < listaMunicipios.size(); i++) {
            defaultDepartmen.addElement(listaMunicipios.get(i).getName());

        }

    }
    
   
    public void asignarDatosCmbTipos(){
    TipoDao typesDao = new TipoDao();

        DefaultComboBoxModel defaultTypes = new DefaultComboBoxModel();

        int codigoType = 0;

        for (int i = 0; i < listaTipos.size(); i++) {
            if (listaTipos.get(i).getNombreTipo().equals(this.vistaActivity.jCmbTypes.getSelectedItem())) {

                codigoType = listaTipos.get(i).getCodigoTipo();
                this.vistaActivity.jTxtTypeId.setText(String.valueOf(codigoType));

            }
        }
    
    }

    public void llenarCmbTypes() {
        
//
//        listaTipos = typesDao.listaTipoMostrarTabla(codigoType);
//        this.vistaActivity.jCmbTypes.setModel(defaultTypes);
//
//        for (int i = 0; i < listaTipos.size(); i++) {
//            defaultTypes.addElement(listaTipos.get(i).getNombreTipo());
//
//        }
TipoDao typesDao = new TipoDao();

        DefaultComboBoxModel defaultTypes = new DefaultComboBoxModel();

     listaTipos= typesDao.listaTipoMostrar();
     this.vistaActivity.jCmbTypes.setModel(defaultTypes);
        for (int i = 0; i < listaTipos.size(); i++) {
            defaultTypes.addElement(listaTipos.get(i).getNombreTipo());
            
        }

    }

    public void seleccionarIDtown() {
        for (int i = 0; i < listaMunicipios.size(); i++) {
            if (listaMunicipios.get(i).name.equals(vistaActivity.jCmbMunicipios.getSelectedItem())) {
                int codigo = listaMunicipios.get(i).town_id;

                vistaActivity.jTxtIdTownId.setText(String.valueOf(codigo));

            }

        }
    }

    public void insertActivity() {
        activity.setActivityID(Integer.parseInt(vistaActivity.jTxtIdActivity.getText()));
        activity.setName(vistaActivity.jTxtNameActivity.getText());
        activity.setTownID(Integer.parseInt(vistaActivity.jTxtIdTownId.getText()));
//        activity.setId_dep(Integer.parseInt(vistaActivity.jTxtDeparmentId.getText()));
        activity.setType_id(Integer.parseInt(vistaActivity.jTxtTypeId.getText()));

        String respuestaInsert = this.modeloActivityDao.registrarActivity(activity);

        if (respuestaInsert != null) {

            JOptionPane.showMessageDialog(null, respuestaInsert);

        } else {
            JOptionPane.showMessageDialog(null, respuestaInsert);
        }

    }

    public void modActivity() {
        activity.setActivityID(Integer.parseInt(vistaActivity.jTxtIdActivity.getText()));
        activity.setName(vistaActivity.jTxtNameActivity.getText());
        activity.setTownID(Integer.parseInt(vistaActivity.jTxtIdTownId.getText()));
//        activity.setId_dep(Integer.parseInt(vistaActivity.jTxtDeparmentId.getText()));
        activity.setType_id(Integer.parseInt(vistaActivity.jTxtTypeId.getText()));

        String mensaje = modeloActivityDao.daoModificarActivity(activity);
        JOptionPane.showMessageDialog(vistaActivity, mensaje);
    }

    public void deleteActivity() {
        activity.setActivityID(Integer.parseInt(vistaActivity.jTxtIdActivity.getText()));
        String mensaje = modeloActivityDao.eliminarActivity(activity.getActivityID());
        JOptionPane.showMessageDialog(vistaActivity, mensaje);
        clean();
    }

    public void clean() {

        vistaActivity.jTxtIdActivity.setText(null);
        vistaActivity.jTxtNameActivity.setText(null);
        vistaActivity.jTxtIdTownId.setText(null);
        vistaActivity.jTxtDeparmentId.setText(null);
        vistaActivity.jTxtTypeId.setText(null);

        vistaActivity.jTxtIdActivity.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaActivity.jBtnGuardar) {
            insertActivity();
            LlenarTablaActivityView();

        }

        if (e.getSource() == vistaActivity.jBtnLimpiar) {
            clean();

        }

        if (e.getSource() == vistaActivity.jBtnMod) {
            modActivity();
            LlenarTablaActivityView();

        }

        if (e.getSource() == vistaActivity.jBtnEliminar) {

            deleteActivity();
            LlenarTablaActivityView();
        }

        if (e.getSource() == vistaActivity.jCmbDeptos) {
            llenarCmbMunicipios();

        }

        if (e.getSource() == vistaActivity.jCmbMunicipios) {

            seleccionarIDtown();

        }
        
        if(e.getSource()==vistaActivity.jCmbTypes){
            asignarDatosCmbTipos();
            
        }

    }

    public void deptosymunicipios() {
        String nombredepto = this.vistaActivity.jTableActivity.getValueAt(this.vistaActivity.jTableActivity.getSelectedRow(), 2).toString();
        //seleccionar departamento
        for (int i = 0; i < listaDeptos.size(); i++) {
            this.vistaActivity.jCmbDeptos.setSelectedItem(nombredepto);
        }

        //seleccionar el municipio
        String nombremuni = this.vistaActivity.jTableActivity.getValueAt(this.vistaActivity.jTableActivity.getSelectedRow(), 3).toString();
        for (int i = 0; i < listaMunicipios.size(); i++) {
            this.vistaActivity.jCmbMunicipios.setSelectedItem(nombremuni);
        }
        
        //seleccionar tipo
        String nombreTipo = this.vistaActivity.jTableActivity.getValueAt(this.vistaActivity.jTableActivity.getSelectedRow(), 4).toString();
        
        for (int i = 0; i < listaTipos.size(); i++) {
            this.vistaActivity.jCmbTypes.setSelectedItem(nombreTipo);
            
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vistaActivity.jTableActivity) {

            vistaActivity.jTxtIdActivity.setText(vistaActivity.jTableActivity.getValueAt(vistaActivity.jTableActivity.getSelectedRow(), 0).toString());
            vistaActivity.jTxtNameActivity.setText(vistaActivity.jTableActivity.getValueAt(vistaActivity.jTableActivity.getSelectedRow(), 1).toString());
            // vistaActivity.jTxtIdTownId.setText(vistaActivity.jTableActivity.getValueAt(vistaActivity.jTableActivity.getSelectedRow(), 2).toString());
            //vistaActivity.jTxtDeparmentId.setText(vistaActivity.jTableActivity.getValueAt(vistaActivity.jTableActivity.getSelectedRow(), 3).toString());
             //vistaActivity.jTxtTypeId.setText(vistaActivity.jTableActivity.getValueAt(vistaActivity.jTableActivity.getSelectedRow(), 3).toString());

            deptosymunicipios();

        }

    }
}
