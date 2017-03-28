package com.pacmangdx.game.controllers;

import java.awt.geom.Point2D;

import com.pacmangdx.game.model.Block;
import com.pacmangdx.game.model.Direction;
import com.pacmangdx.game.model.GameElement;
import com.pacmangdx.game.model.Maze;
import com.pacmangdx.game.model.PacGomme;
import com.pacmangdx.game.model.Pacman;

public class PacmanController
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

	private Direction derniere_commande;
	private Pacman pacman;
	
	/*
	 * Gère le déplacement de Pacman et la collision avec un bloc
	 * Return true s'il y a collision ou aucun movement de Pacman ou alignement de Pacman
	 * Return false si Pacman s'est déplacé normalement
	 */
	private boolean detectionCollisionBloc(float delta)
	{
		// une variable pour éviter de nombreux appels à la méthode getDirection()
		Direction dir_actuelle = this.pacman.getDirection();

		// on demande la même direction que celle où l'on va
		// dans ce cas on ignore, on réinitialise à NONE la dernière commande
		if (dir_actuelle == this.derniere_commande)
			this.derniere_commande = Direction.NONE;

		// on va dans aucune direction ou on a une direction
		// et on veut changer pour la direction opposée
		// dans les 2 cas on change juste la direction et on passe aux tests de collision
		if (dir_actuelle == Direction.NONE
				|| dir_actuelle.estDirectionOpposee(this.derniere_commande))
		{
			this.pacman.setDirection(this.derniere_commande);
			dir_actuelle = this.derniere_commande;
			this.derniere_commande = Direction.NONE;
		}

		// si on veut aller dans une direction perpendiculaire
		// on doit attendre d'être à une intersection (voir le switch)

		//-----------------------------------------------------------------------------------

		// maintenant que la direction est fixée
		// on avance sauf s'il y a obstacle

		// distance qui va être parcourue
		float distance = this.pacman.getDistanceParSec() * delta;
		// un pointeur vers la position du pacman pour aller plus vite
		Point2D.Float p = this.pacman.getPosition();
		// un pointeur vers le labyrinthe pour aller plus vite
		Maze labyrinthe = this.pacman.getWorld().getMaze();

		GameElement ge = null;

		// coordonnées auxquelles va se situer la case dans la direction demandée
		float case_demande_abscisse = p.x;
		float case_demande_ordonnee = p.y;

		// dans la condition où il y a changement de case
		// coordonnées de la case suivante dans la direction actuelle
		float chg_case_suiv_dir_act_abscisse = p.x;
		float chg_case_suiv_dir_act_ordonnee = p.y;

		// dans la condition où on est sur une intersection
		// coordonnées de la case suivante dans la direction actuelle
		float intersec_case_suiv_dir_act_abscisse = p.x;
		float intersec_case_suiv_dir_act_ordonnee = p.y;

		// coordonnées du pacman s'il y a déplacement normal
		float nouv_position_abscisse = p.x;
		float nouv_position_ordonnee = p.y;

		// coordonnées du pacman s'il y a alignement
		float alignement_abscisse = p.x;
		float alignement_ordonnee = p.y;

		// indicateurs d'un changement de case ou d'une intersection
		// ils ne devraient pas être à true en même temps
		boolean changement_case = false;
		boolean intersection = false;

		switch (dir_actuelle)
		{
		case LEFT :
			nouv_position_abscisse -= distance;

			if ((int)p.x != (int)nouv_position_abscisse) changement_case = true;
			if ((int)p.x == p.x)                         intersection = true;

			if (this.derniere_commande == Direction.DOWN)
				case_demande_ordonnee -= 1f;
			else
				case_demande_ordonnee += 1f;

			alignement_abscisse = (int)p.x;
			chg_case_suiv_dir_act_abscisse -= 1f;
			intersec_case_suiv_dir_act_abscisse -= 1f;
			break;
		case RIGHT :
			nouv_position_abscisse += distance;

			if ((int)p.x != (int)nouv_position_abscisse) changement_case = true;
			if ((int)p.x == p.x)                         intersection = true;

			if (this.derniere_commande == Direction.DOWN)
				case_demande_ordonnee -= 1f;
			else
				case_demande_ordonnee += 1f;

			case_demande_abscisse += 1f;
			alignement_abscisse = (int)nouv_position_abscisse;
			chg_case_suiv_dir_act_abscisse += 2f;
			intersec_case_suiv_dir_act_abscisse += 1f;
			break;
		case DOWN :
			nouv_position_ordonnee -= distance;

			if ((int)p.y != (int)nouv_position_ordonnee) changement_case = true;
			if ((int)p.y == p.y)                         intersection = true;

			if (this.derniere_commande == Direction.LEFT)
				case_demande_abscisse -= 1f;
			else
				case_demande_abscisse += 1f;

			alignement_ordonnee = (int)p.y;
			chg_case_suiv_dir_act_ordonnee -= 1f;
			intersec_case_suiv_dir_act_ordonnee -= 1f;
			break;
		case UP :
			nouv_position_ordonnee += distance;

			if ((int)p.y != (int)nouv_position_ordonnee) changement_case = true;
			if ((int)p.y == p.y)                         intersection = true;

			if (this.derniere_commande == Direction.LEFT)
				case_demande_abscisse -= 1f;
			else
				case_demande_abscisse += 1f;

			case_demande_ordonnee += 1f;
			alignement_ordonnee = (int)nouv_position_ordonnee;
			chg_case_suiv_dir_act_ordonnee += 2f;
			intersec_case_suiv_dir_act_ordonnee += 1f;
			break;
		case NONE :
			return true;
		default :
			// on ne devrait jamais tomber dans ce cas-là
			System.out.println("Erreur direction");
			break;
		}


		// s'il y a changement de case
		if (changement_case)
		{
			// si on a une demande de direction perpendiculaire
			if (dir_actuelle.estDirectionPerpendiculaire(this.derniere_commande))
			{
				// on récupère la case suivante en fonction de la direction demandée
				ge = labyrinthe.get(case_demande_abscisse, case_demande_ordonnee);

				// si la case suivante est un obstacle
				if (ge != null && ge instanceof Block)
				{
					// on ignore la demande
					// et on regarde la case suivante dans la direction actuelle
					ge = labyrinthe.get(chg_case_suiv_dir_act_abscisse, chg_case_suiv_dir_act_ordonnee);

					// si c'est un obstacle, on passe la direction à NONE
					// et on aligne le pacman
					if (ge != null && ge instanceof Block)
					{
						p.x = alignement_abscisse;
						p.y = alignement_ordonnee;
						this.pacman.setDirection(Direction.NONE);
						return true;
					}
					// sinon on peut avancer
					else
					{
						p.x = nouv_position_abscisse;
						p.y = nouv_position_ordonnee;
						return false;
					}
				}
				// sinon si la case suivante est vide
				else
				{
					// on aligne le pacman
					// et on change de direction
					p.x = alignement_abscisse;
					p.y = alignement_ordonnee;
					this.pacman.setDirection(this.derniere_commande);
					return true;
				}
			}
			// sinon si on n'a aucune demande
			else
			{
				// on regarde la case suivante dans la direction actuelle
				ge = labyrinthe.get(chg_case_suiv_dir_act_abscisse, chg_case_suiv_dir_act_ordonnee);

				// si c'est un obstacle, on passe la direction à NONE
				// et on aligne le pacman
				if (ge != null && ge instanceof Block)
				{
					p.x = alignement_abscisse;
					p.y = alignement_ordonnee;
					this.pacman.setDirection(Direction.NONE);
					return true;
				}
				// sinon on peut avancer
				else
				{
					p.x = nouv_position_abscisse;
					p.y = nouv_position_ordonnee;
					return false;
				}
			}
		}
		// sinon on regarde si on est sur une intersection
		else
		{
			// si on est sur une intersection
			if (intersection)
			{
				// on regarde la case suivante dans la direction actuelle
				ge = labyrinthe.get(intersec_case_suiv_dir_act_abscisse, intersec_case_suiv_dir_act_ordonnee);

				// si c'est un obstacle, on passe la direction à NONE
				// et on ne bouge pas
				if (ge != null && ge instanceof Block)
				{
					this.pacman.setDirection(Direction.NONE);
					return true;
				}
				// sinon on peut avancer
				else
				{
					p.x = nouv_position_abscisse;
					p.y = nouv_position_ordonnee;
					return false;
				}
			}
			// sinon on peut avancer
			else
			{
				p.x = nouv_position_abscisse;
				p.y = nouv_position_ordonnee;
				return false;
			}
		}


		/*
		/////////////////////////////////////////////////////////////////////////////////////////////

		            ########  ######## ########    ###     ######  ########  #######
		            ##     ## ##       ##         ## ##   ##    ##    ##    ##     ##
		            ##     ## ##       ##        ##   ##  ##          ##    ##     ##
		            ########  ######   ######   ##     ## ##          ##    ##     ##
		            ##   ##   ##       ##       ######### ##          ##    ##     ##
		            ##    ##  ##       ##       ##     ## ##    ##    ##    ##     ##
		            ##     ## ######## ##       ##     ##  ######     ##     #######

		/////////////////////////////////////////////////////////////////////////////////////////////
		 */

		/*
		 * Toute cette partie du code est équivalente à la partie au-dessus
		 * mais celle-ci est plus ancienne, plus longue et donc plus détaillée
		 * Elle est gardée au cas-où il y aurait besoin de recomprendre l'algo au-dessus
		 * où qu'un bug y est repéré car cette partie semble exempt de bug
		 */

		/*switch (dir_actuelle)
				{
				case LEFT :
					// s'il y a changement de case
					if ((int)p.x != (int)(p.x - distance))
					{
						// si on a une demande de direction perpendiculaire
						if (dir_actuelle.estDirectionPerpendiculaire(this.derniere_commande))
						{
							// on récupère la case suivante en fonction de la direction demandée
							if (this.derniere_commande == Direction.DOWN)
								ge = labyrinthe.get(p.x, p.y - 1);
							else
								ge = labyrinthe.get(p.x, p.y + 1);

							// si la case suivante est un obstacle
							if (ge != null)
							{
								// on ignore la demande
								// et on regarde la case suivante dans la direction actuelle
								ge = this.pacman.getWorld().getMaze().get(p.x - 1, p.y);

								// si c'est un obstacle, on passe la direction à NONE
								// et on aligne le pacman avec sa case actuelle
								if (ge != null)
								{
									p.x = (int)p.x;
									this.pacman.setDirection(Direction.NONE);
								}
								// sinon on peut avancer
								else
									p.x -= distance;
							}
							// sinon si la case suivante est vide
							else
							{
								// on aligne le pacman avec sa case actuelle
								// et on change de direction
								p.x = (int)p.x;
								this.pacman.setDirection(this.derniere_commande);
							}
						}
						// sinon si on n'a aucune demande
						else
						{
							// on regarde la case suivante dans la direction actuelle
							ge = labyrinthe.get(p.x - 1, p.y);

							// si c'est un obstacle, on passe la direction à NONE
							// et on aligne le pacman avec la case suivante
							if (ge != null)
							{
								p.x = (int)p.x;
								this.pacman.setDirection(Direction.NONE);
							}
							// sinon on peut avancer
							else
								p.x -= distance;
						}
					}
					// sinon on regarde si on est sur une intersection
					else
					{
						// si on est sur une intersection
						if ((int)p.x == p.x)
						{
							// on regarde la case suivante dans la direction actuelle
							ge = labyrinthe.get(p.x - 1, p.y);

							// si c'est un obstacle, on passe la direction à NONE
							// et on ne bouge pas
							if (ge != null)
								this.pacman.setDirection(Direction.NONE);
							// sinon on peut avancer
							else
								p.x -= distance;
						}
						// sinon on peut avancer
						else
							p.x -= distance;
					}
					break;


				case RIGHT :
					// s'il y a changement de case
					if ((int)p.x != (int)(p.x + distance))
					{
						// si on a une demande de direction perpendiculaire
						if (dir_actuelle.estDirectionPerpendiculaire(this.derniere_commande))
						{
							// on récupère la case suivante en fonction de la direction demandée
							if (this.derniere_commande == Direction.DOWN)
								ge = labyrinthe.get(p.x + 1, p.y - 1);
							else
								ge = labyrinthe.get(p.x + 1, p.y + 1);

							// si la case suivante est un obstacle
							if (ge != null)
							{
								// on ignore la demande
								// et on regarde la case suivante (ici, 2 cases plus loin que l'actuelle)
								// dans la direction actuelle
								ge = labyrinthe.get(p.x + 2, p.y);

								// si c'est un obstacle, on passe la direction à NONE
								// et on aligne le pacman avec la case suivante
								if (ge != null)
								{
									p.x = (int)(p.x + distance);
									this.pacman.setDirection(Direction.NONE);
								}
								// sinon on peut avancer
								else
									p.x += distance;
							}
							// sinon si la case suivante est vide
							else
							{
								// on aligne le pacman avec la case suivante
								// et on change de direction
								p.x = (int)(p.x + distance);
								this.pacman.setDirection(this.derniere_commande);
							}
						}
						// sinon si on n'a aucune demande
						else
						{
							// on regarde la case suivante (ici, 2 cases plus loin que l'actuelle)
							// dans la direction actuelle
							ge = this.pacman.getWorld().getMaze().get(p.x + 2, p.y);

							// si c'est un obstacle, on passe la direction à NONE
							// et on aligne le pacman avec la case suivante
							if (ge != null)
							{
								p.x = (int)(p.x + distance);
								this.pacman.setDirection(Direction.NONE);
							}

							// sinon on peut avancer
							else
								p.x += distance;
						}
					}
					// sinon on regarde si on est sur une intersection
					else
					{
						// si on est sur une intersection
						if ((int)p.x == p.x)
						{
							// on regarde la case suivante dans la direction actuelle
							ge = labyrinthe.get(p.x + 1, p.y);

							// si c'est un obstacle, on passe la direction à NONE
							// et on ne bouge pas
							if (ge != null)
								this.pacman.setDirection(Direction.NONE);
							// sinon on peut avancer
							else
								p.x += distance;
						}
						// sinon on peut avancer
						else
							p.x += distance;
					}
					break;


				case DOWN :
					// s'il y a changement de case
					if ((int)p.y != (int)(p.y - distance))
					{
						// si on a une demande de direction perpendiculaire
						if (dir_actuelle.estDirectionPerpendiculaire(this.derniere_commande))
						{
							// on récupère la case suivante en fonction de la direction demandée
							if (this.derniere_commande == Direction.LEFT)
								ge = labyrinthe.get(p.x - 1, p.y);
							else
								ge = labyrinthe.get(p.x + 1, p.y);

							// si la case suivante est un obstacle
							if (ge != null)
							{
								// on ignore la demande
								// et on regarde la case suivante dans la direction actuelle
								ge = labyrinthe.get(p.x, p.y - 1);

								// si c'est un obstacle, on passe la direction à NONE
								// et on aligne le pacman avec sa case actuelle
								if (ge != null)
								{
									p.y = (int)p.y;
									this.pacman.setDirection(Direction.NONE);
								}
								// sinon on peut avancer
								else
									p.y -= distance;
							}
							// sinon si la case suivante est vide
							else
							{
								// on aligne le pacman avec sa case actuelle
								// et on change de direction
								p.y = (int)p.y;
								this.pacman.setDirection(this.derniere_commande);
							}
						}
						// sinon si on n'a aucune demande
						else
						{
							// on regarde la case suivante dans la direction actuelle
							ge = labyrinthe.get(p.x, p.y - 1);

							// si c'est un obstacle, on passe la direction à NONE
							// et on aligne le pacman avec la case suivante
							if (ge != null)
							{
								p.y = (int)p.y;
								this.pacman.setDirection(Direction.NONE);
							}
							// sinon on peut avancer
							else
								p.y -= distance;
						}
					}
					// sinon on regarde si on est sur une intersection
					else
					{
						// si on est sur une intersection
						if ((int)p.y == p.y)
						{
							// on regarde la case suivante dans la direction actuelle
							ge = labyrinthe.get(p.x, p.y - 1);

							// si c'est un obstacle, on passe la direction à NONE
							// et on ne bouge pas
							if (ge != null)
								this.pacman.setDirection(Direction.NONE);
							// sinon on peut avancer
							else
								p.y -= distance;
						}
						// sinon on peut avancer
						else
							p.y -= distance;
					}
					break;


				case UP :
					// s'il y a changement de case
					if ((int)p.y != (int)(p.y + distance))
					{
						// si on a une demande de direction perpendiculaire
						if (dir_actuelle.estDirectionPerpendiculaire(this.derniere_commande))
						{
							// on récupère la case suivante en fonction de la direction demandée
							if (this.derniere_commande == Direction.LEFT)
								ge = labyrinthe.get(p.x - 1, p.y + 1);
							else
								ge = labyrinthe.get(p.x + 1, p.y + 1);

							// si la case suivante est un obstacle
							if (ge != null)
							{
								// on ignore la demande
								// et on regarde la case suivante (ici, 2 cases plus loin que l'actuelle)
								// dans la direction actuelle
								ge = labyrinthe.get(p.x, p.y + 2);

								// si c'est un obstacle, on passe la direction à NONE
								// et on aligne le pacman avec la case suivante
								if (ge != null)
								{
									p.y = (int)(p.y + distance);
									this.pacman.setDirection(Direction.NONE);
								}
								// sinon on peut avancer
								else
									p.y += distance;
							}
							// sinon si la case suivante est vide
							else
							{
								// on aligne le pacman avec la case suivante
								// et on change de direction
								p.y = (int)(p.y + distance);
								this.pacman.setDirection(this.derniere_commande);
							}
						}
						// sinon si on n'a aucune demande
						else
						{
							// on regarde la case suivante (ici, 2 cases plus loin que l'actuelle)
							// dans la direction actuelle
							ge = labyrinthe.get(p.x, p.y + 2);

							// si c'est un obstacle, on passe la direction à NONE
							// et on aligne le pacman avec la case suivante
							if (ge != null)
							{
								p.y = (int)(p.y + distance);
								this.pacman.setDirection(Direction.NONE);
							}
							// sinon on peut avancer
							else
								p.y += distance;
						}
					}
					// sinon on regarde si on est sur une intersection
					else
					{
						// si on est sur une intersection
						if ((int)p.y == p.y)
						{
							// on regarde la case suivante dans la direction actuelle
							ge = labyrinthe.get(p.x, p.y + 1);

							// si c'est un obstacle, on passe la direction à NONE
							// et on ne bouge pas
							if (ge != null)
								this.pacman.setDirection(Direction.NONE);
							// sinon on peut avancer
							else
								p.y += distance;
						}
						// sinon on peut avancer
						else
							p.y += distance;
					}
					break;


				case NONE :
					return;
				default :
					// on ne devrait jamais tomber dans ce cas-là
					System.out.println("Erreur direction");
					break;
				}*/
	}


	private void detectionCollisionPacGomme()
	{
		float pos_actuelle_abs = this.pacman.getPosition().x;
		float pos_actuelle_ord = this.pacman.getPosition().y;
		
		float pos_cible_abs = pos_actuelle_abs;
		float pos_cible_ord = pos_actuelle_ord;
		
		boolean verif_position = false;
		
		switch (this.pacman.getDirection())
		{
		case LEFT :
			if (pos_actuelle_abs <= (0.6f + (int)pos_actuelle_abs))
				verif_position = true;
			break;
		case RIGHT :
			pos_cible_abs += 1f;
			if (pos_actuelle_abs >= (0.4f + (int)pos_actuelle_abs))
				verif_position = true;
			break;
		case DOWN :
			if (pos_actuelle_ord <= (0.6f + (int)pos_actuelle_ord))
				verif_position = true;
			break;
		case UP :
			pos_cible_ord += 1f;
			if (pos_actuelle_ord >= (0.4f + (int)pos_actuelle_ord))
				verif_position = true;
			break;
		case NONE :
			return;
		default :
			// on ne devrait jamais tomber dans ce cas-là
			System.out.println("Erreur direction");
		}
				
		GameElement ge = this.pacman.getWorld().getMaze().get(pos_cible_abs, pos_cible_ord);
		
		if (ge instanceof PacGomme)
		{
			PacGomme p = (PacGomme)ge;

			if (verif_position)
				if (!p.estMangee())
				{
					p.seFaitManger();
					this.pacman.getWorld().augmenterScore(1);
				}
		}		
	}

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

	public PacmanController(Pacman pacman)
	{
		this.pacman = pacman;
		this.derniere_commande = Direction.NONE;//this.pacman.getDirection();
	}


	public void setDerniereCommande(Direction derniere_commande)
	{
		this.derniere_commande = derniere_commande;
	}


	public void update(float delta)
	{
		// si la méthode retourne false, on se déplace sur une case
		// donc il est possible de rencontrer un pac-gomme 
		if (this.detectionCollisionBloc(delta) == false)
		{
			this.detectionCollisionPacGomme();
		}
	}
	
	

}
