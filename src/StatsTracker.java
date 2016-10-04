import java.awt.Font;
import java.awt.Graphics;

public class StatsTracker implements Drawable{
	
	private int solved;
	private int bitcoins;
	
	public StatsTracker(){
		solved = -1;
		bitcoins = -1;
	}
	
	public void increaseSolved(){
		solved++;
	}
	
	public void increaseBitcoins(){
		bitcoins++;
	}
	
	public int getSolved(){
		return solved;
	}

	@Override
	public void draw(Graphics g, Panel p) {
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("Problems solved: " + solved, 450, 120);
		g.drawString("Total bitcoins: " + bitcoins, 450, 170);
		g.drawString("Other stuff...", 450, 220);
	}

}
