import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;


public class Hello extends JPanel
{
	private JFrame frame;
	public static void main(String[] args)
	{
		new Hello();
	}
	
	public Hello()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Hello");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		
		frame.add(this);
		
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Font font = new Font("Arial", Font.BOLD,36);
		AffineTransform form = new AffineTransform();
//		form.rotate(Math.PI/1);
		form.setTransform(-1, 0, 0, 1, 0, 0);
		g2.translate(200, 200);
		g2.setFont(font.deriveFont(form));
		g2.drawString("Hello 2D", 0, 0);
	}
}
