package MySecondGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

public class Menu extends JPanel implements ActionListener{

	
	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	
	
	private JButton play;

	private int playWidth = 291;
	private int playHeight = 91;
	private int playXpos = Size.Width/2-150;
	private int playYpos = Size.Height/2-200;
	
	private JButton quitthegame;
	private int quitWidth = 50;
	private int quitHeight = 50;
	private int quitXpos = Size.Width-70;
	private int quitYpos = 6;
	
	private JButton aboutthegame;
	private int aboutWidth = 50;
	private int aboutHeight = 50;
	private int aboutXpos = Size.Width-130;
	private int aboutYpos = 6;
	
	
	private JLabel titleLabel;
	//playAudio
//	AudioCreator ac = new AudioCreator();
	
	//BackGround music file
	File menuMusic = new File("mainMenu.wav");
	
	//dbImage
	private Image dbImage;
	private Graphics dbg;

	//moving balls
	ArrayList<MovingBalls> balls;
	
	public static final Color RANDOMCOLORLIST[] ={Color.black,Color.blue,Color.CYAN,Color.DARK_GRAY,Color.gray,Color.GREEN,Color.lightGray,Color.magenta,Color.orange,Color.pink,Color.red,Color.yellow}; 
	private Color saveColor;
	Menu()
	{
		initMenu();
	}
	
	public void initMenu()
	{
		setLayout(null);
//		ac.PlayAudio(menuMusic);
		timer = new Timer(Support.DELAY,this);
		timer.start();
		//playButton
		balls = new ArrayList<MovingBalls>();
		
		createMovingBalls();
		createPlay();
		createAQiutTheGameButton();
		createAAboutTheGameButton();
		initLabel();
		this.saveColor = RANDOMCOLORLIST[Support.rand.nextInt(12)];
		
		
	}
	public JButton getPlay()
	{
		return this.play;
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
		drawMovingBalls(g);
		drawTheTitle(g);
		drawThePlayButton((Graphics2D)g);
		drawTheQuitButton((Graphics2D)g);
		drawTheAboutButton((Graphics2D)g);
		g.dispose();
	}
	public void actionPerformed(ActionEvent arg0) 
	{
		moveMovingBalls();
		repaint();
		
	}
	
	public void createMovingBalls()
	{
		for(int i = 0;i<Support.rand.nextInt(20)+6;i++)
		{
			MovingBalls b = new MovingBalls(Support.giveMeARandomTOF());
			balls.add(b);
		}
	}
	public void moveMovingBalls()
	{
		for(MovingBalls b:balls)
		{
			b.moveTheBall();
			b.keepItInside();
		}
	}
	public void drawMovingBalls(Graphics g)
	{
		for(MovingBalls b:balls)
		{
			b.draw(g);
		}
	}
	public void createPlay()
	{
		play = new JButton("PLAY");
		play.setBounds(playXpos,playYpos,playWidth,playHeight);
		play.setFocusPainted(false);
		play.setBorderPainted(false);
		play.setContentAreaFilled(false);
		play.setToolTipText("Play different modes of the game ranging from the classic game to the modern one!");
		add(play);
	}
	
	public void drawThePlayButton(Graphics2D g2D)
	{
		g2D.setColor(saveColor);
		g2D.setStroke(new BasicStroke(5));
		g2D.drawRoundRect(this.playXpos, this.playYpos, this.playWidth, this.playHeight, 10, 10);
		g2D.setFont(new Font("Serfi",Font.PLAIN,50));
		g2D.drawString("PLAY", this.playXpos+85,this.playYpos+60 );
	}
	public void drawTheTitle(Graphics g)
	{
		g.setColor(this.saveColor);
		g.setFont(new Font("Serfi",Font.PLAIN,100));
		g.drawString("YUPNOPE", Size.Width/2-250, Size.Height-35);
	}
	public void stopTheTimer()
	{
		this.timer.stop();
	}
	public void initLabel()
	{
		this.titleLabel = new JLabel();
		this.titleLabel.setToolTipText("PROGRAMMING NAME OF Moh Yaghoub");
		this.titleLabel.setBounds(Size.Width/2-250,Size.Height-100,1000,300);
		add(this.titleLabel);
	}
	public void resetTheMenu()
	{
		setLayout(null);
//		ac.PlayAudio(menuMusic);
		timer.start();
		//playButton
		balls = new ArrayList<MovingBalls>();
		createMovingBalls();
		createPlay();
		this.saveColor = RANDOMCOLORLIST[Support.rand.nextInt(12)];
	}
	public void createAQiutTheGameButton()
	{
		quitthegame = new JButton("EXIT");
		quitthegame.setFocusPainted(false);
		quitthegame.setBorderPainted(false);
		quitthegame.setContentAreaFilled(false);
		quitthegame.setBounds(quitXpos,quitYpos,quitWidth,quitHeight);
		quitthegame.setToolTipText("Quit the game");
		add(quitthegame);
	}
	public void drawTheQuitButton(Graphics2D g2D)
	{
		g2D.setColor(Color.red);
		g2D.setStroke(new BasicStroke(8));
		g2D.drawRoundRect(this.quitXpos, this.quitYpos, this.quitWidth, this.quitHeight, 10, 10);
		g2D.setFont(new Font("Serfi",Font.PLAIN,70));
		g2D.drawString("x", this.quitXpos+7,this.quitYpos+45);
	}
	public JButton getQuitButton()
	{
		return this.quitthegame;
	}
	
	public void createAAboutTheGameButton()
	{
		aboutthegame = new JButton("EXIT");
		aboutthegame.setFocusPainted(false);
		aboutthegame.setBorderPainted(false);
		aboutthegame.setContentAreaFilled(false);
		aboutthegame.setBounds(aboutXpos,aboutYpos,aboutWidth,aboutHeight);
		aboutthegame.setToolTipText("About the game");
		add(aboutthegame);
	}
	public void drawTheAboutButton(Graphics2D g2D)
	{
		g2D.setColor(Color.green);
		g2D.setStroke(new BasicStroke(8));
		g2D.drawRoundRect(this.aboutXpos, this.aboutYpos, this.aboutWidth, this.aboutHeight, 10, 10);
		g2D.setFont(new Font("Serfi",Font.PLAIN,55));
		g2D.drawString("?", this.aboutXpos+7,this.aboutYpos+45);
	}
	public JButton getAboutButton()
	{
		return this.aboutthegame;
	}
	

}
