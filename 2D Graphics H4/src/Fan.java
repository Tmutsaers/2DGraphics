import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;


public class Fan 
{
	private JFrame frame;
	private JPanel pane;
	
	public static void main(String[] args)
	{
		new Fan();
	}
	
	public Fan()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Running Fan Animation");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		pane = new FanBlades();
		frame.add(pane);
		
		Timer time = new Timer(1000/100,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pane.repaint();
			}
		});
		time.start();
		
		frame.setVisible(true);
	}
}

class FanBlades extends JPanel
{
	private Shape blade1;
	private Shape blade2;
	private Shape blade3;
	private Shape blade4;
	
	public FanBlades()
	{
		createBlades();
	}
	
	
	public void createBlades()
	{
		GeneralPath line1 = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		GeneralPath line2 = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		GeneralPath line3 = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		GeneralPath line4 = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		
		line1.moveTo(0.0, 0.0);
		line1.lineTo(0.0,1.0);
		line1.closePath();
		
		line2.moveTo(0.0, 0.0);
		line2.lineTo(1.0, 0.0);
		line2.closePath();
		
		line3.moveTo(0.0, 0.0);
		line3.lineTo(0.0, -1.0);
		line3.closePath();
		
		line4.moveTo(0.0, 0.0);
		line4.lineTo(-1.0,0.0);
		line4.closePath();
		
		AffineTransform tx = new AffineTransform();
		tx.scale(1,150);
		
		AffineTransform tx2 = new AffineTransform();
		tx2.scale(150, 1);
		
		blade1 = tx.createTransformedShape(line1);
		blade2 = tx2.createTransformedShape(line2);
		blade3 = tx.createTransformedShape(line3);
		blade4 = tx2.createTransformedShape(line4);
	}
	
	public void rotateBlades(int degrees)
	{
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(degrees),0,0);
		
		blade1 = tx.createTransformedShape(blade1);
		blade2 = tx.createTransformedShape(blade2);
		blade3 = tx.createTransformedShape(blade3);
		blade4 = tx.createTransformedShape(blade4);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.translate(400, 400);
		g2.setStroke(new BasicStroke(2.0f));
		g2.setColor(Color.BLACK);
		
		rotateBlades(1);
//		g2.draw(new Ellipse2D.Double(0,0,100,100));
		
		g2.draw(blade1);
		g2.draw(blade2);
		g2.draw(blade3);
		g2.draw(blade4);
	
	}
}
