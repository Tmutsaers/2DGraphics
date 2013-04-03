import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;


public class Graphicsthree 
{
	private JFrame frame;
	private JPanel pane;
	public static void main(String args[])
	{
		new Graphicsthree();
	}
	
	public Graphicsthree()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		this.pane = new YingYang();
		frame.add(pane);

		
		frame.setVisible(true);
	}
}

//class Ellip extends JPanel
//{
//	protected void paintComponent(Graphics g)
//	{
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D)g;
//		g2.draw(new Ellipse2D.Double(100, 300, 80, 100));
////		g2.draw(new Arc2D.Float(0,0,100,200,0,45));
//	}
//}
