package MySecondGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class KoochikPowerUp extends Particle {
	
	private Color color;
	private static final Color[] colorList = {Color.yellow,Color.black,Color.magenta,Color.blue} ; 
	public static enum situation {YELLOW,BLACK,MAGENTA,BLUE};
	public situation state;
	
	KoochikPowerUp()
	{
		super();
		this.color = colorList[Support.rand.nextInt(4)];
		setSituation();
	}
	public void setSituation()
	{
		if(this.color==Color.yellow)
		{
			state = situation.YELLOW;
		}
		else if(this.color==Color.black)
		{
			state = situation.BLACK;
		}
		else if(this.color==Color.magenta)
		{
			state = situation.MAGENTA;
		}
		else if(this.color==Color.blue)
		{
			state = situation.BLUE;
		}
	}

	public void draw(Graphics2D g2d)
	{
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(this.color);
		g2d.drawRect(getXpos(), getYpos(), getWidth(), getHeight());
	}

}
