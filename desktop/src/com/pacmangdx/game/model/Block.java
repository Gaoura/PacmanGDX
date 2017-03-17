package com.pacmangdx.game.model;

import java.awt.Point;
import java.util.Objects;

public class Block extends GameElement
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
	
	public Block(Point p)
	{
		super(p, null);
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
