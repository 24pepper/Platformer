package myGame;

import java.awt.Image;


import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

public class Sprite {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean vis;
	protected Image image;
	protected int curpos1;
	protected int prepos1;
	protected int preprepos1;
	protected int prepos2;
	protected int preprepos2;
	protected int curpos2;
	protected int p1;
	protected boolean contactGround;
	protected boolean touched;
	protected int heightReached;
	protected boolean left;
	protected boolean right;
	protected boolean pressedJump;
	protected int heightReached2;
	

	public Sprite(int x, int y) {
		
		this.x = x;
		this.y = y;
		vis = true;
		curpos1 = x;
		prepos1 = 0;
		preprepos1 = 0;
		curpos2 = x;
		prepos2 = 0;
		preprepos2 = 0;
		contactGround = false;
		touched = false;
		heightReached = 0;
		left = false;
		right = false;
		pressedJump = false;
		heightReached2 = 0;
	}
	
	protected void loadImage(String imageName) throws IOException {
		
		//ImageIcon ii = new ImageIcon(imageName);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(imageName);
	    image = ImageIO.read(input);
	}
	
	protected void getImageDimensions() {
		
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	protected Image getImage() {
		return image;
	}
	
	public int getX() {
        return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth(){
		
		return width;
	}
	
	public int getHeight(){
		
		return height;
	}
	
	public void incrementX(int howMuch){
		x += howMuch;
	}
	
	public boolean isVisible() {
		return vis;
	}
	
	public void setVisible(Boolean visible) {
		vis = visible;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, width,height);
	}
	
	public boolean onEdge(int posi, int posi2, boolean enemy) {
		preprepos1 = prepos1;
		prepos1 = curpos1;
		curpos1 = posi;
		preprepos2 = prepos2;
		prepos2 = curpos2;
		curpos2 = posi2;
		if(enemy){
		System.out.println("begin x");
		System.out.println(posi2 + " posi2");
		System.out.println(prepos2 + " prepos2");
		System.out.println(preprepos2 + " preprepos2");
		System.out.println("begin y");
		System.out.println(posi + " posi");
		System.out.println(prepos1 + " prepos");
		System.out.println(preprepos1 + " preprepos");
		}
		
		if(posi == prepos1 &  posi == preprepos1 & (posi2 - prepos2) >= -3 & (posi2 - prepos2) <= 3 & (posi2 - preprepos2) >= -3 & (posi2 - preprepos2) <= 3){
			if(enemy){
			System.out.println("return first true");
			}
			return true;
		}
//		else if(posi == prepos1 &  posi == preprepos1 & posi2 == prepos2 & posi2 == preprepos2){
//			if(enemy){
//			System.out.println("return second true");
//			}
//			return true;
//		}
		else{
			if(enemy){
			System.out.println("return false");
			}
			return false;
		}
	}
	
	public int getp1(){
		Rectangle c = getBounds();
		p1 = (int)c.getY() + (int)c.getHeight();          // the position of the bottom of the character
		return p1;
	}
	
	public void onGround(boolean o){
		
		contactGround = o;
	}
	
	public boolean checkOnGround(){
		return contactGround;
	}
	
	public void touched(boolean p){
		touched = p;
	}
	
	public boolean getTouched(){
		return touched;
	}
	
	public void resetHeightReached(){
		heightReached = 0;
		//heightReached2 = 0;
	}
	
	public void changeY(int down){
		if(heightReached < 100){
			y += down;
			heightReached += down;
		}
		else if(heightReached < 150){
			y += (down + 1);
			heightReached += (down + 1);
		}
		else{
			y += (down + 2);
		}
	}
	
	public void setLeft(boolean o) {
		left = o;
	}
	
	public void setRight(boolean o) {
		right = o;
	}
	
	public boolean checkJump(){
		return pressedJump;
	}
	
	public void stopJumping(){
		pressedJump = false;
	}
	
    public void setJump(){
		
		pressedJump = true;
	}
    
    public boolean getLeft(){
    	return left;
    }
	
}