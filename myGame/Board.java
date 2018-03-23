package myGame;

import java.awt.*;


import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;


public class Board extends JPanel implements ActionListener{
	
	private final int c_X = 60;                            // initial x location for character
	private final int c_Y = 90;
	private Character character; 
	private Enemy enemy;
	private Soda soda;
	private Timer timer;
	private final int DELAY = 5;
	private final int B_WIDTH = 1000;                        // border width size of window
	private final int B_HEIGHT = 700;
	private ArrayList<Floor> floorTiles;                    // all floor pieces kept here
	private ArrayList<Soda> sodaCans;
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> tempEnemies;
	private Image startButton;
	private Image load;
	private Image startButton2;
	private Image mainMenu;
	private Image mainMenu2;
	private Image restart;
	private Image restart2;
	private Image returny;
	private Image returny2;
	private Image backGround1;
	int width;
	int height;
	int nummy;
	private boolean dontScroll;
	private boolean hover;
	private boolean hover2;
	private boolean hover3;
	private boolean pause;
	private boolean drew;
	private boolean enemyFell;
	private Button startButtoninfo;
	private Button startButton2info;
	private Button mainMenuinfo;
	private Button mainMenu2info;
	private Button restartinfo;
	private Button restart2info;
	private Button returninfo;
	private Button returninfo2;
	private BackGround backGround1info;
	private int nummy2;
	
	private final int[][] floorPos ={                      // array of positions of floor pieces  
			
			{0, 500}, {40, 500}, {80, 500},
	        {120, 500}, {160, 500}, {200, 500},
	        {160, 500}, {200, 500}, {240, 500},	
	        {160, 410}, {300, 500}, {270, 430},
	        {270, 390}, {270, 350}, {270, 310},
	        {340, 500}, {380, 500}, {420, 500},
	        {460, 500}, {500, 500}, {540, 500},
	        {580, 500}, {620, 500}, {660, 500},
	        {700, 500}, {740, 500}, {780, 500},
	        {820, 500}, {860, 500}, {900, 500},
	        {940, 500}, {980, 500}, {1020, 500},
	        {1060, 500}, {1200, 500}, {1240, 500},
	        {660, 460}, {700, 460}, {740, 460},
	        {1280, 500}, {1320, 500}, {1360, 500},
	        {1400, 500}, {1440, 500}, {1480, 500},
	        {1480, 400}, {1520, 400}, {1560, 400},
	        {1520, 500}, {1560, 500}, {1600, 500},
	        {1640, 500}, {1680, 500}, {1720, 500},
	        {1760, 500}, {1800, 500}, {1800, 460},
	        {1840, 500}, {1840, 460}, {1880, 460},
	        {1880, 500}, {1920, 500}, {1960, 500},
	        {2000, 500}, {2040, 500}, {2080, 500},
	        {2120, 500}, {2280, 500}, {2320, 500},
	        {2360, 500}, {2400, 460}, {2440, 460},
	        {2480, 460}, {2520, 420}, {2560, 420},
	        {2600, 420}, {2640, 380}, {2680, 380},
	        {2720, 380}, {2760, 340}, {2800, 340},
	        {2840, 340}, {2880, 300}, {2920, 300},
	        {2960, 300}, {3000, 340}, {3040, 340},
	        {3080, 340}, {3120, 380}, {3160, 380},
	        {3200, 380}, {3240, 420}, {3280, 420},
	        {3320, 420}, {3360, 460}, {3400, 460},
	        {3440, 460}, {3480, 535}, {3520, 535},
	        {3560, 535}, {3440, 300}, {3480, 300},
	        {3520, 300}, {3600, 220}, {3640, 220},
	        {3680, 220}, {3600, 535}, {3640, 535},
	        {3440, 500}, {3720, 220}, {3760, 220},
	        {3800, 220}, {3680, 500}, {3680, 460},
	        {3720, 460}, {3760, 460}, {3760, 500},
	        {3800, 535},
	        
	        
	        
		};
	
	private final int[][] sodaPos = {                       // array of positions of soda cans
			
			{100,460}, {240,430}, {800, 430},
			{3640, 480},
			
	};
	
