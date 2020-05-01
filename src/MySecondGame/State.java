package MySecondGame;

public class State {
	
	public static Situation state;
	
	State()
	{
		state = Situation.MENU;
	}
	
	public static void Game()
	{
		state = Situation.GAME;
	}
	public static void Lost()
	{
		state = Situation.YOU_LOST;
	}
	public static void Won()
	{
		state = Situation.YOU_WON;
	}
	public static void Menu()
	{
		state = Situation.MENU;
	}
	public static void AnotherWorld()
	{
		state = Situation.ANOTHER_WORLD;
	}
	public static boolean isGame()
	{
		return (state==Situation.GAME)?true:false;
	}
	public static boolean isMenu()
	{
		return (state==Situation.MENU)?true:false;
	}
	public static boolean isLost()
	{
		return (state==Situation.YOU_LOST)?true:false;
	}
	public static boolean isWon()
	{
		return (state==Situation.YOU_WON)?true:false;
	}
	public static boolean isAnotherWorld()
	{
		return (state==Situation.ANOTHER_WORLD)?true:false;
	}
}
