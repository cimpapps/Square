package com.cimpApps;

import java.io.Serializable;

/**
 *
 * @author Cimpoeru Catalin
 * This class is a blueprint for the objecs that will be drawn
 * on the game content (Player and Enemy)
 */
public abstract class GameObject implements Serializable, Drawable{
	private static final long serialVersionUID = 1L;
	
	protected int coordX;
	protected int coordY;
	protected int width;
	protected int height;
	protected boolean isHit;
	
	/**
	 * This method is abstract and it will be implemented in the concrete classes
	 */
	public abstract void move();
	
	/**
	 * This method is abstract and it will be implemented in the concrete classes
	 */
	public abstract void setSpeedX(int x);
	
	/**
	 * This method is abstract and it will be implemented in the concrete classes
	 */
	public abstract void setSpeedY(int y);
}
