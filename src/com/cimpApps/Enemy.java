package com.cimpApps;

import java.awt.Color;

public class Enemy extends GameObject {
	
	
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

}//end of Enemy
