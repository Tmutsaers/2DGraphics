import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;


public class BouncingBall extends JPanel
{
	private JFrame frame;
	private int x = 0;
	private int y = 400;
	private boolean way = true;
	private int incrementX = 0;
	private int incrementY = 0;
	private Shape s1 = new Ellipse2D.Double(x,y,50,50);
	
	public static void main(String[] args)
	{
		new BouncingBall();
	}
	
	public BouncingBall()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Bouncing Ball");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(750,775);
		
		
		frame.add(this);
		
		Timer refresh = new Timer(1000/50,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
//				if(x == 750)
//				{
//					way = false;
//				}
//				if(x == 0)
//				{
//					way = true;
//				}
//				
//				if(way == false)
//				{
//					x-=5;
//				}
//				if(way == true)
//				{
//					x+=5;
//				}
				
//				if(x==725 || y == 725 || x> 750 || y > 750)
//				{
//					incrementX = (int)(Math.random() *5);
//					incrementY = (int)(Math.random() *5);
//					way = false;
//				}
				if(s1.getBounds().getMaxX() == 750 || s1.getBounds().getMaxY() == 750 || s1.getBounds().getMaxX() > 750 || s1.getBounds().getMaxY() > 750)
				{
					incrementX = (int)(Math.random()*5);
					incrementY = (int)(Math.random()*5);
					way = false;
				}
				if(x==0 || y ==0 || x< 0 || y < 0)
				{
					incrementX = (int)(Math.random() *5);
					incrementY = (int)(Math.random() *5);
					way = true;
				}
				if(way == false)
				{
					x-= incrementX;
					y-= incrementY;
				}
				if(way == true)
				{
					x+= incrementX;
					y+= incrementY;
				}
				
				repaint();
			}
		});
		refresh.start();
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		s1 = new Ellipse2D.Double(x,y,50,50);
		g2.draw(s1);
		
		
	}
	
}
