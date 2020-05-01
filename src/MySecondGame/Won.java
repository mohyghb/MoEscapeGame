package MySecondGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Won {
	
	private final String text;
	private final String numBombsText;
	private int numBombs;
	private int level;
	

	Won(int number,int l)
	{
		this.level = l;
		this.text = String.format("YOU HAVE PASSED LEVEL %s", this.level);
		this.numBombsText = "YOU HAVE DESTROYED";
		this.numBombs = number;
		
	}
	
	
	public void draw(Graphics g)
	{
		g.setColor(Color.blue);
		g.setFont(new Font("Serfi",Font.BOLD,70));
		g.drawString(String.format("%s", text), Size.Width/10+50, Size.Height/2);
		
		
		g.setColor(Color.red);
		g.setFont(new Font("Serfi",Font.BOLD,50));
		g.drawString(String.format("%s %d BOMBS", numBombsText, numBombs), Size.Width/5+20,Size.Height-280);
		
		
		
		g.setColor(Color.magenta);
		g.setFont(new Font("Serfi",Font.BOLD,70));
		g.drawString("Press Space to proceed", Size.Width/2-365, Size.Height/20+220);
		
	}
}
