package MySecondGame;

/*
 * make the shiled tutorial to work and remove the random power up drops
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Tutorial_Classic extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	private int randomXposDeffuse;
	private int randomYposDeffuse;
	private int t1 = 10;
	private static int tail;
	private static int initialTail = tail;
	private Image dbImage;
	private Graphics dbGraphics;
	private Timer timer;
	
	//tutorial variables
	static boolean play = false;
	static int totaltime = 1;
	static boolean JDialogIsUP = false;
	ShowMessage_Classic showm = new ShowMessage_Classic("","",Color.black,0,0,0,0);
	
	Player player;
	ArrayList<EscapeTail> tails = new ArrayList<EscapeTail>();
	SpeedUp powerSpeed;
	ShieldUp powerShield;
	Deffuser deffuse;
	SpeedUp speedPower;
	myOwnTimer Time;
	myOwnTimer Time1;
	BombTimer bombTimer;
	BackgroundPanel backPanel;
	LightFlashBackground flashyBackground;
	LostTheTutorial lostthetutorial;
	Endoftutorial endoftutorial;
	
//	AudioCreator audioCreator;
	
	
	File backMusic = new File("backEscape.wav");
	File lostMusic = new File("Gameover.wav");
	
	
	Tutorial_Classic()
	{
		initGame();
	}
	
	public void paint(Graphics g)
	{
		dbImage = createImage(Size.Width,Size.Height);
		dbGraphics  = dbImage.getGraphics();
		paintComponent(dbGraphics);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void paintComponent(Graphics g)
	{
		switch(State.state)
		{
		default:
			break;
			
		case GAME:
		
		flashyBackground.drawFlash(g);
		backPanel.draw(g,this);
		player.draw(g);
		powerSpeed.drawTheSpeedUp(g, this);
		powerShield.drawTheShieldUp(g, this);
		deffuse.drawDeffuser(g, this);
		drawTail(g);
		showm.draw(deffuse.getXpos(),deffuse.getYpos(),Tutorial_Classic.totaltime,(Graphics2D)g);
		
		break;
		
		
		case YOU_LOST:
			lostthetutorial.draw((Graphics2D)g);
			break;
			
		case YOU_WON:
			endoftutorial.draw((Graphics2D)g);
			break;
		}
		g.dispose();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(!play&&State.isGame())
		{
			Time1.setMilliCounter(Time1.getMilliCounter()+Time1.getAddMilli());
			thingsOnTime1();
		}
		if(play&&State.isGame()&&!Tutorial_Classic.JDialogIsUP)
		{
			
			bombTimer.addTime();
			addOneSpeedOrShield();
			changeTheBackgroundColor();
			addTimeToOtherThings();
			movePlayerAndTail();
			keepThePlayerInside();
			playerHitsObjects();
			CreateDeffuserEachSixSecond();
			Time.setMilliCounter(Time.getMilliCounter()+Time.getAddMilli());
			TimeAndDeffuse();
			checkDeath();
			checkDeathWithTheDropedBombs();
			checkWon();
		}
		
		repaint();
	}
	
	public void initGame()
	{
			initTail();
			timer = new Timer(Support.DELAY,this);
			
			player  = new Player();
			Time = new myOwnTimer();
			Time.setAddMilli(Support.DELAY);
			initTimer1();
			initLostTheTutorial();
			initEndOfTutorial();
			bombTimer = new BombTimer();
			backPanel = new BackgroundPanel(t1,tail,0,0,1);
			flashyBackground = new LightFlashBackground(Color.white);
			powerSpeed = new SpeedUp(0,0,this);
			powerShield = new ShieldUp(0,0,this);
//			audioCreator = new AudioCreator();
//			
//			audioCreator.PlayAudio(backMusic);
			createDeffuser();
			addKeys();
			createTail();
			
			setVisible(true);
			setFocusable(true);
			timer.start();
		
	}
	public void initEndOfTutorial()
	{
		this.endoftutorial = new Endoftutorial();
	}
	public void initLostTheTutorial()
	{
		this.lostthetutorial = new LostTheTutorial();
	}
	public void createTail()
	{
		for(int i = 1;i<=tail;i++){
			EscapeTail t = new EscapeTail((player.getXpos()-(i*player.getWidth())),player.getYpos()-5);
			tails.add(t);
		}
	}

	public void drawTail(Graphics g)
	{
		for(EscapeTail t:tails)
		{
			if(t.is())
			{
				t.paint(g,this);
			}
		}
	}
	
	public void addKeys()
	{
		Support.addKeyBinding(this, KeyEvent.VK_RIGHT, "Move Right", (evt)->{
			if(State.state==Situation.GAME)
			{
			play = true;
			player.setXvel(player.getSpeed());
			player.setYvel(0);
			player.isMovingRight();
			int i = 1;
			for(EscapeTail t: tails)
			{
				if(t.is()&&t.shouldBeMoving())
				{
				t.setYVel(0);
				t.setXVel(t.getSpeed());
				t.setYpos(player.getYpos()-5);
				t.setXpos(player.getXpos()-(i*player.getWidth()));
				i++;
				}
			}
			}
		}, false);
		
		Support.addKeyBinding(this, KeyEvent.VK_LEFT, "Move Left", (evt)->{
			if(State.state==Situation.GAME)
			{
			play = true;
			player.setXvel(-player.getSpeed());
			player.setYvel(0);
			player.isMovingLeft();
			int i = 1;
			for(EscapeTail t:tails)
			{
			if(t.is()&&t.shouldBeMoving())
				{
				t.setYVel(0);
				t.setXVel(-t.getSpeed());
				t.setYpos(player.getYpos()-5);
				t.setXpos(player.getXpos()+(i*player.getWidth()));
				i++;
				}
			}
			}
		}, false);
		
		Support.addKeyBinding(this, KeyEvent.VK_UP, "Move Up", (evt)->{
			if(State.state==Situation.GAME)
			{
			play = true;
			player.setYvel(-player.getSpeed());
			player.setXvel(0);
			player.isMovingUp();
			int i = 1;
			for(EscapeTail t:tails)
			{
			if(t.is()&&t.shouldBeMoving())
				{
				t.setXVel(0);
				t.setYVel(-t.getSpeed());
				t.setXpos(player.getXpos());
				t.setYpos(player.getYpos()+(i*player.getHeight()-6));
				i++;
				}
			}
			}
		}, false);
		
		Support.addKeyBinding(this, KeyEvent.VK_DOWN, "Move Down", (evt)->{
			if(State.state==Situation.GAME)
			{
			play = true;
			player.setYvel(player.getSpeed());
			player.setXvel(0);
			player.isMovingDown();
			int i = 1;
			for(EscapeTail t:tails)
			{
			if(t.is()&&t.shouldBeMoving())
				{
				t.setYVel(t.getSpeed());
				t.setXVel(0);
				t.setXpos(player.getXpos());
				t.setYpos(player.getYpos()-(i*player.getHeight()+5));
				i++;
				}
			}
			}
		}, false);
		
		//Cheat
		Support.addKeyBinding(this, KeyEvent.VK_C, "Create deffuser", (evt)->{
			createDeffuser();
		}, false);
		
		//reset
		Support.addKeyBinding(this, KeyEvent.VK_ENTER, "Reset", (evt)->{
			if(State.isLost())
			{
				resetTheWholeGame();
			}
			
		}, false);
		
		//powerUPS
		Support.addKeyBinding(this, KeyEvent.VK_SPACE,"Speed up", (evt)->{
			
		if(backPanel.getSpeedCounter()>0&&State.isGame())
			{
				if(totaltime==14)
				{
					totaltime++;
				}
				speedUp();
			}	
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_S, "Shield", (evt)->{
			if(backPanel.getShiledCounter()>0&&State.isGame())
			{
				if(totaltime==18)
				{
					totaltime++;
					createAMessage(totaltime);
				}
				player.setShield(true, tail);
				backPanel.setShiledCounter(backPanel.getShiledCounter()-1);
			}
		}, false);
	}
	public void initTimer1()
	{
		Time1 = new myOwnTimer();
		Time1.setAddMilli(Support.DELAY);
	}
	public void thingsOnTime1()
	{
		if(Time1.isSecond())
		{
			showm.flash();
		}
	}
	public void movePlayerAndTail()
	{
		player.setYpos(player.getYvel()+player.getYpos());
		player.setXpos(player.getXvel()+player.getXpos());
		
		for(EscapeTail t:tails)
		{
			if(t.is())
			{
				t.setXpos(t.getXVel()+t.getXpos());
				t.setYpos(t.getYVel()+t.getYpos());
			}
		}
	}
	
	public void playerHitsObjects() 
	{
		if(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight()).intersects(new Rectangle(deffuse.getXpos(),deffuse.getYpos(),deffuse.getWidth(this),deffuse.getHeight(this))))
		{
			
			if(deffuse.is())
			{
				deleteTheLastBomb();
				if(Tutorial_Classic.totaltime<9)
				{
					Tutorial_Classic.totaltime++;
				}
				
				createAMessage(totaltime);
			}
			deffuse.deleteThisDeffuser();
		}
		if(powerSpeed.is()&&new Rectangle(powerSpeed.getXpos(),powerSpeed.getYpos(),powerSpeed.getWidth(),powerSpeed.getHeight()).intersects(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
		{
			backPanel.setSpeedCounter(backPanel.getSpeedCounter()+1);
			powerSpeed.setIsOrNot(2);
		}
		if(powerShield.is()&&new Rectangle(powerShield.getXpos(),powerShield.getYpos(),powerShield.getWidth(),powerShield.getHeight()).intersects(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
		{
			powerShield.setIsOrNot(4);
			backPanel.setShiledCounter(backPanel.getShiledCounter()+1);
		}
	}
	
	public void makeDeffuserInside(int x, int y)
	{
		if(x>=1000)
		{
			randomXposDeffuse-=70;
		}
		if(y>=600)
		{
			randomYposDeffuse-=80;
		}
	}
	
	public void createDeffuser()
	{
		randomXposDeffuse = Support.rand.nextInt(Size.Width);
		randomYposDeffuse = Support.rand.nextInt(Size.Height);
		makeDeffuserInside(randomXposDeffuse,randomYposDeffuse);
		deffuse = new Deffuser(randomXposDeffuse,randomYposDeffuse);
	
	}
	
	public void deleteTheLastBomb()
	{
		int i = 0;
		for(EscapeTail k:tails)
		{
			if(k.is()&&i==tail-1)
			{
				k.removeAllVelocities();
				tail--;
				backPanel.setTail(tail);
			}
			else if(k.is())
			{
				i++;
			}
		}
	}
	public void TimeAndDeffuse()
	{
		if(Time.isSecond())
		{
			Time.isSecond();
			deffuse.setTIME(deffuse.getTIME()+1);
			player.reduceSpeedTimer();
			refreshShield();
			showm.flash();
			if(player.checkSpeed())
			{
				if(totaltime==15)
				{
					totaltime++;
					createAMessage(totaltime);
				}
				slowDownTail();
			}
		}
	}
	
	public void CreateDeffuserEachSixSecond()
	{
		if(deffuse.isTimeToChange())
		{
			createDeffuser();
		}
	}
	public void changeTheColorOfTheBombs()
	{
		int i = 0;
		for(EscapeTail t: tails)
		{
			if(i==tail-1||(t.getRemoveVelocity()==1&&t.timer<=10))
			{
				t.timer++;
				if(i==tail-1&&t.getRemoveVelocity()==0)
				{
					t1 = 10-t.timer;
					backPanel.setTimer(t1);
				}
				
				t.changeTheBombColor();
			}
			else
				i++;
		}
	}
	public void addTimeToOtherThings()
	{
		if(bombTimer.isSecond())
		{
			changeTheColorOfTheBombs();
			
		}
	}
	public void checkDeath()
	{
		int i = 0;
		for(EscapeTail t:tails)
		{
			if(i==tail-1&&t.getTIMER()>=10)
			{
				if(player.haveShield())
				{
					t.removeAllVelocities();
					tail--;
					backPanel.setTail(tail);
					player.removeShield();
				}
				else 
				{
					play = false;
					State.state = Situation.YOU_LOST;
//					playTheLostMusic();
				}
				
			}
			else 
				i++;
		}
	}
	public void checkDeathWithTheDropedBombs()
	{
		for(EscapeTail t:tails)
		{
			if(!t.shouldBeMoving()&&t.getTIMER()>=10&&new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight()).intersects(new Rectangle (t.getXpos(),t.getYpos(),t.getWidth(this),t.getHeight(this))))
			{
				if(player.haveShield())
				{
					t.setXpos(Support.giveMeARandomX());
					t.setYpos(Support.giveMeARandomY());
					t.deleteThisTail();
					player.removeShield();
				}
				else
				{
				play = false;
				State.state = Situation.YOU_LOST;
//				playTheLostMusic();
				
				}
				
			}
			else if(!t.shouldBeMoving()&&new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight()).intersects(new Rectangle (t.getXpos(),t.getYpos(),t.getWidth(this),t.getHeight(this))))
			{
				player.setXvel(0);
				player.setYvel(0);
				for(EscapeTail k:tails)
				{
					if(k.shouldBeMoving())
					{
						k.setXVel(0);
						k.setYVel(0);
					}
				}
			}
		}
	}
	public void checkWon()
	{
		if(tail==0)
		{
			play = false;
			State.state = Situation.YOU_WON; 
		}
	}
	public void changeTheBackgroundColor()
	{
		if(t1<=4)
		{
			flashyBackground.changeColorBackground();
		}
	}
	public void keepThePlayerInside()
	{
		if(player.getXpos()<=0)
		{
			player.setXpos(0);
			player.setXvel(0);
			
			for(EscapeTail t:tails)
			{
				if(t.shouldBeMoving())
				{
					t.setXVel(0);
				}
			}
		}
		if(player.getXpos()>=Size.Width-46)
		{
			player.setXpos(Size.Width-46);
			player.setXvel(0);
			
			for(EscapeTail t:tails)
			{
				if(t.shouldBeMoving())
				{
					t.setXVel(0);
				}
			}
		}
		if(player.getYpos()<=0)
		{
			player.setYpos(0);
			player.setYvel(0);
			
			for(EscapeTail t:tails)
			{
				if(t.shouldBeMoving())
				{
					t.setYVel(0);
				}
			}
		}
		if(player.getYpos()>=Size.Height-65)
		{
			player.setYpos(Size.Height-65);
			player.setYvel(0);
			
			for(EscapeTail t:tails)
			{
				if(t.shouldBeMoving())
				{
					t.setYVel(0);
				}
			}
		}
		if(new Rectangle(1,0,backPanel.getWidth(),backPanel.getHeight()).intersects(new Rectangle (player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
		{
			player.setXvel(0);
			player.setYvel(0);
			for(EscapeTail t:tails)
			{
				if(t.is()&&t.shouldBeMoving())
				{
					t.setXVel(0);
					t.setYVel(0);
				}
			}
			
			
		}
	}
	public void resetTheWholeGame()
	{
		timer.start();
		t1 = 10;
		initTail();
		Tutorial_Classic.play = false;
		Tutorial_Classic.totaltime = 1;
		createAMessage(Tutorial_Classic.totaltime);
		player  = new Player();
		Time = new myOwnTimer();
		Time.setAddMilli(Support.DELAY);
		initTimer1();
		bombTimer = new BombTimer();
		State.state = Situation.GAME;
		backPanel = new BackgroundPanel(t1,tail,0,0,1);
		flashyBackground = new LightFlashBackground(Color.white);
		tails = new ArrayList<EscapeTail>();
		initLostTheTutorial();
		initEndOfTutorial();
		createDeffuser();
		createTail();
//		resetBackMusic();
		
	}
	public void speedUpTheTail()
	{
		for(EscapeTail t:tails)
		{
			if(t.is()&&t.shouldBeMoving())
			{
				t.setSpeed(t.getSpeed()+t.getSpeed());
			}
		}
	}
	public void speedUp()
	{
			if(player.getSpeedTimer()<=0)
			{
				player.setSpeedTimer(4);
				speedUpTheTail();
				player.setSpeed(player.getSpeed()*2);
				backPanel.setSpeedCounter(backPanel.getSpeedCounter()-1);
			}		
	}
	public void slowDownTail()
	{
		for(EscapeTail t:tails)
		{
			if(t.is()&&t.shouldBeMoving())
			{
				t.checkSpeed();
			}
		}
	}
	public void makeAShield()
	{
		player.setShield(true, tail);
	}
	public void refreshShield()
	{
		if(player.haveShield())
		{
			player.setShield(true, tail);
		}
	}
//	public void resetBackMusic()
//	{
//		audioCreator.stop();
//		audioCreator.PlayAudio(backMusic);
//	}
//	public void stopTheMusic()
//	{
//		audioCreator.stop();
//	}
//	public void playTheLostMusic()
//	{
//		audioCreator.stop();
//		audioCreator.PlayAudio(lostMusic);
//	}
	public void stopTheTimer()
	{
		this.timer.stop();
	}
	public void startTimer()
	{
		this.timer.start();
	}
	@SuppressWarnings("static-access")
	public void makePlayFalse()
	{
		this.play = false;
	}
	public void initTail()
	{
		tail = 6;
		initialTail = tail;
	}
	public static void initMessage(String s,String s1,Color c,int x,int y,int w,int h)
	{
		play = false;
		Tutorial_Classic.JDialogIsUP = true;
		@SuppressWarnings("unused")
		ShowMessage_Classic showmessage = new ShowMessage_Classic(s,s1,c,x,y,w,h);
	}
	public void levelMaker()
	{
		tail = 5;
	}
	public void addOneSpeedOrShield()
	{
		if(totaltime==13)	
		{
			totaltime++;
			backPanel.setSpeedCounter(backPanel.getSpeedCounter()+1);
		}
		else if(totaltime==17)
		{
			totaltime++;
			backPanel.setShiledCounter(backPanel.getShiledCounter()+1);
		}
		
	}
	
	public static void createAMessage(int time)
	{
		switch(time)
		{
			default:
				Tutorial_Classic.JDialogIsUP = false;
				break;
			case 1:
				initMessage("Welcome to the tutorial","Let's go through the game together!",Color.yellow,Size.Width/2,Size.Height/2,240,70);
				break;
			case 2:
				initMessage("This is you!!!","You can move using the arrow keys",Color.green,Size.Width/2+150,Size.Height/2-50,240,60);
				break;
			case 3:
				initMessage(String.format("As you can see there are %d bombs attached to you",initialTail),"There is only one way to survive this!!",Color.ORANGE,Size.Width/2+150,Size.Height/2,290,70);
				break;
			case 4:
				initMessage("You need to get the defuser in order to"," defuse the bombs that are attached to you",Color.cyan,Size.Width/2,Size.Height/2,250,70);
				break;
			case 5:
				initMessage("Go aheah and pick up 3 defusers","",Color.yellow,Size.Width/2,Size.Height/2,250,40);
				break; 
			case 9:
				initMessage("Good job you did it!!!!!","As you can see you defused three bombs!",Color.green,Size.Width/2,Size.Height/2,270,70);
				break;
			case 10:
				initMessage("watch out though, if the bombs are exploded","on the ground they can still kill you!",Color.red,Size.Width/2,Size.Height/2,270,70);
				break;
			case 11:
				initMessage("but if they have not been exploaded then you won't die","",Color.green,Size.Width/2,Size.Height/2,310,40);
				break;
			case 12:
				initMessage("Press space to activate the speed booster!","Speed booster will only last for four seconds",Color.magenta,Size.Width/2,Size.Height/2,270,70);
				
				break;
			case 16:
				initMessage("Press 'S' to activate the shield","The shield does not have a timer on it!",Color.MAGENTA,Size.Width/2,Size.Height/2,270,70);
				break;
			case 19:
				initMessage("You can see how many speed and shield boosters"," you have over here",Color.yellow,Size.Width-300,100,300,70);
				break;
			case 20:
				initMessage("That's it",String.format("defuse %d more bombs in order to win!", tail),Color.CYAN,Size.Width/2,Size.Height/2,270,60);
				break;
			
		}
	}
	
	
	

	

}
