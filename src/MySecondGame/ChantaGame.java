package MySecondGame;

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

public class ChantaGame extends JPanel implements ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	private Timer timer;
	
	private Image dbImage;
	private Graphics dbg;
	private boolean play = false;
	private int tail =2;
	private int lastXpos;
	private int lastYpos; 

	Player player;
	Deffuser deffuser;
	myOwnTimer time;
	ArrayList<EscapeTail> bombs;
	ArrayList<ChantaTail>tails;
	ChantaPanel chantapanel;
	Lost lost;
	Hearts hearts;
	@SuppressWarnings("unused")
	private ScoreSystemJDialog scoresystem;
	private ScoreSystem highScore;
	ClearUp clearup = new ClearUp(100,100,this);
	myOwnTimer time1;
	Guide_MoveToStartPlaying guide_move;
	AudioCreator ac = new AudioCreator();
	File lostMusic = Support.getFile("Gameover.wav");
	
	ChantaGame()
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
		initHighScore();
		initBombs();
		initTime();
		initTime1();
		initGuide_Move();
		initChantaPanel();
		initLost();
		initHearts();
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
				highScore.draw(g);
				clearup.draw(g, this);
				chantapanel.draw(g,this);
				hearts.draw(g, this);
				player.draw(g);
				drawTheTail(g);
				drawTheBombs(g);
				deffuser.drawDeffuser(g, this);
				guide_move.draw(play, (Graphics2D)g, Size.Width/2-420,30);
					break;
			case YOU_LOST:
				lost.draw(g);
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
	public void initScoreSystem(int l,int c)
	{
		this.scoresystem = new ScoreSystemJDialog(this.chantapanel.getChanta(),2);
	}
	public void initHighScore()
	{
		highScore =  new ScoreSystem(0,"null");
		if(Support.docsExist(Support.ChantaGame))
		{
			String loadStr = Support.docsLoad(Support.ChantaGame);
			highScore.readLine(loadStr);
		}
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
	public void detectLost()
	{
		for(EscapeTail b:bombs)
		{
			if(b.is()&&new Rectangle(b.getXpos(),b.getYpos(),b.getWidth(this),b.getHeight(this)).intersects(new Rectangle(player.getXpos(),player.getYpos(),player.getWidth(),player.getHeight())))
			{
				
				if(chantapanel.youLost())
				{
					lost = new Lost(chantapanel.getChanta(),0);
					playTheDeathSound();
					State.Lost();
					if(highScore.isHigher(chantapanel.getChanta()))
					{
						initScoreSystem(chantapanel.getChanta(),2);
						break;
					}
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
		this.play = false;
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
		if(clearup.create()){
			for(int i= 1;i<=10;i++)
			{
				if(chantapanel.getChanta()==i*15)
				{
					clearup = new ClearUp(Support.giveMeARandomX(),Support.giveMeARandomY(),this);
					clearup.setIs(true);
					clearup.setCreate(false);
				}
			}
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
		if(player.getYpos()>=Size.Height-40)
		{
			player.setYpos(Size.Height-40);
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
		initPlayer();
		initTime1();
		initGuide_Move();
		initTail();
		initDeffuser();
		initBombs();
		clearup.setIs(false);
		clearup.setCreate(true);
		initHighScore();
		initTime();
		initChantaPanel();
		initLost();
		initHearts();
	}
	public void playTheDeathSound()
	{
		ac.PlayAudio("Gameover.wav");
	}
	
}
