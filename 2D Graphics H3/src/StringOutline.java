import java.awt.*;
import java.awt.Event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.*;
import javax.swing.*;

public class StringOutline extends JPanel
{
	private JFrame frame;
	
	public static void main(String[] args)
	{
		new StringOutline();
	}
	
	public StringOutline()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("String Outline");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		frame.add(this);
		
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		Font font = new Font("Serif", Font.BOLD, 50);
		g2.setFont(font);
		
		FontRenderContext frc = g2.getFontRenderContext();
		GlyphVector gv = font.createGlyphVector(frc, "NY");
		Shape glyph = gv.getOutline(10, 50);
		g2.draw(glyph);

	}
}
