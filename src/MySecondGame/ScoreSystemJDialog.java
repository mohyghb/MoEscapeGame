package MySecondGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class ScoreSystemJDialog {
	
	private static JDialog jDialog;
	private JButton okButton;
	private JTextField name;
	private int level;
	
	ScoreSystemJDialog(int l, int c)
	{
		initClass(l,c);
	}
	public void initClass(int l ,int c)
	{
		initButton(c);
		initName(c);
		initLevel(l);
		initJDialog();
		
	}
	
	public void initJDialog()
	{
		ScoreSystemJDialog.jDialog = new JDialog();
		jDialog.setLayout(new BorderLayout());
		jDialog.setSize(500,200);
		jDialog.setLocationRelativeTo(jDialog.getParent());
		jDialog.getContentPane().setBackground(Color.green);
		jDialog.setUndecorated(true);
		jDialog.setAlwaysOnTop(true);
		jDialog.add(this.name,BorderLayout.SOUTH);
		jDialog.add(this.okButton,BorderLayout.NORTH);
		jDialog.setVisible(true);
	}
	public void initButton(int i)
	{
		this.okButton = new JButton("ENTER");
		this.okButton.setFont(new Font("Serfi",Font.BOLD+Font.ITALIC,100));
		this.okButton.setBackground(Color.green);
		this.okButton.setForeground(Color.white);
		okButton.setBackground(Color.green);
		okButton.setFocusPainted(false);
		okButton.setBorderPainted(false);
		okButton.setContentAreaFilled(false);
		this.okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(e.getSource()==okButton&&name.getText().length()<=4&&name.getText().length()!=0&&!name.getText().startsWith(" "))
						{
							ScoreSystem s = new ScoreSystem(level,name.getText());
							StringBuilder stringbuilder = new StringBuilder();
							stringbuilder.append(String.format("%s~",s.saveString()));
							
							switch(i)
							{
							case 1:
								Support.docsSave(Support.Classic, stringbuilder.toString());
								break;
							case 2:
								Support.docsSave(Support.ChantaGame, stringbuilder.toString());
								break;
							case 3:
								Support.docsSave(Support.KoochikGame, stringbuilder.toString());
								break;
								
								default:
									
									break;
							}
							
								
								jDialog.setVisible(false);
								jDialog.dispose();
							
						}
						else
							name.setText("Invalid name");
					}
				});
		
		
	}
			
	public void initName(int i)
	{
		this.name = new JTextField();
		name.setText("Your name");
		name.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e)
			{
				if(name.getText().length()>=4)
				{
					e.consume();
				}
			}
			
		});
		name.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	public static void deleteTheJDialog()
	{
		if(jDialog!=null)
		{
			jDialog.setVisible(false);
			jDialog.dispose();
		}
		
	}
	
	public void initLevel(int l )
	{
		this.level = l;
	}
	

	
}
