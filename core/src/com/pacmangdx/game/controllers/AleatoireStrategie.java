package com.pacmangdx.game.controllers;

import com.pacmangdx.game.model.Direction;
import com.pacmangdx.game.model.Fantome;

public class AleatoireStrategie implements FantomeStrategie
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

	@Override
	public Direction choixDirection(Fantome f)
	{
		// nombre entre 0 et 3 inclus
		int nombreAleatoire = 0 + (int)(Math.random() * 3  + 1);

		switch (nombreAleatoire)
		{
		case 0 :
			return Direction.LEFT;
		case 1 :
			return Direction.RIGHT;
		case 2 :
			return Direction.DOWN;
		default:
			return Direction.UP;
		}
	}

}
