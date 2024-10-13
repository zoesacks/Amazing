import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Transporte {
	protected String patente;
	protected int volMax;
	protected double valorViaje;
	protected List <Paquete> paquetes = new ArrayList<>();


	
	public Transporte (String patente, int volMax, int valorViaje) {
		this.patente = patente;
		this.volMax = volMax;
		this.valorViaje = valorViaje;
	}
	
	protected boolean asignarPaquete(Paquete paquete) {
		boolean paqueteAsignado = false;
		
		if(noSuperaElVolMax(paquete)) {
			this.paquetes.add(paquete);
			paqueteAsignado = true;
		}
		
		return paqueteAsignado;
	}
	

	protected boolean cargarPaqueteEnTransporte(Paquete paquete){
		boolean paqueteAsignado = false;	
		
		paqueteAsignado |= asignarPaquete(paquete);
		
		return paqueteAsignado;
	}
	
	protected void noDebeEstarVacio() {
		if (estaVacio()) {
        	throw new RuntimeException("El transporte no tiene ningun paquete");
        }
	}

	protected boolean noSuperaElVolMax(Paquete paquete) {
		double volCargado = 0;
		
		for (Paquete paque : paquetes) {
			volCargado += paque.getVolumen();
		}
		
		volCargado += paquete.getVolumen();

		return volCargado <= volMax;
	}

	protected double calcularCostoViaje(){		
        if (estaVacio()) {
        	throw new RuntimeException("El transporte no tiene ningun paquete");
        }
		
		return this.valorViaje;
	}
	
	public boolean equals (Transporte transporte) {
		if (this.patente != transporte.patente && getClass() == transporte.getClass()) 
				if (!estaVacio() && !transporte.estaVacio())
				return true;
		return false;
	}

	protected String getPatente() {
		return patente;
	}

	protected boolean estaVacio() {
		return paquetes.size() == 0;
	}

}
