package MySecondGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class KoochikLost {
	
	private final String LostText;
	private final String LostLevel;
	private final String[] Guides = {"Did you know that a blue power up can be good and bad?!","Watch Out! you die if your size gets lower than 10!","A black powerUP make you not see the green particles!!!","A yellow powerUP makes you as fast as a flash!!!!","The pink powerUp takes you to another WORLD!!!!"} ;
	private final String guideText = Guides[Support.rand.nextInt(Guides.length)];
	
	private  String bomb = "You have died due to the timer of the bomb!!!!!!!";
	private  String size = "You have died since you had a size lower than 10!!!!";
	private  String health = "You have died since your health got to 0!!!!!!!!!!!";
	private  String howYouDied;
	
	KoochikLost(int l, int r)
	{
		LostText = "YOU HAVE LOST";
		this.LostLevel = String.format("YOU GOT TO LEVEL %d",l);
		setHowYouDied(r);
	}
	public void setHowYouDied(int r)
	{
		if(r==1)
		{
			this.howYouDied = this.bomb;
		}
		else if(r==2)
		{
			this.howYouDied = this.size;
		}
		else if(r==3)
		{
			this.howYouDied = this.health;
		}
			
	}
	
	public void draw(Graphics2D g2d)
	{
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(Color.red);
		g2d.drawRoundRect(100, 100, 1100, 500, 10, 10);
		
		g2d.setFont(new Font("Serfi",Font.PLAIN,40));
		g2d.drawString(LostText, 510, 300);
		g2d.drawString(LostLevel, 490, 400);
		
		g2d.setFont(new Font("Serfi",Font.PLAIN,40));
		g2d.setColor(Color.blue);
		g2d.drawString(this.guideText, 180, 500);
		
		g2d.drawString(this.howYouDied, 190, 200);
	}

}
