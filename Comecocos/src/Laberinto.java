
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Laberinto {
	
	/** dependiendo de lo que haya en cada casilla esta toma un valor inicial
	VALORES Y SIGNIFICADO:
	Bits 0-7 contenido celda:
	*/
	private static final int PARED = 0;
	private static final int VACIO = 1;
	private static final int BOLAP = 3;
	private static final int BOLAG = 5;  // al comer 1 de estas pacman puede comerse a los fantasmas
	
	/** Bits 8-31 restricciones */
	private static final int PROHIBIDOSUBIR = 0x0010; // no permite subir a los fantasmas
	private static final int PROHIBIDOBAJAR = 0x0020; // no permite subir a los fantasmas
	private static final int PROHIBIDOIZDA = 0x0040; // no permite subir a los fantasmas
	private static final int PROHIBIDODCHA = 0x0080; // no permite subir a los fantasmas
	private static final int ZONALENTA = 0x0100; // al pasar por esta zona el fantasma se ralentiza pero el pacman sigue a la misma velocidad
		
	private static Laberinto ORIGINAL;
	
	private int [][] celdas;
	private int [][] celdasAux;
	
	private Ruta [] rutasS; // Rutas de salida de cada fantasma de su casa
	private Ruta [] rutasE; // Rutas de entrada de cada fantasma a su casa
	private Ruta [] rutasC; // Rutas de espera de cada fantasma en su casa
	
	private Point2D.Double [] pIni; // posiciones iniciales de cada personaje
	private Point2D.Double posES; // Posición de entrada/salida de los fantasmas
	private Point2D.Double [] objetivos; // Objetivos de los fantasmas en modo dispersión
	
	private BufferedImage img;
	
	public Laberinto(int [][] celdas, String fichero) {
		this.celdas = new int [celdas.length][];
		for (int i = 0; i < celdas.length; i++) {
			this.celdas[i] = celdas[i].clone();
		}
		this.celdasAux = new int [celdas.length][];
		reset();
		rutasS = new Ruta[4];
		rutasE = new Ruta[4];
		rutasC = new Ruta[4];
		pIni = new Point2D.Double[5];
	    objetivos = new Point2D.Double[4];
		try {
			img = ImageIO.read(Laberinto.class.getResource(fichero));
		} catch (IOException e) {}
	}
	
	public void reset(){
		for (int i = 0; i < celdas.length; i++) {
			this.celdasAux[i] = celdas[i].clone();
		}
	}
	
	public boolean punto(int fil, int col){
		/** retorna true si en [fil][col] hay un punto pequeño*/
		return ((celdasAux[fil][col] & 0x000f) == 2);
	}
	
	public boolean puntoGrueso(int fil, int col){
		/**retorna true si en [fil][col] hay un punto grueso*/
		return ((celdasAux[fil][col] & 0x000f) == 3);
	}
	
	public boolean prohibidoSubir(int fil, int col){
		/** retorna true si en [fil][col] el fantasma tiene prohibido cambiar su dirección hacia arriba*/
		return ((celdasAux[fil][col] & PROHIBIDOSUBIR) == PROHIBIDOSUBIR);
		
	}
	
	public boolean reducirVelocidad(int fil, int col){
		/** retorna true si en [fil][col] el fantasma tiene prohibido cambiar su dirección hacia arriba*/
		return((celdasAux[fil][col] & ZONALENTA) == ZONALENTA);
	}
	
	public void quitarPunto(int fil, int col){
		celdasAux[fil][col] &= 0xfff9;
	}
	
	public Point2D.Double getPosicionES() {
		return  (Point2D.Double) posES.clone();
	}
	
	public Point2D.Double getPosicionInicial(NombresPersonaje id) {
		return (Point2D.Double) pIni[id.ordinal()].clone();
	}
	
	public Point2D.Double getObjetivo(NombresPersonaje id) {
		
	}
	
	public Ruta getRutaE(NombresPersonaje id) {
		
	}
	
	public Ruta getRutaS(NombresPersonaje id) {
		
	}
	
	public Ruta getRutaC(NombresPersonaje id) {
		
	}
	
	public BufferedImage getImagen(){
		return img;
	}
	
	
	static {/*30x33*/
		int [][] celdas  = {
				   /*0*/	 /*5*/     /*10*/    /*15*/    /*20*/    /*25*/  /*29*/
			/*0*/ 	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,0},
					{0,0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,0},
					{0,0,3,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,3,0,0},
			/*5*/	{0,0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,0},
					{0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0},
					{0,0,2,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2,0,0},
					{0,0,2,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2,0,0},
					{0,0,2,2,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,2,2,0,0},
			/*10*/	{0,0,0,0,0,0,0,2,0,0,0,0,0,1,0,0,1,0,0,0,0,0,2,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,2,0,0,0,0,0,0x0011,0,0,0x0011,0,0,0,0,0,2,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,2,0,0,1,1,1,1,1,1,1,1,1,1,0,0,2,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,2,0,0,1,0,1,1,1,1,1,1,0,1,0,0,2,0,0,0,0,0,0,0},
			/*15*/	{0x0100,0x0100,0x0100,0x0100,0x0100,0x0100,0x0100,2,1,1,1,0,1,1,1,1,1,1,0,1,1,1,2,0x0100,0x0100,0x0100,0x0100,0x0100,0x0100,0x0100},
					{0,0,0,0,0,0,0,2,0,0,1,0,1,1,1,1,1,1,0,1,0,0,2,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,2,0,0,1,1,1,1,1,1,1,1,1,1,0,0,2,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,0},
			/*20*/	{0,0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,0},
					{0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,0},
					{0,0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,0},
					{0,0,2,0,0,0,0,2,0,0,0,0,0,0x0012,0,0,0x0012,0,0,0,0,0,2,0,0,0,0,2,0,0},
					{0,0,3,2,2,0,0,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,0,0,2,2,3,0,0},
			/*25*/	{0,0,0,0,2,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,2,0,0,0,0},
					{0,0,0,0,2,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,2,0,0,0,0},
					{0,0,2,2,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,2,2,0,0},
					{0,0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,2,0,0},
					{0,0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,2,0,0},
			/*30*/	{0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			/*32*/	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
		ORIGINAL = new Laberinto(celdas, "/img/laberinto.png");
		ORIGINAL.posES = new Point2D.Double(275, 228);
		ORIGINAL.pIni[NombresPersonaje.BLINKY.ordinal()] =  new Point2D.Double(275,228);
		ORIGINAL.pIni[NombresPersonaje.INKY.ordinal()] =  new Point2D.Double(233,304);
		ORIGINAL.pIni[NombresPersonaje.PINKY.ordinal()] =  new Point2D.Double(276,276);
		ORIGINAL.pIni[NombresPersonaje.CLYDE.ordinal()] =  new Point2D.Double(319,304);
		ORIGINAL.pIni[NombresPersonaje.PACMAN.ordinal()] =  new Point2D.Double(275,456);
		
	}
	
}