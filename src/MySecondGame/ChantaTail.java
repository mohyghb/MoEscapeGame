package MySecondGame;

import java.awt.Color;
import java.awt.Graphics;

public class ChantaTail {
	
	private int Xpos;
	private int Ypos;
	private int Yvel;
	private int Xvel;
	private int Width;
	private int Height;
	private int deleted;
	private Color saveColor;
	
	ChantaTail(int x,int y,int xv, int yv,int w, int h)
	{
		this.Xpos = x;
		this.Ypos = y;
		this.Yvel = yv;
		this.Xvel = xv;
		this.Width = w;
		this.Height = h;
		this.deleted = 0;
		this.saveColor = Menu.RANDOMCOLORLIST[Support.rand.nextInt(12)];
	}
	public void moveThisTail()
	{
		if(this.deleted==0)
		{
		this.Xpos+=this.Xvel;
		this.Ypos+=this.Yvel;
		}
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
	public void setXvel(int xv)
	{
		this.Xvel = xv;
	}
	public int getXvel()
	{
		return this.Xvel;
	}
	public void setYvel(int yv)
	{
		this.Yvel = yv;
	}
	public int getYvel()
	{
		return this.Yvel;
	}
	
	public void setWidth(int wid)
	{
		this.Width = wid;
	}
	public int getWidth()
	{
		return this.Width;
	}
	public void setHeight(int height)
	{
		this.Height = height;
	}
	public int getHeight()
	{
		return this.Height;
	}
	public void setDeleted(int d)
	{
		this.deleted = d;
	}
	public boolean is()
	{
		return (deleted==0)?true:false;
	}
	
	public void drawTheChantaTail(Graphics g)
	{
		if(this.deleted==0)
		{
			g.setColor(saveColor);
			g.fillRoundRect(Xpos, Ypos, Width, Height, 10, 10);
		}
		
	}

}
