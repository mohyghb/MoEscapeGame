package MySecondGame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class KoochikPlayer extends Player implements Draw{

	private boolean playerHasTheDeffuser;
	private boolean createTheDefuser;
	private int halfSize;
	private boolean playerIsInThisWorld;
	private int playerHealth;
	// addd level spped
	
	KoochikPlayer(int level)
	{
		super(createThePlayerWidthHeight(level));
		this.playerHasTheDeffuser = false;
		this.halfSize = super.getWidth()/2;
		this.createTheDefuser = false;
		this.playerIsInThisWorld = true;
		this.playerHealth = Size.Width;
	}
	
	public void playerGotTheDeffuser()
	{
		this.playerHasTheDeffuser = true;
	}
	public boolean playerHasTheDeffuser()
	{
		return this.playerHasTheDeffuser;
	}
	public void setHeightWidth(int hw)
	{
		this.setWidth(this.getWidth()+hw);
		this.setHeight(this.getHeight()+hw);
	}
	public boolean getCreateDefuser()
	{
		return this.createTheDefuser;
	}
	public void playerIsNotInThisWorld()
	{
		this.playerIsInThisWorld = false;
	}
	public void reducePlayerHealth(int h)
	{
		this.playerHealth-=h;
	}
	public int getHealth()
	{
		return this.playerHealth;
	}
	
	@Override
	public void draw(Graphics g) {
		if(!this.playerIsInThisWorld)
		{
//			g.setColor(Color.getHSBColor(0, 300, 100));
			g.setColor(Color.green);
			g.fillRect(0, 0, this.playerHealth, Size.Height);
			super.draw(g);
		}
		
		if(this.playerIsInThisWorld)
		{
			super.draw(g);
		
		if(this.getWidth()<=this.halfSize)
		{
			g.setColor(Color.green);
			this.createTheDefuser = true;
		}
		else
		{
		g.setColor(Color.LIGHT_GRAY);
		this.createTheDefuser = false;
		}
		g.setFont(new Font("Serfi",Font.BOLD,100));
		g.drawString(String.format("%d",super.getWidth()), Size.Width/2+120, Size.Height/2);
		g.drawString(String.format("%d", halfSize), Size.Width/2-290, Size.Height/2);
		
		
		g.setFont(new Font("Serfi",Font.PLAIN,50));
		g.drawString("YOUR WIDTH", Size.Width/2,Size.Height/2+40);
		g.drawString("GOAL", Size.Width/2-300, Size.Height/2+40);
		}
		
	}
	
	

}
