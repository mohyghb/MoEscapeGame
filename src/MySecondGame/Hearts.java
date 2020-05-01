package MySecondGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Hearts {
	
	private int Xpos;
	private int Ypos;
	private Image heartImg;
	private int  is;
	private int timer;
	
	Hearts()
	{
		this.Xpos = Support.giveMeARandomX();
		this.Ypos = Support.giveMeARandomY();
		this.heartImg = Support.getImage1("heartd.png");
		this.is = Support.giveMeARandomNumberUpTo5() ; 
		this.timer = 0;
	}
	public void addTimer()
	{
		this.timer++;
	}
	public boolean isTime()
	{
		return (timer==6)?true:false;
	}
	public void resetTimer()
	{
		this.timer = 0;
	}
	public boolean is1()
	{
		return (this.is==0)?true:false;
	}
	public int getXpos()
	{
		return this.Xpos;
	}
	public int getYpos()
	{
		return this.Ypos;
	}
	public int getWidth(ImageObserver n)
	{
		return this.heartImg.getWidth(n);
	}
	public int getHight(ImageObserver n)
	{
		return this.heartImg.getHeight(n);
	}
	public void draw(Graphics g,ImageObserver n)
	{
		if(is1()&&heartImg!=null)
		{
			g.drawImage(heartImg, Xpos, Ypos, n);
		}
	}

}
