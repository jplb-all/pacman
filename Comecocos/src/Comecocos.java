import javax.swing.JFrame;


public class Comecocos extends JFrame {

	private static final long serialVersionUID = -336014495224502129L;

	public Comecocos() {
		super("Comecocos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(new Escenario());
		pack();
	}
	
	public static void main(String[] args) {
		new Comecocos().setVisible(true);
	}

}
