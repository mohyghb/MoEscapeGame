package MySecondGame;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;

public class EscapeTail {
	//add set and get
	
	private int Xpos;
	private int Ypos;
	private int XVel;
	private int YVel;
	private int deleted;
	private int removeVelocity;
	public int timer;
	private int speed;
	private Image currImage;
	
	public Image bombImage = Support.getImage1("bombd.png");
	public Image bomb1Image = Support.getImage1("bombd1.png");
	public Image bomb2Image = Support.getImage1("bombd2.png");
	public Image bomb3Image = Support.getImage1("bombd3.png");
	public Image bomb4Image = Support.getImage1("bombd4.png");
	public Image explosion = Support.getImage1("explosiond.png");
	public Image explosion1 = Support.getImage1("explosiond1.png");
	
	private boolean control;
	File explosionSFX = Support.getFile("explosionSFX.wav");
	File explosionSFX1 = Support.getFile("explosionSFX1.wav");
	File beep = Support.getFile("Beep.wav");
	
	AudioCreator ac = new AudioCreator();
	
	EscapeTail(int Xplayer,int Yplayer){
		currImage = bombImage;
		Ypos = Yplayer;
		Xpos = Xplayer;
		XVel = 0;
		YVel = 0;
		deleted = 0;
		timer = 0;
		removeVelocity = 0;
		speed = 2;
		this.control = false;
	}
	
	EscapeTail(){
		currImage = bombImage;
		Ypos = 0;
		Xpos = 0;
		XVel = 0;
		YVel = 0;
		deleted = 0;
		timer = 0;
		removeVelocity = 0;
		speed = 2;
		this.control = false;
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
	public void setXVel(int xv)
	{
		this.XVel = xv;
	}
	public int getXVel()
	{
		return this.XVel;
	}
	public void setYVel(int yv)
	{
		this.YVel = yv;
	}
	public int getYVel()
	{
		return this.YVel;
	}
	public void deleteThisTail()
	{ 
		this.deleted = 1;
	}
	public void setTIMER(int t)
	{
		this.timer = t;
	}
	public int getTIMER()
	{
		return this.timer;
	}
	public int getRemoveVelocity()
	{
		return this.removeVelocity;
	}
	public void setRemoveVelocity(int rv)
	{
		this.removeVelocity  = rv;
	}
	public void setCurrImage(Image img)
	{
		this.currImage = img;
	}
	public void paint(Graphics g,ImageObserver b)
	{
		if(is()&&currImage!=null)
		{
		g.drawImage(currImage, this.Xpos, this.Ypos,b);
		}
		//initial drawings
//		g.setColor(Color.black);
//		g.fillOval(this.Xpos, this.Ypos, this.Width, this.Height);
//		g.setColor(Color.red);
//		g.fillOval(this.Xpos, this.Ypos, 5, 10);
	}
	public boolean is()
	{
		if(this.deleted==0)
		{
			return true;
		}
		return false;
	}
	public boolean shouldBeMoving()
	{
		if(this.removeVelocity==0)
		{
			return true;
		}
		return false;
	}
	public void removeAllVelocities()
	{
		this.XVel = 0;
		this.YVel = 0;
		this.removeVelocity = 1;
	}
	public void changeTheBombColor()
	{
		if(is())
		{
		if(this.timer>11)
		{
			currImage = explosion1;
			if(control==false)
			{
				this.control = true;
				ac.PlayAudio("explosionSFX1.wav");
			}		
		}
		else if(this.timer>=10)
		{
			currImage = explosion;
			ac.PlayAudio("explosionSFX.wav");
		}
		else if(this.timer>=8&&this.timer<10)
		{
			currImage = bomb4Image;
			if(this.shouldBeMoving())
			{
			ac.PlayAudio("Beep.wav");
			ac.PlayAudio("Beep.wav");
			ac.PlayAudio("Beep.wav");
			}
		}
		else if(this.timer>=6&&this.timer<8)
		{ 
			currImage = bomb3Image;
			if(this.shouldBeMoving())
			{
			ac.PlayAudio("Beep.wav");
			}
		}
		else if(this.timer>=4&&this.timer<6)
		{
			currImage = bomb2Image;
			if(this.shouldBeMoving())
			{
			ac.PlayAudio("Beep.wav");
			}
		}
		else if(this.timer>=2&&this.timer<4)
		{
			if(this.shouldBeMoving())
			{
			currImage = bomb1Image;
      		ac.PlayAudio("Beep.wav");
			}
		}
		}
	}
	public int getWidth(ImageObserver n)
	{
		return currImage.getWidth(n);
	}
	public int  getHeight(ImageObserver n)
	{
		return currImage.getHeight(n);
	}
	public void setSpeed(int s)
	{
		this.speed = s;
	}
	public int getSpeed()
	{
		return this.speed;
	}
	public void checkSpeed()
	{
			this.speed/=2;
	}


}
