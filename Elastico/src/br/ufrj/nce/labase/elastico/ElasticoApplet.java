package br.ufrj.nce.labase.elastico;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

import br.ufrj.nce.labase.criaconto.images.Images;

public class ElasticoApplet extends javax.swing.JApplet implements MouseInputListener {

	// global variables for off-screen rendering
	private Graphics dbg;
	private Image dbImage = null;

	private static int WIDTH;
	private static int HEIGHT;

	private static int TABULEIRO_X_MIN;
	private static int TABULEIRO_Y_MIN;
	private static int TABULEIRO_X_MAX;
	private static int TABULEIRO_Y_MAX;

	private static int ENCAIXE_X_MIN;
	private static int ENCAIXE_Y_MIN;
	private static int ENCAIXE_X_MAX;
	private static int ENCAIXE_Y_MAX;

	private static int PAINEL_X_MIN = 0;
	private static int PAINEL_Y_MIN;

	private static int PAINEL_X_MAX;
	private static int PAINEL_Y_MAX;

	private static int WIDTH_PAINEL;
	private static int HEIGHT_PAINEL;

	private static int DISTANCIA_PECA;
	private static int TAMANHO_PINO;

	private static int WIDTH_CARTA;
	private static int HEIGHT_CARTA;

	private static int LIMITE_INFERIOR_FILEIRA_CARTA;
	private static int LIMITE_LATERAL_FILEIRA_CARTA;

	static {
		PAINEL_X_MIN = 0;
		WIDTH_PAINEL = 60;
		HEIGHT_PAINEL = 15;

		DISTANCIA_PECA = 125;
		TAMANHO_PINO = 30;

		WIDTH_CARTA = 60;
		HEIGHT_CARTA = 80;

		Dimension dimension_screen = Toolkit.getDefaultToolkit().getScreenSize();

		WIDTH = dimension_screen.width;
		HEIGHT = dimension_screen.height;

		TABULEIRO_X_MIN = 65;
		TABULEIRO_Y_MIN = 85;
		TABULEIRO_X_MAX = WIDTH - 150;
		TABULEIRO_Y_MAX = HEIGHT - 200;

		ENCAIXE_X_MIN = TABULEIRO_X_MIN + 30;
		ENCAIXE_Y_MIN = TABULEIRO_Y_MIN + 30;
		ENCAIXE_X_MAX = TABULEIRO_X_MAX;
		ENCAIXE_Y_MAX = TABULEIRO_Y_MAX;

		LIMITE_INFERIOR_FILEIRA_CARTA = HEIGHT - (HEIGHT_CARTA + 30);
		LIMITE_LATERAL_FILEIRA_CARTA = WIDTH - (WIDTH_CARTA + 20);
	}

