import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

public class TestGravity extends JPanel implements MouseListener,MouseMotionListener 
{
	private JFrame frame;
	private int size = 1;
	private Shape s1 ;
	private Point lastMousePosition= null;
	private int dragging = 0;
	private boolean grow = false;
	
	public static void main(String[] args)
	{
		new TestGravity();
	}
	
	public TestGravity()
	{
		makeFrame();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Test Gravity");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		frame.add(this);
		Timer refresh  = new Timer(1000/10,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				size++;
				size%=20;
				repaint();
			}
		});
		refresh.start();
		s1 = new Rectangle2D.Double(0, 0, 10, 10);
//		addMouseListener(this);
//		addMouseMotionListener(this);
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
//		g2.translate(400, 400);
		g2.setColor(Color.GREEN);
		
		AffineTransform tx = new AffineTransform();
		if(grow == true)
		{
			tx.scale(size,size);
		}
//		tx.scale(size,size);
		g2.transform(tx);
//		s1 = new Rectangle2D.Double(0, 0, 10, 10);
		g2.fill(s1);
		
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		if(dragging == 1)
		{
			AffineTransform tx = new AffineTransform();
			Shape s = s1;
			if((arg0.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)
			{
				tx.translate(arg0.getPoint().x - lastMousePosition.x, 
							 arg0.getPoint().y - lastMousePosition.y);
			}
			s = tx.createTransformedShape(s);
			s1 = s;
			lastMousePosition = arg0.getPoint();
		}
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent event) 
	{
		if(event.getClickCount() == 2 && grow == false)
		{
			grow = true;
		}
		else
		{
			if(event.getClickCount() == 2 && grow == true)
			{
				grow = false;
			}
		}
		
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
		if(s1.contains(event.getPoint()))
		{
			lastMousePosition = event.getPoint();
			dragging = 1;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		dragging = 0;
		
	}
}
