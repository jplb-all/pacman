import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Escenario extends JPanel implements Runnable,ActionListener{
	
	private CtrlJuego cj;
	private Laberinto l;
	private PacMan pacman;
	private Thread t;
	
	
	public Escenario() {
		cj = new CtrlJuego(l = Laberinto.ORIGINAL);
		pacman = cj.getPacman();
		setPreferredSize(new Dimension(l.getImagen().getWidth(),l.getImagen().getHeight()));
		registerKeyboardAction(this, "iniciar",
				  KeyStroke.getKeyStroke(KeyEvent.VK_I, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		registerKeyboardAction(this, "arriba",
				  KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		registerKeyboardAction(this, "abajo",
				  KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		registerKeyboardAction(this, "izquierda",
				  KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		registerKeyboardAction(this, "derecha",
				  KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(l.getImagen(), 0, 0, this);
		int x = (int) pacman.getPosicion().x;
		int y = (int) pacman.getPosicion().y;
		Point2D.Double ds = pacman.getCoordenadasSprite();
		g.drawImage(pacman.getImg(), x, y, x+35, y+35, (int) ds.x, (int) ds.y, (int) ds.x + 35, (int) ds.y + 35, this);

	}
	public void iniciar(){
		t=new Thread(this);
		cj.inicioJuego();
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}
	@Override
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {

			}
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("iniciar"))
				iniciar();
		else if(e.getActionCommand().equals("arriba"))
			cj.cambiarDireccion(Direccion.ARRIBA);
		else if(e.getActionCommand().equals("abajo"))
			cj.cambiarDireccion(Direccion.ABAJO);
		else if(e.getActionCommand().equals("izquierda"))
			cj.cambiarDireccion(Direccion.IZDA);
		else if(e.getActionCommand().equals("derecha"))
			cj.cambiarDireccion(Direccion.DERECHA);
	}

}