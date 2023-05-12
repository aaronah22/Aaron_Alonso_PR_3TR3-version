package vista;

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
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
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


public class Consultarciudades extends JPanel {

	private JTable tblciudades;
	private JTextField txfnombre;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	private final JSlider slider = new JSlider();
	private JButton btnconsultar;
	private JButton btneliminar;
	private JButton btnconsultar_1;
	private JButton btneliminar_1;
	private JComboBox cmbpais1_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	Basededatos bd = new Basededatos();
	private JTextField txfNuevonombre;
	
	public Consultarciudades() {
		
		ImageIcon imagenFondo = new ImageIcon("Downloads/Mapamundo.jpg");
		
		setLayout(null);
		
		 setSize(1020,740);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(184, 92, 603, 226);
		add(scrollPane);
		
		tblciudades = new JTable();
		tblciudades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = (String) modeloTabla.getValueAt(tblciudades.getSelectedRow(),0);
				String countrycode = (String) modeloTabla.getValueAt(tblciudades.getSelectedRow(),1);
				String district= (String) modeloTabla.getValueAt(tblciudades.getSelectedRow(),2);
				String population = modeloTabla.getValueAt(tblciudades.getSelectedRow(),3).toString();
				String continente = (String) modeloTabla.getValueAt(tblciudades.getSelectedRow(),4);
				String idioma = (String) modeloTabla.getValueAt(tblciudades.getSelectedRow(),5);
			}
		});
		scrollPane.setViewportView(tblciudades);
		
		modeloTabla.setColumnIdentifiers(new Object[] {"Ciudad","Pais", "Distrito", "Poblacion" , "Continente", "Idioma"});
		tblciudades.setModel(modeloTabla);
		
		JRadioButton rdbnombre = new JRadioButton("Nombre");
		buttonGroup.add(rdbnombre);
		rdbnombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbnombre.setEnabled(true);
			}
		});
		
		txfNuevonombre = new JTextField();
		JSlider sldNuevapoblacion = new JSlider(0, 2000000, 0);
		sldNuevapoblacion.setFont(new Font("Tahoma", Font.PLAIN, 6));
		JComboBox cmbNuevodistrito = new JComboBox();
		rdbnombre.setBounds(190, 25, 109, 23);
		add(rdbnombre);
		
		JRadioButton rdbpais = new JRadioButton("Pais");
		buttonGroup.add(rdbpais);
		rdbpais.setBounds(456, 25, 109, 23);
		add(rdbpais);
		
		JRadioButton rdbdistrito = new JRadioButton("Distrito");
		buttonGroup.add(rdbdistrito);
		rdbdistrito.setBounds(703, 25, 109, 23);
		add(rdbdistrito);
		
		JRadioButton rdbpoblacion = new JRadioButton("Poblacion");
		buttonGroup.add(rdbpoblacion);
		rdbpoblacion.setBounds(391, 401, 102, 23);
		add(rdbpoblacion);
		
		
		txfnombre = new JTextField();
		txfnombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(rdbnombre.isSelected()) {
				Basededatos bd = new Basededatos();
				modeloTabla.setRowCount(0);
				for(Ciudades c: bd.consultarnombre(txfnombre.getText())) {
					modeloTabla.addRow(new Object[] {
							c.getCity(),
							c.getCountry(),
							c.getDistrict(),
							c.getPopulation(),
							c.getContinente(),
							c.getIdioma()
					});
				}
			}
				
			}
			});
	
		txfnombre.setBounds(192, 55, 140, 24);
		add(txfnombre);
		txfnombre.setColumns(10);
		JComboBox cmbdistrito = new JComboBox();
		ArrayList<Ciudades> arrldistrito = bd.consultadistrito();
		
		for (int i = 0; i < arrldistrito.size(); i++) {
			cmbdistrito.addItem(arrldistrito.get(i).getDistrict());
		}
		
		cmbdistrito.setSelectedIndex(-1);
		
		add(cmbdistrito);
		cmbdistrito.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			String distrito = cmbdistrito.getSelectedItem().toString();
			if(rdbdistrito.isSelected()) {
				modeloTabla.setRowCount(0);
				for(Ciudades c: bd.consultardistritos(distrito)) {
					modeloTabla.addRow(new Object[] {
							c.getCity(),
							c.getCountry(),
							c.getDistrict(),
							c.getPopulation(),
							c.getContinente(),
							c.getIdioma()
					});
				}
			}
				
			}
			});
	
	
		cmbdistrito.setBounds(711, 53, 118, 28);
		add(cmbdistrito);
		
		JSlider sldpoblacion = new JSlider(0,2000000,0);
		sldpoblacion.setFont(new Font("Tahoma", Font.PLAIN, 6));
		ArrayList<Ciudades> arrlpoblacion = bd.consultapoblacion();
		sldpoblacion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int poblacion = sldpoblacion.getValue();
				if(rdbpoblacion.isSelected()) {
					modeloTabla.setRowCount(0);
					for(Ciudades c: bd.consultarpoblaciones(poblacion)) {
						modeloTabla.addRow(new Object[] {
								c.getCity(),
								c.getCountry(),
								c.getDistrict(),
								c.getPopulation(),
								c.getContinente(),
								c.getIdioma()
						});
					}
				}
			
			}
		});

		sldpoblacion.setMajorTickSpacing(50000);
        sldpoblacion.setMinorTickSpacing(50000);
        sldpoblacion.setPaintTicks(true);
        sldpoblacion.setPaintLabels(true);
		sldpoblacion.setBounds(0, 421, 990, 56);
		add(sldpoblacion);
		
		cmbpais1_1 = new JComboBox();
		cmbpais1_1.setBounds(456, 53, 118, 28);
		ArrayList<Paises> arrlPaises = bd.consultapais();
		
		for (int i = 0; i < arrlPaises.size(); i++) {
			cmbpais1_1.addItem(arrlPaises.get(i).getName());
		}
		
		cmbpais1_1.setSelectedIndex(-1);
		
		add(cmbpais1_1);
		cmbpais1_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String pais = cmbpais1_1.getSelectedItem().toString();
				
				if(rdbpais.isSelected()) {
					Basededatos bd = new Basededatos();
					modeloTabla.setRowCount(0);
					for(Ciudades c: bd.consultarpaises(pais)) {
						modeloTabla.addRow(new Object[] {
								c.getCity(),
								c.getCountry(),
								c.getDistrict(),
								c.getPopulation(),
								c.getContinente(),
								c.getIdioma()
						});
					}
				}
				
			}
		});
		
		
		
		
		btnconsultar_1 = new JButton("Modificar");
		btnconsultar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int fila = tblciudades.getSelectedRow();
				
				String ciudad = (String) tblciudades.getValueAt(fila, 0);
				String pais = (String) tblciudades.getValueAt(fila, 1);
				String distrito = (String) tblciudades.getValueAt(fila, 2);
				int poblacion = (int)(tblciudades.getValueAt(fila, 3));
				String continente = (String) tblciudades.getValueAt(fila, 4);
				String idioma = (String) tblciudades.getValueAt(fila, 5);
				
				
				JLabel label = new JLabel("¿Estas seguro de que desear modificar?");
				int confirmar = JOptionPane.showOptionDialog(null, label, "Confirmar acción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sí", "No" }, "Sí");
				
				Basededatos bd = new Basededatos();
				String nuevonombre = txfNuevonombre.getText(); 
				int nuevopoblacion = sldNuevapoblacion.getValue();
				String nuevodistrito = cmbNuevodistrito.getSelectedItem().toString();
				
				bd.modificarciudad(ciudad, poblacion, distrito, nuevonombre , nuevopoblacion, nuevodistrito);
				
				if (confirmar == JOptionPane.YES_OPTION) {
				   modeloTabla.setValueAt(nuevonombre, fila, 0);
				   modeloTabla.setValueAt(nuevopoblacion, fila, 3);
				   modeloTabla.setValueAt(nuevodistrito, fila, 2);
				   JOptionPane.showMessageDialog(null, "Articulo  modificado correctamente");
				} else {
				   
				}
			}
		});
	        
		btnconsultar_1.setBounds(243, 357, 89, 23);
		add(btnconsultar_1);
		
		btneliminar_1 = new JButton("Eliminar");
		btneliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tblciudades.getSelectedRow();
				
				String ciudad = tblciudades.getValueAt(fila, 0).toString();
				String pais = tblciudades.getValueAt(fila, 1).toString();
				String distrito = tblciudades.getValueAt(fila, 2).toString();
				int poblacion = (int) tblciudades.getValueAt(fila, 3);
				String continente = tblciudades.getValueAt(fila, 4).toString();
				String idioma = tblciudades.getValueAt(fila, 5).toString();
				
				JLabel label = new JLabel("¿Estas seguro de que desear eliminar?");
				int confirmar = JOptionPane.showOptionDialog(null, label, "Confirmar acción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sí", "No" }, "Sí");
				
				Basededatos bd = new Basededatos();
				bd.borrarciudad(ciudad, poblacion, distrito);
				
				if (confirmar == JOptionPane.YES_OPTION) {
				   modeloTabla.removeRow(fila);
				   JOptionPane.showMessageDialog(null, "Articulo borrado correctamente");
				} else {
				   
				}
			}
		});
		
		btneliminar_1.setBounds(590, 357, 89, 23);
		add(btneliminar_1);
		
		JToolBar jtbexportar = new JToolBar();
		jtbexportar.setToolTipText("excel");
		jtbexportar.setBounds(21, 2, 129, 28);
		add(jtbexportar);
		
		JButton option1 = new JButton("XML");
		option1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarxml(arrlCiudades2);
