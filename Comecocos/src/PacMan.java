import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PacMan extends Personaje {

	private BufferedImage img;
	private Point2D.Double ds; // desplazamiento (x, y) para seleccionar el
								// sprite del
								// pacman que se tiene que diburar en un
								// instante determinado.

	public PacMan(Laberinto l, String fichero) {
		super(NombresPersonaje.PACMAN, l);
		try {
			img = ImageIO.read(PacMan.class.getResource(fichero));
		} catch (IOException e) {
		}
	}

	public BufferedImage getImg() {
		return img;
	}

	public Point2D.Double getCoordenadasSprite() {

		return null;
	}

	public void mover(double velocidad, Direccion nuevaDireccion) {
		// El par�metro nuevaDirecci�n representa la direcci�n que tiene que
		// tomar si fuese posible.
		super.mover(velocidad);
		if (getDireccion() != nuevaDireccion && puedeCambiarDireccion(nuevaDireccion))
			setDireccion(nuevaDireccion);
	}
}