import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

import net.phys2d.*;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.BroadCollisionStrategy;
import net.phys2d.raw.CollisionContext;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.DynamicShape;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

public class Game2 
{
	private JFrame frame;
	private JPanel pane;
	
	public static void main(String[] args)
	{
		new Game2();
	}
	
	public Game2()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Game 2");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		pane = new pane3();
		frame.add(pane);
		pane.setBackground(Color.BLACK);
		
		
		frame.setVisible(true);
	}
	

}

class panel1 extends JPanel
{
	private World wereld = new World(new Vector2f(0.0f, 10.0f),10,new QuadSpaceStrategy(20, 5));
	private Body wall,ball,box;
	private Point[] points;
	public panel1()
	{
		setPreferredSize(new Dimension(800, 800));
		wereld.clear();
		
		wall = new StaticBody("Ground",new Box(100.0f,10.0f));
		wall.setPosition(400.0f,700.0f);
		
		ball = new Body(new Circle(2.0f),100.0f);
		ball.setPosition(400.0f, 100.0f);
		ball.setRestitution(7.0f);
		
		box = new Body(new Box(50.0f,50.0f),10.0f);
		box.setPosition(400.0f, 100.0f);
		box.setRestitution(7.0f);
		
		
		wereld.add(wall);
		wereld.add(box);
		wereld.add(ball);
		
		System.out.println("XPos: " + ball.getShape().getBounds().getOffsetX() *100);
		System.out.println("YPos: " + ball.getShape().getBounds().getOffsetY() * 100);
		System.out.println("Width: " + ball.getShape().getBounds().getWidth() *100);
		System.out.println("Height: " + ball.getShape().getBounds().getHeight() * 100);
		
		Timer time = new Timer(1000/30,new ActionListener()
		{
	        public void actionPerformed(ActionEvent arg0)
	        {
	                for(int i=0; i<5; i++) {
	                        wereld.step();
	                }

	                if(box.getPosition().getY() >200.0f)
	                {
	                	wereld.setGravity(0.0f, -10.0f);
	                }
	                if(box.getPosition().getY() < 200.0f)
	                {
	                	wereld.setGravity(0.0f, 10.0f);
	                }
	                repaint();
	        }
		});
		time.start();
	}
	
    public void drawBox(Graphics2D g2, Body b)
    {
            Box box = (Box) b.getShape();
            Vector2f[] pts = box.getPoints(b.getPosition(), b.getRotation());

            Vector2f p1 = pts[0];
            Vector2f p2 = pts[1];
            Vector2f p3 = pts[2];
            Vector2f p4 = pts[3];

            g2.drawLine((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
            g2.drawLine((int) p2.x,(int) p2.y,(int) p3.x,(int) p3.y);
            g2.drawLine((int) p3.x,(int) p3.y,(int) p4.x,(int) p4.y);
            g2.drawLine((int) p4.x,(int) p4.y,(int) p1.x,(int) p1.y);

    }
    
    public void drawCircle(Graphics2D g2, Body b)
    {
    	Circle circle = (Circle) b.getShape();
    	initPoints(circle.getRadius());

    	g2.translate(b.getPosition().getX(),b.getPosition().getY());
        Point p1 = points[0];
        for(int j = 1; j <= points.length; j++)
        {
            Point p2 = points[j % points.length];
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            p1 = p2;
        }
    }
    
    private void initPoints(float radius)
    {
        int numberOfPoints = 360/5;
        points = new Point[numberOfPoints];
        double cx = radius;
        double cy = radius;
        double r = 75.0;
        int count = 0;
        for(int theta = 0; theta < 360; theta+=5)
        {
            int x = (int)(cx + r * Math.cos(Math.toRadians(theta)));
            int y = (int)(cy + r * Math.sin(Math.toRadians(theta)));
            points[count++] = new Point(x, y);
        }
    }
    
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D)g;
    	
    	g2.setStroke(new BasicStroke(2.0f));
    	
    	g2.setColor(Color.BLACK);
    	drawBox(g2,wall);
    	   	
    	g2.setColor(Color.GREEN);
    	drawBox(g2,box);
    	
    	g2.setColor(Color.BLUE);
    	drawCircle(g2,ball);
    }
}

class pane2 extends JPanel implements MouseListener,MouseMotionListener
{
	private World wereld = new World(new Vector2f(0.0f, 19.41f),10,new QuadSpaceStrategy(20, 5));
	private Body circle, prison;
	private Point[] points;
	private float alpha = 1.0f;;
	private Point lastMousePos;
	private ArrayList<cirkel> circles = new ArrayList<cirkel>();
	private boolean pressed = false;
	
