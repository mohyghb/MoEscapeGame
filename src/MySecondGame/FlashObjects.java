package MySecondGame;

public class FlashObjects {
	
	private int time;
	private int add;
	private int value;
	private boolean backMot;
	
	FlashObjects(int add,int value)
	{
		this.time = 0;
		this.add = add;
		this.value = value;
		this.backMot = false;
	}
	public void setAdd(int add)
	{
		this.add = add;
	}
	public int timeIsUp()
	{
		
		if(this.time>=value)
		{
			this.time = 0;
			this.backMot =!backMot;
			if(backMot)
			{
				return 1;
			}
			else if(!backMot)
			{
				return 2;
			}
			
		}
		return 0;
		
	}
	public int getTime()
	{
		return this.time;
	}
	public void addTime()
	{
		this.time+=add;
	}
	public void resetTimer(){
		time = 0;
	}
			

}
