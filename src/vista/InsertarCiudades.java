package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import modelo.Ciudades;
import modelo.Paises;
import controlador.Basededatos;
import vista.Consultarciudades;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertarCiudades extends JPanel {
	private JTextField txfnombre;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	Basededatos bd = new Basededatos();
	private JSpinner spnpoblacion;

	/**
	 * Create the panel.
	 */
	public InsertarCiudades() {
		setLayout(null);
		
		JLabel lblInsertar = new JLabel("INSERTAR");
		lblInsertar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInsertar.setBounds(160, 21, 196, 40);
		add(lblInsertar);
		
		JLabel lblciudad = new JLabel("Ciudad:");
		lblciudad.setBounds(79, 96, 46, 14);
		add(lblciudad);
		
		txfnombre = new JTextField();
		txfnombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txfnombre.getText();
			}
		});
		txfnombre.setBounds(142, 90, 161, 26);
		add(txfnombre);
		txfnombre.setColumns(10);
		
		JLabel lblpais = new JLabel("Pais:");
		lblpais.setBounds(79, 129, 46, 14);
		add(lblpais);
		
		JLabel lbldistrito = new JLabel("Distrito:");
		lbldistrito.setBounds(79, 167, 46, 14);
		add(lbldistrito);
		
		JLabel lblpoblacion = new JLabel("Población:");
		lblpoblacion.setBounds(79, 207, 57, 14);
		add(lblpoblacion);
		
		JComboBox cmbpais = new JComboBox();
		cmbpais.setBounds(142, 127, 161, 26);
		ArrayList<Paises> arrlPaises = bd.consultapais();
		
		for (int i = 0; i < arrlPaises.size(); i++) {
			cmbpais.addItem(arrlPaises.get(i).getName());
		}
		
		cmbpais.setSelectedIndex(-1);
		add(cmbpais);
		
		JComboBox cmbdistrito = new JComboBox();
		ArrayList<Ciudades> arrldistritos1 = bd.consultadistritosi(getName());
		
		for (int i = 0; i < arrldistritos1.size(); i++) {
			cmbdistrito.addItem(arrldistritos1.get(i).getDistrict());
		}
		
		cmbdistrito.setSelectedIndex(-1);
		cmbdistrito.setBounds(142, 163, 161, 26);
		add(cmbdistrito);
		
		JButton btninsertar = new JButton("Insertar");
		btninsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = 0;
				JLabel label = new JLabel("¿Estas seguro de que desear insertar?");
				int confirmar = JOptionPane.showOptionDialog(null, label, "Confirmar acción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sí", "No" }, "Sí");
				
				Basededatos bd = new Basededatos();
				String nuevonombre = txfnombre.getText().toString(); 
				String nuevopais = cmbpais.getSelectedItem().toString();
				int nuevopoblacion = Integer.valueOf(spnpoblacion.getValue().toString());
				String nuevodistrito = cmbdistrito.getSelectedItem().toString();
				
				bd.insertarciudad(nuevonombre, nuevopais , nuevopoblacion, nuevodistrito);
			}
		});
		btninsertar.setBounds(160, 248, 89, 23);
		add(btninsertar);
		
		spnpoblacion = new JSpinner();
		spnpoblacion.setBounds(142, 204, 161, 23);
		add(spnpoblacion);
		
	}
}
