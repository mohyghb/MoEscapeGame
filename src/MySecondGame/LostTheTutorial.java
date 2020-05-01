package MySecondGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class LostTheTutorial {
	
	public void draw(Graphics2D g2d)
	{
	g2d.setColor(Color.green);
	g2d.setStroke(new BasicStroke(10));
	g2d.drawRoundRect(100, 100, 1100, 500, 10, 10);
	
	g2d.setFont(new Font("Serfi",Font.PLAIN,40));
	g2d.drawString("You couldn't finish the tutorial", 510, 300);
	g2d.drawString("Press enter to restart the tutorial", 400, 400);
	g2d.drawString("Press space to go to the main menu", 300, 500);
	}
	
	

}
