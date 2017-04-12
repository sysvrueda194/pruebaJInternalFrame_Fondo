/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import vista.*;

/**
 *
 * @author USER
 */
public class codigo {

    
    public String DireccionArchivo;
    private final JFileChooser FileChooser = new JFileChooser();
    JIF_InformacionEmpresas IE=new JIF_InformacionEmpresas();
    

    //>>>>>>>>>>>>>>>>>>>>>>>>>>> JIF_Entrada_Informacion_Excel.java <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public File cargaArchivo() {
        //JIF_Entrada_Informacion_Excel EIE = new JIF_Entrada_Informacion_Excel();
        FileChooser.showDialog(null, "Importar Datos ");
        File file = FileChooser.getSelectedFile();
        DireccionArchivo = file.getAbsolutePath();
        char c1 = (char) 92;
        char c2 = (char) 47;
        System.out.println("char c1 = " + c1 + ", char c2 = " + c2);
        DireccionArchivo = DireccionArchivo.replace(c1, c2);
        System.out.println("Direccion Archivo Seleccionado = " + DireccionArchivo);
        return file;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>> JIF_InformacionEmpresas.java <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public DefaultTableModel cargatablaEmpresa() {
        File info_empresas = new File("src/archivos/EMPRESAS GETSAS.txt");
        System.out.println("el archivo se puede leer: " + info_empresas.canRead());

        DefaultTableModel modelo_tablaEmpresa = new DefaultTableModel();
        FileReader fr = null;
        BufferedReader br;
        String linea;
        try {
            fr = new FileReader(info_empresas);
            br = new BufferedReader(fr);
            String inicio = br.readLine();
            String[] columnas = inicio.split(";");
            for (String columna1 : columnas) {
                modelo_tablaEmpresa.addColumn(columna1);
            }
            while ((linea = br.readLine()) != null) {
                String[] data = linea.split(";");
                modelo_tablaEmpresa.addRow(data);
                System.out.println(linea);
            }
            JTable tablaEmpresas = new JTable();
            tablaEmpresas.setModel(modelo_tablaEmpresa);
            //IE.setJT_informacionEmpresas(tablaEmpresas);
            //IE.getJT_informacionEmpresas().setModel(modelo_tablaEmpresa);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return modelo_tablaEmpresa;
    }

}
