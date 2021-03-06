package com.pacmangdx.game.model;

import java.awt.geom.Point2D;

public abstract class GameElement
{
	
/*
/////////////////////////////////////////////////////////////////////////////////////////////

   ########  ########   #######  ######## ########  ######  ######## ######## ########
   ##     ## ##     ## ##     ##    ##    ##       ##    ##    ##    ##       ##     ##
   ##     ## ##     ## ##     ##    ##    ##       ##          ##    ##       ##     ##
   ########  ########  ##     ##    ##    ######   ##          ##    ######   ##     ##
   ##        ##   ##   ##     ##    ##    ##       ##          ##    ##       ##     ##
   ##        ##    ##  ##     ##    ##    ##       ##    ##    ##    ##       ##     ##
   ##        ##     ##  #######     ##    ########  ######     ##    ######## ########

/////////////////////////////////////////////////////////////////////////////////////////////
*/	
	
	protected Point2D.Float position;
	protected World world;

	protected GameElement(Point2D.Float p, World w)
	{
		this.position = p;
		this.world = w;
	}
	
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
	
	public Point2D.Float getPosition()
	{
		return this.position;
	}
	
	public World getWorld() {
		return world;
	}

	public void setPosition(Point2D.Float position)
	{
		this.position = position;
	}
}
