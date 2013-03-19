import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;


public class Ellip extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.draw(new Ellipse2D.Double(100, 300, 80, 100));
//		g2.draw(new Arc2D.Float(0,0,100,200,0,45));
	}
}
