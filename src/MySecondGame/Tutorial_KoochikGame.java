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

public class Tutorial_KoochikGame extends JPanel implements ActionListener{
	
	
	
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Image  dbImage;
	private Graphics dbg;
	static boolean play;
	private int level;
	private Color backColor;
	static int TotalTime = 1;
	static boolean deffuseris = false;
	static boolean playerHasTheDeffuser =false;
	static boolean JMessageIsOn = false;
	
	
	//powerUPS
	private boolean PLAYER_HAS_BLACK_POWER_UP = false;
	private int BLACK_TIMER = 0 ;
	
	private KoochikPlayer player;
	private Deffuser deffuser;
	private KoochikBomb koochikbomb;
	private myOwnTimer time;
	@SuppressWarnings("unused")
	private KoochikLost koochiklost;
	@SuppressWarnings("unused")
	private KoochikWon koochikwon;
	@SuppressWarnings("unused")
	private KoochikPowerUp koochikpowerup;
	private BackgroundTimer backgroundtimer;
	@SuppressWarnings("unused")
	private KoochikAnotherWorldJDialog koochikanotherjdialog;
	private Endoftutorial endoftutorial;
	private LostTheTutorial lostthetutorial;
	private ShowMessage showm = new ShowMessage("","",Color.BLACK,0,0,0,0);
	
	private ArrayList<KoochikPowerUp> powerUps;
	private ArrayList<Particle> particles;
	
	
	
	Tutorial_KoochikGame()
	{
		initGame();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(TotalTime==9)
		{
			super.grabFocus();
		}
		if(!Tutorial_KoochikGame.play&&State.isGame())
		{
			time.setMilliCounter(time.getMilliCounter()+time.getAddMilli());
			thingsOnTimePlayf();
		}
		if(Tutorial_KoochikGame.play)
		{
			if(State.isGame())
			{ 
			
			 checkTutorial_PlayerHasTheDefuser();
			 checkTutorial_PlayerSpawnedTheDefuser();
			
			 movePlayer();
			 thingsHitThings();
			 moveParticles();
			 time.setMilliCounter(time.getMilliCounter()+time.getAddMilli());
			 thingsOnTime();
			 createDefuser();
			 checkDeath();
			 player.keepThePlayerInside_KoochikGame();
			 checkWon();
//			 keepThePowerUpsIn();
//			 movePowerUps();
//			 for the tutorial they are off 
			 keepTheParticlesIn();
			}
			else if(State.isAnotherWorld()&&!KoochikGame.isJDialogUp)
			{
				  
				movePlayer();
				player.keepThePlayerInside_KoochikGame();
				thingsCollide();
				playerHitsParticlesAnotherWorld();
				keepParticlesInside();
				moveTheParticles();
				time.setMilliCounter(time.getMilliCounter()+time.getAddMilli());
				checkDeathAnotherWorld();
				checkWonAnotherWorld();
				thingsOnTime();
			}
			
		}
		 
		
	
		repaint();
		
	}
	
	//draw sec
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
		
		fillrect(g);
		drawParticles(g);
		drawPowerUps((Graphics2D)g);
		koochikbomb.draw(g, this);
		deffuser.drawDeffuser(g, this);
		player.draw(g);
		showm.draw((Graphics2D)g, TotalTime);
		
		break;
		
		case YOU_LOST:
			lostthetutorial.draw((Graphics2D)g);
			break;
		case YOU_WON:
			endoftutorial.draw((Graphics2D)g);
			break;
			
		case ANOTHER_WORLD:
			player.draw(g);
			drawTheAnotherWorldParticles(g);
			backgroundtimer.draw(g);
			break;
			
