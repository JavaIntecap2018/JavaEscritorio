/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conexion;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.ActivityDao;
import modelo.DepartamentsDao;
import modelo.Place_TypeDao;
import modelo.Tourist_PlaceDao;
import modelo.TownDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.JinFrmActivityCalendar;
import vista.JintFrmActivity;
import vista.JintFrmDeptos;
import vista.JintFrmPlaceType;
import vista.JintFrmTouristPlace;
import vista.JintFrmTown;
import vista.Principal;

/**
 *
 * @author sergio
 */
public class ControladorVistas implements ActionListener {

    Principal pr = new Principal();

    public ControladorVistas(Principal vista) {
        System.out.println("hola desde el constructor");
        this.pr = vista;
        this.pr.mnuActivity.addActionListener(this);
        this.pr.mnuDeptos.addActionListener(this);
        //this.pr.mnuActivityCalendar.addActionListener(this);
        this.pr.jMenuItemDeptos.addActionListener(this);
        this.pr.mnuTown.addActionListener(this);
        this.pr.mnuPlace.addActionListener(this);
        this.pr.mnuTouristPlace.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("hola");
        if (e.getSource() == pr.jMenuItemDeptos) {

            reporteDeptos();

        }

        if (e.getSource() == pr.mnuDeptos) {
            crearDepto();

        }

        if (e.getSource() == pr.mnuActivity) {
            crearActivity();

        }

        if (e.getSource() == pr.mnuTown) {
            crearTown();

        }

        if (e.getSource() == pr.mnuPlace) {
            crearPlaceType();

        }

        if (e.getSource() == pr.mnuTouristPlace) {
            crearTouristPlace();

        }

//        if(e.getSource()==pr.mnuActivityCalendar){
//            crearActivityCalendar();
//            
//        }
    }

    public void reporteDeptos() {
        try {
            // TODO add your handling code here:

            Conexion con = new Conexion();
            con.Conectar();

            Connection conn = con.getMiconexion();

            JasperReport reporte = null;

            String path = "src/reportes/Departamentos.jasper";

            Map parametro = new HashMap();

            parametro.put("codigociudad", JOptionPane.showInputDialog("ingrese codigo de ciudad"));

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);

        } catch (JRException ex) {

        } catch (SQLException ex) {

        }

    }

    public void crearDepto() {

        JintFrmDeptos deptos = new JintFrmDeptos();

        this.pr.desktopPane.add(deptos);

        DepartamentsDao modelDepto = new DepartamentsDao();

        ControladorDeptos controlDepto = new ControladorDeptos(deptos, modelDepto);

        deptos.setSize(800, 600);

        deptos.setVisible(true);

    }

    public void crearTown() {
        JintFrmTown town = new JintFrmTown();
        this.pr.desktopPane.add(town);

        TownDao towndao = new TownDao();

        ControladorTown controlTown = new ControladorTown(town, towndao);

        town.setSize(800, 600);
        town.setVisible(true);

    }

    public void crearActivity() {
        JintFrmActivity activity = new JintFrmActivity();
        this.pr.desktopPane.add(activity);
        ActivityDao modeloActivity = new ActivityDao();

        ControladorActivity controlActivity = new ControladorActivity(activity, modeloActivity);

        activity.setSize(800, 600);
        activity.setVisible(true);
    }

    public void crearPlaceType() {

        JintFrmPlaceType placeType = new JintFrmPlaceType();

        this.pr.desktopPane.add(placeType);

        Place_TypeDao placeTypeDao = new Place_TypeDao();

        ControladorPlaceType controlPlace = new ControladorPlaceType(placeType, placeTypeDao);

        placeType.setSize(800, 600);
        placeType.setVisible(true);
    }

    public void crearTouristPlace() {
        
        JintFrmTouristPlace touristPlace=new JintFrmTouristPlace();
        this.pr.desktopPane.add(touristPlace);
        Tourist_PlaceDao touristPlaceDao = new Tourist_PlaceDao();
        
        ControladorTouristPlace controlTourisPlace= new ControladorTouristPlace(touristPlace, touristPlaceDao);
        
        
        
        touristPlace.setSize(800, 600);
        touristPlace.setVisible(true);

    }

//    public void crearActivityCalendar(){
//        JinFrmActivityCalendar activityCalendar = new JinFrmActivityCalendar();
//        this.pr.desktopPane.add(activityCalendar);
//        
//    //    ControladorActivityCalendar controlActivityCalendar = new ControladorActivityCalendar();
//        
//        
//        
//        activityCalendar.setSize(800,600);
//        activityCalendar.setVisible(true);
//        
//        
//       
//    }
}