	public pane2()
	{
		
//		fillCircles();
		setPreferredSize(new Dimension(800,800));
		wereld.clear();
		
		circle = new Body(new Circle(50.0f),10.0f);
		circle.setPosition(400.0f, 100.0f);
		
		prison = new StaticBody("Prison", new Box(1600.0f, 1600f));
		prison.setPosition(0.0f, 0.0f);
		
		
		
//		wereld.add(circle);
		wereld.add(prison);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		Timer time = new Timer(1000/30, new ActionListener()
		{
	        public void actionPerformed(ActionEvent arg0)
	        {
	        	//TODO
	                for(int i=0; i<5; i++) 
	                {
	                        wereld.step();
	                }
	        		alpha -= 0.005f;
	        		alpha = Math.max(0,alpha);
	        		if(lastMousePos != null)
	        		{
	        			circle.setPosition((int)lastMousePos.getX(), (int)lastMousePos.getY());
	        		}
//	        		if(pressed == true)
//	        		{
//	        			setVisible(true);
//	        		}
//	        		else
//	        		{
//	        			setVisible(false);
//	        		}
	        		
	        		for( Iterator<cirkel> itr = circles.iterator(); itr.hasNext(); )
	        		{
	        			cirkel k = itr.next();

	        			if(k.getAlpha() == 0.0f || k.getPosition().getY() < -200 || k.getPosition().getX() < -200 || k.getPosition().getY() > 900 ||
	        					k.getPosition().getX() > 900)
	        			{
	        				wereld.remove(k);
	        				itr.remove();
	        			}
	        			else
	        			{
	        				
	        				
		        				k.update();
	        				
//	        				k.update();
//	        				if(lastMousePos == null)
//	        				{
//		        				k.setPosition(1.0f, 1.0f);
//	        				}
//	        				if(lastMousePos != null)
//	        				{
//		        				k.setPosition((int)lastMousePos.getX(),(int)lastMousePos.getY());
//	        				}
	        			}
	        		}
	        		
	        		if( circles.size() < 200)
	        		{
	        			if(pressed == true)
	        			{        			
	        				cirkel l = new cirkel(new Circle(25.0f),10.f);
		        			circles.add(l);
		        			wereld.add(l);
//		        			l.setRestitution(7.0f);
	        				if(lastMousePos != null)
	        				{
		        				l.setPosition((int)lastMousePos.getX(),(int)lastMousePos.getY());
	        				}
	        				
	        			}
	        		}
	        		System.out.println(circles.size());
	                repaint();
	        }
		});
		time.start();
	}
	
//	public void fillCircles()
//	{
//		for(int i = 0; i < 50; i++)
//		{
//			circles.add(new cirkel(new Circle(25.0f),10.f));
//		}
//	}
	
	public void drawCircle(Graphics2D g2, Body b)
	{
    	Circle circle = (Circle) b.getShape();
    	initPoints(circle.getRadius(),b.getPosition().getX(),b.getPosition().getY());

//    	g2.translate(b.getPosition().getX(),b.getPosition().getY());
//        Point p1 = points[0];
//        for(int j = 1; j <= points.length; j++)
//        {
//            Point p2 = points[j % points.length];
//            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
//            p1 = p2;
//        }
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        for(int j = 0; j < points.length; j++)
        {
            if(j == 0)
                path.moveTo(points[j].x, points[j].y);
            else
                path.lineTo(points[j].x, points[j].y);
        }
        path.closePath();
        
        AffineTransform tx = new AffineTransform();
        g2.fill(tx.createTransformedShape(path));
	}
	
    private void initPoints(float radius,float xPos,float yPos)
    {
        int numberOfPoints = 360/5;
        points = new Point[numberOfPoints];
        double cx = (int)xPos;
        double cy = (int)yPos;
        double r = (int)radius;
        int count = 0;
        for(int theta = 0; theta < 360; theta+=5)
        {
            int x = (int)(cx + r * Math.cos(Math.toRadians(theta)));
            int y = (int)(cy + r * Math.sin(Math.toRadians(theta)));
            points[count++] = new Point(x, y);
        }
    }
    
