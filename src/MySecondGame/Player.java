package MySecondGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Player
{
	private int Xpos;
	private int Ypos;
	private int YVel;
	private int XVel;
	private int Width;
	private int Height;
	private int speed;
	
	//powerUPS
	private int speedTimer;
	private boolean shieldIs;
	private int numberOfShileds;
	private int shieldTimer;
	
	//movement
	private boolean MOVING_LEFT;
	private boolean MOVING_RIGHT;
	private boolean MOVING_UP;
	private boolean MOVING_DOWN;
	
	/*Account
	public static int numberOfSpeedPowers;
	public static int numberOfShieldPowers;
	public static int coins;
	...
	*/
	
	Player()
	{
		this.Xpos = Size.Width/2;
		this.Ypos = Size.Height/2;
		this.Height = 40;
		this.Width = 40;
		this.XVel = 0;
		this.YVel = 0;
		
		this.speed = 2;
		this.speedTimer = 0;
		this.shieldIs = false;
		this.numberOfShileds = 0;
		this.shieldTimer = 0;
		
		this.MOVING_DOWN = false;
		this.MOVING_LEFT = false;
		this.MOVING_RIGHT = false;
		this.MOVING_DOWN = false;
	}
	
	Player(int h)
	{
		this.Xpos = Size.Width/2;
		this.Ypos = Size.Height/2;
		this.Height = h;
		this.Width = this.Height ;
		this.XVel = 0;
		this.YVel = 0;
		
		this.speed = 4;
		this.speedTimer = 0;
		this.shieldIs = false;
		this.numberOfShileds = 0;
		this.shieldTimer = 0;
		
		this.MOVING_DOWN = false;
		this.MOVING_LEFT = false;
		this.MOVING_RIGHT = false;
		this.MOVING_DOWN = false;
	}
	Player(int xpos, int ypos,int wid, int height, int xvel, int yvel,int s)
	{
		this.Xpos = xpos;
		this.Ypos = ypos;
		this.Width = wid;
		this.Height = height;
		this.XVel = xvel;
		this.YVel = yvel;
		
		this.speed = s;
		this.speedTimer = 0;
		this.shieldIs = false;
		this.numberOfShileds = 0;
		this.shieldTimer = 0;
		
		this.MOVING_DOWN = false;
		this.MOVING_LEFT = false;
		this.MOVING_RIGHT = false;
		this.MOVING_DOWN = false;
		
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
		this.XVel = xv;
	}
	public int getXvel()
	{
		return this.XVel;
	}
	public void setYvel(int yv)
	{
		this.YVel = yv;
	}
	public int getYvel()
	{
		return this.YVel;
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
	public void setSpeed(int s)
	{
		this.speed = s;
	}
	public int getSpeed()
	{
		return this.speed;
	}
	public void setSpeedTimer(int st)
	{
		this.speedTimer = st;
	}
	public int getSpeedTimer()
	{
		return this.speedTimer;
	}
	public void resetSpeed()
	{
		this.speed = 2;
	}
	public void reduceSpeedTimer()
	{
		if(this.speedTimer>0)
		{
			this.speedTimer--;
		}
	}
	public boolean checkSpeed()
	{
		if(this.speedTimer==0&&this.speed>2)
		{
			this.speed/=2;
			return true;
		}
		return false;
	}
	public void setShieldTimer(int s)
	{
		this.shieldTimer = s;
	}
	public int getShieldTimer()
	{
		return this.shieldTimer;
	}
	public boolean haveShield()
	{
		return this.shieldIs;
	}
	public void removeShield()
	{
		this.shieldIs = false;
	}
	public void isMovingRight()
	{
		this.MOVING_DOWN = false;
		this.MOVING_LEFT = false;
		this.MOVING_RIGHT = true;
		this.MOVING_UP = false;
	}
	public void isMovingLeft()
	{
		this.MOVING_DOWN = false;
		this.MOVING_LEFT = true;
		this.MOVING_RIGHT = false;
		this.MOVING_UP = false;
	}
	public void isMovingUp()
	{
		this.MOVING_DOWN = false;
		this.MOVING_LEFT = false;
		this.MOVING_RIGHT = false;
		this.MOVING_UP = true;
	}
	public void isMovingDown()
	{
		this.MOVING_DOWN = true;
		this.MOVING_LEFT = false;
		this.MOVING_RIGHT = false;
		this.MOVING_UP = false;
	}
	public void setShield(boolean ion, int numOfShields)
	{
		this.shieldIs = ion;
		this.numberOfShileds = numOfShields;
	}
	public void moveThePlayer()
	{
		this.Xpos+=this.XVel;
		this.Ypos+=this.YVel;
	}
	public boolean isMovingRight1()
	{
		if(this.MOVING_RIGHT)
		{
			return true;
		}
		return false;
	}
	public boolean isMovingDown1()
	{
		if(this.MOVING_DOWN)
		{
			return true;
		}
		return false;
	}
	public boolean isMovingLeft1()
	{
		return (this.MOVING_LEFT)?true:false;
	}
	public boolean isMovingUp1()
	{
		return (this.MOVING_UP)?true:false;
	}
	public int whichDirection()
	{
		if(this.isMovingDown1())
		{
			return 4;
		}
		else if(this.isMovingLeft1())
		{
			return 3;
		}
		else if(this.isMovingUp1())
		{
			return 2;
		}
		else if(this.isMovingRight1())
		{
			return 1;
		}
		return 0;
	}
	public void drawShiled(Graphics2D g)
	{
		if(shieldIs)
		{
			g.setStroke(new BasicStroke(10));
			if(MOVING_LEFT)
			{
				g.setColor(Color.CYAN);
				g.drawRect(this.Xpos, this.Ypos, this.Width*(this.numberOfShileds+1), this.Height);
			}
			else if(MOVING_RIGHT)
			{
				g.setColor(Color.BLUE);
				g.drawRect(this.Xpos-(this.numberOfShileds*this.Width), this.Ypos, this.Width*(this.numberOfShileds+1), this.Height);
			}
			else if(MOVING_UP)
			{
				g.setColor(Color.MAGENTA);
				g.drawRect(this.Xpos, this.Ypos, this.Width, this.Height*(this.numberOfShileds+1));
			}
			else if(MOVING_DOWN)
			{
				g.setColor(Color.GREEN);
				g.drawRect(this.Xpos, this.Ypos-(this.numberOfShileds*this.Height), this.Width, this.Height*(this.numberOfShileds+1));
			}
			
		}
	}
	public void keepThePlayerInside_KoochikGame()
	{
		if(this.getXpos()<=0)
		{
			this.setXpos(0);
			this.setXvel(0);
		}
		if(this.getXpos()>=Size.Width-46)
		{
			this.setXpos(Size.Width-46);
			this.setXvel(0);
			
			
		}
		if(this.getYpos()<=0)
		{
			this.setYpos(0);
			this.setYvel(0);
		}
		if(this.getYpos()>=Size.Height-65)
		{
			this.setYpos(Size.Height-65);
			this.setYvel(0);
		}
	}
	
	public static int createThePlayerWidthHeight(int lev)
	{
		return lev*(4)+60;
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.getHSBColor(0,250, 225));
		g.fillRoundRect(this.Xpos, this.Ypos, this.Width, this.Height,10,10);
		this.drawShiled((Graphics2D)g);
	}
	
}
