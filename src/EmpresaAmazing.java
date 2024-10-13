import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {
	
	String cuit;
	HashMap<Integer, Pedido> pedidos = new HashMap<>();
	HashMap<String, Transporte> transportes = new HashMap<>();
	double totalPedidosCerrados = 0;

	public EmpresaAmazing(String cuit) {
		this.cuit = cuit;
	}
	
	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq){

        patenteNoDebeEstarRegistrada(patente);
        
        TransporteComun transporteAutomovil = new TransporteComun(patente, volMax, valorViaje, maxPaq);
        transportes.put(patente, transporteAutomovil);
    }


	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra){

		patenteNoDebeEstarRegistrada(patente);

	    TransporteUtilitario transporteUtilitario = new TransporteUtilitario(patente, volMax, valorViaje, valorExtra);
	    transportes.put(patente, transporteUtilitario);
        
    }
	
	
	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq){

		patenteNoDebeEstarRegistrada(patente);

	    TransporteCamion transporteCamion = new TransporteCamion(patente, volMax, valorViaje, adicXPaq);
	    transportes.put(patente, transporteCamion);            
    }
	
	
	@Override
	public int registrarPedido(String cliente, String direccion, int dni) {
		
		Pedido pedido = new Pedido(cliente, direccion, dni);
		Integer numeroDePedido = pedido.getNumPedido();
		pedidos.put(numeroDePedido, pedido);
		
		return numeroDePedido;
	}
	
	
	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		
		pedidoDebeEstarRegistrado(codPedido);
		
		PaqueteOrdinario paquete = new PaqueteOrdinario(volumen, precio, costoEnvio);
		Pedido pedido = pedidos.get(codPedido);
		pedido.agregarPaquete(paquete);
		
		return paquete.getCodigo();
	}
	

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		
		pedidoDebeEstarRegistrado(codPedido);
		
		PaqueteEspecial paquete = new PaqueteEspecial(volumen, precio, adicional, porcentaje);
		Pedido pedido = pedidos.get(codPedido);
		pedido.agregarPaquete(paquete);
		
		return paquete.getCodigo();
		
	}

	
	@Override
	public boolean quitarPaquete(int codPaquete) {
		boolean paqueteEliminado = false;
		
		for (Pedido p : pedidos.values()) {
			paqueteEliminado |= p.eliminarPaquete(codPaquete);
		}
		
		return paqueteEliminado;
	}	
			
	
	@Override
	public double cerrarPedido(int codPedido) {
		
		pedidoDebeEstarRegistrado(codPedido);
		
		Pedido pedido = pedidos.get(codPedido);
		
		double totalPagar = pedido.finalizar();
		totalPedidosCerrados += totalPagar;
		
		return totalPagar;
	}
	
	
	@Override
	public String cargarTransporte(String patente) {
		
		patenteDebeEstarRegistrada(patente);
		
		Transporte transporte = transportes.get(patente);
		StringBuilder sb = new StringBuilder();

		for (Pedido pedido : pedidos.values()) {
			sb.append(pedido.cargarPaquetes(transporte));
		}
		
		return sb.toString();
	}


	@Override
	public double costoEntrega(String patente) {
        patenteDebeEstarRegistrada(patente); 
        
        Transporte transporte = transportes.get(patente);
		return transporte.calcularCostoViaje();
	}

	
	@Override
	public Map<Integer, String> pedidosNoEntregados() {
		
		Map<Integer, String> pedidosIncompletos = new HashMap<>();

		for (Pedido pedido : pedidos.values()) {
			
			if(pedido.incompleto()) {
				pedidosIncompletos.put(pedido.getNumPedido(), pedido.getNombre());
			}
		}
		
		return pedidosIncompletos;
	}

	
	@Override
	public double facturacionTotalPedidosCerrados() {
		return totalPedidosCerrados;
	}

	
	@Override
	public boolean hayTransportesIdenticos() {
		boolean hayIdenticos = false;
		for (Transporte trans1 : transportes.values()) {
			for (Transporte trans2 : transportes.values()) {
				hayIdenticos |= (trans1.equals(trans2)) ;
						
//&& mismoPaquete(trans1.paquetes, trans2.paquetes);
			}
		}
		return hayIdenticos;
	}
	
	
	
	private boolean mismoPaquete(List<Paquete> paquetes, List<Paquete> paquetes2) {
		boolean mismoPaquete = false;
		for (Paquete p1 : paquetes) {
				for(Paquete p2 : paquetes2) {
					mismoPaquete |=p1.equals(p2);
				}
			}
		return mismoPaquete;
		}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("\n");
	    sb.append("--------------------------------------------------\n");
	    sb.append("\n");
	    sb.append("           EMPRESA AMAZING [").append(cuit).append("]\n");
	    sb.append("\n");
	    sb.append("--------------------------------------------------\n");


	    return sb.toString();
	}

	
	private void patenteNoDebeEstarRegistrada(String patente) {
        if (patenteRegistrada(patente)) {
            throw new RuntimeException("La patente ya esta registrada");
        }
	}
	
	private boolean patenteRegistrada(String patente) {
		return transportes.containsKey(patente);
	}

	private void patenteDebeEstarRegistrada(String patente) {
        if (!patenteRegistrada(patente)) {
            throw new RuntimeException("La patente no esta registrada");
        }
	}
	
	private void pedidoDebeEstarRegistrado(int codPedido) {
		if(!pedidoRegistrado(codPedido)) {
			throw new RuntimeException("El pedidio no esta registrado en el sistema");
		}
	}
	
	private boolean pedidoRegistrado(int codPedido) {
		return pedidos.containsKey(codPedido);
	}

}
