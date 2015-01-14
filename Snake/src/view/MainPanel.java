package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Field;
import model.Model;
import model.Objects;

public class MainPanel extends JPanel {
	

	

	final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final static int width = (int) screenSize.getWidth();
	final static int height = (int) screenSize.getHeight();
	public static final int res[] = new int[] {width, height};

	public static int SCALE;
	
	static BufferedImage[] THEME_OBAMA = new BufferedImage[10];
	static BufferedImage[] THEME_SNAKE = new BufferedImage[10];
	
	private final Model model;
	private final Dimension size;
	
	public MainPanel(Dimension size, Model model) {
		super();
		this.size = size;
		this.SCALE = res[0]/size.width;
		this.setLayout(new GridLayout(size.width*SCALE,size.height*SCALE));
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(size.width*SCALE,size.height*SCALE));
		this.setOpaque(true);
		this.model = model;
		
		// Load Obama Theme
		this.THEME_OBAMA[0] = loadImage("Sand.png");
		this.THEME_OBAMA[1] = loadImage("Brick.png");
		this.THEME_OBAMA[2] = loadImage("Bomb.png");
		this.THEME_OBAMA[3] = loadImage("NLeadTerrorist.png");
		this.THEME_OBAMA[4] = loadImage("SLeadTerrorist.png");
		this.THEME_OBAMA[5] = loadImage("ELeadTerrorist.png");
		this.THEME_OBAMA[6] = loadImage("WLeadTerrorist.png");
		this.THEME_OBAMA[7] = loadImage("BodyTerrorist.png");
		this.THEME_OBAMA[8] = loadImage("Obama.png");
		this.THEME_OBAMA[9] = loadImage("Wormhole.png");
		
		// Load Snake Theme
		this.THEME_SNAKE[0] = loadImage("Sand.png");
		this.THEME_SNAKE[1] = loadImage("Brick.png");
		this.THEME_SNAKE[2] = loadImage("Apple.png");
		this.THEME_SNAKE[3] = loadImage("NHead.png");
		this.THEME_SNAKE[4] = loadImage("SHead.png");
		this.THEME_SNAKE[5] = loadImage("EHead.png");
		this.THEME_SNAKE[6] = loadImage("WHead.png");
		this.THEME_SNAKE[7] = loadImage("Body.png");
		this.THEME_SNAKE[8] = loadImage("Body.png");
		this.THEME_SNAKE[9]	= loadImage("Wormhole.png");
		
		
	}
	
	public Dimension getSize(){
		return this.size;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// depending on the theme, paint it.
		if (model.getTheme() == 1) {
			paintFields(g,THEME_SNAKE);
		} else {
			paintFields(g,THEME_OBAMA);
		}
	}
	
	/** Method with a try/catch to load image*/
	private BufferedImage loadImage(String s) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(s));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** Takes a theme bi and draws images depending on the selected theme. */
	@SuppressWarnings("incomplete-switch")
	private void paintFields(Graphics g, BufferedImage[] bi) {
		Field[][] gameField = model.getGameField();
		
		// paint fields
		for(int i = 0; i < size.width; i++) {
			for(int j = 0; j < size.height; j++) {
				//draw sand
				g.drawImage(bi[0], i*SCALE, j*SCALE, SCALE, SCALE, null);
				
				switch(gameField[i][j].getType()) {
					case WALL: 
						g.drawImage(bi[1], i*SCALE, j*SCALE, SCALE, SCALE, null);
						break;
					case SNAKE:
						g.drawImage(bi[7], i*SCALE, j*SCALE, SCALE, SCALE, null);
						break;
					case APPLE: 
						g.drawImage(bi[2], i*SCALE, j*SCALE, SCALE, SCALE, null);
						break;
					case HEAD:
						switch(model.getSnakeDirection()){
			                case 'N':
			                	g.drawImage(bi[3], i*SCALE, j*SCALE, SCALE, SCALE, null);
			                    break;
			                case 'S':
			                	g.drawImage(bi[4], i*SCALE, j*SCALE, SCALE, SCALE, null);
			                    break;
			                case 'E':
			                	g.drawImage(bi[5], i*SCALE, j*SCALE, SCALE, SCALE, null);
			                    break;
			                case 'W':
			                	g.drawImage(bi[6], i*SCALE, j*SCALE, SCALE, SCALE, null);
			                    break;
						}
						break;
					case TAIL:
						g.drawImage(bi[8], i*SCALE, j*SCALE, SCALE, SCALE, null);
						break;
					case WORMHOLE:
						g.drawImage(bi[9], i*SCALE, j*SCALE, null);
						break;
				}
			} // end j
		} // end i
	}
	
	

}
