import java.util.List;

public class TransporteComun extends Transporte{
	private int maxPaq;
	
    public TransporteComun(String patente, int volMax, int valorViaje, int maxPaq){
        super(patente, volMax, valorViaje);
        this.maxPaq = maxPaq;
    }
    
	@Override
	protected boolean cargarPaqueteEnTransporte(Paquete paquete){
		boolean paqueteAsignado = false;
		
		if (paqueteValido(paquete) && !superaCapacidad()) {
			paqueteAsignado |= super.asignarPaquete(paquete);
		}
		
		return paqueteAsignado;
	}

	private boolean paqueteValido(Paquete paquete) {
		return paquete instanceof PaqueteOrdinario && paquete.getVolumen() < 2000;
	}
	
	protected boolean superaCapacidad() {
		return paquetes.size() >= maxPaq;
	}

	
}
