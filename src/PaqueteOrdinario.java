
public class PaqueteOrdinario extends Paquete{
	double costoEnvio;

	public PaqueteOrdinario(double volumen, double precio, int costoEnvio) {
		super(volumen, precio);
		this.costoEnvio = costoEnvio;
	}
	
	 @Override
	 protected double calcularCostoTotalPaquete() {
		return super.precio + this.costoEnvio;
	}
	 
	@Override
	 protected boolean equals (Paquete paquete) {
		return (getClass() == paquete.getClass() && volumen == paquete.getVolumen() 
					&& precio == paquete.precio);
		}

}
