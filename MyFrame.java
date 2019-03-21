import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;

public class MyFrame extends JFrame implements KeyListener{

	Draw draw;

	private int width;
	private int height;

	public MyFrame(Draw draw, int width, int height){

		this.draw = draw;
		this.width = width;
		this.height = height;

		init();

		
	}

	private void init(){


		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().add(draw);

		addKeyListener(this);
		System.out.println("Talon talon");
		draw.start();

	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			draw.moveUp();
			System.out.println("pos: " + draw.x + ", " + draw.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			draw.moveRight();
			System.out.println("pos: " + draw.x + ", " + draw.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			draw.moveDown();
			System.out.println("pos: " + draw.x + ", " + draw.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			draw.moveLeft();
			System.out.println("pos: " + draw.x + ", " + draw.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			draw.talon();
			System.out.println("pos: " + draw.x + "," + draw.y);
		}
	}

	public void keyReleased(KeyEvent e){

	}

	public void keyTyped(KeyEvent e){
		
	}
}