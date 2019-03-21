import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;	
import java.util.ArrayList;
import java.util.Random;

public class Draw extends Canvas implements Runnable{

	private static MyFrame frame;
	protected Controls control;
	private BufferedImage image;
	private BufferedImage backgroundImage;

	private Thread thread;
	private boolean running = false;

	public Player player;
	public Camera cam;
	public Random rand = new Random();

	public URL resource = getClass().getResource("run00.png");
	public ArrayList<Block> list = new ArrayList<Block>();


	public int x = -9;
	public int y = 125;
	public int height = 0;
	public int width = 0;

	public int state = 0;

	public Draw(){


		try{
			backgroundImage = ImageIO.read(getClass().getResource("bg23.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = backgroundImage.getHeight();
		width = backgroundImage.getWidth();

		init();

	}

	private void init(){

		player = new Player(40, 130);
		cam = new Camera(0, 0);

		control = new Controls(player);
		addKeyListener(control);

		for(int i = 0; i <=20; i++){
			list.add(new Block(rand.nextInt(backgroundImage.getWidth()*10), backgroundImage.getHeight() - 100, 25, 25));
		}
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
		player.tick();
		cam.tick(player);
	}

	public void render(){

		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null){
			this.createBufferStrategy(3);
			return;
		}

			Graphics g = bufferStrategy.getDrawGraphics();


			//////////////////////////////////////////
			g.translate(cam.getX(), cam.getY());
			for(int i = 0; i < backgroundImage.getWidth()*10; i+=backgroundImage.getWidth()){
				g.drawImage(backgroundImage, i, 0, null);
			}
			for(int i = 0; i < list.size()- 1; i++){
				list.get(i).render(g);
			}

			g.drawImage(image, x, y, null);


			g.translate(-cam.getX(), -cam.getY());
			//////////////////////////////////////////

				g.drawImage(player.image1, player.x, player.y, null);


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

	public static void main(String args[]){
		frame = new MyFrame(new Draw(), 800, 261);

	}
}	