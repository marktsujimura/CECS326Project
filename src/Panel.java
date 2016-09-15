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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable {

	private String answer = "";
	private String problem = "";
	private String input = "";
	private ProblemGenerator p;
	private boolean solved = true;

	public Panel() {
		p = new ProblemGenerator();
		setBounds(100, 100, 1380, 820);
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
			String[] generated = p.generate(0);
			answer = generated[0];
			problem = generated[1];
			solved = false;
		}
		
		if(input.equals(answer)){
			solved = true;
			input = "";
		}
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		g.drawString(problem, 100, 100);
		g.drawString(input, 100, 200);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point loc = e.getPoint();

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
