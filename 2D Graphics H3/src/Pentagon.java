import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import javax.swing.*;
import javax.swing.border.StrokeBorder;

public class Pentagon extends JPanel
{
	private static final int CAP_BUTT = 0;
	private static final int JOIN_ROUND = 0;
	private JFrame frame;
	
	public static void main(String[] args)
	{
		new Pentagon();
	}
	
	public Pentagon()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame  = new JFrame("Pentagon");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		frame.add(this);
		
		
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(0.5,1.0);
		path.lineTo(-0.5,1.0);
		path.lineTo(-1.0,-0.25);
		path.lineTo(0.0,-1.0);
		path.lineTo(1.0,-0.25);
		path.lineTo(0.5, 1.0);
		path.closePath();
		
		
		g2.setStroke(new BasicStroke(20, CAP_BUTT, JOIN_ROUND));
		AffineTransform tr = new AffineTransform();
		tr.setToScale(100,100);
		g2.translate(250,250);
		Shape sh = tr.createTransformedShape(path);
		g2.draw(sh);
	}
}
