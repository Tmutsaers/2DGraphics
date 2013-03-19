
import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

import javax.swing.JFrame;

public class Graphicstwo 
{
	private JFrame frame;
	private JPanel pane;
	public Graphicstwo()
	{
		makeFrame();
		paint(pane.getGraphics());
	}
	
	public static void main(String args[])
	{
		new Graphicstwo();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D gr = (Graphics2D) g;
		gr.drawString("Hello world", 30,50);
		gr.draw(new Ellipse2D.Double(100,300,100,80));
		gr.draw(new Arc2D.Float(0,0,100,200,0,45, 0));
//		gr.drawLine(1,3, -2, 1);
//		gr.drawLine(0,-2,0,0);
//		gr.drawLine(100,100,200,200);
//		gr.draw(new Rectangle(100,100,100,100));
//		frame.setVisible(false);
//		frame.setVisible(true);
	}
	
	public void makeFrame()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		pane = new JPanel();
		frame.add(pane);
		frame.setVisible(true);
	}
}
