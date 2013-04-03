import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class MiRe extends JPanel
{
	private JFrame frame;
	
	public static void main(String[] args)
	{
		new MiRe();
	}
	
	public MiRe()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Mirrored Square");
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
		path.moveTo(0,1);
		path.lineTo(1, 0);
		path.closePath();
		AffineTransform form = new AffineTransform();
		form.setToScale(800,800);
		Shape s = form.createTransformedShape(path);
		g2.draw(s);
		
		Shape s2 = new Rectangle2D.Double(0,100,100,50);
		g2.translate(400, 400);
		g2.draw(s2);
		
		AffineTransform tx = new AffineTransform();
		tx.setTransform(-1, 0, 0, 1, -150, -150);
		
		s2 = tx.createTransformedShape(s2);
		g2.setColor(Color.GREEN);
		g2.fill(s2);
		
//		Shape s3 = new Rectangle2D.Double(-200,-100,100,50);
//		g2.setColor(Color.GREEN);
//		g2.fill(s3);
		
	}
}
