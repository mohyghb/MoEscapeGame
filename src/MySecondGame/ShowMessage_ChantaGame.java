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

public class ShowMessage_ChantaGame {
	
	private String str;
	private String str1;
	private Color color;
	JDialog j;
	static JDialog jlist[] = new JDialog[11];
	private int x;
	private int y;
	private int w;
	private int h;
	private JButton ok;
	private boolean flash;
	ShowMessage_ChantaGame(String s,String s1,Color c,int x,int y,int w,int h)
	{
		
		this.str = s;
		this.str1 = s1;
		this.color = c;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.flash = false;
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
		j.add(new JLabel(this.str),BorderLayout.NORTH);
		j.add(new JLabel(this.str1), BorderLayout.CENTER);
		j.add(ok, BorderLayout.SOUTH);
		j.setVisible(true);
		jlist[Tutorial_Chanta.totaltime-1]=j;
	}
	public void initOk()
	{
		this.ok = new JButton("OK!");
		ok.setBackground(Color.green);
		ok.setFocusPainted(false);
		ok.setBorderPainted(false);
		ok.setContentAreaFilled(false);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				j.setVisible(false);
				Tutorial_Chanta.totaltime++;
			    Tutorial_Chanta.createAMessage(Tutorial_Chanta.totaltime);
				j.dispose();
			}
		});
		
		ok.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if(KeyEvent.VK_SPACE==e.getKeyCode())
				{
					e.consume();
				}
				else if(KeyEvent.VK_ENTER==e.getKeyCode())
				{
					j.setVisible(false);
					Tutorial_Chanta.totaltime++;
					Tutorial_Chanta.createAMessage(Tutorial_Chanta.totaltime);
					j.dispose();
				}
			}
		});
	}
	public static void destroyJDialog()
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
		this.flash = !this.flash;
	}
	public void draw(boolean gotTheDefuser,int direction,int defx, int defy,int playerx, int playery,int control,Graphics2D g2d)
	{
		if(this.flash)
		{
			switch(control)
			{
				default:
					break;
				case 2:
					g2d.setStroke(new BasicStroke(12));
					g2d.setColor(Color.green);
					g2d.drawRect(playerx-83, playery, 123, 40);
					break;
				case 4:
					g2d.setStroke(new BasicStroke(15));
					g2d.setColor(Color.magenta);
					g2d.drawRect(defx-11, defy-20, 80,80);
					break;
				case 5:
					if(gotTheDefuser)
					{
						g2d.setStroke(new BasicStroke(40));
						g2d.setColor(Color.green);
						g2d.drawRect(Size.Width/2-120, 60, 200, 450);
					}
					
					break;
				case 6:
					g2d.setStroke(new BasicStroke(12));
					g2d.setColor(Color.YELLOW);
					g2d.drawRect(10, 3, 115,43);
					break;
				case 7:
					g2d.setStroke(new BasicStroke(12));
					g2d.setColor(Color.red);
					g2d.drawRect(playerx, playery,40, 40);
					break;
				case 8:
					g2d.setStroke(new BasicStroke(12));
					g2d.setColor(Color.green);
					
					//when player is moving, they can end up being in a left,right,up,or down direction 
					switch(direction)
					{
						default:
							break;
						case 1:
							//right
							g2d.drawRect(playerx-83, playery, 83, 40);
							break;
						case 2:
							//up
							g2d.drawRect(playerx, playery+43, 40, 83);
							break;
						case 3:
							//left
							g2d.drawRect(playerx+43,playery, 83,40);
							break;
						case 4:
							//down
							g2d.drawRect(playerx, playery-83, 40, 83);
							break;
					}
					break;
					
			}
		}
		
	}

}
