import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Pedido {
	private static int contador = 0;
	private String nombre;
	private String direccion;
	private int dni;
	private int numPedido;
	private boolean finalizado;
	private HashMap<Integer, Paquete> paquetes;
	
	public Pedido(String nombre, String direccion, int dni) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.dni = dni;
		this.finalizado = false;
		
		this.numPedido = generarNumeroPedido();
		this.paquetes  = new HashMap<>();
	}
	

	protected void agregarPaquete(Paquete paquete) {
		if (this.finalizado) {
			throw new RuntimeException("El pedidio ya esta finalizado");
		}
		
		int codigo = paquete.getCodigo();
		this.paquetes.put(codigo, paquete);
	}
	
	protected boolean eliminarPaquete(int codPaquete) {
		boolean paqueteEncontrado = false;
		
		Iterator<Paquete> iterador = ((Iterable<Paquete>) paquetes).iterator();
		
		while(iterador.hasNext()) {
			
			Paquete paq = iterador.next();
			
			if (paq.getCodigo() == codPaquete) {
				iterador.remove();
				paqueteEncontrado = true; ///Si encuentra el paquete, lo elimina y cambia a TRUE, sino devuelve FALSE.
			}
		}
		
		return paqueteEncontrado;
	}

	protected double calcularTotal() {
		double total = 0;
		for (Paquete paquete : paquetes.values()) {	
			total += paquete.calcularCostoTotalPaquete();
		}
		return total;
	}

	protected double finalizar() {
		if (this.finalizado) {
			throw new RuntimeException("El pedidio ya esta finalizado");
		}
		if (estaVacio()) {
			throw new RuntimeException("El pedidio esta vacio");
		}
		
		this.finalizado = true;
		
		return calcularTotal();
	}

	public StringBuilder cargarPaquetes(Transporte transporte) {
		
		StringBuilder sb = new StringBuilder();
		
		if(this.finalizado) {
			
			for(Paquete paquete : this.paquetes.values()) {
				
				if(!paquete.isEntregado() && transporte.cargarPaqueteEnTransporte(paquete)) {
					paquete.entregado();
					sb.append(paqueteAgregado( paquete));
				}
			}
		}
		
		return sb;
	}
	
	public boolean incompleto() {
		
		boolean incompleto = false;
		
		if(this.finalizado) {
			
			for(Paquete paquete : this.paquetes.values()) {
				incompleto |= !paquete.isEntregado();
			}
		}
		
		return incompleto;
	}
	
	private boolean estaVacio() {
		return this.paquetes.size() == 0;
	}
	
	protected boolean contieneCodPaquete(int codigo) {
		return paquetes.containsKey(codigo);
	}
	
	
	private StringBuilder paqueteAgregado(Paquete paquete) {
	    int codigo = paquete.getCodigo();
	    int numPedido = this.numPedido;
	    String direccion = this.direccion;

	    StringBuilder sb = new StringBuilder();
	    sb.append(" + [ ")
	                 .append(numPedido)
	                 .append(" - ")
	                 .append(codigo)
	                 .append(" ] ")
	                 .append(direccion)
	                 .append("\n");

	    return sb;
	}
	
 	private int generarNumeroPedido() {
		contador ++;
		return contador;
	}

 	protected boolean getFinalizado() {
		return this.finalizado;
	}

	protected String getNombre() {
		return nombre;
	}

	protected String getDireccion() {
		return direccion;
	}

	protected int getNumPedido() {
		return numPedido;
	}







}
