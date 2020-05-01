package MySecondGame;

public class myOwnTimer {
	
	private int Timer;
	private int addMilli;
	private int milliCounter;
	
	myOwnTimer()
	{
		this.Timer = 0;
		this.addMilli = 0;
		this.milliCounter = 0;
	}
	
	public int getTime()
	{
		return this.Timer;
	}
	public void setTime(int t)
	{
		this.Timer = t;
	}
	public void setAddMilli(int st)
	{
		this.addMilli = st;
	}
	public int getAddMilli()
	{
		return this.addMilli;
	}
	public int getMilliCounter()
	{
		return this.milliCounter;
	}
	public void setMilliCounter(int mc)
	{
		this.milliCounter = mc;
	}
	
	public boolean isSecond()
	{
		if(this.milliCounter>=1000)
		{
			this.milliCounter-=1000;
			this.Timer++;
			return true;
		}
		return false;
	}
	

}
