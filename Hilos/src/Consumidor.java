
public class Consumidor extends Thread {

	private String id;
	private long retardo;
	Almacen almacen;
	
	public Consumidor(String id, long retardo, Almacen almacen) {
		this.id = id;
		this.retardo = retardo;
		this.almacen = almacen;
	}
	
	public void run(){
		int valor;
		while (true) {
			valor = almacen.coger(id);
			System.out.println(id + ": " + valor);
			
			try {
				Thread.sleep(retardo);
			} catch (InterruptedException e) {}
		}
	}

}
