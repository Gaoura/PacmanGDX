package com.pacmangdx.game.model;

import java.awt.geom.Point2D;
import java.util.Objects;

public class Block extends MobileElement
{
	
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
	
	public Block(Point2D.Float p, World w)
	{
		super(p, w);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (!(o instanceof Block))
            return false;
		
		Block b = (Block) o;
        return Objects.equals(this.position, b.position);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(this.position);
	}
}
