import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;


public class Form extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int t = 5;
		int nPoints = 1000;
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200, 200);
		int x1 = 0;
		int y1 = 0;
		for(int i = 0; i < nPoints; i++)
		{
			int x2 = t*t;
			int y2 = t*t*t;
			g2.drawLine(x1, y1, x2, y2);
			x1=x2;
			y1=y2;
		}
	}
}

class Form2 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int nPoints = 8;
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(200,200);
		int x1 = 0;
		int y1 = 0;
		for(int i = 0; i < nPoints; i++)
		{
			int x2 = (int) (20*i*Math.cos(i));
			int y2 = (int) (20*i*Math.sin(i));
			g2.drawLine(x1,y1,x2,y2);
			x1 = x2;
			y1 = y2;
		}
	}
}

class Form3 extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.rotate(45*Math.PI /180);
		g2.draw(new Rectangle2D.Double(200,100,100,100));
	}
}

class ChessBoard extends JPanel
{
	public void blackSquare(Graphics2D g2,int x, int y)
	{
		g2.fill(new Rectangle2D.Double(x,y,50,50));
	}
	
	public void emptySquare(Graphics2D g2,int x,int y)
	{
		g2.draw(new Rectangle2D.Double(x,y,50,50));
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		int Xindex = 0;
		int Yindex = 0;
		int index = 0;
		boolean sq = true;							// true is empty , false is black
		while(index < 64)
		{
			if(sq == true)
			{
				if(Xindex< 400)
				{
					emptySquare(g2,Xindex,Yindex);
					Xindex += 50;
					sq = false;
					index++;
				}
				else
				{
					Xindex = 0;
					Yindex += 50;
					sq = false;
				}
			}
			if(sq == false)
			{
				if(Xindex < 400)
				{
					blackSquare(g2,Xindex,Yindex);
					Xindex += 50;
					sq = true;
					index++;
				}
				else
				{
					Xindex = 0;
					Yindex += 50;
					sq = true;
				}
			}
		}
		
	}
}
	
	class form4 extends JPanel
	{
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
//			g2.draw(new Ellipse2D.Double(200,200,100,50));
//			g2.draw(new Ellipse2D.Double(175,200,100,50));
			Shape s1 = new Ellipse2D.Double(200,200,100,50);
			Shape s2 = new Ellipse2D.Double(175,200,100,50);
			Area a1;
			Area a2 = new Area(s1);
			a1 = new Area(s2);
			a1.subtract(a2);
			g2.fill(a1);
			g2.translate(200,200);
		}
	}
	
	class form5 extends JPanel
	{
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			GeneralPath path = new GeneralPath();
			path.moveTo(0, -1);
			path.quadTo(-0.5,0,0,1);
			path.quadTo(-1,0,0,-1);
			path.closePath();
			
			AffineTransform tr = new AffineTransform();
			tr.setToScale(100,100);
			g2.translate(250, 250);
			Shape sh = tr.createTransformedShape(path);
			g2.fill(sh);
		}
	}
	
	class YingYang extends JPanel
	{
		
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			GeneralPath path1 = new GeneralPath();
			path1.moveTo(0,1.5);
			path1.curveTo(-2,-1,2,1,0,-1.5);
			path1.quadTo(3,0,0,1.5);
			path1.closePath();
			
			GeneralPath path2 = new GeneralPath();
			path2.moveTo(0,1.5);
			path2.curveTo(-2, -1, 2, 1, 0, -1.5);
			path2.quadTo(-3, 0, 0, 1.5);
			path2.closePath();
			
			AffineTransform tr = new AffineTransform();
			tr.setToScale(100,100);
			g2.translate(250, 250);
			Shape sh = tr.createTransformedShape(path1);
			Shape sh2 = tr.createTransformedShape(path2);
			g2.draw(sh2);
			Shape sh3 = new Ellipse2D.Double(0, 50, 10, 10);
//			g2.fill(new Ellipse2D.Double(0,-50,10,10));
			g2.fill(new Ellipse2D.Double(0,-50,10,10));
			
			Area a1;
			Area a2 = new Area(sh3);
			a1 = new Area(sh);
			a1.subtract(a2);
			g2.fill(a1);
		}
	}
	
	class YingYang2 extends JPanel
	{
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			Shape circle1 = new Ellipse2D.Double(200,200,100,50);
			AffineTransform form = new AffineTransform();
			form.setToScale(1, 2);
			circle1 = form.createTransformedShape(circle1);
			g2.translate(1,-1);
			g2.translate(-50, -200);
			g2.setPaint(new GradientPaint(200,250,Color.WHITE,300,250,Color.BLACK));
			g2.fill(circle1);
			
		}
	}

