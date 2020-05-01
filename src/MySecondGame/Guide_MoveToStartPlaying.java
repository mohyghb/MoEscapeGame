package MySecondGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Guide_MoveToStartPlaying {
	private boolean flash = false;
	private Color color = Menu.RANDOMCOLORLIST[Support.giveMeARandomNumberUpTo12()];
	
	public void flash()
	{
		this.flash = !flash;
		color = Menu.RANDOMCOLORLIST[Support.giveMeARandomNumberUpTo12()];
	}
	public void draw(boolean draw,Graphics2D g2d, int xpos,int ypos)
	{
		if(!draw&&flash)
		{
			g2d.setColor(this.color);
			g2d.setStroke(new BasicStroke(20));
			g2d.drawRect(xpos, ypos,900, 100);
			g2d.setFont(new Font("Serfi",Font.BOLD,50));
			g2d.drawString("Press Arrow Keys to start the game", xpos+20, ypos+70);
		}
				
			g2d.dispose();
			
	}
		

	

}
