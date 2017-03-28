package com.pacmangdx.game.controllers;

import com.pacmangdx.game.model.Direction;
import com.pacmangdx.game.model.Fantome;

public class PlusCourtAxeStrategie implements FantomeStrategie
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
		/*Maze labyrinthe = f.getWorld().getMaze();
		// on va stocker les possibilités de déplacement en utilisant le modèle :
		// - left  -> +1
		// - right -> +2
		// - down  -> +4
		// - up    -> +8
		// donc valeurs possibles : 0 à 15 inclus
		int possibilites = 0;

		GameElement ge;
		float pos_abs = f.getPosition().x;
		float pos_ord = f.getPosition().y;
		
		ge = labyrinthe.get(pos_abs - 1f, pos_ord);
		if (ge == null || !(ge instanceof Block)) possibilites += 1;
		ge = labyrinthe.get(pos_abs + 1f, pos_ord);
		if (ge == null || !(ge instanceof Block)) possibilites += 2;
		ge = labyrinthe.get(pos_abs, pos_ord - 1f);
		if (ge == null || !(ge instanceof Block)) possibilites += 4;
		ge = labyrinthe.get(pos_abs, pos_ord + 1f);
		if (ge == null || !(ge instanceof Block)) possibilites += 8;
		
		switch (possibilites)
		{
		case 0 :
		default :
			System.out.println("Fantôme Bloqué");
			break;
		}*/
		
		
		float diff_abs = f.getPosition().x - f.getWorld().getPacman().getPosition().x;
		float diff_ord = f.getPosition().y - f.getWorld().getPacman().getPosition().y;

		if (diff_abs == 0)
		{
			if (diff_ord == 0)
				return Direction.NONE;
			else
			{
				// si diff_ord est négatif, le fantôme est plus bas que pacman
				if (diff_ord < 0)
					return Direction.UP;
				else
					return Direction.DOWN;
			}

		}

		if (diff_ord == 0)
		{
			// si diff_abs est négatif, le fantôme est plus à gauche que pacman
			if (diff_abs < 0)
				return Direction.RIGHT;
			else
				return Direction.LEFT;
		}

		if(Math.abs(diff_abs) < Math.abs(diff_ord))
		{
			// si diff_abs est négatif, le fantôme est plus à gauche que pacman
			if (diff_abs < 0)
				return Direction.RIGHT;
			else
				return Direction.LEFT;
		}
		else
		{
			// si diff_ord est négatif, le fantôme est plus bas que pacman
			if (diff_ord < 0)
				return Direction.UP;
			else
				return Direction.DOWN;
		}

	}

}
