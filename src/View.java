import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {

	private static final long serialVersionUID = 1859028356359840318L;
	private JLabel[][] labels;
	public Model model;
	private boolean play;
	private JPanel panel;
	private boolean firstPlay;
	private ImageIcon[] icon = new ImageIcon[8];
	private int compareNumber;
	private int previousX;
	private int previousY;
	private List<JLabel> jList = new ArrayList<>();
	private Timer timer;

	/**
	 * Konstruktor des Views
	 * 
	 * @param x
	 * @param y
	 * @param model
	 */
	public View(int x, int y, Model model) {
		setTitle("Memory");
		this.model = model;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(x, y);
		this.setVisible(true);
		this.labels = new JLabel[4][4];
		this.panel = new JPanel();
		this.panel.setBackground(Color.BLACK);

		this.add(panel);
		this.firstPlay = true;

		defineLabels();
		addLabels();
		setIcons();
		startOperation();

	}

	/**
	 * in der setIcon-Methode werden die einzelnen Bilder(Icons) in
	 */
	public void setIcons() {
		this.icon[0] = new ImageIcon("images\\0.jpg");
		this.icon[1] = new ImageIcon("images\\1.jpg");
		this.icon[2] = new ImageIcon("images\\2.jpg");
		this.icon[3] = new ImageIcon("images\\3.png");
		this.icon[4] = new ImageIcon("images\\4.jpg");
		this.icon[5] = new ImageIcon("images\\5.jpg");
		this.icon[6] = new ImageIcon("images\\6.png");
		this.icon[7] = new ImageIcon("images\\7.png");

	}

	public void startOperation() {
		this.play = true;
		if (this.firstPlay == false) {
			this.model.setNumbers(this.model.mashUp(new int[this.model.getY()][this.model.getX()]));
			defineLabels();
			addLabels();
		}
		timer = new Timer(100, trigger -> {
			if (this.model.isFinished()) {
				JOptionPane.showMessageDialog(null, "Gewonnen");
				timer.stop();
			}
		});
		timer.start();
	}

	private void addLabels() {
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				this.panel.add(this.labels[i][j], BorderLayout.SOUTH);

			}
		}
	}

	private void defineLabels() {
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				this.labels[i][j] = new JLabel();
				this.labels[i][j].setFont(new Font("Arial Bold", Font.PLAIN, 22));
				this.labels[i][j].setVisible(true);
				this.labels[i][j].setBorder(new EmptyBorder(25, 25, 25, 25));
				this.labels[i][j].setBackground(Color.WHITE);
				this.labels[i][j].setForeground(Color.black);
				this.labels[i][j].setOpaque(true);
				this.labels[i][j].setSize(100, 100);
				this.labels[i][j].setMaximumSize(new Dimension(10, 10));
				this.labels[i][j].setVerticalAlignment(SwingConstants.CENTER);
				this.labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				this.labels[i][j].setIcon(null);
				this.labels[i][j].addMouseListener(new Controller(this, this.model, i, j));

			}
		}
		GridLayout gbLayout = new GridLayout(4, 4);
		gbLayout.setHgap(20);
		gbLayout.setVgap(20);
		panel.setLayout(gbLayout);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.add(panel, BorderLayout.CENTER);

	}

	/**
	 * wählt das Bild für den 1.Klick aus
	 * 
	 * @param x
	 * @param y
	 */
	public void setImage1(int x, int y) {
		this.previousX = x;
		this.previousY = y;
		this.compareNumber = this.model.getNumber(x, y);
		this.labels[x][y].setIcon(this.icon[compareNumber]);

	}

	/**
	 * wählt das Bild für den 2.Klick aus
	 * 
	 * @param x
	 * @param y
	 * @throws InterruptedException
	 */
	public boolean setImage2(int x, int y) throws InterruptedException {
		if (!(this.previousX == x) && !(this.previousY == y)) {
			int dummy = this.model.getNumber(x, y);
			if (this.compareNumber == dummy) {
				this.model.incrementCounter();
				this.labels[x][y].setIcon(this.icon[dummy]);

			} else {
				this.labels[x][y].setIcon(this.icon[dummy]);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});

				System.out.println("Löschen");

			}
			return true;

		}
		return false;

	}
	
	public void deleteImages(int x, int y) {

		this.labels[this.previousX][this.previousY].setIcon(null);
		this.labels[x][y].setIcon(null);
	}
	public boolean getPlay() {
		return this.play;
	}

}
