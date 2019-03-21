import javax.swing.JComponent;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Block{
		
	protected int x, y;
	protected int width, height;
	protected BufferedImage image;
	protected URL source = getClass().getResource("cactus1.png");


	public Block(int x, int y, int width, int height){

		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		try{

			image = ImageIO.read(source);
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

		g.drawImage(image, x, y, 150, 50, null);
		g.setColor(Color.RED);
		g2d.draw(getBounds());


	}

	public void tick(){


	}

	public Rectangle getBounds(){

		return(new Rectangle(x, y, 150, 50));
	}


}