import java.awt.Color;
import java.awt.Graphics;

public class Grid implements Drawable{
	
	private final int dimension = 5;
	private Node[][] grid;
	
	public Grid(){
		grid = new Node[dimension][dimension];
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){
				grid[i][j] = new Node(500 + 100 * i, 250 + 100 * j);
			}
		}
	}

	@Override
	public void draw(Graphics g, Panel p) {
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){
				grid[i][j].draw(g, p);
				g.setColor(Color.GRAY);
				if(i < dimension - 1){
					g.fillRect(500 + 100 * i, 250 + 100 * j, 75, 5);
				}
				if(j < dimension - 1){
					g.fillRect(500 + 100 * i, 250 + 100 * j, 5, 75);
				}
			}
		}
	}

}
