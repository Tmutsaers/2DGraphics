import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class RotateString extends JPanel
{
	private JFrame frame;
	
	public static void main(String[] args)
	{
		new RotateString();
	}
	
	public RotateString()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("RotateString");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		frame.add(this);
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		AffineTransform tx = new AffineTransform();
		tx.rotate(45*(Math.PI/180));
		g2.transform(tx);
		
		Font font = new Font("Serif", Font.BOLD, 50);
		g2.setFont(font);
		
		g2.translate(250, 250);
		
		g2.drawString("Test", 0, 0);
	}
}
