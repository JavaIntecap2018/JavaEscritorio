/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controlador.ControladorVistas;
import java.util.ArrayList;
import modelo.Town;
import modelo.TownDao;
import vista.Principal;

/**
 *
 * @author sergio
 */
public class ProyectoIntecap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Principal pr = new Principal();
        ControladorVistas vista = new ControladorVistas(pr);
        pr.setExtendedState(6);

        pr.setVisible(true);

        TownDao mi = new TownDao();

        ArrayList<Town> a = new ArrayList<>();

        a = mi.listaTown();

        for (Town town : a) {
            System.out.println(town.name);

        }

    }

}
