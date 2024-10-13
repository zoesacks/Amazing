
public class Paquete {
	private static int contador = 0;
	private int codigo;
	private boolean entregado;
	protected double volumen;
	protected double precio;

	
	public Paquete (double volumen, double precio) {
		this.volumen = volumen;
		this.precio = precio;
		this.codigo = generarIdentificacion();
		this.entregado = false;
		
	}
	

	protected double calcularCostoTotalPaquete() {
		return this.precio;
	}


	private int generarIdentificacion() {
		contador ++;
		return contador;
	}
	

	protected int getCodigo() {
		return codigo;
	}

	protected double getPrecio() {
		return precio;
	}
	
	protected double getVolumen() {
		return volumen;
	}
	 
	public boolean isEntregado() {
		return entregado;
	}
	
	protected boolean equals (Paquete paquete) {
		return (getClass() == paquete.getClass() && volumen == paquete.getVolumen() 
				&& precio == paquete.getPrecio()); // Preguntarle a ZOE :D
	}


	public void entregado() {
		this.entregado = true;
		
	}

}
