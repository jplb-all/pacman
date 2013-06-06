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
	private int cont = 0;
	private int inc = 35;

	public PacMan(Laberinto l, String fichero) {
		super(NombresPersonaje.PACMAN, l);
		ds = new Point2D.Double(105, getDireccion().ordinal() * 35);
		try {
			img = ImageIO.read(PacMan.class.getResource(fichero));
		} catch (IOException e) {
		}
	}

	public BufferedImage getImg() {
		return img;
	}

	public Point2D.Double getCoordenadasSprite() {
		return ds;
	}

	public void mover(double velocidad, Direccion nuevaDireccion) {
		// El parámetro nuevaDirección representa la dirección que tiene que
		// tomar si fuese posible.
		
		if (super.mover(velocidad) && ++cont == 5) {
			cont = 0;
			ds.x += inc;
			if (ds.x == 175 || ds.x == 0)
				inc *= -1;
		}
		if (getDireccion() != nuevaDireccion && puedeCambiarDireccion(nuevaDireccion)) {
			setDireccion(nuevaDireccion);
			ds.y = getDireccion().ordinal() * 35;
		}
	}
}