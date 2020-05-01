package MySecondGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;


public class KoochikGame extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Image  dbImage;
	private Graphics dbg;
	private boolean play;
	private int level;
	private Color backColor;
	static boolean isJDialogUp;
	
	
	//powerUPS
	private boolean PLAYER_HAS_BLACK_POWER_UP = false;
	private int BLACK_TIMER = 0 ;
	
	private KoochikPlayer player;
	private Deffuser deffuser;
	private KoochikBomb koochikbomb;
	private myOwnTimer time;
	//for when !play
	private myOwnTimer time1;
	private KoochikLost koochiklost;
	private KoochikWon koochikwon;
	@SuppressWarnings("unused")
	private KoochikPowerUp koochikpowerup;
	private BackgroundTimer backgroundtimer;
	@SuppressWarnings("unused")
	private KoochikAnotherWorldJDialog koochikanotherjdialog;
	@SuppressWarnings("unused")
	private ScoreSystemJDialog scoresystem;
	private ScoreSystem highScore;
	private Guide_MoveToStartPlaying guide_move;
	
	
	private ArrayList<KoochikPowerUp> powerUps;
	private ArrayList<Particle> particles;
	
	private AudioCreator ac = new AudioCreator();
	File lostMusic =Support.getFile("Gameover.wav");
	
	KoochikGame()
	{
		initGame();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(!this.play&&State.isGame())
		{
			time1.setMilliCounter(time1.getMilliCounter()+time1.getAddMilli());
			thingsOnTime1();
		}
		if(this.play)
		{
			if(State.isGame())
			{
			 movePlayer();
			 thingsHitThings();
			 moveParticles();
			 time.setMilliCounter(time.getMilliCounter()+time.getAddMilli());
			 thingsOnTime();
			 createDefuser();
			 checkDeath();
			 player.keepThePlayerInside_KoochikGame();
			 checkWon();
			 keepThePowerUpsIn();
			 movePowerUps();
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
		this.playerHasTheDefuser_Guide(g);
		player.draw(g);
		deffuser.drawDeffuser(g, this);
		drawParticles(g);
		drawPowerUps((Graphics2D)g);
		highScore.draw(g);
		koochikbomb.draw(g, this);
		guide_move.draw(play,(Graphics2D)g,Size.Width/2-420,30);
		
		break;
		
		case YOU_LOST:
			koochiklost.draw((Graphics2D)g);
			break;
		case YOU_WON:
			koochikwon.draw((Graphics2D)g);
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
		initTimer();
		initPlayer();
		initPlay();
		initLost(1);
		initWon();
		initLevel();
		initDeffuser();
		initBackColor();
		initKoochikBomb();
		initTime();
		initTime1();
		initHighScore();
		initIsJDialogUp();
		initBackGroundTimer();
		initGuide_Move();
		initPowerUps();
		initParticles();
		addKeys();
	}
	public void initTimer()
	{
		this.timer = new Timer(Support.DELAY,this);
		this.timer.start();
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
		this.play = false;
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
	}
	public void initTime()
	{
		this.time = new myOwnTimer();
		time.setAddMilli(Support.DELAY);
	}
	public void initTime1()
	{
		this.time1 = new myOwnTimer();
		time1.setAddMilli(Support.DELAY);
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
	public void initScoreSystem(int l,int c)
	{
		this.scoresystem = new ScoreSystemJDialog(this.level,c);
	}
	public void initHighScore()
	{
		highScore =  new ScoreSystem(0,"null");
		if(Support.docsExist(Support.KoochikGame))
		{
			String loadStr = Support.docsLoad(Support.KoochikGame);
			highScore.readLine(loadStr);
		}
	}
	public void initGuide_Move()
	{
		guide_move = new Guide_MoveToStartPlaying();
	}
	
	public void addKeys()
	{
		Support.addKeyBinding(this, KeyEvent.VK_UP, "MoveUp", (evt)->{
			player.isMovingUp();
			this.play = true;
			this.player.setYvel(-this.player.getSpeed());
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_UP, "MoveUpC", (evt)->{
			this.player.setYvel(0);
		}, true);
		
		Support.addKeyBinding(this, KeyEvent.VK_DOWN, "MoveDown", (evt)->{
			player.isMovingDown();
			this.play = true;
			this.player.setYvel(this.player.getSpeed());
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_DOWN, "MoveDownC", (evt)->{
			this.player.setYvel(0);
		}, true);
		
		Support.addKeyBinding(this, KeyEvent.VK_LEFT, "MoveLeft", (evt)->{
			player.isMovingLeft();
			this.play = true;
			this.player.setXvel(-this.player.getSpeed());
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_LEFT, "MoveLeftC", (evt)->{
			this.player.setXvel(0);
		}, true);
		
		Support.addKeyBinding(this, KeyEvent.VK_RIGHT, "MoveRight", (evt)->{
			player.isMovingRight();
			this.play = true;
			this.player.setXvel(this.player.getSpeed());
		}, false);
		Support.addKeyBinding(this, KeyEvent.VK_RIGHT, "MoveRightC", (evt)->{
			this.player.setXvel(0);
		}, true);
		
		Support.addKeyBinding(this,KeyEvent.VK_SPACE, "NextLevel", (evt)->{
			if(State.isWon())
			{
				this.nextLevel();
				State.Game();
			}
		}, false);
		
		Support.addKeyBinding(this, KeyEvent.VK_ENTER, "Restart", (evt)->{
			if(State.isLost())
			{
				this.restartTheWholeGame();
			}
		}, false);
		
		
		
		//cheat
		Support.addKeyBinding(this, KeyEvent.VK_H, "PowerUp", (evt)->{
			if(State.isGame())
			{
				KoochikPowerUp p = new KoochikPowerUp();
				powerUps.add(p);
			}
			
		}, false);
		
		Support.addKeyBinding(this, KeyEvent.VK_N, "nex le",(evt)->{
			this.nextLevel();
		}, false);
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
						ac.PlayAudio("anotherworld.wav");
						break;
				
					case BLUE:
						bluePowerUp();
						break;
				}
			}
		}
	}
	public void playerHasTheDefuser_Guide(Graphics g)
	{
		if(this.player.playerHasTheDeffuser())
		{
			g.setColor(Color.black);
			g.setFont(new Font("Serfi",Font.BOLD,25));
			g.drawString("You have the defuser!", Size.Width-254, 27);
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
			createPowerUps();
			blackPowerUp();
		}
		else if(State.isAnotherWorld()&&time.isSecond())
		{
			time.isSecond();
			backgroundtimer.minusSec();
		}
	}
	public void thingsOnTime1()
	{
		if(State.isGame()&&!this.play&&time1.isSecond())
		{
			guide_move.flash();
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
			playTheDeathSound();
			State.Lost();
			if(highScore.isHigher(this.level))
			{
				initScoreSystem(this.level,3);
			}
		}
		else if(State.isGame()&&player.getWidth()<=10)
		{
			initLost(2);
			playTheDeathSound();
			State.Lost();
			if(highScore.isHigher(this.level))
			{
				initScoreSystem(this.level,3);
			}
		}
		
	}
	public void checkWon()
	{
		if(State.isGame()&&player.playerHasTheDeffuser()&&new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight()).intersects(new Rectangle(0,0,100,100)))
		{
			initWon();
			State.Won();
			ac.PlayAudio("win.wav");
		}
	}
	public void checkDeathAnotherWorld()
	{
		if(player.getHealth()<=0)
		{
			initLost(3);
			playTheDeathSound();
			State.Lost();
			if(highScore.isHigher(this.level))
			{
				initScoreSystem(this.level,3);
			}
		}
	}
	public void checkWonAnotherWorld()
	{
		if(backgroundtimer.won())
		{
			initWon();
			State.Won();
			ac.PlayAudio("win.wav");
		}
	}
	public void nextLevel()
	{
		this.level++;
		State.Game();
		initPlayer();
		initPlayer();
		initPlay();
		initDeffuser();
		initBackGroundTimer();
		initBackColor();
		initKoochikBomb();
		initTime();
		initTime1();
		initGuide_Move();
		initPowerUps();
		initParticles();
	}
	public void restartTheWholeGame()
	{
		State.Game();
		timer.start();
		initLevel();
		initPlayer();
		initPlayer();
		initPlay();
		initBackGroundTimer();
		initPowerUps();
		initBackColor();
		initHighScore();
		initDeffuser();
		initGuide_Move();
		initKoochikBomb();
		initTime();
		initTime1();
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
	public void playTheDeathSound()
	{
		ac.PlayAudio("Gameover.wav");
	}
	
}
