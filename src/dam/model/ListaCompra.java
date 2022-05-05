package dam.model;

import java.util.ArrayList;

public class ListaCompra {
	
	private ArrayList<Producto>listaProductos;

	public ListaCompra() {
		listaProductos = new ArrayList<Producto>();
	}

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	
	public void nuevoProducto(Producto producto) { 
		listaProductos.add(producto);
	}
	
	

}
