import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.GrayFilter;

public class Achievement implements Drawable{
	
	private BufferedImage image;
	private Image imageGS;
	private boolean achieved;
	private Point loc;
	
	public Achievement(BufferedImage im, Point p){
		image = im;
		GrayFilter gf = new GrayFilter(true, 100);
		imageGS = gf.createDisabledImage(image);
		achieved = false;
		loc = p;
	}
	
	public void setAchieved(){
		achieved = true;
	}
	
	@Override
	public void draw(Graphics g, Panel p) {
		if(achieved){
			g.drawImage(image, loc.x, loc.y, p);
		}
		else{
			g.drawImage(imageGS, loc.x, loc.y, p);
		}
	}

}
