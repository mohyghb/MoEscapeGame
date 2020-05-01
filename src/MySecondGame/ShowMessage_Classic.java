package MySecondGame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ShowMessage_Classic {
	private String str;
	private String str1;
	private Color color;
	JDialog j;
	static JDialog jlist[] = new JDialog[20];
	private int x;
	private int y;
	private int w;
	private int h;
	private JButton ok;
	private boolean flash;
	ShowMessage_Classic(String s,String s1,Color c,int x,int y,int w,int h)
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
		jlist[Tutorial_Classic.totaltime-1] = j;
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
				Tutorial_Classic.totaltime++;
			    Tutorial_Classic.createAMessage(Tutorial_Classic.totaltime);
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
					Tutorial_Classic.totaltime++;
					Tutorial_Classic.createAMessage(Tutorial_Classic.totaltime);
					j.dispose();
				}
			}
		});
	}
	public static void destroyTheJList()
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
	public void draw(int defx,int defy,int control,Graphics2D g2d)
	{
		if(this.flash)
		{
			switch(control)
			{
				default:
					break;
				case 2:
					g2d.setStroke(new BasicStroke(9));
					g2d.setColor(Color.GREEN);
					g2d.drawRect(Size.Width/2,Size.Height/2, 40, 40);
					break;
				case 3:
					g2d.setStroke(new BasicStroke(9));
					g2d.setColor(Color.ORANGE);
					g2d.drawRect(Size.Width/2-(6*41)+8, Size.Height/2, 235, 40);
					break;
				case 4:
					g2d.setStroke(new BasicStroke(9));
					g2d.setColor(Color.cyan);
					g2d.drawRect(defx-5,defy-5, 70, 60);
					break;
				case 14:
					g2d.setColor(Color.black);
					g2d.setFont(new Font("Serfi",Font.BOLD,70));
					g2d.drawString("PRESS SPACE!", Size.Width/2-220, Size.Height/2+20);
					break;
				case 18:
					g2d.setColor(Color.black);
					g2d.setFont(new Font("Serfi",Font.BOLD,70));
					g2d.drawString("PRESS 'S'!", Size.Width/2-185, Size.Height/2+20);
					break;
				case 19:
					g2d.setStroke(new BasicStroke(12));
					g2d.setColor(Color.yellow);
					g2d.drawRect(Size.Width-210,2, 100, 60);
					break;
			}
		}
		
	}

}
