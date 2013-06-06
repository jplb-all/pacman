import java.awt.geom.Point2D;


public abstract class Personaje {


		private NombresPersonaje id;
		private Laberinto laberinto;
		private Point2D.Double posicion;
		private Direccion direccion; //Son la clase fantasma y la clase pacman la que cambia la direccion
		private int fil, col, filAnt, filSgt, colAnt, colSgt;
		
		public Personaje(NombresPersonaje id, Laberinto laberinto) {
			this.laberinto = laberinto;
			this.id = id;
			posicion = laberinto.getPosIni(id);
			direccion = laberinto.getDirIni(id);
			
		}
		
		public boolean mover(double velocidad) {
			//Retorna cierto si se mueve en la pantalla y actualiza la posicion del personaje
			Point2D.Double aux= new Point2D.Double(posicion.x, posicion.y);
			
			//comprueba si puede moverse en esa direccion
			if ((direccion == Direccion.DERECHA) && ((int) posicion.x % 19 != 0 || !laberinto.muro(fil, colSgt)))
				posicion.x += velocidad;
			else if ((direccion == Direccion.IZDA) && ((int) posicion.x % 19 != 0 || !laberinto.muro(fil, colAnt)))
				posicion.x -= velocidad;
			else if ((direccion == Direccion.ABAJO) && ((int) posicion.y % 19 != 0 || !laberinto.muro(filSgt, col)))
				posicion.y += velocidad;
			else if ((direccion == Direccion.ARRIBA) && ((int) posicion.y % 19 != 0 || !laberinto.muro(filAnt, col)))
				posicion.y -= velocidad;
			
			//Comrueba si se ha movido y actualiza las posiciones
			boolean seHaMovido = ((int) posicion.x != (int) aux.x || (int) posicion.y != (int) aux.y);
			if(seHaMovido){
				fil = (int) (posicion.y / 19);
				col = (int) (posicion.x / 19);
				filAnt = fil-1;
				filSgt = fil+1;
				colAnt = col-1;
				colSgt = col+1;
			}
			return seHaMovido;
		}
		
		public boolean puedeCambiarDireccion(Direccion direccion){
			// retorna true si en el proximo intento de moverse a esa dirección el personaje se va
			// encontrar con un muro
		return (int) posicion.x % 19 == 0
				&& (int) posicion.y % 19 == 0
				&& ((direccion == Direccion.DERECHA && !laberinto.muro(fil, colSgt))
						|| (direccion == Direccion.IZDA && !laberinto.muro(fil, colAnt))
						|| (direccion == Direccion.ARRIBA && !laberinto.muro(filAnt, col))
						|| (direccion == Direccion.ABAJO && !laberinto.muro(filSgt, col)));
		}
		
		public Direccion getDireccion(){
			return direccion;
		}
		
		public Point2D.Double getPosicion(){
			return posicion;
		}
		
		public void setDireccion(Direccion direccion){
			this.direccion = direccion;
		}
		
		public void reset(){
			posicion = laberinto.getPosIni(id);
			direccion = laberinto.getDirIni(id);
		}
}