//				Fichero f = new Fichero;
//				ArrayList<Ciudades> arrCiudades2 = new ArrayList<>();
//				for (i=0; i < tblciudades.getRowCount(); i++) {
//					Ciudades fila = new Ciudades{
//						(String) modeloTabla.getValueAt(i, 0);
//						(String) modeloTabla.getValueAt(i, 1);
//						(String) modeloTabla.getValueAt(i, 2);
//						(Int) modeloTabla.getValueAt(i, 3);
//						(String) modeloTabla.getValueAt(i, 4);
//						(String) modeloTabla.getValueAt(i, 5);
//					}
//				}
			}
		});
		JButton option2 = new JButton("SQL");
		option2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Fichero f = new Fichero;
//				ArrayList<Ciudades> arrCiudades2 = new ArrayList<>();
//				for (i=0; i < tblciudades.getRowCount(); i++) {
//					Ciudades fila = new Ciudades{
//						(String) modeloTabla.getValueAt(i, 0);
//						(String) modeloTabla.getValueAt(i, 1);
//						(String) modeloTabla.getValueAt(i, 2);
//						(Int) modeloTabla.getValueAt(i, 3);
//						(String) modeloTabla.getValueAt(i, 4);
//						(String) modeloTabla.getValueAt(i, 5);
//					}
//				}
			}
		});
		JButton option3 = new JButton("XLS");
		option3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exportar obj;

		        try {
		            obj = new Exportar();
		            obj.exportarExcel(tblciudades);
		        } catch (IOException ex) {
		            System.out.println("Error: " + ex);
		        }
			}
		});
		
		jtbexportar.add(option1);
		jtbexportar.add(option2);
		jtbexportar.add(option3);

		
		JLabel lblNuevonombre = new JLabel("Nuevo nombre:");
		lblNuevonombre.setBounds(102, 505, 89, 14);
		add(lblNuevonombre);
		
		txfNuevonombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txfNuevonombre.getText();
			}
		});
		
		txfNuevonombre.setBounds(102, 530, 97, 34);
		add(txfNuevonombre);
		txfNuevonombre.setColumns(10);
		
		JLabel lblNuevapoblacion = new JLabel("Nueva poblacion:");
		lblNuevapoblacion.setBounds(385, 581, 108, 14);
		add(lblNuevapoblacion);
		
		
		arrlpoblacion = bd.consultapoblacion();
		sldNuevapoblacion.setPaintTicks(true);
		sldNuevapoblacion.setPaintLabels(true);
		sldNuevapoblacion.setMinorTickSpacing(50000);
		sldNuevapoblacion.setMajorTickSpacing(50000);
		sldNuevapoblacion.setBounds(0, 606, 990, 42);
		add(sldNuevapoblacion);
		
		JLabel lblNuevodistrito = new JLabel("Nuevo distrito:");
		lblNuevodistrito.setBounds(711, 530, 89, 14);
		add(lblNuevodistrito);
		
		arrldistrito = bd.consultadistrito();
		
		for (int i = 0; i < arrldistrito.size(); i++) {
			cmbNuevodistrito.addItem(arrldistrito.get(i).getDistrict());
		}
		
		cmbdistrito.setSelectedIndex(-1);

		cmbNuevodistrito.setBounds(715, 555, 97, 23);
		add(cmbNuevodistrito);
	}		                
}