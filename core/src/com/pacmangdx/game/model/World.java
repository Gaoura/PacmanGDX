package com.pacmangdx.game.model;

import java.awt.Point;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
	private Maze laby;

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
		this.laby = new Maze(this);
		this.pac = new Pacman(new Point(this.getWidth()/2, this.getHeight()/2), this);
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
	
	@Override
	public Iterator<GameElement> iterator()
	{
		return  new Iterator<GameElement>()
				{					
					private int i = 0;
					private Iterator<GameElement> iterator = laby.iterator();
					
					@Override
					public boolean hasNext()
					{
						// si on a pas encore retourner pacman
						if (i == 0)
							return pac != null;
						// sinon on regarde si laby a un suivant
						else
							return iterator.hasNext();
					}
					
					@Override
					public GameElement next()
					{
						if (!hasNext())
							throw new NoSuchElementException("Plus de GameElement");
						
						switch (i)
						{
						case 0 :
							i++;
							return pac;
						default : return iterator.next();
						}
					}
				};
	}
}