package view;

import model.Field;
import model.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class MainPanel extends JPanel {

	private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final static int width = (int) screenSize.getWidth();
	private static final BufferedImage[] THEME_OBAMA = new BufferedImage[10];
	private static final BufferedImage[] THEME_SNAKE = new BufferedImage[10];
	private static int scale;
	private final Model model;
	private final Dimension size;
	private final JLabel paused;
	
	public MainPanel(Dimension size, Model model) {
		super();
		this.size = size;
		scale = width / size.width;
		this.setLayout(new BorderLayout(size.width*scale,size.height*scale));
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(size.width*scale,size.height*scale));
		this.setOpaque(true);
		this.model = model;
		
		paused = new JLabel("PAUSED");
		paused.setAlignmentX(CENTER_ALIGNMENT);
		paused.setFont(new Font("Comic Sans MS", Font.BOLD, 100));
		paused.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		paused.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(paused);
		paused.setVisible(false);
		
		
		// Load Obama Theme
		THEME_OBAMA[0] = loadImage("Sand.png");
		THEME_OBAMA[1] = loadImage("Brick.png");
		THEME_OBAMA[2] = loadImage("Bomb.png");
		THEME_OBAMA[3] = loadImage("NLeadTerrorist.png");
		THEME_OBAMA[4] = loadImage("SLeadTerrorist.png");
		THEME_OBAMA[5] = loadImage("ELeadTerrorist.png");
		THEME_OBAMA[6] = loadImage("WLeadTerrorist.png");
		THEME_OBAMA[7] = loadImage("BodyTerrorist.png");
		THEME_OBAMA[8] = loadImage("Obama.png");
		THEME_OBAMA[9] = loadImage("Wormhole.png");
		
		// Load Snake Theme
		THEME_SNAKE[0] = loadImage("Sand.png");
		THEME_SNAKE[1] = loadImage("Brick.png");
		THEME_SNAKE[2] = loadImage("Apple.png");
		THEME_SNAKE[3] = loadImage("NHead.png");
		THEME_SNAKE[4] = loadImage("SHead.png");
		THEME_SNAKE[5] = loadImage("EHead.png");
		THEME_SNAKE[6] = loadImage("WHead.png");
		THEME_SNAKE[7] = loadImage("Body.png");
		THEME_SNAKE[8] = loadImage("Body.png");
		THEME_SNAKE[9] = loadImage("Wormhole.png");
		
		
	}
	
	public Dimension getSize(){
		return this.size;
	}
	
	public void showPause(boolean b){
		paused.setVisible(b);
	}

	@Override
	protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
          RenderingHints.VALUE_RENDER_QUALITY);
                super.paintComponent(g);
		// depending on the theme, paint it.
		if (model.getTheme() == 2) {
			paintFields(g2,THEME_SNAKE);
		} else {
			paintFields(g2,THEME_OBAMA);
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
	private void paintFields(Graphics2D g, BufferedImage[] bi) {
		Field[][] gameField = model.getGameField();
		
		// paint fields
		for(int i = 0; i < size.width; i++) {
			for(int j = 0; j < size.height; j++) {
				//draw sand
				g.drawImage(bi[0], i*scale, j*scale, scale, scale, null);
				
				switch(gameField[i][j].getType()) {
					case WALL: 
						g.drawImage(bi[1], i*scale, j*scale, scale, scale, null);
						break;
					case SNAKE:
						g.drawImage(bi[7], i*scale, j*scale, scale, scale, null);
						break;
					case APPLE: 
						g.drawImage(bi[2], i*scale, j*scale, scale, scale, null);
						break;
					case HEAD:
						switch(model.getSnakeDirection()){
			                case 'N':
			                	g.drawImage(bi[3], i*scale, j*scale, scale, scale, null);
			                    break;
			                case 'S':
			                	g.drawImage(bi[4], i*scale, j*scale, scale, scale, null);
			                    break;
			                case 'E':
			                	g.drawImage(bi[5], i*scale, j*scale, scale, scale, null);
			                    break;
			                case 'W':
			                	g.drawImage(bi[6], i*scale, j*scale, scale, scale, null);
			                    break;
						}
						break;
					case TAIL:
						g.drawImage(bi[8], i*scale, j*scale, scale, scale, null);
						break;
					case WORMHOLE:
						g.drawImage(bi[9], i*scale, j*scale, null);
						break;
				}
			} // end j
		} // end i
	}
	
	

}

