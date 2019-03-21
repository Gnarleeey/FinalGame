import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;

public class MyFrame extends JFrame{

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

		System.out.println("Talon talon");
		draw.start();

	}


}