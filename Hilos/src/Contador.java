
public class Contador extends Thread {

	private String nombre;
	
	public Contador(String nombre){
		this.nombre = nombre;
	}
	
	public void run() {
		for (int i=0; i<10; i++)
			System.out.println(nombre + ": " + i);
	}
	
	public static void main(String[] args) {
		Contador c1 = new Contador("Contador 1");
		Contador c2 = new Contador("Contador 2");
		c1.start();
		c2.start();
	}

}
