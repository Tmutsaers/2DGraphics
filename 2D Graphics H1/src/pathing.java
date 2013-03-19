import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.*;

import javax.swing.*;


public class pathing extends JPanel
{
	
	public pathing()
	{
		setPreferredSize(new Dimension(640,480));
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
//		path.moveTo(-3,-4);
//		path.lineTo(3,-4);
//		path.moveTo(3,-4);
//		path.lineTo(4, -2);
//		path.moveTo(4, -2);
//		path.lineTo(4, 2);
//		path.moveTo(4, 2);
//		path.lineTo(3, 4);
//		path.moveTo(3, 4);
//		path.lineTo(-3,4);
//		path.moveTo(-3,4);
//		path.lineTo(-4, 3);
//		path.moveTo(-4, 3);
//		path.lineTo(-4, -2);
//		path.moveTo(-4, -2);
//		path.lineTo(-3, -4);
//		path.moveTo(-3, -4);
		
//		path.moveTo(3.0f, 2.0f);
//		path.lineTo(6.0f,2.0f);
//		path.lineTo(7.0f,4.0f);
//		path.lineTo(7.0f, 6.0f);
//		path.lineTo(6.0f, 8.0f);
//		path.lineTo(4.0f, 8.0f);
//		path.lineTo(2.0f, 6.0f);
//		path.lineTo(2.0f, 4.0f);
//		path.lineTo(3.0f,2.0f);
		path.moveTo(0, -1);
		path.lineTo(1, -1);
		path.lineTo(1.5, -0.5);
		path.lineTo(1.5, 0.5);
		path.lineTo(1,1);
		path.lineTo(-0,1);
		path.lineTo(-0.5,0.5);
		path.lineTo(-0.5, -0.5);
		path.lineTo(0,-1);
		path.closePath();
		
		AffineTransform tr = new AffineTransform();
		tr.setToScale(100,100);
		g2.translate(250,250);
		Shape sh = tr.createTransformedShape(path);
		g2.draw(sh);
	}
}
