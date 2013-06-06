import java.awt.geom.Point2D;

import javax.swing.text.Position;


public class CtrlJuego {
	Fantasma blinky, inky, pinky, clyde;
	PacMan pacman;
	Laberinto laberinto;
	Direccion d;
	Thread t;
	boolean iniciado = true;
	
	
	public CtrlJuego (){
		laberinto = Laberinto.ORIGINAL;

/***/
//		blinky = new Fantasma(NombresPersonaje.BLINKY, laberinto);
//		inky = new Fantasma(NombresPersonaje.INKY, laberinto);
//		pinky = new Fantasma(NombresPersonaje.PINKY, laberinto);
//		clyde = new Fantasma(NombresPersonaje.CLYDE, laberinto);
		pacman = new PacMan(laberinto, "/img/comecocos.png");
//		d = pacman.getDireccion();
		d = Direccion.ARRIBA;
	}
	
	/*Cambio de direccion*/
	public void cambiarDireccion (Direccion d){
		this.d = d;
	}
	
	/*Devolvemos la posicion del PacMan*/
	public PacMan getPacman (){
		return pacman;
	}
	
	/*Método para iniciar el juego*/
	public void inicioJuego(){
		t = new Thread(){
			@Override
			public void run() {
				while(iniciado){ //Bucle de ejecucion de la partida
					pacman.mover(0.15, d);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						
					}
				}//Fin de bucle de ejecucion de la partida.
				
			}
		};
		t.start();
	}
}
