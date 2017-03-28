package com.pacmangdx.game.controllers;

import java.util.ArrayList;

import com.pacmangdx.game.model.Block;
import com.pacmangdx.game.model.Direction;
import com.pacmangdx.game.model.Fantome;
import com.pacmangdx.game.model.GameElement;
import com.pacmangdx.game.model.Maze;

public class FantomesController
{
	private ArrayList<Fantome> fantomes;
	
	public FantomesController()
	{
		this.fantomes = new ArrayList<Fantome>();
	}
	
	public void add(Fantome f)
	{
		if (f != null)
			this.fantomes.add(f);
		else
			System.out.println("Erreur ajout fantome null");
	}
	
	private boolean changementCase(Fantome f, float distance)
	{
		float pos_abs = f.getPosition().x;
		float pos_ord = f.getPosition().y;
		
		
		switch (f.getDirection())
		{
		case LEFT :
			if ((int)pos_abs != (int)(pos_abs - distance))
				return true;
			else return false;
		case RIGHT :
			if ((int)pos_abs != (int)(pos_abs + distance))
				return true;
			else return false;
		case DOWN :
			if ((int)pos_ord != (int)(pos_ord - distance))
				return true;
			else return false;
		case UP :
			if ((int)pos_ord != (int)(pos_ord + distance))
				return true;
			else return false;
		case NONE :
		default :
			System.out.println("Erreur direction");
			return false;
		}
	}
	
	private boolean intersection(Fantome f)
	{
		float pos_abs = (int)f.getPosition().x;
		float pos_ord = (int)f.getPosition().y;
		
		Maze labyrinthe = f.getWorld().getMaze();
		GameElement ge;
		
		switch (f.getDirection())
		{
		case LEFT :
		case RIGHT :
			// test de la case dans la direction DOWN
			ge = labyrinthe.get(pos_abs, pos_ord - 1f);
			if (ge != null && ge instanceof Block)
				return true;
			// test de la case dans la direction UP
			ge = labyrinthe.get(pos_abs, pos_ord + 1f);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case DOWN :
		case UP :
			// test de la case dans la direction LEFT
			ge = labyrinthe.get(pos_abs - 1f, pos_ord);
			if (ge != null && ge instanceof Block)
				return true;
			// test de la case dans la direction RIGHT
			ge = labyrinthe.get(pos_abs + 1f, pos_ord);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case NONE :
		default :
			System.out.println("Erreur direction");
			return false;
		}
	}
	
	private void deplacementNormal(Fantome f, float distance)
	{
		switch (f.getDirection())
		{
		case LEFT :
			f.getPosition().x -= distance;
			break;
		case RIGHT :
			f.getPosition().x += distance;
			break;
		case DOWN :
			f.getPosition().y -= distance;
			break;
		case UP :
			f.getPosition().y += distance;
			break;
		case NONE :
		default :
			System.out.println("Erreur direction");
			break;
		}
	}
	
	private boolean detectionObstacleBlock(Fantome f)
	{
		float pos_abs = (int)f.getPosition().x;
		float pos_ord = (int)f.getPosition().y;
		
		Maze labyrinthe = f.getWorld().getMaze();
		GameElement ge;
		
		switch (f.getDirection())
		{
		case LEFT :
			ge = labyrinthe.get(pos_abs - 1f, pos_ord);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case RIGHT :
			ge = labyrinthe.get(pos_abs + 2f, pos_ord);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case DOWN :
			ge = labyrinthe.get(pos_abs, pos_ord - 1f);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case UP :
			ge = labyrinthe.get(pos_abs, pos_ord + 2f);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case NONE :
		default :
			System.out.println("Erreur direction");
			return false;
		}
	}
	
	private void alignement(Fantome f)
	{
		switch (f.getDirection())
		{
		case LEFT :
			f.getPosition().x = (int)f.getPosition().x;
			break;
		case RIGHT :
			f.getPosition().x = (int)f.getPosition().x + 1;
			break;
		case DOWN :
			f.getPosition().y = (int)f.getPosition().y;
			break;
		case UP :
			f.getPosition().y = (int)f.getPosition().y + 1;
			break;
		case NONE :
		default :
			System.out.println("Erreur direction");
			break;
		}
	}
	
	// pré-requis : le fantome est bien aligné sur les cases
	private boolean detectionObstacleBlockDepuisIntersection(Fantome f, Direction direction)
	{
		float pos_abs = (int)f.getPosition().x;
		float pos_ord = (int)f.getPosition().y;
		
		Maze labyrinthe = f.getWorld().getMaze();
		GameElement ge;
		
		switch (direction)
		{
		case LEFT :
			ge = labyrinthe.get(pos_abs - 1f, pos_ord);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case RIGHT :
			ge = labyrinthe.get(pos_abs + 1f, pos_ord);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case DOWN :
			ge = labyrinthe.get(pos_abs, pos_ord - 1f);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case UP :
			ge = labyrinthe.get(pos_abs, pos_ord + 1f);
			if (ge != null && ge instanceof Block)
				return true;
			return false;
		case NONE :
		default :
			System.out.println("Erreur direction");
			return false;
		}
	}
	
	private void deplaceFantomes(float delta)
	{
		float pos_abs;
		float pos_ord;
		float distance;
		
		for (Fantome f : this.fantomes)
		{
			distance = f.getDistanceParSec() * delta;
			pos_abs = f.getPosition().x;
			pos_ord = f.getPosition().y;
						
			// dans la direction actuelle, si on va changer de case
			if (this.changementCase(f, distance))
			{
				// si on est sur une intersection
				if (this.intersection(f))
				{
					// on s'aligne d'abord
					this.alignement(f);
					
					Direction dir_choisie = f.choixDirection();
					Maze labyrinthe = f.getWorld().getMaze();
					GameElement ge;
					
					// s'il y a un bloc dans la direction voulue
					// on change de direction dans le sens horaire
					// jusqu'à trouver une direction (voire NONE)
					// car le fantome doit toujours etre en mouvement
					if (this.detectionObstacleBlockDepuisIntersection(f, dir_choisie))
					{
						int i = 0;
						while (i < 4 && this.detectionObstacleBlockDepuisIntersection(f, dir_choisie))
						{
							switch (dir_choisie)
							{
							case LEFT :
								dir_choisie = Direction.UP;
								break;
							case UP :
								dir_choisie = Direction.RIGHT;
								break;
							case RIGHT :
								dir_choisie = Direction.DOWN;
								break;
							case DOWN :
								dir_choisie = Direction.LEFT;
								break;
							case NONE :
							default :
								break;
							}
							i++;
						}
						
						if (i == 4)
							dir_choisie = Direction.NONE; 							
					}
					
					// on change le fantome de direction
					f.setDirection(dir_choisie);
					
				}
				// si on change de case sans être à une intersection
				else
				{
					if (!this.detectionObstacleBlock(f))
						this.deplacementNormal(f, distance);
					// sinon on se retrouverait dans une voie sans issue
					// donc on s'aligne et on repart dans l'autre sens
					else
					{
						this.alignement(f);
						f.setDirection(f.getDirection().getDirectionOpposee());
					}
				}
			}
			// si on ne change pas de case
			// on est peut aligné
			else
			{
				// s'il y a un obstacle 
				if (this.detectionObstacleBlockDepuisIntersection(f, f.getDirection()))
					f.setDirection(new AleatoireStrategie().choixDirection(f));
				else
					this.deplacementNormal(f, distance);
			}
		}
	}
	
	public void update(float delta)
	{
		this.deplaceFantomes(delta);
	}
	
}
