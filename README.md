# Amazing 
  Informe

Introducción
En este trabajo realizamos la parte de implementación para un sistema de una empresa planteado en la primera parte de este trabajo. La clase principal se llamara “Empresa Amazing”, y esta posee varios métodos públicos que le permitirá al usuario asignar paquetes, carga Transporte y calcular costo de envío. Utilizaremos la interfaz proporcionada por la cátedra para obtener los métodos importantes y solucionarlos.

Descripción Del Trabajo
Se utilizaron los conceptos fundamentales de OOP (Programación Orientada a Objetos) para realizar este trabajo. En primer lugar, la herencia se ve al momento de tener una clase Paquetes especiales y Paquetes Ordinarios de la cual se heredan de la clase Paquete, y también se utiliza la herencia en la clase Transporte de la cual tiene 3 subclases que son Transporte Comun, Transporte Utilitario y Transporte Camión. Esto se hizo así debido a que todas estas clases poseen varias características en común, poseen un HASHMAP que permite almacenar todos los (“paquetes” y “transportes”) que tiene la empresa. Estos se identifican con una clave que se genera, y es única.

Conceptos
Herencia 
La herencia se aplica de la Clase Paquetes hacia las subclases Paquetes especiales y Paquetes Ordinarios. Lo que hace dicha clase Paquete es invocar a un método que inicializa a todos con los parámetros pasados en paquetes, los cuales pueden usarse en las subclases.
Así mismo pasa con la clase Transporte, al cual solo le pasamos los parámetros necesarios como patente, volumen máximo y valor del viaje. Luego, el constructor se encargará de inicializar los parámetros y las funciones proporcionadas. 

Polimorfismo 
El polimorfismo se aplica en la clase paquete, en la cual tenemos el método “calcularCostoDePaquete”, cada subclase (Paquete Especial y Ordinario) proporciona su propia implementación personalizada de ese método. Esto permite que cada tipo de paquete, ya sea ordinario o especial, determine su costo de manera independiente.

Sobreescritura
La sobreescritura se utiliza en diferentes métodos. La sobreescritura implica que estas funciones se redefinieron en las subclases, lo que nos permite que cada subclase nos proporcione su propia implementación. Cada subclase puede definir cómo realizar estas operaciones según sus necesidades individuales. En la clase paquete se utilizo para el metodo calcularCostoDePaquete y en transporte para asignarPaquete y calcularCostoViaje.

Sobrecarga
 Se genera sobrecarga en el método “agregarPaquete”. Este concepto de sobrecarga implica que el método “agregarPaquete” se definió varias veces en la misma clase, pero con diferentes parámetros. Esto permite invocar el mismo nombre de método de manera conveniente.  




Explicación de algunos métodos
Registrar Automóvil
Para poder registrar un nuevo vehículo, primero se tienen que verificar que los datos ingresados por el usuario sean correctos y así poder registrar al nuevo especialista dentro del sistema. Y si el vehículo ya está registrado habrá una excepción. 

Registrar Pedido 
para registrar un pedido tiene que verificar que los datos ingresados sean correctos y este se agrega en un MAP.

Agregar Paquete
Verifica que el pedido exista, que esté registrado en el sistema y que esté finalizado, una vez que se verificó todo esto, se agrega un paquete a la lista de paquetes.


IREP 
Empresa Amazing:
Transportes: HashMap donde la clave es el String patente y el valor el Transpote. No debe ser nula. No se deben registrar dos veces el mismo transporte.
Pedidos: HashMap donde la clave es el Int codPedido y el valor un Pedido.  No debe ser nula y no tendrá repetidos porque codPedido es único por pedido.
Enviados: lista de paquetes enviados, deben pertenecer a pedidos cerrados.
totalPedidosCerrados: Debe ser igual a la suma de los costos de los pedidos cerrados, por lo tanto será siempre mayor o igual a cero.
Paquete:
VolPaquete: Debe ser mayor a 0. 
PrecioPaquete: Debe ser mayor o igual a 0.
CodPaquete: Debe ser mayor o igual que cero y único para cada paquete.
CostoEnvio: Debe ser mayor o igual a cero.
CostoAdicional: Debe ser mayor o igual a cero.

PaqueteEspecial:
ValorAdicional: Debe ser mayor a 0.
PorcentajeAdicional: Debe ser mayor a 0.

PaqueteOrdinario:
CostoEnvioAdicional: Debe ser mayor a 0.

Pedido: 
Código:  Debe ser mayor o igual que cero y único para cada pedido.
Finalizado: indica si el pedido ha sido finalizado o no. En un principio es falso porque no debe ser verdadero si el pedido está vacío.
Paquetes: es una lista que contiene objetos de la clase Paquete. No debe ser nula. No debe tener paquetes repetidos, osea con el mismo código.
Dirección: No debe ser nulo.

Transporte:
Patente: No debe ser nulo.
VolMaxCarga: Debe ser mayor o igual a cero. Al agregar paquetes a la lista Paquetes no se debe superar este volumen .
ValorViaje: Debe ser mayor o igual a cero.
Paquetes: es una lista que contiene objetos de la clase Paquete que están cargados en el transporte.

TransporteComun:
MaxPaq: es un entero que representa la cantidad máxima de paquetes, al agregar paquetes a la lista no se debe superar esta cantidad.

TransporteUtilitario:
valorExtra: Debe ser mayor a 0.

Transporte Camión:
valorAdicional: Debe ser mayor a 0.

Complejidad y álgebra de órdenes 

Se inicializa un iterador para la lista de paquetes. La conversión a Iterable y el uso del iterador tienen una complejidad constante.
El bucle while itera sobre la lista de paquetes utilizando el iterador. iterador.hasNext() tiene una complejidad constante por cada iteración, y iterador.next() también tiene una complejidad constante ya que simplemente obtiene el siguiente elemento en la lista.
La comparación paq.getCodigo() == codPaquete tiene una complejidad constante, ya que se compara un valor con otro.
La operación iterador.remove() tiene una complejidad constante y elimina el elemento actual de la lista mientras se itera.
Aquí se actualiza paqueteEncontrado en el peor de los casos una vez. La complejidad de esta parte es O(1) porque es constante por cada iteración.
Finalmente, el método devuelve “Paquete Encontrado”, lo cual es una operación constante. La complejidad del método es O(n), donde “n” es el número de paquetes en la lista.



En el método Quitar Paquete, se está iterando sobre las claves de un mapa (pedidos). La iteración sobre un mapa tiene una complejidad lineal O(m), donde "m" es el número de elementos en el mapa. En cada iteración, obtenemos el valor asociado con la clave, que es una operación de complejidad constante.
En cada iteración, estás llamando al método recorrerListaYEliminarPaq(codPaquete) de un objeto Pedido. El método recorrerListaYEliminarPaq tiene una complejidad O(n), donde "n" es el número de paquetes en la lista, como comentamos anteriormente.
Finalmente, el método devuelve paqueteEliminado, que es una operación constante. La complejidad total del método quitarPaquete está dominada por la iteración sobre las claves del mapa y la llamada al método recorrerListaYEliminarPaq en cada iteración. Por lo tanto, la complejidad total es O (m * n), donde "m" es el número de pedidos en el mapa y "n" es el número promedio de paquetes en cada pedido.
Diagrama de clases


