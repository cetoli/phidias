package br.ufrj.nce.labase.elastico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import br.ufrj.nce.labase.criaconto.images.Images;
import br.ufrj.nce.labase.phidias.view.player.GameStartTimer;

public class ElasticoApplet extends javax.swing.JApplet implements Runnable, MouseListener, MouseMotionListener {

	// global variables for off-screen rendering
	private static final int PHASE_ONE = 1;
	private static final int PHASE_TWO = 2;

	private int phase;
	private Timer phaseTimer;

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

	private Color[] elasticosColor = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.CYAN };
	private String[] elasticosDescription = { "Vermelho", "Azul", "Verde", "Amarelo", "Laranja", "Azul claro" };

	private List<Pino> pinos = new ArrayList<Pino>();

	private List<Elastico> elasticos = new ArrayList<Elastico>();

	private List<PaletaCorElastico> painel = new ArrayList<PaletaCorElastico>();

	private Elastico elasticoCorrente;

	private SpriteManager spriteManager = new SpriteManager();

	/**
	 * Construtor que inicia o jogo e todos os seus recursos necessários.
	 */
	public ElasticoApplet() {
		super();
		this.initGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JApplet#update(java.awt.Graphics)
	 */
	public void update(Graphics g) {
		if (dbImage == null) {
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics();
		}

		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

		dbg.setColor(getForeground());
		paint(dbg);

		g.drawImage(dbImage, 0, 0, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		if (dbImage == null) {
			// create the buffer
			dbImage = createImage(WIDTH, HEIGHT);
			if (dbImage == null) {
				return;
			} else
				dbg = dbImage.getGraphics();
		}

		Graphics2D g2d = (Graphics2D) dbg;

		if (phase == PHASE_ONE) {
			this.phaseOne(g2d);
		} else if (phase == PHASE_TWO) {
			this.phaseTwo(g2d);
		}
	}

	/**
	 * Inicia Fase 1 do Jogo
	 * 
	 * @param g2d
	 */
	private void phaseOne(Graphics2D g2d) {
		BufferedImage im = Images.getBufferedImage("/images/collage.jpg");
		g2d.drawImage(im, (WIDTH - im.getWidth()) / 2, (HEIGHT - im.getHeight()) / 2, im.getWidth(), im.getHeight(), this);
		g2d.setFont(new Font("Times New Roman", 1, 50));
		g2d.drawString("Jogo dos Elásticos", (((WIDTH - im.getWidth()) / 2) + im.getWidth()) / 2 - 40, ((HEIGHT - im.getHeight()) / 2) - 20);

		// Mantém a fase 1 por 5 segundos para a criança ver as peças do
		// cenário.
		this.phaseTimer = new Timer(5000, new AutomaticChangePhaseStartTimer(PHASE_TWO));
		this.phaseTimer.start();
	}

	/**
	 * Inica Fase 2 do Jogo
	 * 
	 * @param g2d
	 */
	private void phaseTwo(Graphics2D g2d) {
		// Geração de pinos
		for (GraphicPrintElement pino : this.pinos) {
			pino.print(g2d);
		}

		// Geração so painel de Elasticos
		for (PaletaCorElastico paleta : this.painel) {
			paleta.print(g2d);
		}

		// Imprime sprites
		this.spriteManager.paintSprites(g2d, this);

		// Geração dos Elásticos
		for (Elastico elastico : this.elasticos) {
			elastico.print(g2d);
		}
	}

	/**
	 * Inicializa as estruturas de dados de Pinos
	 */
	private void inicializaPinos() {
		for (int y = ENCAIXE_Y_MIN; y < ENCAIXE_Y_MAX + TAMANHO_PINO;) {
			for (int x = ENCAIXE_X_MIN; x < ENCAIXE_X_MAX + TAMANHO_PINO; x += DISTANCIA_PECA) {
				Pino pino = new Pino();
				pino.setPinoBody(new Ellipse2D.Double(x, y, TAMANHO_PINO, TAMANHO_PINO));
				pino.setColor(Color.GRAY);
				pinos.add(pino);
			}
			y += DISTANCIA_PECA;
		}
	}

	/**
	 * Iniicaliza as estruturas de dados das cartas
	 */
	private void inicializaCartas() {
		// Randomiza grupo de 64 cartas
		java.util.List<String> cartas = new ArrayList<String>();
		for (int i = 1; i < 65; i++) {
			cartas.add(String.valueOf(i));
		}

		Collections.shuffle(cartas);

		// Inicializa primeira e ultima fileira de cartas
		BufferedImage image = null;
		Carta carta = null;
		int idimagem = 0;
		for (int x = 0; (x + WIDTH_CARTA) < WIDTH; x += WIDTH_CARTA) {
			image = Images.getBufferedImage("/images/" + cartas.get(idimagem) + " [80x60].GIF");
			carta = new Carta(this.spriteManager, new Point2D.Double(x, 0), image);
			this.spriteManager.addSprite(carta);

			image = Images.getBufferedImage("/images/" + cartas.get(idimagem + 1) + " [80x60].GIF");
			carta = new Carta(this.spriteManager, new Point2D.Double(x, LIMITE_INFERIOR_FILEIRA_CARTA), image);
			this.spriteManager.addSprite(carta);
			idimagem += 2;
		}

		// Inicializa lateral esquerda e direita das cartas
		int y;
		for (y = HEIGHT_CARTA; (y + HEIGHT_CARTA) < HEIGHT - (HEIGHT_CARTA + 30); y += HEIGHT_CARTA) {
			image = Images.getBufferedImage("/images/" + cartas.get(idimagem) + " [80x60].GIF");
			carta = new Carta(this.spriteManager, new Point2D.Double(0, y), image);
			this.spriteManager.addSprite(carta);

			image = Images.getBufferedImage("/images/" + cartas.get(idimagem + 1) + " [80x60].GIF");

			carta = new Carta(this.spriteManager, new Point2D.Double(LIMITE_LATERAL_FILEIRA_CARTA, y), image);
			this.spriteManager.addSprite(carta);

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

	/**
	 * Inicializa a paleta de elasticos.
	 */
	private void inicializaPaletaCorElastico() {

		// Inicializa a paleta esquerda
		PaletaCorElastico paleta = null;
		for (int i = 0; i < 3; i++) {
			paleta = new PaletaCorElastico(new Rectangle2D.Double(PAINEL_X_MIN, PAINEL_Y_MIN, WIDTH_PAINEL, HEIGHT_PAINEL), this.elasticosColor[i], this.elasticosDescription[i]);
			painel.add(paleta);
			PAINEL_Y_MIN = PAINEL_Y_MIN + HEIGHT_PAINEL;
		}

		// Inicializa a paleta direita
		for (int i = 3; i < this.elasticosColor.length; i++) {
			paleta = new PaletaCorElastico(new Rectangle2D.Double(PAINEL_X_MAX, PAINEL_Y_MAX, WIDTH_PAINEL, HEIGHT_PAINEL), this.elasticosColor[i], this.elasticosDescription[i]);
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

	private void initGame() {
		try {

			this.phase = PHASE_ONE;
			this.inicializaPinos();
			this.inicializaCartas();
			this.inicializaPaletaCorElastico();
			setBackground(Color.DARK_GRAY);

			setSize(new Dimension(WIDTH, HEIGHT));
			addMouseListener(this);
			addMouseMotionListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent e) {

		Ellipse2D ellipse;

		if (this.elasticoCorrente == null) {
			for (PaletaCorElastico paletaCor : painel) {
				if (paletaCor.getBody().contains(e.getX(), e.getY()) && !paletaCor.isDisabled()) {
					// Inicializa o elastico corrente
					this.elasticoCorrente = new Elastico();
					this.elasticoCorrente.setColor(paletaCor.getColor());
					this.elasticos.add(this.elasticoCorrente);
					paletaCor.setDisabled(true);
					JOptionPane.showMessageDialog(this, "Elástico " + paletaCor.getColorDescription() + " escolhido!");
				}
			}
		}

		for (Pino pino : pinos) {
			ellipse = (Ellipse2D) pino.getBody();
			// Verifica se o ponto clicado é de algum pino
			if (ellipse.getBounds2D().contains(e.getX(), e.getY())) {

				if (this.elasticoCorrente == null) {
					JOptionPane.showMessageDialog(this, "Escolha um elástico para montar o cenário!");
					return;
				}

				// Adiciono a coordenada do elastico.
				this.elasticoCorrente.addCoordenada(new Point((int) ellipse.getCenterX(), (int) ellipse.getCenterY()));

				// Pinta o pino da cor do elastico corrente
				pino.setColor(this.elasticoCorrente.getColor());
				pino.setSelected(true);

				// Apos a adição da coordenada verifico se o mesmo esta
				// concluido e anulo o elastico coorente.
				if (this.elasticoCorrente.isFinished()) {
					this.elasticoCorrente = null;
				}

				break;
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		this.spriteManager.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		this.spriteManager.mouseReleased(e);
	}

	public void mouseDragged(MouseEvent e) {
		this.spriteManager.mouseDragged(e);
	}

	public void mouseMoved(MouseEvent e) {
	}

	/*
	 * Este método faz a chamada ao repaint responsavel por montar a interface
	 * em 20 em 20 milesimos de segundo através de uma thread.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// Baixa prioridade para o Thread
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		// Roda um loop infinito
		while (true) {
			repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException ex) {
				// FAZ NADA!!
			}

			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#start()
	 */
	public void start() {
		super.start();

		Thread th = new Thread(this);
		th.start();
	}

	/**
	 * Classe interna que muda fase automaticamente.
	 * 
	 * @author Owner
	 */
	private class AutomaticChangePhaseStartTimer extends GameStartTimer {
		private int intern_phase;

		public AutomaticChangePhaseStartTimer(int phase) {
			this.intern_phase = phase;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			phase = intern_phase;
		}
	}
}
