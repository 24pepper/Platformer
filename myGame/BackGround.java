package myGame;

import java.io.IOException;

public class BackGround extends Sprite{

	public BackGround(int x, int y, int which) throws IOException{

		super(x,y);
		initBackground(which);
	}

	private void initBackground(int which) throws IOException{
		
		if(which == 1){
			loadImage("lvl1BackGround3.png");
			getImageDimensions();
		}
	}
}
