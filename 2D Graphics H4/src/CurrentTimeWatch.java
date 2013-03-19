import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Date;
import java.io.File;
import java.io.IOException;


public class CurrentTimeWatch extends JPanel
{
	private JFrame frame;
	private Shape secWijzer;
	private Shape minWijzer;
	private Shape uurWijzer;
	private Timer sec;
	private Date tijd;
	private Image klok = null;
	
	
	public static void main(String[] args)
	{
		new CurrentTimeWatch();
	}
	
	public CurrentTimeWatch()
	{
		createSecWijzer();
		createMinWijzer();
		createUurWijzer();
		try {
			klok = ImageIO.read(new File("src/watch.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		makeFrame();
		
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Current Time Watch");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		frame.add(this);
		sec = new Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				createSecWijzer();
				createMinWijzer();
				createUurWijzer();
				tijd = new Date(System.currentTimeMillis());
				rotateUurWijzer((tijd.getHours()%12)*30);
				rotateMinWijzer(tijd.getMinutes()*6);
				rotateSecWijzer(tijd.getSeconds()*6);
				frame.setTitle("Current Time Watch : " + tijd.getHours() + ":" + tijd.getMinutes() + ":" + tijd.getSeconds());
				repaint();
				
			}
		});
		sec.start();
		
		frame.setVisible(true);
	}
	
	public void createSecWijzer()
	{
		GeneralPath path  = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(0.0, 0.0);
		path.lineTo(0.0, -1.0);
		path.closePath();
		
		AffineTransform form = new AffineTransform();
		form.setToScale(1, 190);
		secWijzer = form.createTransformedShape(path);
	}
	
	public void createMinWijzer()
	{
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(0.0,0.0);
		path.lineTo(0.0,-1.0);
		path.closePath();
		
		AffineTransform form = new AffineTransform();
		form.setToScale(1, 150);
		minWijzer = form.createTransformedShape(path);
	}
	
	public void createUurWijzer()
	{
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(0.0, 0.0);
		path.lineTo(0.0, -1.0);
		path.closePath();
		
		AffineTransform form = new AffineTransform();
		form.setToScale(1,100);
		uurWijzer = form.createTransformedShape(path);
	}
	
	public void rotateSecWijzer(int graden)
	{
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(graden),0,0);
		secWijzer = tx.createTransformedShape(secWijzer);
	}
	
	public void rotateMinWijzer(int graden)
	{
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(graden),0,0);
		minWijzer = tx.createTransformedShape(minWijzer);
	}
	
	public void rotateUurWijzer(int graden)
	{
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(graden),0,0);
		uurWijzer = tx.createTransformedShape(uurWijzer);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(400, 400);
		g2.setColor(Color.WHITE);
		g2.clip(new Ellipse2D.Double(-200,-200,400,400));
		g2.drawImage(klok,-195,-197,null);
		g2.setColor(Color.BLACK);
//		g2.draw(new Ellipse2D.Double(-200,-200,400,400));
		g2.setColor(Color.RED);
		g2.draw(secWijzer);
		g2.setColor(Color.BLACK);
		g2.draw(minWijzer);
		g2.draw(uurWijzer);
	}
	
	
}
