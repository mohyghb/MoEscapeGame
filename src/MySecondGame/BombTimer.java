package MySecondGame;

public class BombTimer {
	
	public int addTime;
	
	BombTimer()
	{
		this.addTime = 0;
	}
	
	public void addTime()
	{
		addTime+=Support.DELAY;
	}
	public boolean isSecond()
	{
		if(this.addTime>=Support.aSecond)
		{
			this.addTime-=1000;
			return true;
		}
		return false;
	}

}
