import java.util.Random;


public class Productor extends Thread {

	private String id;
	private long retardo;
	Almacen almacen;
	
	public Productor(String id, long retardo, Almacen almacen) {
		this.id = id;
		this.retardo = retardo;
		this.almacen = almacen;
	}
	
	public void run(){
		Random r = new Random();
		int valor;
		while (true) {
			almacen.dejar(valor = r.nextInt(10000) + 1, id);
			System.out.println(id + ": " + valor);
				
			try {
				Thread.sleep(retardo);
			} catch (InterruptedException e) {}
		}
	}

}
