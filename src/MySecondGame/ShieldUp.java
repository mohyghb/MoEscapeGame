package MySecondGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class ShieldUp {
	

	private int Xpos;
	private int Ypos;
	private int Width;
	private int Height;
	private boolean isOrNot;
	private Image img;
	private int timer;


	ShieldUp(int x,int y,ImageObserver n)
	{
		this.img = Support.getImage1("Shieldd.png");
		this.Height = img.getHeight(n);
		this.Width = img.getWidth(n);
		this.Xpos = x;
		this.Ypos = y;
		this.isOrNot = false;
		this.timer = 0;
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
		this.Ypos  = y;
	}
	public int getYpos()
	{
		return this.Ypos;
	}
	
	public void drawTheShieldUp(Graphics g, ImageObserver n)
	{
		if(isOrNot)
		{
			g.drawImage(this.img, this.Xpos, this.Ypos,n);
		}
	}
	
	public void setIsOrNot(int ion)
	{
		if(!isOrNot)
		{
			if(ion==1)
			{
				this.isOrNot = true;
			}
			else 	
				this.isOrNot = false;
		}
		
	}
	public void refreshTimerAndIsOrNot()
	{
		if(timer>=4)
		{
			this.timer = 0;
			this.isOrNot = false;
		}
	}
	public boolean is()
	{
		return this.isOrNot;
	}
	public int getWidth()
	{
		return this.Width;
	}
	public int getHeight()
	{
		return this.Height;
	}
	public void setImage(String s)
	{
		this.img = Support.getImage1(s);
	}
	public Image getImage()
	{
		return this.img;
	}
	public void addTimer()
	{
		if(this.isOrNot)
		{
			this.timer++;
		}
		
	}
	public void makeIsOrNotFalse()
	{
		this.isOrNot = false;
	}
	public void changeThePositon()
	{
		if(!isOrNot&&this.img!=null)
		{
			this.Xpos = Support.giveMeARandomX();
			this.Ypos = Support.giveMeARandomY();
		}
	}

}
