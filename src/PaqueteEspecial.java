
public class PaqueteEspecial extends Paquete {
	
	private double valorAdicional;
	private double porcentajeAdicional;
	
	public PaqueteEspecial(double volumen, double precio, double valorAdicional, double porcentajeAdicional) {
		super(volumen, precio);
		this.valorAdicional = valorAdicional;
		this.porcentajeAdicional = porcentajeAdicional;
	}

	@Override
	protected double calcularCostoTotalPaquete() {
		double total = super.precio;
		
		total += porcentajeAdicional();
				
		if(super.volumen > 5000) {
			total += valorAdicional * 2;
		} else if(super.volumen >= 3000) {
			total += valorAdicional;
		}
		
		return total;
	}

	protected double porcentajeAdicional() {

		double porciento = (this.porcentajeAdicional / 100.0) * this.precio;
		
		return porciento;
	}

	protected double getValorAdicional() {
		return valorAdicional;
	}

	protected double getPorcentajeAdicional() {
		return porcentajeAdicional;
	}
	
	private boolean equals (PaqueteEspecial paquete) {
		return (getClass() == paquete.getClass() && volumen == paquete.getVolumen()
				&& precio == paquete.getPrecio() && valorAdicional == paquete.getValorAdicional()
				&& porcentajeAdicional == paquete.getPorcentajeAdicional());
	}
}