	private final int[][] enemyPos = {                         //array of starting positions of enemies
			
		    {540, 100}, {1400, 400}, 
			{2000, 400}, {3520, 440},
			{2800, 280}
	};
	
	
	/* initializes a few variables and creates key, mousemotion, and mouse listener. Also initializes preferences for the board.
	 * Than calls title.
	 */
	public Board() throws IOException {                         
		
		nummy = 1;
		nummy2 = 1;
		drew = false;
		enemyFell = true;
		addKeyListener(new TAdapter());
		addMouseMotionListener(new listener());
		addMouseListener(new listener());
		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		

		title(); 
		
	}
	
	/* Sets up the title screen. Creates the start button by creating two start button objects(one for the start button idle..
	 * the other for when the mouse is over it). Also sets hover to false(this is the boolean value for if the mouse is hovering..
	 * over the mouse or not.
	 */
	
	private void title() throws IOException{                  
		
	    startButtoninfo = new Button(375, 275, 1);
	    width = startButtoninfo.getWidth();
		height = startButtoninfo.getHeight();
		startButton = startButtoninfo.getImage();

	    startButton2info = new Button(375, 275, 2);
	    startButton2 = startButton2info.getImage();
		hover = false;
		
	}
	
	/* This class is used to listen to where the mouse is and if you are clicking anything. The variable "nummy" is a global
	 * variable that is used to keep track of what screen the user is on(the title, loading, room1, menu). If nummy is 1
	 * than the user is in the title screen. If nummy is 4 than the user is in the menu screen.
	 * 
	 */
	
	class listener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {               //if the mouse is pressed...
			
