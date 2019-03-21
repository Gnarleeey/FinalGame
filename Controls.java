import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Controls extends KeyAdapter{
	
	public Player player;

	public Controls(Player player){

		this.player = player;
	}

	public void keyPressed(KeyEvent e){

		if(e.getKeyCode() == KeyEvent.VK_SPACE && player.y >= 130){

			player.y-=60;
		}
	}

	public void keyReleased(KeyEvent e){

			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				player.y--;

				if(player.y == 0){
					player.y = 0;
				}
		}
	}

	public void keyTyped(KeyEvent e){
		
	}

}