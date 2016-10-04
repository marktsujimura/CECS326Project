import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Node implements Drawable{
	
	private Point loc;
	private int state;

	public Node(int x, int y){
		state = 0;
		loc = new Point(x, y);
	}

	@Override
	public void draw(Graphics g, Panel p) {
		if(state == 0){
			g.setColor(Color.GRAY);
		}
		if(state == 1){
			g.setColor(Color.GREEN);
		}
		if(state == 2){
			g.setColor(Color.RED);
		}
		
		g.fillOval(loc.x - 25, loc.y - 25, 50, 50);
		
	}

}
