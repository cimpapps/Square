package com.cimpApps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Game extends JPanel implements Runnable, Serializable{
    
	private static final String BIN_EXTENSION = ".bin";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Thread thread;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
	
	private static Game content;
	
	private JFrame window;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuHelp;
	private JMenu menuOptions;
	private JMenuItem menuItemNewGame;
	private JMenuItem menuItemAbout;
	private JMenuItem menuItemSave;
	private JMenuItem menuItemLoad;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemKeys;
	private KeysInput keys;
	private JButton spButton;
	private JButton mpButton;
	private JButton startButton;
	private boolean buttonsAreRemoved;
	private Image img;
	
	
	private Enemy[] enemy = new Enemy[10];
	private Player player1;
	private Player player2;
	private boolean multiplayer;
	private boolean start;

	private final String imageLocationString = "com/cimpApps/starwars.jpg";

	public static void main(String[] a){
		content = new Game();
		content.window = new JFrame("cimpApps.com");
		content.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		content.window.setContentPane(content);
		content.window.setJMenuBar(content.menuBar);
		
		//we want to center on the screen the app
		content.window.setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-WIDTH/2),
				((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-HEIGHT/2));
		content.window.setSize(WIDTH, HEIGHT);
		content.window.setResizable(false);
		
		content.window.setVisible(true);
		
		
	}//end of main()
	
	public void run(){
		 
		while(true){
			try{
				if (start) {
					player1.move();
					player1.shoot();
					if(multiplayer){
						player2.move();
						player2.shoot();
					}	
					
					for (int i = 0; i < enemy.length; i++) {
						enemy[i].move();
						this.detectCollision(enemy[i], player1);
						if(multiplayer)
							this.detectCollision(enemy[i], player2);
					}
					
				}
				repaint();
				Thread.sleep(8);
				
			}catch (InterruptedException e) {
            e.printStackTrace();
			}//end of try-catch		
		}//end of while
	}//end of run()
	
	/**
	 *Make a new Game content where initialize the players and enemies
	 *initComponents methods is also called in this constructor
	 */
	public Game(){
		
		initComponents();
		player1 = new Player(25, 25, 20, 150);
		player2 = new Player(25, 25, 20, 300);
		
		for (int i = 0; i < enemy.length; i++) {
			enemy[i] = new Enemy();
		}
		thread = new Thread(this);
		thread.start();
		
	}
	
	/**
	 * Initialize instance Swing components used in the class Game
	 */
	public void initComponents() {
		try {
			
			 URL url = getClass().getClassLoader().getResource(imageLocationString);
			 
		   	 if (url != null){
		   		 
			 	 File imageFile = new File(url.getPath());
			 	 
			 	 img = ImageIO.read(imageFile);
			 	 
			 	 
			 }
		   	 
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}finally {
			
		}
		
		setBackground(Color.BLACK);
		
		keys = new KeysInput();
		setFocusable(true);
		KeyCommand actionKey = new KeyCommand();
		addKeyListener(actionKey);
		
		
		ButtonsAction al = new ButtonsAction();
		spButton = new JButton("Singe Player");
		add(spButton, BorderLayout.NORTH);
		spButton.addActionListener(al);
		mpButton = new JButton("MultiPlayer");
		add(mpButton, BorderLayout.CENTER);
		mpButton.addActionListener(al);
		startButton = new JButton("PLAY");
		startButton.addActionListener(al);
	 	add(startButton, BorderLayout.SOUTH);
		startButton.setVisible(false);
		
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuOptions = new JMenu("Options");
		menuHelp = new JMenu("Help");
		menuBar.add(menuFile);
		menuBar.add(menuOptions);
		menuBar.add(menuHelp);
		
		menuItemNewGame = new JMenuItem("New Game");
		menuItemNewGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player1 = new Player(25, 25, 20, 150);
				player2 = new Player(25, 25, 20, 300);
				for (int i = 0; i < enemy.length; i++) {
					enemy[i] = new Enemy();
				}
				player1.setScore(0);
				player2.setScore(0);
				add(spButton);
				add(mpButton);
				add(startButton);
				startButton.setVisible(false);
				startButton.setText("PLAY");
				start = false;
				multiplayer = false;
				buttonsAreRemoved = false;
				
			}
		});
		menuFile.add(menuItemNewGame);
		
		//Setting the menuItemLoad
		menuItemLoad = new JMenuItem("Load Game...");
		menuItemLoad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					saveLoadGui(JFileChooser.OPEN_DIALOG);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
				
				//in case the player wants to load from a new game state we want to
				//make sure that the buttons are gone after loading
				if(buttonsAreRemoved){
					remove(mpButton);
					remove(spButton);
					remove(startButton);
					
				}
			}//end of actionPerformed()
		});//end of adding anonymous ActionListener
		menuFile.add(menuItemLoad);
		
		//Setting the menuItemSave
		
		menuItemSave = new JMenuItem("Save Game");
		menuItemSave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				saveLoadGui(JFileChooser.SAVE_DIALOG);
			}//end of actionPerformed()
			
		});//end of adding anonymous ActionListener
		
		menuFile.add(menuItemSave);
		
		//Setting the menuItemAbout
		menuItemAbout = new JMenuItem("About");
		menuHelp.add(menuItemAbout);
		menuItemAbout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String message = "This game is a property of cimpApps.com!";
				String title = "License";
				int messageType =JOptionPane.INFORMATION_MESSAGE;
				JOptionPane.showMessageDialog(null, message, title, messageType );
			}
			
		});
		
		menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuFile.add(menuItemExit);
		
		menuItemKeys = new JMenuItem("Keys");
		menuItemKeys.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				keys.setVisible(true);				
			}
		});
		menuOptions.add(menuItemKeys);
	}//end of initComponents()
	
	/**
	 * it is the method that paints with a graphic object and it is repainted
	 *  over and over again in the run method
	 * @param g
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (img!=null)
			g.drawImage(img, 0, 0, null);
		
		player1.drawGameObject(g, player1.getColor());
		
		g.setColor(player1.getColor());
		g.drawString( "PLAYER1: " + player1.getScore() , 5, 20);
		
		if(multiplayer){
			player2.drawGameObject(g, player2.getColor());
			g.setColor(player2.getColor());
			g.drawString("PLAYER2: " + player2.getScore() , 110, 20);
		}
		g.setColor(Color.LIGHT_GRAY);
		
		
		if (buttonsAreRemoved)
			g.drawString("Press P to " + (start? "PAUSE" : "PLAY"), getWidth()/2-50, 20);
			
		for (int i = 0; i < enemy.length; i++) {
			enemy[i].drawGameObject(g, enemy[i].getColor());
		}
		
		
	}//end of paintComponent()
	
	
	
	
	
	
	
	/**
	 * Detects the collision between the player and the enemy.
	 * If the player shoot the enemy, player's score will increase with 1.
	 * If the player is touched by the enemy the player's score will decrease with 1.
	 * @param Enemy enemy
	 * @param Player player
	 */
	private static void detectCollision(Enemy enemy, Player player){
		int score = player.getScore();
		if ((player.getBulletX())+player.width> enemy.coordX && 
				player.getBulletX() < (enemy.coordX+enemy.width) &&
				enemy.coordY < (player.getBulletY()  + player.getHeight()/4) &&
				player.getBulletY() < (enemy.coordY+enemy.height) ){
			enemy.isHit = true;
			score++;
			player.setScore(score);
			player.shooting = false;
			return;
			}
		if ((player.getCoordX() + player.getWidth()) >= enemy.coordX && 
				player.getCoordX() <= (enemy.coordX + enemy.width) &&
				(player.getCoordY() + player.getHeight()) >= enemy.coordY &&
				player.getCoordY() <= (enemy.coordY + enemy.height)){
			player.isHit = true;
			enemy.isHit = true;
			score--;
			player.setScore(score);
		}
	}//end of playervsEnemy()
	
	/**
	 * This method makes the player to shoot the bullet by setting the bullet 
	 * speed 3 times bigger forward and calibrate it on the vertical.
	 * @param player
	 */
	public void pullTheTriger(Player player) {
		final int calibre = player.getBulletY();
		player.shooting=true;
		player.setBulletY(calibre);
		player.setBulletSpeed(3);
	}//end of pullTheTriger
	

	/**
	 *This class with will be used to set the KeyListener to our JPanel.
	 *In this way we can set key commands to change the coordinates to 
	 *the Player objects.
	 */
	class KeyCommand implements KeyListener{
		
		public void keyPressed(KeyEvent ke){
			
			int key = ke.getKeyCode();
			if(key == keys.getKeyDown1()){
				player1.goDown(true);
				player1.setSpeedY(1);
			}
			if(key == keys.getKeyUp1()){
				player1.goUp(true);
				player1.setSpeedY(1);
			}
			if(key == keys.getKeyLeft1()){
				player1.goLeft(true);
				player1.setSpeedX(1);
			}
			if(key == keys.getKeyRight1()){
				player1.goRight(true);
				player1.setSpeedX(1);
			}
			
			if(key == keys.getKeyDown2()){
				player2.goDown(true);	
				player2.setSpeedY(1);
			}
			if(key == keys.getKeyUp2()){
				player2.goUp(true);	
				player2.setSpeedY(1);
			}
			if(key == keys.getKeyLeft2()){
				player2.goLeft(true);
				player2.setSpeedX(1);
			}
			if(key == keys.getKeyRight2()){
				player2.goRight(true);
				player2.setSpeedX(1);
			}	
			if(key == KeyEvent.VK_P && (startButton.isVisible())){
				if (start)
					start = false;
				else 
					start = true;
			}
			if(key == keys.getShoot1()){
				pullTheTriger(player1);
				
			}
			if(key == keys.getShoot2()){
				pullTheTriger(player2);}
		}//end of KeyPressed()
 
		public void keyReleased(KeyEvent ke) {
			int key = ke.getKeyCode();
			if(key == keys.getKeyDown1()){
				player1.goDown(false);
				
			}
			if(key == keys.getKeyUp1()){
				player1.goUp(false);
			}
			if(key == keys.getKeyLeft1()){
				player1.goLeft(false);
			}
			if(key == keys.getKeyRight1()){
				player1.goRight(false);
			}
			
			if(key == keys.getKeyDown2()){
				player2.goDown(false);	
			}
			if(key == keys.getKeyUp2()){
				player2.goUp(false);	
			}
			if(key == keys.getKeyLeft2()){
				player2.goLeft(false);
			}
			if(key == keys.getKeyRight2()){
				player2.goRight(false);
			}	
		}//end of keyReleased()

		public void keyTyped(KeyEvent ke) {

		}//end of keyTyped
		
	}//end of KeyCommand
	
	class ButtonsAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mpButton){
				multiplayer = true;
			}
			//after the player choose single or multiplayer
			//it doesn't need the buttons anymore, so it is better 
			//to clean the view a bit
			remove(spButton);
			remove(mpButton);
			
			startButton.setVisible(true);
			
			if (e.getSource() == startButton){

				start = true;	
				remove(startButton);
			}
			buttonsAreRemoved = true;
			
		}//end of actionPerformed
	}//end of ButtonsAction
	
	/**
	 * 
	 * @return a random color
	 * We use to return a random bright color,
	 * by setting r, g, b values in a random way.
	 */
	public static Color getRandomColor(){
		int r = (int)((Math.random()*160)+95);
		int g = (int)((Math.random()*160)+95);
		int b = (int)((Math.random()*160)+95);
	
		return new Color(r,g,b);
	}
	/**
	 * @param fileName - is the file that we write on
	 * We save the players state and some fields of the game itself
	 * using 
	 */
	public void save(File fileName) {
		throwIllegalFileNameException(fileName);
		
		ObjectOutputStream oos = null;
		
		//I used try with resource, to automatically close the FileOutputStream object
		//but we still have to use a finally block to close the ObjectOutputStream object
		try(FileOutputStream fos = new FileOutputStream(fileName)){
			if(fos != null)
				oos = new ObjectOutputStream(fos);
			if(oos != null){
					oos.writeObject(player1);
					oos.writeObject(player2);
					oos.writeBoolean(multiplayer);
					oos.writeBoolean(buttonsAreRemoved);
					oos.writeObject(enemy);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(oos!= null)
					oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @param fileName
	 * This method it will load the state of the Player object and Enemy object
	 * that were saved in the File filename
	 */
	public void load(File fileName){
		// we will handle this exception In the action listener for the save or load menu item
		throwIllegalFileNameException(fileName);
		
		ObjectInputStream ois = null;
		try(FileInputStream fis = new FileInputStream(fileName)){
			if (fis != null)
				ois = new ObjectInputStream(fis);
			
			if (ois != null){
				player1 = (Player) ois.readObject();
				player2 = (Player) ois.readObject();
				multiplayer = (boolean) ois.readBoolean();
				buttonsAreRemoved = (boolean) ois.readBoolean();
				enemy = (Enemy[]) ois.readObject();
				
			}
		}catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ois != null)
					ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param fileName
	 * It is used to throw an Exception if the file name for the save file or load file
	 * is an illegal input from the user
	 */
	private void throwIllegalFileNameException(File fileName) {
		if (!fileName.getName().toLowerCase().endsWith(BIN_EXTENSION)){
			throw new IllegalArgumentException("The File name has to be "+BIN_EXTENSION+" extension");
		}
	}
	
	/**
	 * @param FILE_CHOOSER
	 * the integer passed to this method have to be a JFileChooser constant to work out to its purpose:
	 * JFileChooser.OPEN_DIALOG or JFileChooser.SAVE_DIALOG.
	 * This method purpose is to handle the interface for the I/O 
	 */
	private void saveLoadGui(int FILE_CHOOSER) {
		JFileChooser chooser = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		chooser.setCurrentDirectory(workingDirectory);
		chooser.showOpenDialog(window);
		chooser.setDialogType(FILE_CHOOSER);
		File file = chooser.getSelectedFile();
		
		if (file != null) {
			try {
				switch (FILE_CHOOSER) {
				case JFileChooser.OPEN_DIALOG:
					if(file.exists())
						load(file);
					break;
					
				case JFileChooser.SAVE_DIALOG:
					save(file);
					break;
					
				default:
					System.out.println("You have to use the "
							+ "Open dialog constant or save dialog constant from JFileChooser");
					break;
				}
			} catch (IllegalArgumentException ie) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(chooser,
						ie.getMessage());
			}
			 
		}
	}
}//end of Game



