import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;

public class Spinning
{
	private JFrame frame;
	private JPanel pane;
	private Timer time;
	private JPanel plate = new vo();
	private boolean spin = false;
	
	public static void main(String[] args)
	{
		new Spinning();
	}
	
	public Spinning()
	{
		makeFrame();
		time.start();
		
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Spinning square");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(750,750);
		this.pane = new vo();
		frame.add(pane);
		
		time = new Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Test");
				time.stop();
				pane.setVisible(false);
				frame.remove(pane);
				if(spin == false)
				{
					pane = new vo();
					spin = true;
				}
				else
				{
					pane = new vo2();
					spin = false;
				}
				frame.add(pane);
				pane.setVisible(true);
				time.restart();
			}
		});
		
		
		frame.setVisible(true);
		
	}
	
}

class vo extends JPanel
{
	private Graphics2D g2;
	private AffineTransform form;
	private Shape square;
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g2 = (Graphics2D)g;
		g2.translate(200, 200);
		square = new Rectangle2D.Double(0, 0, 100, 100);
//		g2.draw(square);
		form = new AffineTransform();
		form.rotate(90 * (Math.PI/180));
		square = form.createTransformedShape(square);
		g2.draw(square);
	}
	
}

class vo2 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200,200);
		Shape square = new Rectangle2D.Double(0,0,100,100);
		AffineTransform form = new AffineTransform();
		form.rotate(180*(Math.PI/180));
		square  = form.createTransformedShape(square);
		g2.draw(square);
	}
}

class vo3 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200, 200);
		Shape square = new Rectangle2D.Double(0,0,100,100);
		AffineTransform form = new AffineTransform();
		while (true)
		{
			int i = 0;
			form.rotate((i+10)*(Math.PI/180));
			square = form.createTransformedShape(square);
			g2.draw(square);
		}
	}
}


