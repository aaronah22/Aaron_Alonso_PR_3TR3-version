/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import java.awt.event.*;

import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import controlador.Basededatos;
import controlador.Ejecutador;
import controlador.Exportar;
import modelo.Ciudades;
import modelo.Paises;
import controlador.Exportar;

import java.awt.event.ItemListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;

import java.io.FileWriter;
import javax.swing.JTable;

/**
 *
 * @author AdminOmarGuevara
 */
public class Exportar {

    public void exportarExcel(JTable tblciudades) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("Mi hoja de trabajo 1");
                hoja.setDisplayGridlines(false);
                for (int f = 0; f < tblciudades.getRowCount(); f++) {
                    Row fila = hoja.createRow(f);
                    for (int c = 0; c < tblciudades.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (f == 0) {
                            celda.setCellValue(tblciudades.getColumnName(c));
                        }
                    }
                }
                int filaInicio = 1;
                for (int f = 0; f < tblciudades.getRowCount(); f++) {
                    Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    for (int c = 0; c < tblciudades.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (tblciudades.getValueAt(f, c) instanceof Double) {
                            celda.setCellValue(Double.parseDouble(tblciudades.getValueAt(f, c).toString()));
                        } else if (tblciudades.getValueAt(f, c) instanceof Float) {
                            celda.setCellValue(Float.parseFloat((String) tblciudades.getValueAt(f, c)));
                        } else {
                            celda.setCellValue(String.valueOf(tblciudades.getValueAt(f, c)));
                        }
                    }
                }
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }
    
	public static void exportarxml(ArrayList<Ciudades> arrlCiudades2) throws IOException {
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de xml", "xml");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xml");
            try {
                File archivoXML = new File(ruta);
                if (archivoXML.exists()) {
                    archivoXML.delete();
                }
                archivoXML.createNewFile();
                Desktop.getDesktop().open(archivoXML);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
			
			PrintWriter pw = null;
			
			
			String archivo = null;
			pw = new PrintWriter(new FileWriter(archivo, true));
			for(int i = 0;i>arrlCiudades2.size();i++) {
				pw.println("<ciudad>");
				pw.print(arrlCiudades2.get(i).getCity());
				pw.println("</ciudad>");
				pw.println("<pais>");
				pw.print(arrlCiudades2.get(i).getCountry());
				pw.println("</pais>");
				pw.println("<distrito>");
				pw.print(arrlCiudades2.get(i).getDistrict());
				pw.println("</distrito>");
				pw.println("<poblacion>");
				pw.print(arrlCiudades2.get(i).getPopulation());
				pw.println("</poblacion>");
				pw.println("<continente>");
				pw.print(arrlCiudades2.get(i).getContinente());
				pw.println("</continente>");
				pw.println("<lenguaje>");
				pw.print(arrlCiudades2.get(i).getIdioma());
				pw.println("</lenguaje>");
			}
           
    }
	
            
		
	

//	public static void tableToSql(ArrayList<Ciudades> arrlCiudades2) throws IOException{
//		File Fichero = new File("archivo.sql");
//		PrintWriter pw = null;
//		
//		String archivo = null;
//		pw = new PrintWriter(new FileWriter(archivo, true));
//		pw.println("<ciudad>");
//		pw.println("<pais>");
//		pw.println("<distrito>");
//		pw.println("<poblacion>");
//		pw.println("<continente>");
//		pw.println("<lenguaje>");
//	}

}
