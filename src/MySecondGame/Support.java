package MySecondGame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Support {
	//\\Desktop\\FILES\\YUPNOPE_ESCAPEGAME
	public static String KoochikGame = "\\Desktop\\koochikgame";
	public static String ChantaGame = "\\Desktop\\chantagame";
	public static String Classic = "\\Desktop\\classicgame";
	
	static String emptyStr = "";
	static String CRLFStr = "\n";
	static String parDelim = "\\|";
	static String blockDelim = "~";
	
	public static final int DELAY = 10;
	public static Random rand = new Random();
	public static final int aSecond = 1000;
	public static final int PowerUpsInGame = 3+1;
	public static final String myName = "Moh Yaghoub";
	
	public static void addKeyBinding(JComponent comp,int keycode,String id, ActionListener lam,boolean key){
		InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = comp.getActionMap();
		
		im.put(KeyStroke.getKeyStroke(keycode,0,key), id);
		am.put(id, new AbstractAction(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				lam.actionPerformed(e);
			}
			
		});
	}
	static String docsLoad(String filename) {
		Path filePath = Paths.get(System.getProperty("user.home") + filename);
		
		try {
			byte bytes[] = Files.readAllBytes(filePath);
			return new String(bytes);
		} catch (IOException e) {
			return "";
		}
	}
	
	static boolean docsExist(String filename) {
		Path filePath = Paths.get(System.getProperty("user.home") + filename);
		
		return Files.isRegularFile(filePath) & Files.isReadable(filePath);
	}

	static boolean docsSave(String filename, String str) {
		Path filePath = Paths.get(System.getProperty("user.home") + filename);
		OpenOption oo[] = {StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
		try {
			Files.write(filePath, str.getBytes(), oo);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	public static Image getImage1(String path) 
	{
		 BufferedImage image = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		 ClassLoader classLoader =  
		InputStream is = classLoader.getResourceAsStream(path);
		try {
			image = ImageIO.read(is);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			
		}
		return image;
	}

	public static File getFile(String s)
	{
		File f = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		f = new File(classLoader.getResource(s).getFile());
		return f;
	}
//	 public static Image getImage(String path){
//			BufferedImage finImage = null;
//			try{
//				finImage = ImageIO.read(new File(path));
//				
//			}catch(IOException e){
//				System.out.print("An error occured"+e.getMessage());
//			}
//			return finImage;
//		}
	 public static int giveMeARandomX()
	 {
		 return rand.nextInt(Size.Width-65);
	 }
	 public static int giveMeARandomY()
	 {
		 return rand.nextInt(Size.Height-55);
	 }
	 public static int giveMeARandomPower()
	 {
		 return rand.nextInt(PowerUpsInGame);
	 }
	 public static int giveMeARandomNumberUpTo100()
	 {
		 return rand.nextInt(100);
	 }
	 public static int giveMeARandomNumberUpTo10()
	 {
		 return rand.nextInt(10);
	 }
	 public static boolean giveMeARandomTOF()
	 {
		 int i = rand.nextInt(2);
		 if(i==0)
		 {
			 return true;
		 }
		 return false;
	 }
	 public static int giveMeARandomNumberUpTo25()
	 {
		 return rand.nextInt(25);
	 }
	 public static int giveMeARandomNumberUpTo5()
	 {
		 return rand.nextInt(5);
	 }
	 public static int giveMeARandomNumberUpTo7()
	 {
		 return rand.nextInt(7);
	 }
	 public static int giveMeARandomSizeUpTo100()
	 {
		 return rand.nextInt(100)+50;
	 }
	 public static int giveMeARandomNumberUpTo20()
	 {
		 return rand.nextInt(20);
	 }
	 public static int giveMeARandomNumberUpTo12()
	 {
		 return rand.nextInt(12);
	 }
	 public static Color giveMeARandomColor()
	 {
		 return Menu.RANDOMCOLORLIST[Support.giveMeARandomNumberUpTo12()];
	 }
}
