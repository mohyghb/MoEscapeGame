package MySecondGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BackgroundTimer implements Draw {

	private int timer ;
	BackgroundTimer()
	{
		this.timer = 10;
	}
	public void minusSec()
	{
		this.timer--;
	}
	public boolean won()
	{
		return (this.timer<=0)?true:false;
	}
	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.red);
		g.setFont(new Font("Serfi",Font.PLAIN,100));
		g.drawString(String.format("%d", this.timer), Size.Width/2, Size.Height/2);

	}

}
