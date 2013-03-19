import java.awt.*;
import java.awt.Event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;

public class ClippingEllipse extends JPanel
{
	private JFrame frame;
	BufferedImage img = null;
	
	public static void main(String[] args)
	{
		new ClippingEllipse();
	}
	
	public ClippingEllipse()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Clipping Ellipse");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
	
		getFile();
		
		frame.add(this);
		
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform tx = new AffineTransform();
		Shape s1 = new Ellipse2D.Double(100,100,500,200);
		g2.clip(s1);
		g2.drawImage(img,tx,null);
	}
	
	public void getFile()
	{
		try {
			img = ImageIO.read(new File("src/Dover.png"));
		} catch(IOException e) {
			System.out.println(e);
		}
	}
	
	
}
