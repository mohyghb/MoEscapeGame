package MySecondGame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;


public class ShowMessage  {
	String str;
	String str1;
	Color color;
	JDialog j;
	static JDialog jlist[] = new JDialog[11];
	int x;
	int y;
	int w;
	int h;
	private JButton ok;
	private boolean shouldFlash;
	ShowMessage(String s,String s1,Color c,int x,int y,int w,int h)
	{
		
		this.str = s;
		this.str1 = s1;
		this.color = c;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.shouldFlash = false;
		initOk();
		createAJDialog();
	}
	public void createAJDialog()
	{
		j = new JDialog();
		j.setLayout(new BorderLayout());
		j.setBounds(this.x,this.y,this.w,this.h);
		j.getContentPane().setBackground(color);
		j.setUndecorated(true);
		j.setAlwaysOnTop(true);
		j.requestFocus();
		j.add(new JLabel(this.str),BorderLayout.NORTH);
		j.add(new JLabel(this.str1), BorderLayout.CENTER);
		j.add(ok, BorderLayout.SOUTH);
		j.setVisible(true);
		jlist[Tutorial_KoochikGame.TotalTime-1] = j;
		
	}
	public void initOk()
	{
		this.ok = new JButton("OK!");
		ok.setFocusPainted(false);
		ok.setBorderPainted(false);
		ok.setContentAreaFilled(false);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				j.setVisible(false);
				Tutorial_KoochikGame.TotalTime++;
				Tutorial_KoochikGame.createAMessage(true,Tutorial_KoochikGame.TotalTime);
				if(Tutorial_KoochikGame.TotalTime>=9)
				{
					Tutorial_KoochikGame.JMessageIsOn = false;
				}
				j.dispose();
				
			}
		});
		ok.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(KeyEvent.VK_SPACE==e.getKeyCode())
				{
					e.consume();	
				}
				else if(KeyEvent.VK_ENTER==e.getKeyCode()&&Tutorial_KoochikGame.JMessageIsOn)
				{
					j.setVisible(false);
					Tutorial_KoochikGame.TotalTime++;
					Tutorial_KoochikGame.createAMessage(true,Tutorial_KoochikGame.TotalTime);
					if(Tutorial_KoochikGame.TotalTime>=9)
					{
						Tutorial_KoochikGame.JMessageIsOn = false;
					}
					j.dispose();
				}
				
			}
		});
		
	}
	public static void destroyJDialogs()
	{
		for(JDialog d:jlist)
		{
			if(d!=null)
			{
				d.setVisible(false);
				d.dispose();
			}
		}
	}
	public void flash()
	{
		this.shouldFlash = !this.shouldFlash;
	}
	// wants to make it flashing 
	public void draw(Graphics2D g2d,int num)
	{
		
		if(this.shouldFlash)
		{
			switch(num)
			{
			default:
				break;
			case 2:
				g2d.setStroke(new BasicStroke(10));
				g2d.setColor(Color.yellow);
				g2d.drawRect(Size.Width/2-7, Size.Height/2-7, 80, 80);
				break;
			case 3:
				g2d.setStroke(new BasicStroke(15));
				g2d.setColor(Color.blue);
				g2d.drawRect(0, 0, 110, 100);
				break;
			case 5:
				g2d.setStroke(new BasicStroke(15));
				g2d.setColor(Color.ORANGE);
				g2d.drawRect(Size.Width/2+110, Size.Height/2-85, 125, 105);
				break;
			case 6:
				g2d.setStroke(new BasicStroke(15));
				g2d.setColor(Color.green);
				g2d.drawRect(Size.Width/2-294, Size.Height/2-85, 125, 105);
				break;
			case 10:
				g2d.setStroke(new BasicStroke(12));
				g2d.setColor(Color.magenta);
				g2d.drawOval(Size.Width/2-41, Size.Height/2-41,80, 80);
				break;
			case 11:
				g2d.setStroke(new BasicStroke(15));
				g2d.setColor(Color.blue);
				g2d.drawRect(0, 0, 110, 100);
				break;
			}
			
				
		}
		g2d.dispose();
		
		
		
		
	}
}
