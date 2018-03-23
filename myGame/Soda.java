package myGame;

import java.io.IOException;

public class Soda extends Sprite {

	public Soda(int x, int y) throws IOException{
		
		super(x,y);
		initSoda();
	}
	
	private void initSoda() throws IOException{
		
		loadImage("SodaCan.png");
		getImageDimensions();
	}

}
