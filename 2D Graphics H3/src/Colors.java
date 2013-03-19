import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Colors 
{
	private JFrame frame;
	private JPanel pane;
	private JButton knop1;
	private Kleuren kleur;
	private boolean show = false;
	
	public static void main(String[] args)
	{
		new Colors();
	}
	
	public Colors()
	{
		makeFrame();
		kleur = new Kleuren();
	}
	
	public void makeFrame()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(750,750);
		this.pane = new Vorm7();
		frame.add(pane);
		pane.setLayout(new BorderLayout());
		
		knop1 = new JButton("Kies Kleur");
		pane.add(knop1,BorderLayout.EAST);
		knop1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!show)
				{
					kleur.makeVisible();
					show = true;
				}
				else
				{
					kleur.makeInvisible();
					show = false;
				}
			}
		});
		
		
		
		frame.setVisible(true);
	}
	

}

class Kleuren 
{
	private JColorChooser choice;
	private JFrame frame;
	public Kleuren()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Kleuren");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,600);
		choice = new JColorChooser();
		frame.add(choice);
		
		
		frame.setVisible(false);
	}
	
	public void makeVisible()
	{
		frame.setVisible(true);
	}
	
	public void makeInvisible()
	{
		frame.setVisible(false);
	}
}

class Vorm extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200, 200);
//		AffineTransform form = new AffineTransform();
//		g2.setTransform(form);
//		form.rotate(Math.PI/2);
//		g2.draw(new Rectangle2D.Double(300,100,100,100));
		g2.setPaint(new GradientPaint(200,150,Color.RED,300,150,Color.BLUE,true));
//		g2.fill(new Rectangle2D.Double(200,100,100,100));
		AffineTransform form = new AffineTransform();								
		form.rotate(10*(Math.PI/180),200,200);																	// (aantal graden) * (Math.PI/180)
//		form.setToScale(50,50);
		Shape vorm1 = new Rectangle2D.Double(200,100,100,100);
		vorm1 = form.createTransformedShape(vorm1);
		g2.translate(0,0);
		g2.fill(vorm1);

	}
}

class Vorm2 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(200, 200);
		Shape s1 = new Ellipse2D.Double(200,200,100,50);
//		g2.draw(s1);
		AffineTransform form = new AffineTransform();
		form.scale(1,2);
		s1 = form.createTransformedShape(s1);
		g2.translate(0, 0);
		g2.draw(s1);
//		AffineTransform form = new AffineTransform();
//		try {
//			form.invert();
//		} catch (NoninvertibleTransformException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		s1 = form.createTransformedShape(s1);
//		g2.draw(s1);
		
	}
}

class Vorm3 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200, 200);
		AffineTransform form = new AffineTransform();
		form.rotate(Math.PI/3,200,200);
		Shape Rectangle1 = new Rectangle2D.Double(200,100,100,100);
		Rectangle1 = form.createTransformedShape(Rectangle1);
		g2.draw(Rectangle1);
	}
}

class Vorm4 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200,200);
		g2.setColor(Color.GREEN);
		g2.fill(new Rectangle2D.Double(0,0,100,100));
		g2.setColor(Color.RED);
		g2.fill(new Rectangle2D.Double(0,-100,100,100));
		g2.setColor(Color.YELLOW);
		g2.fill(new Rectangle2D.Double(100,-100,100,100));
		g2.setColor(Color.ORANGE);
		g2.fill(new Rectangle2D.Double(100,0,100,100));
		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(0,100,100,100));
		g2.setColor(Color.GRAY);
		g2.fill(new Rectangle2D.Double(100,100,100,100));
		g2.setColor(Color.CYAN);
		g2.fill(new Rectangle2D.Double(200,-100,100,100));
		g2.setColor(Color.DARK_GRAY);
		g2.fill(new Rectangle2D.Double(200,0,100,100));
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(new Rectangle2D.Double(200,100,100,100));
	}
}

class Vorm5 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200, 200);
		g2.setColor(Color.DARK_GRAY);
		g2.fill(new Rectangle(-100,-100,400,400));
		g2.setColor(Color.GREEN);
		Shape s1 = new Ellipse2D.Double(10,0,200,200);
		g2.fill(s1);
	}
}

class Vorm6 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200, 200);
		g2.setPaint(new GradientPaint(0,-50,Color.BLACK,0,50,Color.WHITE));
		g2.fill(new Rectangle2D.Double(-50,-50,100,100));
	}
}


class Vorm7 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200,200);
		g2.setColor(Color.DARK_GRAY);
		g2.fill(new Rectangle2D.Double(-100,-100,400,400));
		g2.setColor(Color.green);
		g2.setPaint(new GradientPaint(0,0,Color.GREEN,200,0,Color.DARK_GRAY));
//		g2.fill(new Ellipse2D.Double(10,0,200,200));
		Shape s1 = new Ellipse2D.Double(10,0,200,200);
		g2.fill(s1);
	}
}