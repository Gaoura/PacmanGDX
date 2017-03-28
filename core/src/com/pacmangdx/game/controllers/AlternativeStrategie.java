package com.pacmangdx.game.controllers;

import com.pacmangdx.game.model.Direction;
import com.pacmangdx.game.model.Fantome;

public class AlternativeStrategie implements FantomeStrategie
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
		// nombre entre 0 et 1 inclus
		int nombreAleatoire = 0 + (int)(Math.random() + 1);

		if (nombreAleatoire == 0)
			return new AleatoireStrategie().choixDirection(f);
		else
			return new PlusCourtAxeStrategie().choixDirection(f);
	}

}
