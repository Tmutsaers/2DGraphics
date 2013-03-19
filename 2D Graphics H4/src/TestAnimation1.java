import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class TestAnimation1 extends JPanel
{
	private JFrame frame;
	private BufferedImage smurfenSheet = null;
	private int index = 0;
	private int varx = 0;
	int x = 0;
	int y = 0;
	int rotate = 0;
	
	public static void main(String[] args)
	{
		new TestAnimation1();
	}
	
	public TestAnimation1()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Test Animatie 1");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		frame.add(this);
		loadImage();
		
		Timer refresh = new Timer(1000/20,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				x = (index % 4)*128;
				y = (index / 4)*128;
				
				index++;
				index %= 16;
				rotate-= 1;
				rotate%=360;
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
		g2.translate(getWidth()/2, getHeight()/2);
		g2.scale(1, 1);
		
		AffineTransform tx = new AffineTransform();
//		tx.rotate(Math.toRadians(45));
		g2.transform(tx);
		
		BufferedImage subImg = smurfenSheet.getSubimage(x, y, 128, 128);
		g2.drawImage(subImg, -100+varx, 0, 128, 128, null);
		
		varx+=0;
	}
	
	public void loadImage()
	{
		try {
			smurfenSheet = ImageIO.read(new File("src/smurfen.png"));
		} catch(IOException e) {
			System.out.println(e);
		}
	}
}
