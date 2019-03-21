

public class Camera{
	
	public int x, y;

	public Player player;


	public Camera(int x, int y){

		this.x = x;
		this.y = y;

	}

	public void tick(Player player){
		this.player = player;

		x--;

	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

}