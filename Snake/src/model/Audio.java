package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


class Audio {
	private Clip music;
	private Clip clip1;
	private Clip clip2;
	private Clip clip3;
	private Clip clip4;

	public Audio(){
		try{

			AudioInputStream repeat = AudioSystem.getAudioInputStream(new File("src/audio/repeat.wav"));
			AudioInputStream apple = AudioSystem.getAudioInputStream(new File("src/audio/aevle.wav"));
			AudioInputStream bonus = AudioSystem.getAudioInputStream(new File("src/audio/bonus.wav"));
			AudioInputStream gameOver = AudioSystem.getAudioInputStream(new File("src/audio/end_game.wav"));
			AudioInputStream startGame = AudioSystem.getAudioInputStream(new File("src/audio/start_game.wav"));
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
			ex.printStackTrace();
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

	 
	 