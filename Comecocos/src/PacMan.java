import java.awt.geom.Point2D;


public class PacMan extends Personaje{
	
	public PacMan(Laberinto l) {
		super(NombresPersonaje.PACMAN, l);
	}

	public void mover(double velocidad, Direccion nuevaDireccion){
		// El parámetro nuevaDirección representa la dirección que tiene que tomar si fuese posible.
		super.mover(velocidad);
		Direccion d = getDireccion();
		Point2D.Double p = getPosicion();
		switch (d){
		case ARRIBA:
			if(nuevaDireccion == Direccion.ABAJO){
				setDireccion(nuevaDireccion);
			}
			else if(nuevaDireccion != Direccion.ARRIBA && (int) p.y % 19 == 0){
				if(!muro(nuevaDireccion)){
					setDireccion(nuevaDireccion);
				}
			}
			break;
		case IZDA:
			if(nuevaDireccion == Direccion.DERECHA){
				setDireccion(nuevaDireccion);
			}
			else if(nuevaDireccion != Direccion.IZDA && (int) p.x % 19 == 0){
				if(!muro(nuevaDireccion)){
					setDireccion(nuevaDireccion);
				}
			}
			break;
		case ABAJO:
			if(nuevaDireccion == Direccion.ARRIBA){
				setDireccion(nuevaDireccion);
			}
			else if(nuevaDireccion != Direccion.ABAJO && (int) p.y % 19 == 0){
				if (!muro(nuevaDireccion)){
					setDireccion(nuevaDireccion);
				}
			}
			break;
		case DERECHA:
			if(nuevaDireccion == Direccion.IZDA){
				setDireccion(nuevaDireccion);
			}
			else if(nuevaDireccion != Direccion.DERECHA && (int) p.x % 19 == 0){
				if(!muro(nuevaDireccion)){
					setDireccion(nuevaDireccion);
				}
			}
			
			break;
		}
	}
}