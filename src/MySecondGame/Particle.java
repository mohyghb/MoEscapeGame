package MySecondGame;

import java.awt.Color;
import java.awt.Graphics;

public class Particle {
	
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private int xVel;
	private int yVel;
	private boolean bad;
	private boolean is;
	private boolean widthIncreased;
	AudioCreator ac = new AudioCreator();
	
	//some are too small;
	Particle(boolean tof)
	{
		this.xPos = Support.giveMeARandomX();
		this.yPos = Support.giveMeARandomY();
		this.width = Support.giveMeARandomNumberUpTo25()+10;
		this.height = this.width;
		this.is = true;
		this.bad = tof;
		this.widthIncreased = false;
		setXvelYvel();
	}
	Particle(int w,boolean badis)
	{
		this.xPos = Support.giveMeARandomX();
		this.yPos = Support.giveMeARandomY()-10;
		this.width = w;
		this.height = this.width;
		this.is = true;
		this.bad = badis;
		this.widthIncreased = false;
		this.xVel = 4;
		this.yVel = 4;
	}
	Particle()
	{
		this.xPos = Support.giveMeARandomX();
		this.yPos = Support.giveMeARandomY();
		this.width = 20;
		this.height = 20;
		this.is = true;
		this.xVel = 2;
		this.yVel = 2;
	}
	public void setXvelYvel()
	{
		if(this.bad)
		{
			this.xVel = 2;
			this.yVel = 2;
		}
		else 
		{
			this.xVel = 1;
			this.yVel = 1;
		}
		
	}
	public void makeItDifficult()
	{
		if(this.bad)
		{
			this.width = 90;
			this.height = 90;
			this.widthIncreased = true;
		}
	}
	public void makeItEasy()
	{
		if(!this.bad)
		{
			this.width = 90;
			this.height = 90;
			this.widthIncreased = true;
		}
	}
	public void moveIt()
	{
		if(is)
		{
		this.xPos+=this.xVel;
		this.yPos+=this.yVel;
		}
		
	}
	public void keepItInside()
	{
		if(is)
		{
			if(this.getXpos()<=0)
		{
			this.xVel = -this.xVel;
		}
		if(this.getXpos()>=Size.Width-46)
		{
			this.xVel = -this.xVel;
		}
		if(this.getYpos()<=0)
		{
			this.yVel = -this.yVel;
		}
		if(this.getYpos()>=Size.Height-65)
		{
			this.yVel = -this.yVel;
		}
		}
		
	}
	
	public int getXpos()
	{
		return this.xPos;
	}
	public int getYpos()
	{
		return this.yPos;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	public boolean is()
	{
		return this.is;
	}
	public boolean isBad()
	{
		return this.bad;
	}
	public void removeThis()
	{
		this.is = false;
		ac.PlayAudio("pop.wav");
	}
	public int getValue()
	{
		return this.width/5;
	}
	public void setXpos(int x)
	{
		this.xPos = x;
	}
	public void setYpos(int y)
	{
		this.yPos = y;
	}
	public void resetEveryThing()
	{
		this.xPos = Support.giveMeARandomX();
		this.yPos = Support.giveMeARandomY();
		this.width = Support.giveMeARandomNumberUpTo25()+10;
		this.height = this.width;
		this.is = true;
		setXvelYvel();
	}
	public void resetBadParticle()
	{
		this.xPos = Support.giveMeARandomX();
		this.yPos = Support.giveMeARandomY()-10;
		this.is = true;
	}
	public void resetTheSize()
	{
		if(this.bad&&this.widthIncreased)
		{
			this.width = Support.giveMeARandomNumberUpTo25()+10;
			this.height = this.width;
			this.widthIncreased = false;
		}
	}
	public void draw(Graphics g)
	{
		if(is())
		{
			if(this.bad)
			{
				g.setColor(Color.red);
				g.fillOval(xPos, yPos, width, height);
			}
			else if(!this.bad)
			{
				g.setColor(Color.green);
				g.fillOval(xPos, yPos, width, height);
			}
		}
	}
	

}
