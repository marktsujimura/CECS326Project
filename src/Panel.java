import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable {

	private String answer = "";
	private String problem = "";
	private String input = "";
	private ProblemGenerator p;
	private boolean solved = true;
	private int bitcoins = -1;
	private int state = 0;
	
	private Grid grid;
	private StatsTracker stats;
	private ArrayList<Achievement> achievements;

	public Panel(){
		p = new ProblemGenerator();
		grid = new Grid();
		stats = new StatsTracker();
		achievements = new ArrayList<Achievement>();
		
		try {
			achievements.add(new Achievement(ImageIO.read(new File("seemsGood.png")), new Point(450, 100)));
			achievements.add(new Achievement(ImageIO.read(new File("pogChamp.png")), new Point(450, 250)));
			achievements.add(new Achievement(ImageIO.read(new File("feelsBadMan.png")), new Point(450, 400)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBounds(100, 100, 1400, 800);
		setBackground(Color.BLACK);

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(solved){
			bitcoins++;
			stats.increaseBitcoins();
			stats.increaseSolved();
			if(stats.getSolved() == 1){
				achievements.get(0).setAchieved();
			}
			else if(stats.getSolved() == 5){
				achievements.get(1).setAchieved();
			}
			String[] generated = p.generate(0);
			answer = generated[0];
			problem = generated[1];
			solved = false;
		}
		
		if(input.equals(answer)){
			solved = true;
			input = "";
		}
		
		g.setColor(Color.CYAN);
		
		// main borders
		g.fillRect(0, 0, 1380, 10);
		g.fillRect(0, 0, 10, 760);
		g.fillRect(0, 752, 1385, 10);
		g.fillRect(1375, 0, 10, 760);
		
		// frame dividers
		g.fillRect(400, 0, 10, 760);
		g.fillRect(1000, 0, 10, 760);
		
		// tab dividers
		g.fillRect(400, 50, 600, 10);
		g.fillRect(600, 0, 10, 50);
		g.fillRect(800, 0, 10, 50);
		
		// tab strings
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Map", 480, 40);
		g.drawString("Stats", 675, 40);
		g.drawString("Achievements", 820, 40);
		
		g.setFont(new Font("Arial", Font.BOLD, 36));
		g.drawString(problem, 100, 100);
		g.drawString(input, 100, 200);
		
		// what's displayed
		if(state == 0){
			g.drawString("Bitcoins: " +bitcoins, 610, 150);
			grid.draw(g, this);
		}
		else if(state == 1){
			stats.draw(g, this);
		}
		else if(state == 2){
			for(Achievement a : achievements){
				a.draw(g, this);
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point loc = e.getPoint();
		Rectangle tab1 = new Rectangle(410, 10, 190, 40);
		Rectangle tab2 = new Rectangle(610, 10, 190, 40);
		Rectangle tab3 = new Rectangle(810, 10, 190, 40);
		
		if(tab1.contains(loc)){
			state = 0;
		}
		else if(tab2.contains(loc)){
			state = 1;
		}
		else if(tab3.contains(loc)){
			state = 2;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_1 ||
				e.getKeyCode() == KeyEvent.VK_2 ||
				e.getKeyCode() == KeyEvent.VK_3 ||
				e.getKeyCode() == KeyEvent.VK_4 ||
				e.getKeyCode() == KeyEvent.VK_5 ||
				e.getKeyCode() == KeyEvent.VK_6 ||
				e.getKeyCode() == KeyEvent.VK_7 ||
				e.getKeyCode() == KeyEvent.VK_8 ||
				e.getKeyCode() == KeyEvent.VK_9 ||
				e.getKeyCode() == KeyEvent.VK_0 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD1 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD2 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD3 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD4 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD5 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD6 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD7 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD8 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD9 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD0 ||
				e.getKeyCode() == KeyEvent.VK_MINUS) {
			input = input + e.getKeyChar();
		}
		else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if(input.length() >= 1)
				input = input.substring(0, input.length() - 1);
		}

	}

	// unused listener methods
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
