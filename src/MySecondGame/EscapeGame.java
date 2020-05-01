package MySecondGame;

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

import javax.swing.*;

public class EscapeGame extends JPanel implements ActionListener {
	
	/*
	 * the music doesn't stop after the player wins;
	 */
	
	private static final long serialVersionUID = 1L;
	private int randomXposDeffuse;
	private int randomYposDeffuse;
	private int t1 = 10;
	private int level;
	private int tail;
	private int initialTail = tail;
	private Image dbImage;
	private Graphics dbGraphics;
	private Timer timer;
	private boolean play = false;
	private myOwnTimer time1;
	Player player;
	ArrayList<EscapeTail> tails = new ArrayList<EscapeTail>();
	SpeedUp powerSpeed;
	ShieldUp powerShield;
	Deffuser deffuse;
	SpeedUp speedPower;
	myOwnTimer Time;
	BombTimer bombTimer;
	BackgroundPanel backPanel;
	LightFlashBackground flashyBackground;
	Guide_MoveToStartPlaying guide_move;
	AudioCreator ac = new AudioCreator();
	Won YouWon;
	Lost YouLost;
	@SuppressWarnings("unused")
	private ScoreSystemJDialog scoresystem;
	private ScoreSystem highScore;
	
//	File backMusic = new File("backEscape.wav");
	File lostMusic = Support.getFile("Gameover.wav");
	
	
	EscapeGame()
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
		highScore.draw(g);
		guide_move.draw(play, (Graphics2D)g,Size.Width/2-420,60);
		break;
		
		
		case YOU_LOST:
			YouLost.draw(g);
			break;
			
