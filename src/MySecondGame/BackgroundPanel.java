package MySecondGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.imageio.ImageIO;

public class BackgroundPanel {
	
	private int Timer;
	private int Tail;
	private int Width;
	private int Height;
	
	private Image speed = Support.getImage1("SpeedUpd.png");	
	private int howManySpeed;
	
	private Image shield =Support.getImage1("Shieldd.png");
	private int howManyShield;
	
	private int level;
	
	BackgroundPanel(int t, int ta, int s, int sh,int l)
	{
		this.Timer = t;
		this.Tail = ta;
		this.Width = Size.Width;
		this.Height = Size.Height/12;
		this.howManySpeed = s;
		this.howManyShield = sh;
		this.level = l;
//		this.setImageResource("Shield.png", "SpeedUp.png");
	}
	
	public void draw(Graphics g,ImageObserver n)
	{
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.Width, this.Height);
		
		
		
		g.setColor(Color.black);
		g.setFont(new Font("Serfi",Font.BOLD,20));
		g.drawString(String.format("Bombs Left: %d", this.Tail),13 ,Size.Height/30+5 );
		
		g.setColor(Color.PINK);
		g.setFont(new Font("Serfi",Font.BOLD,20));
		g.drawString(String.format("LEVEL: %d", this.level), 13, Size.Height/15+5);
		
		g.setColor(Color.red);
		g.setFont(new Font("Serfi",Font.BOLD+Font.ITALIC,50));
		g.drawString(String.format("%d", this.Timer), Size.Width/2,50);
		
		if(this.shield!=null&&this.speed!=null)
		{
			g.drawImage(this.speed, Size.Width-200,3,35,35,n);
		g.setColor(Color.white);
		g.setFont(new Font("Serfi",Font.BOLD,19));
		g.drawString(String.format("%d", howManySpeed), Size.Width-187, 53);
		
		g.drawImage(this.shield, Size.Width-150,2,35,35,n);
		g.drawString(String.format("%d", howManyShield), Size.Width-138, 53);
		}
		
		
	}
	public void setTimer(int s)
	{
		this.Timer = s;
	}
	public void setTail(int s)
	{
		this.Tail = s;
	}
	public void setHeight(int h)
	{
		this.Height = h;
	}
	public int getHeight()
	{
		return this.Height;
	}
	public int getWidth()
	{
		return this.Width;
	}
	public void setWidth(int w)
	{
		this.Width  = w;
	}
	public void setSpeedCounter(int s)
	{
		this.howManySpeed = s;
	}
	public int getSpeedCounter()
	{
		return this.howManySpeed;
	}
	public void setShiledCounter(int s)
	{
		this.howManyShield = s;
	}
	public int getShiledCounter()
	{
		return this.howManyShield;
	}
//	public void setImageResource(String path,String path1) 
//	{
//		try {
//			this.shield = ImageIO.read(this.getClass().getResourceAsStream(path));
//			this.speed = ImageIO.read(this.getClass().getResourceAsStream(path1));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
