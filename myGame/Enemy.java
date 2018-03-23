package myGame;

import java.io.IOException;

public class Enemy extends Sprite{
	
	private int count;
	private int nummy;
	

	public Enemy(int x, int y, int num) throws IOException {
		
		super(x,y);
		nummy = num;
		initEnemy();
	}
	
	private void initEnemy() throws IOException{
		loadImage("pacman.png");
		getImageDimensions();
		count = 0;
	}
	
	public int getNummy(){
		return nummy;
	}
	
	public void incrementEnemyX(int which){
		if(count == 3 & which == 1 & right == false){
			x++;
			count = 0;
		}
		else if(count == 3 & which == -1 & left == false){
			x--;
			count = 0;
		} 
		else{
			if(count == 3){
				count = 0;
			}
		}
		count++;	
	}
	
	public void jump() {
		if(pressedJump == false){
			return;
		}
		System.out.println("still jumping");
		if( heightReached2 < 60){
			y -= 3;
			heightReached2 += 3;

		}

		else if( heightReached2 <= 100){    
			y -= 2;
			heightReached2 += 2;
		}
		else{
			pressedJump = false;
			heightReached2 = 0;            
		}
	}

}
