package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import controlador.Ejecutador;

import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class Ventana extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void CargarVentana(Ventana frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnciudades = new JMenu("Ciudades");
		menuBar.add(mnciudades);
		
		
		
		JMenuItem mntconsultar = new JMenuItem("consultarCiudades");
		mntconsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultarciudades c = new Consultarciudades();
				nuevoPanel(c);
			}
		});
		mnciudades.add(mntconsultar);
		
		JMenuItem mntinsertar = new JMenuItem("insertarCiudades");
		mntinsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarCiudades i = new InsertarCiudades();
				nuevoPanel(i);
			}
		});
		mnciudades.add(mntinsertar);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelprincipal = new JPanel();
		panelprincipal.setBackground(new Color(0, 128, 192));
		panelprincipal.setForeground(new Color(255, 255, 255));
		contentPane.add(panelprincipal, "name_616864820969800");
		panelprincipal.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BASE DE DATOS CIUDADES");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(154, 96, 322, 137);
		panelprincipal.add(lblNewLabel);

	}
	
	public void nuevoPanel(JPanel panelActual) {
		contentPane.removeAll();
		contentPane.add(panelActual);
		contentPane.repaint();
		contentPane.revalidate();
		
	}
}
