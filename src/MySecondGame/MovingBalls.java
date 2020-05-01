package MySecondGame;

import java.awt.Color;
import java.awt.Graphics;

public class MovingBalls {
	
	private int Xpos;
	private int Ypos;
	private int Yvel;
	private int Xvel;
	private int Width;
	private int Height;
	private Color saveColor;
	private boolean drawORfill;
	
	MovingBalls(boolean dof)
	{
		this.Xpos = Support.giveMeARandomX();
		this.Ypos = Support.giveMeARandomY();
		this.Width = Support.rand.nextInt(30)+10;
		this.Height = this.Width;
		this.Xvel = 2;
		this.Yvel = 2;
		this.drawORfill = dof;
		this.saveColor =Menu.RANDOMCOLORLIST[Support.rand.nextInt(12)];
	}
	public void draw(Graphics g)
	{
		g.setColor(saveColor);
		if(drawORfill)
		{
			g.drawOval(this.Xpos, this.Ypos, this.Width, this.Height);
		}
		else{
			g.fillOval(this.Xpos, this.Ypos, this.Width, this.Height);
		}
	}
	public void moveTheBall()
	{
		this.Xpos+=this.Xvel;
		this.Ypos+=this.Yvel;
	}
	public void keepItInside()
	{
		if(this.Xpos<=0)
		{
			this.Xvel = -this.Xvel;
		}
		else if(this.Xpos>=Size.Width-60)
		{
			this.Xvel = -this.Xvel;
		}
		if(this.Ypos<=0)
		{
			this.Yvel = -this.Yvel;
		}
		else if(this.Ypos>=Size.Height-60)
		{
			this.Yvel = -this.Yvel;
		}
	}
	
}
