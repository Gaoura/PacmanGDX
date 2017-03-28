package com.pacmangdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.pacmangdx.game.controllers.AleatoireStrategie;
import com.pacmangdx.game.controllers.AlternativeStrategie;
import com.pacmangdx.game.controllers.PlusCourtAxeStrategie;
import com.pacmangdx.game.model.Fantome;

public class FantomesDisplayer
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

	private Texture fantomes[];

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
	
	public FantomesDisplayer(Texture fantomes[])
	{
		this.fantomes = fantomes;
	}
	
	public Texture getTextureFantome(Fantome f)
	{
		switch (f.getEtat())
		{
		case MORT :
			return this.fantomes[5];
		case FUYANT :
			return this.fantomes[4];
		case VIVANT :
			if (f.getStrategie() == AleatoireStrategie.class)
				return this.fantomes[0];
			if (f.getStrategie() == PlusCourtAxeStrategie.class)
				return this.fantomes[1];
			if (f.getStrategie() == AlternativeStrategie.class)
				return this.fantomes[2];
		default :
			System.out.println("Erreur texture fantome");
			return null;
		}
	}
}
