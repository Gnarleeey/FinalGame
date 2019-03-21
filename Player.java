import javax.swing.JComponent;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;

public class Player{

	public BufferedImage image1;
	public URL resource = getClass().getResource("run00.png");

	public int x, y;

	public int width, height;

	Draw draw;

	public Player(int x, int y){

		this.x = x;
		this.y = y;	

		try{
			image1 = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();	
		}

		width = image1.getWidth();
		height = image1.getHeight();
	}

	public void tick(){

		if(y <= 130){
			y++;
		}
	}

	public void render(Graphics g){

		g.drawImage(image1, x, y, null);
	}

	public Rectangle getBounds(){
		return(new Rectangle(x, y, width, height));
	}
}