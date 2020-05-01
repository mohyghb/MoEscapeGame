package MySecondGame;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class PausePanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton restart;
	private int restartWidth = 291;
	private int restartHeight = 91;
	private int restartXpos = Size.Width/2-150;
	private int restartYpos = Size.Height/2-60;
	
	private JButton quit;
	private int quitWidth = 291;
	private int quitHeight = 91;
	private int quitXpos = Size.Width/2-150;
	private int quitYpos = Size.Height/2+80;
	
	private JButton resume;
	private int resumeWidth = 291;
	private int resumeHeight = 91;
	private int resumeXpos = Size.Width/2-150;
	private int resumeYpos = Size.Height/2-200;
	
	private Image dbImage;
	private Graphics dbg;
	
	private Timer timer;
	
	ArrayList<MovingBalls> balls;
	PausePanel()
	{
		initMenu();
	}
	
	public void actionPerformed(ActionEvent e) {
		moveMovingBalls();
		repaint();
		
	}
	public void initMenu()
	{
		setLayout(null);
		balls = new ArrayList<MovingBalls>();
		timer = new Timer(Support.DELAY,this);
		createAResume();
		createARestart();
		createAQuit();
		startTheTimer();
		createMovingBalls();
		
		

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
		drawResume((Graphics2D )g);
		drawRestart((Graphics2D)g);
		drawQuit((Graphics2D)g);
		
		g.dispose();
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
	public void startTheTimer()
	{
		this.timer.start();
	}
	public void stopTheTimer()
	{
		this.timer.stop();
	}
	public void createAResume()
	{
		resume = new JButton();
		resume.setFocusPainted(false);
		resume.setBorderPainted(false);
		resume.setContentAreaFilled(false);
		resume.setBounds(this.resumeXpos,this.resumeYpos,this.resumeWidth,this.resumeHeight);
		resume.setToolTipText("Continue the game without loosing anything");
		add(resume);
	}
	public void drawResume(Graphics2D g)
	{
		g.setStroke(new BasicStroke(5));
		g.drawRoundRect(resumeXpos, resumeYpos, resumeWidth, resumeHeight, 10, 10);
		g.setFont(new Font("Serfi",Font.PLAIN,50));
		g.drawString("RESUME", this.resumeXpos+40,this.resumeYpos+60 );
	}
	public JButton getResume()
	{
		return this.resume;
	}
	public void createARestart()
	{
		restart = new JButton();
		restart.setFocusPainted(false);
		restart.setBorderPainted(false);
		restart.setContentAreaFilled(false);
		restart.setBounds(this.restartXpos,this.restartYpos,this.restartWidth,this.restartHeight);
		restart.setToolTipText("restart the game");
		add(restart);
		
	}
	public void drawRestart(Graphics2D g)
	{
		g.setStroke(new BasicStroke(5));
		g.drawRoundRect(restartXpos, restartYpos, restartWidth, restartHeight, 10, 10);
		g.setFont(new Font("Serfi",Font.PLAIN,50));
		g.drawString("RESTART", this.restartXpos+40,this.restartYpos+60);
	}
	public JButton getRestart()
	{
		return this.restart;
	}
	public void createAQuit()
	{
		quit = new JButton();
		quit.setFocusPainted(false);
		quit.setBorderPainted(false);
		quit.setContentAreaFilled(false);
		quit.setBounds(this.quitXpos,this.quitYpos,this.quitWidth,this.quitHeight);
		quit.setToolTipText("quit the game to the main menu");
		add(quit);
	}
	public void drawQuit(Graphics2D g2d)
	{
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(quitXpos, quitYpos, quitWidth, quitHeight, 10, 10);
		g2d.setFont(new Font("Serfi",Font.PLAIN,50));
		g2d.drawString("QUIT", this.quitXpos+80, this.quitYpos+60);
	}
	public JButton getQuit()
	{
		return this.quit;
	}
	
	

}
