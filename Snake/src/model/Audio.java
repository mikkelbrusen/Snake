package model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Audio{
	Clip music;
	Clip clip1;
	Clip clip2;
	Clip clip3;
	Clip clip4;
	AudioInputStream repeat;
	AudioInputStream apple;
	AudioInputStream gameOver;
	AudioInputStream startGame;
	AudioInputStream bonus;
	
	public Audio(){
		try{
		this.repeat = AudioSystem.getAudioInputStream(new File ("repeat.wav"));
		this.apple = AudioSystem.getAudioInputStream(new File ("aevle.wav"));
		this.bonus = AudioSystem.getAudioInputStream(new File ("bonus.wav"));
		this.gameOver = AudioSystem.getAudioInputStream(new File ("end_game.wav"));
		this.startGame = AudioSystem.getAudioInputStream(new File ("start_game.wav"));
		music = AudioSystem.getClip();
		music.open(repeat);
		clip1 = AudioSystem.getClip();
		clip2 = AudioSystem.getClip();
		clip3 = AudioSystem.getClip();
		clip4 = AudioSystem.getClip();
		clip1.open(apple);
		clip2.open(bonus);
		clip3.open(gameOver);
		clip4.open(startGame);
		
		}
		catch(Exception ex){
			
		}
		
	}
	

	
	public void playSound(int sound){
	try {
		switch (sound){
		case 1:
			clip1.setFramePosition(0);
			clip1.start();
			break;
		case 2:
			clip2.setFramePosition(0);
			clip2.start();
			break;
		case 3:
			clip3.setFramePosition(0);
			clip3.start();
			break;
		case 4:
			clip4.setFramePosition(0);
			clip4.start();
		}
		
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	}
	
        public void stopAll(){
            clip1.stop();
            clip2.stop();
            clip3.stop();
            clip4.stop();
        }
        
	public void startMusic(){
		music.loop(Clip.LOOP_CONTINUOUSLY);
		music.start();
	}
	
	public void stopMusic(){
		music.stop();
	}

}

	 
	 