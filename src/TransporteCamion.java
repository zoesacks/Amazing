import java.util.List;

public class TransporteCamion extends Transporte{

	private double valorAdicional;
	
	
    public  TransporteCamion(String patente, int volMax, int valorViaje, double valorAdicional){
        super(patente, volMax, valorViaje);
        this.valorAdicional = valorAdicional;
    }
		
	@Override
	protected boolean cargarPaqueteEnTransporte(Paquete paquete){
		boolean paqueteAsignado = false;
		
		if (paqueteValido(paquete)) {
			paqueteAsignado |= super.asignarPaquete(paquete);
		}
		
		return paqueteAsignado;
	}
    
	
    @Override
    protected double calcularCostoViaje(){
    	
    	super.noDebeEstarVacio();
		
		double costoViaje = valorAdicionalPorCantPaquetes() + super.valorViaje;

		return costoViaje;
    }
    


	private boolean paqueteValido(Paquete paquete) {
		return paquete instanceof PaqueteEspecial && paquete.getVolumen() > 2000;
	}

	private double valorAdicionalPorCantPaquetes() {
		int cantPaquetes = super.paquetes.size();
			
		return cantPaquetes * this.valorAdicional;
	}
	

}
