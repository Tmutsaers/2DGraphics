import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Colors2 extends JPanel
{
	private JFrame frame;
	private JButton button;
	private colors3 color;
	private boolean show = false;
	private Color kleur = Color.GREEN;
	private JButton button2;
	private JPanel pane;
	private JColorChooser colors5;
	
	public static void main(String[] args)
	{
		new Colors2();
	}
	
	public Colors2()
	{
		color = new colors3(this);
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Color Choice");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(1000,800);
//		colors5 = new JColorChooser();
		
		frame.add(this);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		button = new JButton("Kleuren Kiezer");
		this.add(button);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!show)
				{
					color.makeVisible(true);
					show = true;
				}
				else
				{
					color.makeVisible(false);
					show = false;
				}
			}
		});
		
		button2 = new JButton("Get kleur");
		this.add(button2);
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				kleur = color.getColor();
				repaint();
			}
		});
		
//		pane = new JPanel();
//		frame.add(pane);
//		pane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		pane.add(colors);
		
//		this.add(colors5);
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200, 200);
		g2.setColor(Color.DARK_GRAY);
		g2.fill(new Rectangle(-100,-100,400,400));
		g2.setColor(kleur);
		Shape s1 = new Ellipse2D.Double(10,0,200,200);
		g2.fill(s1);
	}
}

class colors3
{
	private JFrame frame;
	private JColorChooser kiezen;
	private Colors2 main;
	private JButton button;
	private Color kleuren;
	
	public colors3(Colors2 kleur)
	{
		main = kleur;
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Kies een Kleur");
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setSize(500,600);
		
		kiezen  = new JColorChooser();
		frame.add(kiezen);
//		kiezen.createDialog(frame, "Kleur kiezen", true, kiezen, new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				kleuren = kiezen.getColor();
//			}
//		}, new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				makeVisible(false);
//			}
//		});
		
		frame.setVisible(false);
	}
	
	public void makeVisible(boolean visible)
	{
		frame.setVisible(visible);
	}
	
	public Color getColor()
	{
		return kiezen.getColor();
	}
	
}
