package MySecondGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class PlayMenu extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JButton classicGame;
	private int classicWidth = 291;
	private int classicHeight = 91;
	private int classicXpos = Size.Width/2-150;
	private int classicYpos = Size.Height/2-200;
	
	private JButton chantaGame;
	private int chantaWidth = 291;
	private int chantaHeight = 91;
	private int chantaXpos = Size.Width/2-150;
	private int chantaYpos = Size.Height/2-60;
	
	private JButton koochickGame;
	private int koochickWidth = 291;
	private int koochickHeight = 91;
	private int koochickXpos = Size.Width/2-150;
	private int koochickYpos = Size.Height/2+80;
	
	private JButton koochikTutorial;
	private int koochikTWidth = 70;
	private int koochikTXpos = Size.Width/2+200;
	private int koochikTYpos = Size.Height/2+90;
	
	private JButton chantaTutorial;
	private int chantaTWidth = 70;
	private int chantaTXpos = Size.Width/2+200;
	private int chantaTYpos = Size.Height/2-50;
	
	private JButton classicTutorial;
	private int classicTWidth = 70;
	private int classicTXpos = Size.Width/2+200;
	private int classicTYpos = Size.Height/2-190;
	
	
	private JButton returnButton;
	private int returnWidth = 140;
	private int returnHeight = 48;
	private int returnXpos = 10;
	private int returnYpos = Size.Height-100;
	
	
	ArrayList<MovingBalls> balls;
	
	private Image dbImage;
	private Graphics dbg;
	
	private Timer timer;
	
	PlayMenu()
	{
		initMenu();
	}
	
	public void initMenu()
	{
		setLayout(null);
		timer = new Timer(Support.DELAY,this);
		timer.start();
		balls = new ArrayList<MovingBalls>();
		createMovingBalls();
		createClassicGameButton();
		createAChantaButton();
		createAKoochickGameButton();
		createAKoochikTutorialButton();
		createAChantaTutorialButton();
		createAClassicTutorialButton();
		initReturnButton();
		
	}
	public JButton getClassicGameButton()
	{
		return this.classicGame;
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
		drawTheClassicGame((Graphics2D)g);
		drawTheChantaGame((Graphics2D)g);
		drawTheKoochickGameButton((Graphics2D)g);
		drawTheReturnButton((Graphics2D)g);
		drawKoochikTutorial((Graphics2D)g);
		drawTheChantaTutorialButton((Graphics2D)g);
		drawTheClassicTutorialButton((Graphics2D)g);
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
	public void createClassicGameButton()
	{
		classicGame = new JButton("CLASSIC GAME");
		classicGame.setFocusPainted(false);
		classicGame.setBorderPainted(false);
		classicGame.setContentAreaFilled(false);
		classicGame.setToolTipText("The Original idea by YUPNOPE (Moh Yaghoub)");
		classicGame.setBounds(this.classicXpos,this.classicYpos,this.classicWidth,this.classicHeight);
		add(classicGame);
	}
	public void drawTheClassicGame(Graphics2D g2D)
	{
		g2D.setStroke(new BasicStroke(5));
		g2D.drawRoundRect(this.classicXpos, this.classicYpos, this.classicWidth, this.classicHeight, 10, 10);
		g2D.setFont(new Font("Serfi",Font.PLAIN,50));
		g2D.drawString("CLASSIC", this.classicXpos+40,this.classicYpos+60 );
	}
	public void createAChantaButton()
	{
		chantaGame = new JButton("");
		chantaGame.setFocusPainted(false);
		chantaGame.setBorderPainted(false);
		chantaGame.setContentAreaFilled(false);
		chantaGame.setToolTipText("How many Bombs can you deffuse? (By Moh Yaghoub)");
		chantaGame.setBounds(this.chantaXpos,this.chantaYpos,this.chantaWidth,this.chantaHeight);
		add(chantaGame);
	}
	public void drawTheChantaGame(Graphics2D g2D)
	{
		g2D.setStroke(new BasicStroke(5));
		g2D.drawRoundRect(this.chantaXpos, this.chantaYpos, this.chantaWidth, this.chantaHeight, 10, 10);
		g2D.setFont(new Font("Serfi",Font.PLAIN,50));
		g2D.drawString("CHANTA", this.chantaXpos+40,this.chantaYpos+60 );
	}
	public JButton getChantaGameButton()
	{
		return this.chantaGame;
	}
	public void createAKoochickGameButton()
	{
		koochickGame = new JButton("");
		koochickGame.setFocusPainted(false);
		koochickGame.setBorderPainted(false);
		koochickGame.setContentAreaFilled(false);
		koochickGame.setToolTipText("Get the green paricles to make yourself smaller in order to spawn the defuser, then get the defuser and touch the bomb in order to defuse it! (By Moh Yaghoub)");
		koochickGame.setBounds(this.koochickXpos,this.koochickYpos,this.koochickWidth,this.koochickHeight);
		add(koochickGame);
	}
	public void drawTheKoochickGameButton(Graphics2D g2d)
	{
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(this.koochickXpos, this.koochickYpos, this.koochickWidth, this.koochickHeight, 10, 10);
		g2d.setFont(new Font("Serfi",Font.PLAIN,50));
		g2d.drawString("KOOCHIK", this.koochickXpos+30,this.koochickYpos+60 );
	}
	public JButton getKoochickGameButton()
	{
		return this.koochickGame;
	}
	public void initReturnButton()
	{
		returnButton = new JButton("");
		returnButton.setFocusPainted(false);
		returnButton.setBorderPainted(false);
		returnButton.setContentAreaFilled(false);
		returnButton.setToolTipText("return to the main menu");
		returnButton.setBounds(this.returnXpos,this.returnYpos,this.returnWidth,this.returnHeight);
		add(returnButton);
	}
	public void drawTheReturnButton(Graphics2D g2d)
	{
		g2d.setColor(Color.green);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(this.returnXpos, this.returnYpos, this.returnWidth, this.returnHeight, 10, 10);
		g2d.setFont(new Font("Serfi",Font.PLAIN,50));
		g2d.drawString("<<<", this.returnXpos+20,this.returnYpos+40 );
	}
	public JButton getReturnButton()
	{
		return this.returnButton;
	}
	public void createAKoochikTutorialButton()
	{
		koochikTutorial = new JButton("");
		koochikTutorial.setFocusPainted(false);
		koochikTutorial.setBorderPainted(false);
		koochikTutorial.setContentAreaFilled(false);
		koochikTutorial.setToolTipText("Tutorial for the koochik Game mode (By Moh Yaghoub)");
		koochikTutorial.setBounds(this.koochikTXpos,this.koochikTYpos,this.koochikTWidth,this.koochikTWidth);
		add(koochikTutorial);
	}
	public void drawKoochikTutorial(Graphics2D g2d)
	{
		
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(this.koochikTXpos, this.koochikTYpos, this.koochikTWidth, this.koochikTWidth, 10, 10);
		g2d.setFont(new Font("Serfi",Font.PLAIN,50));
		g2d.drawString("T", this.koochikTXpos+20,this.koochikTYpos+50 );
	}
	public JButton getKoochikTutorialButton()
	{
		return this.koochikTutorial;
	}
	public void createAChantaTutorialButton()
	{
		chantaTutorial = new JButton("");
		chantaTutorial.setFocusPainted(false);
		chantaTutorial.setBorderPainted(false);
		chantaTutorial.setContentAreaFilled(false);
		chantaTutorial.setToolTipText("Tutorial for the Chanta Game mode (By Moh Yaghoub)");
		chantaTutorial.setBounds(this.chantaTXpos,this.chantaTYpos,this.chantaTWidth,this.chantaTWidth);
		add(chantaTutorial);
	}
	public void drawTheChantaTutorialButton(Graphics2D g2d)
	{
		
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(this.chantaTXpos, this.chantaTYpos, this.chantaTWidth, this.chantaTWidth, 10, 10);
		g2d.setFont(new Font("Serfi",Font.PLAIN,50));
		g2d.drawString("T", this.chantaTXpos+20,this.chantaTYpos+50 );
	}
	public JButton getChantaTutorialButton()
	{
		return this.chantaTutorial;
	}
	
	public void createAClassicTutorialButton()
	{
		classicTutorial = new JButton("");
		classicTutorial.setFocusPainted(false);
		classicTutorial.setBorderPainted(false);
		classicTutorial.setContentAreaFilled(false);
		classicTutorial.setToolTipText("Tutorial for the Classic Game mode (By Moh Yaghoub)");
		classicTutorial.setBounds(this.classicTXpos,this.classicTYpos,this.classicTWidth,this.classicTWidth);
		add(classicTutorial);
	}
	public void drawTheClassicTutorialButton(Graphics2D g2d)
	{
		
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(this.classicTXpos, this.classicTYpos, this.classicTWidth, this.classicTWidth, 10, 10);
		g2d.setFont(new Font("Serfi",Font.PLAIN,50));
		g2d.drawString("T", this.classicTXpos+20,this.classicTYpos+50 );
	}
	public JButton getClassicTutorialButton()
	{
		return this.classicTutorial;
	}


}
