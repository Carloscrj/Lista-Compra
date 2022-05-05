package dam.main;

import java.awt.EventQueue;

import dam.control.ListaCompraControlador;
import dam.view.VListaCompra;


public class ListaCompraInicio {

	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					VListaCompra ventana = new VListaCompra();
					
					ListaCompraControlador controlador = new ListaCompraControlador(ventana);
					
					ventana.setControlador(controlador);
					
					ventana.hacerVisible();
				}
			});
		}

	}


