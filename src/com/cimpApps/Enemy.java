package com.cimpApps;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject{
	
	
	private static final long serialVersionUID = 1L;
	private Color color;
	public Enemy(){
		color = Game.getRandomColor();
		width = Game.WIDTH/20;
		height = Game.HEIGHT/30;
		
		int randomPos = (int)((Math.random()*((int)(Game.HEIGHT - 2*height)/height)) + 1);
		int Y = (Game.HEIGHT - 2*height )*randomPos/((Game.HEIGHT - 2*height)/height);
		coordX = Game.WIDTH;
		coordY = Y;
		
	}
	
	public void move() {
		setSpeedX(1);
		int randomPos = (int)((Math.random()*((int)(Game.HEIGHT - 2*height)/height)) + 1);
		int Y = (Game.HEIGHT - 2*height )*randomPos/((Game.HEIGHT - 2*height)/height);
		if (isHit){
			coordX = Game.WIDTH;
			coordY = Y;
			color = Game.getRandomColor();
			isHit = false;
		}
		else{
			if (coordX < 0){
				coordX = Game.WIDTH;
				randomPos = (int)((Math.random()*((int)(Game.HEIGHT - 2*height)/height)) + 1);
				coordY = (Game.HEIGHT - 2*height )*randomPos/((Game.HEIGHT - 2*height)/height);
				color = Game.getRandomColor();
			}
		}	
	}//end of move()
	
	
	public Color getColor(){
		return color;
	}
	
	@Override
	public void setSpeedX(int speed){
		coordX -= speed;
	}

	@Override
	public void setSpeedY(int y) {}

	@Override
	/**
	 * Is drawing an enemy with color you want using a graphic object
	 * @param g - the object that is used for drawing the enemy
	 * @param enemyColor - it is the color that will have the enemy
	 */
	public void drawGameObject(Graphics g, Color objectColor) {
		g.setColor(objectColor);
		g.fillRect(this.coordX, this.coordY , 
				this.width, this.height);//drawing enemy
	}//end of drawEnemy()

}//end of Enemy
