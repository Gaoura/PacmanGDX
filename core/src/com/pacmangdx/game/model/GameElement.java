package com.pacmangdx.game.model;

import java.awt.Point;

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
	
	protected Point position;
	protected World world;
	
	protected GameElement(Point p, World w)
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
	
	public Point getPosition()
	{
		return this.position;
	}
}
