package MySecondGame;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class EscapeMain extends JFrame{
	//YUPNOPE PRESENTS//
	private static final long serialVersionUID = 1L;
	//YUPNOPE PRESENTS//
	
	EscapeGame game = new EscapeGame();
	Menu menu = new Menu();
	PlayMenu playmenu = new PlayMenu();
	PausePanel pausepanel = new PausePanel();
	ChantaGame chantagame = new ChantaGame();
	KoochikGame koochikgame = new KoochikGame();
	About about = new About();
	JDialog_Back j = new JDialog_Back();
	
	Tutorial_KoochikGame tutorialkoochikgame = new Tutorial_KoochikGame();
	Tutorial_Chanta tutorialchanta = new Tutorial_Chanta();
	Tutorial_Classic tutorialclassic = new Tutorial_Classic();
	
	private boolean ClassicPause = false;
	private boolean ChantaPause = false;
	private boolean KoochikPause = false;
	private boolean tutorial_koochikPause = false;
	private boolean tutorial_chantaPause = false;
	private boolean tutorial_classicPause = false;
	
	EscapeMain()
	{
		
		setSize(Size.Width,Size.Height);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(menu);
		setUndecorated(true);
		
		
		setActionListenerForPlay();
		setActionListenerForClassicGameButton();
		setActionListenerForPausePanel();
		setActionListenerForResumeButton();
		setActionListenerForRestartButton();
		setActionListenerForQuitButton();
		setActionListenerForTheChantaGameButton();
		setActionListenerForTheKoochikGameButton();
		setActionListenerForTheReturnButton();
		setActionListenerForTheKoochikTutorial();
		setActionListenerForTheChantaTutorial();
		setActionListenerForQuitTheGameButton();
		setActionListenerForTheClassicTutorial();
		setActionListenerForTheAboutButton();
		setActionListenerForTheBackButton();
		setActionListenerForTheYesButton();
		stopAllTheTimers();
		setVisible(true);
	}
	
	//Menu ActionListeners
	public void setActionListenerForPlay()
	{
		menu.getPlay().addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(playmenu);
				
				repaint();
				printAll(getGraphics());
			}
		});
	}
	
	//pausepanel
	public void setActionListenerForPausePanel()
	{
		Support.addKeyBinding(game, KeyEvent.VK_ESCAPE, "PauseClassic", (evt)->{
			game.stopTheTimer();
			game.makePlayFalse();
			ClassicPause = true;
			ChantaPause = false;
			KoochikPause = false;
			tutorial_chantaPause = false;
			tutorial_classicPause = false;
			tutorial_koochikPause = false;
			getContentPane().removeAll();
			getContentPane().add(pausepanel);
			ScoreSystemJDialog.deleteTheJDialog();
			pausepanel.startTheTimer();
			repaint();
			printAll(getGraphics());
			
		}, false);
		Support.addKeyBinding(chantagame, KeyEvent.VK_ESCAPE, "PauseChanta", (evt)->{
			chantagame.stopTheTimer();
			chantagame.makePlayFalse();
			ClassicPause = false;
			ChantaPause = true;
			KoochikPause = false;
			tutorial_chantaPause = false;
			tutorial_classicPause = false;
			tutorial_koochikPause = false;
			getContentPane().removeAll();
			getContentPane().add(pausepanel);
			ScoreSystemJDialog.deleteTheJDialog();
			pausepanel.startTheTimer();
			repaint();
			printAll(getGraphics());
		}, false);
		Support.addKeyBinding(koochikgame, KeyEvent.VK_ESCAPE, "PauseKoochik", (evt)->{
			koochikgame.stopTheTimer();
			koochikgame.makeThePlayFalse();
			ClassicPause = false;
			ChantaPause = false;
			KoochikPause = true;
			tutorial_chantaPause = false;
			tutorial_classicPause = false;
			tutorial_koochikPause = false;
			getContentPane().removeAll();
			getContentPane().add(pausepanel);
			ScoreSystemJDialog.deleteTheJDialog();
			pausepanel.startTheTimer();
			repaint();
			printAll(getGraphics());
		}, false);
		Support.addKeyBinding(tutorialkoochikgame, KeyEvent.VK_ESCAPE, "tutorialkoochikpause", (evt)->{
			
			tutorialkoochikgame.stopTheTimer();
			tutorialkoochikgame.makeThePlayFalse();
			ClassicPause = false;
			ChantaPause = false;
			KoochikPause = false;
			tutorial_chantaPause = false;
			tutorial_classicPause = false;
			tutorial_koochikPause = true;
			getContentPane().removeAll();
			getContentPane().add(pausepanel);
			pausepanel.startTheTimer();
			ShowMessage.destroyJDialogs();
			repaint();
			printAll(getGraphics());
		}, false);
		Support.addKeyBinding(tutorialchanta, KeyEvent.VK_ESCAPE, "tutorialchantapause", (evt)->{
			tutorialchanta.stopTheTimer();
			tutorialchanta.makePlayFalse();
			ClassicPause = false;
			ChantaPause = false;
			KoochikPause = false;
			tutorial_chantaPause = true;
			tutorial_koochikPause = false;
			tutorial_classicPause =  false;
			getContentPane().removeAll();
			getContentPane().add(pausepanel);
			pausepanel.startTheTimer();
			ShowMessage_ChantaGame.destroyJDialog();
			repaint();
			printAll(getGraphics());
		}, false);
		Support.addKeyBinding(tutorialclassic, KeyEvent.VK_ESCAPE, "tutorialclassicpause", (evt)->{
			tutorialclassic.stopTheTimer();
			tutorialclassic.makePlayFalse();
			ClassicPause = false;
			ChantaPause = false;
			KoochikPause = false;
			tutorial_chantaPause = false;
			tutorial_koochikPause = false;
			tutorial_classicPause = true;
			getContentPane().removeAll();
			getContentPane().add(pausepanel);
			pausepanel.startTheTimer();
			ShowMessage_Classic.destroyTheJList();
			repaint();
			printAll(getGraphics());
		}, false);
		
		//leave the tutorial by entering space
		Support.addKeyBinding(tutorialkoochikgame, KeyEvent.VK_SPACE, "leavetutorialkoochik", (evt)->{
			if(State.isWon()||State.isLost())
			{
				this.stopAllTheTimers();
				getContentPane().removeAll();
				getContentPane().add(menu);
				menu.resetTheMenu();
				
				repaint();
				printAll(getGraphics());
			}
		}, false);
		Support.addKeyBinding(tutorialchanta, KeyEvent.VK_SPACE, "leavetutorialchanta", (evt)->{
			if(State.isWon()||State.isLost())
			{
				this.stopAllTheTimers();
				getContentPane().removeAll();
				getContentPane().add(menu);
				menu.resetTheMenu();
				
				repaint();
				printAll(getGraphics());
			}
		}, false);
		
		
		tutorialclassic.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e)
			{
				if(State.isWon()||State.isLost())
				{
					if(e.getKeyCode()==KeyEvent.VK_SPACE)
					{
//						tutorialclassic.stopTheMusic();
						getContentPane().removeAll();
						getContentPane().add(menu);
						menu.resetTheMenu();
						tutorialchanta.stopTheTimer();
						State.isMenu();
						repaint();
						printAll(getGraphics());
					}
				}
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	//resume
	public void setActionListenerForResumeButton()
	{
		pausepanel.getResume().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(ClassicPause)
				{
				pausepanel.stopTheTimer();
				game.startTimer();
				getContentPane().removeAll();
				getContentPane().add(game);
				
				repaint();
				printAll(getGraphics());
				}
				else if(ChantaPause)
				{
					pausepanel.stopTheTimer();
					chantagame.startTheTimer();
					getContentPane().removeAll();
					getContentPane().add(chantagame);
					repaint();
					printAll(getGraphics());
				}
				else if (KoochikPause)
				{
					pausepanel.stopTheTimer();
					koochikgame.startTheTimer();
					getContentPane().removeAll();
					getContentPane().add(koochikgame);
					repaint();
					printAll(getGraphics());
				}
				else if(tutorial_koochikPause)
				{
					pausepanel.stopTheTimer();
					tutorialkoochikgame.startTheTimer();
					getContentPane().removeAll();
					getContentPane().add(tutorialkoochikgame);
					Tutorial_KoochikGame.createAMessage(true, Tutorial_KoochikGame.TotalTime);
					repaint();
					printAll(getGraphics());
				}
				else if(tutorial_chantaPause)
				{
					pausepanel.stopTheTimer();
					tutorialchanta.startTheTimer();
					getContentPane().removeAll();
					getContentPane().add(tutorialchanta);
					Tutorial_Chanta.createAMessage(Tutorial_Chanta.totaltime);
					repaint();
					printAll(getGraphics());
				}
				else if(tutorial_classicPause)
				{
					pausepanel.stopTheTimer();
					tutorialclassic.startTimer();
					getContentPane().removeAll();
					getContentPane().add(tutorialclassic);
					Tutorial_Classic.createAMessage(Tutorial_Classic.totaltime);
					repaint();
					printAll(getGraphics());
				}
				
			}
		});
	}
	//restrat
	public void setActionListenerForRestartButton()
	{
		pausepanel.getRestart().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(ClassicPause)
				{
				pausepanel.stopTheTimer();
				getContentPane().removeAll();
				getContentPane().add(game);
//				game.stopTheMusic();
				game.initGame();
				game.resetTheWholeGame();
				repaint();
				printAll(getGraphics());
				}
				else if(ChantaPause)
				{
					pausepanel.stopTheTimer();
					getContentPane().removeAll();
					getContentPane().add(chantagame);
					State.Game();
					chantagame.resetTheWholeGame();
					repaint();
					printAll(getGraphics());
				}
				else if(KoochikPause)
				{
					pausepanel.stopTheTimer();
					getContentPane().removeAll();
					getContentPane().add(koochikgame);
					State.Game();
					koochikgame.initGame();
					koochikgame.restartTheWholeGame();
					repaint();
					printAll(getGraphics());
				}
				else if(tutorial_koochikPause)
				{
					pausepanel.stopTheTimer();
					getContentPane().removeAll();
					getContentPane().add(tutorialkoochikgame);
					State.Game();
					tutorialkoochikgame.initGame();
					tutorialkoochikgame.restartTheWholeGame();
					repaint();
					printAll(getGraphics());
				}
				else if(tutorial_chantaPause)
				{
					pausepanel.stopTheTimer();
					getContentPane().removeAll();
					getContentPane().add(tutorialchanta);
					State.Game();
					ShowMessage_ChantaGame.destroyJDialog();
					tutorialchanta.initGame();
					tutorialchanta.resetTheWholeGame();
					repaint();
					printAll(getGraphics());
				}
				else if(tutorial_classicPause)
				{
					pausepanel.stopTheTimer();
					getContentPane().removeAll();
					getContentPane().add(tutorialclassic);
					State.Game();
					ShowMessage_ChantaGame.destroyJDialog();
					tutorialclassic.initGame();
					tutorialclassic.resetTheWholeGame();
					repaint();
					printAll(getGraphics());
				}
			}
			
		});
	}
	//quit
	public void setActionListenerForQuitButton()
	{
		pausepanel.getQuit().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				pausepanel.stopTheTimer();
				getContentPane().removeAll();
				getContentPane().add(menu);
//				if(ClassicPause)
//				{
////					game.stopTheMusic();
//				}
//				else if(tutorial_classicPause)
//				{
//					tutorialclassic.stopTheMusic();
//				}
				State.Menu();
				menu.resetTheMenu();
				repaint();
				printAll(getGraphics());
			}
		});
	}
	
	//playmenu ActionListeners
	public void setActionListenerForClassicGameButton()
	{
		playmenu.getClassicGameButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
//				menu.ac.stop();
				getContentPane().removeAll();
				getContentPane().add(game);
				State.Game();
				game.resetTheWholeGame();
				repaint();
				printAll(getGraphics());
			}
		});
	}
	public void setActionListenerForTheChantaGameButton()
	{
		playmenu.getChantaGameButton().addActionListener(new ActionListener()
				{
						public void actionPerformed(ActionEvent e)
						{
//							menu.ac.stop();
							menu.stopTheTimer();
							getContentPane().removeAll();
							getContentPane().add(chantagame);
							State.Game();
							chantagame.resetTheWholeGame();
							repaint();
							printAll(getGraphics());
						}
				});
	}
	//changes made to make sure that the tutorial works
	public void setActionListenerForTheKoochikGameButton()
	{
		playmenu.getKoochickGameButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
//				menu.ac.stop();
				menu.stopTheTimer();
				getContentPane().removeAll();
				getContentPane().add(koochikgame);
				State.Game();
				koochikgame.restartTheWholeGame();
			
		        repaint();
				printAll(getGraphics());
			}
		});
	}
	
	
	public void setActionListenerForTheReturnButton()
	{
		this.playmenu.getReturnButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent r)
			{
//				menu.ac.stop();
				getContentPane().removeAll();
				getContentPane().add(menu);
				menu.resetTheMenu();
				repaint();
				printAll(getGraphics());
			}
		});
			
		
	}
	public void setActionListenerForTheKoochikTutorial()
	{
		this.playmenu.getKoochikTutorialButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
//				menu.ac.stop();
				menu.stopTheTimer();
				getContentPane().removeAll();
				getContentPane().add(tutorialkoochikgame);
				State.Game();
				tutorialkoochikgame.restartTheWholeGame();
				
		        repaint();
				printAll(getGraphics());
			}
		});
	}
	public void setActionListenerForTheChantaTutorial()
	{
		this.playmenu.getChantaTutorialButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
//				menu.ac.stop();
				menu.stopTheTimer();
				getContentPane().removeAll();
				getContentPane().add(tutorialchanta);
				State.Game();
				tutorialchanta.resetTheWholeGame();
				repaint();
				printAll(getGraphics());
			}
		});
	}
	public void setActionListenerForTheClassicTutorial()
	{
		this.playmenu.getClassicTutorialButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
//				menu.ac.stop();
				getContentPane().removeAll();
				getContentPane().add(tutorialclassic);
				State.Game();
				tutorialclassic.resetTheWholeGame();
				repaint();
				printAll(getGraphics());
			}
		});
	}
	public void setActionListenerForQuitTheGameButton()
	{
		menu.getQuitButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				j.setJDialogVisisble();;
			}
		});
		
		
	}
	public void setActionListenerForTheYesButton()
	{
		JDialog_Back.getYes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				EscapeMain.this.dispatchEvent(new WindowEvent(EscapeMain.this, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	public void setActionListenerForTheAboutButton()
	{
		menu.getAboutButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(about);
				about.startTheTimer();
				repaint();
				printAll(getGraphics());
			}
		});
	}
	public void setActionListenerForTheBackButton()
	{
		about.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(menu);
				menu.resetTheMenu();
				stopAllTheTimers();
				repaint();
				printAll(getGraphics());
			}
		});
	}
	public void stopAllTheTimers(){
		game.stopTheTimer();
		about.stopTheTimer();
		tutorialclassic.stopTheTimer();
		chantagame.stopTheTimer();
		koochikgame.stopTheTimer();
		tutorialkoochikgame.stopTheTimer();
		tutorialchanta.stopTheTimer();
	}
	
	public static void main(String[] args)
	{
		new EscapeMain();
		
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				EscapeMain s = new EscapeMain();
//				s.setVisible(true);
//			}
//		});
		
	}

}
