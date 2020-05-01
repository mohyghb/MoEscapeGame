package MySecondGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class ChantaPanel {
	
	private int lives;
	private int Chanta;
	private int ChantaSpeed;
	private Image heart;
	
	ChantaPanel()
	{
		lives = 3;
		Chanta = 0;
		ChantaSpeed = 0;
		heart = Support.getImage1("heartd.png");
	}
	public void addChanta()
	{
		Chanta++;
		ChantaSpeed++;
	}
	public void lostALife()
	{
		lives--;
	}
	public void draw(Graphics g,ImageObserver s)
	{
		if(heart!=null)
		{
			for(int i = 1;i<=lives;i++)
			{
				g.drawImage(heart, (i*40)-30, 7,s);
			}
		}
		
		if(this.Chanta<10)
		{
			g.setColor(Color.lightGray);
			g.setFont(new Font("Serfi",Font.PLAIN,500));
			g.drawString(String.format("%d", Chanta), Size.Width/2-150, Size.Height/2+120);
		}
		else
		{
			g.setColor(Color.lightGray);
			g.setFont(new Font("Serfi",Font.PLAIN,500));
			g.drawString(String.format("%d", Chanta), Size.Width/2-300, Size.Height/2+120);
		}
		
		
	}
	public int getChanta()
	{
		return this.Chanta;
	}
	public void addLives()
	{
		this.lives++;
	}
	public void resetChantaSpeed()
	{
		this.ChantaSpeed = 0;
	}
	public boolean timeIsUpForSpeed()
	{
			if(ChantaSpeed==10)
			{
				resetChantaSpeed();
				return true;
			}
		
		return false;
	}
	public boolean youLost()
	{
		return (this.lives==1)?true:false;
	}
	public void setLives(int l)
	{
		this.lives = l;
	}

}
