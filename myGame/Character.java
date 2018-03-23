package myGame;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Character extends Sprite{
	
	private int dx;
	private int dy;
	private int p2;
	private int count4;
	private int count3;          
	private int count2;         
	private int sodaCount;
	private boolean sodaConsumed;
	private boolean atLeftSide;
	private boolean atRightSide;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean pause;
	private boolean ableJump;
	private boolean curLeft;
	private boolean curRight;
	private boolean pressedOne;
	private boolean ableMove;

		public Character(int x, int y) throws IOException {
			
			super(x,y);
			initCharacter();
		}
		
		private void initCharacter() throws IOException {
		
			loadImage("RolliePollieRightBigger.png");
			getImageDimensions();
			count4 = 0;
			count3 = 0;         
			count2 = 0;        
			sodaCount = 99;
			sodaConsumed = false;
			atLeftSide = false;
			atRightSide = false;
			moveRight = false;
			moveLeft = false;
			pause = false;
			ableJump = true;
			pressedOne = false;
			ableMove = true;
			curLeft = false;
			curRight = true;
		}
		
		public void setSodaCount(int i){
			sodaCount += i;
		}
		
		public int getSodaCount(){
			return sodaCount;
		}
		
		public boolean getPause(){
			return pause;
		}
		public void setPause(boolean o){
			pause = o;
		}
		public void changeImage(int i) throws IOException{
			if(i == 1){
				loadImage("RolliePollieRightBigger.png");
				getImageDimensions();
			}
			else if(i == 2){
				loadImage("RolliePollieRightBigger2.png");
				getImageDimensions();
			}
			else if(i == 3){
				loadImage("RolliePollieLeftBigger.png");
				getImageDimensions();
			}
			else if(i == 4){
				loadImage("RolliePollieLeftBigger2.png");
				getImageDimensions();
			}
			
			else if(i == 5){
				loadImage("drinkingRight1.png");
				getImageDimensions();
			}
			else if(i == 6){
				loadImage("drinkingRight2.png");
				getImageDimensions();
			}
			else if(i == 7){
				loadImage("drinkingRight3.png");
				getImageDimensions();
			}
			else if(i == 8){
				loadImage("drinkingRight4.png");
				getImageDimensions();
			}
			else if(i == 9){
				loadImage("drinkingRight5.png");
				getImageDimensions();
			}
			else if(i == 10){
				loadImage("drinkingRight6.png");
				getImageDimensions();
			}
			else if(i == 11){
				loadImage("drinkingLeft1.png");
				getImageDimensions();
			}
			else if(i == 12){
				loadImage("drinkingLeft2.png");
				getImageDimensions();
			}
			else if(i == 13){
				loadImage("drinkingLeft3.png");
				getImageDimensions();
			}
			else if(i == 14){
				loadImage("drinkingLeft4.png");
				getImageDimensions();
			}
			else if(i == 15){
				loadImage("drinkingLeft5.png");
				getImageDimensions();
			}
			else if(i == 16){
				loadImage("drinkingLeft6.png");
				getImageDimensions();
			}
			
		}
		
		public int getp2(){
			Rectangle c = getBounds();
			p2 = (int)c.getX() + (int)c.getWidth();          // position of right side of character
			return p2;
			
		}
		
		public boolean getOnGround(){         // added to test
			 return contactGround;
		}
		
		public void setLeftSide(boolean o){
			atLeftSide = o;
		}
		
		public void setRightSide(boolean o){
			atRightSide = o;
		}
		
		public boolean getLeftSide(){
			return atLeftSide;
		}
		
		public boolean getRightSide(){
			return atRightSide;
		}
		
		public boolean getMoveRight(){
			
			return moveRight;
		}
		
		public boolean getMoveLeft(){
			
			return moveLeft;
		}
		
		public int getHeightReached(){
			return heightReached;
		}
		
		public void restrictMovement(boolean o){
			if(o){
				ableMove = false;
				ableJump = false;
			}
			else{
				ableMove = true;
				ableJump = true;
			}
			
		}
		
		public void move() throws IOException {
				if(dx == -1 & left == false & atLeftSide == false){
					x += dx;
					//System.out.println(contactGround);
					if(contactGround == true){
						walkAnimation(1);
					}
				}
				if(dx == 1 & right == false & atRightSide == false){
					x += dx;
					//System.out.println(contactGround);
					if(contactGround == true){
						walkAnimation(2);
					}
				}
			if (contactGround == false){
				y += dy;
			}
		}
		
		public void walkAnimation(int which) throws IOException{
			
			if(which == 1){
				count2++;
				if(count2 <= 12){
					changeImage(3);
				}
				else if(count2 <= 24){
					changeImage(4);
				}
				else{
					count2 = 0;
				}
			}
			else if(which == 2){
				count3++;
				if(count3 <= 12){
					changeImage(1);
				}
				else if(count3 <= 24){
					changeImage(2);
				}
				else{
					count3 = 0;
				}
			}
			
		}
		
		public void drinkSodaAnimation() throws IOException{
			restrictMovement(true);
			if(curRight == true){
				count4++;
				if(count4 <= 15){
					changeImage(5);
				}
				else if(count4 <= 30){
					changeImage(6);
				}
				else if(count4 <= 45){
					changeImage(7);
				}
				else if(count4 <= 60){
					changeImage(8);
				}
				else if(count4 <= 75){
					changeImage(9);
				}
				else if(count4 <= 100){
					changeImage(10);
				}
				else{
					count4 = 0;
					restrictMovement(false);
					pressedOne = false;
					changeImage(1);
				}
			}
			else if(curLeft == true){
				count4++;
				if(count4 <= 15){
					changeImage(11);
				}
				else if(count4 <= 30){
					changeImage(12);
				}
				else if(count4 <= 45){
					changeImage(13);
				}
				else if(count4 <= 60){
					changeImage(14);
				}
				else if(count4 <= 75){
					changeImage(15);
				}
				else if(count4 <= 100){
					changeImage(16);
				}
				else{
					count4 = 0;
					restrictMovement(false);
					pressedOne = false;
					changeImage(3);
				}
			}
			
		}
		
		public boolean pressedOne(){
			return pressedOne;
			
		}
		
		public void keyPressed(KeyEvent e) throws IOException{
			count2++;
			int key = e.getKeyCode();
			
			if (key == KeyEvent.VK_1 & ableMove == true) {
				if(sodaCount >= 1) {
					pressedOne = true;
					sodaConsumed = true;
					sodaCount -= 1;
				}
			}
			
			if (key == KeyEvent.VK_SPACE & contactGround == true & ableJump == true) {
				pressedJump = true;
				heightReached2 = 0;
				heightReached = 0;
				jump();
			}
			
			if (key == KeyEvent.VK_LEFT & ableMove == true) {
				dx = -1;
				moveLeft = true;
				curLeft = true;
				curRight = false;
			}
			
			
			if (key == KeyEvent.VK_RIGHT & ableMove == true) {
				dx = 1;
				moveRight = true;
				curRight = true;
			    curLeft = false;
			}
			
			if(key == KeyEvent.VK_ESCAPE){
				if(pause){
					pause = false;
				}
				else{
					pause = true;
				}
			}
			
		}
		
		public void keyReleased(KeyEvent e) throws IOException {
	        
	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT) {
	            dx = 0;
	            moveLeft = false;
	            changeImage(3);
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            dx = 0;
	            moveRight = false;
	            changeImage(1);
	        }
	        if (key == KeyEvent.VK_SPACE){
	        	if(sodaConsumed == false){
	        	pressedJump = false;
	        	}
	        }

	    }
		
		public void jump() {
			if(pressedJump == false){
				return;
			}
			if(sodaConsumed){
				if( heightReached2 < 192){
					y -= 4;
					heightReached2 += 4;

				}

				else if( heightReached2 < 250){     
					y -= 3;
					heightReached2 += 3;
				}
				
				else if( heightReached2 <= 280){
					y -= 2;
					heightReached2 += 2;
				}
				
				else{
					pressedJump = false;
					heightReached2 = 0;
					sodaConsumed = false;
				}
			}
			else{
				if( heightReached2 < 120){
					y -= 3;
					heightReached2 += 3;

				}

				else if( heightReached2 <= 164){      //change
					y -= 2;
					heightReached2 += 2;
				}
				else{
					pressedJump = false;
					heightReached2 = 0;            //change
				}
			}
		}
		
		public void stopSuperJump(){
			sodaConsumed = false;
		}
		
		public boolean checkSodaConsumed(){
			return sodaConsumed;
		}
}


