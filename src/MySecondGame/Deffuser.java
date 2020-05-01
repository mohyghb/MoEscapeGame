package MySecondGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Deffuser {
	
	private int Xpos;
	private int Ypos;
	private int TIMER;
	private int deleted;
	private Image deffuserImage = Support.getImage1("defuserd.png");
	
	Deffuser(int x, int y)
	{
		this.Xpos = x;
		this.Ypos = y;
		this.TIMER = 0;
		this.deleted = 0;
	}
	
	public void setXpos(int x)
	{
		this.Xpos = x;
	}
	public int getXpos()
	{
		return this.Xpos;
	}
	public void setYpos(int y)
	{
		this.Ypos = y;
	}
	public int getYpos()
	{
		return this.Ypos;
	}
	public int getWidth(ImageObserver n)
	{
		return deffuserImage.getWidth(n);
	}
	
	public int  getHeight(ImageObserver n)
	{
		return deffuserImage.getHeight(n);
	}
	public void setTIME(int t)
	{
		this.TIMER = t;
	}
	public int getTIME()
	{
		return this.TIMER;
	}
	public void setDeleted(int d)
	{
		this.deleted = d;
	}
	public void deleteThisDeffuser()
	{
		this.deleted = 1;
	}
	public boolean is()
	{
		if(this.deleted == 0)
		{
			return true;
		}
		return false;
	}
	public boolean isTimeToChange()
	{
		if(this.TIMER>=6)
		{
			return true;
		}
		return false;
	}
	public void drawDeffuser(Graphics g, ImageObserver n)
	{
		if(is()&&this.deffuserImage!=null)
		{
			g.drawImage(deffuserImage, this.Xpos, this.Ypos, deffuserImage.getWidth(n),deffuserImage.getHeight(n), n );
		}
		
	}

}
