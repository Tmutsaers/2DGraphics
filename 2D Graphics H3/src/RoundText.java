import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.*;
import javax.swing.*;

public class RoundText extends JPanel
{
	private JFrame frame;
	
	public static void main(String[] args)
	{
		new RoundText();
	}
	
	public RoundText()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Round Text");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		
		frame.add(this);
		
		
		
		frame.setVisible(true);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200,200);
		int rotation = -70;
		
//		String l1 = "H";
//		Font font = new Font("Serif", Font.BOLD, 50);
//		g2.setFont(font);
//		
//		FontRenderContext frc = g2.getFontRenderContext();
//		GlyphVector gv = font.createGlyphVector(frc, l1);
//		Shape glyph1 = gv.getOutline(10, 50);
//		AffineTransform tx = new AffineTransform();
//		tx.rotate(1*(Math.PI/180));
//		glyph1 = tx.createTransformedShape(glyph1);
//		g2.draw(glyph1);
//		
//		String l2 = "E";
//		frc = g2.getFontRenderContext();
//		gv = font.createGlyphVector(frc, l2);
//		Shape glyph2 = gv.getOutline(10, 50);
//		tx.rotate(rotation*(Math.PI/180));
//		glyph2 = tx.createTransformedShape(glyph2);
//		g2.draw(glyph2);
//		
//		String l3 = "L";
//		frc = g2.getFontRenderContext();
//		gv = font.createGlyphVector(frc, l3);
//		Shape glyph3 = gv.getOutline(10, 50);
//		tx.rotate(rotation*(Math.PI/180));
//		glyph3  = tx.createTransformedShape(glyph3);
//		g2.draw(glyph3);
//		
//		String l4 = "L";
//		frc = g2.getFontRenderContext();
//		gv = font.createGlyphVector(frc, l4);
//		Shape glyph4 = gv.getOutline(10, 50);
//		tx.rotate(rotation*(Math.PI/180));
//		glyph4 = tx.createTransformedShape(glyph4);
//		g2.draw(glyph4);
//		
//		String l5 = "O";
//		frc = g2.getFontRenderContext();
//		gv = font.createGlyphVector(frc, l5);
//		Shape glyph5 = gv.getOutline(10, 50);
//		tx.rotate(rotation*(Math.PI/180));
//		glyph5 = tx.createTransformedShape(glyph5);
//		g2.draw(glyph5);
		
		
		AffineTransform t2 = new AffineTransform();
		t2.rotate(1*(Math.PI/180));
		g2.transform(t2);
		g2.drawString("H",10,10);
		
		t2.rotate(-30*(Math.PI/180));
		g2.transform(t2);
		g2.drawString("E", 20,15);
		
//		t2.rotate(-*(Math.PI/180));
		g2.transform(t2);
		g2.drawString("L", 30, 30);
		
		g2.transform(t2);
		g2.drawString("L", 35, 50);
		
		g2.transform(t2);
		g2.drawString("O", 50, 70);
		
	}
}
