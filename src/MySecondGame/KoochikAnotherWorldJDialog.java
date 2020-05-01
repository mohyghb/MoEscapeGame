package MySecondGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;


public class KoochikAnotherWorldJDialog  {

	private JDialog jDialog;
	private JButton Ok;
	
	private JLabel tf1;
	private JLabel tf2;
	private JLabel tf3;
	
	
	KoochikAnotherWorldJDialog()
	{
		initClass();
	}
	
	public void initClass( )
	{
		initButton();
		initText();
		initJDialog();
		setActionListenerForClose();
		
	}
	public void initJDialog()
	{
		
		this.jDialog = new JDialog();
		jDialog.setLayout(null);
		jDialog.setTitle("WELCOME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		jDialog.setSize(600, 500);
		jDialog.setAutoRequestFocus(true);
		jDialog.setUndecorated(true);
		jDialog.setLocationRelativeTo(jDialog.getParent());
		jDialog.getContentPane().setBackground(Color.red);
		
		jDialog.add(this.tf1);
		jDialog.add(this.Ok);
		jDialog.add(this.tf2);
		jDialog.add(this.tf3);
		jDialog.setVisible(true);
	}
	
	public void initButton()
	{
		
		this.Ok = new JButton();
		this.Ok.setFont(new Font("Serfi",Font.PLAIN,20));
		this.Ok.setText("OK!");
		this.Ok.setBounds(270,450,70,50);
		this.Ok.setForeground(Color.black);
		this.Ok.setBorderPainted(false);
		this.Ok.setFocusPainted(false);
		this.Ok.setBackground(Color.GREEN);
	}
	
	public void initText()
	{
		inittf1();
		inittf2();
		inittf3();
	}
	public void inittf1()
	{
		this.tf1 = new JLabel();
		this.tf1.setText("A PINK POWER UP BRINGS YOU TO THIS WORLD!!");
		this.tf1.setBounds(30,140,500,100);
		this.tf1.setFont(new Font("Serfi",Font.PLAIN,20));
		this.tf1.setHorizontalAlignment(SwingConstants.CENTER);
		this.tf1.setForeground(Color.magenta);
	}
	public void inittf2()
	{
		this.tf2 = new JLabel();
		this.tf2.setText("DON'T WORRY!!!!!!");
		this.tf2.setFont(new Font("Serfi",Font.PLAIN,20));
		this.tf2.setBounds(200,200,200,100);
		this.tf2.setHorizontalAlignment(SwingConstants.CENTER);
		this.tf2.setForeground(Color.yellow);
	}
	public void inittf3()
	{
		this.tf3 = new JLabel();
		this.tf3.setText("JUST SURVIVE FOR 10 SECONDS AND YOU'LL GO BACK\n TO YOUR OWN WORLD!");
		this.tf3.setFont(new Font("Serfi",Font.PLAIN,15));
		this.tf3.setBounds(0,260,600,100);
		this.tf3.setHorizontalAlignment(SwingConstants.CENTER);
		this.tf3.setForeground(Color.orange);
	}
	
	public void setActionListenerForClose()
	{
		this.Ok.addActionListener(new ActionListener()
				{
						public void actionPerformed(ActionEvent e)
						{
							if(e.getSource()==Ok)
							{
								jDialog.setVisible(false);
								KoochikGame.makeTheJDialogFalse();
							}
						}
		
				}
		);
		this.Ok.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_SPACE)
				{
					e.consume();
				}
				else if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					jDialog.setVisible(false);
					KoochikGame.makeTheJDialogFalse();
				}
			}
		});
	}
	
}
