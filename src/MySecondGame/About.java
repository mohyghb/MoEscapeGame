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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class About extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image dbImage;
	private Graphics dbg;
	private Timer timer;
	private ArrayList<MovingBalls>balls;
	
	
	private JButton backButton;
	private int backx =10 ;
	private int backy =Size.Height-45 ;
	private int backw = 110;
	private int backh = 40;
	About()
	{
		setLayout(null);
		initTimer();
		balls = new ArrayList<MovingBalls>();
		initBackButton();
		createMovingBalls();
	}
	public void actionPerformed(ActionEvent e) {
		moveMovingBalls();
		repaint();
	}
	
	public void paint(Graphics g) {
		dbImage = createImage(Size.Width,Size.Height);
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Size.Width, Size.Height);
		g.setFont(new Font("Serfi",Font.BOLD,80));
		g.setColor(Color.black);
		g.drawString("by:", Size.Width/2-100, Size.Height/2-100);
		g.drawString(Support.myName, Size.Width/2-250, Size.Height/2);
		g.setFont(new Font("Serfi",Font.PLAIN,20));
		g.drawString("Produced in 2017", Size.Width/2-100, Size.Height/2+40);
		drawMovingBalls(g);
		drawBackButton((Graphics2D)g);
		g.dispose();
	}
	public void initTimer()
	{
		timer = new Timer(Support.DELAY,this);
		timer.start();
	}
	public void stopTheTimer()
	{
		timer.stop();
	}
	public void startTheTimer()
	{
		timer.start();
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
	public void initBackButton()
	{
		backButton = new JButton("PLAY");
		backButton.setBounds(backx,backy,backw,backh);
		backButton.setFocusPainted(false);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setToolTipText("Go back to the main menu");
		add(backButton);
	}
	public void drawBackButton(Graphics2D g2D)
	{
		g2D.setColor(Color.green);
		g2D.setStroke(new BasicStroke(5));
		g2D.drawRoundRect(this.backx, this.backy, this.backw, this.backh, 10, 10);
		g2D.setFont(new Font("Serfi",Font.PLAIN,50));
		g2D.drawString("<<<", this.backx+10,this.backy+36 );
	}
	public JButton getBackButton()
	{
		return this.backButton;
	}

}
