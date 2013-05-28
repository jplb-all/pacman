class Almacen {
	int[] productos = new int[0x10];
	int primero = 0;
	int stock = 0;
 
	public synchronized int coger(String id) {
		int aux;
		while (stock == 0) {
			System.out.println(id + ": esperando");
			try {wait();} catch (InterruptedException e) {}
		}
		stock--;
		aux = primero;
		primero = (primero + 1) & 0xF;
		notifyAll();
		return (productos[aux]);
	}

	public synchronized void dejar(int val, String id) {
		while (stock == 0x10) {
			System.out.println(id + ": esperando");
			try { wait(); } catch (InterruptedException e) {}
		}
		int aux;
		aux = (primero + stock) & 0xF;
		stock++;
		productos[aux] = val;
		notifyAll();
	}
}