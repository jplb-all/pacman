
public class PruebaAlmacen {

	public static void main(String[] args) {
		Almacen almacen = new Almacen();
		Productor p1 = new Productor("Productor 1", 1000, almacen);
		Productor p2 = new Productor("Productor 2", 1500, almacen);
		Consumidor c1 = new Consumidor("Consumidor 1", 1700, almacen);
		Consumidor c2 = new Consumidor("Consumidor 2", 800, almacen);
		p1.start();
		p2.start();
		c1.start();
		c2.start();
	}

}
