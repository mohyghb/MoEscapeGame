package MySecondGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class KoochikWon {

	private final String WonText;
	private final String LevelText;
	private final String nextLevel;
	
	KoochikWon(int l)
	{
		this.WonText = "YOU WON!!!!";
		this.LevelText ="YOU HAVE PASSED LEVEL "+l;
		this.nextLevel = "PRESS SPACE FOR THE NEXT LEVEL";
	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.green);
		g2d.setStroke(new BasicStroke(10));
		g2d.drawRoundRect(100, 100, 1100, 500, 10, 10);
		
		g2d.setFont(new Font("Serfi",Font.PLAIN,40));
		g2d.drawString(WonText, 510, 300);
		g2d.drawString(LevelText, 400, 400);
			g2d.drawString(nextLevel, 300, 500);
	}
}
