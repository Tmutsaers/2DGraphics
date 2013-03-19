import java.awt.*;
import java.awt.Event.*;
import javax.swing.*;
import java.awt.geom.*;

public class Square extends JPanel
{
	private JFrame frame;
	
	public static void main(String[] args)
	{
		new Square();
	}
	
	public Square()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Square");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		frame.add(this);
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Shape s1 = new Rectangle2D.Double(-100, -100, 200, 100);
		AffineTransform form = new AffineTransform();
		form.scale(0.5,1);
		form.rotate(45*(Math.PI/180));
		s1 = form.createTransformedShape(s1);
		g2.translate(200, 200);
		g2.draw(s1);
	}
}
