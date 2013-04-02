import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;

import net.phys2d.*;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

public class Game1 extends JPanel implements MouseListener,MouseMotionListener,MouseWheelListener
{
	private JFrame frame;
	private World myWorld = new World(new Vector2f(0.0f, 10.0f),10,new QuadSpaceStrategy(20,5));
	private ArrayList<Bal> ballen = new ArrayList<Bal>();
	private Timer time;
	private Point lastMousePos = new Point(0,0);
	private boolean selected;
	
	public static void main(String[] args)
	{
		new Game1();
	}
	public Game1()
	{
        myWorld = new World(
                new Vector2f(0.0f, 10.0f),
                10,
                new QuadSpaceStrategy(20,5));
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Test Game 1");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		frame.add(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		
		time = new Timer(1000/30,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for( Iterator<Bal> itr = ballen.iterator(); itr.hasNext(); )
				{
					Bal k = itr.next();

					if(k.y < -200.0)
					{
						itr.remove();
					}
					else
					{
						k.update();
					}
				}
				
				if( ballen.size() < 50)
				{
					ballen.add(new Bal());
				}
				
				repaint();
			}
		});
		ballen.add(new Bal());
		time.start();
		
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.translate(0,getHeight());
		g2.scale(1,-1);
		
		g2.translate(lastMousePos.x,lastMousePos.y);
		g2.scale(1, 1);
		
		for ( Bal b : ballen)
		{
			b.draw(g);
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent event) 
	{
		lastMousePos = event.getPoint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		selected = true;
		lastMousePos = event.getPoint();
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class Bal
{
	public double x,y;
	private double t = 0;
	private double speed;
	private double angle;

	private float red;
	private float green;
	private float blue;
	public float alpha;
	private Ellipse2D.Double ellipse;
	
	private boolean first = true;
	
	public Bal()
	{
		speed = Math.random() * 200.0;
		angle = Math.random() * 60 + 60.0;
		
		red = (float)Math.random();
		green = (float)Math.random();
		blue = (float)Math.random();
		
		alpha = 1.0f;
	}
	
	public void update()
	{
		x = speed * Math.cos((Math.PI/180)*angle) * t;
		y = speed * Math.sin((Math.PI/180)*angle) - 0.5 * 9.8 * Math.pow(t, 2.0);
		t += 0.05;
		alpha -= 0.005f;
		alpha = Math.max(0,alpha);
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		if(first)
		{
			 ellipse = new Ellipse2D.Double(0,0,10,10);
			first = false;
		}
		else
		{
			ellipse = new Ellipse2D.Double(x,y,10,10);
		}
		g2.setColor(new Color(red,green,blue,alpha));
		g2.fill(ellipse);
		
	}
}