    public void drawBox(Graphics2D g2, Body b)
    {
            Box box = (Box) b.getShape();
            Vector2f[] pts = box.getPoints(b.getPosition(), b.getRotation());

            Vector2f p1 = pts[0];
            Vector2f p2 = pts[1];
            Vector2f p3 = pts[2];
            Vector2f p4 = pts[3];

            g2.drawLine((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
            g2.drawLine((int) p2.x,(int) p2.y,(int) p3.x,(int) p3.y);
            g2.drawLine((int) p3.x,(int) p3.y,(int) p4.x,(int) p4.y);
            g2.drawLine((int) p4.x,(int) p4.y,(int) p1.x,(int) p1.y);

    }
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setStroke(new BasicStroke(2.0f));
		
		g2.setColor(new Color(0.0f,1.0f,1.0f,alpha));
		drawCircle(g2,circle);
		
		g2.setColor(Color.BLACK);
		drawBox(g2, prison);
		
		for(cirkel k : circles)
		{
			k.draw(g2);
		}
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) 
	{
		lastMousePos = event.getPoint();
		if(event.getButton() == event.BUTTON1)
		{
			pressed =true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) 
	{
		lastMousePos = event.getPoint();
		pressed = false;
	}
}

class pane3 extends JPanel implements MouseMotionListener,MouseListener
{
	private World wereld = new World(new Vector2f(0.0f, 0.0f),10,new QuadSpaceStrategy(20, 5));
	private Body cage;
	private Point lastMousePos;
	private ArrayList<cirkel> circles = new ArrayList<cirkel>();
	private boolean pressed = false;
	
	public pane3()
	{
		setPreferredSize(new Dimension(800,800));
		wereld.clear();
		
		cage = new StaticBody("Cage",new Box(1600.0f,1600.0f));
		cage.setPosition(0.0f, 0.0f);
		
//		wereld.add(cage);
		wereld.setCollisionStrategy(new BroadCollisionStrategy() {
			
			@Override
			public void collideBodies(CollisionContext arg0, BodyList arg1, float arg2) {
				// TODO Auto-generated method stub
				
			}
		});
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		Timer time = new Timer(1000/30,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO
                for(int i=0; i<5; i++) 
                {
                        wereld.step();
                }
                
                for( Iterator<cirkel> itr = circles.iterator(); itr.hasNext(); )
        		{
        			cirkel k = itr.next();

        			if(k.getAlpha() == 0.0f || k.getPosition().getY() < -200 || k.getPosition().getX() < -200 || k.getPosition().getY() > 900 ||
        					k.getPosition().getX() > 900)
        			{
        				wereld.remove(k);
        				itr.remove();
        			}
        			else
        			{
        				
        				
	        				k.update();
	        				k.addForce(new Vector2f(k.getxForce(),k.getyForce()));
        				
//        				k.update();
//        				if(lastMousePos == null)
//        				{
//	        				k.setPosition(1.0f, 1.0f);
//        				}
//        				if(lastMousePos != null)
//        				{
//	        				k.setPosition((int)lastMousePos.getX(),(int)lastMousePos.getY());
//        				}
        			}

        		}
        		if( circles.size() < 200)
        		{
        			if(pressed == true)
        			{        			
//        				cirkel l = new cirkel(new Circle(20.0f),10.f);
//	        			circles.add(l);
//	        			wereld.add(l);
////	        			l.setRestitution(7.0f);
//        				if(lastMousePos != null)
//        				{
//	        				l.setPosition((int)lastMousePos.getX(),(int)lastMousePos.getY());
//	        				l.adjustVelocity(new Vector2f((float)Math.random()*((int)Math.random()*-1),(float)Math.random()*((int)Math.random()*-1)));
//	        				Random r1 = new Random();
//	        				Random r2 = new Random();
//	        				float x;
//	        				float y;
//	        				if(r1.nextBoolean() == true)
//	        				{
//	        					x = (float)Math.random()* 2000;
//	        				}
//	        				else
//	        				{
//	        					x = (float)Math.random()*-2000;
//	        				}
//	        				if(r2.nextBoolean() == true)
//	        				{
//	        					y = (float)Math.random() * 2000;
//	        				}
//	        				else
//	        				{
//	        					y = (float)Math.random() * -2000;
//	        				}
//	        				l.setxForce(x);
//	        				l.setyForce(y);
//        				}
        				makeCircle();
        				makeCircle();
        				makeCircle();
        				makeCircle();
        				makeCircle();
        				
        			}
        		}
//                System.out.println("Aantal cirkels: " + circles.size());
                repaint();
			}
	
		});
		time.start();
	}
	
