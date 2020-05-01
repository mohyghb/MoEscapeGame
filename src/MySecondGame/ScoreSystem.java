package MySecondGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ScoreSystem {
	
	private String name;
	private int level;
	
	ScoreSystem(int l,String n)
	{
		this.name = n;
		this.level = l;
	}
	
	public boolean isHigher(int currl)
	{
		return (this.level<currl)?true:false;
	}
	
	public String saveString()
	{
		StringBuilder saveStr = new StringBuilder();
		saveStr.append(String.format("%s|%d\n",this.name,this.level));
		
		return saveStr.toString();
	}
	
	public void readLine(String line)
	{
		if(line.contains("|")&&line.contains("~"))
		{
			String trans[] = line.split(Support.CRLFStr);
			String line1 = trans[0];
			String acc[] = line1.split(Support.parDelim);
			
			if(!acc[1].isEmpty()&&acc[0].length()>4)
			{
				char array[] = acc[0].toCharArray();
				char newArray[] = new char[4];
				for(int i = 0;i<4;i++)
				{
					newArray[i] = array[i];
				}
				this.name = new String(newArray);
			}
			else if(!acc[1].isEmpty()&&acc[0].length()<=4)
			{
				this.name = acc[0];
			}
			if(!acc[1].isEmpty()&&Integer.parseInt(acc[1])>=100)
			{
				this.level = 0;
			}
			else if(!acc[1].isEmpty()&&Integer.parseInt(acc[1])<100)
			{
				this.level = Integer.parseInt(acc[1]);
			}
		}
		
				
		
	}
	public void draw(Graphics g)
	{
		if(this.level>0)
		{
			g.setFont(new Font("Serfi",Font.PLAIN,50));
			g.setColor(Color.lightGray);
			g.drawString("HIGH SCORE: ", Size.Width/2-320,Size.Height/2+180);
			g.drawString(this.name, Size.Width/2+30, Size.Height/2+180);
			
			g.drawString(String.format("///%d///", this.level), Size.Width/2+160,Size.Height/2+180);
		}
		
	}
	

}
