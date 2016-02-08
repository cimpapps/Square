package com.cimpApps;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject implements Drawable{
	
	private static final long serialVersionUID = 1L;
	private static int numberOfInstances = 0;
	private Color color;
	
	private int speedX;
	private int speedY;
	
	private int score;
	
	//we will use this sets of booleans to 
	//predict the direction of moving for the player object
	private boolean left;
	private boolean right;
	private boolean down;
	private boolean up;
	
	//we will use this boolean to add a certain behavior for the bullet,
	//when the player is shooting
	protected boolean shooting;
	
	private int bulletX;
	private int bulletY;
	private int bulletSpeed;
	
	public Player(){
		
		this(25, 25, 20,Game.HEIGHT/2);
		
		
	}
	public Player(int width, int height, int x, int y){
		numberOfInstances++;
		this.width= width;
		this.height = height;
		coordX = x - width/2;
		coordY = y - height;
		bulletX = coordX;
		bulletY = coordY + height/2 - height/8;
		
		//we want the players to be different colors
		if(numberOfInstances % 2 != 0)
			color = Color.RED;
		else
			color = Color.CYAN;
	}

	public void move(){

		if (left)
			coordX-=speedX;		
		if (right)
			coordX+=speedX;
		if (up)
			coordY-=speedY;
		if (down)
			coordY+=speedY;
		
		if (isHit){
			coordX = 20;
			coordY = (Game.HEIGHT - height)/2;
			isHit = false;
		}
	
		if(coordX == -width)
			coordX = Game.WIDTH-width;
		else if(coordX == Game.WIDTH)
			coordX = 0;
		if(coordY == -height)
			coordY = Game.HEIGHT - height;
		else if(coordY == Game.HEIGHT)
			coordY = 0;
		
	}
	
	/**
	 * We use this method to update player's bullet coordinates
	 * When the player is shooting is false, the bullet's coordinates 
	 * it will update in the same way as the player's coordinates.
	 * When the player is not shooting the bullet it will move only forward in X-direction.
	 * Be aware that you have only one bullet and it will be recharged only when
	 * it is off the screen or it is HAPPY TO KILL AN ENEMY, so point and shoot first. :D:D:D 
	 */
	public void shoot(){
		if (!shooting){
			bulletX = coordX;
			bulletY = coordY + height/2 - height/8;
		}else {
			bulletX += bulletSpeed;
			
			if(bulletX < 0 || bulletX > Game.WIDTH)
				shooting = false;
		}
	}//end of shoot()
	
	public void setBulletSpeed(int speed){
		bulletSpeed = speed;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	//getters and setters for everybody
	public boolean isShooting() {
		return shooting;
	}
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public void setWidth(int x){
		width = x;
	}
	public void setHeight(int y){
		height = y;
	}
	public int getCoordX(){
		return coordX;
	}
	public int getCoordY(){
		return coordY;
	}	
	public int getBulletX(){
		return bulletX;
	}
	public int getBulletY(){
		return bulletY;
	}
	public void setBulletX(int bulletX){
		this.bulletX = bulletX;
	}
	public void setBulletY(int bulletY){
		
	}
	public void setSpeedX(int dir){
		speedX = dir;
	}
	public void setSpeedY(int dir){
		speedY = dir;
	}
	public void goLeft(boolean b){
		left = b;
	}
	public void goRight(boolean b){
		right = b;
	}
	public void goDown(boolean b){
		down = b;
	}
	public void goUp(boolean b){
		up = b;
	}
	public boolean isLeft(){
		return left;
	}
	public boolean isRight(){
		return right;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	/**
	 * It is used to draw the player
	 * @param Graphics g
	 * @param Player player - the player we want to draw(using the coordinates from the player object)
	 * @param Color playerColor - the color we want for the player's body
	 * @param Color gunColor - the color we want for the enemy's body
	 */
	@Override
	public void drawGameObject(Graphics g, Color objectColor) {
		//draw player (a rectangular shape)
		
				setColor(objectColor);
				g.setColor(objectColor);
				g.fillRect(this.getCoordX(), this.getCoordY(), this.getWidth(), this.getHeight());//drawing player

				//draw the players gun
				g.setColor(Game.getRandomColor());
				g.fillOval(this.getBulletX(), this.getBulletY(),
						this.getWidth()*3/2, this.getHeight()/4);//drawing bullet
		
	}
	
	
}//end of Player