			if(nummy == 1){                                    // if user is in the title screen...

				Point point = e.getPoint();                      // create a point to keep track of coordinates of mouse

				Rectangle rec = startButton2info.getBounds();    //set the bounds of startbutton2(start button that is highlighted because user has mouse over it) to variable rec.

				if(rec.contains(point)){                       // if the users mouse is in the start button and clicking...
					try {
						nummy = 3;                             // set nummy to three( meaning that the screen will be the load screen..)
						loadscreen();                         // and go to load screen.
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
			else if(nummy == 4){                              // else if user is in the menu
				Point point = e.getPoint();                    // set mouses coordinates to point

				Rectangle rec = mainMenu2info.getBounds();      // create rectangles for the main menu, restart, and return buttons.
				Rectangle rec2 = restart2info.getBounds();
				Rectangle rec3 = returninfo2.getBounds();

				if(rec.contains(point)){                       // if user clicked on main menu...
					try {
						floorTiles.clear();                    // delete floor tiles
						sodaCans.clear();                      // delete soda cans
						enemies.clear();                       // delete enemies
						nummy = 1;                             // set nummy to 1 because user is going to main menu
						loadscreen();                          // initiate load screen
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(rec2.contains(point)){                // if user clicked on restart
					try {
						floorTiles.clear();                   // delete floor tiles
						sodaCans.clear();                     // delete soda cans
						enemies.clear();                      // delete enemies
						nummy = 3;                           // set nummy to 3 because user will be going back to room1
						//timer.stop();
						loadscreen();                        // goto load screen.
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(rec3.contains(point)){               // if user clicked on return.
					character.setPause(false);               // setPause to false because game is not paused anymore.
					nummy = 3;                              // set nummy to 3 because user will be going back to room1
					timer.start();                         // start backup the timer
					repaint();                             // and repaint everything
				}
			}
		}
		@Override
		public void mouseMoved(MouseEvent e){                // if the mouse moves...
			
			if(nummy == 1){                                        // if user is in the title screen

				Point point = e.getPoint();                        // create point for mouse

				Rectangle rec = startButtoninfo.getBounds();       //create retangle of start button that has the bounds of the start button
 
				if(rec.contains(point)){                       // if mouse is over start button
					hover = true;                            // hover is true(boolean value used to determine if the mouse is hovering over a button)
					repaint();                              // and repaint so the button changes color
				}
				else{                                          // else because mouse is not over button
					hover = false;                             // hover is false because mouse is not over the button
					repaint();                                 // repaint incase the button needs to turn back to original color
				}
			}
			else if(nummy == 4){                               // else if user is in menu
				
				Point point = e.getPoint();                       // create point for mouse

				Rectangle rec = mainMenuinfo.getBounds();          // create rectangles of all buttons
				Rectangle rec2 = restartinfo.getBounds();
				Rectangle rec3 = returninfo.getBounds();

				if(rec.contains(point)){                      // if mouse is over any button, flip that boolean value to true and repaint
					hover = true;
					repaint();
				}
				else{                                       // otherwise
					hover = false;
					repaint();
				}
				
				if(rec2.contains(point)){
					hover2 = true;
					repaint();
				}
				else{
					hover2 = false;
					repaint();
				}
				if(rec3.contains(point)){
					hover3 = true;
					repaint();
				}
				else{
					hover3 = false;
					repaint();
				}
			}
			
		}

	}
	
	private void loadscreen() throws IOException{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("load.png");
	    load = ImageIO.read(input);
	    
	    if(nummy == 1){
	    	nummy2 = 1;
	    	if(drew == false){
	    		nummy = 2;
	    		repaint();
	    	}
	    	else{
	    		drew = false;
		    	nummy = 1;
		    	repaint();
		    	title();
	    	}
	    }
	    else if(nummy == 3){
	    	nummy2 = 3;
	    	if(drew == false){
		    	nummy = 2;
		    	repaint();
	    	}
	    	else{
	    		drew = false;
		    	nummy = 3;
		    	initBoard();
	    	}
	    }
	    else if(nummy == 4){
	    	nummy2 = 4;
	    	if(drew == false){
		    	nummy = 2;
		    	repaint();
	    	}
	    	else{
	    		drew = false;
	    		nummy = 4;
	    	}
	    }
	    	
	    }
	    		
	
	private void initBoard() throws IOException {
		
		backGround1info = new BackGround(0, 0, 1);
		backGround1 = backGround1info.getImage();
		
		character = new Character(c_X, c_Y);
		
		dontScroll = false;
		
		pause = false;
		
		initSoda();
		
		initFloor();
		
		initEnemy();
		
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	private void initFloor() throws IOException {
		
		floorTiles = new ArrayList<>();                    // initialize arraylist for floor pieces
		
		for (int[] i : floorPos) {                         // populate it
			floorTiles.add(new Floor(i[0], i[1]));
		}
	}
	
	private void initSoda() throws IOException {
		
		sodaCans = new ArrayList<>();
		
		for(int[] i : sodaPos){
			sodaCans.add(new Soda(i[0], i[1]));
		}
	}
	
	private void initEnemy() throws IOException {
		
		enemies = new ArrayList<>();
		
		int num = 0;
		
		for(int[] i : enemyPos){
			num++;
			enemies.add(new Enemy(i[0], i[1], num));
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		if(nummy == 1){
		drawTitle(g);
		}
		else if(nummy == 2){
			try {
				drew = true;
				drawLoading(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(nummy == 3){
			try {
				drawLevel1(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(nummy == 4){
			try {
				drawMenu(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Toolkit.getDefaultToolkit().sync();	
	}
	
	private void drawLevel1(Graphics g) throws IOException{
		
		if(pause){
			
		}
		
		g.drawImage(backGround1, backGround1info.getX(), backGround1info.getY(), this);
		
		g.drawImage(character.getImage(), character.getX(), character.getY(), this);     // draws character
		
		for( Enemy i : enemies){
			
			if(i.getX() > -40 & i.getX() < (B_WIDTH + 40)){
			
				g.drawImage(i.getImage(), i.getX(), i.getY(), this);
			
			}
			
			if(character.getRightSide() & character.getMoveRight() == true & dontScroll == false){
				i.incrementX(-1);
					
			}
			else if(character.getLeftSide() & character.getMoveLeft() == true & dontScroll == false){
				i.incrementX(1);
			}
		}
		
		if(character.getRightSide() & character.getMoveRight() == true & dontScroll == false){
			
			if(character.checkOnGround()){
				character.walkAnimation(2);
			}
		
			for (Floor i : floorTiles) {                                       // draws each floor piece in floorTiles
				
				i.incrementX(-1);
				
				if(i.getX() > -40 & i.getX() < (B_WIDTH + 40)){

					g.drawImage(i.getImage(), i.getX(), i.getY(), this); 
				}
			}

			for (Soda i : sodaCans) {
				
				i.incrementX(-1);
				
				if(i.getX() > -40 & i.getX() < (B_WIDTH + 40)){

					g.drawImage(i.getImage(), i.getX(), i.getY(), this);
				}
			}
		}
		
		else if(character.getLeftSide() & character.getMoveLeft() == true & dontScroll == false){
			
			if(character.checkOnGround()){
				character.walkAnimation(1);
			}
			
			for (Floor i : floorTiles) {                                       // draws each floor piece in floorTiles
				
				i.incrementX(1);
				
				if(i.getX() > -40 & i.getX() < (B_WIDTH + 40)){
				
					g.drawImage(i.getImage(), i.getX(), i.getY(), this); 
				}
			}
			
			for (Soda i : sodaCans) {
				
				i.incrementX(1);
				
				if(i.getX() > -40 & i.getX() < (B_WIDTH + 40)){
				
					g.drawImage(i.getImage(), i.getX(), i.getY(), this);
				
				}
			}
			
		}
		
		else{
			
			for (Floor i : floorTiles) {                    // draws each floor piece in floorTiles
				
				if(i.getX() > -40 & i.getX() < (B_WIDTH + 40)){
				
					g.drawImage(i.getImage(), i.getX(), i.getY(), this); 
				}
			}
			
			for (Soda i : sodaCans) {
				
				if(i.getX() > -40 & i.getX() < (B_WIDTH + 40)){
				
					g.drawImage(i.getImage(), i.getX(), i.getY(), this);
				
				}
			}
		}
		
		Font font = new Font("Times New Roman", Font.PLAIN, 14);
		g.setFont(font);
		g.drawString("Soda cans: " + character.getSodaCount(), 5, 15);
	}
	
	private void drawMenu(Graphics g) throws IOException{
		mainMenuinfo = new Button(200,200,3);
		mainMenu2info = new Button(200,200,4);
		restartinfo = new Button(550,200,5);
		restart2info = new Button(550,200,6);
		returninfo = new Button(375,450,7);
		returninfo2 = new Button(375,450,8);
		
		mainMenu = mainMenuinfo.getImage();
		mainMenu2 = mainMenu2info.getImage();
		restart = restartinfo.getImage();
		restart2 = restart2info.getImage();
		returny = returninfo.getImage();
		returny2 = returninfo2.getImage();
		
		if(hover == false){
		g.drawImage(mainMenu, 200, 200, this);
		}
		else{
			g.drawImage(mainMenu2, 200, 200, this);
		}
		if(hover2 == false){
			g.drawImage(restart, 550, 200, this);
		}
		else{
			g.drawImage(restart2, 550, 200, this);
		}
		if(hover3 == false){
			g.drawImage(returny, 375, 450, this);
		}
		else{
			g.drawImage(returny2, 375, 450, this);
		}
			
		
		
	}
	
	private void drawTitle(Graphics g){
		if(!hover){
			g.drawImage(startButton, 375, 275, this);
		}
		else{
			g.drawImage(startButton2, 375, 275, this);
		}
	}
	private void drawLoading(Graphics g) throws IOException{
		
		g.drawImage(load, 175, 100, this);
		if(nummy2 == 1){
			nummy = 1;
		}
		else if(nummy2 == 3){
		nummy = 3;
		}
		else if(nummy2 == 4){
			nummy = 4;
		}
		loadscreen();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){               // for each game cycle (5 ms)
		
		pause(character.getPause());
		if(pause){
			nummy = 4;
			timer.stop();
			repaint();
		}
		else{
		
			dontScroll = false;

			//checkCollisionWithFloor();                                 // check character collides with floor

			try {
				checkIfFell();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			checkCollisionWithSoda();

			updateSoda();
			
			if(enemies.isEmpty()){
				checkCollisionWithFloor();
				System.out.println("here");
			}
			
			updateEnemies();

			if(character.checkOnGround() == false){            // if character is not touching the top of a floor piece...
				character.changeY(1);                             // character auto moves down (gravity)
			}
		

			if(character.checkJump() == false){          // if character is not jumping...
				try {
					updateCharacter();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                              // allow player to move character

				repaint();                                      // and redraw
			}
			else{                                 // else...
				character.jump();                 // user had hit space (which makes character jump)
				character.onGround(false);
					try {
						updateCharacter();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				              // still allow player to move character even if jumping

				repaint();                        // and redraw
			}
		}
		
	}
	
	private void updateEnemies(){
		
		for( Enemy i : enemies){
			System.out.println("ENEMY START");
			
			enemyCheckCollisionWithFloor(i);
			
			if(i.getX() >= 0 & i.getX() <= B_WIDTH){
			
				//enemyCheckCollisionWithFloor(i);

				enemyCheckCollisionWithEnemy(i);

				if((i.getX() - character.getX()) <= 400 & (i.getX() - character.getX()) > 0){
					i.incrementEnemyX(-1);
				}
				else if((i.getX() - character.getX()) >= -400 & (i.getX() - character.getX()) < 0){
					i.incrementEnemyX(1);
				}
				else if(i.getX() == character.getX() & i.checkJump() == false & i.checkOnGround() & character.checkOnGround()){
					i.setJump();
				}
				if(i.checkOnGround() == false){
					System.out.println("not on ground, set gravity");
					i.changeY(1);
				}
				
				if(i.checkJump() == true){
					i.jump();
					System.out.println("onground false because checkjump");
					i.onGround(false);
				}

				if(i.getTouched() == false){
					System.out.println("onground false because gettouched");
					i.onGround(false);
				}

			}

			checkIfEnemyFell(i);
			if(enemyFell == true){
				enemyFell = false;
				break;
			}
			

		}
	}
	
	private void updateCharacter() throws IOException {     // function that increments or decrements x from user controlled movements (which repostions character when drawing)
		
		character.move();
		if(character.getX() > 600){          // change
			character.setRightSide(true);
		}
		else if(character.getX() < 200){
			character.setLeftSide(true);
		}
		else{
			character.setRightSide(false);
			character.setLeftSide(false);
		}
        if(character.pressedOne()){
        	character.drinkSodaAnimation();
        }
	}
	
	private void updateSoda(){
		
		for (int i = 0; i < sodaCans.size(); i++) {

            Soda a = sodaCans.get(i);
            if (a.isVisible() == false) {
                sodaCans.remove(i);
            }
		}
	}
	
	public void checkCollisionWithFloor() {
		
		Rectangle r1 = character.getBounds();     // get x and y positions of character and set to r1
		
        character.touched(false);             // set touched to false (boolean value used to check if character is in contact with floor pieces) 
        character.setRight(false);            // set right to false (boolean value used to check if character needs to be restricted from moving to the right)
		character.setLeft(false);             // same but for moving left
		
		for (Floor f : floorTiles) {         // for each floor piece...
			
			Rectangle r2 = f.getBounds();      // get x and y values and set to r2
		
			if (r2.intersects(r1)) {           // if character bounds intersects specific floor piece bounds...
				
				character.touched(true);             // touched is equal to true (because character is in contact with floor piece)
				
				if((character.getp1() - r2.getY() >= 1 & character.getp1() - r2.getY() <= 3)){          // if character is on top of the floor piece
					
					if(character.onEdge((int)r2.getY(), (int)r2.getX(), false)){
						if((character.getX() <= ((r2.getX() - character.getWidth()) + 1)) || character.getX() >= ((r2.getX() + r2.getWidth()) - 2)){
							character.onGround(false);
						}
						else{

							character.onGround(true);             // on ground is true (boolean value used to check if character is on TOP of the floor piece
							if(character.checkJump() == false){       // if character is not jumping
								character.resetHeightReached();          // reset height reached (value that keeps track of how high the character has jumped. Needs to be reset when character is not jumping)
							}
						}
					}
					else{
						character.onGround(true);
						if(character.checkJump() == false & ((character.getX() + character.getWidth()) >= (((int)r2.getX()) + 3) & character.getX() <= ((int)(r2.getX() + r2.getWidth()) - 3))){
							character.resetHeightReached();
						}
					}
				}
				                                     
				else{                                        // else because character is not in contact with the top of a floor piece...
				
					if(((character.getX() + character.getWidth()) >= (((int)r2.getX()) + 3) & character.checkJump() & character.getX() <= ((int)(r2.getX() + r2.getWidth()) - 3) & character.checkJump())){
						character.stopJumping();
						if(character.checkSodaConsumed()){
							character.stopSuperJump();
						}
					}
					if(character.getX() < r2.getX()){           // if character is to the left of the center of a floor piece...
						character.setRight(true);                   // restrict character from moving to the right (so character cant pass through it)
						dontScroll = true;
					}
					else if(character.getX() > r2.getX()){      //else if character is to the right of the floor piece...
						character.setLeft(true);                     // restrict character from moving to the left
						dontScroll = true;
					}
				}
			}
		
		if(character.getTouched() == false){             // if character is not in contact with the ground
			character.onGround(false);                   // set on ground to false
		}
		}
		
	}
	
public void enemyCheckCollisionWithFloor(Enemy ene) {
		
		Rectangle e1 = ene.getBounds();
		
		Rectangle r1 = character.getBounds();     //test
		
		
		ene.touched(false);
		ene.setRight(false);
		ene.setLeft(false);
		
		character.touched(false);             // test 
        character.setRight(false);           //test
		character.setLeft(false);            //test
		
		for (Floor f : floorTiles) {         // for each floor piece...
			
			Rectangle r2 = f.getBounds();      // get x and y values and set to r2
			if(ene.getX() >= 0 & ene.getX() <= B_WIDTH){
			if(e1.intersects(r2)){
				System.out.println("new floor piece and onground: " + ene.checkOnGround());
				ene.touched(true);
				if((ene.getp1() - r2.getY() >= 1 & ene.getp1() - r2.getY() <= 3)){ 
					System.out.println("went through first if because " + (ene.getp1() - r2.getY()));
					if(ene.onEdge((int)r2.getY(), (int)r2.getX(), true)){
						System.out.println("went through second if");
						if((ene.getX() <= ((r2.getX() - ene.getWidth()) +2 )) || ene.getX() >= ((r2.getX() + r2.getWidth()) -2)){
							ene.onGround(false);
							System.out.println("not on ground");
						}
						else{
							System.out.println("on ground 2");
							ene.onGround(true);             // on ground is true (boolean value used to check if character is on TOP of the floor piece
							
							if(ene.checkJump() == false){       // if character is not jumping
								ene.resetHeightReached();          // reset height reached (value that keeps track of how high the character has jumped. Needs to be reset when character is not jumping)
								
								
							}
						}
					}
					else{
						System.out.println("on ground 3");
//						if((ene.getX() <= ((r2.getX() - ene.getWidth()) +2 )) || ene.getX() >= ((r2.getX() + r2.getWidth()) -2)){
//							System.out.println("determined not on ground at ground 3");
//							System.out.println("enemy x: " + ene.getX() + " is less than or equal too " + ((r2.getX() - ene.getWidth()) +2 ));
//							System.out.println("or enemy x: " + ene.getX() + " is greater than or equal too " + ((r2.getX() + r2.getWidth()) -2));
//							ene.onGround(false);
//						}
//						else{
//							System.out.println("determined on ground at ground 3");
//							System.out.println("enemy x: " + ene.getX() + " is not less than or equal too " + ((r2.getX() - ene.getWidth()) +2 ));
//							System.out.println("or enemy x: " + ene.getX() + " is not greater than or equal too " + ((r2.getX() + r2.getWidth()) -2));
						ene.onGround(true);
//						}
						if(ene.checkJump() == false & ((ene.getX() + ene.getWidth()) >= (((int)r2.getX()) + 3) & ene.getX() <= ((int)(r2.getX() + r2.getWidth()) - 3))){
							ene.resetHeightReached();
						}
					}
			}
				else{
					
					System.out.println("did not go through first if because " + (ene.getp1() - r2.getY()));
					
					if(((ene.getX() + ene.getWidth()) >= (((int)r2.getX()) + 3) & ene.checkJump() & ene.getX() <= ((int)(r2.getX() + r2.getWidth()) - 3) & ene.checkJump())){
						ene.stopJumping();
						System.out.println("stop jumping");
					}
					
					if(ene.getX() < r2.getX()){           // if character is to the left of the center of a floor piece...
						ene.setRight(true);                   // restrict character from moving to the right (so character cant pass through it)
						System.out.println("set right");
						if(ene.checkJump() == false & ene.checkOnGround() & (ene.getX() - character.getX()) >= -400 & (ene.getX() - character.getX()) < 0){
							ene.setJump();
							System.out.println("JJJUUUUUMMMPPPPPP!!!!!!!!!!!!!");
						}
					}
					else if(ene.getX() > r2.getX()){      //else if character is to the right of the floor piece...
						ene.setLeft(true); 
						System.out.println("set left");// restrict character from moving to the left
						System.out.println(ene.getX() - character.getX());
						System.out.println("jump: " + ene.checkJump());
						System.out.println("on ground: " + ene.checkOnGround());
						if(ene.checkJump() == false & ene.checkOnGround() & (ene.getX() - character.getX()) <= 400 & (ene.getX() - character.getX()) > 0){
							ene.setJump();
							System.out.println("JJJUUUUUMMMPPPPPP!!!!!!!!!!!!!");
						}
					}
				}
		}
			}

			if (r2.intersects(r1)) {           // if character bounds intersects specific floor piece bounds...
				

				character.touched(true);             // touched is equal to true (because character is in contact with floor piece)

				if((character.getp1() - r2.getY() >= 1 & character.getp1() - r2.getY() <= 3)){          // if character is on top of the floor piece

					if(character.onEdge((int)r2.getY(), (int)r2.getX(), false)){
						if((character.getX() <= ((r2.getX() - character.getWidth()) + 1)) || character.getX() >= ((r2.getX() + r2.getWidth()) - 2)){
							character.onGround(false);
						}
						else{
							character.onGround(true);             // on ground is true (boolean value used to check if character is on TOP of the floor piece
							if(character.checkJump() == false){       // if character is not jumping
								character.resetHeightReached();          // reset height reached (value that keeps track of how high the character has jumped. Needs to be reset when character is not jumping)
							}
						}
					}
					else{
						character.onGround(true);
						if(character.checkJump() == false & ((character.getX() + character.getWidth()) >= (((int)r2.getX()) + 3) & character.getX() <= ((int)(r2.getX() + r2.getWidth()) - 3))){
							character.resetHeightReached();
						}
					}
				}

				else{                                        // else because character is not in contact with the top of a floor piece...

					if(((character.getX() + character.getWidth()) >= (((int)r2.getX()) + 3) & character.checkJump() & character.getX() <= ((int)(r2.getX() + r2.getWidth()) - 3) & character.checkJump())){
						character.stopJumping();
						if(character.checkSodaConsumed()){
							character.stopSuperJump();
						}
					}
					if(character.getX() < r2.getX()){           // if character is to the left of the center of a floor piece...
						character.setRight(true);                   // restrict character from moving to the right (so character cant pass through it)
						dontScroll = true;
					}
					else if(character.getX() > r2.getX()){      //else if character is to the right of the floor piece...
						character.setLeft(true);                     // restrict character from moving to the left
						dontScroll = true;
					}
				}
			}

			if(character.getTouched() == false){             // if character is not in contact with the ground
				character.onGround(false);                   // set on ground to false
			}
			if(ene.getTouched() == false){
				ene.onGround(false);
			}
		}
}
		
	//}

	private void enemyCheckCollisionWithEnemy(Enemy ene){
		Rectangle r1 = ene.getBounds();
		Rectangle p = character.getBounds();
		
		for(Enemy e : enemies){
			Rectangle r2 = e.getBounds();
			
			if(r1.intersects(r2)){
				if(ene.getX() < e.getX() & ene.getLeft() == false){
					ene.incrementX(-1);
				}
			}
			if(r1.intersects(p)){
				
			}
		}
	}
	
	private void checkCollisionWithSoda(){
		
		Rectangle r1 = character.getBounds();
		
		if(sodaCans.size() >= 1){
		for (Soda s : sodaCans){
			Rectangle r2 = s.getBounds();
			
			if(r1.intersects(r2)){
				
				s.setVisible(false);
				character.setSodaCount(1);
			}
			
		}
		}
	}
	
	private void checkIfFell() throws IOException{
		if(character.getY() > B_WIDTH){
			floorTiles.clear();
			sodaCans.clear();
			nummy = 1;
			timer.stop();
			title();
		}
	}
	
	private void checkIfEnemyFell(Enemy ene){
		if(ene.getY() > B_WIDTH){
			enemies.remove(ene);
			
			enemyFell = true;
		}
		
	}
	
	private void pause(boolean o){
		
		pause = o;
	}
	
private class TAdapter extends KeyAdapter {
		
		@Override
		public void keyReleased(KeyEvent e) {
			if(nummy == 3){
			try {
				character.keyReleased(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e){
			if(nummy == 3){
			try {
				character.keyPressed(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}
	}
	
}
