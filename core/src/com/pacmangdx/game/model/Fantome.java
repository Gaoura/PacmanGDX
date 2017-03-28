package com.pacmangdx.game.model;

import java.awt.geom.Point2D.Float;

import com.pacmangdx.game.controllers.FantomeStrategie;

public class Fantome extends MobileElement
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

	private FantomeStrategie strategie;
	private Etat etat;
	
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

	public Fantome(Float p, World w, final FantomeStrategie s)
	{
		super(p, w);
		this.strategie = s;
		this.setEtat(Etat.VIVANT);
	}
	
	public Direction choixDirection()
	{
		return this.strategie.choixDirection(this);
	}

	public Etat getEtat()
	{
		return this.etat;
	}

	public void setEtat(Etat etat)
	{
		this.etat = etat;
	}
	
	public Class<? extends FantomeStrategie> getStrategie()
	{
		return this.strategie.getClass();
	}

}
