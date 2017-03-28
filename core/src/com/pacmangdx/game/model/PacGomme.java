package com.pacmangdx.game.model;

import java.awt.geom.Point2D.Float;
import java.util.Objects;

public class PacGomme extends GameElement
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

	private boolean mangee;

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
	public PacGomme(Float p, World w)
	{
		super(p, w);
		this.world.incrementerNbPacGommes();
	}

	public boolean estMangee()
	{
		return this.mangee;
	}

	public void seFaitManger()
	{
		this.mangee = true;
		this.world.decrementerNbPacGommes();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (!(o instanceof PacGomme))
            return false;

		PacGomme p = (PacGomme) o;
        return Objects.equals(this.position, p.position);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.position);
	}

}