	public void makeCircle()
	{
		cirkel l = new cirkel(new Circle(20.0f),10.f);
		circles.add(l);
		wereld.add(l);
//		l.setRestitution(7.0f);
		if(lastMousePos != null)
		{
			l.setPosition((int)lastMousePos.getX(),(int)lastMousePos.getY());
			l.adjustVelocity(new Vector2f((float)Math.random()*((int)Math.random()*-1),(float)Math.random()*((int)Math.random()*-1)));
			Random r1 = new Random();
			Random r2 = new Random();
			float x;
			float y;
			if(r1.nextBoolean() == true)
			{
				x = (float)Math.random()* 2000;
			}
			else
			{
				x = (float)Math.random() * -2000;
			}
			if(r2.nextBoolean() == true)
			{
				y = (float)Math.random() * 2000;
			}
			else
			{
				y = (float)Math.random() * -2000;
			}
			l.setxForce(x);
			l.setyForce(y);
		}
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setStroke(new BasicStroke(2.0f));
		
		g2.setColor(Color.BLACK);
//		drawBox(g2, cage);
		
		for(cirkel k : circles)
		{
			k.draw(g2);
		}
	}
	
    public void drawBox(Graphics2D g2, Body b)
    {
            Box box = (Box) b.getShape();
            Vector2f[] pts = box.getPoints(b.getPosition(), b.getRotation());

            Vector2f p1 = pts[0];
            Vector2f p2 = pts[1];
            Vector2f p3 = pts[2];
            Vector2f p4 = pts[3];

            g2.drawLine((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
            g2.drawLine((int) p2.x,(int) p2.y,(int) p3.x,(int) p3.y);
            g2.drawLine((int) p3.x,(int) p3.y,(int) p4.x,(int) p4.y);
            g2.drawLine((int) p4.x,(int) p4.y,(int) p1.x,(int) p1.y);

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) 
	{
		lastMousePos = event.getPoint();
		if(event.getButton() == event.BUTTON1)
		{
			pressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) 
	{
		lastMousePos = event.getPoint();
		pressed = false;
		
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
}

class cirkel extends Body
{
	private float alpha = 1.0f;
	private Point[] points;
	private float c1;
	private float c2;
	private float c3;
	private float xForce;
	private float yForce;
	public cirkel(DynamicShape shape, float m) 
	{
		super(shape, m);
		initColors();
	}
	
	public void update()
	{
		alpha -= 0.04f;
		alpha = Math.max(0,alpha);
	}
	
	public float getAlpha()
	{
		return alpha;
	}
	
	public void initColors()
	{
		c1 = (float)Math.random();
		c2 = (float)Math.random();
		c3 = (float)Math.random();
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
//		g2.setColor(new Color(c1,c2,c3,alpha));
		g2.setColor(new Color(1,1,1,alpha));
		drawCircle(g2,this);
	}
	
	public void updatePos(int xPos,int yPos)
	{
		setPosition(xPos, yPos);
	}
	
	public void drawCircle(Graphics2D g2, Body b)
	{
    	Circle circle = (Circle) b.getShape();
    	initPoints(circle.getRadius(),b.getPosition().getX(),b.getPosition().getY());

//    	g2.translate(b.getPosition().getX(),b.getPosition().getY());
//        Point p1 = points[0];
//        for(int j = 1; j <= points.length; j++)
//        {
//            Point p2 = points[j % points.length];
//            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
//            p1 = p2;
//        }
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        for(int j = 0; j < points.length; j++)
        {
            if(j == 0)
                path.moveTo(points[j].x, points[j].y);
            else
                path.lineTo(points[j].x, points[j].y);
        }
        path.closePath();
        
        AffineTransform tx = new AffineTransform();
        g2.fill(tx.createTransformedShape(path));
	}
	
    private void initPoints(float radius,float xPos,float yPos)
    {
        int numberOfPoints = 360/5;
        points = new Point[numberOfPoints];
        double cx = (int)xPos;
        double cy = (int)yPos;
        double r = (int)radius;
        int count = 0;
        for(int theta = 0; theta < 360; theta+=5)
        {
            int x = (int)(cx + r * Math.cos(Math.toRadians(theta)));
            int y = (int)(cy + r * Math.sin(Math.toRadians(theta)));
            points[count++] = new Point(x, y);
        }
    }

	public float getxForce() {
		return xForce;
	}

	public void setxForce(float xForce) {
		this.xForce = xForce;
	}

	public float getyForce() {
		return yForce;
	}

	public void setyForce(float yForce) {
		this.yForce = yForce;
	}
	
}
