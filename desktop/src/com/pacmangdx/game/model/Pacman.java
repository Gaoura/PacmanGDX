package com.pacmangdx.game.model;

import java.awt.Point;

public class Pacman extends GameElement
{
	
/*
/////////////////////////////////////////////////////////////////////////////////////////////

                ########  ########  #### ##     ##    ###    ######## ########
                ##     ## ##     ##  ##  ##     ##   ## ##      ##    ##
                ##     ## ##     ##  ##  ##     ##  ##   ##     ##    ##
                ########  ########   ##  ##     ## ##     ##    ##    ######
                ##        ##   ##    ##   ##   ##  #########    ##    ##
                ##        ##    ##   ##    ## ##   ##     ##    ##    ##
                ##        ##     ## ####    ###    ##     ##    ##    ########

/////////////////////////////////////////////////////////////////////////////////////////////
*/
	
	private Direction dir;
	public enum Direction{UP, DOWN, LEFT, RIGHT;} 
	
/*
/////////////////////////////////////////////////////////////////////////////////////////////

                     ########  ##     ## ########  ##       ####  ######
                     ##     ## ##     ## ##     ## ##        ##  ##    ##
                     ##     ## ##     ## ##     ## ##        ##  ##
                     ########  ##     ## ########  ##        ##  ##
                     ##        ##     ## ##     ## ##        ##  ##
                     ##        ##     ## ##     ## ##        ##  ##    ##
                     ##         #######  ########  ######## ####  ######

/////////////////////////////////////////////////////////////////////////////////////////////
*/
	
	public Pacman(Point p, World w) 
	{
		super(p, w);
		this.dir = null;
	}
	
	public void setDirection(Direction d)
	{
		this.dir = d;
	}
	
	public void update(float s)
	{
		
	}

	public Direction getDirection()
	{
		return this.dir;
	}
}

