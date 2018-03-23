package myGame;

import java.awt.Rectangle;
import java.io.IOException;

public class Floor extends Sprite{

		public Floor(int x, int y) throws IOException {
			
			super(x,y);
			initFloor();
		}
		
		private void initFloor() throws IOException {
			loadImage("block.png");
			getImageDimensions();
			Rectangle r2 = getBounds();

			
		}

}
