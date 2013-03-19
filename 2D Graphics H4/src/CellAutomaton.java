import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

import javax.swing.*;

public class CellAutomaton extends JPanel  implements MouseListener
{
	private JFrame frame;
	private boolean [][] cells = new boolean[100][100];						//true is wit,false is zwart
	private Timer time;
	
	public static void main(String[] args)
	{
		new CellAutomaton();
	}
	
	public CellAutomaton()
	{
		fillCells();
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Cell Automaton");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		
		frame.add(this);
		time = new Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				repaint();
			}
		});
		time.start();
		addMouseListener(this);
		
		
		frame.setVisible(true);
	}
	
	public void drawCellsOnce(Graphics2D g2)
	{
		for(int x = 0; x < 100 ; x++)
		{
			for(int y = 0; y < 100; y++)
			{
				Shape cell = new Rectangle2D.Double(x*5,y*5,5,5);
				if((int)(Math.random()*2) == 1)
				{
					g2.setColor(Color.WHITE);
					cells[x][y] = true;
				}
				else
				{
					g2.setColor(Color.BLACK);
					cells[x][y] = false;
				}
				g2.fill(cell);
			}
		}
	}
	
	public void fillCells()
	{
		for(int x = 0 ; x <100 ; x++)
		{
			for(int y = 0; y <100; y++)
			{
				if((int)(Math.random()*2) == 1)
				{
					cells[x][y] = true;
				}
				else
				{
					cells[x][y] = false;
				}
			}
		}
	}
	
	public void transformCells(Graphics2D g2)
	{
		g2.translate(250, 250);
		for(int x = 50 ; x < 100; x++)
		{
			for(int y = 50 ; y < 100; y++)
			{
				cells[x][y] = checkRules(x,y);
			}
		}
		g2.translate(-250, -250);
		g2.translate(0, 250);
		for(int x = 0; x < 50; x++)
		{
			for(int y = 50; y < 100; y++ )
			{
				cells[x][y] = checkRules(x,y);
			}
		}
		g2.translate(0, 0);
		for(int x = 0; x<50; x++)
		{
			for(int y = 0 ; y < 50 ; y++)
			{
				cells[x][y] = checkRules(x,y);
			}
		}
		g2.translate(250, 0);
		for(int x = 50; x < 100; x++)
		{
			for(int y = 0 ; y < 50; y++)
			{
				cells[x][y] = checkRules(x,y);
			}
		}
	}
	
	public void drawCells(Graphics2D g2)
	{
		g2.translate(-250, -250);
		for(int x = 0 ; x < 100; x++)
		{
			for(int y = 0; y < 100; y++)
			{
				Shape cell = new Rectangle2D.Double(x*5,y*5,5,5);
				if(cells[x][y] == false)
				{
					g2.setColor(Color.BLACK);
				}
				else
				{
					g2.setColor(Color.WHITE);
				}
				g2.fill(cell);
			}
		}
	}
	
	public boolean checkRules(int x, int y)
	{
		boolean rechtse = true;
		boolean linkse = true;
		boolean bovenste = true;
		boolean onderste = true;
		boolean self;
		if(x < 99)
		{
			if(cells[x+1][y] == false)				//check rechtse cell
			{
				rechtse = false;
			}
			else
			{
				rechtse = true;
			}
		}
//		if(cells[x+1][y] == false)				//check rechtse cell
//		{
//			rechtse = false;
//		}
//		else
//		{
//			rechtse = true;
//		}
		if( x > 0)
		{
			if(cells[x-1][y] == false)				//check linkse cell
			{
				linkse = false;
			}
			else
			{
				linkse = true;
			}
		}
//		if(cells[x-1][y] == false)				//check linkse cell
//		{
//			linkse = false;
//		}
//		else
//		{
//			linkse = true;
//		}
		
		if( y < 99)
		{
			if(cells[x][y+1] == false)				//check bovenste cell
			{
				bovenste = false;
			}
			else
			{
				bovenste = true;
			}
		}
		
//		if(cells[x][y+1] == false)				//check bovenste cell
//		{
//			bovenste = false;
//		}
//		else
//		{
//			bovenste = true;
//		}
		if(y > 0)
		{
			if(cells[x][y-1] == false)				//check onderste cell
			{
				onderste = false;
			}
			else
			{
				onderste = true;
			}
		}
//		if(cells[x][y-1] == false)				//check onderste cell
//		{
//			onderste = false;
//		}
//		else
//		{
//			onderste = true;
//		}
		
		if(cells[x][y] == false)
		{
			self = false;
		}
		else
		{
			self = true;
		}
		
		return ruleChecker(rechtse,linkse,bovenste,onderste,self);
	}
	
	public boolean ruleChecker(boolean rechtse,boolean linkse,boolean bovenste,boolean onderste,boolean self)
	{
		int numberOfFalse = 0;
		int numberOfTrue = 0;
		if(rechtse == false)
		{
			numberOfFalse += 1;
		}
		else
		{
			numberOfTrue += 1;
		}
		if(linkse == false)
		{
			numberOfFalse += 1;
		}
		else
		{
			numberOfTrue +=1;
		}
		if(bovenste == false)
		{
			numberOfFalse += 1;
		}
		else
		{
			numberOfTrue += 1;
		}
		if(onderste == false)
		{
			numberOfFalse += 1;
		}
		else
		{
			numberOfTrue += 1;
		}
		if(self != false && numberOfFalse != 1)
		{
			return false;
		}
		if(self != false && numberOfFalse == 1)
		{
			return true;
		}
			
		if(self != true)
		{
			if(numberOfFalse == 1 || numberOfFalse == 3)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return false;
		
		
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(0,0);
//		drawCellsOnce(g2);
		transformCells(g2);
		drawCells(g2);
		
	}

	@Override
	public void mouseClicked(MouseEvent event ) 
	{
		
		if(event.getButton() == event.BUTTON1 && event.getClickCount() == 1)
		{
			time.stop();
		}
		if(event.getButton() == event.BUTTON3 && event.getClickCount() == 1)
		{
			time.start();
		}
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class Cell 
{
	private boolean color;	// false is zwart, true is wit
	
	public Cell(boolean color)
	{
		this.color = color;
	}
	
	public boolean getColor()
	{
		return color;
	}
	
	public void setColor(boolean color)
	{
		this.color = color;
	}

}
