import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.*;

import javax.swing.*;

public class ClippingShape extends JPanel
{
	private JFrame frame;
	
	public static void main(String[] args)
	{
		new ClippingShape();
	}
	
	public ClippingShape()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Clipping Shape");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(750,750);
		
		frame.add(this);
		
		
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(100,100);
		g2.setColor(Color.DARK_GRAY);
		g2.setClip(new Rectangle(-100,-100,400,400));
		Shape s1 = new Ellipse2D.Double(10,0,200,200);
		g2.setColor(Color.WHITE);
		g2.setClip(s1);
		
		String msg = "Java 2D";
		Font font = new Font("Serif", Font.BOLD, 100);
		g2.setFont(font);
		
		FontRenderContext frc = g2.getFontRenderContext();		
		GlyphVector gv = font.createGlyphVector(frc, msg);
		Shape glyph = gv.getOutline(-10,100);
		g2.fill(glyph);
	}
	
	
}
