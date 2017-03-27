package com.pacmangdx.game.model;

import java.awt.geom.Point2D;

public class MobileElement extends GameElement
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
	protected Direction direction;
	protected float distance_par_sec;

	protected MobileElement(Point2D.Float p, World w)
	{
		super(p, w);
		this.direction = Direction.NONE;
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

	public void setDirection(Direction d)
	{
		this.direction = d;
	}

	public Direction getDirection()
	{
		return this.direction;
	}

	public float getDistanceParSec()
	{
		return distance_par_sec;
	}

	public void setDistanceParSec(float distance_par_sec)
	{
		this.distance_par_sec = distance_par_sec;
	}
}
