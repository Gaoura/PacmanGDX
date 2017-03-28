package com.pacmangdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.pacmangdx.game.model.Pacman;

public class PacmanDisplayer
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

	private float compteur;
	private Texture pacman[];
	private int derniere_texture;

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

	public PacmanDisplayer(Texture p[])
	{
		this.compteur = 0f;
		this.pacman = p;
	}

	public void augmenterCompteur(float temps)
	{
		this.compteur += temps;

		if (this.compteur > 0.5f)
			this.compteur = 0f;
	}

	public Texture getTexturePacman(Pacman p)
	{
		switch (p.getDirection())
		{
		case LEFT :
			this.derniere_texture = 0;
			break;
		case RIGHT :
			this.derniere_texture = 2;
			break;
		case DOWN :
			this.derniere_texture = 4;
			break;
		case UP :
			this.derniere_texture = 6;
			break;
		case NONE :
			return pacman[this.derniere_texture];
		default :
			System.out.println("On ne devrait jamais arriver ici");
			break;
		}

		if (compteur > 0.25f)
			this.derniere_texture++;

		return this.pacman[this.derniere_texture];
	}
}
