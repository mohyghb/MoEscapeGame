package MySecondGame;

import java.awt.Color;
import java.awt.Graphics;

public class LightFlashBackground {
	
	private Color currColor; 
	private int timer; 
	private boolean changer;
	
	LightFlashBackground(Color c)
	{
		this.currColor = c;
		this.timer = 0;
		this.changer = true;
	}
	
	public void drawFlash(Graphics g)
	{
		g.setColor(currColor);
		g.fillRect(0, 0, Size.Width, Size.Height);
	}
	
	public void changeColorBackground()
	{
		timer+=Support.DELAY;
		if(timer>=500)
		{
			if(changer)
			{
				this.currColor = Color.red;
				this.changer = false;
			}
			else if(!changer)
			{
				this.currColor = Color.white;
				this.changer = true;
			}
			timer-=500;
		}
	}

}
