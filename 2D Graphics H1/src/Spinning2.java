import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import javax.swing.*;

public class Spinning2 extends JPanel
{
	private JFrame frame;
	private int degree = 0;
	private Timer time;
	private Graphics g;
	
	public static void main(String[] args)
	{
		new Spinning2();
	}
	
	public Spinning2()
	{
		makeFrame();
		time.start();
	}
	
	protected void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Shape square = new Rectangle2D.Double(-50,-50,100,100);
		Shape square2 = new Rectangle2D.Double(-100,-100,50,50);
		Shape square3 = new Rectangle2D.Double(-150,-50,50,50);
		AffineTransform form = new AffineTransform();
		form.rotate((degree + 1)*(Math.PI/180));
		degree +=1;
		square = form.createTransformedShape(square);
		square2 = form.createTransformedShape(square2);
		square3 = form.createTransformedShape(square3);
		g2.translate(200,200);
		g2.draw(square);
//		g2.draw(square2);
//		g2.draw(square3);
		this.g = (Graphics)g2;
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Spinning Cube");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.add(this);
		
		time = new Timer(10,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				time.stop();
				paintComponent(g);
				frame.repaint();
				time.restart();
			}
		});
		
		frame.setVisible(true);
	}
}
