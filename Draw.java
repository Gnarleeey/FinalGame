import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;	

public class Draw extends Canvas implements Runnable{

	private static MyFrame frame;
	private BufferedImage image;
	private BufferedImage backgroundImage;

	private Thread thread;
	private boolean running = false;


	public URL resource = getClass().getResource("run00.png");

	// circle's position
	public int x = -9;
	public int y = 125;
	public int height = 0;
	public int width = 0;

	// animation states
	public int state = 0;

	// randomizer
	public Random randomizer;

	// enemy
	public int enemyCount;
	Monster[] monsters = new Monster[10];

	public Draw(){

		randomizer = new Random();

		try{
			backgroundImage = ImageIO.read(getClass().getResource("bg23.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = backgroundImage.getHeight();
		width = backgroundImage.getWidth();

	}

	

	
	public synchronized void start(){

		if(!running){
			running = true;
		}

		try{
			thread = new Thread(this);
			thread.start();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){

		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

			while (running){ 
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;

			while(delta >= 1){
				tick();
				delta --;
			}
			if(running)
				render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println ("FPS: " + frames);
				frames = 0;
			}
		}
	}
	private void tick(){

	}

	public void render(){

		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null){
			this.createBufferStrategy(3);
			return;
		}

			Graphics g = bufferStrategy.getDrawGraphics();

			g.drawImage(backgroundImage, 0, 0, null);
			g.drawImage(image, x, y, null);
		
		for(int c = 0; c < monsters.length; c++){		
			if(monsters[c]!=null){
				g.drawImage(monsters[c].image, monsters[c].xPos, monsters[c].yPos, this);
				g.setColor(Color.GREEN);
				g.fillRect(monsters[c].xPos+7, monsters[c].yPos, monsters[c].life, 2);
			}	
		}
		bufferStrategy.show();
		g.dispose();
	}
	
	public void reloadImage(){
		state++;

		if(state == 0){
			resource = getClass().getResource("run00.png");
		}
		else if(state == 1){
			resource = getClass().getResource("run01.png");
		}
		else if(state == 2){
			resource = getClass().getResource("run02.png");
		}
		else if(state == 3){
			resource = getClass().getResource("run03.png");
		}
		else if(state == 4){
			resource = getClass().getResource("run04.png");
		}
		else if(state == 5){
			resource = getClass().getResource("run05.png");
			state = 0;
		}
	}

	public void jumpAnimation(){
		Thread talon = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("run00.png");
						}
						else{
							resource = getClass().getResource("talon"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		talon.start();
	}

	public void talon(){
		y = y - 20;
		jumpAnimation();
		reloadImage();
		repaint();
	}

	public void moveUp(){
		y = y - 5;
		reloadImage();
		repaint();

	}

	public void moveDown(){
		y = y + 5;
		reloadImage();
		repaint();

	}

	public void moveLeft(){
		x = x - 5;
		reloadImage();
		repaint();

	}

	public void moveRight(){
		x = x + 5;
		reloadImage();
		repaint();

	}



	public static void main(String args[]){
		frame = new MyFrame(new Draw(), 800, 261);
	}
}	