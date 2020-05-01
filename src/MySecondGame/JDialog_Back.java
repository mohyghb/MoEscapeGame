package MySecondGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JDialog_Back {
	
	private JDialog jd;
	private JLabel jl;
	private static JButton jby;
	private JButton jbn;
	
	JDialog_Back()
	{
		initClass();
	}
	public void initClass()
	{
		createAYesButton();
		createANoButton();
		createJLabel();
		initJDialog();
	}
	public void initJDialog()
	{
		this.jd = new JDialog();
		jd.setLayout(null);
		jd.setBounds(Size.Width/2-250,Size.Height/2-250,600,300);
		jd.getContentPane().setBackground(Color.cyan);
		jd.setUndecorated(true);
		jd.setAlwaysOnTop(true);
		jd.requestFocus();
		jd.add(jbn);
		jd.add(jby);
		jd.add(jl);
		jd.setVisible(false);
	}
	public void createJLabel()
	{
		jl = new JLabel();
		jl.setFont(new Font("Serfi",Font.PLAIN,20));
		jl.setText("Are you sure you want to quit?");
		jl.setBounds(160,20,300,50);
		jl.setHorizontalTextPosition(SwingConstants.CENTER);
		jl.setVerticalTextPosition(SwingConstants.TOP);
	}
	public void createAYesButton()
	{
		jby = new JButton();
		jby.setFont(new Font("Serfi",Font.PLAIN,20));
		jby.setText("YES!");
		jby.setBounds(500, 250, 100, 50);
		jby.setForeground(Color.black);
		jby.setFocusPainted(false);
		jby.setBorderPainted(false);
		jby.setBackground(Color.RED);
		
	}
	public void createANoButton()
	{
		jbn = new JButton();
		jbn.setFont(new Font("Serfi",Font.PLAIN,20));
		jbn.setText("NO!");
		jbn.setBounds(0, 250, 100, 50);
		jbn.setForeground(Color.black);
		jbn.setFocusPainted(false);
		jbn.setBorderPainted(false);
		jbn.setBackground(Color.green);
		jbn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jd.setVisible(false);
				jd.dispose();
			}
		});
	}
	public void setJDialogVisisble()
	{
		this.jd.setVisible(true);
	}
	public static JButton getYes()
	{
		return jby;
	}

}
