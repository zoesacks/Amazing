import java.util.ArrayList;
import java.util.List;

public class TransporteUtilitario extends Transporte{

	private double valorExtra;

    public  TransporteUtilitario(String patente, int volMax, int valorViaje, double valorExtra){
        super(patente, volMax, valorViaje);
        this.valorExtra = valorExtra;
    }


    @Override
    protected double calcularCostoViaje(){
    	
    	super.noDebeEstarVacio();
        
		double costoViaje = 0 ;
		
		if (supera10Paquetes()) {
			costoViaje += this.valorExtra;
		}
		
		costoViaje += super.valorViaje;

		return costoViaje;

    }


	private boolean supera10Paquetes() {
		return super.paquetes.size() > 10;
	}
	

	
	
	
	
	
	

}
