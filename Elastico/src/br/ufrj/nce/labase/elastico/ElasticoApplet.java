package br.ufrj.nce.labase.elastico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
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

import br.ufrj.nce.labase.phidias.toolkit.GameBoard;
import br.ufrj.nce.labase.phidias.toolkit.filter.GraphicFilter;
import br.ufrj.nce.labase.phidias.toolkit.filter.HighLightGraphicFilter;
import br.ufrj.nce.labase.phidias.toolkit.filter.ThumbnailGraphicFilter;
import br.ufrj.nce.labase.phidias.toolkit.graphic.GraphicPrintElement;
import br.ufrj.nce.labase.phidias.util.Images;
import br.ufrj.nce.labase.phidias.view.player.GameStartTimer;

public class ElasticoApplet extends GameBoard {

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
			changePhase(intern_phase);
		}
	}

	private static int DISTANCIA_PECA;
	private static int ENCAIXE_X_MAX;

	private static int ENCAIXE_X_MIN;
	private static int ENCAIXE_Y_MAX;

	private static int ENCAIXE_Y_MIN;
	private static int HEIGHT;
	private static int HEIGHT_CARTA;
	private static int HEIGHT_PAINEL;

	private static int LIMITE_INFERIOR_FILEIRA_CARTA;
	private static int LIMITE_LATERAL_FILEIRA_CARTA;
	private static int PAINEL_X_MAX;
	private static int PAINEL_X_MIN = 0;

	private static int PAINEL_Y_MAX;
	private static int PAINEL_Y_MIN;

	private static int TABULEIRO_X_MAX;
	private static int TABULEIRO_X_MIN;

	private static int TABULEIRO_Y_MAX;
	private static int TABULEIRO_Y_MIN;

	private static int TAMANHO_PINO;
	private static int WIDTH;

	private static int WIDTH_CARTA;
	private static int WIDTH_PAINEL;

	private boolean phaseOneCalled;

	static {
		PAINEL_X_MIN = 0;
		WIDTH_PAINEL = 60;
		HEIGHT_PAINEL = 15;

		DISTANCIA_PECA = 120;
		TAMANHO_PINO = 30;

		WIDTH_CARTA = 60;
		HEIGHT_CARTA = 80;

		Dimension dimension_screen = Toolkit.getDefaultToolkit().getScreenSize();

		WIDTH = dimension_screen.width;
		HEIGHT = dimension_screen.height - 20;

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

	private Elastico elasticoCorrente;

	private List<Elastico> elasticos = new ArrayList<Elastico>();

	private Color[] elasticosColor = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.CYAN };

	private String[] elasticosDescription = { "Vermelho", "Azul", "Verde", "Amarelo", "Laranja", "Azul claro" };

	private List<PaletaCorElastico> painel = new ArrayList<PaletaCorElastico>();

	// global variables for off-screen rendering
	private Timer phaseTimer;

	private PinoEstatico pinoEstatico;

	private List<Pino> pinos = new ArrayList<Pino>();

	/**
	 * Construtor que inicia o jogo e todos os seus recursos necessários.
	 */
	public ElasticoApplet() {
		super();
		setBackground(Color.DARK_GRAY);
	}

	@Override
	public String getImagesPackageName() {

		return "br.ufrj.nce.labase.elastico.imagens";
	}

	@Override
	public int getScreenHeight() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}

	@Override
	public int getScreenWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
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
		int idimagem = 0;
		List<GraphicFilter> listFilter = new ArrayList<GraphicFilter>();
		listFilter.add(new HighLightGraphicFilter());
		listFilter.add(new ThumbnailGraphicFilter());

		for (int x = 0; (x + WIDTH_CARTA) < WIDTH; x += WIDTH_CARTA) {
			image = Images.getBufferedImage(this.getImageName(cartas.get(idimagem) + " [80x60].GIF"));
			this.addSprite(new Carta(this.spriteManager, new Point2D.Double(x, 0), image, listFilter));

			image = Images.getBufferedImage(this.getImageName(cartas.get(idimagem + 1) + " [80x60].GIF"));
			this.addSprite(new Carta(this.spriteManager, new Point2D.Double(x, LIMITE_INFERIOR_FILEIRA_CARTA), image, listFilter));
			idimagem += 2;
		}

		// Inicializa lateral esquerda e direita das cartas
		int y;
		for (y = HEIGHT_CARTA; (y + HEIGHT_CARTA) < HEIGHT - (HEIGHT_CARTA + 90); y += HEIGHT_CARTA) {
			image = Images.getBufferedImage(this.getImageName(cartas.get(idimagem) + " [80x60].GIF"));
			this.addSprite(new Carta(this.spriteManager, new Point2D.Double(0, y), image, listFilter));

			image = Images.getBufferedImage(this.getImageName(cartas.get(idimagem + 1) + " [80x60].GIF"));
			this.addSprite(new Carta(this.spriteManager, new Point2D.Double(LIMITE_LATERAL_FILEIRA_CARTA, y), image, listFilter));

			idimagem += 2;
		}

		// calcula os valores do painel esquerdo em funcao da disposicao das
		// cartas
		int espaco_reservado = LIMITE_INFERIOR_FILEIRA_CARTA - y;
		HEIGHT_PAINEL = espaco_reservado / 6;
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

		// Inicializa a paleta de elasticos
		PaletaCorElastico paleta = null;
		for (int i = 0; i < this.elasticosColor.length; i++) {
			paleta = new PaletaCorElastico(new Ellipse2D.Double(PAINEL_X_MAX, PAINEL_Y_MAX, WIDTH_PAINEL, HEIGHT_PAINEL), this.elasticosColor[i], this.elasticosDescription[i]);
			painel.add(paleta);
			PAINEL_Y_MAX = PAINEL_Y_MAX + HEIGHT_PAINEL;
		}
	}

	private void inicializaPinoEstatico() {
		this.pinoEstatico = new PinoEstatico(new Point2D.Double(PAINEL_X_MIN, PAINEL_Y_MIN + 30), Images.getBufferedImage(this.getImageName("DSC02667 [80x60].jpg")));
	}

	/**
	 * Inicializa as estruturas de dados de Pinos
	 */
	private void inicializaPinos() {
		Pino pino;
		for (int y = ENCAIXE_Y_MIN; y < ENCAIXE_Y_MAX + TAMANHO_PINO;) {
			for (int x = ENCAIXE_X_MIN; x < ENCAIXE_X_MAX + TAMANHO_PINO; x += DISTANCIA_PECA) {
				pino = new Pino(Color.BLACK, new Ellipse2D.Double(x, y, TAMANHO_PINO, TAMANHO_PINO));
				this.pinos.add(pino);
				this.addGraphicPrintElement(pino);
			}
			y += DISTANCIA_PECA;
		}
	}

	@Override
	public void initGame() {
		try {

			setSize(new Dimension(WIDTH, HEIGHT));
			this.spriteManager.setSpriteHoverEnabled(true);
			this.changePhase(PHASE_ONE);
			this.inicializaPinos();
			this.inicializaCartas();
			this.inicializaPinoEstatico();
			this.inicializaPaletaCorElastico();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void mouseReleased(MouseEvent e) {

		// Trata os eventos de mouse released do gameboard
		super.mouseReleased(e);

		// Trata os eventos 2D do Elastico corrente
		Ellipse2D ellipse;
		if (this.elasticoCorrente == null) {
			for (PaletaCorElastico paletaCor : painel) {
				if (paletaCor.getBody().contains(e.getX(), e.getY()) && !paletaCor.isDisabled()) {
					// Inicializa o elastico corrent
					this.elasticoCorrente = new Elastico(paletaCor.getColor());
					this.elasticoCorrente.setColor(paletaCor.getColor());
					this.elasticos.add(this.elasticoCorrente);
					paletaCor.setDisabled(true);
					JOptionPane.showMessageDialog(this, "Elástico " + paletaCor.getColorDescription() + " escolhido!");
				}
			}
		}

		// Trata os eventos de Pino Estatico
		if (this.pinoEstatico.getBody().contains(e.getX(), e.getY())) {
			if (!this.pinoEstatico.isSelected())
				this.pinoEstatico.setSelected(true);
			else
				this.pinoEstatico.setSelected(false);
		}

		// Trata a inserção de pinos no tabuleiro
		if (this.pinoEstatico.isSelected()) {
			for (Pino pino : pinos) {
				ellipse = (Ellipse2D) pino.getBody();
				// Verifica se o ponto clicado é de algum pino e habilita o
				// pino
				// para o elastico
				if (ellipse.getBounds2D().contains(e.getX(), e.getY())) {
					if (!pino.isSelected()) {
						if (!pino.isEnabled()) {
							pino.setEnabled(true);
							pino.setColor(Color.GRAY);
						} else if (this.elasticoCorrente == null) {
							pino.setEnabled(false);
							pino.setColor(Color.BLACK);
						}
					}
				}
			}
		}

		// Trata os eventos de inserção do elástico no tabuleiro.
		if (this.elasticoCorrente != null) {

			for (Pino pino : pinos) {
				ellipse = (Ellipse2D) pino.getBody();
				// Verifica se o ponto clicado é de algum pino
				if (pino.isEnabled() && ellipse.getBounds2D().contains(e.getX(), e.getY())) {

					// Adiciono a coordenada do elastico.
					this.elasticoCorrente.addCoordenada(new Point((int) ellipse.getCenterX(), (int) ellipse.getCenterY()));

					if (!pino.isSelected()) {
						// Pinta o pino da cor do elastico corrente
						pino.setColor(this.elasticoCorrente.getColor());
						pino.setSelected(true);
					} else if (!this.elasticoCorrente.isFinished()) {

						this.elasticoCorrente.removeCoordenada(new Point((int) ellipse.getCenterX(), (int) ellipse.getCenterY()));

						// Pinta o pino da cor do elastico corrente
						pino.setColor(Color.GRAY);
						pino.setSelected(false);
					} else
					// Apos a adição da coordenada verifico se o mesmo esta
					// concluido e anulo o elastico corrente.
					if (this.elasticoCorrente.isFinished()) {
						this.elasticoCorrente = null;
					}

					break;
				}
			}
		}
	}

	public void paintGameBoard(Graphics g) {
		if (this.getPhase().equals(PHASE_ONE))
			this.paintPhaseOne(g);
		else if (this.getPhase().equals(PHASE_TWO))
			this.paintPhaseTwo(g);

	}

	public void paintPhaseOne(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		BufferedImage im = Images.getBufferedImage(this.getImageName("collage.jpg"));
		g2d.drawImage(im, (WIDTH - im.getWidth()) / 2, (HEIGHT - im.getHeight()) / 2, im.getWidth(), im.getHeight(), this);
		g2d.setFont(new Font("Times New Roman", 1, 50));
		g2d.drawString("Jogo dos Elásticos", (((WIDTH - im.getWidth()) / 2) + im.getWidth()) / 2 - 40, ((HEIGHT - im.getHeight()) / 2) - 20);
	}

	public void paintPhaseTwo(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// Geração de pinos
		for (GraphicPrintElement pino : this.pinos) {
			pino.print(g2d);
		}

		// Pinta o Pino estatico
		this.pinoEstatico.print(g2d);

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

	@Override
	public void handlePhaseFive() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePhaseFour() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePhaseOne() {
		System.out.println("Mudança Fase 1");

		if (!phaseOneCalled) {
			phaseOneCalled = true;
			// Mantém a fase 1 por 5 segundos para a criança ver as peças do
			// cenário.
			this.phaseTimer = new Timer(5000, new AutomaticChangePhaseStartTimer(PHASE_TWO));
			this.phaseTimer.setRepeats(false);
			this.phaseTimer.start();
		}
	}

	@Override
	public void handlePhaseSeven() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePhaseSix() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePhaseThree() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePhaseTwo() {
		// TODO Auto-generated method stub
		System.out.println("Mudança Fase 2");
	}

}
