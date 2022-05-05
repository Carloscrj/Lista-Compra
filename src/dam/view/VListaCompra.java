package dam.view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;


import dam.control.ListaCompraControlador;
import dam.model.ListaCompra;
import dam.model.Producto;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class VListaCompra extends JFrame {
	
	public static final String[] UNIDADES = {"Kg", "G", "L", "Ud"};
	public static final String BTN_INTRODUCIR = "Introducir Producto";
	public static final String BTN_BORRAR = "Borrar Producto" ;
	public static final int ANCHO = 400;
	public static final int ALTO = 600;
	
	private JSpinner spnCantidad;
	private JComboBox <String>cmbUnidades;
	private JButton btnIntroducirProducto;
	private JScrollPane scpProducto;
	private JList <Producto>lstProducto;
	private JTextField txtProducto;
	private JButton btnBorrarProducto;
	private JTextField txtError;
	
	public VListaCompra() {
		InicializarComponentes();
	}

	private void InicializarComponentes() {
		setTitle("Lista Compra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txtProducto = new JTextField();
		txtProducto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtProducto.setBounds(54, 43, 497, 57);
		getContentPane().add(txtProducto);
		txtProducto.setColumns(10);
		
		spnCantidad = new JSpinner();
		spnCantidad.setModel(new SpinnerNumberModel(1, 1, 500, 1));
		spnCantidad.setBounds(224, 146, 49, 20);
		spnCantidad.setEditor(new JSpinner.DefaultEditor(spnCantidad));// esto lo pongo para que no se pueda escribir en el spn(spinner)
		getContentPane().add(spnCantidad);
		
		JLabel lblCantidad = new JLabel("Introduzca cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidad.setBounds(72, 149, 125, 13);
		getContentPane().add(lblCantidad);
		
		cmbUnidades = new JComboBox<String>();
		cmbUnidades.setModel(new DefaultComboBoxModel<String>(UNIDADES));
		cmbUnidades.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbUnidades.setBounds(72, 210, 125, 21);
		getContentPane().add(cmbUnidades);
		
		btnIntroducirProducto = new JButton(BTN_INTRODUCIR);
		btnIntroducirProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIntroducirProducto.setBounds(190, 293, 155, 21);
		getContentPane().add(btnIntroducirProducto);
		
		scpProducto = new JScrollPane();
		scpProducto.setBounds(46, 431, 497, 72);
		getContentPane().add(scpProducto);
		
		lstProducto = new JList<Producto>();
		lstProducto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpProducto.setViewportView(lstProducto);
		
		btnBorrarProducto = new JButton(BTN_BORRAR);
		btnBorrarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrarProducto.setBounds(190, 349, 155, 21);
		getContentPane().add(btnBorrarProducto);
		
		txtError = new JTextField();
		txtError.setEditable(false);
		txtError.setBounds(54, 606, 483, 20);
		getContentPane().add(txtError);
		txtError.setColumns(10);
		setSize(600, 800);
		
		centrarVentana();
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void setControlador(ListaCompraControlador controlador) {
		btnIntroducirProducto.addActionListener(controlador); 
		btnBorrarProducto.addActionListener(controlador);
	}

	
	public Producto obtenerProducto() {
		Producto producto = null;
		int cantidad=0;
		String unidades="";
		
		String nombre= txtProducto.getText();
		
		if (nombre.isBlank()) { 
			txtError.setText("No ha introducido producto.");
			txtError.setForeground(Color.RED);
			producto = null;
		}else {
			cantidad= (int) spnCantidad.getValue();
			unidades= (String) cmbUnidades.getSelectedItem();
			
			producto = new Producto(nombre, cantidad, unidades);
		}
		
		
		return producto;
	}

	public void mostrarProducto(ArrayList<Producto> arrayList) {
		DefaultListModel<Producto> dlm = new DefaultListModel<Producto>();
		
		for (Producto producto : arrayList) {
			dlm.addElement(producto);
		}
		lstProducto.setModel(dlm);
		scpProducto.setViewportView(lstProducto);
	}

	public void comprobPos(ArrayList<Producto> listaProductos) {
		if (lstProducto.getSelectedIndex()==-1) {
			txtError.setText("No ha seleccionado un producto.");
			txtError.setForeground(Color.RED);
		} else {
			listaProductos.remove(lstProducto.getSelectedIndex());
			
		}
		
		mostrarProducto(listaProductos);
		
	}
	
	private void centrarVentana() {
		// asignamos tamaño a la ventana 
		setPreferredSize(new Dimension(ANCHO, ALTO));  
		// Se obtienen las dimensiones en pixels de la pantalla.       
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		// Se obtienen las dimensiones en pixels de la ventana.       
		Dimension ventana = this.getPreferredSize();               
		// Una cuenta para situar la ventana en el centro de la pantalla.       
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
		
	}

}
