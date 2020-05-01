package MySecondGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;


public class SpeedUp {
	
	private int Xpos;
	private int Ypos;
	private int Width;
	private int Height;
	private boolean isOrNot;
	private boolean Activation;
	private Image img;
	private int timer;
	
	SpeedUp(int x,int y,ImageObserver n)
	{
		this.img = Support.getImage1("SpeedUpd.png");
		this.Height = img.getHeight(n);
		this.Width = img.getWidth(n);
		this.Xpos = x;
		this.Ypos = y;
		this.timer = 0;
		this.isOrNot = false;
		this.Activation = false;
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
	
	public void drawTheSpeedUp(Graphics g, ImageObserver n)
	{
		if(isOrNot&&this.img!=null)
		{
			g.drawImage(this.img, this.Xpos, this.Ypos,n);
		}
	}
	
	public void setIsOrNot(int ion)
	{
		if(!isOrNot)
		{
			if(ion>0)
		{
			this.isOrNot = false;
		}
		else 
			this.isOrNot = true;
		}
		
	}
	public void makeIsOrNotFalse()
	{
		this.isOrNot = false;
	}
	public void refreshTimerAndIsOrNot()
	{
		if(timer>=4)
		{
			this.timer = 0;
			this.isOrNot = false;
		}
	}
	public void addTimer()
	{
		if(isOrNot)
		{
			this.timer++;
		}
		
	}

	public void changeThePositon()
	{
		if(!isOrNot)
		{
			this.Xpos = Support.giveMeARandomX();
			this.Ypos = Support.giveMeARandomY();
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
	public boolean getActivation()
	{
		return this.Activation;
	}
	public void setActivation(boolean tf)
	{
		this.Activation = tf;
	}
}
