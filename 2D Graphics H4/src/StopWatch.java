import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

public class StopWatch extends JPanel implements MouseListener
{
	private JFrame frame;
	private Shape watch = new Ellipse2D.Double(-200,-200,400,400);
	private Shape miliSecWijzer;
	private Shape secWijzer;
	private Timer milisec;
	private Timer sec;
	private int graden = 0;
	private boolean mode = true;   										//true is start, false is stop
	
	public static void main(String[] args)
	{
		new StopWatch();
	}
	
	public StopWatch()
	{
		createSecWijzer();
		createSecWijzer2();
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Analog Stopwatch");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		
		frame.add(this);
		
		milisec = new Timer(1,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				rotateMiliSec();
				graden++;
				if(graden == 360)
				{
					rotateSec();
					graden = 0;
				}
				repaint();
			}
		});
//		sec = new Timer(1000,new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				rotateSec();
//				repaint();
//			}
//		});
		milisec.start();
		addMouseListener(this);
		
		
		frame.setVisible(true);
	}
	
	
	
	public void rotateMiliSec()
	{
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(1),0,0);
		miliSecWijzer = tx.createTransformedShape(miliSecWijzer);
		
	}
	
	public void rotateSec()
	{
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(6),0,0);
		secWijzer = tx.createTransformedShape(secWijzer);
	}
	public void createSecWijzer()
	{
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(0.0,0.0);
		path.lineTo(0.0, -1.0);
		path.closePath();
		
		AffineTransform form = new AffineTransform();
		form.setToScale(1, 150);
		miliSecWijzer = form.createTransformedShape(path);
	}
	
	public void createSecWijzer2()
	{
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(0.0, 0.0);
		path.lineTo(0.0, -1.0);
		path.closePath();
		
		AffineTransform form = new AffineTransform();
		form.setToScale(1,100);
		secWijzer = form.createTransformedShape(path);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(400, 400);
		g2.draw(watch);
		g2.draw(miliSecWijzer);
		g2.draw(secWijzer);

		
	}

	@Override
	public void mouseClicked(MouseEvent event) 
	{
//		if(event.getButton() == event.BUTTON1 && event.getClickCount() == 2)
//		{
//			frame.setTitle("Analog Stopwatch: Reset");
//			createSecWijzer();
//			createSecWijzer2();
//		}
//		if(event.getButton() == event.BUTTON3 && event.getClickCount() == 1)
//		{
//			milisec.stop();
//			frame.setTitle("Analog Stopwatch: Stoppped");
//		}
//		if(event.getButton() == event.BUTTON3 && event.getClickCount() == 2)
//		{
//			frame.setTitle("Analog Stopwatch : resumed");
//			milisec.start();
//		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) 
	{
		if(event.getButton() == event.BUTTON1)
		{	
			if(mode == true)
			{
				frame.setTitle("Analog Stopwatch: Stoppped");
				milisec.stop();
				mode = false;
			}
			else
			{
				frame.setTitle("Analog Stopwatch : resumed");
				milisec.start();
				mode = true;
			}
		}
		if(event.getButton() == event.BUTTON3)
		{
			frame.setTitle("Analog Stopwatch: Reset");
			createSecWijzer();
			createSecWijzer2();
			graden = 0;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
