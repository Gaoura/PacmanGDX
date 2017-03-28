package com.pacmangdx.game.model;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.pacmangdx.game.controllers.AleatoireStrategie;
import com.pacmangdx.game.controllers.AlternativeStrategie;
import com.pacmangdx.game.controllers.PlusCourtAxeStrategie;

public class World implements Iterable<GameElement>
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

	private Pacman pac;
	private Fantome fantome1;
	private Fantome fantome2;
	private Fantome fantome3;
	//private Fantome fantome4;
	private Maze laby;
	private int score;
	private int nb_pac_gommes;
	

	private void newPersonnages()
	{
		boolean pacman = false;
		boolean fantome1 = false;
		boolean fantome2 = false;
		boolean fantome3 = false;
		FileHandle fh = Gdx.files.internal("map2.txt");
		BufferedReader br = new BufferedReader(fh.reader());
		String line;
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			while((line = br.readLine()) != null)
				list.add(line);

			int j = 0;
			while(j < list.size())
			{
				line = list.get(j);
				int i;
				for (i = 0; i < line.length(); i++)
					switch (line.charAt(i))
					{
					case 'C' :
						this.pac = new Pacman(new Point2D.Float(i, list.size() - 1 - j), this);
						pacman = true;
						break;
					case '1' :
						this.fantome1 = new Fantome(new Point2D.Float(i, list.size() - 1 - j),
														this, new AleatoireStrategie());
						fantome1 = true;
						break;
					case '2' :
						this.fantome2 = new Fantome(new Point2D.Float(i, list.size() - 1 - j),
														this, new PlusCourtAxeStrategie());
						fantome2 = true;
						break;
					case '3' :
						this.fantome3 = new Fantome(new Point2D.Float(i, list.size() - 1 - j),
														this, new AlternativeStrategie());
						fantome3 = true;
						break;
					case '4' :
					default:
						break;
					}
				j++;
			}
			
			if (!(pacman && fantome1 && fantome2 && fantome3))
				System.out.println("Erreur création personnages");
		}
		catch (IOException e)
		{
			e.printStackTrace();
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

	public World()
	{		
		this.score = 0;
		this.nb_pac_gommes = 0;
		this.laby = new Maze(this);
		this.newPersonnages();
		
		this.pac.setDirection(Direction.NONE);
		this.fantome1.setDirection(Direction.UP);
		this.fantome2.setDirection(Direction.UP);
		this.fantome3.setDirection(Direction.UP);
	}

	public int getHeight()
	{
		return laby.getHeight();

	}

	public int getWidth()
	{
		return laby.getWidth();
	}

	public Maze getMaze()
	{
		return this.laby;
	}

	public Pacman getPacman()
	{
		return this.pac;
	}
	
	public void augmenterScore(int points)
	{
		this.score += points;
	}
	
	public int getScore()
	{
		return this.score;
	}

	public int getNbPacGommes()
	{
		return this.nb_pac_gommes;
	}

	public void incrementerNbPacGommes()
	{
		this.nb_pac_gommes++;
	}
	
	public void decrementerNbPacGommes()
	{
		this.nb_pac_gommes--;
	}
	
	public Fantome getFantome1()
	{
		return this.fantome1;
	}
	
	public Fantome getFantome2()
	{
		return this.fantome2;
	}
	
	public Fantome getFantome3()
	{
		return this.fantome3;
	}

	@Override
	public Iterator<GameElement> iterator()
	{
		return  new Iterator<GameElement>()
				{
					private int i = 0;
					private Iterator<GameElement> iterator = laby.iterator();

					/*@Override
					public boolean hasNext()
					{
						// si on a pas encore retourner pacman
						if (i == 0)
							return pac != null;
						// sinon on regarde si laby a un suivant
						else
							return iterator.hasNext();
					}*/

					@Override
					public boolean hasNext()
					{
						// on regarde si laby a un suivant
						if (iterator.hasNext())
							return iterator.hasNext();
						else
							// sinon on regarde si on a retourné tous les persos 
							return i < 4;
					}

					/*@Override
					public GameElement next()
					{
						if (!hasNext())
							throw new NoSuchElementException("Plus de GameElement");

						switch (i)
						{
						case 0 :
							i++;
							return pac.pacman;
						default : return iterator.next();
						}
					}*/

					@Override
					public GameElement next()
					{
						if (!hasNext())
							throw new NoSuchElementException("Plus de GameElement");

						if (iterator.hasNext())
							return iterator.next();
						else
							switch(i)
							{
							case 0 :
								i++;
								return pac;
							case 1 :
								i++;
								return fantome1;
							case 2 :
								i++;
								return fantome2;
							case 3 :
								i++;
								return fantome3;
							default :
								return null;
							}
					}
				};
	}

}
