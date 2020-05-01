package MySecondGame;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class ClearUp extends ShieldUp {
	
	private boolean is;
	private boolean create;
	
	
	
	ClearUp(int x,int y, ImageObserver io)
	{
		super(x,y,io);
		super.setImage("Restartd.png");
		this.is = false;
		this.create = true;
	}
	public boolean getIs()
	{
		return is;
	}
	public void setIs(boolean s)
	{
		this.is = s;
	}
	public boolean create()
	{
		return this.create;
	}
	public void setCreate(boolean c)
	{
		this.create = c;
	}
	public int getXpos()
	{
		return super.getXpos();
	}
	public int getYpos()
	{
		return super.getYpos();
	}
	public int getWidth()
	{
		return super.getWidth();
	}
	public void setXpos(int s)
	{
		super.setXpos(s);
	}
	public void setYpos(int y)
	{
		super.setYpos(y);
	}
	public int getHeight()
	{
		return super.getHeight();
	}
	public void draw(Graphics g, ImageObserver n)
	{
		if(is&&super.getImage()!=null)
		{
			g.drawImage(super.getImage(), super.getXpos(), super.getYpos(),n );
		}
		
	}

}