	private Color[] elasticosCor = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.GRAY };

	private List<Pino> pinos = new ArrayList<Pino>();

	private List<Elastico> elasticos = new ArrayList<Elastico>();

	private List<Carta> cartas = new ArrayList<Carta>();

	private List<PaletaCorElastico> painel = new ArrayList<PaletaCorElastico>();

	private Elastico elasticoCorrente;

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		if (dbImage == null) {
			// create the buffer
			dbImage = createImage(WIDTH, HEIGHT);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			} else
				dbg = dbImage.getGraphics();
		}

		Graphics2D g2d = (Graphics2D) dbg;
		geraPainelElastico(g2d);
		geraCartas(g2d);
		geraTabuleiro(g2d);

		geraPinos(g2d);
		geraElasticos(g2d);
		paintScreen();
	}

	private void paintScreen()
	// actively render the buffer image to the screen
	{
		Graphics g;
		try {
			g = this.getGraphics(); // get the panel's graphic context
			if ((g != null) && (dbImage != null))
				g.drawImage(dbImage, 0, 0, null);

			Toolkit.getDefaultToolkit().sync(); // sync the display on
			// some systems
			g.dispose();
		} catch (Exception e) {
			System.out.println("Graphics context error: " + e);
		}
	} // end of paintScreen(

	private void geraTabuleiro(Graphics2D g2d) {
		Rectangle2D rec2D = new Rectangle2D.Double(TABULEIRO_X_MIN, TABULEIRO_Y_MIN, TABULEIRO_X_MAX, TABULEIRO_Y_MAX);

		g2d.setPaint(Color.DARK_GRAY);
		g2d.setStroke(new BasicStroke(10.0f));
		g2d.draw(rec2D);
		g2d.fill(rec2D);
	}

	private void geraPainelElastico(Graphics2D g2d) {
		for (PaletaCorElastico paleta : this.painel) {
			if (!paleta.isDisabled())
				g2d.setPaint(paleta.getColor());
			else
				g2d.setColor(Color.DARK_GRAY);
			g2d.draw(paleta.getPaletaCor());
			g2d.fill(paleta.getPaletaCor());
		}
	}

	private void geraPinos(Graphics2D g2d) {
		for (Pino pino : this.pinos) {
			g2d.setPaint(pino.getColor());
			g2d.draw(pino.getElemento());
			g2d.fill(pino.getElemento());
		}
	}

	private void geraElasticos(Graphics2D g2d) {
		for (Elastico elastico : this.elasticos) {
			if (elastico.isConcluido()) {
				g2d.setStroke(new BasicStroke(5.0f));
				g2d.setPaint(elastico.getColor());
				g2d.draw(elastico.getPoligono());
			} else {
				if (elastico.getCoordenadas() != null) {
					Point[] pontos = new Point[elastico.getCoordenadas().size()];
					elastico.getCoordenadas().toArray(pontos);
					for (int i = 0; i < pontos.length - 1; i++) {
						g2d.setStroke(new BasicStroke(5.0f));
						g2d.setPaint(elastico.getColor());
						g2d.draw(new Line2D.Double(pontos[i].getX(), pontos[i].getY(), pontos[i + 1].getX(), pontos[i + 1].getY()));

					}
				}
			}
		}
	}

	private void geraCartas(Graphics2D g2d) {
		for (Carta carta : this.cartas) {
			g2d.drawImage(carta.getImage(), (int) carta.getCoordenada().getX(), (int) carta.getCoordenada().getY(), null);
		}
	}

	private void inicializaPinos() {
		for (int y = ENCAIXE_Y_MIN; y < ENCAIXE_Y_MAX + TAMANHO_PINO;) {
			for (int x = ENCAIXE_X_MIN; x < ENCAIXE_X_MAX + TAMANHO_PINO; x += DISTANCIA_PECA) {
				Pino pino = new Pino();
				pino.setElemento(new Ellipse2D.Double(x, y, TAMANHO_PINO, TAMANHO_PINO));
				pino.setColor(Color.GRAY);
				pinos.add(pino);
			}
			y += DISTANCIA_PECA;
		}
	}

	private void inicializaCartas() {
		// Randomiza grupo de 64 cartas
		java.util.List<String> cartas = new ArrayList<String>();
		for (int i = 1; i < 65; i++) {
			cartas.add(String.valueOf(i));
		}

		Collections.shuffle(cartas);

		// Inicializa primeira e ultima fileira de cartas
		Image image = null;
		Carta carta = null;
		int idimagem = 0;
		for (int x = 0; (x + WIDTH_CARTA) < WIDTH; x += WIDTH_CARTA) {
			image = Images.createImage("/images/" + cartas.get(idimagem) + " [80x60].GIF");
			carta = new Carta(new Point(x, 0), image);
			this.cartas.add(carta);

			image = Images.createImage("/images/" + cartas.get(idimagem + 1) + " [80x60].GIF");
			carta = new Carta(new Point(x, LIMITE_INFERIOR_FILEIRA_CARTA), image);
			this.cartas.add(carta);

			idimagem += 2;
		}

		// Inicializa lateral esquerda e direita das cartas
		int y;
		for (y = HEIGHT_CARTA; (y + HEIGHT_CARTA) < HEIGHT - (HEIGHT_CARTA + 30); y += HEIGHT_CARTA) {
			image = Images.createImage("/images/" + cartas.get(idimagem) + " [80x60].GIF");
			carta = new Carta(new Point(0, y), image);
			this.cartas.add(carta);

			image = Images.createImage("/images/" + cartas.get(idimagem + 1) + " [80x60].GIF");

			carta = new Carta(new Point(LIMITE_LATERAL_FILEIRA_CARTA, y), image);
			this.cartas.add(carta);

			idimagem += 2;
		}

		// calcula os valores do painel esquerdo em funcao da disposicao das
		// cartas
		int espaco_reservado = LIMITE_INFERIOR_FILEIRA_CARTA - y;
		HEIGHT_PAINEL = espaco_reservado / 3;
		PAINEL_Y_MIN = y;

		// calcula os valores do painel direito em funcao da disposicao das
		// cartas
		PAINEL_Y_MAX = PAINEL_Y_MIN;
		PAINEL_X_MAX = LIMITE_LATERAL_FILEIRA_CARTA;
	}

	private void inicializaPaletaCorElastico() {

		// Inicializa a paleta esquerda
		PaletaCorElastico paleta = null;
		for (int i = 0; i < 3; i++) {
			paleta = new PaletaCorElastico(new Rectangle2D.Double(PAINEL_X_MIN, PAINEL_Y_MIN, WIDTH_PAINEL, HEIGHT_PAINEL), this.elasticosCor[i]);
			painel.add(paleta);
			PAINEL_Y_MIN = PAINEL_Y_MIN + HEIGHT_PAINEL;
		}

		// Inicializa a paleta direita
		for (int i = 3; i < this.elasticosCor.length; i++) {
			paleta = new PaletaCorElastico(new Rectangle2D.Double(PAINEL_X_MAX, PAINEL_Y_MAX, WIDTH_PAINEL, HEIGHT_PAINEL), this.elasticosCor[i]);
			painel.add(paleta);
			PAINEL_Y_MAX = PAINEL_Y_MAX + HEIGHT_PAINEL;
		}
	}

	/**
	 * Auto-generated main method to display this JApplet inside a new JFrame.
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Jogo dos Elásticos");
		ElasticoApplet applet = new ElasticoApplet();
		applet.init();
		applet.start();
		frame.getContentPane().add(applet);
		frame.setPreferredSize(applet.getSize());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public ElasticoApplet() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.inicializaPinos();

			this.inicializaCartas();

			this.inicializaPaletaCorElastico();

			setBackground(Color.DARK_GRAY);
			setSize(new Dimension(WIDTH, HEIGHT));
			addMouseListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent e) {
		Ellipse2D ellipse;

		if (this.elasticoCorrente == null) {
			for (PaletaCorElastico paletaCor : painel) {
				if (paletaCor.getPaletaCor().contains(e.getX(), e.getY()) && !paletaCor.isDisabled()) {
					// Inicializa o elastico corrente
					this.elasticoCorrente = new Elastico();
					this.elasticoCorrente.setColor(paletaCor.getColor());
					this.elasticos.add(this.elasticoCorrente);
					paletaCor.setDisabled(true);
					JOptionPane.showMessageDialog(this, "Elástico " + paletaCor.getColor() + " escolhido!");
				}
			}
		}

		for (Pino pino : pinos) {
			ellipse = pino.getElemento();
			// Verifica se o ponto clicado é de algum pino
			if (ellipse.getBounds2D().contains(e.getX(), e.getY())) {

				if (this.elasticoCorrente == null) {
					JOptionPane.showMessageDialog(this, "Escolha um elástico para montar o cenário!");
				}

				// Adiciono a coordenada do elastico.
				this.elasticoCorrente.addCoordenada(new Point((int) ellipse.getCenterX(), (int) ellipse.getCenterY()));

				// Pinta o pino da cor do elastico corrente
				pino.setColor(this.elasticoCorrente.getColor());

				// Apos a adição da coordenada verifico se o mesmo esta
				// concluido e anulo o elastico coorente.
				if (this.elasticoCorrente.isConcluido()) {
					this.elasticoCorrente = null;
				}

				this.repaint();

				break;
			}
		}

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
