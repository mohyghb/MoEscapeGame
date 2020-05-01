package MySecondGame;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioCreator   {
	public boolean play;
	public Clip clip;
	AudioCreator()
	{
		this.play = false;
	}
	 public void PlayAudio(String s) 
		{
		    try
		    {
		    	
		    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		    	InputStream inputStream = classLoader.getResourceAsStream(s);
		    	try {
		    	  Clip clip = AudioSystem.getClip();
		    	  AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
		    	  clip.open(ais);
		    	  clip.start();
		    	} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
		    	  System.err.println("ERROR: Playing sound has failed");
		    	  //e.printStackTrace();
		    	}
		    	//The code does work in eclipse but not in the exported jar file
//		    	 AudioInputStream ais = AudioSystem.getAudioInputStream(file1);
//	            clip = AudioSystem.getClip();
//		            clip.open(ais);
//		            clip.start();
		           
//		        clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
//		        clip.open(AudioSystem.getAudioInputStream(file1));
//		        clip.start();
		    }
		    catch (Exception exc)
	    {
//		        exc.printStackTrace(System.out);
		    }
		}
	public void stop()
	{
		this.clip.stop();
	}
	public void start()
	{
		this.clip.start();
	}
}
