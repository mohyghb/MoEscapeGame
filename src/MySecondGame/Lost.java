package MySecondGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;



public class Lost {
	
	private final String text;
	private final String numBombsText;
	private final String RESET_TEXT = "Press ENTER to restart";
	private int numBombs;
	private int level;
	
	
	Lost(int number, int lev)
	{
		this.numBombs = number;
		this.text = "YOU LOST";
		this.numBombsText = "YOU DEFFUSED";
		this.level = lev;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(new Font("Serfi",Font.BOLD,100));
		g.drawString(String.format("%s", this.text), Size.Width/3-40, Size.Height/2);
		
			g.setColor(Color.red);
		g.setFont(new Font("Serfi",Font.BOLD,50));
		g.drawString(String.format("%s %d BOMBS", this.numBombsText,this.numBombs),Size.Width/4, Size.Height-280);
		
		
		
		g.setColor(Color.cyan);
		g.drawString(RESET_TEXT, Size.Width/2-300, Size.Height/20+100);
		
		if(this.level>0)
		{
			g.setColor(Color.orange);
			g.drawString(String.format("YOU GOT TO LEVEL %d!", level), Size.Width/2-300, Size.Height/20+200);
		}
	}

}
