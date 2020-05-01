package MySecondGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Tutorial_Chanta extends JPanel implements ActionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


private Timer timer;
	
	private Image dbImage;
	private Graphics dbg;
	private static boolean play = false;
	private int tail =2;
	private int lastXpos;
	private int lastYpos; 
	
	//tutorial variables 
	static int totaltime = 1;
	static boolean playerHasTheDeffuser = false;
	static boolean JDialogIsOn = false;
	ShowMessage_ChantaGame showm = new ShowMessage_ChantaGame("","",Color.BLACK,0,0,0,0);
	
	
	Player player;
	Deffuser deffuser;
	myOwnTimer time;
	myOwnTimer time1;
	ArrayList<EscapeTail> bombs;
	ArrayList<ChantaTail>tails;
	ChantaPanel chantapanel;
	Lost lost;
	Hearts hearts;
	Endoftutorial endoftutorial;
	LostTheTutorial lostthetutorial;
	

	ClearUp clearup = new ClearUp(100,100,this);
	//reset doesnt work
	
	Tutorial_Chanta()
	{
		initGame();
	}
	
	public void initGame()
	{
		setUpTimer();
		setFocusable(true);
		setVisible(true);
		addKeys();
		initPlayer();
		initTail();
		initDeffuser();
		initBombs();
		initTime();
		initTime1();
		initEndOfTutorial();
		initLostTheTutorial();
		initChantaPanel();
		initLost();
		initHearts();
	}
	

	
	public void actionPerformed(ActionEvent e) 
	{
		if(!play&&State.isGame())
		{
			time1.setMilliCounter(time1.getMilliCounter()+time1.getAddMilli());
			thingsOnTime1();
		}
		if(play&&State.isGame())
		{
			createAMessage(Tutorial_Chanta.totaltime);
			player.moveThePlayer();
			moveTheTail();
			playerHitsObjects();
			addSpeed();
			time.setMilliCounter(time.getMilliCounter()+time.getAddMilli());
			addTime();
			keepThePlayerInside();
			detectLost();
			createAClearUp();
			getTheLastBombPos();
		}
		
		repaint();	
	}
	public void paint(Graphics g)
	{
		dbImage = createImage(Size.Width,Size.Height);
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	public void paintComponent(Graphics g)
	{
		switch(State.state)
		{
			case GAME:
				
				clearup.draw(g, this);
				chantapanel.draw(g,this);
				hearts.draw(g, this);
				player.draw(g);
				drawTheTail(g);
				drawTheBombs(g);
				deffuser.drawDeffuser(g, this);
				showm.draw(Tutorial_Chanta.playerHasTheDeffuser,player.whichDirection(),deffuser.getXpos(),deffuser.getYpos(),player.getXpos(),player.getYpos(), totaltime, (Graphics2D)g);
				break;
			case YOU_LOST:
				endoftutorial.draw((Graphics2D)g);
				break;
			case YOU_WON:
				lostthetutorial.draw((Graphics2D)g);
				break;
		default:
			break;
				
		
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
			for(ChantaTail t:tails)
			{
				
				t.setXvel(player.getSpeed());
				t.setYvel(0);
				t.setXpos(player.getXpos()-(player.getWidth()*i));
				t.setYpos(player.getYpos());
				i++;
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
			for(ChantaTail t:tails)
			{
				
				t.setXvel(-player.getSpeed());
				t.setYvel(0);
				t.setXpos(player.getXpos()+(player.getWidth()*i));
				t.setYpos(player.getYpos());
				i++;
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
			for(ChantaTail t:tails)
			{
				t.setXvel(0);
				t.setYvel(-player.getSpeed());
				t.setXpos(player.getXpos());
				t.setYpos(player.getYpos()+(player.getHeight()*i));
				i++;
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
			
			int i =1;
			for(ChantaTail t:tails)
			{
				t.setXvel(0);
				t.setYvel(player.getSpeed());
				t.setXpos(player.getXpos());
				t.setYpos(player.getYpos()-(player.getHeight()*i));
				i++;
			}
			}
		}, false);
		
		Support.addKeyBinding(this, KeyEvent.VK_ENTER, "Restart", (evt)->{
			if(State.isLost())
			{
				this.resetTheWholeGame();
			}
		}, false);
	}
	public void setUpTimer()
	{
		timer = new Timer(Support.DELAY,this);
		timer.start();
	}
	public void startTheTimer()
	{
		this.timer.start();
	}
	public void stopTheTimer()
	{
		this.timer.stop();
	}
	public void initPlayer()
	{
		player = new Player();
	}
	public void initEndOfTutorial()
	{
		this.endoftutorial = new Endoftutorial();
	}
	public void initLostTheTutorial()
	{
		this.lostthetutorial = new LostTheTutorial();
	}
	public static void initMessage(String s,String s1,Color c,int x,int y, int w, int h)
	{
		play = false;
		@SuppressWarnings("unused")
		ShowMessage_ChantaGame showmessage = new ShowMessage_ChantaGame(s,s1,c,x,y,w,h);
	}
	public void initTail()
	{
		tails = new ArrayList<ChantaTail>();
		for(int i = 1;i<=tail;i++)
		{
			ChantaTail t = new ChantaTail(player.getXpos()-(player.getWidth()*i),player.getYpos(),player.getSpeed(),player.getSpeed(),player.getWidth(),player.getHeight());
			tails.add(t);
		}
	}
	public void drawTheTail(Graphics g)
	{
		for(ChantaTail t:tails)
		{
			t.drawTheChantaTail(g);
		}
	}
	public void moveTheTail()
	{
		for(ChantaTail t:tails)
		{
			t.moveThisTail();
		}
	}
	public void getTheLastBombPos()
	{
		int i = 1;
		for(ChantaTail t:tails)
		{
			if(i==2)
			{
				lastXpos = t.getXpos();
				lastYpos = t.getYpos();
				
			}
	
		
			else
			{
				i++;
				
			}
		}
	
	}
	public void initDeffuser()
	{
		deffuser = new Deffuser(Support.giveMeARandomX(),Support.giveMeARandomY());
	}
	public void playerHitsObjects()
	{
		if(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight()).intersects(new Rectangle(deffuser.getXpos(),deffuser.getYpos(),deffuser.getWidth(this),deffuser.getHeight(this))))
		{
			deffuser = new Deffuser(Support.giveMeARandomX(),Support.giveMeARandomY());
			playerHasTheDeffuser = true;
			createABombAtAnSpeceficPlace();
			chantapanel.addChanta();
		}
		if(hearts.is1()&&new Rectangle(hearts.getXpos(),hearts.getYpos(),hearts.getWidth(this),hearts.getHight(this)).intersects(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
		{
			chantapanel.addLives();
			initHearts();
		}
		if(clearup.getIs()&&new Rectangle(clearup.getXpos(),clearup.getYpos(),clearup.getWidth(),clearup.getHeight()).intersects(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
		{
			clearup.setXpos(2000);
			clearup.setIs(false);
			clearup.setCreate(true);
			chantapanel.addChanta();
			clearBombs();
			//remove the bombs that are near the player +-200
		}
		
	}
	public void initBombs()
	{
		bombs = new ArrayList<EscapeTail>();
	}
	public void initLost()
	{
		lost = new Lost(0,0);
	}
	public void initHearts()
	{
		hearts = new Hearts();
	}
	public void createABombAtAnSpeceficPlace()
	{
		EscapeTail t = new EscapeTail(lastXpos,lastYpos);
		t.setRemoveVelocity(1);
		bombs.add(t);
	}
	public void drawTheBombs(Graphics g)
	{
		for(EscapeTail e:bombs)
		{	
			e.paint(g, this);
		}
	}
	public void changeTheColorOfTheBombs()
	{
		for(EscapeTail t: bombs)
		{
			if(t.getRemoveVelocity()==1&&t.timer<=12&&t.is())
			{	
				t.changeTheBombColor();
			}
			
		}
	}
	public void addTime()
	{
		if(time.isSecond())
		{
			hearts.addTimer();
			if(hearts.isTime())
			{
				hearts.resetTimer();
				initHearts();
			}
			time.isSecond();
			for(EscapeTail t:bombs)
			{
				t.setTIMER(t.getTIMER()+1);
			}
			changeTheColorOfTheBombs();
		}
	}
	public void initTime()
	{
		time = new myOwnTimer();
		time.setAddMilli(Support.DELAY);
	}
	public void initTime1()
	{
		time1 = new myOwnTimer();
		time1.setAddMilli(Support.DELAY);
	}
	
	public void detectLost()
	{
		for(EscapeTail b:bombs)
		{
			if(b.is()&&new Rectangle(b.getXpos(),b.getYpos(),b.getWidth(this),b.getHeight(this)).intersects(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
			{
				
				if(chantapanel.youLost())
				{
					lost = new Lost(chantapanel.getChanta(),0);
					State.Lost();
					
					break;
				}
				else 
				{
					b.deleteThisTail();
					chantapanel.lostALife();
				}
				
			}
		}
	}
	public void makePlayFalse()
	{
		Tutorial_Chanta.play = false;
	}
	public void initChantaPanel()
	{
		chantapanel = new ChantaPanel();
	}
	public void addSpeed()
	{
		if(chantapanel.timeIsUpForSpeed())
		{
			player.setSpeed(player.getSpeed()+1);
		}
	}
	public void createAClearUp()
	{
		if(clearup.create()&&(chantapanel.getChanta()==25||chantapanel.getChanta()==50||chantapanel.getChanta()==75||chantapanel.getChanta()==100))
		{
			clearup = new ClearUp(Support.giveMeARandomX(),Support.giveMeARandomY(),this);
			clearup.setIs(true);
			clearup.setCreate(false);
		}
	}
	public void clearBombs()
	{
		int x = player.getXpos();
		int y = player.getYpos();
		int w = 300;
		int h = 300;
		
		if(player.isMovingRight1())
		{
			x = player.getXpos()-w/2;
			y = player.getYpos()-h/2;
		}
		else if(player.isMovingLeft1())
		{
			x = player.getXpos()-w/4;
			y = player.getYpos()-h/2;
		}
		else if(player.isMovingDown1())
		{
			x = player.getXpos()-w/2;
			y = player.getYpos()-h/2;
		}
		else if (player.isMovingUp1())
		{
			x = player.getXpos()-w/2;
			y = player.getYpos()-h/2;
		}
		for(EscapeTail b: bombs)
		{
			if(new Rectangle(b.getXpos(),b.getYpos(),b.getWidth(this),b.getHeight(this)).intersects(x,y,w,h))
			{
				b.deleteThisTail();
			}
		}
	}
	public void keepThePlayerInside()
	{
		if(player.getXpos()<=0)
		{
			player.setXpos(0);
			player.setXvel(0);
			
			for(ChantaTail t:tails)
			{
					t.setXvel(0);	
			}
		}
		if(player.getXpos()>=Size.Width-46)
		{
			player.setXpos(Size.Width-46);
			player.setXvel(0);
			
			for(ChantaTail t:tails)
			{
				
					t.setXvel(0);
				
			}
		}
		if(player.getYpos()<=0)
		{
			player.setYpos(0);
			player.setYvel(0);
			
			for(ChantaTail t:tails)
			{
				
					t.setYvel(0);
				
			}
		}
		if(player.getYpos()>=Size.Height-65)
		{
			player.setYpos(Size.Height-65);
			player.setYvel(0);
			
			for(ChantaTail t:tails)
			{
				
					t.setYvel(0);
				
			}
		}
	}
	public void resetTheWholeGame()
	{
		
		timer.start();
		State.Game();
		play = false;
		totaltime = 1;
		playerHasTheDeffuser = false;
		createAMessage(totaltime);
		initPlayer();
		initTail();
		initDeffuser();
		initBombs();
		initTime();
		initTime1();
		initChantaPanel();
		initLost();
		initHearts();
	}
	public void thingsOnTime1()
	{
		if(time1.isSecond())
		{
			showm.flash();
		}
	}
	
	public static void createAMessage(int num)
	{
		if(State.isGame())
		{
			switch(num)
			{
				default:
					JDialogIsOn = false;
					break;
				case 1:
					JDialogIsOn = true;
					initMessage("Welcome To The Tutorial","Let's go through the Game together!",Color.yellow,Size.Width/2,Size.Height/2,300,100);
					break;
				case 2:
					JDialogIsOn = true;
					initMessage("This is you!","You can move using the arrow keys",Color.green,Size.Width/2,Size.Height/2-100,250,70);
					break;
				case 3:
					JDialogIsOn = true;
					initMessage("The objective of this game is to defuse as many bomb as you can ","by picking up the defuser(there is no timer)",Color.yellow,Size.Width/2,Size.Height/2,400,70);
					break;
				case 4:
					JDialogIsOn = true;
					initMessage("Go ahead and pick up a defuser","",Color.MAGENTA,Size.Width/2,Size.Height/2-60,190,50);
					break;
				case 5:
					JDialogIsOn = true;
					if(playerHasTheDeffuser)
					{
						initMessage("Good job!","As you can see your score went up by 1!",Color.GREEN,Size.Width/2+200,Size.Height/2-60,250,70);
					}
					break;
				case 6:
					JDialogIsOn = true;
					initMessage("There is no timer, but you have three", "lives as you can see on top of the screen",Color.yellow,70,150,300,70);
					break;
				case 7:
					JDialogIsOn = true;
					initMessage("IF YOUR HEAD HITS THE BOMBS YOU WILL LOSE A LIFE","",Color.red,Size.Width/2,Size.Height/2,340,40);
					break;
				case 8:
					JDialogIsOn = true;
					initMessage("BUT YOUR TAIL IS IMMUNE!","",Color.GREEN,Size.Width/2,Size.Height/2,220,40);
					break;
				case 9:
					JDialogIsOn = true;
					initMessage("You can pick up more lives through out the game ","But they are rare!",Color.cyan,Size.Width/2,Size.Height/2,300,70);
					break;
				case 10:
					JDialogIsOn = true;
					initMessage("That's it  !!!!!!!!!!!!!!!!!!!!!!!","If you have the highest score your name will be in the middle of the screen!",Color.yellow,Size.Width/2,Size.Height/2,450,70);
					break;
				case 11:
					JDialogIsOn = true;
					initMessage("Continue until you die or","press the ESC button and click on quit to quit",Color.green,Size.Width/2,Size.Height/2,300,70);
					break;
			}
		}
		
	}
	
}


