import javax.swing.JFrame;

public class Frame extends JFrame{
	
	private Panel panel;
	
	public Frame(){
		panel = new Panel();
		Thread t = new Thread(panel);
		setTitle("Bitcoin Defender");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
		setBounds(100, 100, 1380, 820);
		getContentPane().add(panel);
		t.start();
	}
	
	public static void main(String[] args){
		new Frame();
	}
}