		case YOU_WON:
			YouWon.draw(g);
			break;
		}
		g.dispose();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(!this.play&&State.isGame())
		{
			time1.setMilliCounter(time1.getMilliCounter()+time1.getAddMilli());
			thingsOnTime1();
		}
		
		if(play&&State.isGame())
		{
			bombTimer.addTime();
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
			bombTimer = new BombTimer();
			backPanel = new BackgroundPanel(t1,tail,0,0,this.level);
			flashyBackground = new LightFlashBackground(Color.white);
			powerSpeed = new SpeedUp(0,0,this);
			powerShield = new ShieldUp(0,0,this);
			initHighScore();
			initTime1();
			createDeffuser();
			addKeys();
			initGuide_Move();
			createTail();
			
			setVisible(true);
			setFocusable(true);
			timer.start();
	}
	public void initScoreSystem(int l,int c)
	{
		this.scoresystem = new ScoreSystemJDialog(this.level,c);
	}
	public void initHighScore()
	{
		highScore =  new ScoreSystem(0,"null");
		if(Support.docsExist(Support.Classic))
		{
			String loadStr = Support.docsLoad(Support.Classic);
			highScore.readLine(loadStr);
		}
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
	public void initTime1()
	{
		this.time1 = new myOwnTimer();
		time1.setAddMilli(Support.DELAY);
	}
	public void initGuide_Move()
	{
		guide_move = new Guide_MoveToStartPlaying();
	}
	public void thingsOnTime1()
	{
		if(State.isGame()&&!this.play&&time1.isSecond())
		{
			guide_move.flash();
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
			switch(State.state)
			{
			default:
				break;
			case GAME:
				if(backPanel.getSpeedCounter()>0)
			{
				speedUp();
			}	
				break;
			case YOU_WON:
			
				nextLevel();
			
			break;
			}
			
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_S, "Shield", (evt)->{
			if(backPanel.getShiledCounter()>0&&State.isGame())
			{
				player.setShield(true, tail);
				backPanel.setShiledCounter(backPanel.getShiledCounter()-1);
			}
		}, false);
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
				
			}
			deffuse.deleteThisDeffuser();
		}
		if(powerSpeed.is()&&new Rectangle(powerSpeed.getXpos(),powerSpeed.getYpos(),powerSpeed.getWidth(),powerSpeed.getHeight()).intersects(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
		{
			backPanel.setSpeedCounter(backPanel.getSpeedCounter()+1);
			powerSpeed.makeIsOrNotFalse();
		}
		if(powerShield.is()&&new Rectangle(powerShield.getXpos(),powerShield.getYpos(),powerShield.getWidth(),powerShield.getHeight()).intersects(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
		{
			powerShield.makeIsOrNotFalse();
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
	public void playTheDeathSound()
	{
		ac.PlayAudio("Gameover.wav");
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
	public void addTimeToPowerUps()
	{
		this.powerShield.addTimer();
		this.powerSpeed.addTimer();
	}
	public void refreshPowerUps()
	{
		this.powerShield.refreshTimerAndIsOrNot();
		this.powerSpeed.refreshTimerAndIsOrNot();
	}
	public void TimeAndDeffuse()
	{
		if(Time.isSecond())
		{
			Time.isSecond();
			deffuse.setTIME(deffuse.getTIME()+1);
			player.reduceSpeedTimer();
			addTimeToPowerUps();
			refreshPowerUps();
			powerUps();
			refreshShield();
			if(player.checkSpeed())
			{
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
	public void powerUps()
	{
		powerSpeed.setIsOrNot(Support.giveMeARandomNumberUpTo25());
		powerSpeed.changeThePositon();
		powerShield.setIsOrNot(Support.giveMeARandomNumberUpTo100());
		powerShield.changeThePositon();
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
					YouLost = new Lost(initialTail - tail,this.level);
					State.state = Situation.YOU_LOST;
					playTheDeathSound();
					if(highScore.isHigher(this.level))
					{
						initScoreSystem(this.level,1);
					}
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
				YouLost = new Lost(initialTail - tail,this.level);
				State.state = Situation.YOU_LOST;
				playTheDeathSound();
				if(highScore.isHigher(this.level))
				{
					initScoreSystem(this.level,1);
				}
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
			YouWon = new Won(initialTail,this.level);
			State.state = Situation.YOU_WON; 
			level++;
			ac.PlayAudio("win.wav");
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
		if(player.getYpos()>=Size.Height-40)
		{
			player.setYpos(Size.Height-40);
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
		player  = new Player();
		Time = new myOwnTimer();
		Time.setAddMilli(Support.DELAY);
		bombTimer = new BombTimer();
		State.state = Situation.GAME;
		initTime1();
		backPanel = new BackgroundPanel(t1,tail,0,0,this.level);
		flashyBackground = new LightFlashBackground(Color.white);
		tails = new ArrayList<EscapeTail>();
		powerSpeed.setIsOrNot(1);
		powerShield.setIsOrNot(0);
		createDeffuser();
		initGuide_Move();
		initHighScore();
		createTail();
		
		
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
	public void makePlayFalse()
	{
		this.play = false;
	}
	public void initTail()
	{
		level = 1;
		tail = Support.rand.nextInt(3)+level;
		initialTail = tail;
	}
	public void levelMaker()
	{
		tail = Support.rand.nextInt(3)+level;
	}
	public void nextLevel()
	{
		timer.start();
		t1 = 10;
		levelMaker();
		initialTail = tail;
		player  = new Player();
		Time = new myOwnTimer();
		Time.setAddMilli(Support.DELAY);
		bombTimer = new BombTimer();
		State.state = Situation.GAME;
		backPanel = new BackgroundPanel(t1,tail,backPanel.getSpeedCounter(),backPanel.getShiledCounter(),this.level);
		flashyBackground = new LightFlashBackground(Color.white);
		tails = new ArrayList<EscapeTail>();
		powerSpeed.setIsOrNot(1);
		initTime1();
		powerShield.setIsOrNot(0);
		createDeffuser();
		initGuide_Move();
		createTail();
//		resetBackMusic();
	}

	
			
}
