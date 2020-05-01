package MySecondGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class KoochikBomb extends EscapeTail {
		
	private int timer;
	
	KoochikBomb(int lev)
	{
		super();
		setTimer(lev);
	}
	public void setTimer1(int n)
	{
		this.timer = n;
	}
	public void setTimer(int lev)
	{
		 for(int i = 0;i<40;i++)
		 {
			 if(i==lev)
			 {
				 if(i<=6)
				 {
					 this.timer = (i*15)-((i-1)*10); 
				 }
				 else 
				 {
					 this.timer = 40;
				 }
			 }
		 }
	}
	public void reduceTime()
	{
		this.timer--;
	}
	public boolean getDeath()
	{
		return (this.timer==0)?true:false;
	}
	public void draw(Graphics g,ImageObserver n)
	{
		g.setColor(Color.black);
		g.fillOval(0, 0, 100, 100);
		g.fillOval(80, 10, 15, 15);
		g.setColor(Color.red);
		g.fillOval(90, 6,10,12);
		g.setColor(Color.orange);
		g.fillOval(94, 8,4,6);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Serfi",Font.ITALIC,30));
		g.drawString(String.format("%d", timer),30 , 60);
		
		
	}
}
