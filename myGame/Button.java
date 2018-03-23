package myGame;

import java.io.IOException;

public class Button extends Sprite {

	public Button(int x, int y, int which) throws IOException{

		super(x,y);
		initButton(which);
	}

	private void initButton(int which) throws IOException{

		if(which == 1){
			
		loadImage("Start_Button.png");
		getImageDimensions();
		}
		else if(which == 2){
			
			loadImage("Start Button 2.png");
			getImageDimensions();
		}
		else if(which == 3){
			loadImage("Main Menu.png");
			getImageDimensions();
		}
		else if(which == 4){
			loadImage("Main Menu2.png");
			getImageDimensions();
		}
		else if(which == 5){
			loadImage("Restart.png");
			getImageDimensions();
		}
		else if(which == 6){
			loadImage("Restart2.png");
			getImageDimensions();
		}
		else if(which == 7){
			loadImage("Return.png");
			getImageDimensions();
		}
		else if(which == 8){
			loadImage("Return2.png");
			getImageDimensions();
		}
	}

}