		default:
			break;
		}
		g.dispose();
	}
	public void drawParticles(Graphics g)
	{
		for(Particle p:particles)
		{
			p.draw(g);
		}
	}
	public void drawTheAnotherWorldParticles(Graphics g)
	{
		for(Particle p :particles)
		{
			p.draw(g);
		}
	}
	public void drawPowerUps(Graphics2D g2d)
	{
		for(KoochikPowerUp p:powerUps)
		{
			if(p.is())
			{
				p.draw(g2d);
			}
		}
	}
	public void fillrect(Graphics g)
	{
		g.setColor(backColor);
		g.fillRect(0, 0, Size.Width, Size.Height);
	}
	
	
	//init sec
	public void initGame()
	{
		super.setFocusable(true);
		initTimer();
		initPlayer();
		initPlay();
		initLost(1);
		initWon();
		initLevel();
		initDeffuser();
		initBackColor();
		initEndOfTutorial();
		initKoochikBomb();
		initTime();
		initLostTheTutorial();
		initIsJDialogUp();
		initBackGroundTimer();
		initPowerUps();
		initParticles();
		addKeys();
	}
	public void initTimer()
	{
		timer = new Timer(Support.DELAY,this);
		timer.start();
	}
	public void initPlayer()
	{
		this.player = new KoochikPlayer(this.level);
	}
	public void initDeffuser()
	{
		this.deffuser = new Deffuser(Size.Width/2-30,Size.Height/2-30);
		this.deffuser.deleteThisDeffuser();
	}
	public void initPlay()
	{
		play = false;
	}
	public void initLevel()
	{
		this.level = 1;
	}
	public void initParticles()
	{
		this.particles = new ArrayList<Particle>();
		createInitialParticles();
	}
	public void initKoochikBomb()
	{
		this.koochikbomb = new KoochikBomb(this.level);
		koochikbomb.setTimer1(60);
	}
	public void initTime()
	{
		time = new myOwnTimer();
		time.setAddMilli(Support.DELAY);
	}
	public void initLost(int e)
	{
		this.koochiklost = new KoochikLost(this.level,e);
	}
	public void initWon()
	{
		this.koochikwon = new KoochikWon(this.level);
	}
	public void initPowerUps()
	{
		this.koochikpowerup = new KoochikPowerUp();
		this.powerUps = new ArrayList<KoochikPowerUp>();
	}
	public void initBackColor()
	{
		this.backColor = Color.white;
	}
	public void initAnotherWorld()
	{
		this.particles = new ArrayList<Particle>();
		createAnotherWorldParticles();
	}
	public void initBackGroundTimer()
	{
		this.backgroundtimer = new BackgroundTimer();
	}
	public void initJDialogAnotherWorld()
	{
		this.koochikanotherjdialog = new KoochikAnotherWorldJDialog();
	}
	public void initIsJDialogUp()
	{
		KoochikGame.isJDialogUp = false;
	}
	public static void initMessage(String s,String s1,Color c,int x,int y, int w, int h)
	{
		play = false;
		@SuppressWarnings("unused")
		ShowMessage showmessage = new ShowMessage(s,s1,c,x,y,w,h);
		
	}
	public void initEndOfTutorial()
	{
		endoftutorial = new Endoftutorial();
	}
	public void initLostTheTutorial()
	{
		lostthetutorial = new LostTheTutorial();
	}
	
	@SuppressWarnings("static-access")
	public void addKeys()
	{
		Support.addKeyBinding(this, KeyEvent.VK_UP, "MoveUp", (evt)->{
			if(!Tutorial_KoochikGame.JMessageIsOn)
			{
			player.isMovingUp();
			this.play = true;
			this.player.setYvel(-this.player.getSpeed());
			}
			
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_UP, "MoveUpC", (evt)->{
			if(!Tutorial_KoochikGame.JMessageIsOn)
			{
				this.player.setYvel(0);
			}
			
		}, true);
		
		Support.addKeyBinding(this, KeyEvent.VK_DOWN, "MoveDown", (evt)->{
			if(!Tutorial_KoochikGame.JMessageIsOn)
			{
				player.isMovingDown();
			this.play = true;
			this.player.setYvel(this.player.getSpeed());
			}
			
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_DOWN, "MoveDownC", (evt)->{
			if(!Tutorial_KoochikGame.JMessageIsOn)
			{
				this.player.setYvel(0);
			}
			
		}, true);
		
		Support.addKeyBinding(this, KeyEvent.VK_LEFT, "MoveLeft", (evt)->{
			if(!Tutorial_KoochikGame.JMessageIsOn)
			{
					player.isMovingLeft();
			this.play = true;
			this.player.setXvel(-this.player.getSpeed());
			}
		
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_LEFT, "MoveLeftC", (evt)->{
			if(!Tutorial_KoochikGame.JMessageIsOn)
			{
				this.player.setXvel(0);
			}
			
		}, true);
		
		Support.addKeyBinding(this, KeyEvent.VK_RIGHT, "MoveRight", (evt)->{
			if(!Tutorial_KoochikGame.JMessageIsOn)
			{
				player.isMovingRight();
			this.play = true;
			this.player.setXvel(this.player.getSpeed());
			}
			
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_RIGHT, "MoveRightC", (evt)->{
			if(!Tutorial_KoochikGame.JMessageIsOn)
			{
				this.player.setXvel(0);	
			}
			
		}, true);
		
		
		
		Support.addKeyBinding(this, KeyEvent.VK_ENTER, "Restart", (evt)->{
			if(State.isWon()||State.isLost())
			{
				this.restartTheWholeGame();
			}
		}, false);
		//cheat
		
		
	}
	
	//move sec
	public void movePlayer()
	{
		this.player.moveThePlayer();
	}
	public void moveParticles()
	{
		for(Particle p :particles)
		{
			p.moveIt();
		}
	}
	public void movePowerUps()
	{
		for(KoochikPowerUp p:powerUps)
		{
			if(p.is())
			{
				p.moveIt();
			}
		}
	}
	public void moveTheParticles()
	{
		for(Particle p:particles)
		{
			p.moveIt();
		}
	}
	
	public void thingsHitThings()
	{
		if(deffuser.is()&&new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight()).intersects(new Rectangle(deffuser.getXpos(),deffuser.getYpos(),deffuser.getWidth(this),deffuser.getHeight(this))))
		{
			deffuser.deleteThisDeffuser();
			this.player.playerGotTheDeffuser();
			
		}
		
		playerHitsParticles();
		playerHitsPowerUps();
	
	}
	public void playerHitsParticles()
	{
		for(Particle p: particles)
		{
			if(p.is()&&new Rectangle (player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight()).intersects(new Rectangle(p.getXpos(),p.getYpos(),p.getWidth(),p.getHeight())))
			{	
				p.removeThis();
				if(p.isBad())
				{
					player.setHeightWidth(p.getValue());
				}
				else if(!p.isBad())
				{
					player.setHeightWidth(-p.getValue());
				}
				p.resetEveryThing();
			}
		}
	}
	public void playerHitsParticlesAnotherWorld()
	{
		for(Particle p :particles )
		{
			if(p.is()&&new Rectangle(p.getXpos(),p.getYpos(),p.getWidth(),p.getHeight()).intersects(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
			{
				player.reducePlayerHealth(p.getWidth());
				p.removeThis();
				p.resetBadParticle();
			}
		}
	}
	public void playerHitsPowerUps()
	{
		for(KoochikPowerUp p:powerUps)
		{
			if(State.isGame()&&new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight()).intersects(new Rectangle(p.getXpos(),p.getYpos(),p.getWidth(),p.getHeight())))
			{
				p.removeThis();
				p.setXpos(2000);
				switch(p.state)
				{
					case YELLOW:
						player.setSpeed(5);
						break;
						
				
					case BLACK:
						this.PLAYER_HAS_BLACK_POWER_UP = true;
						this.backColor = Color.green;
						break;
				
					case MAGENTA:
						State.AnotherWorld();
						this.makeThePlayFalse();
						player.playerIsNotInThisWorld();
						time.setMilliCounter(0);
						initAnotherWorld();
						initJDialogAnotherWorld();
						KoochikGame.makeTheJDialogTrue();
						
						break;
				
					case BLUE:
						bluePowerUp();
						break;
				}
			}
		}
	}
	
	public void thingsCollide()
	{
		playerHitsParticlesAnotherWorld();
	}
	public void thingsOnTime()
	{
		if(State.isGame()&&time.isSecond())
		{
			
			koochikbomb.reduceTime();
//			createPowerUps();
	// for tutorial purposes
			blackPowerUp();
		}
		else if(State.isAnotherWorld()&&time.isSecond())
		{
			time.isSecond();
			backgroundtimer.minusSec();
		}
	}
	//things on time when play==false
	public void thingsOnTimePlayf()
	{
		if(time.isSecond())
		{
			showm.flash();
		}
	}
	//inside
	public void keepTheParticlesIn()
	{
		for(Particle p:particles)
		{
			p.keepItInside();
		}
	}
	public void keepParticlesInside()
	{
		for(Particle p:particles)
		{
			p.keepItInside();
		}
	}
	public void keepThePowerUpsIn()
	{
		for(KoochikPowerUp p :powerUps)
		{
			if(p.is())
			{
				p.keepItInside();
			}
		}
	}
	
	//create sec
	public void createInitialParticles()
	{
		boolean isbad = true;
		for(int i = 0; i <=31;i++)
		{
			if(i<16)
			{
				isbad = true;
			}
			else
			{
				isbad = false;
			}
			Particle p = new Particle(isbad);
			particles.add(p);
		}
	}
	public void createDefuser()
	{
		if(player.getCreateDefuser()&&!player.playerHasTheDeffuser())
		{
			deffuser.setDeleted(0);
			for(Particle p:particles)
			{
				p.makeItDifficult();
			}
		}
		else 
		{
			for(Particle p :particles)
			{
				p.resetTheSize();
			}
			deffuser.deleteThisDeffuser();
		}
	}
	public void createAnotherWorldParticles()
	{
		for(int i = 1;i<=level+1;i++)
		{
			Particle p = new Particle(110,true);
			particles.add(p);
		}
	}
	public void blackPowerUp()
	{
		if(this.PLAYER_HAS_BLACK_POWER_UP)
		{
			this.BLACK_TIMER++;
			if(this.BLACK_TIMER>=5)
			{
				this.BLACK_TIMER = 0;
				this.PLAYER_HAS_BLACK_POWER_UP = false;
				this.backColor = Color.white;
			}
		}
	}
	
	public void createPowerUps()
	{
		int random = Support.giveMeARandomNumberUpTo10();
		
		if(random==2)
		{
			KoochikPowerUp s = new KoochikPowerUp();
			powerUps.add(s);
		}
	}
	
	//CheckDeath and Win
	public void checkDeath()
	{
		if(State.isGame()&&koochikbomb.getDeath())
		{
			initLost(1);
			State.Lost();
			
		}
		else if(State.isGame()&&player.getWidth()<=10)
		{
			initLost(2);
			State.Lost();
			
		}
		
	}
	public void checkWon()
	{
		if(State.isGame()&&player.playerHasTheDeffuser()&&new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight()).intersects(new Rectangle(0,0,100,100)))
		{
			initWon();
			State.Won();
		}
	}
	public void checkDeathAnotherWorld()
	{
		if(player.getHealth()<=0)
		{
			initLost(3);
			State.Lost();
			
		}
	}
	public void checkWonAnotherWorld()
	{
		if(backgroundtimer.won())
		{
			initWon();
			State.Won();
		}
	}
	
	public void restartTheWholeGame()
	{
		State.Game();
		timer.start();
		TotalTime=1;
		deffuseris = false;
		playerHasTheDeffuser =  false;
		createAMessage(true,TotalTime);
		initLevel();
		initPlayer();
		initPlayer();
		initPlay();
		initBackGroundTimer();
		initPowerUps();
		initBackColor();
		initDeffuser();
		initKoochikBomb();
		initTime();
		initParticles();
	}
	public void startTheTimer()
	{
		this.timer.start();
	}
	public void stopTheTimer()
	{
		this.timer.stop();
	}
	@SuppressWarnings("static-access")
	public void makeThePlayFalse()
	{
		this.play =  false;
	}
	public static void makeTheJDialogTrue()
	{
		KoochikGame.isJDialogUp = true;
	}
	public static void makeTheJDialogFalse()
	{
		KoochikGame.isJDialogUp = false;
	}
	public void bluePowerUp()
	{
		for(Particle p:particles)
		{
			p.makeItEasy();
		}
	}
	public void checkTutorial_PlayerHasTheDefuser()
	{
		if(!Tutorial_KoochikGame.playerHasTheDeffuser)
		{
			if(player.playerHasTheDeffuser())
			{
				Tutorial_KoochikGame.playerHasTheDeffuser = true;
				createAMessage(true,11);
			}
		}
		
	}
	public void checkTutorial_PlayerSpawnedTheDefuser()
	{
		if(!Tutorial_KoochikGame.deffuseris)
		{
			if(deffuser.is())
			{
				Tutorial_KoochikGame.deffuseris = true;
				TotalTime++;
				createAMessage(true,TotalTime);
			
			}
		
		}
	    
	}
	static void createAMessage(boolean command, int num)
	{
		if(command)
		{
			
			switch(num)
			{
				default:
					Tutorial_KoochikGame.JMessageIsOn = false;
					break;
				case 1:
					Tutorial_KoochikGame.JMessageIsOn = true;
					initMessage("Welcome To The Tutorial","Let's go through the Game together!",Color.cyan,Size.Width/2,Size.Height/2,300,100);
					break;
				case 2:
					Tutorial_KoochikGame.JMessageIsOn = true;
					initMessage("This is you","You can move using the arrow keys",Color.YELLOW,Size.Width/2,Size.Height/2-100,250,70);
					break;
				case 3:
					Tutorial_KoochikGame.JMessageIsOn = true;
					initMessage("The Objective in this game is to defuse the bomb(which has a timer!)","You can see the bomb at the top of the screen to the left",Color.cyan,200,100,400,100);
					break;
				case 4:
					Tutorial_KoochikGame.JMessageIsOn = true;
					initMessage("In order to obtain the defuser you have to reach a certain size","",Color.CYAN,Size.Width/2,Size.Height/2,350,50);
					break;
				case 5:
					Tutorial_KoochikGame.JMessageIsOn = true;
					initMessage("This is your current size","",Color.ORANGE,Size.Width/2+100,200,200,50);
					break;
				case 6:
					Tutorial_KoochikGame.JMessageIsOn = true;
					initMessage("This is the size you need to get","in order to spawn the defuser",Color.green,Size.Width/2-300,200,200,80);
					break;
				case 7:
					Tutorial_KoochikGame.JMessageIsOn = true;
					initMessage("Green particles will decrease your size","Red particles will increase your size",Color.GREEN,Size.Width/2,Size.Height/2,300,70);
					break;
				case 8: 
					Tutorial_KoochikGame.JMessageIsOn = true;
					initMessage("Lower your size by obtaining the green particles","Watch out ! if your size gets to 10 you will die!",Color.red,Size.Width/2,Size.Height/2,400,70);
					break;
				case 10:
					if(Tutorial_KoochikGame.deffuseris)
					{
						Tutorial_KoochikGame.JMessageIsOn = true;
						initMessage("Nice job! the defuser has spawned!","You need to get it but watch out for the raged red particles",Color.magenta,Size.Width/2,Size.Height/2-100,350,70);
					}
					
					break;
				
					
				case 11:
					if(Tutorial_KoochikGame.playerHasTheDeffuser)
					{
						Tutorial_KoochikGame.JMessageIsOn = true;
						initMessage("Good!","Now go touch the bomb and defuse it to win!",Color.pink,Size.Width/2,Size.Height/2,300,70);
					}
					break;
			}
			
			
		}

	}


	


	
	
	

}
	
	
		