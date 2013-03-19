import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

public class Scaling extends JPanel
{
	private JFrame frame;
	
	public static void main(String[] args)
	{
		new Scaling();
	}
	
	public Scaling()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Scaling");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		
		
		frame.add(this);
		
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(0,0);
		Shape s1 = new Rectangle2D.Double(0, 0, 100, 100);
		
		AffineTransform tx = new AffineTransform();
		tx.scale(3, 3);
		s1 = tx.createTransformedShape(s1);
		g2.setColor(Color.GREEN);
		g2.fill(s1);
	}
}
